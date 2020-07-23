package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemParceriasBemVO;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * Interface da DAO do BemPessoaCompartilhamento
 * 
 * @author Bruno.Carneiro
 */
public interface BemPessoaCompartilhamentoDAO extends EntidadeCadastroDaoIF<BemPessoaCompartilhamento> {

	/**
	 * Faz a exclus�o dos bens marcados para exclus�o.
	 * 
	 * @param bem
	 * @throws BancoobException
	 */
	void excluirCompartilhamentosMarcados(Long idBem) throws BancoobException;

	/**
	 * Desmarca os respons�veis pelo bem ap�s a aprova��o
	 * 
	 * @param bem
	 * @throws BancoobException
	 */
	void desmarcarResponsaveisBem(Long idBem) throws BancoobException;

	/**
	 * Inclui o historico do bem pessoa compartilhamento a ser excluido.
	 * 
	 * @param idBem
	 * @param idUsuario
	 * @throws BancoobException
	 */
	void incluirHistoricoBemPessoaCompartilhamento(Long idBem, String idUsuario) throws BancoobException;
	
	/**
	 * Obt�m os dados para exibi��o na tela de listagem de bens.
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 */
	List<DadosListagemBemVO> obterDadosListagem(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Obt�m os dados de parcerias para exibi��o na tela de listagem de bens.
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 */
	List<DadosListagemParceriasBemVO> obterDadosListagemParcerias(Long idPessoaCompartilhamento) throws BancoobException;

	boolean pessoaPossuiBensCadastrados(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Obt�m o compartilhamento por idbem e idPessoaCompartilhamento
	 * @param idBem
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	BemPessoaCompartilhamento obterBemPessoaCompartilhamentoInternoIdPessoaCompartilhamento(Long idPessoaCompartilhamento) throws BancoobException;

}