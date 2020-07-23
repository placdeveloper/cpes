package br.com.sicoob.capes.api.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;

/**
 * A Interface PessoaFisicaDAO.
 */
public interface PessoaFisicaDAO extends CAPESApiDaoIF<PessoaFisicaVO> {
	
	/**
	 * Obt�m a pessoa fisica por CPF e institui��o
	 * 
	 * @param cpf
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	PessoaFisicaVO obterPorCPFInstituicao(String cpf, Integer idInstituicao) throws BancoobException;

}