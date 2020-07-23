package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.infraestrutura.FabricaServicos;
import br.com.bancoob.infraestrutura.cache.ServicoCache;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.SistemaCooperativoVO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.integracao.negocio.excecao.SCIIntegracaoRuntimeException;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.SCIIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.SCIIntegracaoServicoRemote;
import br.com.sicoob.sisbr.sci.integracao.negocio.delegates.SciIntFabricaDelegate;
import br.com.sicoob.sisbr.sci.integracao.negocio.delegates.SciIntInstituicaoCooperativaDelegate;
import br.com.sicoob.sisbr.sci.integracao.negocio.delegates.SciIntInstituicaoDelegate;
import br.com.sicoob.sisbr.sci.integracao.negocio.delegates.SciIntUnidadeInstituicaoDelegate;
import br.com.sicoob.sisbr.sci.integracao.negocio.descriptors.EnumInstituicao;
import br.com.sicoob.sisbr.sci.integracao.negocio.descriptors.EnumSituacaoUnidade;
import br.com.sicoob.sisbr.sci.integracao.negocio.descriptors.EnumTipoInstituicao;
import br.com.sicoob.sisbr.sci.integracao.negocio.descriptors.EnumUnidadeInstituicao;
import br.com.sicoob.sisbr.sci.integracao.negocio.dto.filtro.SciIntFiltroInstituicaoCAPES;
import br.com.sicoob.sisbr.sci.integracao.negocio.dto.filtro.SciIntFiltroInstituicaoGeral;
import br.com.sicoob.sisbr.sci.integracao.negocio.dto.filtro.SciIntFiltroUnidadeInstituicao;
import br.com.sicoob.sisbr.sci.integracao.negocio.entidades.ISciIntInstituicao;
import br.com.sicoob.sisbr.sci.integracao.negocio.entidades.ISciIntInstituicaoCAPES;
import br.com.sicoob.sisbr.sci.integracao.negocio.entidades.ISciIntInstituicaoCooperativa;
import br.com.sicoob.sisbr.sci.integracao.negocio.entidades.ISciIntInstituicaoGeral;
import br.com.sicoob.sisbr.sci.integracao.negocio.entidades.ISciIntUnidadeInstituicao;
import br.com.sicoob.sisbr.sci.integracao.negocio.excecao.SciIntegracaoException;

