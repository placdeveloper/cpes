/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.pessoa;

import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Fábrica para PessoaCompartilhamento.
 * @author erico.junior
 */
public abstract class FabricaPessoaCompartilhamento {

	/**
	 * Obter fabrica pessoa compartilhamento.
	 *
	 * @param tipo o valor de tipo
	 * @return FabricaPessoaCompartilhamento
	 */
	public static FabricaPessoaCompartilhamento obterFabricaPessoaCompartilhamento(
			TipoPessoa tipo) {
		return obterFabricaPessoaCompartilhamento(tipo.getCodTipoPessoa());
	}
	
	/**
	 * Obter fabrica pessoa compartilhamento.
	 *
	 * @param codTipoPessoa o valor de cod tipo pessoa
	 * @return FabricaPessoaCompartilhamento
	 */
	public static FabricaPessoaCompartilhamento obterFabricaPessoaCompartilhamento(
			Short codTipoPessoa) {

		FabricaPessoaCompartilhamento fabrica = null;
		
		if (TipoPessoaEnum.PESSOA_FISICA.getCodigo().equals(codTipoPessoa)) {
			fabrica = new FabricaPessoaFisica();
		} else {
			fabrica = new FabricaPessoaJuridica();
		}
		
		return fabrica;
	}	

	/**
	 * Criar pessoa compartilhamento.
	 *
	 * @return PessoaCompartilhamento
	 */
	public abstract PessoaCompartilhamento criarPessoaCompartilhamento();
}
