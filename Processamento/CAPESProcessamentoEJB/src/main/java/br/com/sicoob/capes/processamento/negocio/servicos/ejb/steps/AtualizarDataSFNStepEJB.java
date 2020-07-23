package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.processamento.negocio.dto.PessoaSFNDTO;
import br.com.sicoob.capes.processamento.persistencia.dao.AtualizacaoDataSFNDao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * Classe com o fluxo de atualização de dataSFN, que antes era executado no fechamento agendado. </br>
 * 
 * <b>NOTA: Por dependência do grupo de fechamento da GESIN 1, esse fluxo não está sendo chamado.</b>
 *
 * @author Bruno.Carneiro
 */
@Remote(StepServico.class)
@Stateless
public class AtualizarDataSFNStepEJB extends CAPESStepBase {

	@Inject
	@Default
	private transient AtualizacaoDataSFNDao dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException {
		try {
			Parametro parametro = contexto.getParametrosStep().get("NUMERO_COOPERATIVA");

			Integer numCooperativa = parametro.getValor();
			PessoaCompartilhamentoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

			List<PessoaSFNDTO> lista = dao.listarPessoasDataSFNAtualizadas(numCooperativa);
			getLogger().info("AtualizacaoDataSFNServicoEJB: cooperativa: " + numCooperativa + ", encontrados: " + lista.size());

			for (PessoaSFNDTO pessoaSFN : lista) {
				delegate.alterarDataCadastramentoSFN(pessoaSFN.getIdPessoa(), getInstituicaoPessoa(pessoaSFN), pessoaSFN.getDataCadastramentoSFN());
			}

			dao.atualizarDataSFN(numCooperativa);

			return sucesso();
		} catch (BancoobException ex) {
			getLogger().erro(ex, "Erro na atualizacao de data cadastro SFN");
			String mensagem = ex.getMessage();
			if (mensagem == null) {
				mensagem = ex.getCause().getMessage();
			}
			return erro(mensagem);
		}
	}

	/**
	 * Recupera o valor de instituicaoPessoa.
	 *
	 * @param pessoa
	 *            o valor de pessoa
	 * @return o valor de instituicaoPessoa
	 */
	private Instituicao getInstituicaoPessoa(PessoaSFNDTO pessoa) {
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(pessoa.getIdInstituicao());
		instituicao.setIdUnidadeInst(pessoa.getIdUnidadeInst());
		return instituicao;
	}

}