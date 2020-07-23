package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IGrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.api.negocio.servicos.GrupoCompartilhamentoServico;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;

/**
 * Delegate dos servi�os de grupos de compartilhamento das institui��es.
 * 
 * @author bruno.carneiro
 */
public class GrupoCompartilhamentoDelegate extends CAPESApiDelegate<GrupoCompartilhamentoServico> implements IGrupoCompartilhamentoDelegate {
	
	/**
	 * 
	 */
	protected GrupoCompartilhamentoDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static GrupoCompartilhamentoDelegate getInstance() {
		return valueOf(GrupoCompartilhamentoDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoCompartilhamentoServico localizarServico() {
		return getLocator().localizarGrupoCompartilhamentoServico();
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