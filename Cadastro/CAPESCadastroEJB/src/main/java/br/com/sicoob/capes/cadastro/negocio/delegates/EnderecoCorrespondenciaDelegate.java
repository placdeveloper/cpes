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
 * Delegate para endere�o de correspond�ncia.
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
	 * Recupera o endere�o de correspond�ncia da pessoa informada na institui��o logada.
	 * 
	 * @param pessoa A pessoa da qual se deseja o endere�o de correspond�ncia.
	 * @return o endere�o de correspond�ncia da pessoa informada na institui��o logada.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	public EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa)	throws BancoobException {
		return getServico().consultar(pessoa);
	}
	
	/**
	 * Recupera o endere�o de correspond�ncia da pessoa informada na institui��o logada.
	 * 
	 * @param pessoa A pessoa da qual se deseja o endere�o de correspond�ncia.
	 * @param instituicao A instituicao na qual se deseja consultar o endere�o de correspond�ncia.
	 * @return o endere�o de correspond�ncia da pessoa informada na institui��o logada.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	public EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa, Instituicao instituicao) 
			throws BancoobException {
		return getServico().consultar(pessoa, instituicao);
	}
}
