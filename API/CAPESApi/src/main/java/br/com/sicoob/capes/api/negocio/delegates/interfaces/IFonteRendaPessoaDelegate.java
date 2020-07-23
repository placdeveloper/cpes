package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IFonteRendaPessoaDelegate extends IAbstractCAPESApiPessoaDelegate<FonteRendaPessoaVO> {

	List<FonteRendaPessoaVO> listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao)
			throws BancoobException;

}
