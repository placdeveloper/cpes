/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public class ParametroVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2587497564000754314L;
	
	private Integer codigo;
	private String descricao;
	private String valor;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	}