/**
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(SCIIntegracaoServicoLocal.class)
@Remote(SCIIntegracaoServicoRemote.class)
public class SCIIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements SCIIntegracaoServicoLocal, SCIIntegracaoServicoRemote {

	/** A constante KEY_CACHE_ID_INSTITUICAO. */
	private static final String KEY_CACHE_ID_INSTITUICAO = "CAPES_CACHE_INSTITUICAO_ID_INSTITUICAO_";

	/** A constante KEY_CACHE_NUM_COOPERATIVA. */
	private static final String KEY_CACHE_NUM_COOPERATIVA = "CAPES_CACHE_INSTITUICAO_NUM_COOPERATIVA_";

	/** A constante CACHE_TIMEOUT. */
	private static final int CACHE_TIMEOUT = 12 * 60 * 60 * 1000; // 12 horas.

	/** A constante COOPERATIVA_ATIVA. */
	private static final Integer COOPERATIVA_ATIVA = 1;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<UnidadeInstituicaoVO> listarUnidadesInstituicao(Integer idInstituicao, boolean somenteAtivas) {
		List<UnidadeInstituicaoVO> retorno = new ArrayList<UnidadeInstituicaoVO>();
		try {
			SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
			SciIntUnidadeInstituicaoDelegate delegate = fabrica.criarUnidadeInstituicaoDelegate();
			SciIntFiltroUnidadeInstituicao filtro = new SciIntFiltroUnidadeInstituicao();
			filtro.setIdInstituicao(idInstituicao);
			if (somenteAtivas) {
				filtro.setSituacaoUnidade(EnumSituacaoUnidade.ATIVA);
			}
			List<ISciIntUnidadeInstituicao> unidades = delegate.pesquisar(filtro);

			if (unidades != null && !unidades.isEmpty()) {
				for (ISciIntUnidadeInstituicao unidade : unidades) {
					retorno.add(criarUnidadeInstituicaoVO(unidade));
				}
			}

		} catch (SciIntegracaoException e) {
			throw new BancoobRuntimeException(e);
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<UnidadeInstituicaoVO> listarUnidadesInstituicaoSemPADigital(Integer idInstituicao, boolean somenteAtivas) {
		List<UnidadeInstituicaoVO> retorno = new ArrayList<UnidadeInstituicaoVO>();
		try {
			SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
			SciIntUnidadeInstituicaoDelegate delegate = fabrica.criarUnidadeInstituicaoDelegate();
			SciIntFiltroUnidadeInstituicao filtro = new SciIntFiltroUnidadeInstituicao();
			filtro.setIdInstituicao(idInstituicao);
			if (somenteAtivas) {
				filtro.setSituacaoUnidade(EnumSituacaoUnidade.ATIVA);
			}
			List<ISciIntUnidadeInstituicao> unidades = delegate.pesquisarUnidadeInstituicaoSemPADigital(filtro);

			if (unidades != null && !unidades.isEmpty()) {
				for (ISciIntUnidadeInstituicao unidade : unidades) {
					retorno.add(criarUnidadeInstituicaoVO(unidade));
				}
			}

			
		} catch (SciIntegracaoException e) {
			throw new SCIIntegracaoRuntimeException(e);
		}

		return retorno;
	}

	/**
	 * Preencher dados instituicao vo.
	 * 
	 * @param dados
	 *            o valor de dados
	 * @return InstituicaoVO
	 */
	private InstituicaoVO preencherDadosInstituicaoVO(ISciIntInstituicaoGeral dados) {
		InstituicaoVO retorno = null;

		/*
		 * Devido a utilizacao de classloaders isolados, nao se deve utilizar
		 * classes de outros projetos como atributo/retorno de metodos nem como
		 * atributos de classe em objetos gerenciados pelo container.
		 */
		if (dados != null) {
			retorno = new InstituicaoVO();
			retorno.setIdInstituicao(dados.getIdInstituicao());
			retorno.setNome(dados.getNomeInstituicao());
			retorno.setNumero(dados.getNumero());
			retorno.setSiglaInstituicao(dados.getSiglaInstituicao());

			retorno.setCodigoTipoInstituicao(dados.getCodTipoInstituicao());
			retorno.setCodigoSituacaoInst(dados.getCodSituacaoInstituicao());

			retorno.setCnpj(dados.getNumCnpj());
		}
		return retorno;
	}

	/**
	 * Criar unidade instituicao vo.
	 * 
	 * @param dados
	 *            o valor de dados
	 * @return UnidadeInstituicaoVO
	 */
	private UnidadeInstituicaoVO criarUnidadeInstituicaoVO(ISciIntUnidadeInstituicao dados) {
		UnidadeInstituicaoVO vo = null;

		if (dados != null) {

			/*
			 * Devido a utilizacao de classloaders isolados, nao se deve
			 * utilizar classes de outros projetos como atributo/retorno de
			 * metodos nem como atributos de classe em objetos gerenciados pelo
			 * container.
			 */
			vo = new UnidadeInstituicaoVO();
			vo.setDescricao(dados.getNomeUnidade());
			vo.setNumCNPJ(dados.getNumCNPJ());
			vo.setSiglaUnidade(dados.getSiglaUnidade());
			vo.setNomeUnidade(dados.getNomeUnidade());
			vo.setIdUnidadeInst(dados.getIdUnidadeInstituicao());
			vo.setId(dados.getIdUnidadeInstituicao());
			vo.setIdInstituicao(dados.getIdInstituicao());
		}

		return vo;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SistemaCooperativoVO obterSistemaCooperativo(Integer idInstituicao) throws BancoobException {
		SistemaCooperativoVO sistema = null;
		SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
		SciIntInstituicaoCooperativaDelegate delegate = fabrica.criarInstituicaoCooperativaDelegate();
		ISciIntInstituicaoCooperativa instCooperativa = delegate.obter(idInstituicao);
		if (instCooperativa != null) {
			sistema = new SistemaCooperativoVO();
			sistema.setIdSistemaCooperativo(instCooperativa.getCodSistemaCooperativo());
			sistema.setNumCooperativa(instCooperativa.getNumCooperativa());
		}
		return sistema;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<InstituicaoVO> listarInstituicoesPorTipo(Integer codTipoInstituicao) throws BancoobException {
		List<InstituicaoVO> listaRetorno = new ArrayList<InstituicaoVO>();
		SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
		SciIntInstituicaoDelegate delegate = fabrica.criarInstituicaoDelegate();
		SciIntFiltroInstituicaoGeral filtro = new SciIntFiltroInstituicaoGeral();
		filtro.setCodTipoInstituicao(codTipoInstituicao);
		filtro.setIdUnidadeInstituicao(EnumUnidadeInstituicao.MATRIZ.getCodigoUnidadeInstituicao());
		List<ISciIntInstituicaoGeral> lista = delegate.pesquisarInformacoesGeraisInstituicao(filtro);

		if (lista != null) {
			for (ISciIntInstituicaoGeral instituicao : lista) {
				InstituicaoVO vo = preencherDadosInstituicaoVO(instituicao);
				listaRetorno.add(vo);
			}
		}
		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean isCooperativa(String cnpj) throws BancoobException {
		SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
		SciIntUnidadeInstituicaoDelegate unidadeDelegate = fabrica.criarUnidadeInstituicaoDelegate();
		SciIntFiltroUnidadeInstituicao filtro = new SciIntFiltroUnidadeInstituicao();
		filtro.setNumCnpj(cnpj);
		List<ISciIntUnidadeInstituicao> listaRetorno = unidadeDelegate.pesquisar(filtro);

		if (listaRetorno != null && listaRetorno.size() > 0) {
			ISciIntUnidadeInstituicao unidadeInstituicao = listaRetorno.get(0);
			if (unidadeInstituicao.getCodTipoInstituicao() == EnumTipoInstituicao.COOPERATIVA.getCodTipoInstituicao()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean isCooperativaAtiva(String cnpj) throws BancoobException {
		SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
		SciIntUnidadeInstituicaoDelegate unidadeDelegate = fabrica.criarUnidadeInstituicaoDelegate();
		SciIntFiltroUnidadeInstituicao filtro = new SciIntFiltroUnidadeInstituicao();
		filtro.setNumCnpj(cnpj);

		List<ISciIntUnidadeInstituicao> listaRetorno = unidadeDelegate.pesquisar(filtro);

		if (listaRetorno != null && listaRetorno.size() > 0) {
			ISciIntUnidadeInstituicao unidadeInstituicao = listaRetorno.get(0);
			InstituicaoVO instTemp = obterInstituicaoSituacao(unidadeInstituicao.getIdInstituicao());
			if (instTemp.getCodigoSituacaoInst().equals(COOPERATIVA_ATIVA)
					&& unidadeInstituicao.getCodTipoInstituicao() == EnumTipoInstituicao.COOPERATIVA.getCodTipoInstituicao()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = null;

		getLogger().debug("Obtendo o número da cooperativa para o idInsituicao:", String.valueOf(idInstituicao));

		if (idInstituicao != null) {
			if (Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(idInstituicao)) {
				numeroCooperativa = idInstituicao;
			} else {
				InstituicaoVO inst = obterInstituicaoSCI(idInstituicao);

				if (inst != null) {
					numeroCooperativa = inst.getNumero();
				}
			}
		}

		getLogger().debug("Retorno obtido do método obterNumeroCooperativa: ", String.valueOf(numeroCooperativa));

		return numeroCooperativa;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Integer obterIdInstituicao(Integer numeroCooperativa) throws BancoobException {
		Integer idInstituicao = null;

		getLogger().debug("Obtendo o identificador da cooperativa para o numero cooperativa:", String.valueOf(numeroCooperativa));

		if (numeroCooperativa != null) {
			if (Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(numeroCooperativa)) {
				idInstituicao = numeroCooperativa;
			} else {
				InstituicaoVO inst = obterInstituicaoPorNumeroCooperativa(numeroCooperativa);

				if (inst != null) {
					idInstituicao = inst.getIdInstituicao();
				}
			}
		}

		getLogger().debug("Retorno obtido do método obterIdInstituicao: ", String.valueOf(idInstituicao));

		return idInstituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public InstituicaoVO obterInstituicaoPorId(Integer idInstituicao) throws BancoobException {
		return obterCachePorIdInstituicao(idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public InstituicaoVO obterInstituicaoPorNumeroCooperativa(Integer numeroCooperativa) throws BancoobException {
		return obterCachePorNumeroCooperativa(numeroCooperativa);
	}

	/**
	 * Recupera o número da cooperativa no SCI a partir do idInstituicao
	 * informado.
	 * 
	 * @param idInstituicao
	 *            O identificador da instituição.
	 * @return A instituição a partir do idInstituicao informado.
	 */
	private InstituicaoVO obterInstituicaoSCI(Integer idInstituicao) throws BancoobException {
		SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
		SciIntInstituicaoDelegate instituicaoDelegate = fabrica.criarInstituicaoDelegate();

		SciIntFiltroInstituicaoCAPES filtro = new SciIntFiltroInstituicaoCAPES();
		filtro.setIdInstituicao(idInstituicao);

		ISciIntInstituicaoCAPES instituicao = instituicaoDelegate.obterInstituicaoCAPES(filtro);
		return converterInstituicaoVO(instituicao);
	}

	/**
	 * Obter instituicao situacao.
	 * 
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return InstituicaoVO
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private InstituicaoVO obterInstituicaoSituacao(Integer idInstituicao) throws BancoobException {
		SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
		SciIntInstituicaoDelegate instituicaoDelegate = fabrica.criarInstituicaoDelegate();

		ISciIntInstituicao instituicao = instituicaoDelegate.obter(idInstituicao);
		return converterInstituicaoSitiacaoVO(instituicao);
	}

	/**
	 * Recupera o número da cooperativa no SCI a partir do numeroCooperativa
	 * informado.
	 * 
	 * @param numeroCooperativa
	 *            O número da instituição.
	 * @return A instituição a partir do número da cooperativa informado.
	 */
	private InstituicaoVO obterInstituicaoSCIPorNumCooperativa(Integer numeroCooperativa) throws BancoobException {
		SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
		SciIntInstituicaoDelegate instituicaoDelegate = fabrica.criarInstituicaoDelegate();

		SciIntFiltroInstituicaoCAPES filtro = new SciIntFiltroInstituicaoCAPES();
		if (Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(numeroCooperativa)) {
			filtro.setIdInstituicao(numeroCooperativa);
		} else {
			filtro.setNumCooperativa(numeroCooperativa);
		}
		ISciIntInstituicaoCAPES instituicao = instituicaoDelegate.obterInstituicaoCAPES(filtro);
		return converterInstituicaoVO(instituicao);
	}

	/**
	 * Gerar chave do cache por id instituicao.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @return string
	 */
	private String gerarChaveCacheIdInstituicao(Integer idInstituicao) {
		return KEY_CACHE_ID_INSTITUICAO + idInstituicao;
	}

	/**
	 * Gerar chave do cache por numero cooperativa.
	 * 
	 * @param numCooperativa
	 *            the num cooperativa
	 * @return string
	 */
	private String gerarChaveCacheNumCooperativa(Integer numCooperativa) {
		return KEY_CACHE_NUM_COOPERATIVA + numCooperativa;
	}

	/**
	 * Obter instituicao do cache por id instituicao.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @return instituicao
	 * @throws BancoobException
	 */
	private InstituicaoVO obterCachePorIdInstituicao(Integer idInstituicao) throws BancoobException {
		ServicoCache servicoCache = FabricaServicos.getInstance().criarServicoCache();
		String chave = gerarChaveCacheIdInstituicao(idInstituicao);
		InstituicaoVO instituicao = (InstituicaoVO) servicoCache.recuperar(chave);
		if (instituicao == null) {
			instituicao = obterInstituicaoSCI(idInstituicao);
			if (instituicao != null) {
				servicoCache.armazenar(chave, instituicao, CACHE_TIMEOUT);
				servicoCache.armazenar(gerarChaveCacheNumCooperativa(instituicao.getNumero()), instituicao, CACHE_TIMEOUT);
			}
		}
		return instituicao;
	}

	/**
	 * Obter instituica do cache por numero cooperativa.
	 * 
	 * @param numCooperativa
	 *            the num cooperativa
	 * @return instituicao
	 * @throws BancoobException
	 */
	private InstituicaoVO obterCachePorNumeroCooperativa(Integer numeroCooperativa) throws BancoobException {
		ServicoCache servicoCache = FabricaServicos.getInstance().criarServicoCache();
		String chave = gerarChaveCacheNumCooperativa(numeroCooperativa);
		InstituicaoVO instituicao = (InstituicaoVO) servicoCache.recuperar(chave);
		if (instituicao == null) {
			instituicao = obterInstituicaoSCIPorNumCooperativa(numeroCooperativa);
			if (instituicao != null) {
				servicoCache.armazenar(chave, instituicao, CACHE_TIMEOUT);
				servicoCache.armazenar(gerarChaveCacheIdInstituicao(instituicao.getIdInstituicao()), instituicao, CACHE_TIMEOUT);
			}
		}
		return instituicao;
	}

	/**
	 * Converter instituicao vo.
	 * 
	 * @param instituicao
	 *            o valor de instituicao
	 * @return InstituicaoVO
	 */
	private InstituicaoVO converterInstituicaoVO(ISciIntInstituicaoCAPES instituicao) {
		InstituicaoVO retorno = null;

		if (instituicao != null) {
			retorno = new InstituicaoVO();
			retorno.setIdInstituicao(instituicao.getIdInstituicao());
			retorno.setNumero(instituicao.getNumCooperativa());
			retorno.setNome(instituicao.getNomeInstituicao());
			retorno.setSiglaInstituicao(instituicao.getSiglaInstituicao());

			if (Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(instituicao.getIdInstituicao())) {
				retorno.setNumero(Constantes.Comum.ID_INSTITUICAO_BANCOOB);
			}
		}
		return retorno;
	}

	/**
	 * Converter instituicao sitiacao vo.
	 * 
	 * @param instituicao
	 *            o valor de instituicao
	 * @return InstituicaoVO
	 */
	private InstituicaoVO converterInstituicaoSitiacaoVO(ISciIntInstituicao instituicao) {
		InstituicaoVO retorno = null;

		if (instituicao != null) {
			retorno = new InstituicaoVO();
			retorno.setIdInstituicao(instituicao.getIdInstituicao());
			retorno.setNome(instituicao.getNomeInstituicao());
			retorno.setSiglaInstituicao(instituicao.getSiglaInstituicao());
			retorno.setCodigoSituacaoInst(instituicao.getCodSituacaoInst());

			if (Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(instituicao.getIdInstituicao())) {
				retorno.setNumero(Constantes.Comum.ID_INSTITUICAO_BANCOOB);
			}
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public InstituicaoVO obterInformacoesSicoob() throws BancoobException {
		InstituicaoVO retorno = null;
		SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
		SciIntInstituicaoDelegate delegate = fabrica.criarInstituicaoDelegate();
		SciIntFiltroInstituicaoGeral filtro = new SciIntFiltroInstituicaoGeral();

		List<Integer> idsInstituicao = new ArrayList<Integer>();
		idsInstituicao.add(EnumInstituicao.ID_SICOOB.getIdInstituicao());
		filtro.setListIdInstituicao(idsInstituicao);
		filtro.setIdUnidadeInstituicao(EnumUnidadeInstituicao.MATRIZ.getCodigoUnidadeInstituicao());
		List<ISciIntInstituicaoGeral> lista = delegate.pesquisarInformacoesGeraisInstituicao(filtro);

		if (lista != null && !lista.isEmpty()) {
			retorno = preencherDadosInstituicaoVO(lista.get(0));
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<InstituicaoVO> obterInsituicoesPorId(List<Integer> ids) throws BancoobException {
		List<InstituicaoVO> listaRetorno = null;
		if (ids != null && ids.size() > 0) {
			listaRetorno = new ArrayList<InstituicaoVO>();
			SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
			SciIntInstituicaoDelegate delegate = fabrica.criarInstituicaoDelegate();
			SciIntFiltroInstituicaoGeral filtro = new SciIntFiltroInstituicaoGeral();
			filtro.setListIdInstituicao(ids);
			filtro.setIdUnidadeInstituicao(EnumUnidadeInstituicao.MATRIZ.getCodigoUnidadeInstituicao());
			List<ISciIntInstituicaoGeral> lista = delegate.pesquisarInformacoesGeraisInstituicao(filtro);

			if (lista != null && lista.size() > 0) {
				for (ISciIntInstituicaoGeral instituicao : lista) {
					InstituicaoVO vo = preencherDadosInstituicaoVO(instituicao);
					listaRetorno.add(vo);
				}
			}
		}
		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean isConfederacao(Integer idInstituicao) throws BancoobException {
		SciIntInstituicaoDelegate delegate = SciIntFabricaDelegate.getInstance().criarInstituicaoDelegate();
		SciIntFiltroInstituicaoGeral filtro = new SciIntFiltroInstituicaoGeral();

		filtro.setIdInstituicao(idInstituicao);
		List<ISciIntInstituicaoGeral> lista = delegate.pesquisarInformacoesGeraisInstituicao(filtro);

		if (lista != null && lista.size() > 0) {
			if (lista.get(0).getConfederacao()) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 
	 * 
	 * @param idInstituicao
	 *            O identificador da instituição.
	 * @return A instituição a partir do idInstituicao informado.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public InstituicaoVO obterInformcoesGeraisInstituicaoSCI(Integer idInstituicao, Integer idUnidadeInst) throws BancoobException {
		SciIntFabricaDelegate fabrica = SciIntFabricaDelegate.getInstance();
		SciIntInstituicaoDelegate instituicaoDelegate = fabrica.criarInstituicaoDelegate();

		SciIntFiltroInstituicaoGeral filtro = new SciIntFiltroInstituicaoGeral();
		filtro.setIdInstituicao(idInstituicao);
		if(idUnidadeInst != null) {
			filtro.setIdUnidadeInstituicao(idUnidadeInst);
		}
		List<ISciIntInstituicaoGeral> instituicao = instituicaoDelegate.pesquisarInformacoesGeraisInstituicao(filtro );
		
		return converterInstituicaoGeraisVO(instituicao);
	}
	
	/**
	 * Converter informações gerais instituicao vo.
	 * 
	 * @param instituicao
	 *            o valor de instituicao
	 * @return InstituicaoVO
	 */
	private InstituicaoVO converterInstituicaoGeraisVO(List<ISciIntInstituicaoGeral>  listInstituicao) {
		InstituicaoVO retorno = null;

		for (ISciIntInstituicaoGeral instituicao : listInstituicao) {
			if (instituicao != null && instituicao.getIdUnidadeInstituicao().equals(Integer.valueOf(0))) {
				retorno = new InstituicaoVO();
				retorno.setIdInstituicao(instituicao.getIdInstituicao());
				retorno.setNumero(instituicao.getNumero());
				retorno.setNome(instituicao.getNomeInstituicao());
				retorno.setSiglaInstituicao(instituicao.getSiglaInstituicao());
				retorno.setCnpj(instituicao.getNumCnpj());
				
				if (Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(instituicao.getIdInstituicao())) {
					retorno.setNumero(Constantes.Comum.ID_INSTITUICAO_BANCOOB);
				}
			}
		}
		return retorno;
	}
	
}