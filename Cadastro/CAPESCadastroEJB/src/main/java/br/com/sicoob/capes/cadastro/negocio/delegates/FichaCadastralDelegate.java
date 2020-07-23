/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;
import br.com.sicoob.capes.cadastro.negocio.servicos.FichaCadastralServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;

/**
 * Business delegate a consulta da ficha cadastral  
 * @author juan.damasceno
 */
public class FichaCadastralDelegate extends
	CAPESCadastroDelegate<FichaCadastralServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FichaCadastralServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarFichaCadastralServico();
	}
	
	/**
	 * Listar.
	 *
	 * @param <T> o tipo generico
	 * @param clazz o valor de clazz
	 * @param consultaDtoCUC o valor de consulta dto cuc
	 * @return List
	 */
	public <T> List<T> listar(Class<T> clazz, ConsultaDtoCUC<?> consultaDtoCUC){
		return getServico().listar(clazz, consultaDtoCUC);
	}

	/**
	 * Obter entidade autorizacao.
	 *
	 * @param <E> o tipo generico
	 * @param dto o valor de dto
	 * @return E
	 */
	@SuppressWarnings("unchecked")
	public <E> E obterEntidadeAutorizacao(ConsultaAutorizacaoDTO dto) {
		return (E)getServico().obterEntidadeAutorizacao(dto);
	}
}
