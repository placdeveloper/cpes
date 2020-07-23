/*
 * SICOOB
 * 
 * DominioServico.java(br.com.sicoob.capes.api.negocio.servicos.DominioServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.DominioVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoDominioEnum;


/**
 * @author Marcelo.Onofre
 *
 */
public interface DominioServico extends CAPESApiServico {

	/**
	 * Obter por tipo dominio.
	 * 
	 * @param tipoDominio
	 *            the tipo dominio
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<DominioVO> obterByTipoDominio(TipoDominioEnum tipoDominio)throws BancoobException;;
}
