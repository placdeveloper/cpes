/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.FabricaAtualizacaoDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;

/**
 * Comando utilizado para as alterações cadastrais.
 * @author juan.damasceno
 */
public class AtualizacaoClienteCommand<R extends EntidadeReplicavel<?>>  implements AtualizacaoCadastralCommand<R>{

	/**
	 * {@inheritDoc}
	 */
	public void executar(R replicavel, Instituicao instituicao) throws BancoobException { 
		
		EntidadeReplicavelDelegate<R,?> delegate = getDelegate(replicavel);
		Integer idInstituicao = instituicao.getIdInstituicao();
		
		R entidade = delegate.obter(replicavel.getIdSQL(), idInstituicao);
		
		if (entidade != null) {
			delegate.alterar(replicavel, idInstituicao);
		}
	}

	/**
	 * Recupera o valor de delegate.
	 *
	 * @param entidade o valor de entidade
	 * @return o valor de delegate
	 */
	@SuppressWarnings("unchecked")
	private EntidadeReplicavelDelegate<R, ?> getDelegate(R entidade) {

		EntidadeReplicavelDelegate<R, ?> delegate = null;

		if (entidade != null) {
			FabricaAtualizacaoDelegates fabrica = FabricaAtualizacaoDelegates.getInstance();
			delegate = fabrica.obterDelegate(entidade.getClass());
		}

		return delegate;
	}
}