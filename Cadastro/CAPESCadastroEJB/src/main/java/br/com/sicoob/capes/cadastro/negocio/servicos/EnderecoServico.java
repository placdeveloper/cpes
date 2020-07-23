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
 * Serviço para manutenção do cadastro de endereços das pessoas.
 * 
 * @author Erico.Junior
 */
public interface EnderecoServico extends EntidadeCadastroServico<Endereco> {

	/**
	 * Listar enderecos.
	 *
	 * @param criterios o valor de criterios
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<EnderecoProxy> listarEnderecos(ConsultaDto<Endereco> criterios)
			throws BancoobException;
	
	/**
	 * Listar enderecos.
	 *
	 * @param criterios o valor de criterios
	 * @param instituicao o valor de instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<EnderecoProxy> listarEnderecos(ConsultaDto<Endereco> criterios, Instituicao instituicao)
			throws BancoobException;
	
	/**
	 * Torna o endereço como padrão para correspondências.
	 * 
	 * @param endereco
	 *            O endereço que será padrão para correspondências.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	void tornarPadraoCorrespondencia(Endereco endereco) throws BancoobException;
	
	/**
	 * Torna o endereço como padrão para correspondências.
	 * 
	 * @param endereco
	 *            O endereço que será padrão para correspondências.
	 * @param instituicao
	 * 			  A instituição para a qual o endereço será correpondência.            
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	void tornarPadraoCorrespondencia(Endereco endereco, Instituicao instituicao) throws BancoobException;		
	
	/**
	 * Inclui o endereço informado e se não existir endereço de correspondência na instituição, 
	 * define o mesmo como endereço de correspondência.
	 * @param endereco O endereço que será incluído. 
	 * @param instituicao A instituição para o endereço.
	 * @return O endereço incluído.
	 * @throws BancoobException
	 */	
	Endereco incluir(Endereco endereco, Instituicao instituicao) throws BancoobException;
	
	/**
	 * Verifica se existe um endereço de correspondência para a instituição e define o mesmo caso
	 * não exista.
	 * @param endereco
	 * @param instituicao
	 * @throws BancoobException
	 */
	void verificarEnderecoCorrespondencia(Endereco endereco, Instituicao instituicao) throws BancoobException;
	
	/**
	 * * Verifica se existe um endereço residencial para a pessoa.
	 * @param pessoa
	 * @param instituicao
	 * @return
	 * @throws BancoobException
	 */
	Endereco obterEnderecoResidencial(PessoaCompartilhamento pessoa) throws BancoobException;
}
