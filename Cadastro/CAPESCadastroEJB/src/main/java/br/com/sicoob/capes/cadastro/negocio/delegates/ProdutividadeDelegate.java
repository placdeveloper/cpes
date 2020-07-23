/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaProdutividadeDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ProdutividadeDTO;
import br.com.sicoob.capes.cadastro.negocio.proxies.ProdutividadeProxy;
import br.com.sicoob.capes.cadastro.negocio.servicos.ProdutividadeServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author Erico.Junior
 * 
 */
public class ProdutividadeDelegate extends
		EntidadeCadastroDelegate<Produtividade, ProdutividadeServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutividadeServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarProdutividadeServico();
	}

	/**
	 * Lista as produtividades a partir dos critérios informados.
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	public List<ProdutividadeProxy> listar(ConsultaProdutividadeDTO criterios) throws BancoobException {
		return getServico().listar(criterios);
	}
	
	/**
	 * Finaliza a exploração informada no dto.
	 * @param dto as informações para finalização da exploração.
	 * @throws BancoobException Caso ocorra alguma exceção
	 */	
	public void finalizarExploracao(ProdutividadeDTO dto) throws BancoobException {
		getServico().finalizarExploracao(dto, dto.getIdProdutividade());
	}	
	
	/**
	 * Lista as produtividades com situação "em aberto" da pessoa informada.
	 * @param pessoaCompartilhamento a pessoa a ser consultada.
	 * @return as produtividades com situação "em aberto" da pessoa informada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public List<Produtividade> listarProdutividadesEmAberto(PessoaCompartilhamento pessoaCompartilhamento) 
			throws BancoobException {
		return getServico().listarProdutividadesEmAberto(pessoaCompartilhamento);
	}	
	
	/**
	 * Importa a produtividade para pessoa;
	 * @param produtividade
	 * @return
	 * @throws BancoobException
	 */
	public Produtividade importar(Produtividade produtividade) throws BancoobException{
		return getServico().importar(produtividade);
	}
	
	/**
	 * Verifica se o produtor possui produtividades associadas.
	 * @param produtor
	 * @return
	 * @throws BancoobException
	 */
	public boolean produtorPossuiDependencia(Produtor produtor) throws BancoobException {
		return getServico().produtorPossuiDependencia(produtor);
	}
	
	public List<String> listarProdutividadesAssociadas(BemImovel bemImovel, List<Long> idsPessoaCompartilhamento) throws BancoobException {
		return getServico().listarProdutividadesAssociadas(bemImovel, idsPessoaCompartilhamento);
	}
	public List<String> listarProdutividadesPorIdBem(Long idBem) throws BancoobException {
		return getServico().listarProdutividadesPorIdBem(idBem);
	}
}
