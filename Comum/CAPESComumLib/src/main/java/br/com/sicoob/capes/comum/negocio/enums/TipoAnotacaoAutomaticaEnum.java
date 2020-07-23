/*
 * SICOOB
 * 
 * TipoAnotacaoAutomaticaEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;


/**
 * Enum para os tipos de anotações automáticas.
 * @author Erico.Junior
 */
public enum TipoAnotacaoAutomaticaEnum {

	/** O atributo SERASA_NADA_CONSTA. */
	SERASA_NADA_CONSTA(1, "Nada Consta", true, OrigemInformacaoEnum.SERASA),
	
	/** O atributo SERASA_ACHEI. */
	SERASA_ACHEI(101, "Achei", OrigemInformacaoEnum.SERASA), 
	
	/** O atributo SERASA_PROTESTO. */
	SERASA_PROTESTO(305, "Protesto", OrigemInformacaoEnum.SERASA), 
	
	/** O atributo SERASA_ACAO_JUDICIAL. */
	SERASA_ACAO_JUDICIAL(306, "Ação Judicial", OrigemInformacaoEnum.SERASA), 
	
	/** O atributo SERASA_RECUPERACA_EXTRAJUDICIAL. */
	SERASA_RECUPERACA_EXTRAJUDICIAL(307, "Recuperação extrajudicial", OrigemInformacaoEnum.SERASA),
	
	/** O atributo SERASA_RECUPERACAO_JUDICIAL. */
	SERASA_RECUPERACAO_JUDICIAL(308, "Recuperação judicial", OrigemInformacaoEnum.SERASA),
	
	/** O atributo SERASA_REFIN. */
	SERASA_REFIN(309, "Refin/Financiamentos", OrigemInformacaoEnum.SERASA),
	
	/** O atributo SERASA_PEFIN. */
	SERASA_PEFIN(310, "Pefin", OrigemInformacaoEnum.SERASA),
	
	/** O atributo SERASA_CONVEM_DEVEDORES. */
	SERASA_CONVEM_DEVEDORES(311, "Convem Devedores", OrigemInformacaoEnum.SERASA),
	
	/** O atributo SERASA_CCF. */
	SERASA_CCF(502, "CCF", OrigemInformacaoEnum.SERASA),
	
	/** O atributo SERASA_FALENCIA. */
	SERASA_FALENCIA(503, "Falência/Concordata", OrigemInformacaoEnum.SERASA),
	
	/** O atributo SERASA_PARTICIPANTE_FALIDA. */
	SERASA_PARTICIPANTE_FALIDA(504, "Participante de empresa falida", OrigemInformacaoEnum.SERASA),
	
	/** O atributo BACEN_NADA_CONSTA. */
	BACEN_NADA_CONSTA(2, "Nada Consta", true, OrigemInformacaoEnum.BACEN),
	
	/** O atributo BACEN_VENCIDO_15_90_DIAS_SFN. */
	BACEN_VENCIDO_15_90_DIAS_SFN(
			314, "Crédito vencido de 15 a 90 dias no SFN", OrigemInformacaoEnum.BACEN),
	
	/** O atributo BACEN_VENCIDO_ACIMA_90_DIAS_SFN. */
	BACEN_VENCIDO_ACIMA_90_DIAS_SFN(
			315, "Crédito vencido acima de 90 dias no SFN", OrigemInformacaoEnum.BACEN),
	
	/** O atributo BACEN_CREDITO_BAIXADO_PREJUIXO_SFN. */
	BACEN_CREDITO_BAIXADO_PREJUIXO_SFN(
			316, "Crédito baixado como prejuízo no SFN", OrigemInformacaoEnum.BACEN),
	
	/** O atributo SISBR_CHEQUE_SEM_FUNDO. */
	SISBR_CHEQUE_SEM_FUNDO(100, "Cheque sem fundo", OrigemInformacaoEnum.SISBR),
	
	/** O atributo SISBR_INADIMPLENCIA_15_DIAS. */
	SISBR_INADIMPLENCIA_15_DIAS(300, "Inadimplência 15 dias – INAD15", OrigemInformacaoEnum.SISBR),
	
	/** O atributo INADIMPLENCIA_30_DIAS. */
	SISBR_INADIMPLENCIA_30_DIAS(320, "Inadimplência 30 dias  INAD30", OrigemInformacaoEnum.SISBR),
	
	/** O atributo SISBR_INADIMPLENCIA_90_DIAS. */
	SISBR_INADIMPLENCIA_90_DIAS(301, "Inadimplência 90 dias – INAD90", OrigemInformacaoEnum.SISBR),
	
	/** O atributo SISBR_OPERACAO_TRANSFERIDA_PREJUIZO. */
	SISBR_OPERACAO_TRANSFERIDA_PREJUIZO(
			302, "Operação transferida para prejuízo", OrigemInformacaoEnum.SISBR),
	
