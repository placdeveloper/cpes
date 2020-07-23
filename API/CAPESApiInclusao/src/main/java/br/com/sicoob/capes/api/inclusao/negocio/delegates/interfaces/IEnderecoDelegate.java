package br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EnderecoDTO;

/**
 * A interfacee EnderecoDelegate.
 * 
 * @author bruno.carneiro
 */
public interface IEnderecoDelegate extends ICAPESApiInclusaoDelegate<EnderecoDTO> {

	/**
	 * Torna o endereço como padrão para correspondências.
	 * 
	 * @param idEndereco
	 *            O ID do endereço que será padrão para correspondências.
	 * @param idInstituicao
	 *            O ID da instituição para a qual o endereço será correpondência.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	void tornarPadraoCorrespondencia(Long idEndereco, Integer idInstituicao) throws BancoobException;

}