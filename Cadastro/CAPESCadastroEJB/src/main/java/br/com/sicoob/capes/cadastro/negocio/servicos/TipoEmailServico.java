/* 
 * Sicoob
 * TipoEmailServico.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;

/**
 * Define as operações do serviço de manipulação de tipo de e-mail
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public interface TipoEmailServico extends CAPESCadastroCrudDominioServico<TipoEmail> {

	public List<TipoEmail> listarTipoEmailIncluindoAmbos(
			ConsultaDto<TipoEmail> criterios) throws BancoobException;
}
