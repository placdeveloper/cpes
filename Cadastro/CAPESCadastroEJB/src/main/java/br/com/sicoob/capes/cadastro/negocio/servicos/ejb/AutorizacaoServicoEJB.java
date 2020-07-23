package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.estrategias.EstrategiaAutorizacaoContext;
import br.com.sicoob.capes.cadastro.negocio.excecao.AutorizacaoLockadaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.AutorizacaoDAO;
import br.com.sicoob.capes.cadastro.persistencia.excecao.ObterLockException;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.negocio.vo.ControleVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.GFTIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Implementa as operações do serviço de autorização
 *
 * @author Rodrigo.Chaves
 */
@Stateless
@Local({ AutorizacaoServicoLocal.class })
@Remote({ AutorizacaoServicoRemote.class })
public class AutorizacaoServicoEJB extends CAPESCadastroCrudServicoEJB<Autorizacao>
		implements AutorizacaoServicoRemote, AutorizacaoServicoLocal {

	@Inject
	@Default
	private AutorizacaoDAO autorizacaoDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AutorizacaoDAO getDAO() {
		return this.autorizacaoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesPendentes(ConsultaAutorizacaoDTO criterios)
			throws BancoobException {
		return this.autorizacaoDAO.pesquisarAutorizacoesPendentes(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesDevolvidas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException {
		return this.autorizacaoDAO.pesquisarAutorizacoesDevolvidas(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesEncaminhadas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException {
		return this.autorizacaoDAO.pesquisarAutorizacoesEncaminhadas(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesNaoEncaminhadas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {
		return this.autorizacaoDAO.pesquisarAutorizacoesNaoEncaminhadas(criterios);
	}

	/**
	 * @see br.com.sicoob.capes.cadastro.negocio.servicos.AutorizacaoServico#pesquisarAutorizacoesAptasEncaminhar(ConsultaAutorizacaoDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Autorizacao> pesquisarAutorizacoesAptasEncaminhar(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {
		return this.autorizacaoDAO.pesquisarAutorizacoesAptasEncaminhar(criterios);
	}
	
	/**
	 * @see AutorizacaoDAO#atualizaDataSolicitacao(Autorizacao)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizaDataSolicitacao(Autorizacao autorizacao)throws BancoobException {
		this.autorizacaoDAO.atualizaDataSolicitacao(autorizacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Autorizacao obterPorEntidade(Aprovavel aprovavel) throws BancoobException {
		return this.autorizacaoDAO.obterPorEntidade(aprovavel);
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterar(Autorizacao autorizacao, Collection<AutorizacaoDocumento> documentos)
			throws BancoobException {

		this.autorizacaoDAO.alterar(autorizacao, documentos);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Autorizacao obterDocumentosComprobatoriosEmAutorizacao(ConsultaAutorizacaoDTO criterios) throws BancoobException{
		return this.autorizacaoDAO.obterDocumentosComprobatoriosEmAutorizacao(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TipoOperacaoEnum obterTipoOperacaoAutorizacao(Aprovavel aprovavel) throws BancoobException {
		return this.autorizacaoDAO.obterTipoOperacaoAutorizacao(aprovavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deletarListaDocumentos(Long idAutorizacao) {
		this.autorizacaoDAO.deletarListaDocumentos(idAutorizacao);
	}

	/**
	 * {@inheritDoc}
	 */
	public void marcarDevolvido(Autorizacao autorizacao, Boolean devolvido) throws BancoobException {
		getDAO().marcarDevolvido(autorizacao, devolvido);
	}

	/**
	 * {@inheritDoc}
	 */
	public ConsultaDto<Autorizacao> obterListaAutorizacoesVencidas(ConsultaDto<Autorizacao> criterios) throws BancoobException {
		return getDAO().obterListaAutorizacoesVencidas(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	public Autorizacao obterComLock(Serializable chave) throws BancoobException {
		try {
			return getDAO().obterComLock(chave);
		} catch (ObterLockException e) {
			throw new AutorizacaoLockadaException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isPessoaPendenteAprovacao(PessoaCompartilhamento pessoa) throws BancoobException {
		return this.autorizacaoDAO.isPessoaPendenteAprovacao(pessoa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isPessoaPendenteAprovacaoAutorizacao(PessoaCompartilhamento pessoa, Autorizacao autorizacao) {
		return this.autorizacaoDAO.isPessoaPendenteAprovacaoAutorizacao(pessoa, autorizacao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void cancelarAutorizacaoVencida(Autorizacao autorizacao) throws BancoobException {
		cancelarAutorizacaoVencida(autorizacao, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void cancelarAutorizacaoVencida(Autorizacao autorizacao, String justificativa) throws BancoobException {
		// cancela o fluxo
		if (autorizacao.getDataHoraSolicitacao() != null) {
			GFTIntegracaoDelegate gftDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarGFTIntegracaoDelegate();
			gftDelegate.cancelarFluxo(autorizacao.getSiglaProcesso(), autorizacao.getIdRegistroControlado(), justificativa);
		}

		// desfaz a alteração
		ControleVO controle = new ControleVO();
		controle.setCodigo(Constantes.Negocio.GFT_CODIGO_CONTROLE_REJEITAR_ALTERACOES);
		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
		EstrategiaAutorizacaoContext contexto = new EstrategiaAutorizacaoContext(tipoAutorizacao, controle);
		contexto.executar(autorizacao);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isPessoaPossuiAutorizacao(Integer idPessoa) throws BancoobException {
		return autorizacaoDAO.isPessoaPossuiAutorizacao(idPessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	public Autorizacao obterAutorizacaoNaoNula(Serializable chave) throws BancoobException {
		Autorizacao autorizacao = obter(chave);

		if (autorizacao == null) {
			throw new RegistroNaoEncontradoNegocioException("Registro de AUTORIZAÇÃO");
		}

		return autorizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	public ConsultaAutorizacaoDTO obterAutorizacoesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		return this.autorizacaoDAO.obterAutorizacoesAutoAtendimento(criterios);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws BancoobException 
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Autorizacao obterAutorizacaoPorIdRegistroNovo(Long IdRegistroNovo, Boolean devolvido) throws BancoobException {
		return this.autorizacaoDAO.obterAutorizacaoPorIdRegistroNovo(IdRegistroNovo, devolvido);
	}
}