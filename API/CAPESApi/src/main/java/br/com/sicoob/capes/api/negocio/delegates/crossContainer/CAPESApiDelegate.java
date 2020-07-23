/*
 * SICOOB
 * 
 * CAPESApiDelegate.java(br.com.sicoob.capes.api.negocio.delegates.CAPESApiDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.delegates.CrossContainerDelegate;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ICAPESApiDelegate;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.servicos.CAPESApiServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;

/**
 * @author erico.junior
 * 
 */
public abstract class CAPESApiDelegate<T extends CAPESApiServico> extends CrossContainerDelegate<T> implements ICAPESApiDelegate {
	
	@SuppressWarnings("unchecked")
	@Override
	public CAPESApiServiceLocator getLocator() {
		return CAPESApiServiceLocator.getInstance();
	}
	
	/**
	 * Pesquisar por filtro
	 * 
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	public <V extends BancoobVo> List<V> pesquisar(FiltroConsultaAPIAbstrato filtro) throws BancoobException {
		return getServico().pesquisar(filtro);
	}

	/**
	 * Pesquisa paginada por filtro.
	 * 
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	public <K extends BancoobVo> ConsultaDto<K> pesquisarPaginado(FiltroConsultaAPIAbstrato filtro, int pagina, int tamanhoPagina) throws BancoobException {
		return getServico().pesquisarPaginado(filtro, pagina, tamanhoPagina);
	}

}