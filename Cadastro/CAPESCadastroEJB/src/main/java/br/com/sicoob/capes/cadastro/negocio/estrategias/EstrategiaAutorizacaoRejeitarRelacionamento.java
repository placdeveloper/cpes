/**
 *
 */
package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * @author Marcelo.Onofre
 *
 */
public class EstrategiaAutorizacaoRejeitarRelacionamento extends
		EstrategiaAutorizacaoRejeitar {

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
		RelacionamentoPessoa registroAntigo = (RelacionamentoPessoa) obter(tipoAutorizacao,
				autorizacao.getIdRegistroAntigo());

		if (registroAntigo != null) {
			// desbloqueia o registro antigo para edição
			delegate.marcarEmAlteracao(registroAntigo, null);

			RelacionamentoPessoa reverso = registroAntigo
					.getRelacionamentoReverso();
			if (reverso != null && reverso.getId() != null) {
				delegate.marcarEmAlteracao(reverso, null);
			}
		}
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

		RelacionamentoPessoa registroAntigo = (RelacionamentoPessoa)aprovavel;
		RelacionamentoPessoa reverso = registroAntigo.getRelacionamentoReverso();
		// desbloqueia o registro para edição
		delegate.marcarEmAlteracao(registroAntigo, null);
		if(reverso != null
				&& reverso.getId() != null){
			delegate.marcarEmAlteracao(reverso, null);
		}

	}
}
