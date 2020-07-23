package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface ICAPESApiDelegate {

	/**
	 * Pesquisar por filtro
	 * 
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	<V extends BancoobVo> List<V> pesquisar(FiltroConsultaAPIAbstrato filtro) throws BancoobException;

	/**
	 * Pesquisa paginada por filtro.
	 * 
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	<K extends BancoobVo> ConsultaDto<K> pesquisarPaginado(FiltroConsultaAPIAbstrato filtro, int pagina, int tamanhoPagina)
			throws BancoobException;

}