package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * A Interface GrupoEconomicoPessoaServico.
 */
public interface GrupoEconomicoPessoaServico extends
		CAPESCadastroCrudServico<GrupoEconomicoPessoa> {

	/**
	 * Recupera os integrantes de grupos econômicos por IDs de
	 * {@link PessoaInstituicao}
	 * 
	 * @param idsPessoaInstituicao
	 *            os IDs das pessoas que se deseja recuperar as participações em
	 *            grupos
	 * @return uma lista de {@link GrupoEconomicoPessoa}
	 */
	List<GrupoEconomicoPessoa> pesquisarPorPessoaInstituicao(Integer... idsPessoaInstituicao)
			throws BancoobException;

	/**
	 * Atualiza o grupo economico das pessoas de acordo com a consulta com o grupo passado
	 * @param grupoEconomicoPessoa
	 * @param grupo
	 * @return
	 * @throws BancoobException
	 */
	void atualizarGrupo(GrupoEconomicoPessoa grupoEconomicoPessoa, GrupoEconomico grupo, String idUsuario)
			throws BancoobException;

	Long pesquisarNumeroRegistros(Integer idGrupo) throws BancoobException;

}