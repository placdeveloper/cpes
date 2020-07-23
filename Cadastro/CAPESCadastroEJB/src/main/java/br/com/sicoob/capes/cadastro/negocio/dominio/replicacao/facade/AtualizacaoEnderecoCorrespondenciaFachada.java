/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import java.util.Collection;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;

/**
 * Fachada para atualiza��o do endere�o de correspond�ncia em determinada institui��o.
 * 
 * @author Erico.Junior
 */
public class AtualizacaoEnderecoCorrespondenciaFachada extends
		AtualizacaoCadastralFachada<EnderecoCorrespondencia> {

	/**
	 * Executa a atualiza��o do endere�o de correspond�ncia no cadastro da pessoa no legado.
	 * 
	 * @param correspondencia
	 *            O endere�o de correspond�ncia a ser atualizado no legado..
	 * @throws BancoobException
	 *             Caso ocorra algum erro no envio da replica��o.
	 */
	@Override
	public void executarAtualizacao(EnderecoCorrespondencia correspondencia, 
			TipoAtualizacaoCadastralEnum tipoAtualizacao) throws BancoobException {
		
		TransicaoPessoa transicao = recuperarTransicao(correspondencia);
		if(transicao != null) {
			salvarAtualizacaoCadastral(correspondencia, transicao, 
					TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_ENDERECO_CORRESPONDENCIA);
		}
	}
	
	/**
	 * Recupera a transi��o da pessoa para o endere�o de correspond�ncia ser alterado.
	 * Cada institui��o tem um endere�o de correspond�ncia, por isso precisamos enviar apenas
	 * para a institui��o que est� atualizando o registro.
	 * 
	 * @param correspondencia
	 *            O endere�o de correspond�ncia da pessoa na institui��o.
	 * @return a transi��o da pessoa para o endere�o de correspond�ncia ser alterado.
	 * @throws BancoobException Caso ocorr alguma exce��o.
	 */
	private TransicaoPessoa recuperarTransicao(EnderecoCorrespondencia correspondencia) 
		throws BancoobException {

		TransicaoPessoa retorno = null;
		Collection<TransicaoPessoa> transicoes = obterTransicoes(correspondencia);
		Integer idInstituicao = correspondencia.getIdInstituicao();
		
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