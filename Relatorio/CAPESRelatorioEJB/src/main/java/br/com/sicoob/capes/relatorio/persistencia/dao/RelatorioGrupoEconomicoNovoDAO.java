package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioGrupoEconomicoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioGrupoEconomicoNovoVO;

/**
 * A Interface RelatorioGrupoEconomicoNovoDAO	.
 */
public interface RelatorioGrupoEconomicoNovoDAO {

	/**
	 * Consulta relatorio grupo economico.
	 *
	 * @param relatorioGrupoEconomicoDTO
	 *            o valor de relatorio grupo economico dto
	 * @return List
	 * @throws BancoobException
	 *             lançaa a exceção BancoobException
	 */
	List<RelatorioGrupoEconomicoNovoVO> consultaRelatorioGrupoEconomico(RelatorioGrupoEconomicoDTO relatorioGrupoEconomicoDTO)
			throws BancoobException;

}