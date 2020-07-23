/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;

/**
 * Define as operações do serviço de manipulação de Referencia.
 * 
 * @author Juan.Damasceno
 */
public interface ReferenciaServico extends CAPESCadastroCrudServico<Referencia> {

	/**
	 * Importa a referencia para pessoa;
	 * @param referencia
	 * @return
	 * @throws BancoobException
	 */
	Referencia importar(Referencia referencia) throws BancoobException;
}
