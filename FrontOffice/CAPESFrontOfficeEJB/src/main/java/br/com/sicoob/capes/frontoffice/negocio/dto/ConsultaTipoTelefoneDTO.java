package br.com.sicoob.capes.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

public class ConsultaTipoTelefoneDTO extends BancoobDto {

	private static final long serialVersionUID = 4997418376889046536L;

	@AtributoRetorno(posicao = 1)
	private Short codTipoTelefone;

	@AtributoRetorno(posicao = 2)
	private String descTipoTelefone;

	public Short getCodTipoTelefone() {
		return codTipoTelefone;
	}

	public void setCodTipoTelefone(Short codTipoTelefone) {
		this.codTipoTelefone = codTipoTelefone;
	}

	public String getDescTipoTelefone() {
		return descTipoTelefone;
	}

	public void setDescTipoTelefone(String descTipoTelefone) {
		this.descTipoTelefone = descTipoTelefone;
	}

}
