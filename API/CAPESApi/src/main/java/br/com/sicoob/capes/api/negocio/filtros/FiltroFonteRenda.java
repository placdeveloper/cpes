package br.com.sicoob.capes.api.negocio.filtros;

import java.math.BigDecimal;
import java.util.Date;

public class FiltroFonteRenda extends FiltroConsultaAPIAbstrato {
	private static final long serialVersionUID = 7320188292420593786L;

	/** O atributo renda fixa. */
	private Boolean rendaFixa;

	/** O atributo data validade renda. */
	private Date dataValidadeRenda;

	/** O atributo valor receita bruta mensal. */
	private BigDecimal valorReceitaBrutaMensal;

	/** O atributo descricao. */
	private String descricao;

	/** O atributo codigo tipo fonte renda. */
	private Short codigoTipoFonteRenda;

	/** O atributo id pessoa empregador. */
	private Integer idPessoaEmpregador;

	public Boolean getRendaFixa() {
		return rendaFixa;
	}

	public void setRendaFixa(Boolean rendaFixa) {
		this.rendaFixa = rendaFixa;
	}

	public Date getDataValidadeRenda() {
		return dataValidadeRenda;
	}

	public void setDataValidadeRenda(Date dataValidadeRenda) {
		this.dataValidadeRenda = dataValidadeRenda;
	}

	public BigDecimal getValorReceitaBrutaMensal() {
		return valorReceitaBrutaMensal;
	}

	public void setValorReceitaBrutaMensal(BigDecimal valorReceitaBrutaMensal) {
		this.valorReceitaBrutaMensal = valorReceitaBrutaMensal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getCodigoTipoFonteRenda() {
		return codigoTipoFonteRenda;
	}

	public void setCodigoTipoFonteRenda(Short codigoTipoFonteRenda) {
		this.codigoTipoFonteRenda = codigoTipoFonteRenda;
	}

	public Integer getIdPessoaEmpregador() {
		return idPessoaEmpregador;
	}

	public void setIdPessoaEmpregador(Integer idPessoaEmpregador) {
		this.idPessoaEmpregador = idPessoaEmpregador;
	}

}