/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Rodrigo.Chaves
 */
public interface AutorizacaoDAO extends CAPESCadastroCrudDaoIF<Autorizacao> {

	/**
	 * Realiza pesquisa por autorizações pendentes da instituição do usuário
	 * logado
	 * 
	 * @param criterios
	 *            os critérios da consulta
	 * @return DTO contendo a lista de alterações
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesPendentes(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autorizações encaminhadas pela instituição do
	 * usuário logado e devolvidas pela instituição autorizadora
	 * 
	 * @param criterios
	 *            os critérios da consulta
	 * @return DTO contendo a lista de alterações
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesDevolvidas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autorizações encaminhadas pela instituição do
	 * usuário logado
	 * 
	 * @param criterios
	 *            os critérios da consulta
	 * @return DTO contendo a lista de alterações
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesEncaminhadas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autorizações que ainda não foram encaminhadas pela
	 * instituição do usuário logado
	 * 
	 * @param criterios
	 *            os critérios da consulta
	 * @return DTO contendo a lista de autorizações
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesNaoEncaminhadas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza a pesquisa por autorização que estão aptas a serem encaminhadas
	 * 
	 * @param criterios
	 *            Criterios de consulta (ID pessoa selecionada e ID da
	 *            instituição)
	 * 
	 * @return Retorna a lista de {@link Autorizacao} da pessoa selecionada por
	 *         instituição
	 * @throws BancoobException
	 */
	List<Autorizacao> pesquisarAutorizacoesAptasEncaminhar(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;
	
	/**
	 * Atualiza a data de solicitação de autorização encaminhada ao GFT
	 * 
	 * @param autorizacao
	 *            Autorização a ser atualizada
	 * 
	 * @throws BancoobException
	 */
	void atualizaDataSolicitacao(Autorizacao autorizacao) throws BancoobException;

	/**
	 * Recupera uma {@link Autorizacao} pela entidade associada
	 * 
	 * @see Aprovavel#getTipoAutorizacao()
	 * @see Aprovavel#getId()
	 * 
	 * @param aprovavel
	 *            a entidade
	 * @return a autorização, caso encontre
	 */
	Autorizacao obterPorEntidade(Aprovavel aprovavel) throws BancoobException;

	/**
	 * O método Alterar.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param documentos o valor de documentos
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void alterar(Autorizacao autorizacao, Collection<AutorizacaoDocumento> documentos)
			throws BancoobException;

	/**
	 * Obter documentos comprobatorios em autorizacao.
	 *
	 * @param criterios o valor de criterios
	 * @return Autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Autorizacao obterDocumentosComprobatoriosEmAutorizacao(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Obter tipo operacao autorizacao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return TipoOperacaoEnum
	 * @throws BancoobException lança a exceção BancoobException
	 */
	TipoOperacaoEnum obterTipoOperacaoAutorizacao(Aprovavel aprovavel) throws BancoobException;

	/**
	 * O método Deletar lista documentos.
	 *
	 * @param idAutorizacao o valor de id autorizacao
	 */
	void deletarListaDocumentos(Long idAutorizacao);

	/**
	 * O método Marcar devolvido.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param devolvido o valor de devolvido
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void marcarDevolvido(Autorizacao autorizacao, Boolean devolvido) throws BancoobException;
	
	/**
	 * Obtém as autorizações vencidas
	 * @return A lista de autorizações
	 * @throws BancoobException
	 */
	 ConsultaDto<Autorizacao> obterListaAutorizacoesVencidas(ConsultaDto<Autorizacao> criterios) throws BancoobException;
	 
	 /**
	  * Obtém a autorização 'lockando' o registro
	  * @param chave
	  * @return
	  * @throws BancoobException
	  */
	 Autorizacao obterComLock(Serializable chave) throws BancoobException;

	/**
	 * Verifica se eh pessoa pendente aprovacao.
	 *
	 * @param pessoa o valor de pessoa
	 * @return {@code true}, se for pessoa pendente aprovacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	boolean isPessoaPendenteAprovacao(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Verifica se eh pessoa possui autorizacao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @return {@code true}, se for pessoa possui autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	boolean isPessoaPossuiAutorizacao(Integer idPessoa) throws BancoobException;
	
	 /**
	  * Verifica se eh pessoa possui autorizacao, removendo a autorizacao passada,
	  *  do resultado.
	  * @param pessoa
	  * @param autorizacao
	  * @return
	  */
	boolean isPessoaPendenteAprovacaoAutorizacao(PessoaCompartilhamento pessoa, Autorizacao autorizacao);

	/**
	 * Obtem as autorizações pendentes de auto atendimento.
	 * Mobile e Internet Bank
	 * @param listaAutorizacoes
	 * @return
	 */
	ConsultaAutorizacaoDTO obterAutorizacoesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException;
	
	/**
	 * Obtem a autorização por IdRegistro novo.
	 * 
	 * @param IdRegistroNovo
	 * @param devolvido
	 * @return
	 * @throws BancoobException
	 */
	Autorizacao obterAutorizacaoPorIdRegistroNovo(Long IdRegistroNovo, Boolean devolvido) throws BancoobException;
}