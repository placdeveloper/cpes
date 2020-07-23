package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;


/**
 * @author Marcelo.Onofre
 *
 */
public class CorrecaoAtualizacaoRelacionamento extends CorrecaoAtualizacaoGenerica {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void excluirAtualizacao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {

		AutorizacaoCadastroDelegate autorizacaoCadastroDelegate =
				this.fabricaComum.criarAutorizacaoCadastroDelegate();
		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = criarDelegate(autorizacao.getTipoAutorizacao());

		RelacionamentoPessoa relacionamentoAprovavel = (RelacionamentoPessoa)aprovavel;
		relacionamentoAprovavel.setVerificarAutorizacao(false);
		relacionamentoAprovavel.setGravarHistorico(false);

		// se a autorizacao não foi originada por uma inclusão
		if (!TipoOperacaoEnum.I.equals(autorizacao.getTipoOperacao())) {
			RelacionamentoPessoa registroOrigem = (RelacionamentoPessoa) delegate.obter(autorizacao.getIdRegistroAntigo());
			if (registroOrigem != null) {
				RelacionamentoPessoa reverso = registroOrigem
						.getRelacionamentoReverso();
				autorizacaoCadastroDelegate.marcarEmAlteracao(registroOrigem,
						null);
				if (reverso != null && reverso.getId() != null) {
					autorizacaoCadastroDelegate
							.marcarEmAlteracao(reverso, null);
				}
			}
		}

		autorizacaoCadastroDelegate.excluirAutorizacaoRejeitada(autorizacao);
		delegate.excluirEntidade(relacionamentoAprovavel);
	}
}