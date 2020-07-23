package br.com.sicoob.capes.relatorio.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.vo.FormularioVisitaVO;

public interface RelFormularioVisitaDAO {
	
	/**
	 * Obter dados relatorio de Pessoa Fis�ca.
	 *
	 * @param idPessoaCompartilhamento
	 * @return FormularioVisitaVO
	 * @throws BancoobException
	 */
	FormularioVisitaVO obterDadosFormularioVisitaPF(Long idPessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Obter dados relatorio de Pessoa Jur�dica.
	 *
	 * @param idPessoaCompartilhamento
	 * @return FormularioVisitaVO
	 * @throws BancoobException
	 */
	FormularioVisitaVO obterDadosFormularioVisitaPJ(Long idPessoaCompartilhamento) throws BancoobException;

}
