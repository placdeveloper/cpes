/* 
 * Sicoob
 * TipoEmailDelegate.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoEmailServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;

/**
 * Business delegate para os tipos de e-mail
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class TipoEmailDelegate extends
		CAPESCadastroCrudDominioDelegate<TipoEmail, TipoEmailServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEmailServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoEmailServico();
	}
	
	public List<TipoEmail> listarTipoEmailIncluindoAmbos(ConsultaDto<TipoEmail> criterios) throws BancoobException{
		return getServico().listarTipoEmailIncluindoAmbos(criterios);
	}
}
