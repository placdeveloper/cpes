package br.com.sicoob.capes.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.InformacaoUtilizadaVO;

/**
 * A Interface InformacaoUtilizadaServico.
 */
public interface InformacaoUtilizadaServico extends CAPESApiServico {

	/**
	 * O m�todo Incluir informacao utilizada.
	 *
	 * @param informacaoUtilizadaVO o valor de informacao utilizada vo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void incluirInformacaoUtilizada(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException;
	
	/**
	 * O m�todo Excluir informacao utilizada.
	 *
	 * @param informacaoUtilizadaVO o valor de informacao utilizada vo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void excluirInformacaoUtilizada(InformacaoUtilizadaVO informacaoUtilizadaVO) throws BancoobException;

}