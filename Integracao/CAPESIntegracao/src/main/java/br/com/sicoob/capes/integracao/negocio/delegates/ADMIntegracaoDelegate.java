package br.com.sicoob.capes.integracao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.AgenciaCafVO;
import br.com.sicoob.capes.comum.negocio.vo.BancoCafVO;
import br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO;
import br.com.sicoob.capes.comum.negocio.vo.ModalidadeProdutoVO;
import br.com.sicoob.capes.comum.negocio.vo.ProdutoInstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.ProdutoVO;
import br.com.sicoob.capes.integracao.negocio.servicos.ADMIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.locator.CAPESIntegracaoServiceLocator;

/**
 * The Class ADMIntegracaoDelegate.
 * 
 * @author Bruno.Carneiro
 */
public class ADMIntegracaoDelegate extends CAPESIntegracaoDelegate<ADMIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ADMIntegracaoServico localizarServico() {
		return CAPESIntegracaoServiceLocator.getInstance().localizarADMIntegracaoServico();
	}

	/**
	 * Obter banco caf.
	 * 
	 * @param numBanco
	 *            the num banco
	 * @return banco caf vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public BancoCafVO obterBancoCaf(Short numBanco) throws BancoobException {
		return getServico().obterBancoCaf(numBanco);
	}

	/**
	 * Obter agencia caf.
	 * 
	 * @param numBanco
	 *            the num banco
	 * @param numAgencia
	 *            the num agencia
	 * @return agencia caf vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public AgenciaCafVO obterAgenciaCaf(Short numBanco, Short numAgencia) throws BancoobException {
		return getServico().obterAgenciaCaf(numBanco, numAgencia);
	}

	/**
	 * Verificar agencia caf.
	 * 
	 * @param numAgencia
	 *            the num agencia
	 * @param numBanco
	 *            the num banco
	 * @return true, em caso de sucesso
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public boolean verificarAgenciaCaf(Integer numAgencia, Integer numBanco) throws BancoobException {
		return getServico().verificarAgenciaCaf(numAgencia, numBanco);
	}

	/**
	 * Listar esferas administrativas.
	 * 
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<EsferaAdministrativaVO> listarEsferasAdministrativas() throws BancoobException {
		return getServico().listarEsferasAdministrativas();
	}

	/**
	 * Obter esfera administrativa.
	 * 
	 * @param idEsferaAdministrativa
	 *            the id esfera administrativa
	 * @return esfera administrativa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public EsferaAdministrativaVO obterEsferaAdministrativa(Short idEsferaAdministrativa) throws BancoobException {
		return getServico().obterEsferaAdministrativa(idEsferaAdministrativa);
	}

	/**
	 * Obter produtos instituicao.
	 * 
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<ProdutoInstituicaoVO> obterProdutosInstituicao() throws BancoobException {
		return getServico().obterProdutosInstituicao();
	}

	/**
	 * Obter modalidade produto.
	 * 
	 * @param idProduto
	 *            the id produto
	 * @param idModalidade
	 *            the id modalidade
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idUnidadeInst
	 *            the id unidade inst
	 * @return modalidade produto vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public ModalidadeProdutoVO obterModalidadeProduto(Integer idProduto, Integer idModalidade, Integer idInstituicao, Integer idUnidadeInst)
			throws BancoobException {
		return getServico().obterModalidadeProduto(idProduto, idModalidade, idInstituicao, idUnidadeInst);
	}

	/**
	 * Obter produto.
	 * 
	 * @param idProduto
	 *            the id produto
	 * @return produto vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public ProdutoVO obterProduto(Integer idProduto) throws BancoobException {
		return getServico().obterProduto(idProduto);
	}

	/**
	 * Obtém o registro do ValorParametro do SQL Legado de uma determinada
	 * cooperativa. <br>
	 * ID da instituição e ID do parâmetro sao obrigatórios.
	 * 
	 * @param idParametro
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public String obterValorParametro(Integer idParametro, Integer idInstituicao) throws BancoobException {
		return getServico().obterValorParametro(idParametro, idInstituicao);
	}

}