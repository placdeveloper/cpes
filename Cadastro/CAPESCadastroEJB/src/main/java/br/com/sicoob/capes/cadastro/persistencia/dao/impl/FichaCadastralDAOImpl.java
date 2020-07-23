package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.dao.BancoobDao;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoConsultaEnum;
import br.com.sicoob.capes.cadastro.persistencia.dao.FichaCadastralDAO;
import br.com.sicoob.capes.cadastro.util.CAPESCadastroConstantes;
import br.com.sicoob.capes.cadastro.util.EntidadeCadastroBaseComparator;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.comum.persistencia.GerenciadorFiltro;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;

/**
 * A Classe FichaCadastralDAOImpl.
 */
public class FichaCadastralDAOImpl extends BancoobDao implements FichaCadastralDAO {
	
	/** A constante FILTRO_IMPRIMIR_FICHA_CADASTRAL_PREVIA. */
	private static final String FILTRO_IMPRIMIR_FICHA_CADASTRAL_PREVIA = "imprimirFichaCadastralPrevia";
	
	/** A constante FILTRO_IMPRIMIR_FICHA_CADASTRAL. */
	private static final String FILTRO_IMPRIMIR_FICHA_CADASTRAL = "imprimirFichaCadastral";
	
	/** A constante FILTRO_DATA_VIGENTE. */
	private static final String FILTRO_DATA_VIGENTE = "dataVigenteAntiga";
	
	/** A constante FILTRO_PERIODO_HISTORICO. */
	private static final String FILTRO_PERIODO_HISTORICO = "periodoHistoricoAntiga";
	
