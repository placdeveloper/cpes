package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;

/**
 * Interface que define os m�todos do servi�o de replica��o 
 * @author Juan.Damasceno
 *
 */
public interface ReplicarCadastroClientesServico extends CAPESServicoReplicacaoServico{
	
	/**
	 * Implementa a replica��o dos clientes na base de dados do SQL server para o DB2.
	 * @throws BancoobException A exce��o que possa ocorrer.
	 */
	void replicaCadastroClientes() throws BancoobException;
	
	/**
	 * Implementa a centraliza��o das altera��es nos clientes.
	 * @param bancoServidor 
	 * @throws BancoobException A exce��o que possa ocorrer.
	 */
	void centralizaTransicaoReplicacao(BancoServidor bancoServidor) throws BancoobException;
}