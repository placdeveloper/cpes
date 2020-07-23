package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * A interface da dao de grupo de tipo anotação
 * 
 * @author Bruno.Carneiro
 */
public interface GrupoTipoAnotacaoDAO extends CAPESCadastroCrudDaoIF<GrupoTipoAnotacao> {

	/**
	 * Obtém os grupos de um tipo de anotação
	 * 
	 * @param idTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoTipoAnotacao> obterGruposPorTipoAnotacao(Integer idTipoAnotacao) throws BancoobException;

	/**
	 * Obtém os grupos de tipo de anotação por tipos de anotação
	 * 
	 * @param tiposAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoTipoAnotacao> obterGruposPorTiposAnotacao(List<TipoAnotacao> tiposAnotacao) throws BancoobException;

}