package br.com.sicoob.capes.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

public class ConsultaTipoFonteRendaDTO extends BancoobDto {

	private static final long serialVersionUID = 8374859602788671722L;

	@AtributoRetorno(posicao = 1)
	private Short codTipoFonteRenda;

	@AtributoRetorno(posicao = 2)
	private String descTipoFonteRenda;

	@AtributoRetorno(posicao = 3)
	private Short codTipoPessoa;

	@AtributoRetorno(posicao = 4)
	private Boolean valorObrigatorio;

	public Short getCodTipoFonteRenda() {
		return codTipoFonteRenda;
	}

	public void setCodTipoFonteRenda(Short codTipoFonteRenda) {
		this.codTipoFonteRenda = codTipoFonteRenda;
	}

	public String getDescTipoFonteRenda() {
		return descTipoFonteRenda;
	}

	public void setDescTipoFonteRenda(String descTipoFonteRenda) {
		this.descTipoFonteRenda = descTipoFonteRenda;
	}

	public Short getCodTipoPessoa() {
		return codTipoPessoa;
	}

	public void setCodTipoPessoa(Short codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}

	public Boolean getValorObrigatorio() {
		return valorObrigatorio;
	}

	public void setValorObrigatorio(Boolean valorObrigatorio) {
		this.valorObrigatorio = valorObrigatorio;
	}

}
