package br.com.sicoob.capes.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

public class ConsultaTipoEnderecoDTO extends BancoobDto {
	
	private static final long serialVersionUID = 3835651765255886934L;

	@AtributoRetorno(posicao = 1)
	private Short codTipoEndereco;

	@AtributoRetorno(posicao = 2)
	private String descTipoEndereco;

	@AtributoRetorno(posicao = 3)
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
