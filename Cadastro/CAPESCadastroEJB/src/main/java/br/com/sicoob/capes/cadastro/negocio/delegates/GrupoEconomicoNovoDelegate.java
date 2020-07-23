package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoEconomicoNovoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Business delegate para grupo economico novo.
 * 
 * @author paulo.stoppa
 */
public class GrupoEconomicoNovoDelegate extends CAPESCadastroCrudDelegate<GrupoEconomicoNovo, GrupoEconomicoNovoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoNovoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarGrupoEconomicoNovoServico();
	}
	
	/**
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 */
	public List<GrupoEconomicoNovo> listarPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) {
		return getServico().listarPorPessoaInstituicao(idPessoa, idInstituicao);
	}

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
	public void validarpessoa(GrupoEconomicoNovo grupoEconomico, PessoaCompartilhamento pessoa) throws BancoobException {
		getServico().validarPessoa(grupoEconomico, pessoa);
	}

}