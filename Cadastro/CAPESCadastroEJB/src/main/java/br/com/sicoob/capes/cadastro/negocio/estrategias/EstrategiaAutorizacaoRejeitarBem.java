package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemPessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * Estratégia para a rejeição de uma aprovação de um bem.
 * 
 * @author Bruno.Carneiro
 */
public class EstrategiaAutorizacaoRejeitarBem extends EstrategiaAutorizacaoRejeitar {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		AutorizacaoCadastroDelegate delegate = getFabricaDelegatesComum().criarAutorizacaoCadastroDelegate();

		// recupera o registro antigo
		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao.getTipoAutorizacao());
		Aprovavel registroAntigo = obter(tipoAutorizacao, autorizacao.getIdRegistroAntigo());

		Bem bem = (Bem) registroAntigo;
		BemPessoaCompartilhamentoDelegate bemPessoaCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarBemPessoaCompartilhamentoDelegate();
		for (BemPessoaCompartilhamento bemPessoaCompartilhamento : bem.getProprietarios()) {
			bemPessoaCompartilhamento.setPessoaResponsavel(Boolean.FALSE);
			bemPessoaCompartilhamentoDelegate.marcarEmAlteracao(bemPessoaCompartilhamento, null);
		}

		// desbloqueia o registro antigo para edição
		delegate.marcarEmAlteracao(registroAntigo, null);

		//exclui os dados da aprovação
		excluirDadosAprovacao(aprovavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarExclusao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		super.tratarExclusao(autorizacao, aprovavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void excluirDadosAprovacao(Aprovavel aprovavel) throws BancoobException {
		tratarAprovavel(aprovavel);
		tratarHistorico(aprovavel, false);

		Bem bem = (Bem) aprovavel;
		for (BemPessoaCompartilhamento bemPessoaCompartilhamento : bem.getProprietarios()) {
			tratarAprovavel(bemPessoaCompartilhamento);
			tratarHistorico(bemPessoaCompartilhamento, false);
		}

		excluir(aprovavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void excluir(Aprovavel aprovavel) throws BancoobException {
		LOGGER.debug("Excluindo ", aprovavel.getTipoAutorizacao().name(), ": ", String.valueOf(aprovavel.getId()));

		Bem bem = (Bem) aprovavel;
		obterBemDelegate(bem).excluirEntidade(bem);
	}

	/**
	 * Faz o tratamento da classe aprovavel.
	 * 
	 * @param aprovavel
	 */
	private void tratarAprovavel(Aprovavel aprovavel) {
		aprovavel.setVerificarAutorizacao(false);
	}

	/**
	 * Obtém o delegate do bem.
	 * 
	 * @param bem
	 * @return
	 * @throws BancoobException
	 */
	private CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> obterBemDelegate(Bem bem) throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarDelegate(bem.getClass());
	}
	
}