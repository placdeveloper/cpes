package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;

/**
 * A Interface GrupoEconomicoPessoaDAO.
 */
public interface GrupoEconomicoPessoaDAO extends
		CAPESCadastroCrudDaoIF<GrupoEconomicoPessoa> {

	/**
	 * Recupera os integrantes de grupos econômicos por IDs de
	 * {@link br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao}
	 * 
	 * @param idsPessoaInstituicao
	 *            os IDs das pessoas que se deseja recuperar as participações em
	 *            grupos
	 * @return uma lista de {@link GrupoEconomicoPessoa}
	 */
	List<GrupoEconomicoPessoa> pesquisarPorPessoaInstituicao(Integer... idsPessoaInstituicao)
			throws BancoobException;

	Long pesquisarNumeroRegistros(Integer idGrupo) throws BancoobException;

}