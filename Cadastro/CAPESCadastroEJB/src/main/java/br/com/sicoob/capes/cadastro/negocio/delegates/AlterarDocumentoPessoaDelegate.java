/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.AlteracaoDocumentoPessoaDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.AlterarDocumentoPessoaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.ConsultaAlteracaoDocumentoVO;

/**
 * Delegate utilizada para alteração do documento da pessoa.
 * 
 * @author Erico.Junior
 */
public class AlterarDocumentoPessoaDelegate extends
		CAPESCadastroDelegate<AlterarDocumentoPessoaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AlterarDocumentoPessoaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarAlterarDocumentoPessoaServico();
	}

	/**
	 * Altera o cpf/cnpj da pessoa.
	 * 
	 * @param dto
	 *            O dto com os dados para a alteração.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void alterarDocumentoPessoa(AlteracaoDocumentoPessoaDTO dto) throws BancoobException {
		getServico().alterarDocumentoPessoa(dto);
	}

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
	public List<ConsultaAlteracaoDocumentoVO> consultarVinculosDocumento(String cpfCnpj)
			throws BancoobException {
		return getServico().consultarVinculosDocumento(cpfCnpj);
	}
}
