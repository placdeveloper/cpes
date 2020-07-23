/*
 * SICOOB
 * 
 * DataUltimaAtualizacaoServico.java(br.com.sicoob.capes.api.negocio.servicos.DataUltimaAtualizacaoServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.DataUltimaAtualizacaoVO;

/**
 * Interface de Servi�o para a data da ultima atualizacao
 * 
 * @author Marcelo.Onofre
 * 
 */
public interface DataUltimaAtualizacaoServico extends CAPESApiServico {

	/**
	 * M�todo que consulta a �ltima data de altera��o pelo ID pessoa e ID institui��o.
	 * 
	 * @param idPessoa
	 *            ID da pessoa
	 * @param idInstituicao
	 *            ID da institui��o
	 * @return Retorna a data da �ltima altera��o
	 * @throws BancoobException
	 */
	Date obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * M�todo que consulta a �ltima data de altera��o de pessoas que tenham sido atualizadas no mesmo dia ou depois da
	 * data de referencia.
	 * 
	 * @param idInstituicao
	 *            ID da instituicao
	 * @param dataReferencia
	 *            a data de referencia para filtro
	 * @param idsPessoas
	 *            IDs das pessoas
	 * @return lista com a data da ultima alteracao de cada pessoa alterada no mesmo dia ou ap�s a data de referencia
	 * @throws BancoobException
	 */
	List<DataUltimaAtualizacaoVO> consultarPorDataReferenciaPessoas(Integer idInstituicao, Date dataReferencia,
			Integer... idsPessoas) throws BancoobException;
}
