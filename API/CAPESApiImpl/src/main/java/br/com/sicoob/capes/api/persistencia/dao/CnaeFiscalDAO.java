package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.CnaeFiscalVO;

public interface CnaeFiscalDAO extends CAPESApiDaoIF<CnaeFiscalVO> {

	List<CnaeFiscalVO> listar() throws BancoobException;

	CnaeFiscalVO obterCnaeFiscalPorCodigo(String codigoCnae) throws BancoobException;
	
	List<CnaeFiscalVO> obterPorDescricao(String descricao) throws BancoobException;
}
