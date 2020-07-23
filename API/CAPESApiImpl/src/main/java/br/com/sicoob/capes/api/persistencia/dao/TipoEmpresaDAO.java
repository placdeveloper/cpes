package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoEmpresaVO;


public interface TipoEmpresaDAO extends CAPESApiDaoIF<TipoEmpresaVO> {
	
	List<TipoEmpresaVO> listar() throws BancoobException;
}
