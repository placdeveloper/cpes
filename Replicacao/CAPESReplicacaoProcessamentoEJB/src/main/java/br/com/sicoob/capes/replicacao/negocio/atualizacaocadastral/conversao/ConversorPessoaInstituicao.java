/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;


import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.negocio.entidades.legado.Funcionario;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.ClienteDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.FuncionarioDelegate;

/**
 * Conversor de Tributação do Cadastro único em cliente da replicação.
 * 
 * @author juan.damasceno
 */
public class ConversorPessoaInstituicao extends
		Conversor<Cliente, PessoaInstituicao> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente obterEntidadeReplicacao(AtualizacaoCadastralDTO
			<PessoaInstituicao> dto)
			throws BancoobException {

		PessoaInstituicao pessoaInstituicao = dto.getEntidadeCadastro();

		// Replicação
		Pessoa pessoa = obterPessoa(dto);
		Cliente cliente = null;

		CAPESReplicacaoComumFabricaDelegates fabrica = CAPESReplicacaoComumFabricaDelegates.getInstance();
		ClienteDelegate clienteDelegate = fabrica.criarClienteDelegate(); 
		Integer idInstituicao = dto.getInstituicao().getIdInstituicao();
		Cliente clientePersistent = clienteDelegate.obter(pessoa.getId(), idInstituicao);

		if (clientePersistent == null) {
			cliente = new Cliente();
		} else {
			cliente = clientePersistent;
		}

		cliente.setIdCliente(pessoa.getId());
		cliente.setPessoa(pessoa);
		cliente.setParecerGerencia(pessoaInstituicao.getParecerGerencia());
		cliente.setEmiteAvisoLancamento(pessoaInstituicao.getEmiteAvisoLancamento());
		cliente.setNumPacCliente(pessoaInstituicao.getIdUnidadeInst());
		cliente.setDataUltimaAtualizacao(DataUtil.obterDataAtual());
		cliente.setNumNucleo(pessoaInstituicao.getNucleo().getNumNucleo());

		if (pessoaInstituicao.getFuncionario() != null) {

			FuncionarioDelegate funcionarioDelegate = fabrica.criarFuncionarioDelegate();
			Integer idPessoaLegado = obterIdPessoaLegado(pessoaInstituicao.getFuncionario().getPessoaCompartilhamento(), idInstituicao);

			Funcionario funcionario = funcionarioDelegate.obter(idPessoaLegado, idInstituicao);
			cliente.setFuncionario(funcionario);
		} else {
			cliente.setFuncionario(null);
		}

		if (pessoaInstituicao.getPerfilTarifario() != null) {
			cliente.setIdPerfilTarifario(pessoaInstituicao.getPerfilTarifario().getId().getCodPerfilTarifario());
		} else {
			cliente.setIdPerfilTarifario(null);
		}

		if (isInclusao(dto)) {
			cliente.setDataRisco(pessoaInstituicao.getDataEnquadramentoRisco());
			cliente.setIdNivelRisco(pessoaInstituicao.getNivelRisco());
			cliente.setMotivoRisco(pessoaInstituicao.getMotivoRisco());
		}

		return cliente;
	}

	/**
	 * Verifica se eh inclusao.
	 *
	 * @param dto o valor de dto
	 * @return {@code true}, se for inclusao
	 */
	private boolean isInclusao(AtualizacaoCadastralDTO<PessoaInstituicao> dto) {
		return dto.getTipoOperacao().equals(
				TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO_CLIENTE.getTipoOperacao());
	}
	
	/**
	 * Obter id pessoa legado.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Integer obterIdPessoaLegado(br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento pessoa, Integer idInstituicao) 
		throws BancoobException {

		CAPESCadastroFabricaDelegates fabrica = 
				CAPESCadastroFabricaDelegates.getInstance();
		PessoaCompartilhamentoDelegate delegate = fabrica.criarPessoaCompartilhamentoDelegate();
		br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento pessoaPersistent = 
			delegate.obter(pessoa.getId());

		for (TransicaoPessoa transicaoPessoa : pessoaPersistent.getTransicoes()) {
			if (transicaoPessoa.getInstituicao().getIdInstituicao().equals(idInstituicao)) {
				return transicaoPessoa.getIdPessoaLegado();
			}
		}

		return null;
	}	
}
