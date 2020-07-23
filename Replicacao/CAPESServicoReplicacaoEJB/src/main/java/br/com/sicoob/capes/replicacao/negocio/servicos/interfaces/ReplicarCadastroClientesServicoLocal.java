// 09/08/2013
package br.com.sicoob.capes.replicacao.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicarCadastroClientesServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.command.ReplicacaoCommand;
import br.com.sicoob.capes.replicacao.negocio.servicos.command.ReplicacaoCommandFactory;

/**
 * A Interface ReplicarCadastroClientesServicoLocal.
 */
public interface ReplicarCadastroClientesServicoLocal extends ReplicarCadastroClientesServico {

	/**
	 * A replica��o � efetuada por instancias de ReplicacaoCommand, que s�o criadas apartir da opera��o registrada na base do SQL server.
	 * 
	 * @param transicaoReplicacao
	 *            a entidade que ser� replicada.
	 * @throws BancoobException
	 *             exce��o lan�ada durante a replica��o.
	 * @see {@link ReplicacaoCommandFactory#createCommand()}
	 * @see {@link ReplicacaoCommand#execute(TransicaoReplicacao)}}
	 */
	void replicar(TransicaoReplicacao transicaoReplicacao) throws BancoobException;

	/**
	 * Atualiza a data de processamento de TransicaoReplicacao e chama o TransicaoReplicacaoDelegate para salvar as altera��es na entidade.
	 * 
	 * @param transicaoReplicacao
	 *            a instancia de TransicaoReplicacao que ser� atualizada.
	 */
	void atualizar(TransicaoReplicacao transicaoReplicacao);

	/**
	 * O m�todo Centraliza.
	 *
	 * @param bancoServidor
	 *            o valor de banco servidor
	 * @throws BancoobException
	 *             lan�a a exce��o BancoobException
	 */
	void centraliza(BancoServidor bancoServidor) throws BancoobException;

}