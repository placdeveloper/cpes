/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Interface para o DAO de tipo de anotação.
 * 
 * @author Erico.Junior
 */
public interface TipoAnotacaoDAO extends
		CAPESCadastroCrudDaoIF<TipoAnotacao> {
	
	/**
	 * Consulta os tipos de anotações ativos com o tipo de captura informado.
	 * @param consultaDto tipo captura / idInstituicao usuario logado
	 * @return Lista de tipos de anotação a partir do tipo de captura.
	 */
	List<TipoAnotacao> listarTiposAnotacaoAtivos(ConsultaDto<TipoAnotacao> consultaDto);
	
	/**
	 * Obtem a saida do Tipo de Anotação informada.
	 * 
	 * @param objeto Tipo de Anotação
	 * @return Texto de saida.
	 * @throws BancoobException
	 */
	String obterSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException;

}
