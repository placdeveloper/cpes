package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.Mensagem;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoMensagemPessoa;

/**
 * @author Diego.Rezende
 */
public class HistoricoMensagemPessoaBuilder extends HistoricoBuilder<HistoricoMensagemPessoa, Mensagem> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoMensagemPessoa novaInstanciaHistorico() {
		return new HistoricoMensagemPessoa();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HistoricoMensagemPessoa preencherDadosHistorico(HistoricoMensagemPessoa historico, Mensagem entidadeVigente) {
		// Atribuindo null no id porque o valor do idEndereco foi atribuido na copia.
		historico.setId(null);
		return historico;
	}

}
