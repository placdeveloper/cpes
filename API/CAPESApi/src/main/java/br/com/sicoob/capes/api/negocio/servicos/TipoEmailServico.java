package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoEmailVO;

public interface TipoEmailServico extends CAPESApiServico {
	
	List<TipoEmailVO> listar() throws BancoobException;
	
}
