/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.ConsultaAlteracaoDocumentoVO;

/**
 * @author erico.junior
 */
public interface AlterarDocumentoPessoaDAO {

	/**
	 * Consulta os vinculos da pessoa a partir do documento informado, podendo
	 * ser um CPF ou CNPJ.
	 * 
	 * @param cpfCnpj
	 *            O documento para a consulta.
	 * @return Os vínculos da pessoa consultada para o documento informado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	List<ConsultaAlteracaoDocumentoVO> consultarVinculosDocumento(
			String cpfCnpj) throws BancoobException;
}
