package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.ProdutoEnum;
import br.com.sicoob.capes.comum.negocio.vo.AgenciaCafVO;
import br.com.sicoob.capes.comum.negocio.vo.BancoCafVO;
import br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO;
import br.com.sicoob.capes.comum.negocio.vo.ModalidadeProdutoVO;
import br.com.sicoob.capes.comum.negocio.vo.ProdutoInstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.ProdutoVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.ADMIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.ADMIntegracaoServicoRemote;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiAgenciaCafDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiBancoCafDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiEsferaAdministrativaDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiFabricaDelegates;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiModalidadeProdutoDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiProdutoDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiProdutoInstituicaoDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiValorParametroDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiAgenciaCaf;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiBancoCaf;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiEsferaAdministrativa;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiModalidadeProduto;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiProduto;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiProdutoInstituicao;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiValorParametroLegado;

/**
 * EJB contendo servicos relacionados a ADMIntegracao.
 */
@Stateless
@Local(ADMIntegracaoServicoLocal.class)
@Remote(ADMIntegracaoServicoRemote.class)
public class ADMIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements ADMIntegracaoServicoLocal, ADMIntegracaoServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BancoCafVO obterBancoCaf(Short numBanco) throws BancoobException {
		AdmApiBancoCafDelegate bancoCafDelegate = AdmApiFabricaDelegates.getInstance().createAdmApiBancoCafDelegate();
		IAdmApiBancoCaf retorno = bancoCafDelegate.obter(numBanco);

		BancoCafVO banco = new BancoCafVO();
		banco.setNumBanco(retorno.getNumBanco());
		banco.setDescBanco(retorno.getDescBanco());

		return banco;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public AgenciaCafVO obterAgenciaCaf(Short numBanco, Short numAgencia) throws BancoobException {
		AgenciaCafVO retorno = null;

		AdmApiAgenciaCafDelegate agenciaCafDelegate = AdmApiFabricaDelegates.getInstance().createAdmApiAgenciaCafDelegate();
		IAdmApiAgenciaCaf retornoAgenciaCaf = agenciaCafDelegate.obter(numBanco, numAgencia);

		if (retornoAgenciaCaf != null) {
			retorno = new AgenciaCafVO();
			retorno.setDescricaoAgencia(retornoAgenciaCaf.getDescAgencia());
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean verificarAgenciaCaf(Integer numAgencia, Integer numBanco) throws BancoobException {
		AdmApiAgenciaCafDelegate agenciaCafDelegate = AdmApiFabricaDelegates.getInstance().createAdmApiAgenciaCafDelegate();
		return agenciaCafDelegate.existeAgencia(numAgencia, numBanco);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EsferaAdministrativaVO> listarEsferasAdministrativas() throws BancoobException {
		List<EsferaAdministrativaVO> retorno = new ArrayList<EsferaAdministrativaVO>();

		AdmApiEsferaAdministrativaDelegate delegate = AdmApiFabricaDelegates.getInstance().createAdmApiEsferaAdministrativaDelegate();
		List<IAdmApiEsferaAdministrativa> lista = delegate.listar();

		if (lista != null) {
			EsferaAdministrativaVO esferaVO = null;
			for (IAdmApiEsferaAdministrativa esfera : lista) {
				esferaVO = new EsferaAdministrativaVO();
				esferaVO.setCodigo(esfera.getCodEsferaAdministrativa());
				esferaVO.setDescricao(esfera.getDescEsferaAdministrativa());
				retorno.add(esferaVO);
			}
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EsferaAdministrativaVO obterEsferaAdministrativa(Short idEsferaAdministrativa) throws BancoobException {
		EsferaAdministrativaVO retorno = null;

		AdmApiEsferaAdministrativaDelegate delegate = AdmApiFabricaDelegates.getInstance().createAdmApiEsferaAdministrativaDelegate();
		IAdmApiEsferaAdministrativa esferaRetorno = delegate.obter(idEsferaAdministrativa);

		if (esferaRetorno != null) {
			retorno = new EsferaAdministrativaVO();
			retorno.setCodigo(esferaRetorno.getCodEsferaAdministrativa());
			retorno.setDescricao(esferaRetorno.getDescEsferaAdministrativa());
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ProdutoInstituicaoVO> obterProdutosInstituicao() throws BancoobException {
		List<ProdutoInstituicaoVO> listaRetorno = new ArrayList<ProdutoInstituicaoVO>();
		AdmApiProdutoInstituicaoDelegate produtoInstituicaoDelegate = AdmApiFabricaDelegates.getInstance().createAdmApiProdutoInstituicaoDelegate();
		List<IAdmApiProdutoInstituicao> listaProdutos = produtoInstituicaoDelegate.pesquisarProdutoInstituicao(Constantes.Comum.ID_INSTITUICAO_BANCOOB, ProdutoEnum.CCS.getId());

		for (IAdmApiProdutoInstituicao produtoInst : listaProdutos) {
			ProdutoInstituicaoVO vo = new ProdutoInstituicaoVO();
			vo.setDataAtualMovimento(produtoInst.getDataAtualMovimento());
			vo.setDataProximoMovimento(produtoInst.getDataProximoMovimento());
			listaRetorno.add(vo);
		}

		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ModalidadeProdutoVO obterModalidadeProduto(Integer idProduto, Integer idModalidade, Integer idInstituicao, Integer idUnidadeInst) throws BancoobException {
		ModalidadeProdutoVO retorno = null;
		AdmApiModalidadeProdutoDelegate modalidadeProdutoDelegate = AdmApiFabricaDelegates.getInstance().createAdmApiModalidadeProdutoDelegate();
		IAdmApiModalidadeProduto modalidade = modalidadeProdutoDelegate.obter(idProduto.shortValue(), idModalidade.shortValue(), idInstituicao, idUnidadeInst);

		if (modalidade != null) {
			retorno = new ModalidadeProdutoVO();
			retorno.setDescricaoModalidadeProduto(modalidade.getDescModalidadeProduto());
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ProdutoVO obterProduto(Integer idProduto) throws BancoobException {
		ProdutoVO retorno = null;
		AdmApiProdutoDelegate produtoDelegate = AdmApiFabricaDelegates.getInstance().createAdmApiProdutoDelegate();
		Map<Short, IAdmApiProduto> retornoAdmProdutos = produtoDelegate.obterProdutos();

		IAdmApiProduto admProduto = retornoAdmProdutos.get(idProduto.shortValue());

		if (admProduto != null) {
			retorno = new ProdutoVO();
			retorno.setDescricaoProduto(admProduto.getDescricaoProduto());
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String obterValorParametro(Integer idParametro, Integer idInstituicao) throws BancoobException {
		AdmApiValorParametroDelegate valorParametroDelegate = AdmApiFabricaDelegates.getInstance().createAdmApiValorParametroDelegate();
		IAdmApiValorParametroLegado objetoValorParametro = valorParametroDelegate.obterValorParametroLegado(idInstituicao, idParametro);
		if (objetoValorParametro != null) {
			return objetoValorParametro.getValorPar();
		}
		return null;
	}
}