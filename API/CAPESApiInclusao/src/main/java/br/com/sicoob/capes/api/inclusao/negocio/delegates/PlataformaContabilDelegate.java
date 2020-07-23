package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IPlataformaContabilDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PlataformaContabilDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.PlataformaContabilServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe PlataformaContabilDelegate.
 * 
 * @author valdemar.xavier
 */
public class PlataformaContabilDelegate extends BancoobDelegate<PlataformaContabilServico> implements IPlataformaContabilDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PlataformaContabilServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarPlataformaContabilServico();
	}
	
	
	/**
	 * Atualiza faturamento e patrimonio das instituições.
	 * 
	 * @param idInstituicao
	 *            O idInstituicao, na qual se refere a cooperativa a atualizar os dados.
	 * @param valorPatrimonio
	 *            O Valor do Patrimonio bruto da cooperativa, para ser informado na fonte de renda da cooperativa.
	 * @param valorFaturamento
	 * 			  O Valor do Faturamento bruto anual da Cooperativa, onde será convertido para mensal.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public boolean atualizarDadosContabil(PlataformaContabilDTO dto) throws BancoobException {
		return getServico().atualizarDadosContabil(dto);
	}
}