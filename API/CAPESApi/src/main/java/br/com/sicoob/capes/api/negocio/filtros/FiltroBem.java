package br.com.sicoob.capes.api.negocio.filtros;

public class FiltroBem extends FiltroConsultaAPIAbstrato {
	private static final long serialVersionUID = 7600705893760893587L;

	private Short codigoTipoBem;
	private Long idBem;
	private String descricao;

	public Short getCodigoTipoBem() {
		return codigoTipoBem;
	}

	public void setCodigoTipoBem(Short codigoTipoBem) {
		this.codigoTipoBem = codigoTipoBem;
	}

	public Long getIdBem() {
		return idBem;
	}

	public void setIdBem(Long idBem) {
		this.idBem = idBem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}