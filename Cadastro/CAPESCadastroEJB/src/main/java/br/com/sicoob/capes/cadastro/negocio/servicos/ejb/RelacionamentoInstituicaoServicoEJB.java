/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaIntegracaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.RelacionamentoInstituicaoDTO;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CadastroPossuiAutorizacaoInstituicaoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.InstituicaoGestoraCadastroException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.RelacionamentoInstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.RelacionamentoInstituicaoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ResponsavelCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.RelacionamentoInstituicaoDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.SCIIntegracaoServicoLocal;
import br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.PessoaDelegate;

/**
 * Servi�o utilizado para relacionamento das institui��es com pessoas.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { RelacionamentoInstituicaoServicoLocal.class })
@Remote( { RelacionamentoInstituicaoServicoRemote.class })
@IntegracaoGedGft
public class RelacionamentoInstituicaoServicoEJB extends CAPESCadastroServicoEJB
		implements RelacionamentoInstituicaoServicoRemote, RelacionamentoInstituicaoServicoLocal {

	/** O atributo servicoResponsavelCadastro. */
	@EJB(mappedName = "capes/cadastro/ResponsavelCadastroServico")
	private ResponsavelCadastroServicoLocal servicoResponsavelCadastro;
	
	/** O atributo servicoAutorizacaoCadastro. */
	@EJB(mappedName = "capes/cadastro/AutorizacaoCadastroServico")
	private AutorizacaoCadastroServicoLocal servicoAutorizacaoCadastro;
	
	/** O atributo servicoAutorizacao. */
	@EJB(mappedName = "capes/cadastro/AutorizacaoServico")
	private AutorizacaoServicoLocal servicoAutorizacao;
	
	/** O atributo servicoIntegracao. */
	@EJB(mappedName = "capes/integracao/SCIIntegracaoServico")
	private SCIIntegracaoServicoLocal servicoIntegracao;
	
	@Inject
	@Default
	private RelacionamentoInstituicaoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	public List<RelacionamentoInstituicaoDTO> listarRelacionamentoInstituicao(PessoaCompartilhamento pessoa)
			throws BancoobException {

		if(pessoa == null || pessoa.getIdPessoaCompartilhamento() == null) {
			throw new CampoNaoInformadoException("Pessoa");			
		}
		
		Integer idInstituicaoResponsavel = obterInstituicaoResponsavelCadastro(pessoa);
		List<RelacionamentoInstituicaoDTO> lista = 
				dao.listarRelacionamentoInstituicao(pessoa);

		return preencherDadosInstituicao(lista, idInstituicaoResponsavel);
	}
	
	/**
	 * Recupera o idInstituicao da institui��o respons�vel pelo cadastro da pessoa informada.
	 * 
	 * @param pessoa
	 *            A pessoa da qual se deseja o respons�vel pelo cadastro.
	 * @return O respons�vel pelo cadastro da pessoa informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	private Integer obterInstituicaoResponsavelCadastro(PessoaCompartilhamento pessoa) 
		throws BancoobException {

		Integer idInstituicao = null;

		try {
			
			ResponsavelCadastro responsavel = servicoResponsavelCadastro.consultar(pessoa);
			if(responsavel != null) {
				idInstituicao = responsavel.getIdInstituicao();
			}
			
		} catch (RegistroNaoEncontradoNegocioException ex) {
			debug("Respons�vel cadastro da pessoa " + pessoa.getIdPessoaCompartilhamento() + " n�o encontrado.");
		}

		return idInstituicao;
	}

	/**
	 * Preenche o nome das institui��es das lista.
	 * 
	 * @param lista
	 *            A lista com as transi��es da pessoa.
	 * @param idInstituicaoResponsavel
	 * 			  O identificador da institui��o respons�vel pelo cadastro.
	 * @return A lista com o nome das institui��es preenchidos.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	private List<RelacionamentoInstituicaoDTO> preencherDadosInstituicao(
			List<RelacionamentoInstituicaoDTO> lista, Integer idInstituicaoResponsavel) 
			throws BancoobException {

		Map<Integer, InstituicaoVO> mapa = new HashMap<Integer, InstituicaoVO>();
		SCIIntegracaoDelegate delegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		
		List<RelacionamentoInstituicaoDTO> listaRetorno = new ArrayList<RelacionamentoInstituicaoDTO>();
		
		for (RelacionamentoInstituicaoDTO dto : lista) {

			Instituicao instituicao = dto.getInstituicao();
			Integer idInstituicao = instituicao.getIdInstituicao();

			InstituicaoVO instituicaoSCI = null;
			if (!mapa.containsKey(idInstituicao)) {
				instituicaoSCI = delegate.obterInstituicaoPorId(idInstituicao);
				mapa.put(idInstituicao, instituicaoSCI);
			}

			instituicaoSCI = mapa.get(idInstituicao);
			if (instituicaoSCI != null) {
				instituicao.setNome(instituicaoSCI.getNome());
				instituicao.setNumero(String.valueOf(instituicaoSCI.getNumero()));
			}
			
			dto.setResponsavelCadastro(idInstituicao.equals(idInstituicaoResponsavel));
			
			listaRetorno.add(dto);
		}

		return listaRetorno;
	}

	/**
	 * N�o utilizado enquanto se define a quest�o do gestor do cadastro.
	 * Verifica se o idInstituicao informado � da institui��o gestora do cadastro do sistema 
	 * cooperativo ou n�o.
	 * @param idInstituicao
	 *            O idInstituicao a ser verificado.
	 * @return se o idInstituicao informado � da institui��o gestora do sistema cooperativo ou n�o.
	 * @throws BancoobException Caso ocorra alguma exce��o na consulta do sistema. 
	 */
