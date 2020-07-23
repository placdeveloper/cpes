package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO;

/**
 * A Interface TelefonePessoaDAO.
 */
public interface TelefonePessoaDAO extends CAPESApiDaoIF<TelefonePessoaVO> {
	List<TelefonePessoaVO> listarTelefonePessoaPorIdPessoaLegadoIdInstituicao(
			Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;
}