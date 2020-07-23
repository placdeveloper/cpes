/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.replicacao.negocio.dto.AtualizacaoRiscoClienteDTO;

/**
 * Dao utilizada para os clientes.
 * 
 * @author juan.damasceno
 */
public interface ClienteDAO extends EntidadeReplicavelDaoIF<Cliente> {

	/**
	 * Pesquisar por lista id.
	 *
	 * @param ids o valor de ids
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<Cliente> pesquisarPorListaID(List<Integer> ids) throws BancoobException;
	
	/**
	 * O método Atualizar risco cliente.
	 *
	 * @param dto o valor de dto
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto, Integer numeroCooperativa) throws BancoobException;

	/**
	 * Alterar clientes em lote
	 * @param clientesAlterar
	 * @param idInstituicao 
	 * @throws BancoobException
	 */
	void replicarLote(List<Cliente> clientesIncluir, List<Cliente> clientesAlterar, Integer idInstituicao) throws BancoobException;

}