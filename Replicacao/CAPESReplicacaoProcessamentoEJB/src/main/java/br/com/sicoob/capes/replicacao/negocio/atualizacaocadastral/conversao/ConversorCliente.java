/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;


import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.ClienteDelegate;

/**
 * Conversor de Tributação do Cadastro único em cliente da replicação.
 * 
 * @author juan.damasceno
 */
public class ConversorCliente extends
		Conversor<Cliente, Tributacao> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente obterEntidadeReplicacao(AtualizacaoCadastralDTO
			<Tributacao> dto)
			throws BancoobException {

		Tributacao tributacao =	dto.getEntidadeCadastro();

		// Replicação
		Pessoa pessoa = obterPessoa(dto);
		Cliente cliente = null;

		ClienteDelegate clienteDelegate = CAPESReplicacaoComumFabricaDelegates.getInstance().criarClienteDelegate(); 
		Cliente clientePersistent = clienteDelegate.obter(pessoa.getId(), dto.getInstituicao().getIdInstituicao());

		if (clientePersistent == null) {
			cliente = new Cliente();
		} else {
			cliente = clientePersistent;
		}
		
		cliente.setPessoa(pessoa);
		cliente.setIdCliente(pessoa.getId());
		cliente.setPessoa(pessoa);
		cliente.setCobrarCPMF(tributacao.getCobrarCPMF());
		cliente.setCobrarIOF(tributacao.getCobrarIOF());
		cliente.setCobrarIR(tributacao.getCobrarIR());
		cliente.setDataUltimaAtualizacao(DataUtil.obterDataAtual());

		return cliente;
	}
}
