/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaEmOutroGrupoException;
import br.com.sicoob.capes.cadastro.negocio.vo.StatusTransferenciaGrupoEconomicoVO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Define as opera��es do servi�o de manipula��o de GrupoEconomico.
 * 
 * @author Juan.Damasceno
 */
public interface GrupoEconomicoServico extends
		CAPESCadastroCrudServico<GrupoEconomico> {

	/**
	 * Listar por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 */
	List<GrupoEconomico> listarPorPessoa(Pessoa pessoa);

	/**
	 * Verifica se a pessoa j� faz parte de algum outro grupo econ�mico na mesma institui��o
	 * 
	 * @param grupoEconomico o grupo econ�mico ao qual se deseja vincular a pessoa
	 * @param pessoa a pessoa que se deseja validar
	 * @throws PessoaEmOutroGrupoException caso a pessoa j� esteja em outro grupo econ�mico
	 */
	void validarPessoa(GrupoEconomico grupoEconomico, PessoaInstituicao pessoa)
			throws BancoobException;
	
	/**
	 * 
	 * @return
	 * @throws BancoobException
	 */
	List<StatusTransferenciaGrupoEconomicoVO> obterStatusTransferenciaGrupoEconomico() throws BancoobException;

	/**
	 * Cria automatica atraves de Job a cria��o automatica de Grupos Economicos para 
	 * pessoa fisica e juridica.
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoEconomico> gerarGruposEconomicos() throws BancoobException;
	
	/**
	 * 
	 * @param idInstituicao
	 * @param idPessoa
	 * @return
	 */
	List<GrupoEconomico> obterListaGrupoEconomico(Integer idInstituicao);

}