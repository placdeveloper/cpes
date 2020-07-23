package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralRegraDelegate;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * Classe com o fluxo de validação cadastral, onde as regras são executadas e a categoria de cadastro alterada.
 * 
 * Fluxo que executado anteriormente pelo Fechamento Agendado.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Remote(StepServico.class)
public class ExecutarValidacaoCadastralStepEJB extends CAPESStepBase {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException {
		getLogger().info("Iniciando a execucao do step de validacoes cadastrais");

		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		ValidacaoCadastralRegraDelegate regraDelegate = fabrica.criarValidacaoCadastralRegraDelegate();
		ValidacaoCadastralDelegate validacaoDelegate = fabrica.criarValidacaoCadastralDelegate();

		try {

			DateTimeDB dataValidacao = new DateTimeDB();
			for (ValidacaoCadastralRegra regra : regraDelegate.listarRegrasExecutaveis()) {
				if (regra.getExecutarRegra() || !regra.getAtivo()) {
					regra.setExecutarRegra(Boolean.FALSE);
					regraDelegate.validar(regra);
				} else {
					regraDelegate.validar(regra, dataValidacao);
				}
			}

			validacaoDelegate.atualizarDataUltimaValidacao(dataValidacao);
			regraDelegate.validarCategoriaCadastro();// esse procedimento ira atualizar os cadastros em relacao a categoria, se possivel subira de nivel

			return sucesso();
		} catch (BancoobException e) {
			getLogger().erro(e, "Erro no fechamento de validacoes cadastrais.");
			return erro("Erro na execucao do step de validacoes cadastrais: " + e.getMessage());
		}
	}

}