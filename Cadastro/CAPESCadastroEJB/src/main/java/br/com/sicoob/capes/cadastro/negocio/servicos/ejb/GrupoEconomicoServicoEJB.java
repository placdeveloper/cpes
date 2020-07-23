/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaEmOutroGrupoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroMesmoAtributoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.PessoaInstituicaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.StatusTransferenciaGrupoEconomicoVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoDAO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Implementa as operacoes do servico de GrupoEconomico.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { GrupoEconomicoServicoLocal.class })
@Remote( { GrupoEconomicoServicoRemote.class })
public class GrupoEconomicoServicoEJB extends
		CAPESCadastroCrudServicoEJB<GrupoEconomico> implements GrupoEconomicoServicoRemote, GrupoEconomicoServicoLocal {

	@Inject
	@Default
	protected GrupoEconomicoDAO grupoEconomicoDAO;
	
	/** O atributo grupoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/GrupoEconomicoPessoaServico")
	private GrupoEconomicoPessoaServicoLocal grupoPessoaServico;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoDAO getDAO() {
		return grupoEconomicoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<GrupoEconomico> listarPorPessoa(Pessoa pessoa) {
		return getDAO().listarPorPessoa(pessoa);
	}
	
	/**
	 * O método Validar grupo economico.
	 *
	 * @param grupo o valor de grupo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarGrupoEconomico(final GrupoEconomico grupo) throws BancoobException {
		if (getDAO().isGrupoEconomicoExistente(grupo)) {
			throw new RegistroMesmoAtributoException("Grupo Econômico", "nome");
		}
		
		for (GrupoEconomicoPessoa grupoPessoa : grupo.getIntegrantes()) {
			try {
				validarPessoa(grupo, grupoPessoa.getPessoaInstituicao());
			} catch(PessoaEmOutroGrupoException e) {
				throw new PessoaEmOutroGrupoException(PessoaEmOutroGrupoException.MENSAGEM_GRUPO);
			}
		}
	}
	
	/**
	 * Valida a inclusao de um grupo economico
	 * @param grupo
	 * @throws BancoobException
	 */
	protected void validarIncluir(final GrupoEconomico grupo) throws BancoobException {
		validarGrupoEconomico(grupo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GrupoEconomico incluir(GrupoEconomico grupo) throws BancoobException {
		
		validarIncluir(grupo);
		grupo.setDataHoraCadastro(new DateTimeDB());
		List<GrupoEconomicoPessoa> pessoasEmOutroGrupo = tratarPessoasEmOutroGrupo(grupo);
		super.incluir(grupo);
		for (GrupoEconomicoPessoa pessoa : pessoasEmOutroGrupo) {
			grupoPessoaServico.alterar(pessoa);
		}
		return grupo;
	}

	/**
	 * O método Validar alterar.
	 *
	 * @param grupo o valor de grupo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarAlterar(final GrupoEconomico grupo) throws BancoobException {
		validarGrupoEconomico(grupo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(GrupoEconomico grupo) throws BancoobException {
		
		validarAlterar(grupo);
		for (GrupoEconomicoPessoa pessoa : tratarPessoasEmOutroGrupo(grupo)) {
			grupoPessoaServico.alterar(pessoa);
		}
		super.alterar(grupo);
	}

	/**
	 * Tratar pessoas em outro grupo.
	 *
	 * @param grupo o valor de grupo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<GrupoEconomicoPessoa> tratarPessoasEmOutroGrupo(GrupoEconomico grupo) throws BancoobException {
		
		List<GrupoEconomicoPessoa> participantes = null;
		
		// prepara lista de ids para recuperar pessoas que ja sao integrantes de algum grupo
		int index = 0;
		List<GrupoEconomicoPessoa> integrantes = new ArrayList<GrupoEconomicoPessoa>(
				grupo.getIntegrantes());
		Integer ids[] = new Integer[integrantes.size()];
		for (GrupoEconomicoPessoa grupoPessoa : integrantes) {
			ids[index++] = grupoPessoa.getPessoaInstituicao().getIdPessoaInstituicao();
		}
		
		if (ids.length > 0) {
			participantes = grupoPessoaServico.pesquisarPorPessoaInstituicao(ids);
			Iterator<GrupoEconomicoPessoa> itParticipantes = participantes.iterator();
			
			// percorre as pessoas que ja sao integrantes de algum grupo
			for (; itParticipantes.hasNext();) {
				GrupoEconomicoPessoa participante = itParticipantes.next();
				
				// se for integrante de outro grupo, altera o grupo...
				if (!participante.getGrupoEconomico().getId().equals(grupo.getId())) {
					participante.setGrupoEconomico(grupo);
					
					// ...e remove da lista do grupo
					Iterator<GrupoEconomicoPessoa> itIntegrantes = integrantes.iterator();
					for (; itIntegrantes.hasNext();) {
						GrupoEconomicoPessoa integrante = itIntegrantes.next();
						if (integrante.getPessoaInstituicao().getId().equals(
								participante.getPessoaInstituicao().getId())) {
							itIntegrantes.remove();
						}
					}
					grupo.getIntegrantes().clear();
					grupo.getIntegrantes().addAll(integrantes);
				} else {
					
					// senao, remove da lista de participantes
					itParticipantes.remove();
				}
			}
		} else {
			participantes = new ArrayList<GrupoEconomicoPessoa>();
		}
		return participantes;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void validarPessoa(final GrupoEconomico grupoEconomico, final PessoaInstituicao pessoa)
			throws BancoobException {

		CAPESCadastroServiceLocator locator;
		locator = CAPESCadastroServiceLocator.getInstance();
		
		PessoaInstituicaoServico servico = locator.localizarPessoaInstituicaoServico();
		
		PessoaInstituicao pessoaPersistente = servico.obter(pessoa.getIdPessoaInstituicao());
		List<GrupoEconomicoPessoa> grupos = new ArrayList<GrupoEconomicoPessoa>(
				pessoaPersistente.getGruposEconomicos());

		/*
		 * Limpa a lista de grupos, removendo os grupos de outras instituicoes e o grupo recebido
		 * como parametro
		 */
		for (Iterator<GrupoEconomicoPessoa> it = grupos.iterator(); it.hasNext();) {
			GrupoEconomico grupo = it.next().getGrupoEconomico();
			if (!grupo.getIdInstituicao().equals(grupoEconomico.getIdInstituicao()) 
					|| grupo.getIdGrupoEconomico().equals(grupoEconomico.getIdGrupoEconomico())) {
				it.remove();
			}
		}
		
		// para ser valida, apos a limpeza a lista de grupo deve estar vazia ou a pessoa deve 
		// estar marcada para trocar de grupo
		if (!grupos.isEmpty() 
				&& ((pessoa.getTrocarGrupo() == null) || !pessoa.getTrocarGrupo())) {
			List<GrupoEconomicoPessoa> list = new ArrayList<GrupoEconomicoPessoa>(grupos);
			throw new PessoaEmOutroGrupoException(PessoaEmOutroGrupoException.MENSAGEM_INDIVIDUAL,
					list.get(0).getGrupoEconomico().getDescricao());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<StatusTransferenciaGrupoEconomicoVO> obterStatusTransferenciaGrupoEconomico() throws BancoobException {
		Integer idInstituicao = Integer.valueOf(InformacoesUsuarioCAPES.getInstance().getIdInstituicao());
		return getDAO().obterStatusTransferenciaGrupoEconomico(idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<GrupoEconomico> gerarGruposEconomicos() throws BancoobException {
		return getDAO().gerarGruposEconomicos();
	}

	@Override
	public List<GrupoEconomico> obterListaGrupoEconomico(Integer idInstituicao) {
		return getDAO().obterListaGrupoEconomico(idInstituicao);
	}

}
