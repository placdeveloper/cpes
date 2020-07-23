package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoEnderecoVO;

public interface TipoEnderecoServico extends CAPESApiServico {
	
	List<TipoEnderecoVO> listar() throws BancoobException;
	
}