package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;

/**
 * A Interface FonteRendaPessoaDAO.
 */
public interface FonteRendaPessoaDAO extends CAPESApiDaoIF<FonteRendaPessoaVO> {

	List<FonteRendaPessoaVO> listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;
}