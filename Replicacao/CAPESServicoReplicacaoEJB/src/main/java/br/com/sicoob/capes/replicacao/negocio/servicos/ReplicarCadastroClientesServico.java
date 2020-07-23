package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;

/**
 * Interface que define os métodos do serviço de replicação 
 * @author Juan.Damasceno
 *
 */
public interface ReplicarCadastroClientesServico extends CAPESServicoReplicacaoServico{
	
	/**
	 * Implementa a replicação dos clientes na base de dados do SQL server para o DB2.
	 * @throws BancoobException A exceção que possa ocorrer.
	 */
	void replicaCadastroClientes() throws BancoobException;
	
	/**
	 * Implementa a centralização das alterações nos clientes.
	 * @param bancoServidor 
	 * @throws BancoobException A exceção que possa ocorrer.
	 */
	void centralizaTransicaoReplicacao(BancoServidor bancoServidor) throws BancoobException;
}