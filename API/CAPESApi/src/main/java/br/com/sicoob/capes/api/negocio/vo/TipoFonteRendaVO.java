package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

public class TipoFonteRendaVO extends BancoobVo {
	private static final long serialVersionUID = -4516112434131528960L;

	private Short codigo;
	private String descricao;
	private Short codigoTipoPessoa;
	private Boolean valorObrigatorio;
	
	public TipoFonteRendaVO() {
		
	}
	
	public TipoFonteRendaVO(Short codigo, String descricao, Boolean valorObrigatorio) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valorObrigatorio = valorObrigatorio;
	}

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

	public Short getCodigoTipoPessoa() {
		return codigoTipoPessoa;
	}

	public void setCodigoTipoPessoa(Short codigoTipoPessoa) {
		this.codigoTipoPessoa = codigoTipoPessoa;
	}

	public Boolean getValorObrigatorio() {
		return valorObrigatorio;
	}

	public void setValorObrigatorio(Boolean valorObrigatorio) {
		this.valorObrigatorio = valorObrigatorio;
	}

}