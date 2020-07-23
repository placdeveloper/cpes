/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.InclusaoPessoaLegadoFachada;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ResponsavelCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ResponsavelCadastroServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.ResponsavelCadastroDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Implementa as operações do serviço de ResponsavelCadastro.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { ResponsavelCadastroServicoLocal.class })
@Remote( { ResponsavelCadastroServicoRemote.class })
@IntegracaoGedGft
public class ResponsavelCadastroServicoEJB extends EntidadeCadastroServicoEJB<ResponsavelCadastro>
		implements ResponsavelCadastroServicoRemote, ResponsavelCadastroServicoLocal {

	@Inject
	@Default
	protected ResponsavelCadastroDAO dao;
	
	
	/** O atributo transicaoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/TransicaoPessoaServico") 
	private TransicaoPessoaServicoLocal transicaoPessoaServico;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ResponsavelCadastroDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponsavelCadastro consultar(PessoaCompartilhamento pessoa) throws BancoobException {
		
		ResponsavelCadastro responsavel = null;
		
		try {
			responsavel = getDAO().consultar(pessoa);
		} catch (RegistroNaoEncontradoException e) {
			throw new RegistroNaoEncontradoNegocioException(e, "Responsável pelo cadastro");
		}
		return responsavel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(ResponsavelCadastro objeto) throws BancoobException {
		// Não existe regra para inclusão.
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(ResponsavelCadastro objeto) throws BancoobException {
		// Não existe regra para alteração.
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public ResponsavelCadastro incluir(ResponsavelCadastro responsavel) throws BancoobException {
		atualizarCooperativaOrigem(responsavel);
		return getDAO().incluir(responsavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@IgnorarAutorizar
	public void alterar(ResponsavelCadastro responsavel, Instituicao instituicao) throws BancoobException {
		responsavel.setIdInstituicao(instituicao.getIdInstituicao());
		atualizarCooperativaOrigem(responsavel);
		getDAO().alterar(responsavel);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(ResponsavelCadastro responsavel) throws BancoobException {
		atualizarCooperativaOrigem(responsavel);
		super.alterar(responsavel);
	}

	/**
	 * O método Atualizar cooperativa origem.
	 *
	 * @param responsavel o valor de responsavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void atualizarCooperativaOrigem(ResponsavelCadastro responsavel) throws BancoobException {
		
		Integer idInstituicaoResponsavel = responsavel.getIdInstituicao();
		PessoaCompartilhamento pessoaCompartilhamento = responsavel.getPessoaCompartilhamento();
		
		if(!Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(idInstituicaoResponsavel)) { 

			TransicaoPessoa transicaoBancoob = transicaoPessoaServico.obterTransicaoBancoob(pessoaCompartilhamento);
			if(transicaoBancoob != null) {
				SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
				Integer numCooperativa = sciDelegate.obterNumeroCooperativa(idInstituicaoResponsavel);
				InclusaoPessoaLegadoFachada<PessoaCompartilhamento> fachada = 
						new InclusaoPessoaLegadoFachada<PessoaCompartilhamento>();
				fachada.atualizarCooperativaOrigem(transicaoBancoob, numCooperativa);				
			}
		}
	}

}
