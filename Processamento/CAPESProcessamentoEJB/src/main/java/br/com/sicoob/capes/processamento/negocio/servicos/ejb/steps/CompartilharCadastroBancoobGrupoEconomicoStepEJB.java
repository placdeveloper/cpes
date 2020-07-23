package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ReplicacaoCadastroServicoLocal;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

@Stateless
@Remote(StepServico.class)
public class CompartilharCadastroBancoobGrupoEconomicoStepEJB extends CompartilharCadastroGrupoEconomicoStep {
	
	@EJB
	private transient ReplicacaoCadastroServicoLocal servicoReplicacao;
	
	@EJB
	private transient PessoaCompartilhamentoServicoLocal pessoaCompartilhamentoServico;

	@Override
	protected PessoaCompartilhamento compartilharCadastro(ContextoExecucao contexto, Long idPessoaCompartilhamento) throws BancoobException {
		final PessoaCompartilhamento pessoa = pessoaCompartilhamentoServico.obter(idPessoaCompartilhamento);
		return servicoReplicacao.incluirRelacionamentoBancoob(pessoa);
	}
	
}
