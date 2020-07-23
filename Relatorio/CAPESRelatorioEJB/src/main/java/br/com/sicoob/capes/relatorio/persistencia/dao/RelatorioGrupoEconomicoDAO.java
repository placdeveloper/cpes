package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioGrupoEconomicoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioGrupoEconomicoVO;

/**
 * A Interface RelatorioGrupoEconomicoDAO.
 */
public interface RelatorioGrupoEconomicoDAO {
	
	/**
	 * Consulta relatorio grupo economico.
	 *
	 * @param relatorioGrupoEconomicoDTO o valor de relatorio grupo economico dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<RelatorioGrupoEconomicoVO> consultaRelatorioGrupoEconomico(RelatorioGrupoEconomicoDTO relatorioGrupoEconomicoDTO) throws BancoobException;

}