package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.vo.EncaminharAutorizacaoVO;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * Define as opera��es do servi�o de manipula��o de autoriza��opesquisarAutorizacoesDevolvidas
 * 
 * 
 * @author Rodrigo.Chaves
 */
public interface AutorizarServico extends CAPESCadastroServico {

	/**
	 * Realiza pesquisa por autoriza��es pendentes da institui��o do usu�rio
	 * logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	ConsultaDto<Autorizacao> pesquisarAutorizacoesPendentes(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autoriza��es devolvidas pela institui��o autorizadora
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	ConsultaDto<Autorizacao> pesquisarAutorizacoesDevolvidas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autoriza��es encaminhadas pela institui��o do
	 * usu�rio logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	ConsultaDto<Autorizacao> pesquisarAutorizacoesEncaminhadas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * Realiza pesquisa por autoriza��es que ainda n�o foram encaminhadas pela institui��o do
	 * usu�rio logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de autoriza��es
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesNaoEncaminhadas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza a autoriza��o de uma altera��o
	 * @param autorizacao TODO
	 * @param justificativa
	 *            A justificativa da execu��o do procedimento
	 * @param idProcedimento
	 *            o ID do {@link Procedimento} a ser executado
	 * 
	 * @param <A>
	 *            Classe da hierarquia de
	 *            {@link CAPESCadastroEntidade} que implemente
	 *            {@link Aprovavel}
	 */
	void executarProcedimento(Autorizacao autorizacao, OcorrenciaAtividadeVO procedimento, String justificativa) throws BancoobException;

	/**
	 * Realiza a pesquisa por autoriza��o que est�o aptas a serem encaminhadas
	 * 
	 * @param criterios 
	 * 		Criterios de consulta (ID pessoa selecionada e ID da institui��o)
	 * 
	 * @return
	 * 		Retorna a lista de {@link Autorizacao} da pessoa selecionada por institui��o
	 * @throws BancoobException 
	 */
	List<Autorizacao> pesquisarAutorizacoesAptasEncaminhar(
			ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * M�todo que recupera as autorizacoes e encaminha ao GFT, instanciando os
	 * respectivos fluxos.
	 * <p>
	 * <strong>ATENCAO!</strong> Este metodo realiza um {@code commit} para
	 * cada autorizacao encaminhada, para evitar timeout quando a lista de
	 * autorizacoes for demasiadamente grande.
	 * </p>
	 * 
	 * @param criterios
	 *            Os criterios para recuperar as autorizacoes
	 */
	boolean encaminharAutorizacoes(ConsultaAutorizacaoDTO criterios) throws BancoobException;
	
	/**
	 * Verifica se existem autorizacoes a serem encaminhadas. Caso existam
	 * verifica se elas precisam ter o responsavel (instituicao de destino)
	 * atualizado e atualiza (em caso positivo)
	 * 
	 * @param criterios
	 *            Os criterios para localizacao das autorizacoes
	 * @return {@code true} caso o responsavel tenha sido atualizado
	 */
	boolean atualizarResponsavel(ConsultaAutorizacaoDTO criterios) throws BancoobException;
	
	/**
	 * Atualiza a institui��o de destino da aplica��o caso seja necess�rio.
	 * 
	 * <p>
	 * <b>Esse m�todo ser� utilizado apenas para a API de inclus�o.</b>
	 * </p>
	 * 
	 * @param criterios
	 * @throws BancoobException
	 */
	void atualizarResponsavelAutorizacao(ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * M�todo que encaminha a autorizacao ao GFT, instanciando o respectivo
	 * fluxo.
	 * <p>
	 * <strong>ATENCAO!</strong> Este metodo utiliza uma transa��o nova para evitar
	 * timeout quando a lista de autorizacoes for demasiadamente grande.
	 * </p>
	 * 
	 * @param autorizacao a autorizacao a ser encaminhada
	 * @see #encaminharAutorizacoes(ConsultaAutorizacaoDTO)
	 */
	void encaminharAutorizacao(Autorizacao autorizacao) throws BancoobException;
	
	/**
	 * M�todo que encaminha a autorizacao ao GFT, instanciando o respectivo
	 * fluxo.
	 * <p>
	 * <strong>ATENCAO!</strong> Este metodo utiliza uma transa��o nova para evitar
	 * timeout quando a lista de autorizacoes for demasiadamente grande.
	 * </p>
	 * 
	 * @param autorizacao a autorizacao a ser encaminhada
	 * @param usuario bancoob
	 * @see #encaminharAutorizacoes(ConsultaAutorizacaoDTO)
	 */
	void encaminharAutorizacao(Autorizacao autorizacao, String usuario) throws BancoobException;
	
	/**
	 * M�todo para excluir a autoriza��o selecionada, antes de ser encaminhada.
	 * 
	 * Esse m�todo � chamado na tela de encaminhar autoriza��es e foi adicionado
	 * devido � falta de um bot�o excluir para os itens que n�o possuem listagem
	 * (JSON). Ex: Produtor, Pessoa F�sica, etc.
	 * 
	 * <p><strong>Obs:</strong> esse m�todo chama a estrat�gia de rejei��o, que no caso, apaga a
	 * autoriza��o e desbloqueia o registro antigo.</p>
	 * 
	 */
	void excluirAutorizacaoEncaminhada(EncaminharAutorizacaoVO vo) throws BancoobException;
	
	/**
	 * Adiciona o ID usu�rio envio.
	 * 
	 * @param autorizacao
	 * @throws BancoobException
	 */
	void incluirUsuarioEnvioAutorizacao(Autorizacao autorizacao) throws BancoobException;
	
	/**
	 * Pesquisar autorizacoes somente canais de auto atendimento.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	ConsultaDto<Autorizacao> pesquisarAutorizacoesPendentesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException;

	
	/**
	 * Obtem autoriza��o por IdRegistro novo
	 * 
	 * @param idRegistroNovo
	 * @param devolvido
	 * @return
	 * @throws BancoobException 
	 */
	Autorizacao obterAutorizacaoPorIdRegistroNovo(Long IdRegistroNovo, Boolean devolvido) throws BancoobException;

}