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
	 * Lista os benefic�rios para importa��o
	 * 
	 * @return lista com os benefici�rios para importa��o na base da
	 *         cooperativa;
	 * @param numCooperativa
	 *            O n�mero da cooperativa para qual os benefici�rios ser�o
	 *            importados.
	 * @param dataProduto
	 *            A data do produto.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	List<BeneficiarioDTO> listarBeneficiariosImportacao(Integer numCooperativa,
			Date dataProduto) throws BancoobException;

	/**
	 * Grava o log da inconsist�ncia na gera��o de conta do benefici�rio.
	 * 
	 * @param numCooperativa
	 *            O n�mero da cooperativa onde a inconsist�ncia aconteceu.
	 * @param inconsistencia
	 *            O dto com as informa��es da inconsist�ncia.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	void gravarLogInconsistenciaBeneficiario(Integer numCooperativa,
			InconsistenciaContaDTO inconsistencia) throws BancoobException;

}
