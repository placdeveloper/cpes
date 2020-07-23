/* 
 * Sicoob
 * CAPESCadastroCrudDominioDelegate.java 
 * Criado em: 06/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroCrudDominioServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroCrudServico;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * Business delegate base para as funcionalidades de domínio
 * 
 * 06/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public abstract class CAPESCadastroCrudDominioDelegate<T extends CAPESEntidade<?>, S extends CAPESCadastroCrudServico<T>>
		extends CAPESCadastroCrudDelegate<T, S> {

	/**
	 * Pesquisar proximo codigo.
	 *
	 * @return Short
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Short pesquisarProximoCodigo() throws BancoobException {
		CAPESCadastroCrudDominioServico<T> servico = 
			(CAPESCadastroCrudDominioServico<T>) localizarServico();
		return servico.pesquisarProximoCodigo();
	}

}
