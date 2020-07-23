/**
 * 
 */
package br.com.sicoob.capes.processamento.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;
import br.com.sicoob.capes.processamento.negocio.dto.InconsistenciaContaDTO;

/**
 * @author Erico.Junior
 * 
 */
public interface FechamentoBeneficiariosINSSDao extends CAPESProcessamentoDaoIF {

	/**
	 * Lista os beneficários para importação
	 * 
	 * @return lista com os beneficiários para importação na base da
	 *         cooperativa;
	 * @param numCooperativa
	 *            O número da cooperativa para qual os beneficiários serão
	 *            importados.
	 * @param dataProduto
	 *            A data do produto.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	List<BeneficiarioDTO> listarBeneficiariosImportacao(Integer numCooperativa,
			Date dataProduto) throws BancoobException;

	/**
	 * Grava o log da inconsistência na geração de conta do beneficiário.
	 * 
	 * @param numCooperativa
	 *            O número da cooperativa onde a inconsistência aconteceu.
	 * @param inconsistencia
	 *            O dto com as informações da inconsistência.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	void gravarLogInconsistenciaBeneficiario(Integer numCooperativa,
			InconsistenciaContaDTO inconsistencia) throws BancoobException;

}
