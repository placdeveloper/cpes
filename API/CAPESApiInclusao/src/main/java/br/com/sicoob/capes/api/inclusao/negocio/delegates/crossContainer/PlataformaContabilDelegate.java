package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.delegates.CrossContainerDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IPlataformaContabilDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PlataformaContabilDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.PlataformaContabilServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe PlataformaContabilDelegate.
 * 
 * @author valdemar.xavier
 */
public class PlataformaContabilDelegate extends CrossContainerDelegate<PlataformaContabilServico> implements IPlataformaContabilDelegate {
	
	/**
	 * 
	 */
	protected PlataformaContabilDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static PlataformaContabilDelegate getInstance() {
		return valueOf(PlataformaContabilDelegate.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CAPESApiInclusaoServiceLocator getLocator() {
		return CAPESApiInclusaoServiceLocator.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PlataformaContabilServico localizarServico() {
		return getLocator().localizarPlataformaContabilServico();
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