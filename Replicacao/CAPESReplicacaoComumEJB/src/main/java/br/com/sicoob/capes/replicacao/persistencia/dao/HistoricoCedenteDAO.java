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
	 * Inclui o hist�rico do cedente.
	 * @param dto DTO com as informa��es para o hist�rico do cedente.
	 * @param numCooperativa O n�mero da cooperativa
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	void incluirHistoricoCendente(HistoricoCedenteDTO dto, 
			Integer numCooperativa) throws BancoobException;
}
