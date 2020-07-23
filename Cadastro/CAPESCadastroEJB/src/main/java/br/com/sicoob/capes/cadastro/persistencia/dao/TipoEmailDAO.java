/* 
 * Sicoob
 * TipoEmailDAO.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;

/**
 * Interface para o DAO de tipo de e-mail
 *
 * 05/05/2011
 * @author Rodrigo.Chaves
 */
public interface TipoEmailDAO extends CAPESCadastroCrudDominioDaoIF<TipoEmail> {
	
	public List<TipoEmail> listarTipoEmailIncluindoAmbos(
			ConsultaDto<TipoEmail> criterios) throws BancoobException;
}
