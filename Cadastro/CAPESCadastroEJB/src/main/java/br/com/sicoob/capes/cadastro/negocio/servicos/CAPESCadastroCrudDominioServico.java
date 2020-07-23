/* 
 * Sicoob
 * CAPESCadastroCrudDominioServico.java 
 * Criado em: 06/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * Define as operações dos serviços de CRUD de domínios
 * 
 * 06/05/2011
 * @author Rodrigo.Chaves
 * 
 */
public interface CAPESCadastroCrudDominioServico<T extends CAPESEntidade<?>> extends
		CAPESCadastroCrudServico<T> {

	/**
	 * Recupera o próximo código disponível
	 *
	 * @return o próximo código
	 */
	Short pesquisarProximoCodigo() throws BancoobException;

}
