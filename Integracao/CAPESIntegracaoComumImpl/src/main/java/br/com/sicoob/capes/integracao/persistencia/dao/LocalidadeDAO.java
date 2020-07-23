package br.com.sicoob.capes.integracao.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO;

public interface LocalidadeDAO {

	/**
	 * Listar localidades.
	 * 
	 * @param cooperativa
	 * @return
	 * @throws BancoobException
	 */
	List<LocalidadeVO> listarLocalidade(Integer cooperativa) throws BancoobException;
}
