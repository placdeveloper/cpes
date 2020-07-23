/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.EnderecoCorrespondenciaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Delegate para endereço de correspondência.
 * @author Erico.Junior
 */
public class EnderecoCorrespondenciaDelegate extends
		CAPESCadastroCrudDelegate<EnderecoCorrespondencia, EnderecoCorrespondenciaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoCorrespondenciaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarEnderecoCorrespondenciaServico();
	}

	/**
	 * Recupera o endereço de correspondência da pessoa informada na instituição logada.
	 * 
	 * @param pessoa A pessoa da qual se deseja o endereço de correspondência.
	 * @return o endereço de correspondência da pessoa informada na instituição logada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa)	throws BancoobException {
		return getServico().consultar(pessoa);
	}
	
	/**
	 * Recupera o endereço de correspondência da pessoa informada na instituição logada.
	 * 
	 * @param pessoa A pessoa da qual se deseja o endereço de correspondência.
	 * @param instituicao A instituicao na qual se deseja consultar o endereço de correspondência.
	 * @return o endereço de correspondência da pessoa informada na instituição logada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa, Instituicao instituicao) 
			throws BancoobException {
		return getServico().consultar(pessoa, instituicao);
	}
}
