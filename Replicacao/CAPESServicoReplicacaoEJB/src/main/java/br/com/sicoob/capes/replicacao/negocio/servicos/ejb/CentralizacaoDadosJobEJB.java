package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BancoServidorServicoLocal;
import br.com.sicoob.capes.replicacao.utils.SWSUtils;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sws.contexto.ContextoExecucaoJob;
import br.com.sicoob.sws.exception.JobException;
import br.com.sicoob.sws.job.EJBContextualJob;
import br.com.sicoob.sws.job.ISicoobContextualJob;
import br.com.sicoob.sws.job.RetornoVerificacaoJob;
import br.com.sicoob.sws.parametro.ParametroJob;
import br.com.sicoob.sws.parametro.ParametroStep;
import br.com.sicoob.sws.step.StepMetadata;
import br.com.sicoob.sws.util.TipoParametro;

/**
 * A Classe CentralizacaoDadosJobEJB.
 */
@Stateless
@Remote(ISicoobContextualJob.class)
public class CentralizacaoDadosJobEJB extends EJBContextualJob {

	/** O atributo bancoServidorServico. */
	@EJB(mappedName = "capes/replicacao/BancoServidorServico")
	private BancoServidorServicoLocal bancoServidorServico; 
	
	/** A constante NOME_JNDI_SERVICO_DEFAULT. */
	private static final String NOME_JNDI_SERVICO_DEFAULT = "capes/replicacao/CentralizacaoDadosStepRemote";
	
	/** A constante PARAM_NOME_JNDI_STEP. */
	private static final short PARAM_NOME_JNDI_STEP = 1;
	
	private ISicoobLogger logger = SicoobLoggerPadrao.getInstance(CentralizacaoDadosJobEJB.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StepMetadata> obterSteps(ContextoExecucaoJob ctx) throws JobException {
		ArrayList<StepMetadata> steps = new ArrayList<StepMetadata>();
		List<BancoServidor> listaBancosServidor = listarBancosServidor();
		
		logger.debug("Quantidade de bancos encontrados para centralizacao: ", String.valueOf(listaBancosServidor.size()));
		final double trabalho = (double) listaBancosServidor.size() / 100;
		for (BancoServidor bancoServidor : listaBancosServidor) {
			
			List<String> valoresNomeServer = new ArrayList<String>();
			valoresNomeServer.add(bancoServidor.getNomeServer());
			
			ParametroStep parametroNomeServer = new ParametroStep();
			parametroNomeServer.setCodTipoParametro(TipoParametro.VARCHAR.getCode());
			parametroNomeServer.setOrdem((short)1);
			parametroNomeServer.setValores(valoresNomeServer);
			
			List<String> valoresNomeBancoDados = new ArrayList<String>();
			valoresNomeBancoDados.add(bancoServidor.getNomeBancoDados());
			
			ParametroStep parametroNomeBancoDados = new ParametroStep();
			parametroNomeBancoDados.setCodTipoParametro(TipoParametro.VARCHAR.getCode());
			parametroNomeBancoDados.setOrdem((short)2);
			parametroNomeBancoDados.setValores(valoresNomeBancoDados);
			
			List<String> valoresNumCooperativa = new ArrayList<String>();
			valoresNumCooperativa.add(bancoServidor.getNumCooperativa().toString());
			
			ParametroStep parametroNumCooperativa = new ParametroStep();
			parametroNumCooperativa.setCodTipoParametro(TipoParametro.INT.getCode());
			parametroNumCooperativa.setOrdem((short)3);
			parametroNumCooperativa.setValores(valoresNumCooperativa);
			
			StepMetadata step = new StepMetadata();
			
			Map<Short, ParametroJob> mapaParamJob = SWSUtils.criarMapaParametrosJob(ctx.getParametrosJob());
			step.setServico(getNomeServico(mapaParamJob));
			
			step.setTimeout((short) 0);
			step.setTipo(StepMetadata.TIPO_STEP_EJB);
			step.setTrabalhoJob(trabalho);
			step.getParametros().add(parametroNomeServer);
			step.getParametros().add(parametroNomeBancoDados);
			step.getParametros().add(parametroNumCooperativa);
			steps.add(step);
		} 
		return steps;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoVerificacaoJob verificarDependencias(ContextoExecucaoJob ctx) throws JobException {
		return new RetornoVerificacaoJob();
	}

	/**
	 * Listar bancos servidor.
	 *
	 * @return List
	 * @throws JobException lança a exceção JobException
	 */
	private List<BancoServidor> listarBancosServidor() throws JobException {
		try {
			return bancoServidorServico.listar(new ConsultaDto<BancoServidor>());
		} catch (BancoobException e) {
			throw new JobException(e);
		}
	}
	
	/**
	 * Recupera o nome jndi do serviço de step.
	 * 
	 * @param mapaParamJob
	 *            o mapa de parametros do job.
	 * @return o valor do parametro NOME_JNDI_STEP
	 */
	protected String getNomeServico(Map<Short, ParametroJob> mapaParamJob) {
		ParametroJob param = mapaParamJob.get(PARAM_NOME_JNDI_STEP);
		if (param != null) {
			return param.getValores().get(0);
		}

		return NOME_JNDI_SERVICO_DEFAULT;
	}
	
}
