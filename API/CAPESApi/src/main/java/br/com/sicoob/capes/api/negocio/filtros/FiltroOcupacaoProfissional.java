package br.com.sicoob.capes.api.negocio.filtros;

public class FiltroOcupacaoProfissional extends FiltroConsultaAPIAbstrato {
	private static final long serialVersionUID = -2661901009549178106L;

	private Integer idOcupacaoProfissional;
	private String codigo;
	private String codigoPai;
	private String descricao;
	private Short codigoTipoOcupacao;
	private Boolean ativo;

	public Integer getIdOcupacaoProfissional() {
		return idOcupacaoProfissional;
	}

	public void setIdOcupacaoProfissional(Integer idOcupacaoProfissional) {
		this.idOcupacaoProfissional = idOcupacaoProfissional;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoPai() {
		return codigoPai;
	}

	public void setCodigoPai(String codigoPai) {
		this.codigoPai = codigoPai;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getCodigoTipoOcupacao() {
		return codigoTipoOcupacao;
	}

	public void setCodigoTipoOcupacao(Short codigoTipoOcupacao) {
		this.codigoTipoOcupacao = codigoTipoOcupacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}