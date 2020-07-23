/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.builder;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Superclasse para os Builders de pessoa física e jurídica.
 * 
 * @author erico.junior
 */
public abstract class PessoaBuilder
		<P extends br.com.sicoob.capes.negocio.entidades.legado.Pessoa, C extends PessoaCompartilhamento> {

	/**
	 * Cria uma pessoa do legado com as informações da pessoa no cadastro único.
	 * 
	 * @param pessoa
	 *            A pessoa do cadastro único.
	 * @return A pessoa do legado.
	 * @throws BancoobException 
	 */
	public P criarPessoaLegado(C pessoa) throws BancoobException {

		P pessoaLegado = instanciarPessoa();

		pessoaLegado.setApelido(pessoa.getNomeApelido());
		pessoaLegado.setDataCadastramentoPessoa(pessoa.getDataInclusaoSistema());
		pessoaLegado.setDataUltimaAtualizacao(pessoa.getDataHoraInicio());
		pessoaLegado.setNome(pessoa.getNomePessoa());
		pessoaLegado.setNomeCompleto(pessoa.getNomeCompleto());
		pessoaLegado.setNumeroCgcCpf(pessoa.getPessoa().getCpfCnpj()); 
		pessoaLegado.setObservacao(pessoa.getDescricao());
		pessoaLegado.setTipoPessoa(pessoa.getPessoa().getTipoPessoa().getCodTipoPessoa());

		if(pessoa.getCnae() != null) {
			pessoaLegado.setCodigoCnae(pessoa.getCnae().getCodigo());
		}
		if(pessoa.getAtividadeEconomica() != null) {
			pessoaLegado.setIdAtividadeEconomica(pessoa.getAtividadeEconomica().getCodigo());
		}

		atribuirInformacoesEspecificas(pessoaLegado, pessoa);
		return pessoaLegado;
	}

	/**
	 * Instanciar pessoa.
	 *
	 * @return P
	 */
	protected abstract P instanciarPessoa();

	/**
	 * O método Atribuir informacoes especificas.
	 *
	 * @param pessoaLegado o valor de pessoa legado
	 * @param pessoa o valor de pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected abstract void atribuirInformacoesEspecificas(P pessoaLegado, C pessoa) throws BancoobException;

}
