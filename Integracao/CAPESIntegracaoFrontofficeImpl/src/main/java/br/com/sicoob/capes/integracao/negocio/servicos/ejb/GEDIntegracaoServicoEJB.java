package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.comum.negocio.dto.GEDIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.TipoDocumentoVO;
import br.com.sicoob.capes.comum.util.Constantes.Negocio;
import br.com.sicoob.capes.integracao.negocio.excecao.GEDIntegracaoNegocioException;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.GEDIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.GEDIntegracaoServicoRemote;
import br.com.sicoob.capes.integracao.negocio.vo.DocumentoGEDVO;
import br.com.sicoob.capes.integracao.negocio.vo.PaginaDocumentoGEDVO;
import br.com.sicoob.capes.integracao.negocio.vo.ValorChaveDocumentoGEDVO;
import br.com.sicoob.mcgdfo.negocio.delegates.DocumentoDelegate;
import br.com.sicoob.mcgdfo.negocio.delegates.MCGDFOFabricaDelegates;
import br.com.sicoob.mcgdfo.negocio.dto.DocumentoDTO;
import br.com.sicoob.mcgdfo.negocio.dto.SalvarDocumentosPorTipoDocumentoDTO;
import br.com.sicoob.mcgdfo.negocio.dto.SequencialDocumentoDTO;
import br.com.sicoob.mcgdfo.negocio.dto.TipoDocumentoDTO;
import br.com.sicoob.mcgdfo.negocio.dto.ValorChaveDTO;
import br.com.sicoob.mcgdfo.negocio.vo.TipoDocumentoFOVO;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiFabricaDelegates;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.GedApiFabricaFiltros;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.IFiltroSiglaSistemaIdRegistroControlado;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoDocumento;

