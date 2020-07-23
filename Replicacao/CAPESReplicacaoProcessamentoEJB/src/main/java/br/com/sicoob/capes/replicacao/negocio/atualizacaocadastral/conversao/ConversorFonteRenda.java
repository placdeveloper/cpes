/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;
import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;

/**
 * Converte a Fonte de renda do Cadastro único na fonte da replicação.
 * 
 * @author Erico.Junior
 */
public class ConversorFonteRenda extends
		ConversorAbstract<FonteRenda, br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda> {

	/** A constante TAMANHO_DESCRICAO. */
	private static final int TAMANHO_DESCRICAO = 190;

	/**
	 * Atualiza a pessoa referenciada.
	 * 
	 * @param dto
	 *            O dto com os dados da atualização.
	 * @param referencia
	 *            A referência da pessoa a ser preenchida.
	 * @return A referência preenchida.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private FonteRenda atualizarPessoaEmpregador(FonteRenda fonte, Instituicao instituicao,
			br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda entidade) 
			throws BancoobException {

		br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento empregadorCUC = 
				obterPessoaCompartilhamento(entidade.getPessoaEmpregador());

		if (empregadorCUC != null) {
			
			Pessoa empregador = obterPessoaReplicacao(instituicao, empregadorCUC);
			String descricao = obterDescricao(empregadorCUC, fonte.getDescricao());
			
			if(descricao.length() >= TAMANHO_DESCRICAO) {
				descricao = descricao.substring(0, TAMANHO_DESCRICAO);
			}
			
			if(empregador == null) {
				getLogger().debug("Empregador não encontrado no legado.");
				fonte.setDescricao(descricao);
			} else {
				if (empregador instanceof PessoaFisica) {
					getLogger().debug("Empregador é uma pessoa física no legado.");
					fonte.setDescricao(descricao);
				} else if (empregador instanceof PessoaJuridica){
					fonte.setPessoaJuridica((PessoaJuridica)empregador);
				}
			}
		}

		return fonte;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRenda instanciarEntidade() {
		return new FonteRenda();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRenda converter(
			br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda entidade,
			FonteRenda fonte, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {

		// Replicação
		Pessoa pessoa = obterPessoa(numPessoa, instituicao);

		fonte.setCodigo(entidade.getTipoFonteRenda().getCodigo());
		fonte.setDataCadastro(entidade.getDataHoraInicio());
		fonte.setDescricao(entidade.getDescFonteRenda()); 
		fonte.setIdFonteRendaDB2(entidade.getId());
		fonte.setPessoa(pessoa);
		fonte.setBolPossuiAtivo(entidade.getBolPossuiAtivo());
		fonte.setBolSimplesNacional(entidade.getBolSimplesNacional());
		fonte.setReceitaBrutaMensal(obterValor(entidade.getValorReceitaBrutaMensal()));
		fonte.setRendaFixa(entidade.getBolRendaFixa());
		
		if (entidade.getPessoaEmpregador() != null) {
			fonte = atualizarPessoaEmpregador(fonte, instituicao, entidade);
		}
		
		return fonte;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDelegate<FonteRenda, ?> obterDelegate() {
		return obterFabricaDelegates().criarFonteRendaDelegate();
	}	
	
}
