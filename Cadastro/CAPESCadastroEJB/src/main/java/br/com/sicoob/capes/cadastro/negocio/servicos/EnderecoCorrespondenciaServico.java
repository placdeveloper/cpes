/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Servi�o utilizado para endere�os de correspond�ncia.
 * 
 * @author Erico.Junior
 */
public interface EnderecoCorrespondenciaServico extends
		CAPESCadastroCrudServico<EnderecoCorrespondencia> {

	/**
	 * Recupera o endere�o de correspond�ncia da pessoa informada na institui��o logada.
	 * 
	 * @param pessoa A pessoa da qual se deseja o endere�o de correspond�ncia.
	 * @return o endere�o de correspond�ncia da pessoa informada na institui��o logada.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa)	throws BancoobException;
	
	/**
	 * Recupera o endere�o de correspond�ncia da pessoa informada na institui��o logada.
	 * 
	 * @param pessoa A pessoa da qual se deseja o endere�o de correspond�ncia.
	 * @param instituicao A instituicao na qual se deseja consultar o endere�o de correspond�ncia.
	 * @return o endere�o de correspond�ncia da pessoa informada na institui��o logada.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa, Instituicao instituicao) throws BancoobException;
	
	/**
	 * Verifica se o endere�o informado � de correspond�ncia em alguma institui��o.
	 * @param endereco O endere�o a ser verificado.
	 * @return se o endere�o informado � de correspond�ncia em alguma institui��o.
	 * @throws BancoobException caso ocorra alguma exce��o.
	 */
	boolean isEnderecoCorrespondencia(Endereco endereco) throws BancoobException;
	
	/**
	 * Lista as institui��es vinculadas ao endere�o
	 * @param endereco
	 * @return
	 * @throws BancoobException
	 */
	List<Integer> listarInstituicoesVinculadas(Endereco endereco)
			throws BancoobException;
}
