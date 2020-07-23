/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.ProdutorServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author Erico.Junior
 * 
 */
public class ProdutorDelegate extends
		EntidadeCadastroDelegate<Produtor, ProdutorServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutorServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarProdutorServico();
	}

	/**
	 * Valida se a pessoa pode ser incluída como Produtor.
	 * @param pessoa a pessoa ser verificada
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public void validarInclusaoProdutor(PessoaCompartilhamento pessoa) throws BancoobException {
		getServico().validarInclusaoProdutor(pessoa);
	}
	
	/**
	 * O método Validar incluir.
	 *
	 * @param produtor o valor de produtor
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void validarIncluir(Produtor produtor) throws BancoobException{
		getServico().validarIncluir(produtor);
	}
}
