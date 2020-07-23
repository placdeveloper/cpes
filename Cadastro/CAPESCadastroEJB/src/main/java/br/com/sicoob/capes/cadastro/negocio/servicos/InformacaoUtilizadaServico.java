package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.InformacaoUtilizada;

/**
 * A Interface InformacaoUtilizadaServico.
 */
public interface InformacaoUtilizadaServico extends CAPESCadastroCrudServico<InformacaoUtilizada> {

	/**
	 *  Obt�m a lista dos nomes dos sistemas que utilizam a informa��o
	 * @param codigoTipoInformacao
	 * @param codigoInformacao
	 * @return
	 * @throws BancoobException
	 */
	List<String> listarSistemasUsandoInformacao(Short codigoTipoInformacao, Long codigoInformacao) throws BancoobException;

}