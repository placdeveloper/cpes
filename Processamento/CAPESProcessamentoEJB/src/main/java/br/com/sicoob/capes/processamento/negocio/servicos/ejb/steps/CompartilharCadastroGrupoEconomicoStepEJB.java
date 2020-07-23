package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ReplicacaoCadastroServicoLocal;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

@Stateless
@Remote(StepServico.class)
public class CompartilharCadastroGrupoEconomicoStepEJB extends CompartilharCadastroGrupoEconomicoStep {
	
	@EJB
	private transient ReplicacaoCadastroServicoLocal servicoReplicacao;

	@Override
	protected PessoaCompartilhamento compartilharCadastro(ContextoExecucao contexto, Long idPessoaCompartilhamento) throws BancoobException {
		final Integer idInstituicao = getParametroDinamico(contexto);
		final Instituicao destino = new Instituicao(idInstituicao, Constantes.Comum.ID_UNIDADEINST_ZERO);
		return servicoReplicacao.iniciarRelacionamentoInstituicao(idPessoaCompartilhamento, destino);
	}

}
