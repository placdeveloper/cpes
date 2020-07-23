package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

public class UnidadeFederativaVO extends BancoobVo{

	
	private static final long serialVersionUID = 1L;

	private Integer idUF;
	private Integer idRegiao;
	private String siglaUF;
	private String nomeUF;
	private String codIBGE;
	private String codRF;
	private String codBACEN;

	public UnidadeFederativaVO() {
	}

	public Integer getIdUF() {
		return idUF;
	}

	public void setIdUF(Integer idUF) {
		this.idUF = idUF;
	}

	public Integer getIdRegiao() {
		return idRegiao;
	}

	public void setIdRegiao(Integer idRegiao) {
		this.idRegiao = idRegiao;
	}

	public String getSiglaUF() {
		return siglaUF;
	}

	public void setSiglaUF(String siglaUF) {
		this.siglaUF = siglaUF;
	}

	public String getNomeUF() {
		return nomeUF;
	}

	public void setNomeUF(String nomeUF) {
		this.nomeUF = nomeUF;
	}

	public String getCodIBGE() {
		return codIBGE;
	}

	public void setCodIBGE(String codIBGE) {
		this.codIBGE = codIBGE;
	}

	public String getCodRF() {
		return codRF;
	}

	public void setCodRF(String codRF) {
		this.codRF = codRF;
	}

	public String getCodBACEN() {
		return codBACEN;
	}

	public void setCodBACEN(String codBACEN) {
		this.codBACEN = codBACEN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
