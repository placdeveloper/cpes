package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;

/**
 * A Classe AtualizacaoEmailCommand.
 */
public class AtualizacaoEmailCommand extends AtualizacaoCadastralCommandAbstract<Pessoa> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doExecute(Pessoa pessoa, Instituicao instituicao,
			EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?> delegate) throws BancoobException {
		
		Integer idInstituicao = instituicao.getIdInstituicao();
		getLogger().info("Definindo novo e-mail na instituição: ", idInstituicao.toString());
		
		// através da delegate recebida como parâmetro não é possível acessar um método específico 
		PessoaDelegate pessoaDelegate = CAPESReplicacaoComumFabricaDelegates.getInstance()
				.criarPessoaDelegate();
		pessoaDelegate.alterarEmail(pessoa, idInstituicao);
	}

}
