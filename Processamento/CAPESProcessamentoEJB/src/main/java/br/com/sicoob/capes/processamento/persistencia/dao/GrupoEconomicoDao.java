package br.com.sicoob.capes.processamento.persistencia.dao;

import java.util.List;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public interface GrupoEconomicoDao extends CAPESProcessamentoDaoIF {
	
	/**
	 * Contar os grupos sem compartilhamentos de cadastros
	 * @param idInstituicao
	 * @return
	 */
	int contarGruposSemCompartilhamentos(Integer idInstituicao);
	
	/**
	 * 
	 * @param idInstituicao
	 * @return
	 */
	List<Long> listarPessoasSemCompartilhamento(Integer idInstituicao);

}
