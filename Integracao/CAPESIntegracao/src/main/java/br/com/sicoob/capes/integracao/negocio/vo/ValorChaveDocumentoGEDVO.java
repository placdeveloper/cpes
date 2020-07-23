package br.com.sicoob.capes.integracao.negocio.vo;

public class ValorChaveDocumentoGEDVO extends CAPESIntegracaoVO {
	private static final long serialVersionUID = -6071395649575861989L;

	private String siglaChaveDocumento;
	private String valorChave;
	
	public ValorChaveDocumentoGEDVO(){
		
	}

	public ValorChaveDocumentoGEDVO(String siglaChaveDocumento, String valorChave) {
		this.siglaChaveDocumento = siglaChaveDocumento;
		this.valorChave = valorChave;
	}
	
	public String getValorChave() {
		return valorChave;
	}

	public void setValorChave(String valorChave) {
		this.valorChave = valorChave;
	}

	public String getSiglaChaveDocumento() {
		return siglaChaveDocumento;
	}

	public void setSiglaChaveDocumento(String siglaChaveDocumento) {
		this.siglaChaveDocumento = siglaChaveDocumento;
	}

}