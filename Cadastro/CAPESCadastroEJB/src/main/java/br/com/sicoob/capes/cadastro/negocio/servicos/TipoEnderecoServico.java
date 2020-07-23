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
 * Define as operações do serviço de manipulação de tipo de endereço
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
