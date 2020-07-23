/*
 * SICOOB
 * 
 * AbstractPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.AbstractPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;


/**
 * @author Lucas.Borges
 */
public abstract class AbstractPessoaVO extends BancoobVo{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1126670732720790474L;

	/**
	 * Clonar.
	 * 
	 * @param objeto
	 *            the objeto
	 * @return novo objeto {@link Date}
	 * 
	 * @see Date#clone()
	 */
	protected Date clonar(Date objeto) {

		return (Date) (objeto != null ? objeto.clone() : null);
	}
}
