/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.ReferenciaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;

/**
 * Business delegate para referencia.  
 * @author juan.damasceno
 */
public class ReferenciaDelegate extends CAPESCadastroCrudDelegate<Referencia, ReferenciaServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarReferenciaServico();
	}
	
	/**
	 * Importa a referencia para pessoa;
	 * @param referencia
	 * @return
	 * @throws BancoobException
	 */
	public Referencia importar(Referencia referencia) throws BancoobException {
		return getServico().importar(referencia);
	}	
}
