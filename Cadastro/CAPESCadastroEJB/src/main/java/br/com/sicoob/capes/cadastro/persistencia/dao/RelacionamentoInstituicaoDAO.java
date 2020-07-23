/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.RelacionamentoInstituicaoDTO;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * DAO utilizado para manter o relacionamento das pessoas com as institui��es.
 * 
 * @author Erico.Junior
 */
public interface RelacionamentoInstituicaoDAO {

	/**
	 * Lista as transi��es da pessoa e a institui��o respons�vel.
	 * @param pessoa A pessoa da qual se deseja as transi��es.
	 * @return A lista das transi��es.
	 * @throws br.com.bancoob.excecao.BancoobException Caso ocorra alguma exce��o.
	 */	
	List<RelacionamentoInstituicaoDTO> listarRelacionamentoInstituicao(PessoaCompartilhamento pessoa);
}
