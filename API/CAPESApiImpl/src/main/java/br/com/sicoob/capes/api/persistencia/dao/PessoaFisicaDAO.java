package br.com.sicoob.capes.api.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;

/**
 * A Interface PessoaFisicaDAO.
 */
public interface PessoaFisicaDAO extends CAPESApiDaoIF<PessoaFisicaVO> {
	
	/**
	 * Obtém a pessoa fisica por CPF e instituição
	 * 
	 * @param cpf
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	PessoaFisicaVO obterPorCPFInstituicao(String cpf, Integer idInstituicao) throws BancoobException;

}