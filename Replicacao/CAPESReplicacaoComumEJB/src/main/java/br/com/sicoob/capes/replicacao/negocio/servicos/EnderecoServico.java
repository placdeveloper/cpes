/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Endereco;

/**
 * Servi�o utilizado para replica��o de endere�os.
 * 
 * @author Erico.Junior
 */
public interface EnderecoServico extends EntidadeReplicavelServico<Endereco> {

	/**
	 * Torna o endere�o como padr�o para correspond�ncias.
	 * 
	 * @param endereco
	 *            O endere�o que ser� padr�o para correspond�ncias.
	 * @param instituicao
	 *            A institui��o.            
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	void tornarPadraoCorrespondencia(Endereco endereco, Integer idInstituicao)
			throws BancoobException;	
}
