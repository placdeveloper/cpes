package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;
import java.util.List;

public class CamposTelaVO implements Serializable {
	private static final long serialVersionUID = 6582833646099338815L;

	private List<CampoTelaVO> campos;

	private List<CampoTelaListaVO> listas;

	public List<CampoTelaVO> getCampos() {
		return campos;
	}

	public void setCampos(List<CampoTelaVO> campos) {
		this.campos = campos;
	}

	public List<CampoTelaListaVO> getListas() {
		return listas;
	}

	public void setListas(List<CampoTelaListaVO> listas) {
		this.listas = listas;
	}

}