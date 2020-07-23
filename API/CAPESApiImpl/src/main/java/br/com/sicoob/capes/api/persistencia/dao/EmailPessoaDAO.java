package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO;

/**
 * A Interface EmailPessoaDAO.
 */
public interface EmailPessoaDAO extends CAPESApiDaoIF<EmailPessoaVO> {
	
	List<EmailPessoaVO> listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;
	
}