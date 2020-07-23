/*
 * SICOOB
 * 
 * CAPESApiServico.java(br.com.sicoob.capes.api.negocio.servicos.CAPESApiServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.servicos.BancoobServico;
import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;

/**
 * @author erico.junior
 *
 */
public interface CAPESApiServico extends BancoobServico {
	
	/**
	 * Realiza a pesquisa utilizando a consulta e os criterios recebidos
	 * 
	 * @param criterios
	 *            os criterios de consulta
	 * @return lista com os resultados
	 */
	<K extends BancoobVo> List<K> pesquisar(FiltroConsultaAPIAbstrato filtro) throws BancoobException;
	
	/**
	 * Realiza a pesquisa paginada utilizando a consulta e os criterios recebidos
	 * 
	 * @param criterios
	 *            os criterios de consulta
	 * @return lista com os resultados
	 */
	<K extends BancoobVo> ConsultaDto<K> pesquisarPaginado(FiltroConsultaAPIAbstrato filtro, int pagina, int tamanhoPagina) throws BancoobException;

}
