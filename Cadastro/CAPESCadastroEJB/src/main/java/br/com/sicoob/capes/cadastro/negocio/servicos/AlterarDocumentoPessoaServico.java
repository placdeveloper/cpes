/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.AlteracaoDocumentoPessoaDTO;
import br.com.sicoob.capes.cadastro.negocio.vo.ConsultaAlteracaoDocumentoVO;

/**
 * Serviço para alteração do cpf/cnpj da pessoa.
 * 
 * @author Erico.Junior 
 */
public interface AlterarDocumentoPessoaServico extends CAPESCadastroServico {

	/**
	 * Altera o cpf/cnpj da pessoa.
	 * @param dto O dto com os dados para a alteração.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	void alterarDocumentoPessoa(AlteracaoDocumentoPessoaDTO dto) throws BancoobException;
	
	
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
