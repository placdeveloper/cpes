package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;
import br.com.sicoob.capes.negocio.entidades.legado.AvaliacaoFinanceira;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;

/**
 * A Classe AlteracaoAvalFinanceiraCommand.
 */
public class AlteracaoAvalFinanceiraCommand extends AtualizacaoCadastralCommandAbstract<AvaliacaoFinanceira> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doExecute(AvaliacaoFinanceira entidade, Instituicao instituicao, EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?> delegate)
			throws BancoobException {
		AvaliacaoFinanceira avaliacao = (AvaliacaoFinanceira) delegate.obter(entidade.getIdSQL(), instituicao.getIdInstituicao());
		
		if(avaliacao == null){
			delegate.incluir(entidade, instituicao.getIdInstituicao());
		}else{
			delegate.alterar(entidade, instituicao.getIdInstituicao());
		}
	}
}