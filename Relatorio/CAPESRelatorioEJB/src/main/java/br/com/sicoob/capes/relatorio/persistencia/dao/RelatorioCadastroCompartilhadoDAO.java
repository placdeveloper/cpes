package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioCadastroCompartilhadoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioCadastroCompartilhadoVO;

/**
 * A Interface RelCompartilhamentoDAO.
 */
public interface RelatorioCadastroCompartilhadoDAO {

	/**
	 * Obter dados relatorio.
	 *
	 * @param proxy o valor de proxy
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<RelatorioCadastroCompartilhadoVO> obterDadosRelatorio(RelatorioCadastroCompartilhadoDTO proxy) throws BancoobException;
}
