package br.com.sicoob.capes.processamento.negocio.anotacao.estrategia;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Classe EstrategiaAtualizarAnotacoesExternasReceita.
 */
public class EstrategiaAtualizarAnotacoesExternasReceita extends EstrategiaAtualizarAnotacoesExternasPadrao {

	/** A constante TAMANHO_NOME_RFB. */
	private static final int TAMANHO_NOME_RFB = 200;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void atualizarAnotacoes(OrigemInformacao origemInformacao, AnotacaoExternaDTO dto,
			List<PessoaCompartilhamento> pessoas) throws BancoobException {

		super.atualizarAnotacoes(origemInformacao, dto, pessoas);
		for (PessoaCompartilhamento pessoa : pessoas) {
			atualizarDadosPessoa(pessoa, dto);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<PessoaCompartilhamento> obterPessoas(AnotacaoExternaDTO dto) throws BancoobException {

		PessoaCompartilhamentoDelegate delegate = CAPESCadastroFabricaDelegates
				.getInstance().criarPessoaCompartilhamentoDelegate();
		return delegate.listarPessoasMesmoDocumento(dto.getNumCpfCnpj());
	}

	/**
	 * Atualiza os dados da pessoa verificando se é uma pessoa física ou pessoa jurídica
	 * 
	 * @param pessoa
	 *            {@link Pessoa}
	 * @param objetoConsulta
	 *            Objeto serializado que é retornado na consulta à Receita Federal
	 * @throws BancoobException
	 */
	private void atualizarDadosPessoa(PessoaCompartilhamento pessoaCompartilhamento, AnotacaoExternaDTO dto)
			throws BancoobException {

		Integer codSituacaoCadastral = Integer.valueOf(dto.getListaAnotacoes().get(0).getCodigoTipoOrigemInformacao());
		String nomeReceita = dto.getNomeReceita();

		SituacaoCadastralRFBEnum situacao = SituacaoCadastralRFBEnum.valueOf(codSituacaoCadastral,
				obterTipoPessoa(pessoaCompartilhamento));
		Pessoa pessoa = pessoaCompartilhamento.getPessoa();

		pessoa.setSituacaoCadastralRFB(situacao);
		if (StringUtils.isNotBlank(nomeReceita)) {
			pessoa.setNomeRFB(StringUtils.left(nomeReceita, TAMANHO_NOME_RFB));
		}

		PessoaDelegate pessoaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaDelegate();
		pessoaDelegate.alterar(pessoa);
	}

	/**
	 * 
	 * @param pessoa
	 * @return
	 */
	private TipoPessoaEnum obterTipoPessoa(PessoaCompartilhamento pessoa) {

		return TipoPessoaEnum.valueOf(pessoa.getPessoa().getTipoPessoa().getCodTipoPessoa());
	}

}