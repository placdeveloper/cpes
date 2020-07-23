/*
 * SICOOB
 * 
 * TipoPessoaEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

import br.com.sicoob.capes.comum.util.Constantes.Negocio;

/**
 * Enum utilizado para o tipo de pessoa. 
 * PF ou PJ.
 * @author erico.junior
 */
public enum TipoPessoaEnum {

	/** O atributo PESSOA_FISICA. */
	PESSOA_FISICA((short)0, "Pessoa Física", Negocio.GED_SIGLA_ASSUNTO_PF),
	
	/** O atributo PESSOA_JURIDICA. */
	PESSOA_JURIDICA((short)1, "Pessoa Jurídica", Negocio.GED_SIGLA_ASSUNTO_PJ);

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo sigla assunto ged. */
	private String siglaAssuntoGED;

	/**
	 * Construtor do Enum.
	 * @param codigo O identificador do tipo de pessoa.
	 * @param descricao A descrição do tipo de pessoa.
	 */
	private TipoPessoaEnum(Short codigo, String descricao, String siglaAssuntoGED) {
		this.descricao = descricao;
		this.codigo = codigo;
		this.siglaAssuntoGED = siglaAssuntoGED;
	}

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * @return the siglaAssuntoGED
	 */
	public String getSiglaAssuntoGED() {
		return siglaAssuntoGED;
	}
	
	/**
	 * Recupera documento.
	 * 
	 * @return documento
	 */
	public String getDocumento() {
		return PESSOA_FISICA.equals(this) ? "CPF" : "CNPJ";
	}
	
	/**
	 * Recupera sigla assunto ged.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return sigla assunto ged
	 */
	public static String getSiglaAssuntoGED(Short codigo) {
		return values()[codigo].getSiglaAssuntoGED();
	}

	/**
	 * Verifica se é pessoa fisica.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return true, se for pessoa fisica
	 */
	public static boolean isPessoaFisica(Short codigo) {
		return PESSOA_FISICA.getCodigo().equals(codigo);
	}
	
	/**
	 * Verifica se é pessoa juridica.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return true, se for pessoa juridica
	 */
	public static boolean isPessoaJuridica(Short codigo) {
		return PESSOA_JURIDICA.getCodigo().equals(codigo);
	}
	
	/**
	 * Value of.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return tipo pessoa enum
	 */
	public static TipoPessoaEnum valueOf(Short codigo) {
		TipoPessoaEnum value = null;
		TipoPessoaEnum[] values = values();
		for (int i = 0; (i < values.length) && (value == null); i++) {
			TipoPessoaEnum tipo = values[i];
			if (tipo.codigo.equals(codigo)) {
				value = tipo;
			}
		}
		return value;
	}
}