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
	 * Lista as produtividades a partir dos crit�rios informados.
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	List<ProdutividadeProxy> listar(ConsultaProdutividadeDTO criterios) throws BancoobException;
	
	/**
	 * Finaliza a explora��o informada no dto.
	 * @param dto as informa��es para finaliza��o da explora��o.
	 * @throws BancoobException Caso ocorra alguma exce��o
	 */
	void finalizarExploracao(ProdutividadeDTO dto, Long idProdutividade) throws BancoobException;
	
	/**
	 * Lista as produtividades com situa��o "em aberto" da pessoa informada.
	 * @param pessoaCompartilhamento a pessoa a ser consultada.
	 * @return as produtividades com situa��o "em aberto" da pessoa informada.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	List<Produtividade> listarProdutividadesEmAberto(PessoaCompartilhamento pessoaCompartilhamento) 
			throws BancoobException;
	
	/**
	 * Lista as produtividades associadas ao BemImovel informado.
	 * @param imovel O Imovel a ser verificado
	 * @return as produtividades associadas ao BemImovel informado.
	 * @throws BancoobException Caso ocorra alguma exce��o.
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
