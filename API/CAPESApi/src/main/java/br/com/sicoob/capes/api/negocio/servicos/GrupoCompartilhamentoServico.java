package br.com.sicoob.capes.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;

/**
 * Interface dos servi�os de grupo de compartilhamento
 * 
 * @author bruno.carneiro
 */
public interface GrupoCompartilhamentoServico extends CAPESApiServico {

	/**
	 * Obt�m o grupo de compartilhamento da institui��o informada.
	 * 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	GrupoCompartilhamentoVO obterGrupoCompartilhamentoInstituicao(Integer idInstituicao) throws BancoobException;

}