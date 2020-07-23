/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaProdutividadeDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ProdutividadeDTO;
import br.com.sicoob.capes.cadastro.negocio.proxies.ProdutividadeProxy;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author Erico.Junior
 *
 */
public interface ProdutividadeServico extends EntidadeCadastroServico<Produtividade> {

	/**
	 * Lista as produtividades a partir dos critérios informados.
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	List<ProdutividadeProxy> listar(ConsultaProdutividadeDTO criterios) throws BancoobException;
	
	/**
	 * Finaliza a exploração informada no dto.
	 * @param dto as informações para finalização da exploração.
	 * @throws BancoobException Caso ocorra alguma exceção
	 */
	void finalizarExploracao(ProdutividadeDTO dto, Long idProdutividade) throws BancoobException;
	
	/**
	 * Lista as produtividades com situação "em aberto" da pessoa informada.
	 * @param pessoaCompartilhamento a pessoa a ser consultada.
	 * @return as produtividades com situação "em aberto" da pessoa informada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	List<Produtividade> listarProdutividadesEmAberto(PessoaCompartilhamento pessoaCompartilhamento) 
			throws BancoobException;
	
	/**
	 * Lista as produtividades associadas ao BemImovel informado.
	 * @param imovel O Imovel a ser verificado
	 * @return as produtividades associadas ao BemImovel informado.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	List<String> listarProdutividadesAssociadas(BemImovel bemImovel, List<Long> idsPessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Importa a produtividade para pessoa;
	 * @param produtividade
	 * @return
	 * @throws BancoobException
	 */
	Produtividade importar(Produtividade produtividade) throws BancoobException;
	
	/**
	 * Verifica se o produtor possui produtividades associadas
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
	List<String> listarProdutividadesPorIdBem(Long idBem) throws BancoobException ;
}
