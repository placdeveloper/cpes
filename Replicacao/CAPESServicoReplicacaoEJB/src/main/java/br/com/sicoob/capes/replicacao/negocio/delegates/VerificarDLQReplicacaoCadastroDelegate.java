/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.servicos.VerificarDLQReplicacaoCadastroServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESServicoReplicacaoServiceLocator;

/**
 * Delegate utilizada para verificação de mensagens na DLQ de replicação de
 * cadastro.
 * 
 * @author Erico.Junior
 */
public class VerificarDLQReplicacaoCadastroDelegate extends
		CAPESServicoReplicacaoDelegate<VerificarDLQReplicacaoCadastroServico> {

	/**
	 * Processa as mensagens que estão na DLQ de atualização de cadastro.
	 * 
	 * @throws BancoobException
	 *             A exceção que possa ocorrer.
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
