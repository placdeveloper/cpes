package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.InformacaoUtilizada;

/**
 * A Interface InformacaoUtilizadaDAO.
 */
public interface InformacaoUtilizadaDAO extends CAPESCadastroCrudDaoIF<InformacaoUtilizada> {
	
	/**
	 * Obtém a lista dos nomes dos sistemas que utilizam a informação
	 * @param codigoTipoInformacao
	 * @param codigoInformacao
	 * @return
	 * @throws BancoobException
	 */
	List<String> listarSistemasUsandoInformacao(Short codigoTipoInformacao, Long codigoInformacao) throws BancoobException;

}