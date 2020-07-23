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
	 * Atualiza o cadastro do benefici�rio.
	 * @param dto O dto com as informa��es do benefici�rio.
	 * @param dataProduto A data do produto INSS.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	void atualizarCadastroBeneficiario(BeneficiarioDTO dto,	DateTimeDB dataProduto) throws BancoobException;
}
