// 09/08/2013
package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.TransicaoPessoaServico;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;

/**
 * A Interface TransicaoPessoaServicoLocal.
 */
public interface TransicaoPessoaServicoLocal extends TransicaoPessoaServico {
	
	/**
	 * Obtém a transicao por idPessoa e Idinstituicao
	 * @param idPessoaCompartilhamento
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	TransicaoPessoa obterTransicaoPorPessoaCompartilhamentoInstituicao(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException;

}