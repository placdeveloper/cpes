package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IEnderecoDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EnderecoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.EnderecoServico;

/**
 * A Classe EnderecoDelegate.
 * 
 * @author bruno.carneiro
 */
public class EnderecoDelegate extends CAPESApiInclusaoDelegate<EnderecoDTO, EnderecoServico> implements IEnderecoDelegate {
	
	/**
	 * 
	 */
	protected EnderecoDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static EnderecoDelegate getInstance() {
		return valueOf(EnderecoDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoServico localizarServico() {
		return getLocator().localizarEnderecoServico();
	}

	/**
	 * Torna o endere�o como padr�o para correspond�ncias.
	 * 
	 * @param idEndereco
	 *            O ID do endere�o que ser� padr�o para correspond�ncias.
	 * @param idInstituicao
	 *            O ID da institui��o para a qual o endere�o ser�
	 *            correpond�ncia.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public void tornarPadraoCorrespondencia(Long idEndereco, Integer idInstituicao) throws BancoobException {
		getServico().tornarPadraoCorrespondencia(idEndereco, idInstituicao);
	}

}