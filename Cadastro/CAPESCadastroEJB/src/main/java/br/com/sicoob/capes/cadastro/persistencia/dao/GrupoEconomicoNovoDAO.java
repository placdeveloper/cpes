package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public interface GrupoEconomicoNovoDAO extends CAPESCadastroCrudDaoIF<GrupoEconomicoNovo> {
	
	/**
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 */
	List<GrupoEconomicoNovo> listarPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao);

}