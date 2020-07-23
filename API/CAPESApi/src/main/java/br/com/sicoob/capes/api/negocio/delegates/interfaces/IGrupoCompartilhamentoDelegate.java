package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;

/**
 * Delegate dos servi�os de grupos de compartilhamento das institui��es.
 * 
 * @author bruno.carneiro
 */
public interface IGrupoCompartilhamentoDelegate extends ICAPESApiDelegate {

	/**
	 * Obt�m o grupo de compartilhamento da institui��o informada.
	 * 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	GrupoCompartilhamentoVO obterGrupoCompartilhamentoInstituicao(Integer idInstituicao) throws BancoobException;

}