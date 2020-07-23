package br.com.sicoob.capes.cadastro.negocio.dto;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoConsultaEnum;

/**
 * A Classe ConsultaDtoCUC.
 *
 * @param <T> o tipo generico
 */
public class ConsultaDtoCUC<T> extends ConsultaDto<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6036208520540185612L;
	
	private DateTimeDB data;
	
	/** O atributo data. */
	private DateTimeDB dataFim;
	
	/** O atributo tipoConsulta. */
	private TipoConsultaEnum tipoConsulta = TipoConsultaEnum.ATUAL;
	
	/** O atributo nomeConsulta. */
	private String nomeConsulta;
	
	/** O atributo classeConsulta. */
	private String classeConsulta;
	
	
	/**
	 * Recupera o valor de dataFim.
	 *
	 * @return o valor de dataFim
	 */
	public DateTimeDB getDataFim() {
		return dataFim;
	}

	/**
	 * Define o valor de dataFim.
	 *
	 * @param dataFim o novo valor de dataFim
	 */
	public void setDataFim(DateTimeDB dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * Recupera o valor de classeConsulta.
	 *
	 * @return o valor de classeConsulta
	 */
	public String getClasseConsulta() {
		return classeConsulta;
	}
	
	/**
	 * Define o valor de classeConsulta.
	 *
	 * @param classeConsulta o novo valor de classeConsulta
	 */
	public void setClasseConsulta(String classeConsulta) {
		this.classeConsulta = classeConsulta;
	}
	
	/**
	 * Recupera o valor de nomeConsulta.
	 *
	 * @return o valor de nomeConsulta
	 */
	public String getNomeConsulta() {
		return nomeConsulta;
	}
	
	/**
	 * Define o valor de nomeConsulta.
	 *
	 * @param nomeConsulta o novo valor de nomeConsulta
	 */
	public void setNomeConsulta(String nomeConsulta) {
		this.nomeConsulta = nomeConsulta;
	}
	
	/**
	 * Recupera o valor de data.
	 *
	 * @return o valor de data
	 */
	public DateTimeDB getData() {
		return data;
	}
	
	/**
	 * Define o valor de data.
	 *
	 * @param data o novo valor de data
	 */
	public void setData(DateTimeDB data) {
		this.data = data;
	}
	
	/**
	 * Recupera o valor de tipoConsulta.
	 *
	 * @return o valor de tipoConsulta
	 */
	public TipoConsultaEnum getTipoConsulta() {
		return tipoConsulta;
	}
	
	/**
	 * Define o valor de tipoConsulta.
	 *
	 * @param tipoConsulta o novo valor de tipoConsulta
	 */
	public void setTipoConsulta(TipoConsultaEnum tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	} 
}