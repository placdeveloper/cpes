/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Erico.Junior
 * 
 */
public interface EnderecoCorrespondenciaDAO extends
		CAPESCadastroCrudDaoIF<EnderecoCorrespondencia> {

	/**
	 * Consulta o endere�o de correspond�ncia para a pessoa na institui��o.
	 * @param pessoa A pessoa a ser consultada.
	 * @param instituicao A institui��o na qual se deseja saber o endere�o de correspond�ncia da 
	 * pessoa.
	 * @return  o endere�o de correspond�ncia para a pessoa na institui��o.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa, Instituicao instituicao) 
			throws BancoobException;
	
	/**
	 * Lista as institui��es vinculadas ao endere�o
	 * @param endereco
	 * @return
	 * @throws BancoobException
	 */
	List<Integer> listarInstituicoesVinculadas(Endereco endereco)
			throws BancoobException;
}
