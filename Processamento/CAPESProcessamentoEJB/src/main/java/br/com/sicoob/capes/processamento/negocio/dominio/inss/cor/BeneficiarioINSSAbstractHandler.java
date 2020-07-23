/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss.cor;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dto.RequisicaoBeneficiarioDTO;

/**
 * Super classe para os elos da cadeia de beneficiário.
 * @author Erico.Junior
 */
public abstract class BeneficiarioINSSAbstractHandler {

	/** O atributo proximo. */
	private transient BeneficiarioINSSAbstractHandler proximo;
	
	/** O atributo delegate. */
	private transient PessoaCompartilhamentoDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

	/**
	 * Recupera o valor de proximo.
	 *
	 * @return o valor de proximo
	 */
	public BeneficiarioINSSAbstractHandler getProximo() {
		return proximo;
	}
	
	/**
	 * Define o valor de proximo.
	 *
	 * @param proximo o novo valor de proximo
	 */
	public void setProximo(BeneficiarioINSSAbstractHandler proximo) {
		this.proximo = proximo;
	}
	
	/**
	 * Recupera o valor de delegate.
	 *
	 * @return o valor de delegate
	 */
	protected PessoaCompartilhamentoDelegate getDelegate(){
		return delegate;
	}
	
	/**
	 * Método que irá processar a requisição ou passar para o próximo elo da cadeia de responsabilidade.
	 * @param requisicao A requisição utilizado na cadeia de responsabilidade.
	 * @return A PessoaFisica incluída ou alterada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public abstract PessoaFisica processarRequisicao(RequisicaoBeneficiarioDTO requisicao) throws BancoobException;

}
