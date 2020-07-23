package br.com.sicoob.capes.integracao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.integracao.negocio.servicos.CTZIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.locator.CAPESIntegracaoServiceLocator;

/**
 * Delegate com os serviços de CARTEIRIZAÇÃO
 * 
 * @author bruno.carneiro
 */
public class CTZIntegracaoDelegate extends CAPESIntegracaoDelegate<CTZIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CTZIntegracaoServico localizarServico() {
		return CAPESIntegracaoServiceLocator.getInstance().localizarCTZIntegracaoServico();
	}
	
	/**
	 * Verifica a necessidade de cadastrar o cliente no Carteirização;
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param idUnidadeInstituicao
	 * @param codigoTipoPessoa
	 * @throws BancoobException
	 */
	public void atualizarCarteirizacao(Integer idPessoa, Integer idInstituicao, Integer idUnidadeInstituicao, Integer idGerente, Short codigoTipoPessoa) throws BancoobException {
		getServico().atualizarCarteirizacao(idPessoa, idInstituicao, idUnidadeInstituicao, idGerente, codigoTipoPessoa);
	}

}