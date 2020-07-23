package br.com.sicoob.capes.integracao.negocio.delegates;

import java.util.List;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.GFTIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.vo.ControleVO;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;
import br.com.sicoob.capes.integracao.negocio.servicos.GFTIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.locator.CAPESIntegracaoServiceLocator;

/**
 * @author Rodrigo.Chaves
 */
public class GFTIntegracaoDelegate extends CAPESIntegracaoDelegate<GFTIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GFTIntegracaoServico localizarServico() {
		return CAPESIntegracaoServiceLocator.getInstance().localizarGFTIntegracaoServico();
	}

	/**
	 * Verificar disponibilidade.
	 */
	public void verificarDisponibilidade() {
		getServico().verificarDisponibilidade();
	}

	/**
	 * Usuario possui atividades nao executadas.
	 * 
	 * @param dados
	 *            the dados
	 * @return boolean
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public Boolean usuarioPossuiAtividadesNaoExecutadas(GFTIntegracaoDTO dados) throws BancoobException {
		return getServico().usuarioPossuiAtividadesNaoExecutadas(dados);
	}

	/**
	 * Recuperar ultima ocorrencia finalizada.
	 * 
	 * @param idRegistroControlado
	 *            the id registro controlado
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<OcorrenciaAtividadeVO> recuperarUltimaOcorrenciaFinalizada(String idRegistroControlado) throws BancoobException {
		return getServico().recuperarUltimaOcorrenciaFinalizada(idRegistroControlado);
	}

	/**
	 * Executar procedimento.
	 * 
	 * @param dados
	 *            the dados
	 * @param atividadeVO
	 *            the atividade vo
	 * @param procedimentoVO
	 *            the procedimento vo
	 * @param justificativa
	 *            the justificativa
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void executarProcedimento(GFTIntegracaoDTO dados, OcorrenciaAtividadeVO procedimentoVO, String justificativa) throws BancoobException {
		getServico().executarProcedimento(dados, procedimentoVO, justificativa);
	}

	/**
	 * Obter id registros controlados.
	 * 
	 * @return sets the
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public Set<String> obterIDRegistrosControlados() throws BancoobException {
		return getServico().obterIDRegistrosControlados();
	}

	/**
	 * Obter atividades atuais.
	 * 
	 * @param idRegistroControlado
	 *            the id registro controlado
	 * @param usuarioBancoob
	 *            the usuario bancoob
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<OcorrenciaAtividadeVO> obterAtividadesAtuais(String idRegistroControlado, String siglaProcesso) throws BancoobException {
		return getServico().obterAtividadesAtuais(idRegistroControlado, siglaProcesso);
	}

	/**
	 * Listar historico autorizacao.
	 * 
	 * @param dados
	 *            the dados
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<OcorrenciaAtividadeVO> listarHistoricoAutorizacao(GFTIntegracaoDTO dados) throws BancoobException {
		return getServico().listarHistoricoAutorizacao(dados);
	}

	/**
	 * Instanciar fluxo.
	 * 
	 * @param dados
	 *            the dados
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void instanciarFluxo(GFTIntegracaoDTO dados) throws BancoobException {
		getServico().instanciarFluxo(dados);
	}
	
	/**
	 * Obter controles.
	 * 
	 * @param vo
	 *            the vo
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<ControleVO> obterControles(OcorrenciaAtividadeVO vo) throws BancoobException {
		return getServico().obterControles(vo);
	}

	/**
	 * Cancelar fluxo.
	 * 
	 * @param siglaProcesso
	 *            the sigla processo
	 * @param idRegistroControlado
	 *            the id registro controlado
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void cancelarFluxo(String siglaProcesso, String idRegistroControlado) throws BancoobException {
		getServico().cancelarFluxo(siglaProcesso, idRegistroControlado);
	}
	
	/**
	 * Cancelar fluxo.
	 * 
	 * @param siglaProcesso
	 *            the sigla processo
	 * @param idRegistroControlado
	 *            the id registro controlado
	 * @param justificativa
	 * 			a justificativa do cancelamento
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void cancelarFluxo(String siglaProcesso, String idRegistroControlado, String justificativa) throws BancoobException {
		getServico().cancelarFluxo(siglaProcesso, idRegistroControlado, justificativa);
	}

	/**
	 * Verifica se ha fluxo ativo no GED.
	 * @param siglaProcesso
	 * @param idRegistroControlado
	 * @param justificativa
	 * @return
	 * @throws BancoobException
	 */
	public boolean isFluxoAtivo(String siglaProcesso, String idRegistroControlado) throws BancoobException {
		return getServico().isFluxoAtivo(siglaProcesso, idRegistroControlado);
	}		

}