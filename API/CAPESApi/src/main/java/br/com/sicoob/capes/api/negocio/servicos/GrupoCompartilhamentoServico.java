package br.com.sicoob.capes.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;

/**
 * Interface dos serviços de grupo de compartilhamento
 * 
 * @author bruno.carneiro
 */
public interface GrupoCompartilhamentoServico extends CAPESApiServico {

	/**
	 * Obtém o grupo de compartilhamento da instituição informada.
	 * 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	GrupoCompartilhamentoVO obterGrupoCompartilhamentoInstituicao(Integer idInstituicao) throws BancoobException;

}