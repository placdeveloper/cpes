/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Define as opera��es do servi�o de manipula��o de mapa de tipo de anota��es.
 * 
 */
public interface MapaTipoAnotacaoServico extends CAPESCadastroCrudServico<MapaTipoAnotacao> {

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