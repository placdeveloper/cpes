/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.servicos.VerificarDLQReplicacaoCadastroServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESServicoReplicacaoServiceLocator;

/**
 * Delegate utilizada para verifica��o de mensagens na DLQ de replica��o de
 * cadastro.
 * 
 * @author Erico.Junior
 */
public class VerificarDLQReplicacaoCadastroDelegate extends
		CAPESServicoReplicacaoDelegate<VerificarDLQReplicacaoCadastroServico> {

	/**
	 * Processa as mensagens que est�o na DLQ de atualiza��o de cadastro.
	 * 
	 * @throws BancoobException
	 *             A exce��o que possa ocorrer.
	 */
	public void processarMensagens() throws BancoobException {
		getServico().processarMensagens();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected VerificarDLQReplicacaoCadastroServico localizarServico() {
		return CAPESServicoReplicacaoServiceLocator.getInstance()
				.localizarVerificarDLQReplicacaoCadastroServico();
	}

}
