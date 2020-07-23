/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Endereco;
import br.com.sicoob.capes.replicacao.negocio.servicos.EnderecoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para a replica��o de endere�o.
 * 
 * @author erico.junior
 */
public class EnderecoDelegate extends EntidadeReplicavelDelegate<Endereco, EnderecoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarEnderecoServico();
	}

	/**
	 * Torna o endere�o como padr�o para correspond�ncias.
	 * 
	 * @param endereco
	 *            O endere�o que ser� padr�o para correspond�ncias.
	 * @param idInstituicao
	 *            A institui��o.            
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public void tornarPadraoCorrespondencia(Endereco endereco, Integer idInstituicao) 
		throws BancoobException{
		getServico().tornarPadraoCorrespondencia(endereco, idInstituicao);
	}
}
