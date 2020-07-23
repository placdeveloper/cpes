/* 
 * Sicoob
 * TipoPoderRelacionamentoDelegate.java 
 * Criado em: 29/08/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoPoderRelacionamentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento;

/**
 * Delegate de {@link TipoPoderRelacionamento}
 * 
 * 29/08/2011
 * @author rodrigo.chaves
 */
public class TipoPoderRelacionamentoDelegate
		extends
		CAPESCadastroCrudDelegate<TipoPoderRelacionamento, TipoPoderRelacionamentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPoderRelacionamentoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoPoderRelacionamentoServico();
	}

}
