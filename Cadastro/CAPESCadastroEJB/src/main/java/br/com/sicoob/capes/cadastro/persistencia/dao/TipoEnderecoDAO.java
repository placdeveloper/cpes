/* 
 * Sicoob
 * TipoEnderecoDAO.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;

/**
 * Interface para o DAO de tipo de endereço
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public interface TipoEnderecoDAO extends
		CAPESCadastroCrudDominioDaoIF<TipoEndereco> {

	/**
	 * Consulta tipos de endereço de uma pessoa específica, incluindo o tipo
	 * ambos.
	 * 
	 * @param criterios
	 *            os critrerios de busca.
	 * @return {List<TipoEndereco>}, lista de tipos de endereço.
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public List<TipoEndereco> listarTipoEnderecoIncluindoAmbos(
			ConsultaDto<TipoEndereco> criterios) throws BancoobException;
}
