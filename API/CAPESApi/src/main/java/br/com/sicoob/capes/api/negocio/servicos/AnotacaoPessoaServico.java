/*
 * SICOOB
 * 
 * AnotacaoPessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.AnotacaoPessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPeriodoVO;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;

/**
 * @author Lucas.Borges
 */
public interface AnotacaoPessoaServico extends CAPESApiServico {

	/**
	 * Obter vigentes por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<AnotacaoPessoaVO> obterVigentesByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter vigentes por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<AnotacaoPessoaVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, boolean baixadas) throws BancoobException;

	/**
	 * Obter impeditivas por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<AnotacaoPessoaVO> obterImpeditivasByPessoaInstituicao(
			Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter vigentes por pessoa instituicao tipo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idTipoAnotacao
	 *            the id tipo anotacao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<AnotacaoPessoaVO> obterVigentesByPessoaInstituicaoTipo(
			Integer idPessoa, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException;
	
	/**
	 * Obter vigentes por pessoa instituicao tipo.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idTipoAnotacao
	 *            the id tipo anotacao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<AnotacaoPessoaVO> obterVigentesByPessoaInstituicaoTipo(
			String cpfCnpj, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException;
	
	/**
	 * Obter anotacao por id.
	 *
	 * @param idAnotacao o valor de id anotacao
	 * @return AnotacaoPessoaVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	AnotacaoPessoaVO obterAnotacaoPorId(Long idAnotacao) throws BancoobException;

	/**
	 * Obter nao vigentes por pessoa instituicao tipo.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param codigoTipoAnotacao o valor de codigo tipo anotacao
	 * @param dataBaixa o valor de data baixa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<AnotacaoPessoaVO> obterNaoVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao, Date dataBaixa) throws BancoobException;

	/**
	 * Obter vigentes por pessoa instituicao tipo.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param codigoTipoAnotacao o valor de codigo tipo anotacao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao) throws BancoobException;
	
	/**
	 * Obtém as pessoas do grupo compartilhamento do SICOOB que tiveram uma anotação de determinado tipo em uma determinada data
	 * @param listaCpfCnpj
	 * @param idTipoAnotacao
	 * @param dataInicioPeriodo
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(List<String> listaCpfCnpj, Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException;
	
	/**
	 * Obtém as pessoas do grupo compartilhamento do SICOOB que tiveram determinada anotação na data especificada.
	 * @param idTipoAnotacao
	 * @param dataInicioPeriodo
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException;
	
}