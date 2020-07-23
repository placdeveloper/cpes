package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoFonteRendaVO;

public interface TipoFonteRendaDAO extends CAPESApiDaoIF<TipoFonteRendaVO> {

	List<TipoFonteRendaVO> listar() throws BancoobException;

	List<TipoFonteRendaVO> listarPorTipoPessoa(Short codigoTipoPessoa) throws BancoobException;

	TipoFonteRendaVO obterTipoFonteRenda(Short codigo) throws BancoobException;
	
}