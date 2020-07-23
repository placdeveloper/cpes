package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.PosseBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.ProprietarioBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.ValoresBensVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Interface base para os servi�os de Bem.
 * 
 * @author bruno.carneiro
 * 
 * @param <T>
 *            Bem
 */
public interface BemServico extends EntidadeCadastroServico<Bem> {
	
	/**
	 * Obt�m as posses do bem para exibi��o do componente 'Consultar Posses' da
	 * tela principal.
	 * 
	 * @param idBem
	 * @param tipoClassificacaoBem
	 * @return
	 * @throws BancoobException
	 */
	PosseBemVO obterPossesBem(Long idBem, TipoClassificacaoBem tipoClassificacaoBem) throws BancoobException;

	/**
	 * Obt�m a lista de {@code ProprietarioBemVO} para a tela de edi��o,
	 * utilizado pelo componente de pesquisa de bens.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	List<ProprietarioBemVO> obterProprietariosBem(Long idBem) throws BancoobException;

	/**
	 * Obt�m os valores do bem e avalia��o para exibi��o na tela principal.
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	ValoresBensVO obterValoresBensPessoa(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Adiciona um bem interno do tipo 'SEM PATRIM�NIO'
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	void adicionarBemSemPatrimonio(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;

	/**
	 * Adiciona um bem interno do tipo 'RECUSOU-SE A INFORMAR'
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	void adicionarBemRecusouInformar(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;

	/**
	 * Obt�m o tipo da classifica��o do bem informado.
	 * 
	 * @param idBem
	 * @return {@code TipoClassificacaoBemEnum} enum com o tipo da classifica��o
	 *         do bem informado.
	 * @throws BancoobException
	 */
	TipoClassificacaoBemEnum obterTipoClassificacaoBem(Long idBem) throws BancoobException;

	/**
	 * Obt�m os relacionamentos ({@code BemImovelTipoRelacionamentoPessoa}) do
	 * bem im�vel.
	 */
	List<BemImovelTipoRelacionamentoPessoa> obterRelacionamentosBemImovel(Long idBem) throws BancoobException;
	
	/**
	 * Obt�m os im�veis rurais da pessoa para preenchimento da combo na tela de
	 * produtividade.
	 * 
	 * <p><b>ATEN��O:</b> esse m�todo traz uma inst�ncia de bem im�vel com apenas
	 * algumas informa��es b�sicas.</p>
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovel> obterImoveisRurais(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Verifica/Compartilha os associados do bem com a institui��o logada.
	 * 
	 * @param idBem
	 * @throws BancoobException
	 */
	void verificarCompartilhamentoAssociados(Long idBem) throws BancoobException;
	
	/**
	 * Consulta os bens de uma determinada pessoa compartilhamento
	 * 
	 * @param idPessoaCompartilhamento
	 * @param tipoClassificacao
	 * @return List<Bem>
	 * @throws BancoobException
	 */
	List<Bem> obterBensPorPessoaCompartilhamento(Long idPessoaCompartilhamento, Short tipoClassificacao)  throws BancoobException;
	
	/**
	 * Consulta os bensImoveis de uma determinada pessoa compartilhamento
	 * 
	 * @param idPessoaCompartilhamento
	 * @return List<Bem>
	 * @throws BancoobException
	 */
	List<Bem> obterBensImoveisPorPessoaCompartilhamento(Long idPessoaCompartilhamento)  throws BancoobException;
	
	
	/**
	 * Verifica se o bem j� foi incluido para a pessoa informada.
	 * 
	 * @param idBem
	 * @param idPessoaCompartilhamento
	 * @return
	 */
	boolean verificarExistenciaBemInterno(Long idBem, Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Verifica se o bem est� em garantia.
	 * 
	 * @param idBem
	 * @return
	 */
	boolean verificarBemEmGarantia(Long idBem) throws BancoobException;

	/**
	 * Realiza o desbloqueio do bem.
	 * 
	 * @param idBem
	 *            idBem o identificador do bem informado.
	 * @throws BancoobException
	 */
	void desbloquearBem(Long idBem) throws BancoobException;

}