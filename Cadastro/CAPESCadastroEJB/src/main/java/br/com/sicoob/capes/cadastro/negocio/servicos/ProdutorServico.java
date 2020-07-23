/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author Erico.Junior
 *
 */
public interface ProdutorServico extends EntidadeCadastroServico<Produtor> {

	/**
	 * Valida se a pessoa pode ser inclu�da como Produtor.
	 * @param pessoa a pessoa ser verificada
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	void validarInclusaoProdutor(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * O m�todo Validar incluir.
	 *
	 * @param produto o valor de produto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void validarIncluir(Produtor produto) throws BancoobException;
	
}
