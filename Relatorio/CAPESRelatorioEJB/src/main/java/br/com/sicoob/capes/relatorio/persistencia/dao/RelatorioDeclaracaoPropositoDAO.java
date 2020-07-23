package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioDeclaracaoPropositoVO;

/**
 * A Interface RelatorioDeclaracaoPropositoDAO.
 */
public interface RelatorioDeclaracaoPropositoDAO {

	/**
	 * Realiza a consulta do relatorio de declaracao de proposito.
	 * 
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<RelatorioDeclaracaoPropositoVO> obterDadosRelatorioDeclaracaoProposito(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
}