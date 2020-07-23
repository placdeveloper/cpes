/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.FuncionarioDTO;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * Interface para o DAO de funcionario.
 * 
 * @author juan.damasceno
 */
public interface FuncionarioDAO extends
		CAPESCadastroCrudDaoIF<Funcionario> {

	/**
	 * Listar ativos por instituicao.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return List
	 */
	List<Funcionario> listarAtivosPorInstituicao(ConsultaDto<Funcionario> consultaDto);
	
	/**
	 * Recupera a quantidade de funcion�rios por n�cleo.
	 * @param nucleo O n�cleo utilizado como filtro.
	 * @return a quantidade de funcion�rios por n�cleo.
	 */
	Long obterQuantidadeFuncionariosPorNucleo(Nucleo nucleo);
	
	List<FuncionarioDTO> obterListaFuncionarioGerente(Integer idInstituicao);
	
}