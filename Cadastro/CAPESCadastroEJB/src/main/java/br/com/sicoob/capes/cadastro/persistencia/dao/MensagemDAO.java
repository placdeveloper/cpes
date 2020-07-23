/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.Mensagem;

/**
 * Interface para o DAO de Mensagem.
 * 
 * @author juan.damasceno
 */
public interface MensagemDAO extends CAPESCadastroCrudDaoIF<Mensagem> {

	/**
	 * Lista as mensagens que que estão vencidas há 30 dias.
	 * @param criterios Os critérios utilizados como filtro para as rendas vencidas. 
	 * @return as mensagens que que estão vencidas há 30 dias.
	 * @throws BancoobException
	 */
	List<Mensagem> listarVencidas(ConsultaDto<Mensagem> criterios) throws BancoobException;
	
}