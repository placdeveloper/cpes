package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemParceriasBemVO;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * Interface com os serviços de BemPessoaCompartilhamento e auxiliares.
 * 
 * @author Bruno.Carneiro
 */
public interface BemPessoaCompartilhamentoServico extends EntidadeCadastroServico<BemPessoaCompartilhamento> {

	List<DadosListagemBemVO> obterDadosListagem(Long idPessoaCompartilhamento) throws BancoobException;
	
	boolean pessoaPossuiBensCadastrados(Long idPessoaCompartilhamento) throws BancoobException;
	
	List<DadosListagemParceriasBemVO> obterDadosListagemParcerias(Long idPessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Realiza a exclusão do bem, caso seja válido
	 * 
	 * @param idBem
	 * @param idBemPessoaCompartilhamento
	 * @param bemInterno
	 * @throws BancoobException
	 */
	void excluir(Long idBem, Long idBemPessoaCompartilhamento, Boolean bemInterno) throws BancoobException;
	
}