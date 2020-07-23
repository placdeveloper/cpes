/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.FuncionarioDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.FuncionarioServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Business delegate para funcionario.  
 * @author juan.damasceno
 */
public class FuncionarioDelegate extends
	CAPESCadastroCrudDelegate<Funcionario, FuncionarioServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FuncionarioServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarFuncionarioServico();
	}
	
	/**
	 * Listar ativos por instituicao.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return List
	 */
	public List<Funcionario> listarAtivosPorInstituicao(ConsultaDto<Funcionario> consultaDto) {
		return getServico().listarAtivosPorInstituicao(consultaDto);
	}
	
	/**
	 * Listar gerentes ativos.
	 *
	 * @param instituicao o valor de instituicao
	 * @return List
	 */
	public List<Funcionario> listarGerentesAtivos(Instituicao instituicao) {
		return getServico().listarGerentesAtivos(instituicao);
	}

	/**
	 * Listar gerentes.
	 *
	 * @param instituicao o valor de instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<Funcionario> listarGerentes(Instituicao instituicao) throws BancoobException {
		return getServico().listarGerentes(instituicao);
	}
	
	/**
	 * Listar gerentes.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<Funcionario> listarGerentes() throws BancoobException {
		return getServico().listarGerentes();
	}
	
	/**
	 * Pesquisar por instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ConsultaDto<Funcionario> pesquisarPorInstituicao(ConsultaDto<Funcionario> criterios)
			throws BancoobException {
		return getServico().pesquisarPorInstituicao(criterios);
	}
	
	/**
	 * O método Excluir.
	 *
	 * @param funcionario o valor de funcionario
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void excluir(Funcionario funcionario) throws BancoobException {
		getServico().excluir(funcionario);
	}

	/**
	 * Listar gerentes ativos.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<Funcionario> listarGerentesAtivos() throws BancoobException {
		return getServico().listarGerentesAtivos();
	}

	/**
	 * Obtem o gerente da pessoa em uma determinada instituicao.
	 * @param pessoaCompartilhamento
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException 
	 */
	public List<Funcionario> obterGerente(PessoaCompartilhamento pessoaCompartilhamento, Integer idInstituicao) throws BancoobException {
		return getServico().obterGerente(pessoaCompartilhamento, idInstituicao);
	}

	/**
	 * Obtem os gerentes por instituicao.
	 * 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<FuncionarioDTO> obterListaFuncionarioGerente(Integer idInstituicao) throws BancoobException {
		return getServico().obterListaFuncionarioGerente(idInstituicao);
	}
	
}
