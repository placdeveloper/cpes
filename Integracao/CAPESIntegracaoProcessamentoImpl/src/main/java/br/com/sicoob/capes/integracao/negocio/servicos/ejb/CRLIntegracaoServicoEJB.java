package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.vo.RiscoVO;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CRLIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CRLIntegracaoServicoRemote;
import br.com.sicoob.sisbr.crl.api.negocio.delegates.CRLAPIFabricaDelegates;
import br.com.sicoob.sisbr.crl.api.negocio.delegates.RiscoDelegate;
import br.com.sicoob.sisbr.crl.api.negocio.vo.RiscoNotaVO;

/**
 * Classe com os serviços de integração com o CRL.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local(CRLIntegracaoServicoLocal.class)
@Remote(CRLIntegracaoServicoRemote.class)
public class CRLIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements CRLIntegracaoServicoLocal, CRLIntegracaoServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RiscoVO obterRisco(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		RiscoDelegate riscoDelegate = CRLAPIFabricaDelegates.getInstance().criarRiscoDelegate();
		return converterRiscoVO(riscoDelegate.recuperarNotaRisco(idPessoa, idInstituicao));
	}

	/**
	 * Converter risco vo.
	 *
	 * @param riscoNota o valor de risco nota
	 * @return RiscoVO
	 */
	private RiscoVO converterRiscoVO(RiscoNotaVO riscoNota) {
		RiscoVO retorno = null;

		if (riscoNota != null) {
			retorno = new RiscoVO();
			retorno.setDataEnquadramentoRisco(riscoNota.getDataClassificacao() != null ? new DateTimeDB(riscoNota.getDataClassificacao().getTime()) : new DateTimeDB());
			retorno.setNivelRisco(riscoNota.getNotaRisco().getNota());
		}

		return retorno;
	}

}