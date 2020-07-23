/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * Interface para o DAO de fonte de rendas.
 * 
 * @author Erico.Junior
 */
public interface FonteRendaDAO extends EntidadeCadastroDaoIF<FonteRenda> {

	/**
	 * Lista as rendas que possuem a dataValidadeRenda menor que dataLimite informada.
	 * 
	 * @param criterios
	 *            Os critérios utilizados como filtro para as rendas vencidas.
	 * @return Lista com as rendas com a dataValidadeRenda menor que dataLimite informada.
	 * @throws BancoobException
	 */
	List<FonteRenda> listarVencidas(ConsultaDto<FonteRenda> criterios)
			throws BancoobException;
}
