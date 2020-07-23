/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoEconomicoPessoaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;

/**
 * Business delegate para {@link GrupoEconomicoPessoa}.  
 */
public class GrupoEconomicoPessoaDelegate extends
	CAPESCadastroCrudDelegate<GrupoEconomicoPessoa, GrupoEconomicoPessoaServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoPessoaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarGrupoEconomicoPessoaServico();
	}
	
	/**
	 * O método Atualizar grupo.
	 *
	 * @param grupoEconomicoPessoa o valor de grupo economico pessoa
	 * @param grupo o valor de grupo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void atualizarGrupo(GrupoEconomicoPessoa grupoEconomicoPessoa,
			GrupoEconomico grupo, String idUsuario) throws BancoobException {
		getServico().atualizarGrupo(grupoEconomicoPessoa, grupo, idUsuario);
	}

	/**
	 * Pesquisar numero registros.
	 *
	 * @return Long
	 */
	public Long pesquisarNumeroRegistros(Integer idGrupo) throws BancoobException{
		return getServico().pesquisarNumeroRegistros(idGrupo);
	}
}
