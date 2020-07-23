package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.estrategias.EstrategiaAutorizacaoContext;
import br.com.sicoob.capes.cadastro.negocio.excecao.ExclusaoBemPessoaCompartilhamentoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroPendenteAprovacaoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemAntigoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.BemPessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.BemPessoaCompartilhamentoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemParceriasBemVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.BemPessoaCompartilhamentoDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.vo.ControleVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * Classe dos serviços do BemPessoaCompartilhamento e auxiliares.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local(BemPessoaCompartilhamentoServicoLocal.class)
@Remote(BemPessoaCompartilhamentoServicoRemote.class)
public class BemPessoaCompartilhamentoServicoEJB extends EntidadeCadastroServicoEJB<BemPessoaCompartilhamento> implements BemPessoaCompartilhamentoServicoLocal, BemPessoaCompartilhamentoServicoRemote {

	@Inject
	@Default
	private BemPessoaCompartilhamentoDAO dao;
	
	@EJB
	private BemAntigoServicoLocal bemAntigoServico;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPessoaCompartilhamentoDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(BemPessoaCompartilhamento objeto) throws BancoobException {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(BemPessoaCompartilhamento objeto) throws BancoobException {

	}
	
	/**
	 * {@inheritDoc}
	 */
	@IgnorarAutorizar
	@Override
	public void excluirEntidade(BemPessoaCompartilhamento objeto) throws BancoobException {
		Bem bem = objeto.getBem();
		
		// Verifica se é um bem interno.
		if (bem.getInterno()) {
			excluirBemPessoaCompartilhamentoSemAutorizacao(objeto);
			
			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> lista = 
					bemAntigoServico.obterPorIdBemNovo(objeto.getPessoaCompartilhamento(), objeto.getIdBem());
			
			if (lista != null && !lista.isEmpty()) {
				br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemAntigo = lista.get(0);
				bemAntigoServico.excluirEntidade(bemAntigo);
			}

			// Caso não seja um bem interno, o controle deverá ser feito pelo
			// bem, para que possa passar de forma correta pelos fluxos.. etc.
		} else {
			// Se o bem estiver em aprovação, cancela o fluxo.
			if(bem.getDataHoraInicio() == null && bem.getIdInstituicaoAtualizacao() != null) {
				// Obtém a autorização
				AutorizacaoDelegate autorizacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoDelegate();
				Autorizacao autorizacao = autorizacaoDelegate.obterPorEntidade(bem);
				
				// Cancela o fluxo.
				ControleVO controle = new ControleVO();
				controle.setCodigo(Constantes.Negocio.GFT_CODIGO_CONTROLE_REJEITAR_ALTERACOES);
				TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
				EstrategiaAutorizacaoContext contexto = new EstrategiaAutorizacaoContext(tipoAutorizacao, controle);
				contexto.executar(autorizacao);
				
				// Se o bem não estiver em aprovação, lança uma exceção pois,
				// nesse momento ele não pode excluir um compartilhamento pela tela principal.
				// Deverá ser excluído na tela de alteração, pois o bem deverá ter seu percentual modificado.
			} else {
				throw new ExclusaoBemPessoaCompartilhamentoException();
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void excluirCompartilhamentosMarcados(Bem bem) throws BancoobException {
		getDAO().incluirHistoricoBemPessoaCompartilhamento(bem.getId(), obterUsuario().getLogin());
		getDAO().excluirCompartilhamentosMarcados(bem.getId());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void desmarcarResponsaveisBem(Long idBem) throws BancoobException {
		getDAO().desmarcarResponsaveisBem(idBem);
	}

	/**
	 * Método utilizado para excluir um Bem Interno.
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	@IgnorarAutorizar
	private void excluirBemPessoaCompartilhamentoSemAutorizacao(BemPessoaCompartilhamento objeto) throws BancoobException {
		getDAO().excluirEntidade(objeto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@IgnorarAutorizar
	public BemPessoaCompartilhamento incluirSemAutorizacao(BemPessoaCompartilhamento objeto) throws BancoobException {
		return getDAO().incluir(objeto);
	}
	
	/**
	 * Exclui o bem pessoa compartilhamento.
	 * 
	 * @param id
	 * @throws BancoobException
	 */
	@IgnorarAutorizar
	private void excluirBemPessoaCompartilhamentoSemAutorizacao(Long id) throws BancoobException {
		getDAO().excluir(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<DadosListagemBemVO> obterDadosListagem(Long idPessoaCompartilhamento) throws BancoobException {
		return getDAO().obterDadosListagem(idPessoaCompartilhamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<DadosListagemParceriasBemVO> obterDadosListagemParcerias(Long idPessoaCompartilhamento) throws BancoobException {
		return getDAO().obterDadosListagemParcerias(idPessoaCompartilhamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean pessoaPossuiBensCadastrados(Long idPessoaCompartilhamento) throws BancoobException {
		return getDAO().pessoaPossuiBensCadastrados(idPessoaCompartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@IgnorarAutorizar
	public void excluir(Long idBem, Long idBemPessoaCompartilhamento, Boolean bemInterno) throws BancoobException {
		// Verifica se é um bem interno.
		if (bemInterno) {
			BemPessoaCompartilhamento bemPessoaCompartilhamento = obter(idBemPessoaCompartilhamento);
			excluirBemPessoaCompartilhamentoSemAutorizacao(idBemPessoaCompartilhamento);

			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> lista = 
					bemAntigoServico.obterPorIdBemNovo(bemPessoaCompartilhamento.getPessoaCompartilhamento(), idBem);

			if (lista != null && !lista.isEmpty()) {
				br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemAntigo = lista.get(0);
				bemAntigoServico.excluirEntidadeSemAutorizacao(bemAntigo);
			}

			// Caso não seja um bem interno, o controle deverá ser feito pelo
			// bem, para que possa passar de forma correta pelos fluxos.. etc.
		} else {
			BemDelegate bemDelegate = CAPESCadastroFabricaDelegates.getInstance().criarBemDelegate();
			Bem bem = bemDelegate.obter(idBem);
			// Se o bem estiver em aprovação e ainda não foi encaminhado, cancela o fluxo.
			if (!isRegistroVigente(bem)) {
				SituacaoRegistroEnum situacao = bem.getSituacaoAprovacao();

				if (SituacaoRegistroEnum.BLOQUEADO.equals(situacao) || SituacaoRegistroEnum.DEVOLVIDO.equals(situacao)
						|| SituacaoRegistroEnum.EM_AUTORIZACAO.equals(situacao)) {
					throw new RegistroPendenteAprovacaoException();
				}

				// Obtém a autorização
				AutorizacaoDelegate autorizacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoDelegate();
				Autorizacao autorizacao = autorizacaoDelegate.obterPorEntidade(bem);
				
				// Cancela o fluxo.
				if(autorizacao != null) {
					ControleVO controle = new ControleVO();
					controle.setCodigo(Constantes.Negocio.GFT_CODIGO_CONTROLE_REJEITAR_ALTERACOES);
					TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
					EstrategiaAutorizacaoContext contexto = new EstrategiaAutorizacaoContext(tipoAutorizacao, controle);
					contexto.executar(autorizacao);
				}

				// Se o bem não estiver em aprovação, lança uma exceção pois, nesse momento ele não pode excluir um compartilhamento pela
				// tela principal. Deverá ser excluído na tela de alteração, pois o bem deverá ter seu percentual modificado.
			} else {
				throw new ExclusaoBemPessoaCompartilhamentoException();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public BemPessoaCompartilhamento obterBemPessoaCompartilhamentoInternoIdPessoaCompartilhamento(Long idPessoaCompartilhamento) throws BancoobException {
		return getDAO().obterBemPessoaCompartilhamentoInternoIdPessoaCompartilhamento(idPessoaCompartilhamento);
	}

}