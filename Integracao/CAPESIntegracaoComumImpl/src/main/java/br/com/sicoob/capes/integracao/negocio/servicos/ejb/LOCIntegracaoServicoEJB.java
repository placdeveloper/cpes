package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoTipoLogradouroVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoUFVO;
import br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO;
import br.com.sicoob.capes.integracao.negocio.excecao.LOCIntegracaoNegocioException;
import br.com.sicoob.capes.integracao.negocio.excecao.LOCIntegracaoRuntimeException;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.LOCIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.LOCIntegracaoServicoRemote;
import br.com.sicoob.capes.integracao.persistencia.dao.LocalidadeDAO;
import br.com.sicoob.sisbr.localidade.api.enums.EnumTipoLocalidade;
import br.com.sicoob.sisbr.localidade.api.filtro.LocApiFabricaFiltro;
import br.com.sicoob.sisbr.localidade.api.filtro.LocApiFiltroLocalidade;
import br.com.sicoob.sisbr.localidade.api.filtro.LocApiFiltroLogradouro;
import br.com.sicoob.sisbr.localidade.api.filtro.LocApiFiltroTipoLogradouro;
import br.com.sicoob.sisbr.localidade.api.filtro.retorno.ILocApiLocalidadeRet;
import br.com.sicoob.sisbr.localidade.api.filtro.retorno.ILocApiLogradouroRet;
import br.com.sicoob.sisbr.localidade.api.filtro.retorno.ILocApiTipoLogradouroRet;
import br.com.sicoob.sisbr.localidade.api.filtro.retorno.ILocApiUFRet;
import br.com.sicoob.sisbr.localidade.api.negocio.delegates.LocApiFabricaDelegates;
import br.com.sicoob.sisbr.localidade.api.negocio.delegates.LocApiLogradouroDelegate;
import br.com.sicoob.sisbr.localidade.api.negocio.delegates.LocApiUFDelegate;

