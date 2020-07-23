/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;

/**
 * @author Erico.Junior
 * 
 */
public interface AtualizarCadastroClienteServico<R extends EntidadeReplicavel<?>, E extends Replicavel>
		extends CAPESReplicacaoProcessamentoServico {

	/**
	 * O método Atualizar cadastro.
	 *
	 * @param mensagem o valor de mensagem
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public abstract void atualizarCadastro(MensagemReplicacao mensagem) throws BancoobException;

	/**
	 * Obter mensagens replicacao.
	 *
	 * @param idOperacao o valor de id operacao
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 */
	public abstract List<MensagemReplicacao> obterMensagensReplicacao(final String idOperacao, final Integer idInstituicao);

	/**
	 * O método Atualizar data processamento.
	 *
	 * @param mensagem o valor de mensagem
	 * @param excecao o valor de excecao
	 */
	public abstract void atualizarDataProcessamento(final MensagemReplicacao mensagem, final Exception excecao);

}
