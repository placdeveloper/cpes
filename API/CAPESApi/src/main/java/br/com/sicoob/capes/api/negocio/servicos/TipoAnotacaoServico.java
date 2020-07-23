package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoAnotacaoVO;

/**
 * A Interface TipoAnotacaoServico.
 */
public interface TipoAnotacaoServico extends CAPESApiServico {
	
	/**
	 * Obter tipos anotacao ativos.
	 *
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<TipoAnotacaoVO> obterTiposAnotacaoAtivos() throws BancoobException;
	
	/**
	 * Obter tipo anotacao por id.
	 *
	 * @param idTipoAnotacao o valor de id tipo anotacao
	 * @return TipoAnotacaoVO
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	TipoAnotacaoVO obterTipoAnotacaoPorId(Integer idTipoAnotacao) throws BancoobException;
}