	/** O atributo SISBR_AVALISTA_INADIMPLENCIA_90_DIAS. */
	SISBR_AVALISTA_INADIMPLENCIA_90_DIAS(
			303, "Avalista de operação com INAD90", OrigemInformacaoEnum.SISBR),
	
	/** O atributo SISBR_AVALISTA_OPERACAO_PREJUIZO. */
	SISBR_AVALISTA_OPERACAO_PREJUIZO(
			304, "Avalista de operação em prejuízo", OrigemInformacaoEnum.SISBR), 
			
	/** O atributo RECEITA_NADA_CONSTA. */
	RECEITA_NADA_CONSTA(3, "Nada Consta", OrigemInformacaoEnum.RECEITA),
	
	/** O atributo RECEITA_CPF_IRREGULAR. */
	RECEITA_CPF_IRREGULAR(500, "CPF Irregular", OrigemInformacaoEnum.RECEITA),
	
	/** O atributo RECEITA_CNPJ_IRREGULAR. */
	RECEITA_CNPJ_IRREGULAR(501, "CNPJ Irregular", OrigemInformacaoEnum.RECEITA),
	
	CPF_IRREGULAR_SUSPENSA(512, "CPF IRREGULAR - SUSPENSA", OrigemInformacaoEnum.RECEITA),
	CPF_IRREGULAR_TITULAR_FALECIDO(513, "CPF IRREGULAR - TITULAR FALECIDO", OrigemInformacaoEnum.RECEITA),
	CPF_IRREGULAR_PENDENTE_REGULARIZACAO(514, "CPF IRREGULAR - PENDENTE DE REGULARIZAÇÃO", OrigemInformacaoEnum.RECEITA),
	CPF_IRREGULAR_CANCELADO_POR_MULTIPLICIDADE(515, "CPF IRREGULAR - CANCELADO POR MULTIPLICIDADE", OrigemInformacaoEnum.RECEITA),
	CPF_IRREGULAR_NULO(516, "CPF IRREGULAR - NULO", OrigemInformacaoEnum.RECEITA),
	CPF_IRREGULAR_CANCELADO_POR_OFICIO(517, "CPF IRREGULAR - CANCELADO POR OFÍCIO", OrigemInformacaoEnum.RECEITA),
	
	/** O atributo CADASTRO_DIGITAL. */
	CADASTRO_DIGITAL(518, "Cadastro incluído/alterado por uma conta digital", null),
	
	CADASTRO_SEM_RENOVACAO_36_MESES(519, "CADASTRO SEM RENOVAÇÃO HÁ MAIS DE 36 MESES", null);
	
	/** O atributo cod tipo anotacao. */
	private Integer codTipoAnotacao;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo nada consta. */
	private boolean nadaConsta; 
	
	/** O atributo origem. */
	private OrigemInformacaoEnum origem; 
	
	/**
	 * Construtor do Enum.
	 * @param codTipoAnotacao O identificador do tipo de anotação.
	 * @param descricao A descrição do tipo de anotação.
	 * @param origem A origem da informação.
	 */
	private TipoAnotacaoAutomaticaEnum(Integer codTipoAnotacao, String descricao, 
			OrigemInformacaoEnum origem) {
		this(codTipoAnotacao, descricao, false, origem);
	}

	/**
	 * Construtor do Enum.
	 * @param codTipoAnotacao O identificador do tipo de anotação.
	 * @param descricao A descrição do tipo de anotação.
	 * @param nadaConsta indica se a anotação é de nada consta.
	 * @param origem A origem da informação.
	 */
	private TipoAnotacaoAutomaticaEnum(Integer codTipoAnotacao, String descricao, 
			boolean nadaConsta, OrigemInformacaoEnum origem) {
		this.descricao = descricao;
		this.codTipoAnotacao = codTipoAnotacao;
		this.nadaConsta = nadaConsta;
		this.origem = origem;
	}
	
	/**
	 * @return the codTipoAnotacao
	 */
	public Integer getCodTipoAnotacao() {
		return codTipoAnotacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the nadaConsta
	 */
	public boolean isNadaConsta() {
		return nadaConsta;
	}
	
	/**
	 * @return the origem
	 */
	public OrigemInformacaoEnum getOrigem() {
		return origem;
	}

	/**
	 * Recupera o enum do tipo de anotação automática a partir do código informado.
	 * @param codigo O código do tipo de anotação.
	 * @return o enum do tipo de anotação automática a partir do código informado.
	 */
	public static TipoAnotacaoAutomaticaEnum 
			recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer codigo){
		
		TipoAnotacaoAutomaticaEnum[] tipos = TipoAnotacaoAutomaticaEnum.values();
		TipoAnotacaoAutomaticaEnum encontrada = null;
		
		for (TipoAnotacaoAutomaticaEnum tipoAnotacaoAutomaticaEnum : tipos) {
			if(tipoAnotacaoAutomaticaEnum.getCodTipoAnotacao().equals(codigo)) {
				encontrada = tipoAnotacaoAutomaticaEnum;
				break;
			}
		}
		
		return encontrada;
	}
}
