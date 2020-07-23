package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicarCadastroClientesServicoLocal;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sws.contexto.ContextoExecucaoStep;
import br.com.sicoob.sws.exception.StepException;
import br.com.sicoob.sws.parametro.ParametroStep;
import br.com.sicoob.sws.step.EJBContextualStep;
import br.com.sicoob.sws.step.ISicoobContextualStep;
import br.com.sicoob.sws.step.RetornoStep;

/**
 * A Classe CentralizacaoDadosStepEJB.
 */
@Stateless
@Remote(ISicoobContextualStep.class)
public class CentralizacaoDadosStepEJB extends EJBContextualStep {

	/** O atributo servico. */
	@EJB(mappedName = "capes/replicacao/ReplicarCadastroClientesServico")
	private ReplicarCadastroClientesServicoLocal servico;
	
	/** O atributo logger. */
	private ISicoobLogger logger = SicoobLoggerPadrao.getInstance(CentralizacaoDadosStepEJB.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoStep executar(ContextoExecucaoStep ctx) throws StepException {
		RetornoStep retornoStep = new RetornoStep();
		retornoStep.setResultado(RetornoStep.RESULTADO_SUCESSO);
		
		BancoServidor bancoServidor = null;
		try {
			
			ParametroStep parametroStepNomeServer = ctx.getParametrosStep().get(0);
			ParametroStep parametroStepNomeBancoDados = ctx.getParametrosStep().get(1);
			ParametroStep parametroStepNumCooperativa = ctx.getParametrosStep().get(2);
			
			String nomeServer = parametroStepNomeServer.getValores().get(0);
			String nomeBancoDados = parametroStepNomeBancoDados.getValores().get(0);
			String numCooperativa = parametroStepNumCooperativa.getValores().get(0);
			
			logger.debug("CAPES parametroStepNomeServer - ", nomeServer);
			logger.debug("CAPES parametroStepNomeBancoDados - ", nomeBancoDados);
			logger.debug("CAPES parametroStepNumeroCooperativa - ", numCooperativa);
			
			bancoServidor = new BancoServidor();
			bancoServidor.setNomeBancoDados(nomeBancoDados);
			bancoServidor.setNomeServer(nomeServer);
			bancoServidor.setNumCooperativa(Integer.valueOf(numCooperativa));
			
			servico.centralizaTransicaoReplicacao(bancoServidor);
		} catch (BancoobException e) {
			logger.erro(e, gerarMensagemLog(bancoServidor));
			retornoStep.setResultado(RetornoStep.RESULTADO_ERRO);
			retornoStep.setMensagem("Falha ao centralizar os dados para replicação. " +
					"Será feita uma nova tentativa no próximo processamento.");
		}
		
		return retornoStep;
	}
	
	private String gerarMensagemLog(BancoServidor bancoServidor) {
		StringBuilder builder = new StringBuilder("Erro na centralizacao dos dados.");
		if(bancoServidor != null) {
			builder.append("Servidor: ");
			builder.append(bancoServidor.getNomeServer());
			builder.append("Banco: ");
			builder.append(bancoServidor.getNomeBancoDados());
			builder.append("Numero cooperativa: ");
			builder.append(bancoServidor.getNumCooperativa());
		}
		return builder.toString(); 
				
	}
}
