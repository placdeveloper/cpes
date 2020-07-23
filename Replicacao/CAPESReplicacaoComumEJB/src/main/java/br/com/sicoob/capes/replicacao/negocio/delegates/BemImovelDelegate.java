/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.BemImovel;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemImovelServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate utilizado na replicação dos bens.
 * @author juan.damasceno
 */
public class BemImovelDelegate extends EntidadeReplicavelDelegate<BemImovel, BemImovelServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemImovelServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarBemImovelServico();
	}
	
	public List<BemImovel> obterPorIdsDB2(BemImovel entidade, Integer idInstituicao) throws BancoobException{
		return super.obterPorIdsDB2(entidade, idInstituicao);
	}
}