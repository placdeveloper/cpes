package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoTelefoneVO;

public interface TipoTelefoneServico extends CAPESApiServico {
	
	List<TipoTelefoneVO> listar() throws BancoobException;
	
}
