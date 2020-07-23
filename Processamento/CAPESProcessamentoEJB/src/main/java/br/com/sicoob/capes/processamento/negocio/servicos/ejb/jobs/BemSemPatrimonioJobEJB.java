package br.com.sicoob.capes.processamento.negocio.servicos.ejb.jobs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.cadastro.persistencia.dao.BemDAO;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;


@Stateless
@Remote(JobServico.class)
public class BemSemPatrimonioJobEJB extends CAPESJobBase {

	@Inject
	@Default
	private BemDAO dao;

	private static final String JNDI_STEP = "capes/processamento/BemSemPatrimonioStepRemote";

	@Override
	protected VerificacaoDependencias executarVerificacaoDependencias(ContextoExecucao contexto) throws BancoobException {
		Number total = dao.quantidadePessoasSemBensVinculados();
		if (total != null && total.intValue() > 0) {
			return sucesso();
		} else {
			return finalizarJob(MensagemUtil.getString("msg.finalizarJob.semRegistros.001"));
		}
	}

	
	@Override
	protected List<Step> criarListaSteps(ContextoExecucao contexto) throws BancoobException {
		List<Object[]> listaPessoas = dao.obterPessoaSemBensVinculados();
		List<Step> steps = new ArrayList<Step>();
		for (Object[] object : listaPessoas) {
			Step step = criarStep(JNDI_STEP);
			long idPessoacompartilhamento = ((Number) object[0]).longValue();
			int idInstituicao = ((Number) object[1]).intValue();
			step.comParametros(new Parametro("idPessoacompartilhamento", idPessoacompartilhamento, TipoParametro.LONGO));
			step.comParametros(new Parametro("idInstituicao", idInstituicao, TipoParametro.INTEIRO));
			steps.add(step);
		}
		return calcularEsforco(steps);
	}

}
