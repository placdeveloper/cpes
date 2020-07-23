/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
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
 * Define as opera��es do servi�o de cadastro de autoriza��es.
 * 
 * @author juan.damasceno
 */
public interface AutorizacaoCadastroServico extends CAPESCadastroServico {

	/**
	 * Verifica se eh necessario autorizacao.
	 *
	 * @param entidadeAprovavel o valor de entidade aprovavel
	 * @return {@code true}, se for necessario autorizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	boolean isNecessarioAutorizacao(Aprovavel entidadeAprovavel) throws BancoobException;

	/**
	 * Lista tipos documento.
	 *
	 * @param siglaTipoDocumentoPai o valor de sigla tipo documento pai
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<TipoDocumentoVO> listaTiposDocumento(String siglaTipoDocumentoPai) throws BancoobException;


	/**
	 * Verifica se eh usuario gestor cadastro.
	 *
	 * @return {@code true}, se for usuario gestor cadastro
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	boolean isUsuarioGestorCadastro() throws BancoobException;

	/**
	 * O m�todo Marcar em alteracao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param idInstituicaoAtualizacao o valor de id instituicao atualizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void marcarEmAlteracao(Aprovavel aprovavel, Integer idInstituicaoAtualizacao)
			throws BancoobException;
	
	/**
	 * O m�todo Remover objeto sessao.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void removerObjetoSessao(Aprovavel objeto) throws BancoobException;

	/**
	 * Verifica se eh instituicao responsavel cadastro.
	 *
	 * @param entidadeAprovavel o valor de entidade aprovavel
	 * @param idInstituicao o valor de id instituicao
	 * @return {@code true}, se for instituicao responsavel cadastro
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	boolean isInstituicaoResponsavelCadastro(Aprovavel entidadeAprovavel, Integer idInstituicao)
			throws BancoobException;

	/**
	 * Verifica se eh gestor.
	 *
	 * @param usuario o valor de usuario
	 * @return {@code true}, se for gestor
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	boolean isGestor(UsuarioBancoob usuario) throws BancoobException;
	
	/**
	 * Verifica se eh gestor cadastro instituicao.
	 *
	 * @param usuario o valor de usuario
	 * @return {@code true}, se for gestor cadastro instituicao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	boolean isGestorCadastroInstituicao(UsuarioBancoob usuario) throws BancoobException;

	/**
	 * Obter instituicao responsavel.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return Instituicao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Instituicao obterInstituicaoResponsavel(Aprovavel aprovavel) throws BancoobException;

	/**
	 * O m�todo Gravar solicitacao aprovacao.
	 *
	 * @param <A> o tipo generico
	 * @param aprovavel o valor de aprovavel
	 * @param tipoOperacao o valor de tipo operacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	<A extends Aprovavel> void
			gravarSolicitacaoAprovacao(A aprovavel, TipoOperacaoEnum tipoOperacao)
					throws BancoobException;

	/**
	 * Obter configuracoes fluxo.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return DadosConfiguracaoFluxo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	DadosConfiguracaoFluxo obterConfiguracoesFluxo(Aprovavel aprovavel) throws BancoobException;

	/**
	 * Obter configuracoes fluxo.
	 * 
	 * @param aprovavel
	 * @param usuario
	 * @return
	 * @throws BancoobException
	 */
	DadosConfiguracaoFluxo obterConfiguracoesFluxo(Aprovavel aprovavel, UsuarioBancoob usuario) throws BancoobException;
	
	/**
	 * O m�todo Excluir autorizacao rejeitada.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void excluirAutorizacaoRejeitada(Autorizacao autorizacao) throws BancoobException;


	/**
	 * realiza a correcao em um registro que est� em aprovacao e ainda nao foi
	 * enviado ao GFT ou foi devolvido para correcao
	 * 
	 * @param autorizacao
	 *            a autorizacao
	 * @param aprovavel
	 *            o registro
	 * @return {@code true} caso seja permitido
	 */
	<A extends CAPESEntidade<Long> & Aprovavel> void
			corrigirAutorizacao(Autorizacao autorizacao, A aprovavel,
					TipoOperacaoEnum tipoOperacao) throws BancoobException;

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
	Boolean isPermitidoCorrigir(Autorizacao autorizacao) throws BancoobException;

	/**
	 * O m�todo Corrigir documentos.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void corrigirDocumentos(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException;

	/**
	 * Verifica se est� na vez do usu�rio fazer altera��o
	 * 
	 * @param aprovavel
	 * @param usuarioBancoob
	 * @return
	 * @throws BancoobException
	 */
	Boolean isRegistroBloqueadoAlteracao(Aprovavel aprovavel) throws BancoobException;

	/**
	 * Obter documentos comprobatorios.
	 *
	 * @param comprovavel o valor de comprovavel
	 * @return Set
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Set<DocumentoComprobatorio> obterDocumentosComprobatorios(Comprovavel comprovavel)
			throws BancoobException;


}
