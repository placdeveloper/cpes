package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe EntidadeCadastroCollectionDataSource.
 */
public class EntidadeCadastroCollectionDataSource extends
		JRBeanCollectionDataSource implements Serializable{

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 571142855770345192L;
	
	/** A constante CAMPO_VIGENCIA_REGISTRO. */
	private static final String CAMPO_VIGENCIA_REGISTRO = "vigenciaRegistro";
	
	/** A constante LOGGER. */
	private static final ISicoobLogger LOGGER = SicoobLoggerPadrao
			.getInstance(EntidadeCadastroCollectionDataSource.class);
	
	/**
	 * Instancia um novo EntidadeCadastroCollectionDataSource.
	 *
	 * @param beanCollection o valor de bean collection
	 */
	public EntidadeCadastroCollectionDataSource(Collection<?> beanCollection) {
		super(beanCollection);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean next()
	  {
	    boolean hasNext = super.next();

	    return hasNext;
	  }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object getFieldValue(Object bean, JRField field) throws JRException
	{	
		
		Object retorno = null;
		
		String propertyName = getPropertyName(field);
		
		LOGGER.debug("\t### PROPRIEDADE: " + bean.getClass() + "." + propertyName + " ###");
		if (isCampoVigencia(propertyName)) {
			
			StringBuilder retornoStr = new StringBuilder();
			
			if (isVigente(bean)) {
				LOGGER.debug("\t### VIGENTE ###");
				Date dataHoraInicio = obterDataHoraInicioVigente(bean);
				if (dataHoraInicio != null) {
					retornoStr.append("A partir de ").append(formatarData(dataHoraInicio));
				}else{
					retornoStr.append("EM AUTORIZAÇÃO");
				}
			} else if (isHistorico(bean)) {
				LOGGER.debug("\t### HISTÓRICO ###");
				Date dataHoraInicio = obterDataHoraInicioHistorico(bean);
				Date dataHoraFim = obterDataHoraFimHistorico(bean);
				retornoStr.append(formatarData(dataHoraInicio));
				
				if (dataHoraInicio != null) {
					retornoStr.append(" a ");
				}
				retornoStr.append(formatarData(dataHoraFim));
			}
			LOGGER.debug("\t### VIGENCIA_REGISTRO: " + retornoStr.toString() + " ###");
			retorno = retornoStr.toString();
		} else {
			
			if (bean instanceof HistoricoPessoaCompartilhamento //FIXME O nome do identificador da entidade é diferente para o histórico 
					&& propertyName.equals("idPessoaCompartilhamento")) {
				return ((HistoricoPessoaCompartilhamento) bean).getId();
			}
			
			retorno = super.getFieldValue(bean, field);
		}
		
		return retorno;
	}

	/**
	 * Verifica se eh campo vigencia.
	 *
	 * @param propertyName o valor de property name
	 * @return {@code true}, se for campo vigencia
	 */
	private boolean isCampoVigencia(String propertyName) {
		return propertyName.equals(CAMPO_VIGENCIA_REGISTRO);
	}

	/**
	 * Verifica se eh historico.
	 *
	 * @param bean o valor de bean
	 * @return {@code true}, se for historico
	 */
	private boolean isHistorico(Object bean) {
		return bean instanceof Historico;
	}

	/**
	 * Verifica se eh vigente.
	 *
	 * @param bean o valor de bean
	 * @return {@code true}, se for vigente
	 */
	private boolean isVigente(Object bean) {
		return bean instanceof Vigente;
	}

	/**
	 * Formatar data.
	 *
	 * @param dataHora o valor de data hora
	 * @return String
	 */
	private String formatarData(Date dataHora) {
		String retorno = "";
		
		if (dataHora != null){
			retorno = DataUtil.toStringBr(dataHora);
		}
		
		return retorno;
	}

	/**
	 * Obter data hora inicio vigente.
	 *
	 * @param bean o valor de bean
	 * @return Date
	 */
	private Date obterDataHoraInicioVigente(Object bean) {
		return ((Vigente) bean).getDataHoraInicio();
	}

	/**
	 * Obter data hora inicio historico.
	 *
	 * @param bean o valor de bean
	 * @return Date
	 */
	private Date obterDataHoraInicioHistorico(Object bean) {
		return ((Historico) bean).getDataHoraInicio();
	}
	
	/**
	 * Obter data hora fim historico.
	 *
	 * @param bean o valor de bean
	 * @return Date
	 */
	private Date obterDataHoraFimHistorico(Object bean) {
		return ((Historico) bean).getDataHoraFim();
	}
}