/**
 * Classe com os serviços do GED.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local(GEDIntegracaoServicoLocal.class)
@Remote(GEDIntegracaoServicoRemote.class)
public class GEDIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements GEDIntegracaoServicoRemote, GEDIntegracaoServicoLocal {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void marcaDocumentosExpurgoPorSistema(List<Long> idDocumentos) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void associarDocumentosDossie(GEDIntegracaoDTO dados) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void desassociarDocumentosDossie(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean isDossieExistente(GEDIntegracaoDTO dados) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoDocumentoVO> listarTipoDocumento() throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Long> listarIDDocumentosAprovados(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoDocumentoVO> obterTiposDocumentoPorSiglaClasseDocumento(String siglaAssunto, String siglaClasseDocumento) throws BancoobException {
		DocumentoDelegate documentoDelegate = MCGDFOFabricaDelegates.getInstance().criarDocumentoDelegate();
		TipoDocumentoDTO documentoDTO = new TipoDocumentoDTO();
		documentoDTO.setSiglaAssunto(siglaAssunto);
		documentoDTO.setSiglaClasseDocumento(siglaClasseDocumento);
		Set<TipoDocumentoFOVO> tiposDocumento = documentoDelegate.recuperarTipoDocumento(documentoDTO);
		return converterTiposDocumento(tiposDocumento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> enviarDocumentosGED(List<DocumentoGEDVO> documentos) throws BancoobException {
		if (documentos != null) {
			getLogger().debug("Enviando os documentos para o GED");
			List<DocumentoDTO> retorno = new ArrayList<DocumentoDTO>();
			DocumentoDelegate documentoDelegate = MCGDFOFabricaDelegates.getInstance().criarDocumentoDelegate();
			for (DocumentoGEDVO documento : documentos) {
				retorno.addAll(documentoDelegate.salvarDocumentosPorTipoDocumento(new SalvarDocumentosPorTipoDocumentoDTO(documento.getIdTipoDocumento(), obterListaDocumentoGED(documento))));
			}

			if (retorno != null && !retorno.isEmpty()) {
				getLogger().debug("Retorno do metodo de salvar documentos: ", String.valueOf(retorno.size()));
				List<Long> ids = new ArrayList<Long>();
				for (DocumentoDTO documento : retorno) {
					ids.add(documento.getIdDocumento());
				}

				return ids;
			}
		}
		return null;
	}

	private List<DocumentoDTO> obterListaDocumentoGED(DocumentoGEDVO documento) {
		List<DocumentoDTO> listaDocumentoGED = new ArrayList<DocumentoDTO>();
		listaDocumentoGED.add(obterDocumentoGED(documento));
		return listaDocumentoGED;
	}

	private DocumentoDTO obterDocumentoGED(DocumentoGEDVO documento) {
		DocumentoDTO documentoGED = new DocumentoDTO();
		documentoGED.setIdModulo(documento.getIdModulo());
		documentoGED.setIdSistema(documento.getIdSistema());
		documentoGED.setIdInstituicao(documento.getIdInstituicao());
		documentoGED.setIdUnidadeInstituicao(documento.getIdUnidadeInstituicao());
		documentoGED.setIdUsuarioInclusao(documento.getIdUsuarioInclusao());
		documentoGED.setListaSequencialDocumentoDTO(obterListaSequencial(documento.getPaginas()));
		documentoGED.setListaValorChave(obterListaValoresChave(documento.getListaValorChave()));
		return documentoGED;
	}

	private List<SequencialDocumentoDTO> obterListaSequencial(List<PaginaDocumentoGEDVO> paginas) {
		List<SequencialDocumentoDTO> retorno = new ArrayList<SequencialDocumentoDTO>();
		if (paginas != null) {
			for (PaginaDocumentoGEDVO pagina : paginas) {
				SequencialDocumentoDTO sequencial = obterSequencialDocumento(pagina);
				retorno.add(sequencial);
			}
		}
		return retorno;
	}

	private SequencialDocumentoDTO obterSequencialDocumento(PaginaDocumentoGEDVO pagina) {
		SequencialDocumentoDTO retorno = new SequencialDocumentoDTO();
		retorno.setDescricaoExtensao(pagina.getDescricaoExtensao());
		retorno.setNomeArquivo(pagina.getNomeArquivo());
		retorno.setValorBinario(pagina.getValorBinario());
		return retorno;
	}

	private List<ValorChaveDTO> obterListaValoresChave(List<ValorChaveDocumentoGEDVO> listaValorChave) {
		List<ValorChaveDTO> retorno = new ArrayList<ValorChaveDTO>();
		if (listaValorChave != null) {
			for (ValorChaveDocumentoGEDVO chave : listaValorChave) {
				ValorChaveDTO valorChaveDTO = obterValorChaveDTO(chave);
				retorno.add(valorChaveDTO);
			}
		}
		return retorno;
	}

	private ValorChaveDTO obterValorChaveDTO(ValorChaveDocumentoGEDVO chave) {
		return new ValorChaveDTO(chave.getValorChave(), chave.getSiglaChaveDocumento());
	}
	
	private List<TipoDocumentoVO> converterTiposDocumento(Set<TipoDocumentoFOVO> tiposDocumento) {
		List<TipoDocumentoVO> retorno = new ArrayList<TipoDocumentoVO>();

		for (TipoDocumentoFOVO vo : tiposDocumento) {
			TipoDocumentoVO tipo = new TipoDocumentoVO();
			tipo.setId(vo.getIdTipoDocumento());
			tipo.setNome(vo.getDescTipoDocumento());
			retorno.add(tipo);
		}

		return retorno;
	}

	@Override
	public List<Number> recuperarDocumentosGED(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException {
		try {
			List<Number> listaDocumentosGED = new ArrayList<Number>();
			
			IFiltroSiglaSistemaIdRegistroControlado filtro = GedApiFabricaFiltros.getInstance()
					.criarFiltroSiglaSistemaIdRegistroControlado(idRegistroControlado, 
					TipoPessoaEnum.getSiglaAssuntoGED(codigoTipoPessoa), Negocio.CTA_SIGLA_SISTEMA);

			List<IRetornoDocumento> retorno = 
					GedApiFabricaDelegates.getInstance()
					.criarGedApiDocumentoDelegate().listarDocumentosAprovadosPorSistemaERegistroControlado(filtro);
			
			listaDocumentosGED = criarListaDocumentosGED(retorno);
			
			return listaDocumentosGED;
			
		} catch (NegocioException e) {
			throw new GEDIntegracaoNegocioException(e);
		}
	}
	
	private List<Number> criarListaDocumentosGED(List<IRetornoDocumento> listaRetornoDocumentos) {
		List<Number> lista = new ArrayList<Number>();

		for (IRetornoDocumento retorno : listaRetornoDocumentos) {
			lista.add(retorno.getIdTipoDocumento());
		}
		return lista;
	}

}