/*
 * SICOOB
 * 
 * TipoValorParametroEnum.java(br.com.sicoob.capes.frontoffice.negocio.enums.TipoValorParametroEnum)
 */
package br.com.sicoob.capes.frontoffice.negocio.enums;

import java.sql.Types;

/**
 * O Enum TipoValorParametroEnum.
 */
public enum TipoValorParametroEnum {

	/** O atributo CHAR. */
	CHAR(Types.CHAR,           "CHAR       "),
	
	/** O atributo VARCHAR. */
	VARCHAR(Types.VARCHAR,     "VARCHAR    "),
	
	/** O atributo BIT. */
	BIT(Types.BIT,             "BIT        "),
	
	/** O atributo LONGVARCHAR. */
	LONGVARCHAR(Types.LONGVARCHAR, "LONGVARCHAR"),
	
	/** O atributo TINYINT. */
	TINYINT(Types.TINYINT,     "TINYINT    "),
	
	/** O atributo SMALLINT. */
	SMALLINT(Types.SMALLINT,   "SMALLINT   "),
	
	/** O atributo INTEGER. */
	INTEGER(Types.INTEGER,     "INTEGER    "),
	
	/** O atributo BIGINT. */
	BIGINT(Types.BIGINT,       "BIGINT     "),
	
	/** O atributo FLOAT. */
	FLOAT(Types.FLOAT,         "FLOAT      "),
	
	/** O atributo DOUBLE. */
	DOUBLE(Types.DOUBLE,       "DOUBLE     "),	
	
	/** O atributo NUMERIC. */
	NUMERIC(Types.NUMERIC,     "NUMERIC    "),
	
	/** O atributo DECIMAL. */
	DECIMAL(Types.DECIMAL,     "DECIMAL    "),
	
	/** O atributo DATE. */
	DATE(Types.DATE,           "DATE       "),
	
	/** O atributo TIMESTAMP. */
	TIMESTAMP(Types.TIMESTAMP, "TIMESTAMP  ");
	
	/** O atributo tipo jdbc. */
	private int tipoJdbc;
	
	/** O atributo nome tipo jdbc. */
	private String nomeTipoJdbc;
	
	/**
	 * Cria uma nova instância de tipo valor parametro enum.
	 * 
	 * @param tipoJdbc
	 *            the tipo jdbc
	 * @param nomeTipoJdbc
	 *            the nome tipo jdbc
	 */
	private TipoValorParametroEnum (int tipoJdbc, String nomeTipoJdbc) {
		this.tipoJdbc = tipoJdbc;
		this.nomeTipoJdbc = nomeTipoJdbc;
	}
	
	/**
	 * Recupera o enum do tipo do valor do parâmetro a partir do tipoJdbc informado
	 * @param tipoJdbc O código do tipo jdbc
	 * @return o enum do tipo do valor do parâmetro a partir do tipoJdbc informado
	 */
	public static TipoValorParametroEnum recuperarPorTipoJdbc(int tipoJdbc) {
		
		TipoValorParametroEnum encontrado = null;
		
		for (TipoValorParametroEnum tipo : TipoValorParametroEnum.values()) {
			if(tipo.getTipoJdbc() == tipoJdbc) {
				encontrado = tipo;
				break;
			}
		}
		
		return encontrado;
	}

	/**
	 * Recupera o atributo tipoJdbc
	 * @return o atributo tipoJdbc
	 */
	public int getTipoJdbc() {
		return tipoJdbc;
	}

	/**
	 * Recupera o atributo nomeTipoJdbc
	 * @return o atributo nomeTipoJdbc
	 */
	public String getNomeTipoJdbc() {
		return nomeTipoJdbc;
	}

}
