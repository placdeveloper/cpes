package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemImovelParticipanteVO;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;

public interface BemImovelDAO extends CAPESApiDao {

	List<BemImovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, boolean avancado, boolean avaliacao, Short codigoTipoBem) throws BancoobException;

	BemImovelVO obterPorIdBem(Long idBem) throws BancoobException;

	List<BemImovelParticipanteVO> obterParticipantes(Long idBem) throws BancoobException;

	/**
	 * Obtém todos os bens associados à uma determinada pessoa.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterTodosBensAssociados(Integer idPessoa, Integer idInstituicao);

}