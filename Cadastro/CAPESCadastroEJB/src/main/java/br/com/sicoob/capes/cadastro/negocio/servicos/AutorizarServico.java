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
 * Define as operações do serviço de manipulação de autorizaçãopesquisarAutorizacoesDevolvidas
 * 
 * 
 * @author Rodrigo.Chaves
 */
public interface AutorizarServico extends CAPESCadastroServico {

	/**
	 * Realiza pesquisa por autorizações pendentes da instituição do usuário
	 * logado
	 * 
	 * @param criterios
	 *            os critérios da consulta
	 * @return DTO contendo a lista de alterações
	 */
	ConsultaDto<Autorizacao> pesquisarAutorizacoesPendentes(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autorizações devolvidas pela instituição autorizadora
	 * 
	 * @param criterios
	 *            os critérios da consulta
	 * @return DTO contendo a lista de alterações
	 */
	ConsultaDto<Autorizacao> pesquisarAutorizacoesDevolvidas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza pesquisa por autorizações encaminhadas pela instituição do
	 * usuário logado
	 * 
	 * @param criterios
	 *            os critérios da consulta
	 * @return DTO contendo a lista de alterações
	 */
	ConsultaDto<Autorizacao> pesquisarAutorizacoesEncaminhadas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * Realiza pesquisa por autorizações que ainda não foram encaminhadas pela instituição do
	 * usuário logado
	 * 
	 * @param criterios
	 *            os critérios da consulta
	 * @return DTO contendo a lista de autorizações
	 */
	ConsultaAutorizacaoDTO pesquisarAutorizacoesNaoEncaminhadas(ConsultaAutorizacaoDTO criterios)
			throws BancoobException;

	/**
	 * Realiza a autorização de uma alteração
	 * @param autorizacao TODO
	 * @param justificativa
	 *            A justificativa da execução do procedimento
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
	 * Realiza a pesquisa por autorização que estão aptas a serem encaminhadas
	 * 
	 * @param criterios 
	 * 		Criterios de consulta (ID pessoa selecionada e ID da instituição)
	 * 
	 * @return
	 * 		Retorna a lista de {@link Autorizacao} da pessoa selecionada por instituição
	 * @throws BancoobException 
	 */
	List<Autorizacao> pesquisarAutorizacoesAptasEncaminhar(
			ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * Método que recupera as autorizacoes e encaminha ao GFT, instanciando os
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
	 * Atualiza a instituição de destino da aplicação caso seja necessário.
	 * 
	 * <p>
	 * <b>Esse método será utilizado apenas para a API de inclusão.</b>
	 * </p>
	 * 
	 * @param criterios
	 * @throws BancoobException
	 */
	void atualizarResponsavelAutorizacao(ConsultaAutorizacaoDTO criterios) throws BancoobException;

	/**
	 * Método que encaminha a autorizacao ao GFT, instanciando o respectivo
	 * fluxo.
	 * <p>
	 * <strong>ATENCAO!</strong> Este metodo utiliza uma transação nova para evitar
	 * timeout quando a lista de autorizacoes for demasiadamente grande.
	 * </p>
	 * 
	 * @param autorizacao a autorizacao a ser encaminhada
	 * @see #encaminharAutorizacoes(ConsultaAutorizacaoDTO)
	 */
	void encaminharAutorizacao(Autorizacao autorizacao) throws BancoobException;
	
	/**
	 * Método que encaminha a autorizacao ao GFT, instanciando o respectivo
	 * fluxo.
	 * <p>
	 * <strong>ATENCAO!</strong> Este metodo utiliza uma transação nova para evitar
	 * timeout quando a lista de autorizacoes for demasiadamente grande.
	 * </p>
	 * 
	 * @param autorizacao a autorizacao a ser encaminhada
	 * @param usuario bancoob
	 * @see #encaminharAutorizacoes(ConsultaAutorizacaoDTO)
	 */
	void encaminharAutorizacao(Autorizacao autorizacao, String usuario) throws BancoobException;
	
	/**
	 * Método para excluir a autorização selecionada, antes de ser encaminhada.
	 * 
	 * Esse método é chamado na tela de encaminhar autorizações e foi adicionado
	 * devido à falta de um botão excluir para os itens que não possuem listagem
	 * (JSON). Ex: Produtor, Pessoa Física, etc.
	 * 
	 * <p><strong>Obs:</strong> esse método chama a estratégia de rejeição, que no caso, apaga a
	 * autorização e desbloqueia o registro antigo.</p>
	 * 
	 */
	void excluirAutorizacaoEncaminhada(EncaminharAutorizacaoVO vo) throws BancoobException;
	
	/**
	 * Adiciona o ID usuário envio.
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
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ConsultaDto<Autorizacao> pesquisarAutorizacoesPendentesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException;

	
	/**
	 * Obtem autorização por IdRegistro novo
	 * 
	 * @param idRegistroNovo
	 * @param devolvido
	 * @return
	 * @throws BancoobException 
	 */
	Autorizacao obterAutorizacaoPorIdRegistroNovo(Long IdRegistroNovo, Boolean devolvido) throws BancoobException;

}