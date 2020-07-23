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
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.processamento.negocio.dominio.fechamento.FechamentoAutorizacoesVencidasIterator;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.CancelarAutorizacoesVencidasServicoLocal;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * Classe com o fluxo de cancelamento das autorizações, antes executado no Fechamento Agendado.
 *
 * @author Bruno.Carneiro
 */
@Local(CancelarAutorizacoesVencidasServicoLocal.class)
@Remote(StepServico.class)
@Stateless
public class CancelarAutorizacoesVencidasStepEJB extends CAPESStepBase implements CancelarAutorizacoesVencidasServicoLocal {

	/** A constante PRAZO_VALIDADE_AUTORIZACAO. */
	private static final int PRAZO_VALIDADE_AUTORIZACAO = 10;

	private static final String CODIGO_MSG_JUSTIFICATIVA_CANCELAMENTO = "MN004";

	@EJB
	private CancelarAutorizacoesVencidasServicoLocal cancelarAutorizacoesVencidasServico;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException {
		Long idAutorizacao = null;
		final int prazoValidadeAutorizacao;

		try {
			getLogger().info("Iniciando o step de cancelamento das autorizacoes vencidas.");

			if (contexto.getParametrosStep() != null && contexto.getParametrosStep().get("VALIDADE_AUTORIZACOES") != null) {
				Parametro parametro = contexto.getParametrosStep().get("VALIDADE_AUTORIZACOES");
				prazoValidadeAutorizacao = parametro.getValor();
			} else {
				prazoValidadeAutorizacao = PRAZO_VALIDADE_AUTORIZACAO;
			}

			// Cria a data de vencimento para a verificação
			Date dataLimite = new Date();
			dataLimite = DataUtil.incrementarData(dataLimite, Calendar.DATE, -prazoValidadeAutorizacao);
			dataLimite = DateUtils.truncate(dataLimite, Calendar.DATE);

			// Obtém a mensagem de justificativa do cancelamento.
			String justificativa = MensagemUtil.getString(CODIGO_MSG_JUSTIFICATIVA_CANCELAMENTO, PRAZO_VALIDADE_AUTORIZACAO);

			// Para cada autorização vencida encontrada, cancela o fluxo no GFT e apaga o registro da autorização.
			Iterator<Autorizacao> it = new FechamentoAutorizacoesVencidasIterator(dataLimite);
			while (it.hasNext()) {
				try {
					Autorizacao autorizacao = it.next();
					idAutorizacao = autorizacao.getId();
					getLogger().info("Iniciando o cancelamento da autorizacao: " + idAutorizacao);
					cancelarAutorizacoesVencidasServico.cancelarAutorizacaoVencida(autorizacao, justificativa);
				} catch (BancoobException e) {
					getLogger().erro(e, "Erro no fechamento da autorizacao: " + idAutorizacao);
				}
			}

			getLogger().info("Fim do fechamento das autorizacoes vencidas");

			return sucesso();

		} catch (Exception e) {// NOPMD
			getLogger().erro(e, "Erro no fechamento da autorizacao: " + idAutorizacao);
			return erro("Erro no fechamento da autorizacao: " + idAutorizacao);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void cancelarAutorizacaoVencida(Autorizacao autorizacao, String justificativa) throws BancoobException {
		AutorizacaoDelegate autorizacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoDelegate();
		autorizacaoDelegate.cancelarAutorizacaoVencida(autorizacao, justificativa);
	}

}