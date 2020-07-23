package br.com.sicoob.capes.api.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.DataUltimaAtualizacaoVO;

/**
 * A Interface DataUltimaAtualizacaoDAO.
 */
public interface DataUltimaAtualizacaoDAO extends CAPESApiDaoIF<DataUltimaAtualizacaoVO> {
	
	/**
	 * Obter data ultima atualizacao por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return Date
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Date obterDataUltimaAtualizacaoPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Método que consulta a última data de alteração de pessoas que tenham sido atualizadas no mesmo dia ou depois da
	 * data de referencia.
	 *  
	 * @param idInstituicao
	 *            ID da instituicao
	 * @param dataReferencia
	 *            a data de referencia para filtro
	 * @param idsPessoa
	 *            IDs das pessoas
	 * @return lista com a data da ultima alteracao de cada pessoa alterada no mesmo dia ou após a data de referencia
	 * @throws BancoobException
	 */
	List<DataUltimaAtualizacaoVO> consultarDatasUltimaAtualizacaoPorDataReferenciaPessoas(Integer idInstituicao,
			Date dataReferencia, Integer... idsPessoas) throws BancoobException;

}