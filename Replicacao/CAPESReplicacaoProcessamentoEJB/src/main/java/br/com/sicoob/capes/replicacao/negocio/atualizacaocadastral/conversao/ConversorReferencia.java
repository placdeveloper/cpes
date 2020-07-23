/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;


import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TipoReferencia;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Referencia;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Conversor de referência do Cadastro único em referência da replicação.
 * 
 * @author Erico.Junior
 */
public class ConversorReferencia extends
		ConversorAbstract<Referencia, br.com.sicoob.capes.negocio.entidades.vigente.Referencia> {

	/** A constante TAMANHO_DESCRICAO. */
	private static final int TAMANHO_DESCRICAO = 200;

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
	private Referencia atualizarPessoaReferenciada(br.com.sicoob.capes.negocio.entidades.vigente.Referencia entidade,
			Referencia referencia, Instituicao instituicao) throws BancoobException {

		PessoaCompartilhamento pessoaReferencia = 
				obterPessoaCompartilhamento(entidade.getPessoaReferencia());

		if (pessoaReferencia != null) {
			Pessoa referenciada = obterPessoaReplicacao(instituicao, pessoaReferencia);
			referencia.setPessoaReferenciada(referenciada);

			if (referenciada == null) {
				getLogger().debug("Pessoa referenciada não encontrada no legado.");
				String descricao = obterDescricao(pessoaReferencia, referencia.getDescricao());
				if(descricao.length() >= TAMANHO_DESCRICAO) {
					descricao = descricao.substring(0, TAMANHO_DESCRICAO);
				}				
				referencia.setDescricao(descricao);
			}
		}

		return referencia;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Referencia instanciarEntidade() {
		return new Referencia();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Referencia converter(br.com.sicoob.capes.negocio.entidades.vigente.Referencia entidade,
			Referencia referencia, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {
		
		Short ddd = entidade.getDdd();
		TipoReferencia tipo = entidade.getTipoReferencia();

		// Replicação
		Pessoa pessoa = obterPessoa(numPessoa, instituicao);
		
		referencia.setPessoa(pessoa);
		referencia.setDataCadastro(entidade.getDataHoraInicio());
		referencia.setDescricao(entidade.getObservacao());
		referencia.setTelefone(entidade.getTelefone());
		referencia.setIdReferenciaPessoaDB2(entidade.getIdReferenciaPessoa());

		if (ddd != null) {
			referencia.setDdd(String.valueOf(ddd));
		}
		if (tipo != null) {
			referencia.setCodigoTipoReferencia(tipo.getCodigo());
		}
		if (entidade.getPessoaReferencia() != null) {
			referencia = atualizarPessoaReferenciada(entidade, referencia, instituicao);
		}

		return referencia;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDelegate<Referencia, ?> obterDelegate() {
		return obterFabricaDelegates().criarReferenciaDelegate();
	}

}
