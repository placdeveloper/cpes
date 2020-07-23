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
	void tornarPadraoCorrespondencia(Long idEndereco, Integer idInstituicao) throws BancoobException;

}