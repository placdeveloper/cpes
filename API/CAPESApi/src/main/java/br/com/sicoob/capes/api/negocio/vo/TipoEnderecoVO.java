package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

public class TipoEnderecoVO extends BancoobVo {

	private static final long serialVersionUID = -6825786478868246353L;

	private Short codTipoEndereco;

	private String descTipoEndereco;

	private Short idTipoPessoaContato;

	public Short getCodTipoEndereco() {
		return codTipoEndereco;
	}

	public void setCodTipoEndereco(Short codTipoEndereco) {
		this.codTipoEndereco = codTipoEndereco;
	}

	public String getDescTipoEndereco() {
		return descTipoEndereco;
	}

	public void setDescTipoEndereco(String descTipoEndereco) {
		this.descTipoEndereco = descTipoEndereco;
	}

	public Short getIdTipoPessoaContato() {
		return idTipoPessoaContato;
	}

	public void setIdTipoPessoaContato(Short idTipoPessoaContato) {
		this.idTipoPessoaContato = idTipoPessoaContato;
	}

}
