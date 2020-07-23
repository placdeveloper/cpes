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
 * Interface para o DAO de tipo de endere�o
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public interface TipoEnderecoDAO extends
		CAPESCadastroCrudDominioDaoIF<TipoEndereco> {

	/**
	 * Consulta tipos de endere�o de uma pessoa espec�fica, incluindo o tipo
	 * ambos.
	 * 
	 * @param criterios
	 *            os critrerios de busca.
	 * @return {List<TipoEndereco>}, lista de tipos de endere�o.
	 * @throws BancoobException
	 *             lan�a a exce��o BancoobException
	 */
	public List<TipoEndereco> listarTipoEnderecoIncluindoAmbos(
			ConsultaDto<TipoEndereco> criterios) throws BancoobException;
}
