package br.com.sicoob.capes.integracao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.GEDIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.vo.TipoDocumentoVO;
import br.com.sicoob.capes.integracao.negocio.servicos.GEDIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.locator.CAPESIntegracaoServiceLocator;
import br.com.sicoob.capes.integracao.negocio.vo.DocumentoGEDVO;

/**
 * @author Rodrigo.Chaves
 */
public class GEDIntegracaoDelegate extends CAPESIntegracaoDelegate<GEDIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GEDIntegracaoServico localizarServico() {
		return CAPESIntegracaoServiceLocator.getInstance().localizarGEDIntegracaoServico();
	}

	/**
	 * Marca documentos expurgo por sistema.
	 * 
	 * @param idDocumentos
	 *            the id documentos
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void marcaDocumentosExpurgoPorSistema(List<Long> idDocumentos) throws BancoobException {
		getServico().marcaDocumentosExpurgoPorSistema(idDocumentos);
	}

	/**
	 * Associar documentos dossie.
	 * 
	 * @param dados
	 *            the dados
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void associarDocumentosDossie(GEDIntegracaoDTO dados) throws BancoobException {
		getServico().associarDocumentosDossie(dados);
	}

	/**
	 * Desassociar documentos dossie.
	 * 
	 * @param idRegistroControlado
	 *            the id registro controlado
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void desassociarDocumentosDossie(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException {
		getServico().desassociarDocumentosDossie(idRegistroControlado, codigoTipoPessoa);
	}

	/**
	 * Listar tipo documento.
	 * 
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<TipoDocumentoVO> listarTipoDocumento() throws BancoobException {
		return getServico().listarTipoDocumento();
	}

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
	public List<Long> listarIDDocumentosAprovados(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException {
		return getServico().listarIDDocumentosAprovados(idRegistroControlado, codigoTipoPessoa);
	}
	
	/**
	 * Obtém os tipos de documento por siglaClasseDocumento.
	 * 
	 * @param siglaClasseDocumento
	 * @return
	 * @throws BancoobException
	 */
	public List<TipoDocumentoVO> obterTiposDocumentoPorSiglaClasseDocumento(String siglaAssunto, String siglaClasseDocumento) throws BancoobException {
		return getServico().obterTiposDocumentoPorSiglaClasseDocumento(siglaAssunto, siglaClasseDocumento);
	}
	
	/**
	 * Envia documentos para o GED. Utilizado apenas no Frontoffice, onde não
	 * temos o componente de envio de documentos do GED.
	 * 
	 * @param documentos
	 * @return
	 * @throws BancoobException
	 */
	public List<Long> enviarDocumentosGED(List<DocumentoGEDVO> documentos) throws BancoobException {
		return getServico().enviarDocumentosGED(documentos);
	}
	
	public List<Number> recuperarDocumentosGED(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException {
		return getServico().recuperarDocumentosGED(idRegistroControlado, codigoTipoPessoa);
	}

}