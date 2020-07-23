package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTZIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTZIntegracaoServicoRemote;

/**
 * Classe com os servi�os de CARTEIRIZA��O.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(CTZIntegracaoServicoLocal.class)
@Remote(CTZIntegracaoServicoRemote.class)
public class CTZIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements CTZIntegracaoServicoLocal, CTZIntegracaoServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarCarteirizacao(Integer idPessoa, Integer idInstituicao, Integer idUnidadeInstituicao, Integer idGerente, Short codigoTipoPessoa) throws BancoobException {
		
	}

}