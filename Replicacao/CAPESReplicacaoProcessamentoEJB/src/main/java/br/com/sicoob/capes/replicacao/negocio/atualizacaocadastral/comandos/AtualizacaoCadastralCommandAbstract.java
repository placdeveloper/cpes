/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.FabricaAtualizacaoDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.negocio.entidades.legado.interfaces.Replicavel;

/**
 * Classe abstrata para as atualiza��es cadastrais.
 * 
 * @author Erico.Junior
 */
public abstract class AtualizacaoCadastralCommandAbstract<R extends Replicavel> implements
		AtualizacaoCadastralCommand<R> {

	/**
	 * Construtor do commndo abstrato.
	 * 
	 * @param delegate
	 */
	public AtualizacaoCadastralCommandAbstract() {
	}

	/**
	 * Recupera o logger para essa inst�ncia.
	 * 
	 * @return O logger para essa inst�ncia.
	 */
	protected ISicoobLogger getLogger() {
		return SicoobLoggerPadrao.getInstance(getClass());
	}

	/**
	 * {@inheritDoc}
	 */
	public final void executar(R entidade, Instituicao instituicao)
			throws BancoobException {

		EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?> delegate = getDelegate(entidade);
		doExecute(entidade, instituicao, delegate);
	}

	/**
	 * Executa a a��o do comando.
	 * 
	 * @param entidade
	 *            A entidade que est� sendo atualizada.
	 * @param instituicao
	 *            A institui��o onde ser� feita a atualiza��o.
	 * @param delegate
	 *            O delegate para a atualiza��o.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public abstract void doExecute(R entidade, Instituicao instituicao,
			EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?> delegate) throws BancoobException;

	/**
	 * @return the delegate
	 */
	@SuppressWarnings("unchecked")
	private EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?> getDelegate(R entidade) {

		EntidadeReplicavelDelegate<EntidadeReplicavel<?>, ?> delegate = null;

		if (entidade != null) {
			FabricaAtualizacaoDelegates fabrica = FabricaAtualizacaoDelegates.getInstance();
			delegate = fabrica.obterDelegate(entidade.getClass());
			if(delegate != null){
				getLogger().debug("Delegate encontrada: " + delegate.getClass());
			}
		}
		
		return delegate;
	}
}