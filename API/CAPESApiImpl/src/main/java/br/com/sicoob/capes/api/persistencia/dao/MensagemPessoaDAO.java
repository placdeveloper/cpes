package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.MensagemPessoaVO;

/**
 * A Interface MensagemPessoaDAO.
 */
public interface MensagemPessoaDAO extends CAPESApiDaoIF<MensagemPessoaVO> {

	/**
	 * Obter mensagens ativas por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param codigoTipoMensagem o valor de codigo tipo mensagem
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<MensagemPessoaVO> obterMensagensAtivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoMensagem) throws BancoobException;
	
}