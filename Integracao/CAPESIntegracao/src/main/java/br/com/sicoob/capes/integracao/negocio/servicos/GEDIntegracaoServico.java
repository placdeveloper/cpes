package br.com.sicoob.capes.integracao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.GEDIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.vo.TipoDocumentoVO;
import br.com.sicoob.capes.integracao.negocio.vo.DocumentoGEDVO;

/**
 * 
 * @author Rodrigo.Chaves
 */
public interface GEDIntegracaoServico extends CAPESIntegracaoServico {

	/**
	 * Marca documentos expurgo por sistema.
	 * 
	 * @param idDocumentos
	 *            the id documentos
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void marcaDocumentosExpurgoPorSistema(List<Long> idDocumentos) throws BancoobException;

	/**
	 * Associar documentos dossie.
	 * 
	 * @param parameterObject
	 *            the parameter object
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void associarDocumentosDossie(GEDIntegracaoDTO parameterObject) throws BancoobException;

	/**
	 * Listar tipo documento.
	 * 
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<TipoDocumentoVO> listarTipoDocumento() throws BancoobException;

	/**
	 * Listar id documentos aprovados.
	 * 
	 * @param idRegistroControlado
	 *            the id registro controlado
	 * @param codigoTipoPessoa
	 *            the codigo tipo pessoa
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<Long> listarIDDocumentosAprovados(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException;

	/**
	 * Desassociar documentos dossie.
	 * 
	 * @param idRegistroControlado
	 *            the id registro controlado
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void desassociarDocumentosDossie(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException;
	
	/**
	 * Obtém os tipos de documento por siglaClasseDocumento.
	 * 
	 * @param siglaClasseDocumento
	 * @return
	 * @throws BancoobException
	 */
	List<TipoDocumentoVO> obterTiposDocumentoPorSiglaClasseDocumento(String siglaAssunto, String siglaClasseDocumento) throws BancoobException;
	
	/**
	 * Envia documentos para o GED. Utilizado apenas no Frontoffice, onde não
	 * temos o componente de envio de documentos do GED.
	 * 
	 * @param documentos
	 * @return
	 * @throws BancoobException
	 */
	List<Long> enviarDocumentosGED(List<DocumentoGEDVO> documentos) throws BancoobException;
	
	/**
	 * Recuperar a lista de documentos no GED.
	 * 
	 * @param idRegistroControlado
	 * @param codigoTipoPessoa
	 * @return
	 * @throws BancoobException
	 */
	List<Number> recuperarDocumentosGED(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException;
	
}