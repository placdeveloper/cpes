package br.com.sicoob.capes.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

public class ConsultaEmailPessoaDTO extends BancoobDto {

	private static final long serialVersionUID = 4858380849265156054L;

	/** O atributo id email. */
	@AtributoRetorno(posicao = 1)
	private Long idEmail;

	/** O atributo descricao email. */
	@AtributoRetorno(posicao = 2)
	private String descricaoEmail;
	
	/** O atributo codigo tipo email. */
	@AtributoRetorno(posicao = 3)
	private Short codigoTipoEmail;

	public Long getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	public String getDescricaoEmail() {
		return descricaoEmail;
	}

	public void setDescricaoEmail(String descricaoEmail) {
		this.descricaoEmail = descricaoEmail;
	}

	public Short getCodigoTipoEmail() {
		return codigoTipoEmail;
	}

	public void setCodigoTipoEmail(Short codigoTipoEmail) {
		this.codigoTipoEmail = codigoTipoEmail;
	}

}
