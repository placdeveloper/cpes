package br.com.sicoob.capes.api.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IGrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.api.negocio.servicos.GrupoCompartilhamentoServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;

/**
 * Delegate dos servi�os de grupos de compartilhamento das institui��es.
 * 
 * @author bruno.carneiro
 */
public class GrupoCompartilhamentoDelegate extends CAPESApiDelegate<GrupoCompartilhamentoServico> implements IGrupoCompartilhamentoDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoCompartilhamentoServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarGrupoCompartilhamentoServico();
	}

	/**
	 * Obt�m o grupo de compartilhamento da institui��o informada.
	 * 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public GrupoCompartilhamentoVO obterGrupoCompartilhamentoInstituicao(Integer idInstituicao) throws BancoobException {
		return getServico().obterGrupoCompartilhamentoInstituicao(idInstituicao);
	}

}