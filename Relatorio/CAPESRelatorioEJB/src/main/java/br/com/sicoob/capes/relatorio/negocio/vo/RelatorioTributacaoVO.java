package br.com.sicoob.capes.relatorio.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe RelatorioTributacaoVO.
 */
public class RelatorioTributacaoVO extends BancoobVo {

    /** A constante serialVersionUID. */
    private static final long serialVersionUID = 2712859992100379618L;
    
    /** O atributo numeroCentral. */
    private String numeroCentral;
	
	/** O atributo numeroCooperativa. */
	private String numeroCooperativa;
	
	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;
	
	/** O atributo nomePessoa. */
	private String nomePessoa;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo cobraIR. */
	private Boolean cobraIR;
	
	/** O atributo cobraIOF. */
	private Boolean cobraIOF;
	
	/** O atributo codigoOcupacao. */
	private String codigoOcupacao;
	
	/** O atributo descOcupacao. */
	private String descOcupacao;

	/**
	 * @return the numeroCooperativa
	 */
	public String getNumeroCooperativa() {
		return numeroCooperativa;
	}

	/**
	 * @param numeroCooperativa
	 *            the numeroCooperativa to set
	 */
	public void setNumeroCooperativa(String numeroCooperativa) {
		this.numeroCooperativa = numeroCooperativa;
	}

	/**
	 * @return the idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * @param idUnidadeInst
	 *            the idUnidadeInst to set
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * @param nomePessoa
	 *            the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj
	 *            the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return the cobraIR
	 */
	public Boolean getCobraIR() {
		return cobraIR;
	}

	/**
	 * @param cobraIR
	 *            the cobraIR to set
	 */
	public void setCobraIR(Boolean cobraIR) {
		this.cobraIR = cobraIR;
	}

	/**
	 * @return the cobraIOF
	 */
	public Boolean getCobraIOF() {
		return cobraIOF;
	}

	/**
	 * @param cobraIOF
	 *            the cobraIOF to set
	 */
	public void setCobraIOF(Boolean cobraIOF) {
		this.cobraIOF = cobraIOF;
	}

	/**
	 * @return the codigoOcupacao
	 */
	public String getCodigoOcupacao() {
		return codigoOcupacao;
	}

	/**
	 * @param codigoOcupacao
	 *            the codigoOcupacao to set
	 */
	public void setCodigoOcupacao(String codigoOcupacao) {
		this.codigoOcupacao = codigoOcupacao;
	}

	/**
	 * @return the descOcupacao
	 */
	public String getDescOcupacao() {
		return descOcupacao;
	}

	/**
	 * @param descOcupacao
	 *            the descOcupacao to set
	 */
	public void setDescOcupacao(String descOcupacao) {
		this.descOcupacao = descOcupacao;
	}

	/**
     * @return the numeroCentral
     */
    public String getNumeroCentral() {
    	return numeroCentral;
    }

	/**
     * @param numeroCentral the numeroCentral to set
     */
    public void setNumeroCentral(String numeroCentral) {
    	this.numeroCentral = numeroCentral;
    }
    
    /**
     * Recupera o valor de agrupador.
     *
     * @return o valor de agrupador
     */
    public String getAgrupador() {
    	return getNumeroCentral() + " / " + getNumeroCooperativa() + " / " + getIdUnidadeInst();
    }
}
