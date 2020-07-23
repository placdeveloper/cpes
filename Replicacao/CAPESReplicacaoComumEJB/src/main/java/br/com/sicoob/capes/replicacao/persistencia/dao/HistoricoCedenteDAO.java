/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.replicacao.negocio.dto.HistoricoCedenteDTO;

/**
 * @author erico.junior
 */
public interface HistoricoCedenteDAO {

	/**
	 * Inclui o histórico do cedente.
	 * @param dto DTO com as informações para o histórico do cedente.
	 * @param numCooperativa O número da cooperativa
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	void incluirHistoricoCendente(HistoricoCedenteDTO dto, 
			Integer numCooperativa) throws BancoobException;
}
