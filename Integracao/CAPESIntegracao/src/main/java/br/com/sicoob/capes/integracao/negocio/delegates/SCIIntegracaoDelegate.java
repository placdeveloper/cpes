package br.com.sicoob.capes.integracao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.SistemaCooperativoVO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.servicos.SCIIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.locator.CAPESIntegracaoServiceLocator;

/**
 * @author Rodrigo.Chaves
 */
public class SCIIntegracaoDelegate extends CAPESIntegracaoDelegate<SCIIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SCIIntegracaoServico localizarServico() {
		return CAPESIntegracaoServiceLocator.getInstance().localizarSCIIntegracaoServico();
	}

	/**
	 * Listar unidades instituicao.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @param somenteAtivas
	 *            the somente ativas
	 * @return list
	 */
	public List<UnidadeInstituicaoVO> listarUnidadesInstituicao(Integer idInstituicao, boolean somenteAtivas) {
		return getServico().listarUnidadesInstituicao(idInstituicao, somenteAtivas);
	}

	/**
	 * Lista as unidades de instituição ignorando o PA Digital.
	 * 
	 * @param idInstituicao
	 * @param somenteAtivas
	 * @return
	 */
	public List<UnidadeInstituicaoVO> listarUnidadesInstituicaoSemPADigital(Integer idInstituicao, boolean somenteAtivas) {
		return getServico().listarUnidadesInstituicaoSemPADigital(idInstituicao, somenteAtivas);
	}

	/**
	 * Obter sistema cooperativo.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @return sistema cooperativo vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public SistemaCooperativoVO obterSistemaCooperativo(Integer idInstituicao) throws BancoobException {
		return getServico().obterSistemaCooperativo(idInstituicao);
	}

	/**
	 * Listar instituicoes por tipo.
	 * 
	 * @param codTipoInstituicao
	 *            the cod tipo instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<InstituicaoVO> listarInstituicoesPorTipo(Integer codTipoInstituicao) throws BancoobException {
		return getServico().listarInstituicoesPorTipo(codTipoInstituicao);
	}

	/**
	 * Verifica se é cooperativa.
	 * 
	 * @param cnpj
	 *            the cnpj
	 * @return true, se for cooperativa
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public boolean isCooperativa(String cnpj) throws BancoobException {
		return getServico().isCooperativa(cnpj);
	}

	/**
	 * Recupera o número da cooperativa a partir do idInstituicao informado.
	 * 
	 * @param instituicao
	 *            A instituição.
	 * @return O número da cooperativa a partir do idInstituicao informado.
	 */
	public Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException {
		return getServico().obterNumeroCooperativa(idInstituicao);
	}

	/**
	 * Recupera o identificador da cooperativa a partir do número da
	 * cooperativa.
	 * 
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterIdInstituicao(Integer numeroCooperativa) throws BancoobException {
		return getServico().obterIdInstituicao(numeroCooperativa);
	}

	/**
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public InstituicaoVO obterInstituicaoPorId(Integer idInstituicao) throws BancoobException {
		return getServico().obterInstituicaoPorId(idInstituicao);
	}

	/**
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	public InstituicaoVO obterInstituicaoPorNumeroCooperativa(Integer numeroCooperativa) throws BancoobException {
		return getServico().obterInstituicaoPorNumeroCooperativa(numeroCooperativa);
	}

	/**
	 * @return
	 * @throws BancoobException
	 */
	public InstituicaoVO obterInformacoesSicoob() throws BancoobException {
		return getServico().obterInformacoesSicoob();
	}

	/**
	 * @param ids
	 * @return
	 * @throws BancoobException
	 */
	public List<InstituicaoVO> obterInsituicoesPorId(List<Integer> ids) throws BancoobException {
		return getServico().obterInsituicoesPorId(ids);
	}

	/**
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public boolean isConfederacao(Integer idInstituicao) throws BancoobException {
		return getServico().isConfederacao(idInstituicao);
	}

	/**
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public InstituicaoVO obterInformcoesGeraisInstituicaoSCI (Integer idInstituicao, Integer idUnidadeInst) throws BancoobException {
		return getServico().obterInformcoesGeraisInstituicaoSCI(idInstituicao, idUnidadeInst);
	}

}