package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoEnderecoVO;


public interface TipoEnderecoDAO extends CAPESApiDaoIF<TipoEnderecoVO> {
	
	List<TipoEnderecoVO> listar() throws BancoobException;
}
