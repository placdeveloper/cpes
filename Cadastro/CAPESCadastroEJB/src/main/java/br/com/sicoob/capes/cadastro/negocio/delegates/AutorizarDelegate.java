package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.AutorizarServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.EncaminharAutorizacaoVO;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;

/**
 * Business delegate para as autoriza��es
 * 
 * @author Rodrigo.Chaves
 */
public class AutorizarDelegate extends CAPESCadastroDelegate<AutorizarServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AutorizarServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarAutorizarServico();
	}

	/**
	 * Realiza pesquisa por autoriza��es pendentes da institui��o do usu�rio
	 * logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	public ConsultaDto<Autorizacao>
			pesquisarAutorizacoesPendentes(ConsultaAutorizacaoDTO criterios)
					throws BancoobException {

		return getServico().pesquisarAutorizacoesPendentes(criterios);
	}

	/**
	 * Pesquisar autorizacoes devolvidas.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public ConsultaDto<Autorizacao>
			pesquisarAutorizacoesDevolvidas(ConsultaAutorizacaoDTO criterios)
					throws BancoobException {

		return getServico().pesquisarAutorizacoesDevolvidas(criterios);
	}

	/**
	 * Realiza pesquisa por autoriza��es encaminhadas pela institui��o do
	 * usu�rio logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	public ConsultaDto<Autorizacao> pesquisarAutorizacoesEncaminhadas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {

		return getServico().pesquisarAutorizacoesEncaminhadas(criterios);
	}

	/**
	 * Pesquisar autorizacoes nao encaminhadas.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaAutorizacaoDTO
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesNaoEncaminhadas(
			ConsultaAutorizacaoDTO criterios) throws BancoobException {

		return getServico().pesquisarAutorizacoesNaoEncaminhadas(criterios);
	}

	/**
	 * Executa o procedimento identificado pelo ID {@code idProcedimento}
	 * 
	 * @param autorizacao
	 *            TODO
	 * @param atividade
	 *            TODO
	 * @param procedimento
	 *            TODO
	 * @param justificativa
	 *            a justificativa para a execu��o do procedimento
	 */
	public void executarProcedimento(Autorizacao autorizacao, OcorrenciaAtividadeVO procedimento, String justificativa) throws BancoobException {
		getServico().executarProcedimento(autorizacao, procedimento, justificativa);
	}

	/**
	 * @see AutorizarServico#pesquisarAutorizacoesAptasEncaminhar(ConsultaAutorizacaoDTO)
	 */
	public List<Autorizacao> pesquisarAutorizacoesAptasEncaminhar(ConsultaAutorizacaoDTO criterios)
			throws BancoobException {
		return getServico().pesquisarAutorizacoesAptasEncaminhar(criterios);
	}

	/**
	 * @see AutorizarServico#encaminharAutorizacao(ConsultaAutorizacaoDTO)
	 */
	public boolean encaminharAutorizacoes(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		return getServico().encaminharAutorizacoes(criterios);
	}
	
	/**
	 * Atualizar responsavel.
	 *
	 * @param criterios o valor de criterios
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public boolean atualizarResponsavel(ConsultaAutorizacaoDTO criterios)
			throws BancoobException {
		return getServico().atualizarResponsavel(criterios);
	}
	
	/**
	 * O m�todo Excluir autorizacao encaminhada.
	 *
	 * @param vo o valor de vo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void excluirAutorizacaoEncaminhada(EncaminharAutorizacaoVO vo) throws BancoobException {
		getServico().excluirAutorizacaoEncaminhada(vo);
	}
	
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
	public void atualizarResponsavelAutorizacao(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		getServico().atualizarResponsavelAutorizacao(criterios);
	}
	
	/**
	 * Adiciona o IDUsuarioEnvio.
	 * 
	 * @param autorizacao
	 * @throws BancoobException
	 */
	public void incluirUsuarioEnvioAutorizacao(Autorizacao autorizacao) throws BancoobException {
		getServico().incluirUsuarioEnvioAutorizacao(autorizacao);
	}
	
	/**
	 * Pesquisar autorizacoes somente canais de auto atendimento.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public ConsultaDto<Autorizacao> pesquisarAutorizacoesPendentesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		return getServico().pesquisarAutorizacoesPendentesAutoAtendimento(criterios);
	}
	
	public Autorizacao obterAutorizacaoPorIdRegistroNovo(Long IdRegistroNovo, Boolean devolvido) throws BancoobException {
		return getServico().obterAutorizacaoPorIdRegistroNovo(IdRegistroNovo, devolvido);
	}
}