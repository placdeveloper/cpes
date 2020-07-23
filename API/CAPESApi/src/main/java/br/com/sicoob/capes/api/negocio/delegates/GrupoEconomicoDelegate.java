/*
 * SICOOB
 * 
 * GrupoEconomicoDelegate.java(br.com.sicoob.capes.api.negocio.delegates.GrupoEconomicoDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IGrupoEconomicoDelegate;
import br.com.sicoob.capes.api.negocio.servicos.GrupoEconomicoServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaGrupoEconomicoVO;

/**
 * Classe repons�vel por consultas relacionada com o Grupo Econ�mico
 * @author Marcelo.Onofre
 */
public class GrupoEconomicoDelegate extends CAPESApiDelegate<GrupoEconomicoServico> implements IGrupoEconomicoDelegate {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarGrupoEconomicoServico();
	}

	/**
	 * M�todo que consulta o grupo econ�mico 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return lista de {@link GrupoEconomicoVO}
	 * @throws BancoobException
	 */
	public GrupoEconomicoVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterByPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * Obter pessoas por grupo instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public List<PessoaGrupoEconomicoVO> obterPessoasPorGrupoInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterPessoasPorGrupoInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obt�m os grupos das pessoas por cpf/cnpj
	 * @param List<String> listaCpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException {
		return getServico().obterGruposPorCpfCnpj(listaCpfCnpj, idInstituicao);
	}
	
	/**
	 * Obt�m as pessoas de um determinado grupo.
	 * 
	 * @param idGrupoEconomico
	 * @return
	 * @throws BancoobException
	 * @deprecated usar {@link #obterPessoasPorGrupo(Integer, Integer)}
	 */
	@Deprecated
	public List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico) throws BancoobException {
		return getServico().obterPessoasPorGrupo(idGrupoEconomico);
	}
	
	/**
	 * Obt�m as pessoas de um determinado grupo.
	 * @param idGrupoEconomico
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico, Integer idInstituicao) throws BancoobException {
		return getServico().obterPessoasPorGrupo(idGrupoEconomico, idInstituicao);
	}
	
	
	/**
	 * Obt�m os grupos das pessoas por cpf/cnpj
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return getServico().obterGruposPorCpfCnpj(cpfCnpj, idInstituicao);
	}
}