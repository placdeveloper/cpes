/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.replicacao.negocio.dto.AtualizacaoRiscoClienteDTO;
import br.com.sicoob.capes.replicacao.negocio.servicos.ClienteServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para os clientes.
 * 
 * @author juan.damasceno
 */
public class ClienteDelegate extends EntidadeReplicavelDelegate<Cliente, ClienteServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ClienteServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarClienteServico();
	}

	/**
	 * Pesquisar por lista id.
	 *
	 * @param ids o valor de ids
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public List<Cliente> pesquisarPorListaID(List<Integer> ids) throws BancoobException {
		return getServico().pesquisarPorListaID(ids);
	}

	/**
	 * O m�todo Atualizar risco cliente.
	 *
	 * @param dto o valor de dto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException {
		getServico().atualizarRiscoCliente(dto);
	}

	/**
	 * O m�todo Replicar dados cliente em Lote.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @param clientesAlterar o valor de atualizar risco
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void replicarClienteLegadoLote(List<Cliente> clientesIncluir, List<Cliente> clientesAlterar, Integer idInstituicao) throws BancoobException{
		getServico().replicarClienteLegadoLote(clientesIncluir, clientesAlterar, idInstituicao);
	}
}