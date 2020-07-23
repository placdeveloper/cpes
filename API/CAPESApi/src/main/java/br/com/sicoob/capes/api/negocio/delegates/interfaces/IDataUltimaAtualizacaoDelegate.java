package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.DataUltimaAtualizacaoVO;

/**
 * Delegate responsavel por retornar a data da utlima atualizacao
 * 
 * @author Marcelo.Onofre
 * 
 */
public interface IDataUltimaAtualizacaoDelegate extends ICAPESApiDelegate {

	/**
	 * Metodo que consulta a ultima data de alteracao pelo ID pessoa e ID instituicao.
	 * 
	 * @param idPessoa
	 *            ID da pessoa
	 * @param idInstituicao
	 *            ID da instituicao
	 * @return Retorna a data da ultima alteracao
	 * @throws BancoobException
	 */
	Date obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Método que consulta a última data de alteração de pessoas que tenham sido atualizadas no mesmo dia ou depois da data de referencia.
	 * 
	 * @param idInstituicao
	 *            ID da instituicao
	 * @param dataReferencia
	 *            a data de referencia para filtro
	 * @param idsPessoas
	 *            IDs das pessoas
	 * @return lista com a data da ultima alteracao de cada pessoa alterada no mesmo dia ou após a data de referencia
	 * @throws BancoobException
	 */
	List<DataUltimaAtualizacaoVO> consultarPorDataReferenciaPessoas(Integer idInstituicao, Date dataReferencia, Integer... idsPessoas)
			throws BancoobException;

}