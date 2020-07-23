/*
 * SICOOB
 * 
 * SituacaoCadastralRFBEnum.java(br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * @author Rodrigo.Chaves
 */
public enum SituacaoCadastralRFBEnum {

	/*
	 * NÃO ALTERAR A ORDEM DOS TIPOS ABAIXO, POIS REFLETE NO BANCO: 
	 * 0 - ATIVA 
	 * 1 - BAIXADA 
	 * 2 - CANCELADA_ENCERRAMENTO_ESPOLIO 
	 * 3 - CANCELADA_MULTIPLICIDADE 
	 * 4 - CANCELADA_OBITO_SEM_ESPOLIO 
	 * 5 - CANCELADA_OFICIO
	 * 6 - INAPTA 
	 * 7 - NULA 
	 * 8 - PENDENTE_REGULARIZACAO 
	 * 9 - REGULAR 
	 * 10 - SUSPENSA
	 */
	/** O atributo ATIVA. */
	ATIVA(null, 2, "Ativa"),
	
	/** O atributo BAIXADA. */
	BAIXADA(null, 8, "Baixada"),
	
	/** O atributo CANCELADA_ENCERRAMENTO_ESPOLIO. */
	CANCELADA_ENCERRAMENTO_ESPOLIO(1, null, "Cancelada por encerramento de espólio"),
	
	/** O atributo CANCELADA_MULTIPLICIDADE. */
	CANCELADA_MULTIPLICIDADE(5, null, "Cancelada por multiplicidade"),
	
	/** O atributo CANCELADA_OBITO_SEM_ESPOLIO. */
	CANCELADA_OBITO_SEM_ESPOLIO(3, null, "Cancelada por óbito sem espólio"),
	
	/** O atributo CANCELADA_OFICIO. */
	CANCELADA_OFICIO(9, null, "Cancelada de ofício"),
	
	/** O atributo INAPTA. */
	INAPTA(null, 4, "Inapta"),
	
	/** O atributo NULA. */
	NULA(8, 1, "Nula"),
	
	/** O atributo PENDENTE_REGULARIZACAO. */
	PENDENTE_REGULARIZACAO(4, null, "Pendente de regularização"),
	
	/** O atributo REGULAR. */
	REGULAR(0, null, "Regular"),
	
	/** O atributo SUSPENSA. */
	SUSPENSA(2, 3, "Suspensa");

	/** A Constante SITUACOES_PF. */
	private static final SituacaoCadastralRFBEnum[] SITUACOES_PF = new SituacaoCadastralRFBEnum[] {
			CANCELADA_ENCERRAMENTO_ESPOLIO, CANCELADA_MULTIPLICIDADE, CANCELADA_OBITO_SEM_ESPOLIO, CANCELADA_OFICIO,
			NULA, PENDENTE_REGULARIZACAO, REGULAR, SUSPENSA };

	/** A Constante SITUACOES_PJ. */
	private static final SituacaoCadastralRFBEnum[] SITUACOES_PJ = new SituacaoCadastralRFBEnum[] { ATIVA, BAIXADA,
			INAPTA, NULA, SUSPENSA };

	/** O atributo codigo pf. */
	private Integer codigoPF;

	/** O atributo codigo pj. */
	private Integer codigoPJ;

	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor
	 * 
	 * @param codigoPF
	 * @param codigoPJ
	 * @param descricao
	 */
	private SituacaoCadastralRFBEnum(Integer codigoPF, Integer codigoPJ, String descricao) {

		this.codigoPF = codigoPF;
		this.codigoPJ = codigoPJ;
		this.descricao = descricao;
	}

	/**
	 * Recupera o codigo.
	 * 
	 * @return codigo
	 * @see #ordinal()
	 */
	public Integer getCodigo() {

		return ordinal();
	}

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Recupera codigo pf.
	 * 
	 * @return codigo pf
	 */
	public Integer getCodigoPF() {

		return codigoPF;
	}

	/**
	 * Recupera codigo pj.
	 * 
	 * @return codigo pj
	 */
	public Integer getCodigoPJ() {

		return codigoPJ;
	}

	/**
	 * Value of.
	 * 
	 * @param codigoRFB
	 *            the codigo RFB
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @return situacao cadastral rfb enum
	 */
	public static SituacaoCadastralRFBEnum valueOf(Integer codigoRFB, Short codTipoPessoa) {

		SituacaoCadastralRFBEnum value = null;

		boolean pessoaFisica = TipoPessoaEnum.isPessoaFisica(codTipoPessoa);
		SituacaoCadastralRFBEnum[] values = pessoaFisica ? SITUACOES_PF : SITUACOES_PJ;
		for (int i = 0; (i < values.length) && (value == null); i++) {
			SituacaoCadastralRFBEnum temp = values[i];
			Integer codigo = pessoaFisica ? temp.getCodigoPF() : temp.getCodigoPJ();
			if (codigo.equals(codigoRFB)) {
				value = temp;
			}
		}
		return value;
	}

	/**
	 * Value of.
	 * 
	 * @param codigoRFB
	 *            the codigo RFB
	 * @param tipoPessoa
	 *            the tipo pessoa
	 * @return situacao cadastral rfb enum
	 */
	public static SituacaoCadastralRFBEnum valueOf(Integer codigoRFB, TipoPessoaEnum tipoPessoa) {

		SituacaoCadastralRFBEnum situacao = null;
		if (tipoPessoa != null) {
			situacao = valueOf(codigoRFB, tipoPessoa.getCodigo());
		}
		return situacao;
	}

	/**
	 * Value of.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return a situação cadastral na RFB
	 * 
	 * @see #ordinal()
	 */
	public static SituacaoCadastralRFBEnum valueOf(Integer codigo) {

		SituacaoCadastralRFBEnum value = null;
		if ((codigo >= 0) && (codigo < values().length)) {
			value = values()[codigo];
		}
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return descricao + ": PF(" + codigoPF + ")/PJ(" + codigoPJ + ")";
	}
}
