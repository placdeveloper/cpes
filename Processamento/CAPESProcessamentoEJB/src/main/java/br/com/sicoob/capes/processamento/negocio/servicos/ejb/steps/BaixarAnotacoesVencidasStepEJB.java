package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.enums.GrupoTipoAnotacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.processamento.negocio.dominio.fechamento.FechamentoAnotacoesVencidasIterator;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.BaixarAnotacoesVencidasServicoLocal;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * Classe ocm o fluxo de expurgo das anotações vencidas, antes executado no Fechamento Agendado.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Remote(StepServico.class)
@Local(BaixarAnotacoesVencidasServicoLocal.class)
public class BaixarAnotacoesVencidasStepEJB extends CAPESStepBase implements BaixarAnotacoesVencidasServicoLocal {

	private static final int PRAZO_VALIDADE_ANOTACOES = 365;
	private static final String USUARIO_FECHAMENTO_CAPES = "CAPES - FechamentoAnotacoesVencidas";

	@EJB
	private BaixarAnotacoesVencidasServicoLocal baixarAnotacoesVencidasServico;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException {
		final int prazoValidadeAnotacao;

		try {
			getLogger().info("Iniciando o step de baixa das anotacoes vencidas");

			if (contexto.getParametrosStep() != null && contexto.getParametrosStep().get("VALIDADE_ANOTACOES") != null) {
				Parametro parametro = contexto.getParametrosStep().get("VALIDADE_ANOTACOES");
				prazoValidadeAnotacao = parametro.getValor();
			} else {
				prazoValidadeAnotacao = PRAZO_VALIDADE_ANOTACOES;
			}

			// Cria a data de vencimento para a verificação
			Date dataLimite = new Date();
			dataLimite = DataUtil.incrementarData(dataLimite, Calendar.DATE, -prazoValidadeAnotacao);
			dataLimite = DateUtils.truncate(dataLimite, Calendar.DATE);

			// Para cada anotação vencida encontrada, realiza a baixa da mesma
			Iterator<Anotacao> it = new FechamentoAnotacoesVencidasIterator(dataLimite, GrupoTipoAnotacaoEnum.BAIXA_ANUAL_SOCIOAMBIENTAL.getCodigo());
			while (it.hasNext()) {
				Anotacao anotacao = null;
				try {
					anotacao = it.next();
					getLogger().debug("Iniciando baixa da anotacao: ", String.valueOf(anotacao.getId()));
					baixarAnotacoesVencidasServico.baixarAnotacao(anotacao, USUARIO_FECHAMENTO_CAPES);
				} catch (BancoobException e) {
					getLogger().erro(e, "Erro ao baixar a anotacao: " + anotacao.getId());
				}
			}

			getLogger().info("Fim do step de baixa das anotacoes vencidas.");
			return sucesso();
		} catch (Exception e) {// NOPMD
			getLogger().erro(e, "Falha no fechamento das anotacoes vencidas.");
			return erro("Falha no fechamento das anotacoes vencidas: " + e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void baixarAnotacao(Anotacao anotacao, String usuario) throws BancoobException {
		AnotacaoDelegate anotacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAnotacaoDelegate();
		anotacaoDelegate.baixarAnotacao(anotacao, usuario);
	}

}