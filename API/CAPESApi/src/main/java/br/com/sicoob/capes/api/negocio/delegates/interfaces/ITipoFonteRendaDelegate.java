package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoFonteRendaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface ITipoFonteRendaDelegate extends ICAPESApiDelegate {

	/**
	 * 
	 * @param codigo
	 * @return
	 * @throws BancoobException
	 */
	TipoFonteRendaVO obterTipoFonteRenda(Short codigo) throws BancoobException;

	/**
	 * Obter tipos de fonte de renda.
	 *
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	List<TipoFonteRendaVO> listar() throws BancoobException;

	/**
	 * Lista os tipos de fonte de renda por codigo do tipo da pessoa
	 * 
	 * @param codigoTipoPessoa
	 * @return
	 * @throws BancoobException
	 */
	List<TipoFonteRendaVO> listarPorTipoPessoa(Short codigoTipoPessoa) throws BancoobException;

}
