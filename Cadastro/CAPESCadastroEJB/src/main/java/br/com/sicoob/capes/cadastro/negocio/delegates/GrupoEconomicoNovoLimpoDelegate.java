package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoEconomicoNovoLimpoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovoLimpo;

/**
 * Business delegate para grupo economico novo.
 * 
 * @author paulo.stoppa
 */
public class GrupoEconomicoNovoLimpoDelegate extends CAPESCadastroCrudDelegate<GrupoEconomicoNovoLimpo, GrupoEconomicoNovoLimpoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoNovoLimpoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarGrupoEconomicoNovoLimpoServico();
	}
	
	/**
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 */
//	public List<GrupoEconomicoNovo> listarPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) {
//		return getServico().listarPorPessoaInstituicao(idPessoa, idInstituicao);
//	}

	/**
	 * O método Validarpessoa.
	 *
	 * @param grupoEconomico
	 *            o valor de grupo economico
	 * @param pessoa
	 *            o valor de pessoa
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
//	public void validarpessoa(GrupoEconomicoNovo grupoEconomico, PessoaCompartilhamento pessoa) throws BancoobException {
//		getServico().validarPessoa(grupoEconomico, pessoa);
//	}

}