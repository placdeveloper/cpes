package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoTelefoneVO;


public interface TipoTelefoneDAO extends CAPESApiDaoIF<TipoTelefoneVO> {
	
	List<TipoTelefoneVO> listar() throws BancoobException;
}
