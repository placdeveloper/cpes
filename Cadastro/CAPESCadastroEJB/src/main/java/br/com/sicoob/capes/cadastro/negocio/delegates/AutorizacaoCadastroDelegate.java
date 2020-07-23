package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.sicoob.capes.cadastro.negocio.servicos.AutorizacaoCadastroServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.negocio.vo.TipoDocumentoVO;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;

/**
 * Business delegate para o cadastro de autorizações no sistema.
 * 
 * @author juan.damasceno
 */
public class AutorizacaoCadastroDelegate extends CAPESCadastroDelegate<AutorizacaoCadastroServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AutorizacaoCadastroServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarAutorizacaoCadastroServico();
	}

	/**
	 * Verifica se eh necessario autorizacao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return {@code true}, se for necessario autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public boolean isNecessarioAutorizacao(Aprovavel aprovavel) throws BancoobException {
		return getServico().isNecessarioAutorizacao(aprovavel);
	}
	
	/**
	 * Lista tipos documento.
	 *
	 * @param sigla o valor de sigla
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoDocumentoVO> listaTiposDocumento(String sigla) throws BancoobException {
		return getServico().listaTiposDocumento(sigla);
	}
	
	/**
	 * Verifica se eh usuario gestor cadastro.
	 *
	 * @return {@code true}, se for usuario gestor cadastro
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public boolean isUsuarioGestorCadastro() throws BancoobException {
		return getServico().isUsuarioGestorCadastro();
	}
	
	/**
	 * O método Marcar em alteracao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param idInstituicaoAtualizacao o valor de id instituicao atualizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void marcarEmAlteracao(Aprovavel aprovavel, Integer idInstituicaoAtualizacao) throws BancoobException {
		getServico().marcarEmAlteracao(aprovavel, idInstituicaoAtualizacao);
	}
	
	/**
	 * Verifica se eh instituicao responsavel cadastro.
	 *
	 * @param entidadeAprovavel o valor de entidade aprovavel
	 * @param idInstituicao o valor de id instituicao
	 * @return {@code true}, se for instituicao responsavel cadastro
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public boolean isInstituicaoResponsavelCadastro(Aprovavel entidadeAprovavel,
			Integer idInstituicao) throws BancoobException {
		return getServico().isInstituicaoResponsavelCadastro(entidadeAprovavel, idInstituicao);
	}

	/**
	 * Verifica se eh gestor.
	 *
	 * @param usuario o valor de usuario
	 * @return {@code true}, se for gestor
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public boolean isGestor(UsuarioBancoob usuario) throws BancoobException {
		return getServico().isGestor(usuario);
	}
	
	/**
	 * Verifica se eh gestor cadastro instituicao.
	 *
	 * @param usuario o valor de usuario
	 * @return {@code true}, se for gestor cadastro instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public boolean isGestorCadastroInstituicao(UsuarioBancoob usuario) throws BancoobException {
		return getServico().isGestorCadastroInstituicao(usuario);
	}
	
	/**
	 * Obter instituicao responsavel.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return Instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Instituicao obterInstituicaoResponsavel(Aprovavel aprovavel) throws BancoobException {
		return getServico().obterInstituicaoResponsavel(aprovavel);
	}

	/**
	 * O método Gravar solicitacao aprovacao.
	 *
	 * @param <A> o tipo generico
	 * @param aprovavel o valor de aprovavel
	 * @param tipoOperacao o valor de tipo operacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public <A extends CAPESEntidade<Long> & Aprovavel> void gravarSolicitacaoAprovacao(
			A aprovavel, TipoOperacaoEnum tipoOperacao) throws BancoobException {
		
		getServico().gravarSolicitacaoAprovacao(aprovavel, tipoOperacao);
	}
	
	/**
	 * Obter configuracoes fluxo.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return DadosConfiguracaoFluxo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public DadosConfiguracaoFluxo obterConfiguracoesFluxo(Aprovavel aprovavel) throws BancoobException {
		return getServico().obterConfiguracoesFluxo(aprovavel);
	}

	/**
	 * Obter configuracoes fluxo. 
	 * 
	 * @param aprovavel
	 * @param usuario
	 * @return
	 * @throws BancoobException
	 */
	public DadosConfiguracaoFluxo obterConfiguracoesFluxo(Aprovavel aprovavel, UsuarioBancoob usuario) throws BancoobException {
		return getServico().obterConfiguracoesFluxo(aprovavel, usuario);
	}
	
	/**
	 * O método Excluir autorizacao rejeitada.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void excluirAutorizacaoRejeitada(Autorizacao autorizacao) throws BancoobException {
		getServico().excluirAutorizacaoRejeitada(autorizacao);
	}


	/**
	 * realiza a correcao em um registro que está em aprovacao e ainda nao foi
	 * enviado ao GFT ou foi devolvido para correcao
	 * 
	 * @param autorizacao
	 *            a autorizacao
	 * @param aprovavel
	 *            o registro
	 * @return {@code true} caso seja permitido
	 */
	public <A extends CAPESEntidade<Long> & Aprovavel> void
			corrigirAutorizacao(Autorizacao autorizacao, A aprovavel,
					TipoOperacaoEnum tipoOperacao) throws BancoobException {
		
		getServico().corrigirAutorizacao(autorizacao, aprovavel, tipoOperacao);
	}

	/**
	 * verifica se e permitido realizar a correcao de um registro enviado para
	 * autorizacao
	 * 
	 * @param autorizacao
	 *            a autorizacao
	 * @param aprovavel
	 *            o registro
	 * @return {@code true} caso seja permitido
	 */
	public Boolean isPermitidoCorrigir(Autorizacao autorizacao)	throws BancoobException {
		return getServico().isPermitidoCorrigir(autorizacao);
	}
	
	/**
	 * O método Corrigir documentos.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void corrigirDocumentos(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException {
		getServico().corrigirDocumentos(autorizacao, aprovavel);
	}
	
	/**
	 * Verifica se eh registro bloqueado alteracao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return {@code true}, se for registro bloqueado alteracao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public boolean isRegistroBloqueadoAlteracao(Aprovavel aprovavel) throws BancoobException {
		return getServico().isRegistroBloqueadoAlteracao(aprovavel);
	}

	/**
	 * Obter documentos comprobatorios.
	 *
	 * @param comprovavel o valor de comprovavel
	 * @return Set
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Set<DocumentoComprobatorio> obterDocumentosComprobatorios(Comprovavel comprovavel)
			throws BancoobException {
		return getServico().obterDocumentosComprobatorios(comprovavel);
	}
	
	/**
	 * O método Remover objeto sessao.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void removerObjetoSessao(Aprovavel objeto) throws BancoobException {
		getServico().removerObjetoSessao(objeto);
	}
	
}