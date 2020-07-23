package br.com.sicoob.capes.integracao.negocio.servicos;

import java.util.List;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.GFTIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.vo.ControleVO;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;

/**
 * @author Rodrigo.Chaves
 */
public interface GFTIntegracaoServico extends CAPESIntegracaoServico {

	/**
	 * Usuario possui atividades nao executadas.
	 * 
	 * @param dados
	 *            the dados
	 * @return boolean
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	Boolean usuarioPossuiAtividadesNaoExecutadas(GFTIntegracaoDTO dados) throws BancoobException;

	/**
	 * Recuperar ultima ocorrencia finalizada.
	 * 
	 * @param idRegistroControlado
	 *            the id registro controlado
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<OcorrenciaAtividadeVO> recuperarUltimaOcorrenciaFinalizada(String idRegistroControlado) throws BancoobException;

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
	void executarProcedimento(GFTIntegracaoDTO dados, OcorrenciaAtividadeVO procedimentoVO, String justificativa) throws BancoobException;

	/**
	 * Obter id registros controlados.
	 * 
	 * @return sets the
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	Set<String> obterIDRegistrosControlados() throws BancoobException;

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
	List<OcorrenciaAtividadeVO> obterAtividadesAtuais(String idRegistroControlado, String siglaProcesso) throws BancoobException;

	/**
	 * Listar historico autorizacao.
	 * 
	 * @param dados
	 *            the dados
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<OcorrenciaAtividadeVO> listarHistoricoAutorizacao(GFTIntegracaoDTO dados) throws BancoobException;

	/**
	 * Instanciar fluxo.
	 * 
	 * @param dados
	 *            the dados
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void instanciarFluxo(GFTIntegracaoDTO dados) throws BancoobException;

	/**
	 * Obter controles.
	 * 
	 * @param vo
	 *            the vo
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<ControleVO> obterControles(OcorrenciaAtividadeVO vo) throws BancoobException;

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
	void cancelarFluxo(String siglaProcesso, String idRegistroControlado) throws BancoobException;
	
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
	void cancelarFluxo(String siglaProcesso, String idRegistroControlado, String justificativa) throws BancoobException;

	/**
	 * Verifica se ha fluxo ativo no GED.
	 * @param siglaProcesso
	 * @param idRegistroControlado
	 * @param justificativa
	 * @return
	 * @throws BancoobException
	 */
	boolean isFluxoAtivo(String siglaProcesso, String idRegistroControlado) throws BancoobException;

}