	/**
	 * Instancia um novo FichaCadastralDAOImpl.
	 */
	public FichaCadastralDAOImpl() {
		super(
				Constantes.Persistencia.DATASOURCE_CAPES,
				CAPESCadastroConstantes.Persistencia.ARQUIVO_QUERIES_FICHA_CADASTRAL,
				CAPESCadastroConstantes.Persistencia.COMANDOS_CADASTRO_UNICO_CLIENTE_FICHA_CADASTRAL);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> List<T> listar(Class<T> clazz, ConsultaDtoCUC<?> consultaDtoCUC) {
		return pesquisarLista(clazz, consultaDtoCUC, consultaDtoCUC.getNomeConsulta()) ;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <E> List<E> pesquisarLista(Class<E> clazz, ConsultaDto criterios,
			String nomePesquisa) {
		
		if (!(criterios instanceof ConsultaDtoCUC)) {
			throw new IllegalArgumentException("criterios não é uma instancia de ConsultaDtoCUC");
		}
				
		ConsultaDtoCUC<?> consultaDtoCUC = (ConsultaDtoCUC<?>) criterios;
		consultaDtoCUC.setClasseConsulta(getClasseConsulta(clazz, consultaDtoCUC));
		
		ativarfiltrosConsulta(consultaDtoCUC);
		
		List<E> pesquisarLista = null;
		
		try {
			pesquisarLista = super.pesquisarLista(clazz, consultaDtoCUC, nomePesquisa);
		} finally {
			desativarFiltros();
		}
		
		if (isConsultaEntidadeBase(consultaDtoCUC)) { 
			// Não é possivel realizar o order by quando a consulta é realizada na entidade base
			Collections.sort(pesquisarLista, new EntidadeCadastroBaseComparator());
		}
		
		return pesquisarLista;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings({ "unchecked" })
	public <E> E obterEntidadeAutorizacao(ConsultaAutorizacaoDTO dto) {
		
		List<Autorizacao> result = super.pesquisarLista(Autorizacao.class, dto, "PESQUISAR_ENTIDADE_EM_AUTORIZACAO");
		
		if (result != null
				&& !result.isEmpty()) {
			Autorizacao autorizacao = result.get(0);
			return (E) SerializacaoUtils.deserializarJson(dto.getTipoAutorizacao().getTipo(), 
					autorizacao.getAlteracao());
		}
		
		return null;
	}
	

	/**
	 * O método Ativarfiltros consulta.
	 *
	 * @param criterios o valor de criterios
	 */
	private void ativarfiltrosConsulta(ConsultaDtoCUC<?> criterios) {
		
		if (isConsultaPeriodo(criterios)) {
			ativarFiltroDataInicio(criterios);
			ativarFiltroDataFim(criterios);
		}
		
		if(isAutorizacaoFichaPrevia(criterios)){
			ativarFiltroFichaPrevia();
		}else{
			ativarFiltroFichaCadastral();
		}
		
	}

	/**
	 * Recupera o valor de classeConsulta.
	 *
	 * @param clazz o valor de clazz
	 * @param criterios o valor de criterios
	 * @return o valor de classeConsulta
	 */
	private String getClasseConsulta(Class<?> clazz, ConsultaDtoCUC<?> criterios){
		
		String classe = clazz.getName();
				
		if (isConsultaEntidadeBase(criterios)) {
			classe = obterNomeClasseBase(classe);
		}
		
		return classe;
	}

	/**
	 * Obter nome classe base.
	 *
	 * @param classe o valor de classe
	 * @return String
	 */
	private String obterNomeClasseBase(final String classe) {
		String className = classe.substring(classe.lastIndexOf('.') + 1);
		if (classe.contains("vigente")) {
			Integer indexVigente = classe.lastIndexOf(".vigente");
			className = classe.substring(0, indexVigente) + "." + className;
		} else if (classe.contains("bemantigo")) {
			className = classe;
		}

		return className + "Base";
	}
	
	/**
	 * O método Ativar filtro data inicio.
	 *
	 * @param criterios o valor de criterios
	 */
	private void ativarFiltroDataInicio(ConsultaDto<?> criterios) {
		
		Map<String, Object> parametros = null;
		if(criterios instanceof ConsultaDtoCUC<?>){
			parametros = new HashMap<String, Object>();
			parametros.put("data", ((ConsultaDtoCUC<?>) criterios).getData());
		}
		GerenciadorFiltro.ativarFiltro(super.getEntityManager(), FILTRO_DATA_VIGENTE, parametros);
	}

	/**
	 * O método Ativar filtro data fim.
	 *
	 * @param criterios o valor de criterios
	 */
	private void ativarFiltroDataFim(ConsultaDto<?> criterios) {
		
		Map<String, Object> parametros = null;
		if(criterios instanceof ConsultaDtoCUC<?>){
			parametros = new HashMap<String, Object>();
			parametros.put("data", ((ConsultaDtoCUC<?>) criterios).getData());
		}
		GerenciadorFiltro.ativarFiltro(super.getEntityManager(), FILTRO_PERIODO_HISTORICO,
		        parametros);
	}

	/**
	 * O método Ativar filtro ficha cadastral.
	 */
	private void ativarFiltroFichaCadastral() {
		GerenciadorFiltro.ativarFiltro(super.getEntityManager(), FILTRO_IMPRIMIR_FICHA_CADASTRAL,
		        null);
    }

	/**
	 * O método Ativar filtro ficha previa.
	 */
	private void ativarFiltroFichaPrevia() {
		GerenciadorFiltro.ativarFiltro(super.getEntityManager(),
		        FILTRO_IMPRIMIR_FICHA_CADASTRAL_PREVIA, null);
    }
	
	/**
	 * O método Desativar filtros.
	 */
	private void desativarFiltros() {
		EntityManager manager = super.getEntityManager();
		GerenciadorFiltro.desativarFiltro(manager, FILTRO_DATA_VIGENTE);
		GerenciadorFiltro.desativarFiltro(manager, FILTRO_PERIODO_HISTORICO);
		GerenciadorFiltro.desativarFiltro(manager, FILTRO_IMPRIMIR_FICHA_CADASTRAL_PREVIA);
		GerenciadorFiltro.desativarFiltro(manager, FILTRO_IMPRIMIR_FICHA_CADASTRAL);
	}

	/**
	 * Verifica se eh consulta periodo.
	 *
	 * @param criterios o valor de criterios
	 * @return {@code true}, se for consulta periodo
	 */
	private boolean isConsultaPeriodo(ConsultaDtoCUC<?> criterios) {		
		return criterios.getTipoConsulta() != null 
				&& criterios.getTipoConsulta() == TipoConsultaEnum.PERIODO;
	}

	/**
	 * Verifica se eh consulta entidade base.
	 *
	 * @param criterios o valor de criterios
	 * @return {@code true}, se for consulta entidade base
	 */
	private boolean isConsultaEntidadeBase(ConsultaDtoCUC<?> criterios) {
		return criterios.getTipoConsulta() != null 
				&& (criterios.getTipoConsulta() == TipoConsultaEnum.PERIODO 
				|| criterios.getTipoConsulta() == TipoConsultaEnum.TUDO);
	}
	
	/**
	 * Verifica se eh autorizacao ficha previa.
	 *
	 * @param criterios o valor de criterios
	 * @return {@code true}, se for autorizacao ficha previa
	 */
	private boolean isAutorizacaoFichaPrevia(ConsultaDtoCUC<?> criterios) {
		return criterios.getTipoConsulta() != null 
				&& criterios.getTipoConsulta() == TipoConsultaEnum.AUTORIZACAO_FICHA_PREVIA;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PersistenceContext(unitName = "emCapes")
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}

}