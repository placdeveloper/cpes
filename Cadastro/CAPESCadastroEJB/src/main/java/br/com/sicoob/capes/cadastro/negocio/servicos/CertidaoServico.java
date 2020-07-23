/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Define as operações do serviço de manipulação de Certidao.
 * 
 * @author Juan.Damasceno
 */
public interface CertidaoServico extends
		EntidadeCadastroServico<Certidao> {

	/**
	 * Listar por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<Certidao> listarPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Exclui as certidoes informadas.
	 * @throws BancoobException 
	 */
	void excluirCertidoes(List<Certidao> certidoes) throws BancoobException;

	/**
	 * Lista as certidões vencidas somente quando o tipo de prazo de vencimento for 0,1 e 2.
	 * @param criterios DTO com o número de registros por pagina e o número da página.
	 * @throws BancoobException 
	 */
	List<Certidao> listarVencidas(ConsultaDto<Certidao> criterios) throws BancoobException;
	
}