/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Endereco;
import br.com.sicoob.capes.replicacao.negocio.servicos.EnderecoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para a replicação de endereço.
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
	 * Torna o endereço como padrão para correspondências.
	 * 
	 * @param endereco
	 *            O endereço que será padrão para correspondências.
	 * @param idInstituicao
	 *            A instituição.            
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void tornarPadraoCorrespondencia(Endereco endereco, Integer idInstituicao) 
		throws BancoobException{
		getServico().tornarPadraoCorrespondencia(endereco, idInstituicao);
	}
}
