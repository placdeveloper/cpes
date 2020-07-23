package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.math.BigDecimal;

/**
 * Classes contendo as informações do proprietário do bem.
 * 
 * @author Bruno.Carneiro
 */
public class BemProprietarioDTO extends CAPESApiInclusaoDTO {
	private static final long serialVersionUID = 1465718860208110305L;

	private Boolean pessoaResponsavel = Boolean.FALSE;
	private BigDecimal percentualProprietario;

	public Boolean getPessoaResponsavel() {
		return pessoaResponsavel;
	}

	public void setPessoaResponsavel(Boolean pessoaResponsavel) {
		this.pessoaResponsavel = pessoaResponsavel;
	}
	
	public BigDecimal getPercentualProprietario() {
		return percentualProprietario;
	}

	public void setPercentualProprietario(BigDecimal percentualProprietario) {
		this.percentualProprietario = percentualProprietario;
	}

}