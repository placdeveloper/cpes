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
	 * Lista as informa��es profissionais da pessoa cadastradas na institui��o do usu�rio logado.
	 * @param pessoa A pessoa utilizada na pesquisa
	 * @return as informa��es profissionais da pessoa cadastradas na institui��o do usu�rio logado.
	 * @throws BancoobException
	 */
	List<InformacaoProfissional> listar(Pessoa pessoa) throws BancoobException;

	/**
	 * Lista as informa��es profissionais da pessoa cadastradas na institui��o informada.
	 * @param pessoa A pessoa utilizada na pesquisa
	 * @param idInstituicao o identificador da intitui��o
	 * @return as informa��es profissionais da pessoa cadastradas na institui��o informada
	 * @throws BancoobException
	 */
	List<InformacaoProfissional> listar(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException;

	/**
	 * Inclui a informa��o profissional no Bancoob.
	 * @param objeto A informa��o a ser inclu�da.
	 * @return
	 * @throws BancoobException
	 */
	InformacaoProfissional incluirBancoob(InformacaoProfissional objeto) throws BancoobException;
	
	/**
	 * Inclui a informa��o profissional no Bancoob.
	 * @param objeto A informa��o a ser inclu�da.
	 * @param idInstituicao O identificador da institui��o.
	 * @return a informa��o profissional inclu�da.
	 * @throws BancoobException
	 */
	InformacaoProfissional incluir(InformacaoProfissional objeto, Integer idInstituicao) 
			throws BancoobException;	
	
	/**
	 * Lista as informa��es profissionais cadastradas por matricula, empregador e institui��o 
	 * informadas.
	 * @param informacao
	 * @return
	 */
	List<InformacaoProfissional> listarPorMatriculaEmpregador(InformacaoProfissional informacao);
}
