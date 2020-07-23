/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Interface para o DAO de certidao.
 * 
 * @author juan.damasceno
 */
public interface CertidaoDAO extends
		EntidadeCadastroDaoIF<Certidao> {
	
	/**
	 * Listar por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<Certidao> listarPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Lista as certidões com 1 dia de vencida somente quando o tipo de prazo de vencimento for 0,1 e 2.
	 * @param criterios Os critérios utilizados como filtro para as rendas vencidas. 
	 * @return as certidões com 1 dia de vencida somente quando o tipo de prazo de vencimento for 0,1 e 2.
	 * @throws BancoobException
	 */
	List<Certidao> listarVencidas(ConsultaDto<Certidao> criterios) throws BancoobException;	
}