package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

public class CnaeFiscalVO extends BancoobVo {

	private static final long serialVersionUID = -8580692184084952097L;
	
	/** O atributo codigo. */
	private String codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo tipo classificacao. */
	private Character tipoClassificacao;
	
	/** O atributo cnae pai. */
	private String codigoCnaePai;

	public CnaeFiscalVO(){
		
	}
	
	public CnaeFiscalVO(String codigo, String descricao, Character tipoClassificacao, String codigoCnaePai) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.tipoClassificacao = tipoClassificacao;
		this.codigoCnaePai = codigoCnaePai;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character getTipoClassificacao() {
		return tipoClassificacao;
	}

	public void setTipoClassificacao(Character tipoClassificacao) {
		this.tipoClassificacao = tipoClassificacao;
	}

	public String getCodigoCnaePai() {
		return codigoCnaePai;
	}

	public void setCodigoCnaePai(String CodigoCnaePai) {
		this.codigoCnaePai = CodigoCnaePai;
	}

}
