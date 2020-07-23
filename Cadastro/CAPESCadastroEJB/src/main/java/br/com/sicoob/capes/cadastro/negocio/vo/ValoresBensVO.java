package br.com.sicoob.capes.cadastro.negocio.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;

public class ValoresBensVO extends BancoobVo {
	private static final long serialVersionUID = 984237003631335235L;

	private BigDecimal valorTotalMovelDeclarado;
	private BigDecimal valorTotalMovelAvaliado;

	private BigDecimal valorTotalImovelDeclarado;
	private BigDecimal valorTotalImovelAvaliado;

	private BigDecimal valorTotalDeclarado;
	private BigDecimal valorTotalAvaliado;
	
	public BigDecimal getValorTotalMovelDeclarado() {
		return valorTotalMovelDeclarado;
	}

	public void setValorTotalMovelDeclarado(BigDecimal valorTotalMovelDeclarado) {
		this.valorTotalMovelDeclarado = valorTotalMovelDeclarado;
	}

	public BigDecimal getValorTotalMovelAvaliado() {
		return valorTotalMovelAvaliado;
	}

	public void setValorTotalMovelAvaliado(BigDecimal valorTotalMovelAvaliado) {
		this.valorTotalMovelAvaliado = valorTotalMovelAvaliado;
	}

	public BigDecimal getValorTotalImovelDeclarado() {
		return valorTotalImovelDeclarado;
	}

	public void setValorTotalImovelDeclarado(BigDecimal valorTotalImovelDeclarado) {
		this.valorTotalImovelDeclarado = valorTotalImovelDeclarado;
	}

	public BigDecimal getValorTotalImovelAvaliado() {
		return valorTotalImovelAvaliado;
	}

	public void setValorTotalImovelAvaliado(BigDecimal valorTotalImovelAvaliado) {
		this.valorTotalImovelAvaliado = valorTotalImovelAvaliado;
	}

	public BigDecimal getValorTotalDeclarado() {
		return valorTotalDeclarado;
	}

	public void setValorTotalDeclarado(BigDecimal valorTotalDeclarado) {
		this.valorTotalDeclarado = valorTotalDeclarado;
	}

	public BigDecimal getValorTotalAvaliado() {
		return valorTotalAvaliado;
	}

	public void setValorTotalAvaliado(BigDecimal valorTotalAvaliado) {
		this.valorTotalAvaliado = valorTotalAvaliado;
	}

}