/* 
 * Sicoob
 * CAPESCadastroCrudDominioServico.java 
 * Criado em: 06/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * Define as opera��es dos servi�os de CRUD de dom�nios
 * 
 * 06/05/2011
 * @author Rodrigo.Chaves
 * 
 */
public interface CAPESCadastroCrudDominioServico<T extends CAPESEntidade<?>> extends
		CAPESCadastroCrudServico<T> {

	/**
	 * Recupera o pr�ximo c�digo dispon�vel
	 *
	 * @return o pr�ximo c�digo
	 */
	Short pesquisarProximoCodigo() throws BancoobException;

}
