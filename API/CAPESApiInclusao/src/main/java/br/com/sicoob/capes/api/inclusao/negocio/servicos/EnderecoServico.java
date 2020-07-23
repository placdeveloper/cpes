package br.com.sicoob.capes.api.inclusao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EnderecoDTO;

/**
 * A Interface EnderecoServico.
 *
 * @author bruno.carneiro
 */
public interface EnderecoServico extends CAPESApiInclusaoServico<EnderecoDTO> {

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
	void tornarPadraoCorrespondencia(Long idEndereco, Integer idInstituicao) throws BancoobException;

}