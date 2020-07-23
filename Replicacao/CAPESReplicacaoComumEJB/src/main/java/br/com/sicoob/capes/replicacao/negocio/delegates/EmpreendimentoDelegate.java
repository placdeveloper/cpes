/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Empreendimento;
import br.com.sicoob.capes.replicacao.negocio.servicos.EmpreendimentoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * @author Erico.Junior
 * 
 */
public class EmpreendimentoDelegate extends
		CAPESReplicacaoComumCrudDelegate<Empreendimento, EmpreendimentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmpreendimentoServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarEmpreendimentoServico();	
	}

	/**
	 * Obter prod lab.
	 *
	 * @param codigo o valor de codigo
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @return Empreendimento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public Empreendimento obterProdLab(Integer codigo, Integer idCooperativaProdlab) throws BancoobException {
		return getServico().obterProdLab(codigo, idCooperativaProdlab);
	}

	/**
	 * O m�todo Incluir prod lab.
	 *
	 * @param empreendimentoReplicacao o valor de empreendimento replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void incluirProdLab(Empreendimento empreendimentoReplicacao, Integer idCooperativaProdlab) throws BancoobException {
		getServico().incluirProdLab(empreendimentoReplicacao, idCooperativaProdlab);
	}

	/**
	 * O m�todo Alterar prod lab.
	 *
	 * @param empreendimentoReplicacao o valor de empreendimento replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void alterarProdLab(Empreendimento empreendimentoReplicacao, Integer idCooperativaProdlab) throws BancoobException {
		getServico().alterarProdLab(empreendimentoReplicacao, idCooperativaProdlab);
	}
	
}
