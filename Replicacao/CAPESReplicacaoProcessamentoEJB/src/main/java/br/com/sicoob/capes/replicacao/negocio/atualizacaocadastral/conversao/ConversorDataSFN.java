/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.AtualizacaoDataSFNDTO;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.ClienteDelegate;

/**
 * @author Erico.Junior
 * 
 */
public class ConversorDataSFN extends Conversor<Cliente, AtualizacaoDataSFNDTO> {

	/** O atributo delegate. */
	private transient ClienteDelegate delegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarClienteDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente obterEntidadeReplicacao(
			AtualizacaoCadastralDTO<AtualizacaoDataSFNDTO> dto)
			throws BancoobException {
		
		AtualizacaoDataSFNDTO atualizacao = dto.getEntidadeCadastro();
		PessoaCompartilhamento pessoa = atualizacao.getPessoaCompartilhamento();
		Cliente cliente = delegate.obter(dto.getIdPessoaLegado(), dto.getInstituicao().getIdInstituicao());

		if (cliente != null) {
			cliente.setDataCadastramentoSFN(pessoa.getDataInclusaoSFN());
		}
		
		return cliente;
	}

}
