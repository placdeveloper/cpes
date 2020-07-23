package br.com.sicoob.capes.relatorio.negocio.vo;

import java.math.BigDecimal;

/**
 * 
 * @author Tiago.Stangarlin
 *
 */
public class VinculoEmpregaticioVO {

	
	private Short codigo;

	private String descricao;
	
	private BigDecimal valorRendaMinimaObrigatoria;

	
	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorRendaMinimaObrigatoria() {
		return valorRendaMinimaObrigatoria;
	}

	public void setValorRendaMinimaObrigatoria(
			BigDecimal valorRendaMinimaObrigatoria) {
		this.valorRendaMinimaObrigatoria = valorRendaMinimaObrigatoria;
	}
}
