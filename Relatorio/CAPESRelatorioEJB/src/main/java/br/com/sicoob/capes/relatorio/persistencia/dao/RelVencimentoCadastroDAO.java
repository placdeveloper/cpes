package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelVencimentoCadastroDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelVencimentoCadastroVO;

/**
 * A Interface RelVencimentoCadastroDAO.
 */
public interface RelVencimentoCadastroDAO {

	/**
	 * Obter dados relatorio.
	 *
	 * @param proxy o valor de proxy
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<RelVencimentoCadastroVO> obterDadosRelatorio(RelVencimentoCadastroDTO proxy) throws BancoobException;
}