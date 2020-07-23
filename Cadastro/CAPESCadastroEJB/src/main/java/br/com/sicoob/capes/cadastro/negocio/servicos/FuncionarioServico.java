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
 * Define as operações do serviço de manipulação de Funcionario.
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
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<Funcionario> listarGerentes(Instituicao instituicao) throws BancoobException;
	
	/**
	 * Pesquisar por instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ConsultaDto<Funcionario> pesquisarPorInstituicao(ConsultaDto<Funcionario> criterios)
			throws BancoobException;
	
	/**
	 * O método Excluir.
	 *
	 * @param funcionario o valor de funcionario
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void excluir(Funcionario funcionario) throws BancoobException;
	
	/**
	 * Recupera a quantidade de funcionários por núcleo.
	 * @param nucleo O núcleo utilizado como filtro.
	 * @return a quantidade de funcionários por núcleo.
	 */
	Long obterQuantidadeFuncionariosPorNucleo(Nucleo nucleo);

	/**
	 * Listar gerentes.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<Funcionario> listarGerentes() throws BancoobException;

	/**
	 * Listar gerentes ativos.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
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