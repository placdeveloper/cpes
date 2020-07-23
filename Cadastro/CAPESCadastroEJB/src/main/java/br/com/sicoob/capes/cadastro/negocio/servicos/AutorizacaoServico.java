package br.com.sicoob.capes.cadastro.negocio.servicos;

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

/**
 * Define as opera��es do servi�o de manipula��o de autoriza��o
 * 
 * @author Rodrigo.Chaves
 */
public interface AutorizacaoServico extends CAPESCadastroCrudServico<Autorizacao> {

	/**
	 * Realiza pesquisa por autoriza��es pendentes da institui��o do usu�rio
	 * logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesPendentes(ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * Realiza pesquisa por autoriza��es encaminhadas pela institui��o do
	 * usu�rio logado e devolvidas pela institui��o autorizadora
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesDevolvidas(ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * Realiza pesquisa por autoriza��es encaminhadas pela institui��o do
	 * usu�rio logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesEncaminhadas(ConsultaAutorizacaoDTO criterios) throws BancoobException;

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
	 * @return Retorna o dto com a lista de {@link Autorizacao} da pessoa
	 *         selecionada por institui��o
	 * @throws BancoobException
	 */
	List<Autorizacao> pesquisarAutorizacoesAptasEncaminhar(ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * Atualiza a data de solita��o da autoriza��o para o Fluxo (GFT)
	 * 
	 * @param autorizacao
	 *            {@link Autorizacao} a ser atualizada
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
	 * Realiza a altera��o, atualizando a lista de documentos
	 * 
	 * @param autorizacao
	 * @param documentos
	 */
	void alterar(Autorizacao autorizacao, Collection<AutorizacaoDocumento> documentos) throws BancoobException;

	/**
	 * Obt�m a lista de documentos em autoriza��o
	 */
	Autorizacao obterDocumentosComprobatoriosEmAutorizacao(ConsultaAutorizacaoDTO criterios) throws BancoobException;

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
	 * 
	 * @return A lista de autoriza��es
	 * @throws BancoobException
	 */
	ConsultaDto<Autorizacao> obterListaAutorizacoesVencidas(ConsultaDto<Autorizacao> criterios) throws BancoobException;

	/**
	 * Obt�m a autoriza��o 'lockando' o registro
	 * 
	 * @param chave
	 * @return
	 * @throws BancoobException
	 */
	Autorizacao obterComLock(Serializable chave) throws BancoobException;

	/**
	 * O m�todo Cancelar autorizacao vencida.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void cancelarAutorizacaoVencida(Autorizacao autorizacao) throws BancoobException;
	
	/**
	 * O m�todo Cancelar autorizacao vencida.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param justificativa a justificativa do cancelamento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void cancelarAutorizacaoVencida(Autorizacao autorizacao, String justificativa) throws BancoobException;

	/**
	 * Obter a autoriza��o, caso seja nula, lan�a exce��o.
	 * 
	 * @param chave
	 * @return
	 */
	Autorizacao obterAutorizacaoNaoNula(Serializable chave) throws BancoobException;

	/**
	 * Obtem as autoriza��es pendentes de auto atendimento.
	 * @param criterios
	 * @return
	 */
	ConsultaAutorizacaoDTO obterAutorizacoesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * Obtem a autoriza��o por IdRegistro novo
	 * @param idRegistroNovo
	 * @param devolvido
	 * @return
	 */
	Autorizacao obterAutorizacaoPorIdRegistroNovo(Long IdRegistroNovo, Boolean devolvido) throws BancoobException;

}