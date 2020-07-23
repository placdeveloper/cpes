/*
 * SICOOB
 * 
 * VigenteInterceptor.java(br.com.sicoob.capes.negocio.entidades.interceptors.VigenteInterceptor)
 */
package br.com.sicoob.capes.negocio.entidades.interceptors;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Ibem;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Interceptor responsavel por atribuir a dataHoraInicio nas entidades que
 * implementam a interface {@link Vigente}.
 * 
 * @author Juan.Damasceno
 * 
 */
public class VigenteInterceptor extends EmptyInterceptor {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -7333631969997922330L;
	
	/** A Constante DATA_HORA_INICIO. */
	private static final String DATA_HORA_INICIO = "dataHoraInicio";
	
	/** A Constante ID_INSTITUICAO_ATUALIZACAO. */
	private static final String ID_INSTITUICAO_ATUALIZACAO = "idInstituicaoAtualizacao";
	
	/** A Constante LOGGER. */
	private static final SicoobLoggerPadrao LOGGER = SicoobLoggerPadrao.getInstance(VigenteInterceptor.class);

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames,
			Type[] types) {
		
		boolean modified = false;
		if (entity instanceof Vigente) {
			
			LOGGER.info("Interceptando para geracao da data de inicio: ", entity.getClass().getName(),
					"#", String.valueOf(id));
			
			modified  = setValue(entity, id, propertyNames, state, DATA_HORA_INICIO, new DateTimeDB());
		}
		return modified;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,
			Object[] previousState, String[] propertyNames, Type[] types) {

		boolean modified = false;
		if (entity instanceof Vigente) {
			
			LOGGER.info("Interceptando para geracao da data de inicio: ", entity.getClass().getName(),
					"#", String.valueOf(id));
			
			modified = setValue(entity, id, propertyNames, currentState, DATA_HORA_INICIO, new DateTimeDB());
		}
		return modified;
	}

	/**
	 * Sets the value.
	 * 
	 * @param entity
	 *            the entity
	 * @param id
	 *            the id
	 * @param propertyNames
	 *            the property names
	 * @param currentState
	 *            the current state
	 * @param propertyToSet
	 *            the property to set
	 * @param value
	 *            the value
	 * @return true, em caso de sucesso
	 */
	private boolean setValue(Object entity, Serializable id, String[] propertyNames,
			Object[] currentState, String propertyToSet, Object value) {

		String identificacaoEntidade = entity.getClass().getName() + "#" + id;
		boolean setValue = true;
		
		if (entity instanceof Aprovavel) {
			Integer idInstituicaoAtualizacao =
					(Integer) currentState[getPropertyIndex(propertyNames,
							ID_INSTITUICAO_ATUALIZACAO)];
			setValue = (idInstituicaoAtualizacao == null);
			
			LOGGER.info("Entidade aprovavel interceptada: ", identificacaoEntidade,
					"{idInstituicaoAtualizacao=", String.valueOf(idInstituicaoAtualizacao), "}");
		}
		
		//tratamento para as listas do bem não inserir data hora início se tiverem em autorização
		if(entity instanceof Ibem){
			Ibem iBem = (Ibem)entity;
			br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemPai = iBem.getBem();
			setValue = bemPai.getIdInstituicaoAtualizacao() == null;
		}
		
		if(entity instanceof BemPessoaCompartilhamento) {
			BemPessoaCompartilhamento bemPessoaCompartilhamento = (BemPessoaCompartilhamento) entity;
			Bem bem = bemPessoaCompartilhamento.getBem();
			setValue = bem.getIdInstituicaoAtualizacao() == null;
		}

		if (setValue) {
			int index = getPropertyIndex(propertyNames, propertyToSet);
			
			LOGGER.info("Gerando data inicio: ", identificacaoEntidade, "{", propertyToSet, "=",
					String.valueOf(currentState[index]), "}");
			
			if (index >= 0) {
				currentState[index] = value;
				Field propriedade = ReflexaoUtils.getField(entity.getClass(), DATA_HORA_INICIO);
				ReflexaoUtils.setValorAtributo(entity ,propriedade , value, false);
			}
			
			LOGGER.info("Data inicio gerada: ", identificacaoEntidade, "{", propertyToSet, "=",
					String.valueOf(currentState[index]), "}");
		}
		
		return setValue;
	}

	/**
	 * Recupera property index.
	 * 
	 * @param propertyNames
	 *            the property names
	 * @param property
	 *            the property
	 * @return property index
	 */
	private int getPropertyIndex(String[] propertyNames, String property) {
		return Arrays.asList(propertyNames).indexOf(property);
	}
	
}