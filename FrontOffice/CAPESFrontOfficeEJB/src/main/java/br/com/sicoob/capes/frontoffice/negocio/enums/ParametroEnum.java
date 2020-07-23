/*
 * SICOOB
 * 
 * ParametroEnum.java(br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum)
 */
package br.com.sicoob.capes.frontoffice.negocio.enums;

import java.math.BigDecimal;
import java.sql.Types;

import br.com.bancoob.srtb.dto.TipoParametro;

/**
 * The Enum ParametroEnum.
 */
public enum ParametroEnum {

	/*
	 * CodRetorno TINYINT(1) OUTPUT ERRO_CODIGO 00062 Mensagem VARCHAR(200) OUTPUT ERRO_MENSAGEM 00063 CampoErro
	 * VARCHAR(50) OUTPUT CAMPO_ERRO 00065 Instituicao INTEGER INPUT Id da Instituição do cliente INSTITUICAO 09306
	 * CpfCnpj VARCHAR INPUT CPF ou CNPJ do cliente CPF_CNPJ 09007 CodTipoAnotacao INTEGER INPUT Código do Tipo de
	 * anotação COD_TIPO_ANOTACAO 02111
	 */
	/** O atributo CODIGO_ERRO. */
	CODIGO_ERRO("CodRetorno", "00062", Short.class, Types.TINYINT, TipoParametro.SAIDA),
	
	/** O atributo MENSAGEM_ERRO. */
	MENSAGEM_ERRO("Mensagem", "00063", String.class, Types.VARCHAR, TipoParametro.SAIDA),
	
	/** O atributo CAMPO_ERRO. */
	CAMPO_ERRO("CampoErro", "00065", String.class, Types.VARCHAR, TipoParametro.SAIDA),
	
