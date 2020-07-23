/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Interface para o DAO de Mapa de tipos de anotação
 */
public interface MapaTipoAnotacaoDAO extends CAPESCadastroCrudDaoIF<MapaTipoAnotacao> {

	/**
	 * 
	 * @param codigoTipoOrigem
	 * @param nomeOrigem
	 * @param nomeTipoConsulta
	 * @return
	 * @throws BancoobException
	 */
	MapaTipoAnotacao obterTipoAnotacaoAnotacaoExterna(MapaTipoAnotacao mapaTipoAnotacao) throws BancoobException;
	
	/**
	 * 
	 * @param tipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<MapaTipoAnotacao> obterMapasTipoAnotacaoPorTipoAnotacao(TipoAnotacao tipoAnotacao) throws BancoobException;
}