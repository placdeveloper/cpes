package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.ProprietarioBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.ValoresBensVO;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;

/**
 * Inteface base para as DAO's do {@link Bem}.
 * 
 * @author Bruno.Carneiro
 * 
 * @param <T>
 */
public interface BemDAO extends EntidadeCadastroDaoIF<Bem> {
	
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
	 * Obtém os participantes do bem imóvel.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	List<ProprietarioBemVO> obterParticipantesBemImovel(Long idBem) throws BancoobException;

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
	Long obterIdBemSemPatrimonio(Short codigoCompartilhamentoCadastro) throws BancoobException;

	/**
	 * Adiciona um bem interno do tipo 'RECUSOU-SE A INFORMAR'
	 * 
	 * @param pessoaCompartilhamento
	 * @throws BancoobException
	 */
	Long obterIdBemRecusouSeInformar(Short codigoCompartilhamentoCadastro) throws BancoobException;

	/**
	 * Obtém o tipo da classificação do bem informado.
	 * 
	 * @param idBem
	 * @return {@code TipoClassificacaoBemEnum} enum com o tipo da classificação
	 *         do bem informado.
	 * @throws BancoobException
	 */
	Short obterTipoClassificacaoBem(Long idBem) throws BancoobException;
	
	/**
	 * Obtém os tipos de relacionamento do bem imóvel.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelTipoRelacionamentoPessoa> obterRelacionamentosBemImovel(Long idBem) throws BancoobException;

	/**
	 * Obtém os imóveis rurais da pessoa
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 */
	List<BemImovel> obterImoveisRurais(Long idPessoaCompartilhamento) throws BancoobException;

	/**
	 * Verifica se o bem já foi incluido para a pessoa informada.
	 * 
	 * @param idBem
	 * @param idPessoaCompartilhamento
	 * @return
	 */
	boolean verificarExistenciaBemInterno(Long idBem, Long idPessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Obtém o id do bem interno.
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	Long obterIdBemInterno(Long idPessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Consulta os bens de uma determinada pessoa compartilhamento
	 * 
	 * @param idPessoaCompartilhamento
	 * @param tipoClassificacao
	 * @return List<Bem>
	 * @throws BancoobException
	 */
	List<Bem> obterBensPorPessoaCompartilhamento(Long idPessoaCompartilhamento, Short tipoClassificacao) throws BancoobException;
	
	/**
	 * Consulta os bensImoveis de uma determinada pessoa compartilhamento
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	List<Bem> obterBensImoveisPorPessoaCompartilhamento(Long idPessoaCompartilhamento) throws BancoobException;
	

	/**
	 * Verifica se o bem está em garantia.
	 * 
	 * @param idBem
	 * @return
	 */
	boolean verificarBemEmGarantia(Long idBem)throws BancoobException;
	
	/**
	 * Insere o histórico de quem desbloqueou o bem.
	 * 
	 * @param idBem
	 * @param idUsuario
	 * @throws BancoobException
	 */
	void inserirHistoricoDesbloqueioBem(Long idBem, String idUsuario) throws BancoobException;
	
	/**
	 * Retorna a lista de pessoas sem bens vinculados.
	 * 
	 * @return
	 * @throws BancoobException
	 */
	List<Object[]> obterPessoaSemBensVinculados() throws BancoobException;
	
	/**
	 * Retorna a quantidade pessoas sem bens vinculados.
	 * 
	 * @return
	 * @throws BancoobException
	 */
	Number quantidadePessoasSemBensVinculados() throws BancoobException;
	
}