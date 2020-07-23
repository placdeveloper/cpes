package br.com.sicoob.capes.cadastro.util;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO;
import br.com.sicoob.capes.integracao.negocio.delegates.ADMIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;

/**
 * @author Marcelo.Onofre
 *
 */
public final class EsferaAdministrativaUtil {

	/**
	 * Instancia um novo EsferaAdministrativaUtil.
	 */
	private EsferaAdministrativaUtil(){
	}
	
	/**
	 * Recupera o nome da esfera administrativa a partir do identificador informado.
	 * @param idEsferaAdministrativa O identificador da localidade.
	 * @return o nome da esfera administrativa a partir do identificador informado.
	 * @throws BancoobException Caso ocorra alguma exceção na consulta.
	 */
	public static String obterNomeEsferaAdministrativa(Integer idEsferaAdministrativa) throws BancoobException{
		
		String nomeEsferaAdm = null;
		EsferaAdministrativaVO esferaAdm = obterEsferaAdministrativa(idEsferaAdministrativa);
		
		if(esferaAdm != null) {
			nomeEsferaAdm = esferaAdm.getDescricao();
		}
		
		return nomeEsferaAdm;
	}
	
	/**
	 * Recupera a esfera administrativa a partir do idEsferaAdministrativa informado.
	 * @param idEsferaAdministrativa O id da esfera administrativa utilizado na consulta.
	 * @return a esfera administrativa a partir do idEsferaAdministrativa informado.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public static EsferaAdministrativaVO obterEsferaAdministrativa(Integer idEsferaAdministrativa) throws BancoobException {
		
		EsferaAdministrativaVO esfera = null;
		
		if(idEsferaAdministrativa != null) {
			ADMIntegracaoDelegate admIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarADMIntegracaoDelegate();
			esfera = admIntegracaoDelegate.obterEsferaAdministrativa(idEsferaAdministrativa.shortValue());
		}
		
		return esfera; 
	}		
}
