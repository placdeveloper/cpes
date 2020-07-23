package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.BemPessoaCompartilhamentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemParceriasBemVO;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * A delegate para os serviços de BemPessoaCompartilhamento.
 * 
 * @author Bruno.Carneiro
 */
public class BemPessoaCompartilhamentoDelegate extends EntidadeCadastroDelegate<BemPessoaCompartilhamento, BemPessoaCompartilhamentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BemPessoaCompartilhamentoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarBemPessoaCompartilhamentoServico();
	}
	
	/**
	 * Obtém os dados para listagem
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public List<DadosListagemBemVO> obterDadosListagem(Long idPessoaCompartilhamento) throws BancoobException {
		return getServico().obterDadosListagem(idPessoaCompartilhamento);
	}

	/**
	 * Obtém os dados da parceria para listagem
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public List<DadosListagemParceriasBemVO> obterDadosListagemParcerias(long idPessoaCompartilhamento) throws BancoobException {
		return getServico().obterDadosListagemParcerias(idPessoaCompartilhamento);
	}
	
	/**
	 * Realiza a exclusão, caso seja válido.
	 * @param idBem
	 * @param idBemPessoaCompartilhamento
	 * @param bemInterno
	 * @throws BancoobException
	 */
	public void excluir(Long idBem, Long idBemPessoaCompartilhamento, Boolean bemInterno) throws BancoobException {
		getServico().excluir(idBem, idBemPessoaCompartilhamento, bemInterno);
	}

}