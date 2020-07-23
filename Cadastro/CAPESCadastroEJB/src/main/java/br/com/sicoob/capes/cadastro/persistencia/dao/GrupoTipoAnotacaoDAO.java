package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * A interface da dao de grupo de tipo anota��o
 * 
 * @author Bruno.Carneiro
 */
public interface GrupoTipoAnotacaoDAO extends CAPESCadastroCrudDaoIF<GrupoTipoAnotacao> {

	/**
	 * Obt�m os grupos de um tipo de anota��o
	 * 
	 * @param idTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoTipoAnotacao> obterGruposPorTipoAnotacao(Integer idTipoAnotacao) throws BancoobException;

	/**
	 * Obt�m os grupos de tipo de anota��o por tipos de anota��o
	 * 
	 * @param tiposAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoTipoAnotacao> obterGruposPorTiposAnotacao(List<TipoAnotacao> tiposAnotacao) throws BancoobException;

}