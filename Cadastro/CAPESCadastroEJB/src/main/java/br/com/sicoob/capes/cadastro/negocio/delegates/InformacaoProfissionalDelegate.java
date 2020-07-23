/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;

/**
 * @author Erico.Junior
 * 
 */
public class InformacaoProfissionalDelegate
		extends	CAPESCadastroCrudDelegate<InformacaoProfissional, InformacaoProfissionalServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoProfissionalServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarInformacaoProfissionalServico();
	}

	/**
	 * Listar.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public List<InformacaoProfissional> listar(Pessoa pessoa) throws BancoobException {
		return getServico().listar(pessoa);
	}
	
	/**
	 * Listar.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public List<InformacaoProfissional> listar(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException {
		return getServico().listar(pessoa, idInstituicao);
	}

	/**
	 * Incluir bancoob.
	 *
	 * @param informacao o valor de informacao
	 * @return InformacaoProfissional
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public InformacaoProfissional incluirBancoob(InformacaoProfissional informacao)
			throws BancoobException {
		return getServico().incluirBancoob(informacao);
	}

	/**
	 * Listar por matricula empregador.
	 *
	 * @param informacao o valor de informacao
	 * @return List
	 */
	public List<InformacaoProfissional> listarPorMatriculaEmpregador(
			InformacaoProfissional informacao) {
		return getServico().listarPorMatriculaEmpregador(informacao);
	}
	
	/**
	 * Inclui a informa��o profissional no Bancoob.
	 * @param objeto A informa��o a ser inclu�da.
	 * @param idInstituicao O identificador da institui��o.
	 * @return a informa��o profissional inclu�da.
	 * @throws BancoobException
	 */
	public InformacaoProfissional incluir(InformacaoProfissional objeto, Integer idInstituicao) 
			throws BancoobException {
		return getServico().incluir(objeto, idInstituicao);
	}
}
