/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.builder.FabricaPessoaBuilder;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.builder.PessoaBuilder;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.PessoaDelegate;

/**
 * Fachada para a inclusão de pessoa no legado.
 * @author erico.junior
 */
public class InclusaoPessoaLegadoFachada<C extends PessoaCompartilhamento> {

	/** O atributo delegate. */
	private transient PessoaDelegate delegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarPessoaDelegate();
 
	/**
	 * Incluir pessoa legado.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer incluirPessoaLegado(C pessoa, Integer idInstituicao) throws BancoobException {
		Integer retorno = null;
		br.com.sicoob.capes.negocio.entidades.legado.Pessoa pessoaLegado = 
				criarPessoaLegado(pessoa);
		pessoaLegado = delegate.incluir(pessoaLegado, idInstituicao);
		retorno = pessoaLegado.getId();
		
		return retorno;
	}

	/**
	 * Criar pessoa legado.
	 *
	 * @param pessoa o valor de pessoa
	 * @return br.com.sicoob.capes.negocio.entidades.legado.Pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public br.com.sicoob.capes.negocio.entidades.legado.Pessoa criarPessoaLegado(C pessoa) throws BancoobException {

		FabricaPessoaBuilder<PessoaCompartilhamento> fabrica = FabricaPessoaBuilder.getInstance();
		PessoaBuilder<?, C> builder = (PessoaBuilder<?, C>) fabrica.obterBuilder(pessoa);

		return builder.criarPessoaLegado(pessoa);
	}
	
	/**
	 * O método Atualizar cooperativa origem.
	 *
	 * @param numCooperativa o valor de id instituicao responsavel
	 * @param transicao o valor de transicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void atualizarCooperativaOrigem(TransicaoPessoa transicao, Integer numCooperativa) 
			throws BancoobException {
		delegate.atualizarCooperativaOrigem(transicao.getIdPessoaLegado(), numCooperativa);
	}

}
