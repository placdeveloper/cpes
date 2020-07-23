package br.com.sicoob.capes.processamento.persistencia.dao.enums;

import br.com.sicoob.capes.comum.negocio.enums.TipoInformacaoEnum;
import br.com.sicoob.capes.processamento.persistencia.transformador.TranformadorResultado;
import br.com.sicoob.capes.processamento.persistencia.transformador.exportacaoarquivo.TransformadorRegistroCooperativa;
import br.com.sicoob.capes.processamento.persistencia.transformador.exportacaoarquivo.TransformadorRegistroEndereco;
import br.com.sicoob.capes.processamento.persistencia.transformador.exportacaoarquivo.TransformadorRegistroPessoa;

/**
 * O Enum ComplementoTipoInformacaoEnum.
 */
public enum ComplementoTipoInformacaoEnum {

	// O tipo informação profissional não é exportado.
	/** O atributo INFORMACAO_PROFISSIONAL. */
	INFORMACAO_PROFISSIONAL (TipoInformacaoEnum.INFORMACAO_PROFISSIONAL),
	
	/** O atributo ENDERECO. */
	ENDERECO				(TipoInformacaoEnum.ENDERECO, " LEFT JOIN CLI.ENDERECOPESSOA EP ON EP.IDPESSOACOMPARTILHAMENTO = V.IDPESSOACOMPARTILHAMENTO	AND DATE(V.DATAHORAULTIMAATUALIZACAO) = DATE(EP.DATAHORAINICIO)", " OR EP.IDENDERECOPESSOA IS NOT NULL", TipoQuery.SQL, TransformadorRegistroEndereco.class),
	
	/** O atributo TELEFONE. */
	TELEFONE				(TipoInformacaoEnum.TELEFONE, " LEFT JOIN CLI.TELEFONEPESSOA TP ON V.IDPESSOACOMPARTILHAMENTO = TP.IDPESSOACOMPARTILHAMENTO	AND DATE(V.DATAHORAULTIMAATUALIZACAO) = DATE(TP.DATAHORAINICIO)", " OR TP.IDTELEFONEPESSOA IS NOT NULL"),
	
	/** O atributo EMAIL. */
	EMAIL					(TipoInformacaoEnum.EMAIL, " LEFT JOIN CLI.EMAILPESSOA EMAIL ON V.IDPESSOACOMPARTILHAMENTO = EMAIL.IDPESSOACOMPARTILHAMENTO AND DATE(V.DATAHORAULTIMAATUALIZACAO) = DATE(EMAIL.DATAHORAINICIO)", " OR EMAIL.IDEMAILPESSOA IS NOT NULL"),
	
	/** O atributo FONTE_RENDA. */
	FONTE_RENDA				(TipoInformacaoEnum.FONTE_RENDA, " LEFT JOIN CLI.FONTERENDA FR ON FR.IDPESSOACOMPARTILHAMENTO = V.IDPESSOACOMPARTILHAMENTO AND DATE(V.DATAHORAULTIMAATUALIZACAO) = DATE(FR.DATAHORAINICIO)", " OR FR.IDFONTERENDA IS NOT NULL"),
	
	/** O atributo BEM. */
	BEM						(TipoInformacaoEnum.BEM, " LEFT JOIN CLI.BEMPESSOA BP ON BP.IDPESSOACOMPARTILHAMENTO = V.IDPESSOACOMPARTILHAMENTO AND DATE(V.DATAHORAULTIMAATUALIZACAO) = DATE(EMAIL.DATAHORAINICIO)", " OR BP.IDBEMPESSOA IS NOT NULL"),
	
	/** O atributo PAC. */
	PAC						(TipoInformacaoEnum.PAC, " LEFT JOIN CLI.PESSOAINSTITUICAO PI ON PI.IDPESSOA = P.IDPESSOA AND DATE(V.DATAHORAULTIMAATUALIZACAO) = DATE(PI.DATAHORAINICIO)", " OR PI.IDPESSOAINSTITUICAO IS NOT NULL", TipoQuery.SQL, TransformadorRegistroCooperativa.class),
	
	/** O atributo PESSOA. */
	PESSOA					(TipoInformacaoEnum.PESSOA, TipoQuery.SQL, TransformadorRegistroPessoa.class);

	/**
	 * O Enum TipoQuery.
	 */
	public enum TipoQuery {
		
