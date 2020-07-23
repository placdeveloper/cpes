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
 * Interface base para os serviços de Bem.
 * 
 * @author bruno.carneiro
 * 
 * @param <T>
 *            Bem
 */
public interface BemServico extends EntidadeCadastroServico<Bem> {
	
	/**
	 * Obtém as posses do bem para exibição do componente 'Consultar Posses' da
	 * tela principal.
	 * 
	 * @param idBem
	 * @param tipoClassificacaoBem
	 * @return
	 * @throws BancoobException
	 */
	PosseBemVO obterPossesBem(Long idBem, TipoClassificacaoBem tipoClassificacaoBem) throws BancoobException;

	/**
	 * Obtém a lista de {@code ProprietarioBemVO} para a tela de edição,
	 * utilizado pelo componente de pesquisa de bens.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	List<ProprietarioBemVO> obterProprietariosBem(Long idBem) throws BancoobException;

	/**
	 * Obtém os valores do bem e avaliação para exibição na tela principal.
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	ValoresBensVO obterValoresBensPessoa(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Adiciona um bem interno do tipo 'SEM PATRIMÔNIO'
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
	 * Obtém o tipo da classificação do bem informado.
	 * 
	 * @param idBem
	 * @return {@code TipoClassificacaoBemEnum} enum com o tipo da classificação
	 *         do bem informado.
	 * @throws BancoobException
	 */
	TipoClassificacaoBemEnum obterTipoClassificacaoBem(Long idBem) throws BancoobException;

	/**
	 * Obtém os relacionamentos ({@code BemImovelTipoRelacionamentoPessoa}) do
	 * bem imóvel.
	 */
	List<BemImovelTipoRelacionamentoPessoa> obterRelacionamentosBemImovel(Long idBem) throws BancoobException;
	
	/**
	 * Obtém os imóveis rurais da pessoa para preenchimento da combo na tela de
	 * produtividade.
	 * 
	 * <p><b>ATENÇÃO:</b> esse método traz uma instância de bem imóvel com apenas
	 * algumas informações básicas.</p>
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovel> obterImoveisRurais(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Verifica/Compartilha os associados do bem com a instituição logada.
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
	 * Verifica se o bem já foi incluido para a pessoa informada.
	 * 
	 * @param idBem
	 * @param idPessoaCompartilhamento
	 * @return
	 */
	boolean verificarExistenciaBemInterno(Long idBem, Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Verifica se o bem está em garantia.
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