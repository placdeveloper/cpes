package br.com.sicoob.capes.relatorio.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe RelatorioValidacaoCadastralVO.
 */
public class RelatorioValidacaoCadastralVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2953734871137295506L;

	/** O atributo codigoRegra. */
	private String codigoRegra;
	
	/** O atributo funcionalidade. */
	private String funcionalidade;
	
	/** O atributo erro. */
	private String erro;
	
	/** O atributo tipoRegra. */
	private String tipoRegra;

	/**
	 * Recupera o valor de codigoRegra.
	 *
	 * @return o valor de codigoRegra
	 */
	public String getCodigoRegra() {
		return codigoRegra;
	}

	/**
	 * Define o valor de codigoRegra.
	 *
	 * @param codigoRegra o novo valor de codigoRegra
	 */
	public void setCodigoRegra(String codigoRegra) {
		this.codigoRegra = codigoRegra;
	}

	/**
	 * Recupera o valor de funcionalidade.
	 *
	 * @return o valor de funcionalidade
	 */
	public String getFuncionalidade() {
		return funcionalidade;
	}

	/**
	 * Define o valor de funcionalidade.
	 *
	 * @param funcionalidade o novo valor de funcionalidade
	 */
	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	/**
	 * Recupera o valor de erro.
	 *
	 * @return o valor de erro
	 */
	public String getErro() {
		return erro;
	}

	/**
	 * Define o valor de erro.
	 *
	 * @param erro o novo valor de erro
	 */
	public void setErro(String erro) {
		this.erro = erro;
	}

	/**
	 * Recupera o valor de tipoRegra.
	 *
	 * @return o valor de tipoRegra
	 */
	public String getTipoRegra() {
		return tipoRegra;
	}

	/**
	 * Define o valor de tipoRegra.
	 *
	 * @param tipoRegra o novo valor de tipoRegra
	 */
	public void setTipoRegra(String tipoRegra) {
		this.tipoRegra = tipoRegra;
	}

}