/**
 * 
 */
package br.com.sicoob.capes.relatorio.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioProvaVidaDTO;

/**
 * Interface DAO do RelatorioProvaVidaDAO
 * 
 * @author Daniel.Domingues
 *
 */
public interface RelatorioProvaVidaDAO {

	/**
	 * Obtem os dados da Institui��o informada.
	 * 
	 * @param instituicao Institui��o do relatorio.
	 * @return RelatorioProvaVidaDTO
	 * @throws BancoobException
	 */
	RelatorioProvaVidaDTO obterDadosRelatorio(Instituicao instituicao) throws BancoobException;
}