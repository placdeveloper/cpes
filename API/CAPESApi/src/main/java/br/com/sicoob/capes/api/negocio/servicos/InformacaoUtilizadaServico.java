package br.com.sicoob.capes.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.InformacaoUtilizadaVO;

/**
 * A Interface InformacaoUtilizadaServico.
 */
public interface InformacaoUtilizadaServico extends CAPESApiServico {

	/**
	 * O método Incluir informacao utilizada.
	 *
	 * @param informacaoUtilizadaVO o valor de informacao utilizada vo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluirInformacaoUtilizada(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException;
	
	/**
	 * O método Excluir informacao utilizada.
	 *
	 * @param informacaoUtilizadaVO o valor de informacao utilizada vo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void excluirInformacaoUtilizada(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException;

}