	/** O atributo INSTITUICAO. */
	INSTITUICAO("Instituicao", "09306", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	
	/** O atributo CPF_CNPJ. */
	CPF_CNPJ("CpfCnpj", "09007", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	
	/** O atributo NUMERO_PESSOA. */
	NUMERO_PESSOA("NumeroPessoa", "03031", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	
	/** O atributo CODIGO_TIPO_ANOTACAO. */
	CODIGO_TIPO_ANOTACAO("CodTipoAnotacao", "02111", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	
	/** O atributo NUMERO_COOP_REMETENTE. */
	NUMERO_COOP_REMETENTE("NumeroCoopRemetente", "00205", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	
	TIPO_PESSOA("TipoPessoa", "01805", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	
	/** O atributo ID_PESSOA. */
	ID_PESSOA("IdPessoa", "09471", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	
	/** O atributo ID_EMAIL. */
	ID_EMAIL("IdEmail", "09520", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	
	/** O atributo CODIGO_TIPO_EMAIL. */
	CODIGO_TIPO_EMAIL("CodigoTipoEmail", "09521", Short.class, Types.SMALLINT, TipoParametro.ENTRADA),
	
	/** O atributo DESC_EMAIL. */
	DESC_EMAIL("DescEmail", "09162", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	
	/** O atributo ID_TELEFONE. */
	ID_TELEFONE("IdTelefone", "09522", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	
	/** O atributo CODIGO_TIPO_TELEFONE. */
	CODIGO_TIPO_TELEFONE("CodigoTipoTelefone", "09523", Short.class, Types.SMALLINT, TipoParametro.ENTRADA),
	
	/** O atributo TELEFONE. */
	TELEFONE("Telefone", "08046", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	
	/** O atributo DDD. */
	DDD("Ddd", "01425", String.class, Types.CHAR, TipoParametro.ENTRADA),
	
	/** O atributo RAMAL. */
	RAMAL("Ramal", "01426", String.class, Types.CHAR, TipoParametro.ENTRADA),
	
	/** O atributo DESC_OBSERVACAO. */
	DESC_OBSERVACAO("DescObservacao", "08056", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	
	ID_RENDA("IdRenda", "09534", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	COD_TIPO_RENDA("CodTipoRenda", "09526", Short.class, Types.SMALLINT, TipoParametro.ENTRADA),
	VALOR_TRANSACAO("ValorTransacao", "09418", BigDecimal.class, Types.DECIMAL, TipoParametro.ENTRADA),
	
	ID_ENDERECO("IdEndereco", "09256", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	COD_TIPO_ENDERECO("CodTipoEndereco", "09524", Short.class, Types.SMALLINT, TipoParametro.ENTRADA),
	COD_TIPO_LOGRADOURO("CodTipoLogradouro", "09525", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	ID_LOCALIDADE("IdLocalidade", "09470", Integer.class, Types.INTEGER, TipoParametro.ENTRADA),
	DESC_CEP("DescCep", "09161", String.class, Types.CHAR, TipoParametro.ENTRADA),
	DESC_ENDERECO("DescEndereco", "01052", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	DESC_SEU_NUMERO("DescSeuNumero", "08054", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	DESC_COMPLEMENTO("DescComplemento", "09528", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	DESC_NOME_BAIRRO("DescNomeBairro", "09159", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	
	DOCUMENTO_COMPROBATORIO("DocumentoComprobatorio", "09428", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	NOME_ARQUIVO_DOCUMENTO_COMPROBATORIO("NomeArquivoDocumentoComprobatorio", "09165", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	EXTENSAO_ARQUIVO_DOCUMENTO_COMPROBATORIO("ExtensaoArquivoDocumentoComprobatorio", "09180", String.class, Types.VARCHAR, TipoParametro.ENTRADA),
	
	/** O atributo ID_USUARIO. */
	ID_USUARIO("IdUsuario", "00184", String.class, Types.VARCHAR, TipoParametro.ENTRADA);
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo rotulo. */
	private String rotulo;
	
	/** O atributo tipo parametro. */
	private TipoParametro tipoParametro;
	
	/** O atributo tipo dado. */
	private Class<?> tipoDado;

	/** @see Types */
	private Integer tipoDadoPersistencia;

	/**
	 * Cria uma nova instância de parametro enum.
	 * 
	 * @param nome
	 *            the nome
	 * @param rotulo
	 *            the rotulo
	 * @param tipoDado
	 *            the tipo dado
	 * @param tipoDadoPersistencia
	 *            the tipo dado persistencia
	 * @param tipoParametro
	 *            the tipo parametro
	 */
	private ParametroEnum(String nome, String rotulo, Class<?> tipoDado, Integer tipoDadoPersistencia, TipoParametro tipoParametro) {

		this.nome = nome;
		this.rotulo = rotulo;
		this.tipoDado = tipoDado;
		this.tipoDadoPersistencia = tipoDadoPersistencia;
		this.tipoParametro = tipoParametro;
	}

	/**
	 * Recupera nome.
	 * 
	 * @return nome
	 */
	public String getNome() {

		return nome;
	}

	/**
	 * Recupera rotulo.
	 * 
	 * @return rotulo
	 */
	public String getRotulo() {

		return rotulo;
	}

	/**
	 * Verifica se é entrada.
	 * 
	 * @return true, se for entrada
	 */
	public boolean isEntrada() {
	
		return tipoParametro.equals(TipoParametro.ENTRADA);
	}
	
	/**
	 * Verifica se é saida.
	 * 
	 * @return true, se for saida
	 */
	public boolean isSaida() {
		
		return tipoParametro.equals(TipoParametro.SAIDA);
	}

	
	/**
	 * Recupera tipo parametro.
	 * 
	 * @return tipo parametro
	 */
	public TipoParametro getTipoParametro() {
	
		return tipoParametro;
	}

	/**
	 * Recupera tipo dado.
	 * 
	 * @return tipo dado
	 */
	public Class<?> getTipoDado() {
	
		return tipoDado;
	}

	/**
	 * Recupera tipo dado persistencia.
	 * 
	 * @return tipo dado persistencia
	 */
	public Integer getTipoDadoPersistencia() {

		return tipoDadoPersistencia;
	}

}
