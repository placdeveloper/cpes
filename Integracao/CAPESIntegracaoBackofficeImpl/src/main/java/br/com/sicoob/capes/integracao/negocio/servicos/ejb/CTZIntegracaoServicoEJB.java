package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTZIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.CTZIntegracaoServicoRemote;
import br.com.sicoob.ctz.api.factory.CTZAPIDelegateFactory;
import br.com.sicoob.ctz.api.negocio.dto.CarteirizacaoDTO;

/**
 * Classe com os serviços de CARTEIRIZAÇÃO.
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
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void atualizarCarteirizacao(Integer idPessoa, Integer idInstituicao, Integer idUnidadeInstituicao, Integer idGerente, Short codigoTipoPessoa) throws BancoobException {
		try {
			getLogger().debug(MensagemUtil.getString("msg.debug.atualizarCarteirizacao", idPessoa, idInstituicao, idUnidadeInstituicao, idGerente, codigoTipoPessoa));
			CarteirizacaoDTO dto = new CarteirizacaoDTO();
			dto.setIdInstituicao(idInstituicao);
			dto.setIdPessoa(idPessoa);
			dto.setIdUnidadeInstituicao(idUnidadeInstituicao);
			dto.setIdGerente(idGerente);
			dto.setTipoPessoa(codigoTipoPessoa.intValue());
			CTZAPIDelegateFactory.getInstance().getCadastrarPessoaCarteirizacaoCTZAPIDelegate().cadastrarPessoaCarteirizacao(dto);
		} catch (Throwable e) { // NOPMD
			getLogger().erro(e, "Erro na integracao com o CARTEIRIZACAO");
		}
	}

}