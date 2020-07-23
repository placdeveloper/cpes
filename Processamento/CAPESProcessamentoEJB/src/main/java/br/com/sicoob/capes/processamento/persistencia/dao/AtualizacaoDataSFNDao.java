package br.com.sicoob.capes.processamento.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.processamento.negocio.dto.PessoaSFNDTO;

/**
 * A Interface AtualizacaoDataSFNDao.
 */
public interface AtualizacaoDataSFNDao extends CAPESProcessamentoDaoIF {

	/**
	 * Listar pessoas data sfn atualizadas.
	 *
	 * @param numCooperativa o valor de num cooperativa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<PessoaSFNDTO> listarPessoasDataSFNAtualizadas(Integer numCooperativa) throws BancoobException;
	
	/**
	 * O método Atualizar data sfn.
	 *
	 * @param numCooperativa o valor de num cooperativa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void atualizarDataSFN(Integer numCooperativa) throws BancoobException;
}
