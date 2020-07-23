package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;

public class TipoEmpresaVO extends BancoobVo {
	
	private static final long serialVersionUID = 3250300317537071422L;

	/** O atributo codigo tipo empresa. */
	private Short codTipoEmpresa;

	/** O atributo descricao. */
	private String descricao;

	/** O atributo possuiAtivoSuperior. */
	private Boolean possuiAtivoSuperior;

	/** O atributo isSimplesNacional. */
	private Boolean isSimplesNacional;

	/** O atributo valorLimiteInferior. */
	private BigDecimal valorLimiteInferior;

	/** O atributo valorLimiteSuperior. */
	private BigDecimal valorLimiteSuperior;

	public Short getCodTipoEmpresa() {
		return codTipoEmpresa;
	}

	public void setCodTipoEmpresa(Short codTipoEmpresa) {
		this.codTipoEmpresa = codTipoEmpresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getPossuiAtivoSuperior() {
		return possuiAtivoSuperior;
	}

	public void setPossuiAtivoSuperior(Boolean possuiAtivoSuperior) {
		this.possuiAtivoSuperior = possuiAtivoSuperior;
	}

	public Boolean getIsSimplesNacional() {
		return isSimplesNacional;
	}

	public void setIsSimplesNacional(Boolean isSimplesNacional) {
		this.isSimplesNacional = isSimplesNacional;
	}

	public BigDecimal getValorLimiteInferior() {
		return valorLimiteInferior;
	}

	public void setValorLimiteInferior(BigDecimal valorLimiteInferior) {
		this.valorLimiteInferior = valorLimiteInferior;
	}

	public BigDecimal getValorLimiteSuperior() {
		return valorLimiteSuperior;
	}

	public void setValorLimiteSuperior(BigDecimal valorLimiteSuperior) {
		this.valorLimiteSuperior = valorLimiteSuperior;
	}

}
