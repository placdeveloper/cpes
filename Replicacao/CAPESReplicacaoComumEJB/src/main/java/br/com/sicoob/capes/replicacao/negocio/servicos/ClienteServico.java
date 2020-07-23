/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.replicacao.negocio.dto.AtualizacaoRiscoClienteDTO;

/**
 * Serviço utilizado para replicação de clientes.
 * 
 * @author juan.damasceno
 */
public interface ClienteServico extends EntidadeReplicavelServico<Cliente> {

	/**
	 * Pesquisar por lista id.
	 *
	 * @param ids o valor de ids
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<Cliente> pesquisarPorListaID(List<Integer> ids) throws BancoobException;

	/**
	 * O método Alterar data sfn.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void alterarDataSFN(Pessoa pessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * O método Atualizar risco cliente.
	 *
	 * @param dto o valor de dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException;

	/**
	 * O método Replicar dados cliente em Lote.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param clientesAlterar o valor de atualizar risco
	 * @param idInstituicao 
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void replicarClienteLegadoLote(List<Cliente>clientesIncluir, List<Cliente> clientesAlterar, Integer idInstituicao) throws BancoobException;
}