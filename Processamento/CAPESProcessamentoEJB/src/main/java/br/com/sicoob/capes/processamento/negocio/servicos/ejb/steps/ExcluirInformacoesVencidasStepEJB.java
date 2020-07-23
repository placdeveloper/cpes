package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemReplicacaoDelegate;
import br.com.sicoob.capes.processamento.negocio.dominio.informacoesvencidas.CertidaoVencida;
import br.com.sicoob.capes.processamento.negocio.dominio.informacoesvencidas.FonteRendaVencida;
import br.com.sicoob.capes.processamento.negocio.dominio.informacoesvencidas.InformacoesVencidas;
import br.com.sicoob.capes.processamento.negocio.dominio.informacoesvencidas.MensagemVencida;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * Classe com o fluxo de expurgo das informações cadastrais vencidas, antes executado no Fechamento Agendado.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Remote(StepServico.class)
public class ExcluirInformacoesVencidasStepEJB extends CAPESStepBase {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException {
		try {
			getLogger().info("Iniciando a execucao do step de exclusao de informacoes vencidas.");

			excluirRendasVencidas();
			excluirCertidoesVencidas();
			excluirMengensVencidas();
			expurgarMensagensReplicacao();

			return sucesso();
		} catch (BancoobException e) {
			getLogger().erro(e, "Erro na execucao do step de exclusao de informacoes vencidas.");
			return erro("Erro na execucao do step de exclusao de informacoes vencidas: " + e.getMessage());
		}
	}

	/**
	 * O método Excluir rendas vencidas.
	 * 
	 * @throws BancoobException
	 */
	private void excluirRendasVencidas() throws BancoobException {
		InformacoesVencidas fachada = new FonteRendaVencida();
		fachada.excluir();
	}

	/**
	 * O método Excluir mengens vencidas.
	 * 
	 * @throws BancoobException
	 */
	private void excluirMengensVencidas() throws BancoobException {
		InformacoesVencidas fachada = new MensagemVencida();
		fachada.excluir();
	}

	/**
	 * O método Excluir certidoes vencidas.
	 * 
	 * @throws BancoobException
	 */
	private void excluirCertidoesVencidas() throws BancoobException {
		InformacoesVencidas fachada = new CertidaoVencida();
		fachada.excluir();
	}

	/**
	 * O método Expurgar mensagens replicacao.
	 * 
	 * @throws BancoobException
	 */
	private void expurgarMensagensReplicacao() throws BancoobException {
		MensagemReplicacaoDelegate mensagemReplicacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarMensagemReplicacaoDelegate();
		mensagemReplicacaoDelegate.expurgarMensagensReplicacao();
	}

}