package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import static br.com.sicoob.capes.comum.util.Constantes.Negocio.GED_SIGLA_CHAVE_NEGOCIO_GRUPO_COMPARTILHAMENTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiDocumentoDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiDossieDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiFabricaDelegates;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiTipoDocumentoDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.GedApiFabricaFiltros;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.IFiltroSiglaSistemaIdRegistroControlado;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.IFiltroValorChaveNegocio;
import br.com.sicoob.sisbr.ged.api.negocio.dto.parametro.GedApiFabricaParametro;
import br.com.sicoob.sisbr.ged.api.negocio.dto.parametro.IParametroDossie;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoDocumento;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoTipoDocumento;

/**
 * @author Rodrigo.Chaves
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
		try {
			getLogger().debug("[CAPES] marcaDocumentosExpurgoPorSistema - siglaSistema: ", Negocio.CTA_SIGLA_SISTEMA, ", idDocumentos: ", Arrays.toString(idDocumentos.toArray()));
			GedApiDocumentoDelegate documentoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiDocumentoDelegate();
			documentoDelegate.marcaDocumentosExpurgo(GedApiFabricaFiltros.getInstance().criarFiltroMarcarDocumentoExpurgo(null, Negocio.CTA_SIGLA_SISTEMA, idDocumentos, null));
		} catch (NegocioException e) {
			throw new GEDIntegracaoNegocioException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void associarDocumentosDossie(GEDIntegracaoDTO dados) throws BancoobException {
		for (Long idDocumento : dados.getListaIdDocumento()) {
			getLogger().debug("Anexando o documento \"", String.valueOf(idDocumento), "\" ao dossie da pessoa (", String.valueOf(dados.getIdPessoa()), ")");

			try {
				IFiltroValorChaveNegocio chaveSiglaAssunto = GedApiFabricaFiltros.getInstance().criarFiltroValorChaveNegocio(null, obterSiglaChaveNegocioCpfCnpj(dados.getCodTipoPessoa()), dados.getCpfCnpj());
				IFiltroValorChaveNegocio chaveGrupoCompartilhamento = GedApiFabricaFiltros.getInstance().criarFiltroValorChaveNegocio(null, GED_SIGLA_CHAVE_NEGOCIO_GRUPO_COMPARTILHAMENTO, dados.getCodCompartilhamentoCadastro().toString());
				List<IFiltroValorChaveNegocio> listaChavesNegocio = new ArrayList<IFiltroValorChaveNegocio>();
				listaChavesNegocio.add(chaveSiglaAssunto);
				listaChavesNegocio.add(chaveGrupoCompartilhamento);

				IParametroDossie parametro = GedApiFabricaParametro.getInstance().criarParametroDossieCriarDossieEAssociarDocumento(idDocumento, dados.getIdRegistroControlado(), TipoPessoaEnum.getSiglaAssuntoGED(dados.getCodTipoPessoa()), Negocio.CTA_SIGLA_SISTEMA, listaChavesNegocio, null);
				GedApiDossieDelegate dossieDelegate = GedApiFabricaDelegates.getInstance().criarGedApiDossieDelegate();
				dossieDelegate.criarDossieEAssociarDocumento(parametro);
			} catch (NegocioException e) {
				throw new GEDIntegracaoNegocioException(e);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void desassociarDocumentosDossie(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException {
		getLogger().info("Desassociando documentos do dossie: ", idRegistroControlado);
		try {
			IParametroDossie param = GedApiFabricaParametro.getInstance().criarParametroDossieDesvincularDocumentoDoDossiePorSiglaSistemaIdRegControlado(idRegistroControlado, TipoPessoaEnum.getSiglaAssuntoGED(codigoTipoPessoa), Negocio.CTA_SIGLA_SISTEMA);
			GedApiDossieDelegate dossieDelegate = GedApiFabricaDelegates.getInstance().criarGedApiDossieDelegate();
			dossieDelegate.desvincularDocumentoDoDossiePorSiglaSistemaIdRegControlado(param);
		} catch (NegocioException e) {
			throw new GEDIntegracaoNegocioException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoDocumentoVO> listarTipoDocumento() throws BancoobException {
		List<TipoDocumentoVO> retorno = new LinkedList<TipoDocumentoVO>();
		
		try {
			GedApiTipoDocumentoDelegate tipoDocumentoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiTipoDocumentoDelegate();
			for (IRetornoTipoDocumento tipoDocumento : tipoDocumentoDelegate.listar()) {
				TipoDocumentoVO tipoDocumentoVO = new TipoDocumentoVO();
				tipoDocumentoVO.setId(tipoDocumento.getIdTipoDocumento());
				tipoDocumentoVO.setNome(tipoDocumento.getNomeTipoDocumento());
				retorno.add(tipoDocumentoVO);
			}

			return retorno;
		} catch (NegocioException e) {
			throw new GEDIntegracaoNegocioException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Long> listarIDDocumentosAprovados(String idRegistroControlado, Short codigoTipoPessoa) throws BancoobException {
		try {
			IFiltroSiglaSistemaIdRegistroControlado filtro = GedApiFabricaFiltros.getInstance()
					.criarFiltroSiglaSistemaIdRegistroControlado(idRegistroControlado, TipoPessoaEnum.getSiglaAssuntoGED(codigoTipoPessoa), Negocio.CTA_SIGLA_SISTEMA);
			GedApiDocumentoDelegate documentoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiDocumentoDelegate();
			return documentoDelegate.listaIdsDocumentosAprovadosPorSistemaERegControlado(filtro);
		} catch (NegocioException e) {
			throw new GEDIntegracaoNegocioException(e);
		}
	}

	/**
	 * Obter sigla chave negocio cpf cnpj.
	 * 
	 * @param codTipoPessoa
	 *            o valor de cod tipo pessoa
	 * @return String
	 */
	private String obterSiglaChaveNegocioCpfCnpj(Short codTipoPessoa) {
		return TipoPessoaEnum.isPessoaFisica(codTipoPessoa) ? Negocio.GED_SIGLA_CHAVE_NEGOCIO_CPF : Negocio.GED_SIGLA_CHAVE_NEGOCIO_CNPJ;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> enviarDocumentosGED(List<DocumentoGEDVO> documentos) throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada nesse ambiente.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoDocumentoVO> obterTiposDocumentoPorSiglaClasseDocumento(String siglaAssunto, String siglaClasseDocumento) throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada nesse ambiente.");
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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