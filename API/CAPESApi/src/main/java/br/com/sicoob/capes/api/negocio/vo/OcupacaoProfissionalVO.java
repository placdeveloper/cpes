package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * VO que representa uma ocupação profissional.
 * 
 * @author Bruno.Carneiro
 * 
 */
public class OcupacaoProfissionalVO extends BancoobVo {
	private static final long serialVersionUID = -6376691619236801532L;

	private Integer idOcupacaoProfissional;
	private String codigo;
	private String codigoPai;
	private String descricao;
	private Short codigoTipoOcupacao;
	private Boolean ativo;
	
	/**
	 * Construtor
	 */
	public OcupacaoProfissionalVO(){
		super();
	}
	
	/**
	 * Construtor.
	 * 
	 * @param idOcupacaoProfissional
	 * @param codigo
	 * @param codigoPai
	 * @param descricao
	 * @param codigoTipoOcupacao
	 * @param ativo
	 */
	public OcupacaoProfissionalVO(Integer idOcupacaoProfissional, String codigo, String codigoPai, String descricao, Short codigoTipoOcupacao, Boolean ativo) {
		super();
		this.idOcupacaoProfissional = idOcupacaoProfissional;
		this.codigo = codigo;
		this.codigoPai = codigoPai;
		this.descricao = descricao;
		this.codigoTipoOcupacao = codigoTipoOcupacao;
		this.ativo = ativo;
	}

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