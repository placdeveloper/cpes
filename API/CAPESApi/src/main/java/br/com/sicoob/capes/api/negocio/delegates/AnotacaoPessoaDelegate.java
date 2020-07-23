/*
 * SICOOB
 * 
 * AnotacaoPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.AnotacaoPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IAnotacaoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.AnotacaoPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPeriodoVO;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;

/**
 * @author Lucas.Borges
 */
public class AnotacaoPessoaDelegate extends CAPESApiDelegate<AnotacaoPessoaServico> implements IAnotacaoPessoaDelegate {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoPessoaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarAnotacaoPessoaServico();
	}

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
	public List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterVigentesByPessoaInstituicao(idPessoa, idInstituicao);
	}
	
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
	public List<AnotacaoPessoaVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, boolean baixadas) throws BancoobException {
		return getServico().obterPorPessoaInstituicao(idPessoa, idInstituicao, baixadas);
	}

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
	public List<AnotacaoPessoaVO> obterImpeditivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterImpeditivasByPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * <ul>
	 * <li>Operação disponível em <strong>Backoffice</strong>,
	 * <strong>Frontoffice</strong> e <strong>Processamento</strong></li>
	 * </ul>
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param idTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	public List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException {
		return getServico().obterVigentesByPessoaInstituicaoTipo(idPessoa, idInstituicao, idTipoAnotacao);
	}
	
	/**
	 * <ul>
	 * <li>Operação disponível em <strong>Backoffice</strong>,
	 * <strong>Frontoffice</strong> e <strong>Processamento</strong></li>
	 * </ul>
	 * 
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @param idTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	public List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(String cpfCnpj, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException {
		return getServico().obterVigentesByPessoaInstituicaoTipo(cpfCnpj, idInstituicao, idTipoAnotacao);
	}

	/**
	 * Obter anotacao por id.
	 *
	 * @param idAnotacao o valor de id anotacao
	 * @return AnotacaoPessoaVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public AnotacaoPessoaVO obterAnotacaoPorId(Long idAnotacao) throws BancoobException {
		return getServico().obterAnotacaoPorId(idAnotacao);
	}
	
	/**
	 * Obter nao vigentes por Pessoa instituicao
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoAnotacao
	 * @param dataBaixa
	 * @return
	 */
	public List<AnotacaoPessoaVO> obterNaoVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao, Date dataBaixa) throws BancoobException {
		return getServico().obterNaoVigentesPorPessoaInstituicaoTipo(idPessoa, idInstituicao, codigoTipoAnotacao, dataBaixa);
	}
	
	/**
	 * obter Vigentes Por Pessoa Instituicao Tipo
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoAnotacao
	 * @return
	 */
	public List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao) throws BancoobException {
		return getServico().obterVigentesPorPessoaInstituicaoTipo(idPessoa, idInstituicao, codigoTipoAnotacao);
	}
	
	/**
	 * Obtém as pessoas do grupo compartilhamento do SICOOB que tiveram uma anotação de determinado tipo em uma determinada data
	 * @param listaCpfCnpj
	 * @param idTipoAnotacao
	 * @param dataInicioPeriodo
	 * @return
	 * @throws BancoobException
	 */
	public List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(List<String> listaCpfCnpj, Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException {
		return getServico().obterPessoasSicoobPorTipoAnotacaoPeriodo(listaCpfCnpj, idTipoAnotacao, dataAnotacao);
	}
	
	/**
	 * Obtém as pessoas do grupo compartilhamento do SICOOB que tiveram determinada anotação na data especificada.
	 * @param idTipoAnotacao
	 * @param dataInicioPeriodo
	 * @return
	 * @throws BancoobException
	 */
	public List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException {
		return getServico().obterPessoasSicoobPorTipoAnotacaoPeriodo(idTipoAnotacao, dataAnotacao);
	}

}