package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * 15/02/2011
 *
 * @author Rodrigo.Chaves
 */
public class EstrategiaAutorizacaoRejeitar extends EstrategiaAutorizacaoFinalizacao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarInclusao(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException {

		excluirDadosAprovacao(aprovavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException {

		AutorizacaoCadastroDelegate delegate =
				getFabricaDelegatesComum().criarAutorizacaoCadastroDelegate();

		// recupera o registro antigo
		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao
				.getTipoAutorizacao());
		Aprovavel registroAntigo = obter(tipoAutorizacao, autorizacao.getIdRegistroAntigo());

		// desbloqueia o registro antigo para edição
		delegate.marcarEmAlteracao(registroAntigo, null);

		excluirDadosAprovacao(aprovavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarExclusao(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException {

		AutorizacaoCadastroDelegate delegate =
				getFabricaDelegatesComum().criarAutorizacaoCadastroDelegate();

		// desbloqueia o registro para edição
		delegate.marcarEmAlteracao(aprovavel, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void excluirAutorizacao(Autorizacao autorizacao) throws BancoobException {

		LOGGER.debug("Excluindo a autorizacao (rejeitada): ",
				String.valueOf(autorizacao.getIdAutorizacao()));
		getFabricaDelegatesComum().criarAutorizacaoCadastroDelegate().excluirAutorizacaoRejeitada(
				autorizacao);
	}

}
