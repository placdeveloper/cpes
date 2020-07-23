package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import org.junit.Before;

import br.com.sicoob.capes.processamento.negocio.servicos.locator.ServiceLocator;

/**
 * A Classe CAPESProcessamentoServicoEJBTest.
 *
 * @param <S> o tipo generico
 */
public abstract class CAPESProcessamentoServicoEJBTest<S> {

	/** O atributo servico. */
	private S servico;
	
    /**
     * O método Sets the up.
     *
     * @throws Exception lança a exceção Exception
     */
    @Before
    @SuppressWarnings("unchecked")
	public void setUp() throws Exception {
		servico = (S) ServiceLocator.getInstance().getObjetoRemoto(getJndiName());
	}

	/**
	 * Retorna o JNDI Name sem o "Remote" 
	 * @return o JNDI name
	 */
	protected abstract String getJndiName();

    /**
     * Recupera o valor de servico.
     *
     * @return o valor de servico
     */
    public S getServico() {
    	return servico;
    }

}
