package br.com.sicoob.capes.relatorio.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.ValoresBensVO;

public interface RelatorioBemDAO {

	/**
	 * Obtem os valores dos bens vigentes
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public ValoresBensVO obterValoresBensPessoa(Long idPessoaCompartilhamento) throws BancoobException;
	
}