		/** O atributo SQL. */
		SQL,
		
		/** O atributo HQL. */
		HQL;
	}
	
	/** O atributo tipoInformacao. */
	private TipoInformacaoEnum tipoInformacao;
	
	/** O atributo complementoFrom. */
	private String complementoFrom = "";
	
	/** O atributo complementoWhere. */
	private String complementoWhere = "";
	
	/** O atributo tipoQuery. */
	private TipoQuery tipoQuery = TipoQuery.HQL;
	
	/** O atributo transformador. */
	private Class<? extends TranformadorResultado<?>> transformador;
	
	/**
	 * Instancia um novo ComplementoTipoInformacaoEnum.
	 *
	 * @param tipoInformacao o valor de tipo informacao
	 * @param complementoFrom o valor de complemento from
	 * @param complementoWhere o valor de complemento where
	 */
	private ComplementoTipoInformacaoEnum(TipoInformacaoEnum tipoInformacao, String complementoFrom, String complementoWhere) {
		this.tipoInformacao = tipoInformacao;
		this.complementoFrom = complementoFrom;
		this.complementoWhere = complementoWhere;
	}

	/**
	 * Instancia um novo ComplementoTipoInformacaoEnum.
	 *
	 * @param tipoInformacao o valor de tipo informacao
	 * @param complementoFrom o valor de complemento from
	 * @param complementoWhere o valor de complemento where
	 * @param tipoQuery o valor de tipo query
	 * @param transformador o valor de transformador
	 */
	private ComplementoTipoInformacaoEnum(TipoInformacaoEnum tipoInformacao, String complementoFrom, String complementoWhere, TipoQuery tipoQuery, Class<? extends TranformadorResultado<?>> transformador) {
		this.tipoInformacao = tipoInformacao;
		this.complementoFrom = complementoFrom;
		this.complementoWhere = complementoWhere;
		this.tipoQuery = tipoQuery;
		this.transformador = transformador;
	}

	/**
	 * Instancia um novo ComplementoTipoInformacaoEnum.
	 *
	 * @param tipoInformacao o valor de tipo informacao
	 */
	private ComplementoTipoInformacaoEnum(TipoInformacaoEnum tipoInformacao) {
		this.tipoInformacao = tipoInformacao;
	}

	/**
	 * Instancia um novo ComplementoTipoInformacaoEnum.
	 *
	 * @param tipoInformacao o valor de tipo informacao
	 * @param tipoQuery o valor de tipo query
	 * @param transformador o valor de transformador
	 */
	private ComplementoTipoInformacaoEnum(TipoInformacaoEnum tipoInformacao, TipoQuery tipoQuery, Class<? extends TranformadorResultado<?>> transformador) {
		this.tipoInformacao = tipoInformacao;
		this.tipoQuery = tipoQuery;
		this.transformador = transformador;
	}

	/**
	 * Recupera o valor de tipoInformacao.
	 *
	 * @return o valor de tipoInformacao
	 */
	public TipoInformacaoEnum getTipoInformacao() {
		return tipoInformacao;
	}

	/**
	 * Recupera o valor de complementoFrom.
	 *
	 * @return o valor de complementoFrom
	 */
	public String getComplementoFrom() {
		return complementoFrom;
	}

	/**
	 * Recupera o valor de complementoWhere.
	 *
	 * @return o valor de complementoWhere
	 */
	public String getComplementoWhere() {
		return complementoWhere;
	}

	/**
	 * Recupera o valor de tipoQuery.
	 *
	 * @return o valor de tipoQuery
	 */
	public TipoQuery getTipoQuery() {
		return tipoQuery;
	}

	/**
	 * Gets the transformador.
	 *
	 * @return the transformador
	 */
	public Class<? extends TranformadorResultado<?>> getTransformador() {
		return transformador;
	}

	/**
	 * Value of.
	 *
	 * @param tipoInformacao o valor de tipo informacao
	 * @return ComplementoTipoInformacaoEnum
	 */
	public static ComplementoTipoInformacaoEnum valueOf(TipoInformacaoEnum tipoInformacao) {
		for (ComplementoTipoInformacaoEnum enumm : values()) {
			if (enumm.getTipoInformacao().equals(tipoInformacao)) {
				return enumm;
			}
		}
		throw new IllegalArgumentException("Complemento do tipo da informacao nao encontrado: " + tipoInformacao.getDescricao());
	}

}