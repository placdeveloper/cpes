// 12/03/2013 - 08:16:10
package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * TODO javadoc
 *
 *
 * @author Rodrigo.Chaves
 */
public class CorrecaoAtualizacaoGenerica implements CorrecaoAtualizacaoStrategy {

	/** O atributo fabricaComum. */
	protected final CAPESCadastroFabricaDelegates fabricaComum =
			CAPESCadastroFabricaDelegates.getInstance();

	/**
	 * {@inheritDoc}
	 */
	public <A extends CAPESEntidade<Long> & Aprovavel> void execute(
			Autorizacao autorizacao, A aprovavel, TipoOperacaoEnum tipoOperacao)
			throws BancoobException {

		// verifica o tipo da operação atual
		if (TipoOperacaoEnum.E.equals(tipoOperacao)) {
			excluirAtualizacao(autorizacao, aprovavel);
		} else if (TipoOperacaoEnum.A.equals(tipoOperacao)){
			corrigirAtualizacao(autorizacao, aprovavel);
			corrigirDocumentos(autorizacao, aprovavel);
		} else {
			throw new BancoobRuntimeException(new IllegalArgumentException(
					"O tipoOperacao de uma correcao nao pode ser \"I - Inclusao\""));
		}
	}

	/**
	 * O método Corrigir atualizacao.
	 *
	 * @param <A> o tipo generico
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected <A extends CAPESEntidade<Long> & Aprovavel> void
			corrigirAtualizacao(Autorizacao autorizacao, A aprovavel) throws BancoobException {

		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = criarDelegate(autorizacao.getTipoAutorizacao());
		aprovavel.setVerificarAutorizacao(false);
		aprovavel.setGravarHistorico(false);
		delegate.alterar(aprovavel);
	}

	/**
	 * O método Corrigir documentos.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void corrigirDocumentos(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException {

		AutorizacaoCadastroDelegate autorizacaoCadastroDelegate =
				this.fabricaComum.criarAutorizacaoCadastroDelegate();
		autorizacaoCadastroDelegate.corrigirDocumentos(autorizacao, aprovavel);
	}

	/**
	 * O método Excluir atualizacao.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void excluirAtualizacao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {

		AutorizacaoCadastroDelegate autorizacaoCadastroDelegate =
				this.fabricaComum.criarAutorizacaoCadastroDelegate();
		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = criarDelegate(autorizacao.getTipoAutorizacao());

		aprovavel.setVerificarAutorizacao(false);
		((Vigente) aprovavel).setGravarHistorico(false);

		// se a autorizacao não foi originada por uma inclusão
		if (!TipoOperacaoEnum.I.equals(autorizacao.getTipoOperacao())) {
			Aprovavel registroOrigem = (Aprovavel) delegate.obter(autorizacao.getIdRegistroAntigo());
			autorizacaoCadastroDelegate.marcarEmAlteracao(registroOrigem, null);
		}

		autorizacaoCadastroDelegate.excluirAutorizacaoRejeitada(autorizacao);
		delegate.excluirEntidade((CAPESEntidade<?>) aprovavel);
	}

	/**
	 * Criar delegate.
	 *
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @return CAPESCadastroCrudDelegate
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> criarDelegate(TipoAutorizacaoEnum tipoAutorizacao)
			throws BancoobException {

		return this.fabricaComum.criarDelegate(TipoAutorizacaoEntidadeEnum.valueOf(tipoAutorizacao).getTipo());
	}
}
