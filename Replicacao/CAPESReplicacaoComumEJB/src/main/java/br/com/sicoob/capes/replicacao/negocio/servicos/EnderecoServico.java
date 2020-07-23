/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Endereco;

/**
 * Serviço utilizado para replicação de endereços.
 * 
 * @author Erico.Junior
 */
public interface EnderecoServico extends EntidadeReplicavelServico<Endereco> {

	/**
	 * Torna o endereço como padrão para correspondências.
	 * 
	 * @param endereco
	 *            O endereço que será padrão para correspondências.
	 * @param instituicao
	 *            A instituição.            
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	void tornarPadraoCorrespondencia(Endereco endereco, Integer idInstituicao)
			throws BancoobException;	
}
