package br.com.sicoob.capes.replicacao.negocio.servicos.command;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaNaoEncontradaException;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * 
 * Classe responsável por replicar a alteração/inclusão dos clientes que estão cadastrados na base 
 * do SQL Server para o DB2. 
 * 
 * @author Juan.Damasceno
 *
 */
public class ReplicarAlteracaoCommand extends AbstractReplicacaoCommand {
	
	/**
	 * {@inheritDoc}
	 */
	public void doExecute(TransicaoReplicacao transicaoReplicacao) throws BancoobException {

		List<TransicaoPessoa> transicoesPessoas = listaTransicoesPessoa(transicaoReplicacao);

		if (!transicoesPessoas.isEmpty()) {
			TransicaoPessoa transicaoPessoa = transicoesPessoas.get(0);
			atualizaTransicaoPessoa(transicaoReplicacao, transicaoPessoa);
		} else {
			incluiTransicaoPessoa(transicaoReplicacao);
		}
	}

	/**
	 * Inclui uma instância TransicaoPessoa com os dados de TransicaoReplicacao, caso a pessoa não 
 	 * exista ela também será incluída.
	 * 
	 * @param transicaoReplicacao instância de TransicaoReplicacao contendo os dados alterados
	 *  na base do SQL server.
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	private void incluiTransicaoPessoa(TransicaoReplicacao transicaoReplicacao)
			throws BancoobException {

		Pessoa pessoa = null;

		try {
			pessoa = consultarPessoaPorDocumentoSistemaCooperativo(transicaoReplicacao);
		} catch (PessoaNaoEncontradaException e) {
			pessoa = criarPessoa(transicaoReplicacao);
			getPessoaDelegate().incluir(pessoa);
		}

		CompartilhamentoCadastro compartilhamentoCadastro = criarCompartilhamentoCadastro(transicaoReplicacao);
		PessoaCompartilhamento pessoaCompartilhamento = getPessoaCompartilhamentoDelegate().consultar(pessoa, compartilhamentoCadastro);

		if (pessoaCompartilhamento == null) {
			pessoaCompartilhamento = criarPessoaCompartilhamento(transicaoReplicacao, pessoa);
			pessoaCompartilhamento.setVerificarAutorizacao(false);
			getPessoaCompartilhamentoDelegate().incluirSemReplicacao(pessoaCompartilhamento);

			ResponsavelCadastro responsavelCadastro = criarResponsavelCadastro(transicaoReplicacao, pessoaCompartilhamento);
			getResponsavelCadastroDelegate().incluir(responsavelCadastro);
		} else {
			if (isNomesDiferentes(transicaoReplicacao, pessoaCompartilhamento)) {
				pessoaCompartilhamento.setNomePessoa(transicaoReplicacao.getNome());
				pessoaCompartilhamento.setNomeCompleto(transicaoReplicacao.getNome());
				pessoaCompartilhamento.setVerificarAutorizacao(false);
				getPessoaCompartilhamentoDelegate().alterarSemReplicacao(pessoaCompartilhamento);
			}				
		}

		getTransicaoPessoaDelegate().incluir(criarTransicaoPessoa(transicaoReplicacao, pessoaCompartilhamento));
	}

	/**
	 * Criar pessoa.
	 *
	 * @param transicaoReplicacao o valor de transicao replicacao
	 * @return Pessoa
	 */
	private Pessoa criarPessoa(TransicaoReplicacao transicaoReplicacao) {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setTipoPessoa(criarTipoPessoa(transicaoReplicacao));
		pessoa.setCpfCnpj(transicaoReplicacao.getCpfCnpj());

		return pessoa;
	}

	/**
	 * Criar compartilhamento cadastro.
	 *
	 * @param transicaoReplicacao o valor de transicao replicacao
	 * @return CompartilhamentoCadastro
	 */
	private CompartilhamentoCadastro criarCompartilhamentoCadastro(
			TransicaoReplicacao transicaoReplicacao) {
		CompartilhamentoCadastro compartilhamentoCadastro = new CompartilhamentoCadastro();
		compartilhamentoCadastro.setCodigo(transicaoReplicacao.getCodCompartilhamentoCadastro());
		return compartilhamentoCadastro;
	}
	
	/**
	 * Cria uma instancia de ResponsavelCadastro.
	 * @param transicaoReplicacao instancia de TransicaoReplicacao que será utilizada para
	 * @param pessoa
	 * @return uma instancia de ResponsavelCadastro.
	 */
	private ResponsavelCadastro criarResponsavelCadastro(
			TransicaoReplicacao transicaoReplicacao, PessoaCompartilhamento pessoa) {
		
		ResponsavelCadastro responsavelCadastro = new ResponsavelCadastro();
		responsavelCadastro.setPessoa(pessoa);
		responsavelCadastro.setIdInstituicao(transicaoReplicacao.getIdInstituicao());
		responsavelCadastro.setId(pessoa.getId());
		responsavelCadastro.setGravarHistorico(false);
		
		return responsavelCadastro;
	}

	/**
	 * Consulta a pessoa pelo documento informado.
	 * @param transicaoReplicacao a instância de TransicaoReplicacao usada para extrair o documento.
	 * @param cpfCnpj o documento informado.
	 * @return uma instância de PessoaFisica ou PessoaJuridica. 
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	private Pessoa consultarPessoaPorDocumentoSistemaCooperativo(TransicaoReplicacao transicaoReplicacao) 
		throws BancoobException {
		
		return getPessoaDelegate().consultarPessoaPorDocumento(transicaoReplicacao.getCpfCnpj());
	}

	/**
	 * Atualiza os dados da TransicaoPessoa com os dados de TransicaoReplicacao.
	 * 
	 * @param transicaoReplicacao instância de TransicaoReplicacao contendo os dados alterados
	 *  na base do SQL server.
	 * @param transicaoPessoa instância de TransicaoPessoa que será atualizado.
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	private void atualizaTransicaoPessoa(
			TransicaoReplicacao transicaoReplicacao,
			TransicaoPessoa transicaoPessoa) throws BancoobException {

		Instituicao instituicao = transicaoPessoa.getInstituicao();
		instituicao.setIdUnidadeInst(transicaoReplicacao.getIdUnidade());
		transicaoPessoa.setDataHoraIntegracao(DataUtil.obterDataAtual());

		PessoaCompartilhamento pessoa = transicaoPessoa.getPessoaCompartilhamento();

		if (isNomesDiferentes(transicaoReplicacao, pessoa)) {
			String nomePessoa = transicaoReplicacao.getNome();
			
			transicaoPessoa.setNomePessoaLegado(nomePessoa);
			pessoa.setNomePessoa(nomePessoa);
			pessoa.setNomeCompleto(nomePessoa);
			pessoa.setVerificarAutorizacao(false);
			getPessoaCompartilhamentoDelegate().alterarSemReplicacao(pessoa);
			getLogger().info("O nome da pessoa alterado para ", nomePessoa);
		}

		getTransicaoPessoaDelegate().alterar(transicaoPessoa);
	}

	/**
	 * Verifica se os nomes são diferentes.
	 * 
	 * @param transicaoReplicacao a instância de TransicaoReplicacao que será será extraído o nome.
	 * @param pessoa instancia de Pessoa que será será extraído o nome.
	 * @return <code>true</code> caso os nomes sejam diferentes, <code>false</code> caso sejam iguais.
	 */
	private boolean isNomesDiferentes(TransicaoReplicacao transicaoReplicacao,
			PessoaCompartilhamento pessoa) {
		return !transicaoReplicacao.getNome().trim().equals(pessoa.getNomePessoa().trim());
	}
}