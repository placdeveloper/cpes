/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;

/**
 * @author Erico.Junior
 * 
 */
public interface InformacaoProfissionalDAO extends
		CAPESCadastroCrudDaoIF<InformacaoProfissional> {

	/**
	 * Listar por matricula empregador.
	 *
	 * @param informacao o valor de informacao
	 * @return List
	 */
	List<InformacaoProfissional> listarPorMatriculaEmpregador(
			InformacaoProfissional informacao);
}