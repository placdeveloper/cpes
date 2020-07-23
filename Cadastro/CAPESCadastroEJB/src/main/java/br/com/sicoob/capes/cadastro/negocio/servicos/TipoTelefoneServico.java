/* 
 * Sicoob
 * TipoTelefoneServico.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;

/**
 * Define as operações do serviço de manipulação de tipo de telefone
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public interface TipoTelefoneServico extends
		CAPESCadastroCrudDominioServico<TipoTelefone> {
	
	public List<TipoTelefone> listarTipoTelefoneIncluindoAmbos(
			ConsultaDto<TipoTelefone> criterios) throws BancoobException;
}
