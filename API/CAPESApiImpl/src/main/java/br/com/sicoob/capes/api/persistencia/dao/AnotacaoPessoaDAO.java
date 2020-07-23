package br.com.sicoob.capes.api.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPeriodoVO;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;

/**
 * A Interface AnotacaoPessoaDAO.
 */
public interface AnotacaoPessoaDAO extends CAPESApiDaoIF<AnotacaoPessoaVO> {

	/**
	 * Obter anotacoes vigentes por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<AnotacaoPessoaVO> obterAnotacoesVigentesPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter anotacoes por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param baixadas o valor de baixadas
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<AnotacaoPessoaVO> obterAnotacoesPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, boolean baixadas) throws BancoobException;

	/**
	 * Obter anotacoes impeditivas por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<AnotacaoPessoaVO> obterAnotacoesImpeditivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter anotacoes vigentes por pessoa instituicao tipo.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param idTipoAnotacao o valor de id tipo anotacao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<AnotacaoPessoaVO> obterAnotacoesVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException;
	
	/**
	 * Obter nao vigentes por pessoa instituicao tipo.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param codigoTipoAnotacao o valor de codigo tipo anotacao
	 * @param dataBaixa o valor de data baixa
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<AnotacaoPessoaVO> obterNaoVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao, Date dataBaixa) throws BancoobException;

	/**
	 * Obter vigentes por pessoa instituicao tipo.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param codigoTipoAnotacao o valor de codigo tipo anotacao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao) throws BancoobException;
	
	/**
	 * Obt�m as pessoas que tiveram uma anota��o de determinado tipo em uma determinada data
	 * @param listaCpfCnpj
	 * @param idTipoAnotacao
	 * @param dataInicioPeriodo
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(List<String> listaCpfCnpj, Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException;
	
	/**
	 * Obt�m as pessoas que tiveram determinada anota��o na data especificada.
	 * @param idTipoAnotacao
	 * @param dataInicioPeriodo
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException;
	
	/**
	 * obt�m a lista de anota��o por tipo de anota��o
	 * @return
	 */
	List<AnotacaoPessoaVO> obterVigentesPorTipo(Integer idPessoa, String cpfCnpj, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException;

}