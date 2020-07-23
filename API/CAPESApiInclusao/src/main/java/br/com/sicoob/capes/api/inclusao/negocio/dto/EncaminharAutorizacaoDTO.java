package br.com.sicoob.capes.api.inclusao.negocio.dto;

/**
 * DTO com as informa��es para encaminhar uma autoriza��o.
 * 
 * @author bruno.carneiro
 */
public class EncaminharAutorizacaoDTO extends CAPESApiInclusaoDTO {
	private static final long serialVersionUID = 4554343639650368102L;

	private Long idRegistro;
	private Integer idInstituicaoRegistro;

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}
	
	public Integer getIdInstituicaoRegistro() {
		return idInstituicaoRegistro;
	}

	public void setIdInstituicaoRegistro(Integer idInstituicaoRegistro) {
		this.idInstituicaoRegistro = idInstituicaoRegistro;
	}

}