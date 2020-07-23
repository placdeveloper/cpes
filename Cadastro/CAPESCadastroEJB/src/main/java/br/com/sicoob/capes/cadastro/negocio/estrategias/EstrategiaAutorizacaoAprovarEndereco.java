package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoDelegate;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;

/**
 * Estratégia para aprovação de endereços.
 */
public class EstrategiaAutorizacaoAprovarEndereco extends EstrategiaAutorizacaoAprovar {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarInclusao(Autorizacao autorizacao, Aprovavel registroNovo) throws BancoobException {
		super.tratarInclusao(autorizacao, registroNovo);

		EnderecoDelegate delegate = CAPESCadastroFabricaDelegates
				.getInstance().criarEnderecoDelegate();
		delegate.verificarEnderecoCorrespondencia((Endereco) registroNovo,
				autorizacao.getInstituicaoOrigem());
	}
}