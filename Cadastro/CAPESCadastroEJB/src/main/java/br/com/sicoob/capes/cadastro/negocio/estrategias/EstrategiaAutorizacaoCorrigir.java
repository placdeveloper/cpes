// 24/02/2013 - 13:25:11
package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;

/**
 * TODO javadoc
 * 
 * 
 * @author Rodrigo.Chaves
 */
public class EstrategiaAutorizacaoCorrigir implements EstrategiaAutorizacao {

	/**
	 * {@inheritDoc}
	 */
	public void executar(Autorizacao autorizacao) throws BancoobException {
		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		fabrica.criarAutorizacaoDelegate().marcarDevolvido(autorizacao, false);
		// Marca��o tempor�ria para solu��o ap�s retirada do digitiza��o.
		autorizacao.setDevolvido(false);
		fabrica.criarAutorizarDelegate().incluirUsuarioEnvioAutorizacao(autorizacao);
	}

}