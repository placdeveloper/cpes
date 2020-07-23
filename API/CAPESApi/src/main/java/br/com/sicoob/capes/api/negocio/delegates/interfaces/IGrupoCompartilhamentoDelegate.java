package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;

/**
 * Delegate dos serviços de grupos de compartilhamento das instituições.
 * 
 * @author bruno.carneiro
 */
public interface IGrupoCompartilhamentoDelegate extends ICAPESApiDelegate {

	/**
	 * Obtém o grupo de compartilhamento da instituição informada.
	 * 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	GrupoCompartilhamentoVO obterGrupoCompartilhamentoInstituicao(Integer idInstituicao) throws BancoobException;

}