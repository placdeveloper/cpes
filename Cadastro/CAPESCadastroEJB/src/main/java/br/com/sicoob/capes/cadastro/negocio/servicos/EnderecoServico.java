/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.proxies.EnderecoProxy;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Servi�o para manuten��o do cadastro de endere�os das pessoas.
 * 
 * @author Erico.Junior
 */
public interface EnderecoServico extends EntidadeCadastroServico<Endereco> {

	/**
	 * Listar enderecos.
	 *
	 * @param criterios o valor de criterios
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<EnderecoProxy> listarEnderecos(ConsultaDto<Endereco> criterios)
			throws BancoobException;
	
	/**
	 * Listar enderecos.
	 *
	 * @param criterios o valor de criterios
	 * @param instituicao o valor de instituicao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<EnderecoProxy> listarEnderecos(ConsultaDto<Endereco> criterios, Instituicao instituicao)
			throws BancoobException;
	
	/**
	 * Torna o endere�o como padr�o para correspond�ncias.
	 * 
	 * @param endereco
	 *            O endere�o que ser� padr�o para correspond�ncias.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	void tornarPadraoCorrespondencia(Endereco endereco) throws BancoobException;
	
	/**
	 * Torna o endere�o como padr�o para correspond�ncias.
	 * 
	 * @param endereco
	 *            O endere�o que ser� padr�o para correspond�ncias.
	 * @param instituicao
	 * 			  A institui��o para a qual o endere�o ser� correpond�ncia.            
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	void tornarPadraoCorrespondencia(Endereco endereco, Instituicao instituicao) throws BancoobException;		
	
	/**
	 * Inclui o endere�o informado e se n�o existir endere�o de correspond�ncia na institui��o, 
	 * define o mesmo como endere�o de correspond�ncia.
	 * @param endereco O endere�o que ser� inclu�do. 
	 * @param instituicao A institui��o para o endere�o.
	 * @return O endere�o inclu�do.
	 * @throws BancoobException
	 */	
	Endereco incluir(Endereco endereco, Instituicao instituicao) throws BancoobException;
	
	/**
	 * Verifica se existe um endere�o de correspond�ncia para a institui��o e define o mesmo caso
	 * n�o exista.
	 * @param endereco
	 * @param instituicao
	 * @throws BancoobException
	 */
	void verificarEnderecoCorrespondencia(Endereco endereco, Instituicao instituicao) throws BancoobException;
	
	/**
	 * * Verifica se existe um endere�o residencial para a pessoa.
	 * @param pessoa
	 * @param instituicao
	 * @return
	 * @throws BancoobException
	 */
	Endereco obterEnderecoResidencial(PessoaCompartilhamento pessoa) throws BancoobException;
}
