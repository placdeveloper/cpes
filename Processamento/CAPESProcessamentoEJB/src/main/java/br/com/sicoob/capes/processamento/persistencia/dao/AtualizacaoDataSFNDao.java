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
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<PessoaSFNDTO> listarPessoasDataSFNAtualizadas(Integer numCooperativa) throws BancoobException;
	
	/**
	 * O m�todo Atualizar data sfn.
	 *
	 * @param numCooperativa o valor de num cooperativa
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void atualizarDataSFN(Integer numCooperativa) throws BancoobException;
}
