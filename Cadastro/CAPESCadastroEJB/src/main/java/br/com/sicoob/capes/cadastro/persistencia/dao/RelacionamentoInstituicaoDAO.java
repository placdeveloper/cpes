/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.RelacionamentoInstituicaoDTO;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * DAO utilizado para manter o relacionamento das pessoas com as instituições.
 * 
 * @author Erico.Junior
 */
public interface RelacionamentoInstituicaoDAO {

	/**
	 * Lista as transições da pessoa e a instituição responsável.
	 * @param pessoa A pessoa da qual se deseja as transições.
	 * @return A lista das transições.
	 * @throws br.com.bancoob.excecao.BancoobException Caso ocorra alguma exceção.
	 */	
	List<RelacionamentoInstituicaoDTO> listarRelacionamentoInstituicao(PessoaCompartilhamento pessoa);
}
