/* 
 * Sicoob
 * TipoTelefoneDAO.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;

/**
 * Interface para o DAO de tipo de telefone
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 */
public interface TipoTelefoneDAO extends CAPESCadastroCrudDominioDaoIF<TipoTelefone> {
	
	
	public List<TipoTelefone> listarTipoTelefoneIncluindoAmbos(
			ConsultaDto<TipoTelefone> criterios) throws BancoobException;
}
