/*
 * SICOOB
 * 
 * GrupoEconomicoServico.java(br.com.sicoob.capes.api.negocio.servicos.GrupoEconomicoServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaGrupoEconomicoVO;

/**
 * @author Lucas.Borges
 */
public interface GrupoEconomicoServico extends CAPESApiServico {

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return grupo economico vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	GrupoEconomicoVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter pessoas por grupo instituicao.
	 *
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasPorGrupoInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém os grupos das pessoas por cpf/cnpj
	 * 
	 * @param List<String>
	 *            listaCpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém as pessoas de um determinado grupo.
	 * 
	 * @param idGrupoEconomico
	 * @return
	 * @deprecated usar {@link #obterPessoasPorGrupo(Integer, Integer)}
	 */
	@Deprecated
	List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico);

	/**
	 * Obtém as pessoas de um determinado grupo.
	 * 
	 * @param idGrupoEconomico
	 * @param idInstituicao
	 * @return
	 */
	List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico, Integer idInstituicao);

	/**
	 * Obtém os grupos das pessoas por cpf/cnpj
	 * 
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException;
}
