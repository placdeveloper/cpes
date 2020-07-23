/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.FuncionarioDTO;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Define as opera��es do servi�o de manipula��o de Funcionario.
 * 
 * @author Juan.Damasceno
 */
public interface FuncionarioServico extends
		CAPESCadastroCrudServico<Funcionario> {

	/**
	 * Listar ativos por instituicao.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return List
	 */
	List<Funcionario> listarAtivosPorInstituicao(ConsultaDto<Funcionario> consultaDto);
	
	/**
	 * Listar gerentes ativos.
	 *
	 * @param instituicao o valor de instituicao
	 * @return List
	 */
	List<Funcionario> listarGerentesAtivos(Instituicao instituicao);
	
	/**
	 * Listar gerentes.
	 *
	 * @param instituicao o valor de instituicao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<Funcionario> listarGerentes(Instituicao instituicao) throws BancoobException;
	
	/**
	 * Pesquisar por instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	ConsultaDto<Funcionario> pesquisarPorInstituicao(ConsultaDto<Funcionario> criterios)
			throws BancoobException;
	
	/**
	 * O m�todo Excluir.
	 *
	 * @param funcionario o valor de funcionario
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void excluir(Funcionario funcionario) throws BancoobException;
	
	/**
	 * Recupera a quantidade de funcion�rios por n�cleo.
	 * @param nucleo O n�cleo utilizado como filtro.
	 * @return a quantidade de funcion�rios por n�cleo.
	 */
	Long obterQuantidadeFuncionariosPorNucleo(Nucleo nucleo);

	/**
	 * Listar gerentes.
	 *
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<Funcionario> listarGerentes() throws BancoobException;

	/**
	 * Listar gerentes ativos.
	 *
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<Funcionario> listarGerentesAtivos() throws BancoobException;

	/**
	 * Obtem o gerente da pessoa em uma determinada instituicao.
	 * @param pessoaCompartilhamento
	 * @param idInstituicao
	 * @return
	 */
	List<Funcionario> obterGerente(PessoaCompartilhamento pessoaCompartilhamento, Integer idInstituicao) throws BancoobException;

	List<FuncionarioDTO> obterListaFuncionarioGerente(Integer idInstituicao) throws BancoobException;
	
}