/**
 * Servico de integracao com o projeto Localidade
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Remote(LOCIntegracaoServicoRemote.class)
@Local(LOCIntegracaoServicoLocal.class)
public class LOCIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements LOCIntegracaoServicoRemote, LOCIntegracaoServicoLocal {

	/** A constante RETORNO_NAO_ENCONTRADO. */
	private static final String RETORNO_NAO_ENCONTRADO = "NÃO ENCONTRADO";

	@Inject
	@Default
	private LocalidadeDAO localidadeDao;
	
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public LOCIntegracaoLocalidadeVO obterLocalidade(Integer chave) throws BancoobException {
		ILocApiLocalidadeRet localidade = null;

		if (chave != null && chave > 0) {
			LocApiFabricaDelegates fabrica = LocApiFabricaDelegates.getInstancia();
			localidade = fabrica.criarLocApiLocalidadeDelegate().obterLocalidade(chave);
		}

		LOCIntegracaoLocalidadeVO retorno = new LOCIntegracaoLocalidadeVO();
		if (localidade != null) {
			retorno.setIdLocalidade(localidade.getIdLocalidade());
			retorno.setNome(localidade.getNomeLimpoLocalidade());
			retorno.setNomeLimpo(localidade.getNomeLimpoLocalidade());
			retorno.setNumeroCep(localidade.getNumCepLocalidade());
			retorno.setCodigoIBGE(localidade.getCodIBGE());
			retorno.setUf(copiarUF(localidade));
		} else {
			retorno.setNome(RETORNO_NAO_ENCONTRADO);
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public LOCIntegracaoLocalidadeVO obterLocalidadeRFB(Integer codigoRFB) throws BancoobException {
		List<ILocApiLocalidadeRet> localidades = null;
		LOCIntegracaoLocalidadeVO retorno = new LOCIntegracaoLocalidadeVO();

		LocApiFiltroLocalidade filtro = new LocApiFabricaFiltro().createLocApiFiltroLocalidade();
		filtro.setTipoLocalidade(EnumTipoLocalidade.MUNICIPIO_IBGE);
		filtro.setCodRF(codigoRFB.toString());
		try {
			LocApiFabricaDelegates fabrica = LocApiFabricaDelegates.getInstancia();
			localidades = fabrica.criarLocApiLocalidadeDelegate().pesquisarLocalidade(filtro);
		} catch (NegocioException e) {
			throw new LOCIntegracaoNegocioException(e);
		} catch (BancoobException e) {
			throw new LOCIntegracaoRuntimeException(e);
		}

		if ((localidades != null) && !localidades.isEmpty()) {
			ILocApiLocalidadeRet localidade = localidades.get(0);
			retorno.setIdLocalidade(localidade.getIdLocalidade());
			retorno.setNome(localidade.getNomeLimpoLocalidade());
			retorno.setUf(copiarUF(localidade));
		} else {
			retorno.setIdLocalidade(0);
			retorno.setNome(RETORNO_NAO_ENCONTRADO);
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public LOCIntegracaoTipoLogradouroVO obterTipoLogradouroRFB(String nome) throws BancoobException {
		LOCIntegracaoTipoLogradouroVO tipoLogradouro = null;
		LOCIntegracaoTipoLogradouroVO outros = new LOCIntegracaoTipoLogradouroVO();

		LocApiFabricaDelegates fabrica = LocApiFabricaDelegates.getInstancia();
		List<ILocApiTipoLogradouroRet> tipos = fabrica.criarLocApiTipoLogradouroDelegate().pesquisarTiposLogradouro(
				new LocApiFabricaFiltro().createLocApiFiltroTipoLogradouro());

		Iterator<ILocApiTipoLogradouroRet> it = tipos.iterator();
		for (; it.hasNext() && (tipoLogradouro == null);) {
			ILocApiTipoLogradouroRet tipo = it.next();
			if (nome != null && tipo.getDescricao().toUpperCase().startsWith(nome.toUpperCase())) {
				tipoLogradouro = new LOCIntegracaoTipoLogradouroVO();
				tipoLogradouro.setIdTipoLogradouro(tipo.getId());
				tipoLogradouro.setNome(tipo.getDescricao());
			} else if (tipo.getId().equals(Integer.valueOf(99))) {
				outros.setIdTipoLogradouro(tipo.getId());
				outros.setNome(tipo.getDescricao());
			}
		}
		return tipoLogradouro != null ? tipoLogradouro : outros;
	}

	/**
	 * Copiar uf.
	 * 
	 * @param localidade
	 *            o valor de localidade
	 * @return LOCIntegracaoUFVO
	 */
	private LOCIntegracaoUFVO copiarUF(ILocApiLocalidadeRet localidade) {

		/*
		 * Devido a utilizacao de classloaders isolados, nao se deve utilizar
		 * classes de outros projetos como atributo/retorno de metodos nem como
		 * atributos de classe em objetos gerenciados pelo container.
		 */
		LOCIntegracaoUFVO unidade = new LOCIntegracaoUFVO();
		unidade.setIdUF(localidade.getIdUF());
		unidade.setNomeUF(localidade.getNomeUF());
		unidade.setSiglaUF(localidade.getSiglaUF());
		return unidade;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public LOCIntegracaoTipoLogradouroVO obterTipoLogradouro(Short id) throws BancoobException {
		LOCIntegracaoTipoLogradouroVO retorno = null;
		try {
			LocApiFiltroTipoLogradouro filtro = new LocApiFabricaFiltro().createLocApiFiltroTipoLogradouro();
			filtro.setId(id.intValue());

			LocApiFabricaDelegates fabrica = LocApiFabricaDelegates.getInstancia();
			List<ILocApiTipoLogradouroRet> listaRetorno = fabrica.criarLocApiTipoLogradouroDelegate().pesquisarTiposLogradouro(filtro);

			if (!listaRetorno.isEmpty()) {
				ILocApiTipoLogradouroRet locTipoLogradouro = listaRetorno.get(0);
				retorno = new LOCIntegracaoTipoLogradouroVO(locTipoLogradouro.getId(), locTipoLogradouro.getDescricao());
			}

		} catch (NegocioException e) {
			throw new LOCIntegracaoNegocioException(e);
		} catch (BancoobException e) {
			throw new LOCIntegracaoRuntimeException(e);
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<LOCIntegracaoTipoLogradouroVO> listarTiposLogradouro() throws BancoobException {
		LocApiFabricaDelegates fabrica = LocApiFabricaDelegates.getInstancia();
		LocApiFiltroTipoLogradouro filtro = new LocApiFabricaFiltro().createLocApiFiltroTipoLogradouro();
		List<ILocApiTipoLogradouroRet> tipos = fabrica.criarLocApiTipoLogradouroDelegate().pesquisarTiposLogradouro(filtro);

		List<LOCIntegracaoTipoLogradouroVO> retorno = new ArrayList<LOCIntegracaoTipoLogradouroVO>();
		for (ILocApiTipoLogradouroRet tipo : tipos) {
			retorno.add(new LOCIntegracaoTipoLogradouroVO(tipo.getId(), tipo.getDescricao()));
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<LOCIntegracaoUFVO> listarUFs() throws BancoobException {
		LocApiFabricaDelegates fabrica = LocApiFabricaDelegates.getInstancia();
		LocApiUFDelegate ufDelegate = fabrica.criarLocApiUFDelegate();

		List<ILocApiUFRet> ufs = ufDelegate.pesquisarUFs(new LocApiFabricaFiltro().createLocApiFiltroUF());
		List<LOCIntegracaoUFVO> listaRetorno = new ArrayList<LOCIntegracaoUFVO>();
		for (ILocApiUFRet uf : ufs) {
			LOCIntegracaoUFVO ufvo = new LOCIntegracaoUFVO();
			ufvo.setIdUF(uf.getIdUF());
			ufvo.setSiglaUF(uf.getSiglaUF());
			ufvo.setNomeUF(uf.getNomeUF());
			listaRetorno.add(ufvo);
		}
		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<LocalidadeVO> pesquisarLocalidadeComplemento(LocalidadeVO localidadeVO) throws BancoobException {
		List<LocalidadeVO> retorno = new ArrayList<LocalidadeVO>();

		LocApiFiltroLocalidade filtro = new LocApiFabricaFiltro().createLocApiFiltroLocalidade();
		filtro.setNumCep(localidadeVO.getNumCep());
		filtro.setNomeBairro(localidadeVO.getNomeBairro());
		filtro.setNomeLocalidade(localidadeVO.getNomeLocalidade());
		filtro.setNomeLogradouro(localidadeVO.getNomeLogradouro());
		filtro.setSiglaUF(localidadeVO.getSiglaUF());

		LocApiFabricaDelegates fabrica = LocApiFabricaDelegates.getInstancia();
		List<ILocApiLocalidadeRet> lista = fabrica.criarLocApiLocalidadeDelegate().pesquisarLocalidadeComplemento(filtro);
		for (ILocApiLocalidadeRet loc : lista) {
			LocalidadeVO localidadeRetorno = new LocalidadeVO();
			localidadeRetorno.setIdLogradouro(loc.getIdLogradouro());
			localidadeRetorno.setIdTipoLogradouro(loc.getIdTipoLogradouro());
			localidadeRetorno.setDescComplementoLogradouro(loc.getDescComplementoLogradouro());
			localidadeRetorno.setIdLocalidade(loc.getIdLocalidade());
			localidadeRetorno.setIdLocalidadePai(loc.getIdLocalidadePai());
			localidadeRetorno.setNomeBairro(loc.getNomeBairro());
			localidadeRetorno.setNomeLocalidade(loc.getNomeLocalidade());
			localidadeRetorno.setNomeLogradouro(loc.getNomeLogradouro());
			localidadeRetorno.setSiglaUF(loc.getSiglaUF());

			if (StringUtils.isNotEmpty(loc.getNumCepLogradouro())) {
				localidadeRetorno.setNumCep(loc.getNumCepLogradouro());
			} else {
				localidadeRetorno.setNumCep(loc.getNumCepLocalidade());
			}

			retorno.add(localidadeRetorno);
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public LocalidadeVO pesquisarLocalidadePorIdLogradouro(Integer idLogradouro) throws BancoobException {
		LocApiFiltroLogradouro filtro = new LocApiFabricaFiltro().createLocApiFiltroLogradouro();
		filtro.setIdLogradouro(idLogradouro);

		LocApiLogradouroDelegate delegate = LocApiFabricaDelegates.getInstancia().criarLocApiLogradouroDelegate();
		List<ILocApiLogradouroRet> lista = delegate.pesquisarLogradouro(filtro);

		LocalidadeVO localidadeRetorno = null;
		if (lista != null && lista.size() == 1) {
			ILocApiLogradouroRet loc = lista.get(0);

			localidadeRetorno = new LocalidadeVO();
			localidadeRetorno.setDescComplementoLogradouro(loc.getDescComplementoLogradouro());
			localidadeRetorno.setIdLocalidade(loc.getIdLocalidade());
			localidadeRetorno.setIdLocalidadePai(loc.getIdLocalidadePai());
			localidadeRetorno.setIdLogradouro(loc.getIdLogradouro());
			localidadeRetorno.setNomeBairro(loc.getNomeBairro());
			localidadeRetorno.setNomeLocalidade(loc.getNomeLocalidade());
			localidadeRetorno.setNomeLogradouro(loc.getNomeLogradouro());
			localidadeRetorno.setSiglaUF(loc.getSiglaUF());
			localidadeRetorno.setIdTipoLogradouro(loc.getIdTipoLogradouro());

			if (StringUtils.isNotEmpty(loc.getNumCEP())) {
				localidadeRetorno.setNumCep(loc.getNumCEP());
			} else {
				localidadeRetorno.setNumCep(loc.getNumCepLocalidade());
			}
		}

		return localidadeRetorno;
	}

	/**
	 * Recupera um mapa com os tipos de logradouro, onde a chave eh o
	 * identificador do tipo e o valor eh a descricao.
	 * 
	 * @return um mapa com os tipos de logradouro, onde a chave eh o
	 *         identificador do tipo e o valor eh a descricao.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Map<Integer, String> obterMapaTiposLogradouro() throws BancoobException {

		LocApiFabricaDelegates fabrica = LocApiFabricaDelegates.getInstancia();
		LocApiFiltroTipoLogradouro filtro = new LocApiFabricaFiltro().createLocApiFiltroTipoLogradouro();
		List<ILocApiTipoLogradouroRet> lista = fabrica.criarLocApiTipoLogradouroDelegate().pesquisarTiposLogradouro(filtro);

		Map<Integer, String> mapa = new HashMap<Integer, String>();
		for (ILocApiTipoLogradouroRet tipoLogradouro : lista) {
			mapa.put(tipoLogradouro.getId(), tipoLogradouro.getDescricao().toUpperCase());
		}

		return mapa;
	}

	/**
	 * Recupera o nome da localidade a partir do identificador informado.
	 * 
	 * @param idLocalidade
	 *            O identificador da localidade.
	 * @return o nome da localidade a partir do identificador informado.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao na consulta.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String obterNomeLocalidade(Integer idLocalidade) throws BancoobException {
		String nomeLocalidade = null;
		LOCIntegracaoLocalidadeVO vo = obterLocalidade(idLocalidade);
		if (vo != null) {
			nomeLocalidade = vo.getNome();
		}
		return nomeLocalidade;
	}


	/**
	 * {@inheritDoc}
	 */
	public List<LocalidadeVO> pesquisarLocalidade(String estadoLocalidade, String nomeLocalidade) throws BancoobException{
		List<LocalidadeVO> retorno = new ArrayList<LocalidadeVO>();
		LocApiFiltroLocalidade requisicao = LocApiFabricaFiltro.getInstance().createLocApiFiltroLocalidade();
         requisicao.setNomeLocalidade(nomeLocalidade);
         requisicao.setSiglaUF(estadoLocalidade);
        
         LocApiFabricaDelegates fabrica = LocApiFabricaDelegates.getInstancia();
 		 List<ILocApiLocalidadeRet> lista = fabrica.criarLocApiLocalidadeDelegate().pesquisarLocalidade(requisicao);
         
 		for (ILocApiLocalidadeRet loc : lista) {
			LocalidadeVO localidadeRetorno = new LocalidadeVO();
			localidadeRetorno.setIdLogradouro(loc.getIdLogradouro());
			localidadeRetorno.setIdTipoLogradouro(loc.getIdTipoLogradouro());
			localidadeRetorno.setDescComplementoLogradouro(loc.getDescComplementoLogradouro());
			localidadeRetorno.setIdLocalidade(loc.getIdLocalidade());
			localidadeRetorno.setIdLocalidadePai(loc.getIdLocalidadePai());
			localidadeRetorno.setNomeBairro(loc.getNomeBairro());
			localidadeRetorno.setNomeLocalidade(loc.getNomeLocalidade());
			localidadeRetorno.setNomeLogradouro(loc.getNomeLogradouro());
			localidadeRetorno.setSiglaUF(loc.getSiglaUF());

			if (StringUtils.isNotEmpty(loc.getNumCepLogradouro())) {
				localidadeRetorno.setNumCep(loc.getNumCepLogradouro());
			} else {
				localidadeRetorno.setNumCep(loc.getNumCepLocalidade());
			}

			retorno.add(localidadeRetorno);
		}
		return retorno;
	}
	
	/**
	 * Lista todas as localidades cadastradas.
	 * 
	 * @return
	 * @throws BancoobException
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<LocalidadeVO> listarLocalidade(Integer cooperativa) throws BancoobException {
		return localidadeDao.listarLocalidade(cooperativa);
	}

}