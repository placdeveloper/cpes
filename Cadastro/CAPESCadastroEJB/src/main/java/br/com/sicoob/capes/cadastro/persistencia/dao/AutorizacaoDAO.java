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
	 * Realiza pesquisa por autoriza��es pendentes da institui��o do usu�rio
	 * logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesPendentes(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autoriza��es encaminhadas pela institui��o do
	 * usu�rio logado e devolvidas pela institui��o autorizadora
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesDevolvidas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autoriza��es encaminhadas pela institui��o do
	 * usu�rio logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesEncaminhadas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autoriza��es que ainda n�o foram encaminhadas pela
	 * institui��o do usu�rio logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de autoriza��es
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesNaoEncaminhadas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza a pesquisa por autoriza��o que est�o aptas a serem encaminhadas
	 * 
	 * @param criterios
	 *            Criterios de consulta (ID pessoa selecionada e ID da
	 *            institui��o)
	 * 
	 * @return Retorna a lista de {@link Autorizacao} da pessoa selecionada por
	 *         institui��o
	 * @throws BancoobException
	 */
	List<Autorizacao> pesquisarAutorizacoesAptasEncaminhar(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;
	
	/**
	 * Atualiza a data de solicita��o de autoriza��o encaminhada ao GFT
	 * 
	 * @param autorizacao
	 *            Autoriza��o a ser atualizada
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
	 * @return a autoriza��o, caso encontre
	 */
	Autorizacao obterPorEntidade(Aprovavel aprovavel) throws BancoobException;

	/**
	 * O m�todo Alterar.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param documentos o valor de documentos
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void alterar(Autorizacao autorizacao, Collection<AutorizacaoDocumento> documentos)
			throws BancoobException;

	/**
	 * Obter documentos comprobatorios em autorizacao.
	 *
	 * @param criterios o valor de criterios
	 * @return Autorizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Autorizacao obterDocumentosComprobatoriosEmAutorizacao(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Obter tipo operacao autorizacao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return TipoOperacaoEnum
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	TipoOperacaoEnum obterTipoOperacaoAutorizacao(Aprovavel aprovavel) throws BancoobException;

	/**
	 * O m�todo Deletar lista documentos.
	 *
	 * @param idAutorizacao o valor de id autorizacao
	 */
	void deletarListaDocumentos(Long idAutorizacao);

	/**
	 * O m�todo Marcar devolvido.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param devolvido o valor de devolvido
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void marcarDevolvido(Autorizacao autorizacao, Boolean devolvido) throws BancoobException;
	
	/**
	 * Obt�m as autoriza��es vencidas
	 * @return A lista de autoriza��es
	 * @throws BancoobException
	 */
	 ConsultaDto<Autorizacao> obterListaAutorizacoesVencidas(ConsultaDto<Autorizacao> criterios) throws BancoobException;
	 
	 /**
	  * Obt�m a autoriza��o 'lockando' o registro
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
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	boolean isPessoaPendenteAprovacao(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Verifica se eh pessoa possui autorizacao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @return {@code true}, se for pessoa possui autorizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
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
	 * Obtem as autoriza��es pendentes de auto atendimento.
	 * Mobile e Internet Bank
	 * @param listaAutorizacoes
	 * @return
	 */
	ConsultaAutorizacaoDTO obterAutorizacoesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException;
	
	/**
	 * Obtem a autoriza��o por IdRegistro novo.
	 * 
	 * @param IdRegistroNovo
	 * @param devolvido
	 * @return
	 * @throws BancoobException
	 */
	Autorizacao obterAutorizacaoPorIdRegistroNovo(Long IdRegistroNovo, Boolean devolvido) throws BancoobException;
}