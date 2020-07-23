package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * TODO javadoc
 *
 */
public class CorrecaoAtualizacaoProdutor extends CorrecaoAtualizacaoEspecifica {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void excluirAtualizacao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = this.fabricaComum.criarAutorizacaoCadastroDelegate();
		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = criarDelegate(autorizacao.getTipoAutorizacao());

		// se a autorizacao não foi originada por uma inclusão
		if (!TipoOperacaoEnum.I.equals(autorizacao.getTipoOperacao())) {
			Aprovavel registroOrigem = (Aprovavel) delegate.obter(autorizacao.getIdRegistroAntigo());
			registroOrigem.setVerificarAutorizacao(false);
			((Vigente) registroOrigem).setGravarHistorico(false);
			autorizacaoCadastroDelegate.marcarEmAlteracao(registroOrigem, null);
		}

		autorizacaoCadastroDelegate.excluirAutorizacaoRejeitada(autorizacao);
	}
}