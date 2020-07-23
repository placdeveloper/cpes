package br.com.sicoob.capes.integracao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.SistemaCooperativoVO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;

/**
 * @author Rodrigo.Chaves
 */
public interface SCIIntegracaoServico extends CAPESIntegracaoServico {

	/**
	 * Listar unidades instituicao.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @param somenteAtivas
	 *            the somente ativas
	 * @return list
	 */
	List<UnidadeInstituicaoVO> listarUnidadesInstituicao(Integer idInstituicao, boolean somenteAtivas);

	/**
	 * Lista as unidades de instituição ignorando o PA Digital.
	 * 
	 * @param idInstituicao
	 * @param somenteAtivas
	 * @return
	 */
	List<UnidadeInstituicaoVO> listarUnidadesInstituicaoSemPADigital(Integer idInstituicao, boolean somenteAtivas);

	/**
	 * Obter sistema cooperativo.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @return sistema cooperativo vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	SistemaCooperativoVO obterSistemaCooperativo(Integer idInstituicao) throws BancoobException;

	/**
	 * Listar instituicoes por tipo.
	 * 
	 * @param codTipoInstituicao
	 *            the cod tipo instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<InstituicaoVO> listarInstituicoesPorTipo(Integer codTipoInstituicao) throws BancoobException;

	/**
	 * Verifica se é cooperativa.
	 * 
	 * @param cnpj
	 *            the cnpj
	 * @return true, se for cooperativa
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	boolean isCooperativa(String cnpj) throws BancoobException;

	/**
	 * Verifica se eh cooperativa ativa.
	 * 
	 * @param cnpj
	 *            o valor de cnpj
	 * @return {@code true}, se for cooperativa ativa
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	boolean isCooperativaAtiva(String cnpj) throws BancoobException;

	/**
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém o identificador da instituição por numCooperativa
	 * 
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	Integer obterIdInstituicao(Integer numeroCooperativa) throws BancoobException;

	/**
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	InstituicaoVO obterInstituicaoPorId(Integer idInstituicao) throws BancoobException;

	/**
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	InstituicaoVO obterInstituicaoPorNumeroCooperativa(Integer numeroCooperativa) throws BancoobException;

	/**
	 * @return
	 * @throws BancoobException
	 */
	InstituicaoVO obterInformacoesSicoob() throws BancoobException;

	/**
	 * @param ids
	 * @return
	 * @throws BancoobException
	 */
	List<InstituicaoVO> obterInsituicoesPorId(List<Integer> ids) throws BancoobException;

	/**
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	boolean isConfederacao(Integer idInstituicao) throws BancoobException;
	
	/**
	 * 
	 * 
	 * @param idInstituicao
	 *            O identificador da instituição.
	 * @param idUnidadeInst 
	 * @return A instituição a partir do idInstituicao informado.
	 */
	InstituicaoVO obterInformcoesGeraisInstituicaoSCI(Integer idInstituicao, Integer idUnidadeInst) throws BancoobException;

}