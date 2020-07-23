package br.com.sicoob.capes.integracao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.AgenciaCafVO;
import br.com.sicoob.capes.comum.negocio.vo.BancoCafVO;
import br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO;
import br.com.sicoob.capes.comum.negocio.vo.ModalidadeProdutoVO;
import br.com.sicoob.capes.comum.negocio.vo.ProdutoInstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.ProdutoVO;

/**
 * A interface ADMIntegracaoServico.
 * 
 * @author Bruno.Carneiro
 */
public interface ADMIntegracaoServico extends CAPESIntegracaoServico {

	/**
	 * Obter banco caf.
	 * 
	 * @param numBanco
	 *            the num banco
	 * @return banco caf vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	BancoCafVO obterBancoCaf(Short numBanco) throws BancoobException;

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
	AgenciaCafVO obterAgenciaCaf(Short numBanco, Short numAgencia) throws BancoobException;

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
	boolean verificarAgenciaCaf(Integer numAgencia, Integer numBanco) throws BancoobException;

	/**
	 * Listar esferas administrativas.
	 * 
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<EsferaAdministrativaVO> listarEsferasAdministrativas() throws BancoobException;

	/**
	 * Obter esfera administrativa.
	 * 
	 * @param idEsferaAdministrativa
	 *            the id esfera administrativa
	 * @return esfera administrativa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	EsferaAdministrativaVO obterEsferaAdministrativa(Short idEsferaAdministrativa) throws BancoobException;

	/**
	 * Obter produtos instituicao.
	 * 
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<ProdutoInstituicaoVO> obterProdutosInstituicao() throws BancoobException;

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
	ModalidadeProdutoVO obterModalidadeProduto(Integer idProduto, Integer idModalidade, Integer idInstituicao, Integer idUnidadeInst) throws BancoobException;

	/**
	 * Obter produto.
	 * 
	 * @param idProduto
	 *            the id produto
	 * @return produto vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ProdutoVO obterProduto(Integer idProduto) throws BancoobException;

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
	String obterValorParametro(Integer idParametro, Integer idInstituicao) throws BancoobException;

}