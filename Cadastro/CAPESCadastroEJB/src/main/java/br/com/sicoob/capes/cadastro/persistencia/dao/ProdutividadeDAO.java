/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author Erico.Junior
 * 
 */
public interface ProdutividadeDAO extends EntidadeCadastroDaoIF<Produtividade> {

	/**
	 * Lista as produtividades associadas ao BemImovel informado.
	 * 
	 * @param imovel
	 *            O Imovel a ser verificado
	 * @return as produtividades associadas ao BemImovel informado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	List<String> listarProdutividadesAssociadas(BemImovel bemImovel, List<Long> idsPessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Verifica se o produtor possui produtividades associadas.
	 * @param produtor
	 * @return
	 * @throws BancoobException
	 */
	boolean produtorPossuiDependencia(Produtor produtor) throws BancoobException;
	
	/**
	 * Lista as produtividades associadas ao BemAntigo informado.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	List<String> listarProdutividadesPorIdBem(Long idBem) throws BancoobException;
}
