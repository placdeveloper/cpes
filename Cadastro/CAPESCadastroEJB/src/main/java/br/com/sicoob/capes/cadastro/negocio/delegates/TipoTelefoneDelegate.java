/* 
 * Sicoob
 * TipoTelefoneDelegate.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoTelefoneServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;

/**
 * Business delegate para os tipos de telefone
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class TipoTelefoneDelegate extends
		CAPESCadastroCrudDominioDelegate<TipoTelefone, TipoTelefoneServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoTelefoneServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoTelefoneServico();
	}
	
	public List<TipoTelefone> listarTipoTelefoneIncluindoAmbos(ConsultaDto<TipoTelefone> criterios) throws BancoobException{
		return getServico().listarTipoTelefoneIncluindoAmbos(criterios);
	}
}
