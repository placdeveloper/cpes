/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * Serviço utilizado para processar as mensagens presentes na DLQ de atualização do cadastro.
 * 
 * @author Erico.Junior
 */
public interface VerificarDLQReplicacaoCadastroServico extends
		CAPESServicoReplicacaoServico {

	/**
	 * Processa as mensagens que estão na DLQ de atualização de cadastro.
	 * 
	 * @throws BancoobException
	 *             A exceção que possa ocorrer.
	 */
	void processarMensagens() throws BancoobException;

}
