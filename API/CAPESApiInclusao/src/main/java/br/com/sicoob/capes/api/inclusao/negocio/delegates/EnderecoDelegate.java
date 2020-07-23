package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IEnderecoDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EnderecoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.EnderecoServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe EnderecoDelegate.
 * 
 * @author bruno.carneiro
 */
public class EnderecoDelegate extends CAPESApiInclusaoDelegate<EnderecoDTO, EnderecoServico> implements IEnderecoDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarEnderecoServico();
	}

	/**
	 * Torna o endereço como padrão para correspondências.
	 * 
	 * @param idEndereco
	 *            O ID do endereço que será padrão para correspondências.
	 * @param idInstituicao
	 *            O ID da instituição para a qual o endereço será
	 *            correpondência.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void tornarPadraoCorrespondencia(Long idEndereco, Integer idInstituicao) throws BancoobException {
		getServico().tornarPadraoCorrespondencia(idEndereco, idInstituicao);
	}

}