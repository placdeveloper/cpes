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
 * Interface de Serviço para a data da ultima atualizacao
 * 
 * @author Marcelo.Onofre
 * 
 */
public interface DataUltimaAtualizacaoServico extends CAPESApiServico {

	/**
	 * Método que consulta a última data de alteração pelo ID pessoa e ID instituição.
	 * 
	 * @param idPessoa
	 *            ID da pessoa
	 * @param idInstituicao
	 *            ID da instituição
	 * @return Retorna a data da última alteração
	 * @throws BancoobException
	 */
	Date obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Método que consulta a última data de alteração de pessoas que tenham sido atualizadas no mesmo dia ou depois da
	 * data de referencia.
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
	List<DataUltimaAtualizacaoVO> consultarPorDataReferenciaPessoas(Integer idInstituicao, Date dataReferencia,
			Integer... idsPessoas) throws BancoobException;
}
