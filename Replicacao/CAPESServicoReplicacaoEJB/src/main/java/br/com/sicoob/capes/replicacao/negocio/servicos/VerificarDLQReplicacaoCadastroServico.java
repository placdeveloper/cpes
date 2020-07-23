/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * Servi�o utilizado para processar as mensagens presentes na DLQ de atualiza��o do cadastro.
 * 
 * @author Erico.Junior
 */
public interface VerificarDLQReplicacaoCadastroServico extends
		CAPESServicoReplicacaoServico {

	/**
	 * Processa as mensagens que est�o na DLQ de atualiza��o de cadastro.
	 * 
	 * @throws BancoobException
	 *             A exce��o que possa ocorrer.
	 */
	void processarMensagens() throws BancoobException;

}
