/* 
 * Sicoob
 * TipoEnderecoServico.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;

/**
 * Define as opera��es do servi�o de manipula��o de tipo de endere�o
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public interface TipoEnderecoServico extends
		CAPESCadastroCrudDominioServico<TipoEndereco> {
	
	public List<TipoEndereco> listarTipoEnderecoIncluindoAmbos(
			ConsultaDto<TipoEndereco> criterios) throws BancoobException;
}
