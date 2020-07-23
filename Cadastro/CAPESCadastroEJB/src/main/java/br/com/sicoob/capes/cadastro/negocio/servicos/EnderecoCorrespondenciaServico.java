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
 * Serviço utilizado para endereços de correspondência.
 * 
 * @author Erico.Junior
 */
public interface EnderecoCorrespondenciaServico extends
		CAPESCadastroCrudServico<EnderecoCorrespondencia> {

	/**
	 * Recupera o endereço de correspondência da pessoa informada na instituição logada.
	 * 
	 * @param pessoa A pessoa da qual se deseja o endereço de correspondência.
	 * @return o endereço de correspondência da pessoa informada na instituição logada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa)	throws BancoobException;
	
	/**
	 * Recupera o endereço de correspondência da pessoa informada na instituição logada.
	 * 
	 * @param pessoa A pessoa da qual se deseja o endereço de correspondência.
	 * @param instituicao A instituicao na qual se deseja consultar o endereço de correspondência.
	 * @return o endereço de correspondência da pessoa informada na instituição logada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa, Instituicao instituicao) throws BancoobException;
	
	/**
	 * Verifica se o endereço informado é de correspondência em alguma instituição.
	 * @param endereco O endereço a ser verificado.
	 * @return se o endereço informado é de correspondência em alguma instituição.
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	boolean isEnderecoCorrespondencia(Endereco endereco) throws BancoobException;
	
	/**
	 * Lista as instituições vinculadas ao endereço
	 * @param endereco
	 * @return
	 * @throws BancoobException
	 */
	List<Integer> listarInstituicoesVinculadas(Endereco endereco)
			throws BancoobException;
}
