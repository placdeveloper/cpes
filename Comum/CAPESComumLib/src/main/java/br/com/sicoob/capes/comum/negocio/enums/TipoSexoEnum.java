/*
 * SICOOB
 * 
 * TipoSexoEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * @author erico.junior
 *
 */
public enum TipoSexoEnum {

	/** O atributo MASCULINO. */
	MASCULINO('M', "Masculino", 1),
	
	/** O atributo FEMININO. */
	FEMININO('F', "Feminino", 2),
	
	/** O atributo NAO_INFORMADO. */
	NAO_INFORMADO('N', "Não Informado", 9);

	/** O atributo codigo. */
	private Character codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo codigo rfb. */
	private Integer codigoRFB;

	/**
	 * Construtor do Enum.
	 * @param codigo O identificador do tipo de sexo.
	 * @param descricao A descrição do tipo de sexo.
	 */
	private TipoSexoEnum(Character codigo, String descricao, Integer codigoRFB) {
		this.descricao = descricao;
		this.codigo = codigo;
		this.codigoRFB = codigoRFB;
	}

	/**
	 * @return the codigo
	 */
	public Character getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}	
	
	/**
	 * Recupera codigo rfb.
	 * 
	 * @return codigo rfb
	 */
	public Integer getCodigoRFB() {
		return codigoRFB;
	}

	/**
	 * Recuperar tipo sexo enum por codigo.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return tipo sexo enum
	 */
	public static TipoSexoEnum recuperarTipoSexoEnumPorCodigo(Character codigo){
		
		TipoSexoEnum encontrada = null;
		
		for (TipoSexoEnum tipoSexoEnum :  TipoSexoEnum.values()) {
			if(tipoSexoEnum.getCodigo().equals(codigo)) {
				encontrada = tipoSexoEnum;
				break;
			}
		}
		return encontrada;
	}	
	
	/**
	 * Recuperar por codigo rfb.
	 * 
	 * @param codigoRFB
	 *            the codigo rfb
	 * @return tipo sexo enum
	 */
	public static TipoSexoEnum recuperarPorCodigoRFB(Integer codigoRFB){
		
		TipoSexoEnum encontrada = null;
		TipoSexoEnum[] values = values();
		for (int i = 0; (i < values.length) && (encontrada == null); i++) {
			TipoSexoEnum sexo = values[i];
			if(sexo.getCodigoRFB().equals(codigoRFB)) {
				encontrada = sexo;
			}
		}
		return encontrada;
	}	
}
