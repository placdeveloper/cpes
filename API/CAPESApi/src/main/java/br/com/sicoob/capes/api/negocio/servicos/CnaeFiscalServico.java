package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.CnaeFiscalVO;

public interface CnaeFiscalServico extends CAPESApiServico {
	
	List<CnaeFiscalVO> listar() throws BancoobException;
	
	CnaeFiscalVO obterCnaeFiscalPorCodigo(String codigoCnae) throws BancoobException;
	
	List<CnaeFiscalVO> obterPorDescricao(String descricao) throws BancoobException;
}
