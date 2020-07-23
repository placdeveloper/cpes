package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.DominioVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoDominioEnum;

/**
 * interfacee responsavel pelo retorno de todos as entidade denominandas dominio do CAPES
 * 
 * @author Marcelo.Onofre
 */
public interface IDominioDelegate extends ICAPESApiDelegate {

	/**
	 * Obter dominio por tipo de dominio
	 * 
	 * @param tipoDominio
	 *            TipoDominioEnum a ser consultado
	 * @return Retorna a lista de {@link DominioVO} com codigo {@link DominioVO#getCodigo()} e descricao {@link DominioVO#getDescricao()}
	 * @throws BancoobException
	 */
	List<DominioVO> obterPorTipoDominio(TipoDominioEnum tipoDominio) throws BancoobException;

}