package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPeriodoVO;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IAnotacaoPessoaDelegate extends ICAPESApiDelegate {

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
	List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

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
	List<AnotacaoPessoaVO> obterImpeditivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * <ul>
	 * <li>Opera��o dispon�vel em <strong>Backoffice</strong>, <strong>Frontoffice</strong> e <strong>Processamento</strong></li>
	 * </ul>
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param idTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Integer idTipoAnotacao)
			throws BancoobException;

	/**
	 * <ul>
	 * <li>Opera��o dispon�vel em <strong>Backoffice</strong>, <strong>Frontoffice</strong> e <strong>Processamento</strong></li>
	 * </ul>
	 * 
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @param idTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(String cpfCnpj, Integer idInstituicao, Integer idTipoAnotacao)
			throws BancoobException;

	/**
	 * Obter anotacao por id.
	 *
	 * @param idAnotacao
	 *            o valor de id anotacao
	 * @return AnotacaoPessoaVO
	 * @throws BancoobException
	 *             lan�a a exce��o BancoobException
	 */
	AnotacaoPessoaVO obterAnotacaoPorId(Long idAnotacao) throws BancoobException;

	/**
	 * Obter nao vigentes por Pessoa instituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoAnotacao
	 * @param dataBaixa
	 * @return
	 */
	List<AnotacaoPessoaVO> obterNaoVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao,
			Date dataBaixa) throws BancoobException;

	/**
	 * obter Vigentes Por Pessoa Instituicao Tipo
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoAnotacao
	 * @return
	 */
	List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao)
			throws BancoobException;

	/**
	 * Obt�m as pessoas do grupo compartilhamento do SICOOB que tiveram uma anota��o de determinado tipo em uma determinada data
	 * 
	 * @param listaCpfCnpj
	 * @param idTipoAnotacao
	 * @param dataInicioPeriodo
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(List<String> listaCpfCnpj, Integer idTipoAnotacao, Date dataAnotacao)
			throws BancoobException;

	/**
	 * Obt�m as pessoas do grupo compartilhamento do SICOOB que tiveram determinada anota��o na data especificada.
	 * 
	 * @param idTipoAnotacao
	 * @param dataInicioPeriodo
	 * @return
	 * @throws BancoobException
	 */
	List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException;

}