package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;

public interface CAPESApiDao {
	
	/**
	 * Realiza a pesquisa utilizando a consulta e os criterios recebidos
	 * 
	 * @param criterios
	 *            os criterios de consulta
	 * @return lista com os resultados
	 */
	<V extends BancoobVo> List<V> pesquisar(ConsultaDto<? extends FiltroConsultaAPIAbstrato> criterios) throws BancoobException;

	/**
	 * Realiza a pesquisa paginada utilizando a consulta e os criterios recebidos
	 * 
	 * @param criterios
	 *            os criterios de consulta
	 * @return lista com os resultados
	 */
	<V extends BancoobVo> ConsultaDto<V> pesquisarPaginado(ConsultaDto<? extends FiltroConsultaAPIAbstrato> criterios) throws BancoobException;
}
