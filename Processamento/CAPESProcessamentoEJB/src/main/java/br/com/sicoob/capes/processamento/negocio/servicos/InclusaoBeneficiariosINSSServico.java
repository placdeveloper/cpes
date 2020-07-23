/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;

/**
 * @author Erico.Junior
 * 
 */
public interface InclusaoBeneficiariosINSSServico extends
		CAPESProcessamentoServico {

	/**
	 * Atualiza o cadastro do beneficiário.
	 * @param dto O dto com as informações do beneficiário.
	 * @param dataProduto A data do produto INSS.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	void atualizarCadastroBeneficiario(BeneficiarioDTO dto,	DateTimeDB dataProduto) throws BancoobException;
}
