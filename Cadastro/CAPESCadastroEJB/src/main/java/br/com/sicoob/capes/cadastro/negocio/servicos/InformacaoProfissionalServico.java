/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;

/**
 * @author Erico.Junior
 * 
 */
public interface InformacaoProfissionalServico extends
		CAPESCadastroCrudServico<InformacaoProfissional> {

	/**
	 * Lista as informações profissionais da pessoa cadastradas na instituição do usuário logado.
	 * @param pessoa A pessoa utilizada na pesquisa
	 * @return as informações profissionais da pessoa cadastradas na instituição do usuário logado.
	 * @throws BancoobException
	 */
	List<InformacaoProfissional> listar(Pessoa pessoa) throws BancoobException;

	/**
	 * Lista as informações profissionais da pessoa cadastradas na instituição informada.
	 * @param pessoa A pessoa utilizada na pesquisa
	 * @param idInstituicao o identificador da intituição
	 * @return as informações profissionais da pessoa cadastradas na instituição informada
	 * @throws BancoobException
	 */
	List<InformacaoProfissional> listar(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException;

	/**
	 * Inclui a informação profissional no Bancoob.
	 * @param objeto A informação a ser incluída.
	 * @return
	 * @throws BancoobException
	 */
	InformacaoProfissional incluirBancoob(InformacaoProfissional objeto) throws BancoobException;
	
	/**
	 * Inclui a informação profissional no Bancoob.
	 * @param objeto A informação a ser incluída.
	 * @param idInstituicao O identificador da instituição.
	 * @return a informação profissional incluída.
	 * @throws BancoobException
	 */
	InformacaoProfissional incluir(InformacaoProfissional objeto, Integer idInstituicao) 
			throws BancoobException;	
	
	/**
	 * Lista as informações profissionais cadastradas por matricula, empregador e instituição 
	 * informadas.
	 * @param informacao
	 * @return
	 */
	List<InformacaoProfissional> listarPorMatriculaEmpregador(InformacaoProfissional informacao);
}
