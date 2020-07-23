// 24/02/2013 - 12:01:02
package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;

/**
 * @author Rodrigo.Chaves
 */
public class EstrategiaAutorizacaoDevolver implements EstrategiaAutorizacao {

	/** 
	 * {@inheritDoc}
	 */
	public void executar(Autorizacao autorizacao) throws BancoobException {

		CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoDelegate()
				.marcarDevolvido(autorizacao, true);
	}

}
