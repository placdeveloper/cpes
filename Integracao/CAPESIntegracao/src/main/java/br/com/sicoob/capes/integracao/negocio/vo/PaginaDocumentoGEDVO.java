package br.com.sicoob.capes.integracao.negocio.vo;

public class PaginaDocumentoGEDVO extends CAPESIntegracaoVO {
	private static final long serialVersionUID = 1679879286746318151L;

	private String descricaoExtensao;
	private String nomeArquivo;
	private byte[] valorBinario;

	public String getDescricaoExtensao() {
		return descricaoExtensao;
	}

	public void setDescricaoExtensao(String descricaoExtensao) {
		this.descricaoExtensao = descricaoExtensao;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public byte[] getValorBinario() {
		return valorBinario;
	}

	public void setValorBinario(byte[] valorBinario) {
		if (valorBinario != null) {
			this.valorBinario = valorBinario.clone();
		}
	}

}