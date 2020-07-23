package br.com.sicoob.capes.api.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;

/**
 * Interface das operações do grupo de compartilhamento
 * 
 * @author bruno.carneiro
 */
public interface GrupoCompartilhamentoDAO extends CAPESApiDaoIF<GrupoCompartilhamentoVO> {

	/**
	 * Obtém o grupo de compartilhamento da instituição informada.
	 * 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	GrupoCompartilhamentoVO obterGrupoCompartilhamentoInstituicao(Integer idInstituicao) throws BancoobException;

}