/*
 * SICOOB
 * 
 * DominioDelegate.java(br.com.sicoob.capes.api.negocio.delegates.DominioDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IDominioDelegate;
import br.com.sicoob.capes.api.negocio.servicos.DominioServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.DominioVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoDominioEnum;

/**
 * Classe responsavel pelo retorno de todos as entidade denominandas dominio
 * do CAPES
 * 
 * @author Marcelo.Onofre
 */
public class DominioDelegate extends CAPESApiDelegate<DominioServico> implements IDominioDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DominioServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarDominioServico();
	}

	/**
	 * Obter dominio por tipo de dominio
	 * 
	 * @param tipoDominio
	 *            TipoDominioEnum a ser consultado
	 * @return Retorna a lista de {@link DominioVO} com codigo
	 *         {@link DominioVO#getCodigo()} e descricao
	 *         {@link DominioVO#getDescricao()}
	 * @throws BancoobException
	 */
	public List<DominioVO> obterPorTipoDominio(TipoDominioEnum tipoDominio) throws BancoobException {
		return getServico().obterByTipoDominio(tipoDominio);
	}
}