package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaGrupoEconomicoVO;

/**
 * interfacee repons�vel por consultas relacionada com o Grupo Econ�mico
 * 
 * @author Marcelo.Onofre
 */
public interface IGrupoEconomicoDelegate extends ICAPESApiDelegate {

	/**
	 * M�todo que consulta o grupo econ�mico
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return lista de {@link GrupoEconomicoVO}
	 * @throws BancoobException
	 */
	GrupoEconomicoVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter pessoas por grupo instituicao.
	 *
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return List
	 * @throws BancoobException
	 *             lan�a a exce��o BancoobException
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasPorGrupoInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obt�m os grupos das pessoas por cpf/cnpj
	 * 
	 * @param List<String>
	 *            listaCpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obt�m as pessoas de um determinado grupo.
	 * 
	 * @param idGrupoEconomico
	 * @return
	 * @throws BancoobException
	 * @deprecated usar {@link #obterPessoasPorGrupo(Integer, Integer)}
	 */
	@Deprecated
	List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico) throws BancoobException;

	/**
	 * Obt�m as pessoas de um determinado grupo.
	 * 
	 * @param idGrupoEconomico
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico, Integer idInstituicao) throws BancoobException;

	/**
	 * Obt�m os grupos das pessoas por cpf/cnpj
	 * 
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException;

}