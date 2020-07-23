package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.AutorizacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * Business delegate para as autoriza��es
 * 
 * @author Rodrigo.Chaves
 */
public class AutorizacaoDelegate extends
		CAPESCadastroCrudDelegate<Autorizacao, AutorizacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AutorizacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarAutorizacaoServico();
	}

	/**
	 * Realiza pesquisa por autoriza��es pendentes da institui��o do usu�rio
	 * logado
	 * 
	 * @param criterios
	 *            os crit�rios da consulta
	 * @return DTO contendo a lista de altera��es
	 */
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesPendentes(ConsultaAutorizacaoDTO criterios)
			throws BancoobException {
		return getServico().pesquisarAutorizacoesPendentes(criterios);
	}

	/**
	 * Pesquisar autorizacoes devolvidas.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaAutorizacaoDTO
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public ConsultaAutorizacaoDTO pesquisarAutorizacoesDevolvidas(ConsultaAutorizacaoDTO criterios)
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
	public ConsultaAutorizacaoDTO
			pesquisarAutorizacoesEncaminhadas(ConsultaAutorizacaoDTO criterios)
					throws BancoobException {
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
	 * @see AutorizacaoServico#pesquisarAutorizacoesAptasEncaminhar(ConsultaAutorizacaoDTO)
	 */
	public List<Autorizacao>  pesquisarAutorizacoesAptasEncaminhar(
			ConsultaAutorizacaoDTO criterios)throws BancoobException {
		return getServico().pesquisarAutorizacoesAptasEncaminhar(criterios);
	}
	
	/**
	 * @see AutorizacaoServico#encaminharAutorizacoes(ConsultaAutorizacaoDTO)
	 */	
	public void atualizaDataSolicitacao(Autorizacao autorizacao)throws BancoobException {
		getServico().atualizaDataSolicitacao(autorizacao);		
	}
	
	/**
	 * Recupera uma {@link Autorizacao} pela entidade associada
	 * 
	 * @see Aprovavel#getTipoAutorizacao()
	 * @see Aprovavel#getId()
	 * 
	 * @param aprovavel a entidade
	 * @return a autoriza��o, caso encontre
	 */
	public Autorizacao obterPorEntidade(Aprovavel aprovavel) throws BancoobException {
		return getServico().obterPorEntidade(aprovavel);
	}
	
	/**
	 * Obter documentos comprobatorios em autorizacao.
	 *
	 * @param criterios o valor de criterios
	 * @return Autorizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public Autorizacao obterDocumentosComprobatoriosEmAutorizacao(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		return getServico().obterDocumentosComprobatoriosEmAutorizacao(criterios);
	}

	/**
	 * Obter tipo operacao autorizacao.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @return TipoOperacaoEnum
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public TipoOperacaoEnum obterTipoOperacaoAutorizacao(Aprovavel aprovavel) throws BancoobException {
		return getServico().obterTipoOperacaoAutorizacao(aprovavel);
	}

	/**
	 * O m�todo Marcar devolvido.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param devolvido o valor de devolvido
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void marcarDevolvido(Autorizacao autorizacao, Boolean devolvido) throws BancoobException {
		getServico().marcarDevolvido(autorizacao, devolvido);
	}
	
	/**
	 * Obter lista autorizacoes vencidas.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public ConsultaDto<Autorizacao> obterListaAutorizacoesVencidas(ConsultaDto<Autorizacao> criterios) throws BancoobException{
		return getServico().obterListaAutorizacoesVencidas(criterios);
	}
	
	/**
	 * Obter com lock.
	 *
	 * @param chave o valor de chave
	 * @return Autorizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public Autorizacao obterComLock(Serializable chave) throws BancoobException {
		return getServico().obterComLock(chave);
	}
	
	/**
	 * O m�todo Cancelar autorizacao vencida.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void cancelarAutorizacaoVencida(Autorizacao autorizacao) throws BancoobException {
		getServico().cancelarAutorizacaoVencida(autorizacao);
	}
	
	/**
	 * O m�todo Cancelar autorizacao vencida.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @param justificativa a justificativa do cancelamento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void cancelarAutorizacaoVencida(Autorizacao autorizacao, String justificativa) throws BancoobException {
		getServico().cancelarAutorizacaoVencida(autorizacao, justificativa);
	}
	
	/**
	 * Obter a autoriza��o, caso seja nula, lan�a exce��o.
	 * 
	 * @param chave
	 * @return
	 */
	public Autorizacao obterAutorizacaoNaoNula(Serializable chave) throws BancoobException {
		return getServico().obterAutorizacaoNaoNula(chave);
	}

	/**
	 * Obtem as autoriza��es pendentes de auto atendimento.
	 * Mobile e Internet Bank
	 * @param listaAutorizacoes
	 * @return
	 */	
	public ConsultaAutorizacaoDTO obterAutorizacoesAutoAtendimento(ConsultaAutorizacaoDTO criterios) throws BancoobException {
		return getServico().obterAutorizacoesAutoAtendimento(criterios);
	}

	public Autorizacao obterAutorizacaoPorIdRegistroNovo(Long IdRegistroNovo, Boolean devolvido) throws BancoobException {
		return getServico().obterAutorizacaoPorIdRegistroNovo(IdRegistroNovo, devolvido);
	}
	
}