//	private boolean isInstituicaoGestoraCadastroSistemaCooperativo(Integer idInstituicao) 
//			throws BancoobException {
//		
//		Integer idSistema = obterIdSistemaCooperativoUsuarioLogado();
//		
//		SistemaCooperativoDelegate delegate = 
//			SCIFabricaDelegates.getInstance().criarSistemaCooperativoDelegate();
//		SistemaCooperativo sistema = delegate.obter(idSistema);
//		
//		Integer idInstituicaoGestora = sistema.getIdInstGestoraCadastro().getIdInstituicao();
//		return idInstituicao.equals(idInstituicaoGestora);
//	}

	/**
	 * {@inheritDoc}
	 */
	public void alterarGestorCadastro(ResponsavelCadastro novoResponsavel) throws BancoobException {
		PessoaCompartilhamento pessoa = novoResponsavel.getPessoaCompartilhamento();
		Integer novoIdInstituicao = novoResponsavel.getIdInstituicao();
		
		ResponsavelCadastro responsavel = servicoResponsavelCadastro.consultar(pessoa);
		Integer idInstituicao = responsavel.getIdInstituicao();
		
		// Verifica se a pessoa � PJ
		if (TipoPessoaEnum.isPessoaJuridica(pessoa.getPessoa().getTipoPessoa().getCodTipoPessoa())) {
			// Verifica se O CNPJ � de uma cooperativa ativa
			if (servicoIntegracao.isCooperativaAtiva(pessoa.getPessoa().getCpfCnpj())) {
				// Verifica se a cooperativa � a 0001, se for impede a mudan�a!
				if (idInstituicao.equals(Constantes.Comum.ID_INSTITUICAO_BANCOOB)) {
					throw new CAPESCadastroNegocioException(
							"N�o � poss�vel transferir a responsabilidade do cadastro, pois, esta cooperativa est� no SCI com situa��o ativa.");
				}
			}
		}
		
		if(servicoAutorizacao.isPessoaPossuiAutorizacao(pessoa.getPessoa().getIdPessoa())) {
			throw new CadastroPossuiAutorizacaoInstituicaoException();
		}
		
		if(idInstituicao.equals(novoIdInstituicao)) {
			throw new InstituicaoGestoraCadastroException();
		}
		
		DadosConfiguracaoFluxo config = servicoAutorizacaoCadastro.obterConfiguracoesFluxo(responsavel);
		
		//Se a altera��o partir da institui��o respons�vel pelo cadastro ou o usu�rio for gestor, n�o ser� necess�ria a aprova��o.
		if(config.getIsResponsavel() || config.getIsGestor()){
			Instituicao instituicao = new Instituicao();
			instituicao.setIdInstituicao(novoResponsavel.getIdInstituicao());
			servicoResponsavelCadastro.alterar(responsavel, instituicao);
		}else{
			//responsavel.setIdInstituicao(novoResponsavel.getIdInstituicao());
			//servicoResponsavelCadastro.alterar(responsavel);
			//TODO: Tratar a altera��o do respons�vel por outra institui��o
			throw new CAPESCadastroNegocioException("A altera��o s� pode ser realizada pelo respons�vel do cadastro.");
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizarAssinaturaFotoBancoob(PessoaCompartilhamento pessoa)
			throws BancoobException {
		final Integer idInstituicaoResponsavel = obterInstituicaoResponsavelCadastro(pessoa);
		final Integer idInstituicaoUsuario = obterInstituicaoUsuarioLogado().getIdInstituicao();
		// verifica se a instituicao do usuario logado � igual a responsavel pelo cadastro da pessoa ou institui��o bancoob
		if(idInstituicaoUsuario.equals(idInstituicaoResponsavel)
				|| idInstituicaoUsuario.equals(Constantes.Comum.ID_INSTITUICAO_BANCOOB)){
			final PessoaIntegracaoDelegate pessoaIntegracaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaIntegracaoDelegate();
			final PessoaDelegate pessoaDelegate = CAPESReplicacaoComumFabricaDelegates.getInstance().criarPessoaDelegate();
			// pesquisando id da pessoa na base da instituicao responsavel
			final Integer idPessoa = pessoaIntegracaoDelegate.obterPessoaPlataforma(pessoa.getPessoa().getId(), idInstituicaoResponsavel).getIdPessoaLegado();
			// atualizando assiantura/foto da pessoa na base 1 de acordo com os dados da institui��o respons�vel
			pessoaDelegate.atualizarAssinaturaFotoBancoob(idPessoa, idInstituicaoResponsavel);
		} else {
			throw new CAPESCadastroNegocioException("Esta institui��o n�o � a respons�vel por este cadastro. Para atualizar a assinatura/foto no Bancoob procure a institui��o respons�vel.");
		}
	}

}
