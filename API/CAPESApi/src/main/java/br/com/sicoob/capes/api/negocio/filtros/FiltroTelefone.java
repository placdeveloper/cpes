package br.com.sicoob.capes.api.negocio.filtros;

public class FiltroTelefone extends FiltroConsultaAPIAbstrato {
	private static final long serialVersionUID = 708082418564952791L;

	private String ddd;
	private String telefone;
	private String ramal;
	private Short codigoTipoTelefone;

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public Short getCodigoTipoTelefone() {
		return codigoTipoTelefone;
	}

	public void setCodigoTipoTelefone(Short codigoTipoTelefone) {
		this.codigoTipoTelefone = codigoTipoTelefone;
	}
}
