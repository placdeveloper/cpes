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
 * Fachada para atualização do endereço de correspondência em determinada instituição.
 * 
 * @author Erico.Junior
 */
public class AtualizacaoEnderecoCorrespondenciaFachada extends
		AtualizacaoCadastralFachada<EnderecoCorrespondencia> {

	/**
	 * Executa a atualização do endereço de correspondência no cadastro da pessoa no legado.
	 * 
	 * @param correspondencia
	 *            O endereço de correspondência a ser atualizado no legado..
	 * @throws BancoobException
	 *             Caso ocorra algum erro no envio da replicação.
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
	 * Recupera a transição da pessoa para o endereço de correspondência ser alterado.
	 * Cada instituição tem um endereço de correspondência, por isso precisamos enviar apenas
	 * para a instituição que está atualizando o registro.
	 * 
	 * @param correspondencia
	 *            O endereço de correspondência da pessoa na instituição.
	 * @return a transição da pessoa para o endereço de correspondência ser alterado.
	 * @throws BancoobException Caso ocorr alguma exceção.
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