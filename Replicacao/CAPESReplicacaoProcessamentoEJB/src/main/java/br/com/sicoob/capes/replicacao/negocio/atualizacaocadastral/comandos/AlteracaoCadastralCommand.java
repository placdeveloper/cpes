/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;

/**
 * Comando utilizado para as alterações cadastrais.
 * @author Erico.Junior
 */
public class AlteracaoCadastralCommand<R extends EntidadeReplicavel<?>> extends
		AtualizacaoCadastralCommandAbstract<R> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doExecute(R entidade, Instituicao instituicao,
			EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?> delegate)
			throws BancoobException {
		
		if(delegate != null && entidade != null) {
			Integer idInstituicao = instituicao.getIdInstituicao();
			getLogger().debug(
					"Alterando entidade: " + entidade + " na instituição: " + idInstituicao);
			delegate.alterar(entidade, idInstituicao);	
		}
	}

}
