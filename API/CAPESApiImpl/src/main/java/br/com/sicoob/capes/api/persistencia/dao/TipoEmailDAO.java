package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoEmailVO;


public interface TipoEmailDAO extends CAPESApiDaoIF<TipoEmailVO> {
	
	List<TipoEmailVO> listar() throws BancoobException;
}
