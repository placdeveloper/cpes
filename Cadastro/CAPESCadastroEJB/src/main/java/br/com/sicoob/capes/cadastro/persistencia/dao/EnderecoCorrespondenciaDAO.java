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
	 * Consulta o endereço de correspondência para a pessoa na instituição.
	 * @param pessoa A pessoa a ser consultada.
	 * @param instituicao A instituição na qual se deseja saber o endereço de correspondência da 
	 * pessoa.
	 * @return  o endereço de correspondência para a pessoa na instituição.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa, Instituicao instituicao) 
			throws BancoobException;
	
	/**
	 * Lista as instituições vinculadas ao endereço
	 * @param endereco
	 * @return
	 * @throws BancoobException
	 */
	List<Integer> listarInstituicoesVinculadas(Endereco endereco)
			throws BancoobException;
}
