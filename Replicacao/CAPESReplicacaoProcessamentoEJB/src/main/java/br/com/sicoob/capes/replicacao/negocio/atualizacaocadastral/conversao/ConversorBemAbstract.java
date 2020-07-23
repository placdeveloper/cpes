/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.replicacao.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;

/**
 * Superclasse de conversor para BemRegistro, BemOnus e BemPosse. 
 * @author Erico.Junior
 */
public abstract class ConversorBemAbstract<R extends EntidadeReplicavel<?>, PK extends BancoobChavePrimaria, 
		E extends CAPESEntidade<?> & Replicavel> 
		extends ConversorAbstract<R, E> {

	/** O atributo bemDelegate. */
	private transient BemDelegate bemDelegate = CAPESReplicacaoComumFabricaDelegates
			.getInstance().criarBemDelegate();

	/**
	 * Obter bem.
	 *
	 * @param bem o valor de bem
	 * @param instituicao o valor de instituicao
	 * @return Bem
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected Bem obterBem(br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bem,
			Instituicao instituicao) throws BancoobException {

		Bem bemSQL = new Bem();
		bemSQL.setIdBemDB2(bem.getId());

		return bemDelegate.obterPorIdDB2(bemSQL, instituicao.getIdInstituicao());
	}

	/**
	 * Obter chave.
	 *
	 * @param bemPosse o valor de bem posse
	 * @param bem o valor de bem
	 * @param instituicao o valor de instituicao
	 * @return PK
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected abstract PK obterChave(R bemPosse, 
			br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bem,
			Instituicao instituicao) throws BancoobException;

}
