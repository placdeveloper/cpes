package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelCompartilhamentoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelCompartilhamentoVO;

/**
 * A Interface RelCompartilhamentoDAO.
 */
public interface RelCompartilhamentoDAO {

	/**
	 * Obter dados relatorio.
	 *
	 * @param proxy o valor de proxy
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<RelCompartilhamentoVO> obterDadosRelatorio(RelCompartilhamentoDTO proxy) throws BancoobException;
}
