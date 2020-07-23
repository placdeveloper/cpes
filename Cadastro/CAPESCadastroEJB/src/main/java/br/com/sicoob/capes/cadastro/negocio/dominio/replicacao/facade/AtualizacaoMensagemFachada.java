/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import java.util.Collection;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.Mensagem;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;

/**
 * @author erico.junior
 * 
 */
public class AtualizacaoMensagemFachada extends
		AtualizacaoCadastralFachada<Mensagem> {

	/**
	 * Executa a atualização da mensagem no cadastro da pessoa no legado.
	 * 
	 * @param mensagem
	 *            A mensagem a ser atualizado no legado..
	 * @throws BancoobException
	 *             Caso ocorra algum erro no envio da replicação.
	 */
	@Override
	public void executarAtualizacao(Mensagem mensagem, 
			TipoAtualizacaoCadastralEnum tipoAtualizacao) throws BancoobException {
		
		TransicaoPessoa transicao = recuperarTransicao(mensagem);
		if(transicao != null) {
			salvarAtualizacaoCadastral(mensagem, transicao, tipoAtualizacao);
		}
	}
	
	/**
	 * Recupera a transição da pessoa para o a mensagem a ser alterada.
	 * 
	 * @param mensagem
	 *            A mensagem da pessoa na instituição.
	 * @return a transição da pessoa para a mensagem a ser alterado.
	 * @throws BancoobException Caso ocorr alguma exceção.
	 */
	private TransicaoPessoa recuperarTransicao(Mensagem mensagem) throws BancoobException {

		TransicaoPessoa retorno = null;
		Collection<TransicaoPessoa> transicoes = obterTransicoes(mensagem);
		Integer idInstituicao = mensagem.getIdInstituicao();
		
		if (transicoes != null && !transicoes.isEmpty()) {

			for (TransicaoPessoa transicao : transicoes) {
				if (idInstituicao.equals(transicao.getInstituicao().getIdInstituicao())) {
					retorno = transicao;
					break;
				}
			}
		}

		return retorno;
	}	
}
