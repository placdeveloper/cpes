package br.com.sicoob.capes.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

public class ConsultaTipoEmailDTO extends BancoobDto {

	private static final long serialVersionUID = 4997418376889046536L;

	@AtributoRetorno(posicao = 1)
	private Short codTipoEmail;

	@AtributoRetorno(posicao = 2)
	private String descTipoEmail;

	public Short getCodTipoEmail() {
		return codTipoEmail;
	}

	public void setCodTipoEmail(Short codTipoEmail) {
		this.codTipoEmail = codTipoEmail;
	}

	public String getDescTipoEmail() {
		return descTipoEmail;
	}

	public void setDescTipoEmail(String descTipoEmail) {
		this.descTipoEmail = descTipoEmail;
	}

}
