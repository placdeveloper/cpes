/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.builder;

import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * Fábrica para os builders de pessoas do legado.
 * 
 * @author Erico.Junior
 */
public final class FabricaPessoaBuilder<C extends PessoaCompartilhamento> {

	/** Instancia do Fabrica. */
	private static FabricaPessoaBuilder<PessoaCompartilhamento> fabrica;

	/**
	 * Construtor privado para garantir a instância única.
	 */
	private FabricaPessoaBuilder() {
	}

	/**
	 * Retorna a fabrica a ser utilizada.
	 * 
	 * @return a fabrica a ser utilizada.
	 */
	public static FabricaPessoaBuilder<PessoaCompartilhamento> getInstance() {
		if (fabrica == null) {
			synchronized (FabricaPessoaBuilder.class) {
				if (fabrica == null) {
					fabrica = new FabricaPessoaBuilder<PessoaCompartilhamento>();
				}
			}
		}
		return fabrica;
	}

	/**
	 * Recupera o Builder para as pessoas no legado.
	 * 
	 * @param pessoa
	 *            A pessoa no cadastro único.
	 * @return o Builder para as pessoas no legado.
	 */
	public PessoaBuilder<?, ?> obterBuilder(C pessoa) {

		PessoaBuilder<?, ?> builder = null;

		if(pessoa instanceof PessoaFisica){
			builder = new PessoaFisicaBuilder();
		} else if(pessoa instanceof PessoaJuridica){
			builder = new PessoaJuridicaBuilder();
		}
		
		return builder;
	}
}
