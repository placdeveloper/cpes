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
 * Servi�o utilizado para replica��o de clientes.
 * 
 * @author juan.damasceno
 */
public interface ClienteServico extends EntidadeReplicavelServico<Cliente> {

	/**
	 * Pesquisar por lista id.
	 *
	 * @param ids o valor de ids
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<Cliente> pesquisarPorListaID(List<Integer> ids) throws BancoobException;

	/**
	 * O m�todo Alterar data sfn.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void alterarDataSFN(Pessoa pessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * O m�todo Atualizar risco cliente.
	 *
	 * @param dto o valor de dto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException;

	/**
	 * O m�todo Replicar dados cliente em Lote.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param clientesAlterar o valor de atualizar risco
	 * @param idInstituicao 
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void replicarClienteLegadoLote(List<Cliente>clientesIncluir, List<Cliente> clientesAlterar, Integer idInstituicao) throws BancoobException;
}