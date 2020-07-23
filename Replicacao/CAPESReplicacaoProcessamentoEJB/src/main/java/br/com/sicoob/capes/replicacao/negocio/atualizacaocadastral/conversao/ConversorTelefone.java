/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Telefone;

/**
* Converte o telefone do cadastro único no telefone da replicação. 
* @author Erico.Junior
*/
public class ConversorTelefone extends
		ConversorAbstract<Telefone, br.com.sicoob.capes.negocio.entidades.vigente.Telefone> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Telefone instanciarEntidade() {
		return new Telefone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Telefone converter(br.com.sicoob.capes.negocio.entidades.vigente.Telefone entidade,
			Telefone telefone, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {

		// Replicação
		Pessoa pessoa = obterPessoa(numPessoa, instituicao);
		
		telefone.setCodTipoTelefone(entidade.getTipoTelefone().getCodigo());
		telefone.setDdd(entidade.getDdd());
		telefone.setIdTelefonePessoaDB2(entidade.getId());
		telefone.setNumero(entidade.getTelefone());
		telefone.setPessoa(pessoa);
		telefone.setRamal(entidade.getRamal());
		
		return telefone;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDelegate<Telefone, ?> obterDelegate() {
		return obterFabricaDelegates().criarTelefoneDelegate();
	}

}
