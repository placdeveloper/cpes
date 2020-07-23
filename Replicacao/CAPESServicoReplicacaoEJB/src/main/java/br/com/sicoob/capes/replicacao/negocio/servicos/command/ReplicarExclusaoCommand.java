package br.com.sicoob.capes.replicacao.negocio.servicos.command;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;

/**
 * 
 * Classe responsável por replicar a exclusão dos clientes da base do SQL Server para o DB2. 
 * 
 * @author Juan.Damasceno
 *
 */
public class ReplicarExclusaoCommand extends AbstractReplicacaoCommand {

	/**
	 * {@inheritDoc}
	 */
	public void doExecute(TransicaoReplicacao transicaoReplicacao) throws BancoobException {

		TransicaoPessoaDelegate transicaoPessoaDelegate = getTransicaoPessoaDelegate();		

		List<TransicaoPessoa> transicoes = listaTransicoesPessoa(transicaoReplicacao);

		if (!transicoes.isEmpty()) {

			TransicaoPessoa transicaoPessoa = transicoes.get(0);
			
			transicaoPessoaDelegate.excluir(transicaoPessoa.getIdTransicaoPessoa());
		}
	}
}