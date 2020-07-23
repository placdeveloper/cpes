package br.com.sicoob.capes.frontoffice.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

public class ConsultarTipoEmpresaDTO extends BancoobDto {

	private static final long serialVersionUID = -8665912203140182293L;

	@AtributoRetorno(posicao = 1)
	private Short codTipoEmpresa;

	/** O atributo descricao. */
	@AtributoRetorno(posicao = 2)
	private String descricao;

	/** O atributo possuiAtivoSuperior. */
	@AtributoRetorno(posicao = 3)
	private Boolean possuiAtivoSuperior;

	/** O atributo isSimplesNacional. */
	@AtributoRetorno(posicao = 4)
	private Boolean isSimplesNacional;

	/** O atributo valorLimiteInferior. */
	@AtributoRetorno(posicao = 5)
	private BigDecimal valorLimiteInferior;

	/** O atributo valorLimiteSuperior. */
	@AtributoRetorno(posicao = 6)
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
