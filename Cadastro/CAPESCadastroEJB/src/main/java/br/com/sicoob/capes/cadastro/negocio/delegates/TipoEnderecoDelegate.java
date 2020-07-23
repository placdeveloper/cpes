/* 
 * Sicoob
 * TipoEnderecoDelegate.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoEnderecoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;

/**
 * Business delegate para os tipos de endereço
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class TipoEnderecoDelegate extends
		CAPESCadastroCrudDominioDelegate<TipoEndereco, TipoEnderecoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEnderecoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoEnderecoServico();
	}
	
	public List<TipoEndereco> listarTipoEnderecoIncluindoAmbos(ConsultaDto<TipoEndereco> criterios) throws BancoobException{
		return getServico().listarTipoEnderecoIncluindoAmbos(criterios);
	}

}
