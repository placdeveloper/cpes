package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;
import java.util.List;

public class CampoTelaListaVO implements Serializable {
	private static final long serialVersionUID = -6374539912093249545L;

	private String nomeAtributo;
	private int contadorSeparador;
	private List<CampoTelaVO> campos;

	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

	public int getContadorSeparador() {
		return contadorSeparador;
	}

	public void setContadorSeparador(int contadorSeparador) {
		this.contadorSeparador = contadorSeparador;
	}
	
	public List<CampoTelaVO> getCampos() {
		return campos;
	}

	public void setCampos(List<CampoTelaVO> campos) {
		this.campos = campos;
	}

}
