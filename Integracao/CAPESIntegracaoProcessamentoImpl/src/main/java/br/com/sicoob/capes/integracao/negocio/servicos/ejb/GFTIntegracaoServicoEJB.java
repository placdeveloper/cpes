package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.comum.negocio.dto.GFTIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.vo.ControleVO;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;
import br.com.sicoob.capes.integracao.negocio.excecao.GFTIntegracaoNegocioException;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.GFTIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.GFTIntegracaoServicoRemote;
import br.com.sicoob.sisbr.ged.api.proc.negocio.delegates.GedApiConsultaOcorrenciaProcessoDelegate;
import br.com.sicoob.sisbr.ged.api.proc.negocio.delegates.GedApiFabricaDelegates;
import br.com.sicoob.sisbr.ged.api.proc.negocio.delegates.GedApiProcessoDelegate;
import br.com.sicoob.sisbr.ged.api.proc.negocio.dto.filtro.GedApiFabricaFiltros;
import br.com.sicoob.sisbr.ged.api.proc.negocio.dto.filtro.IFiltroConsultaOcorrenciaProcesso;
import br.com.sicoob.sisbr.ged.api.proc.negocio.dto.parametro.GedApiFabricaParametroProcessamento;
import br.com.sicoob.sisbr.ged.api.proc.negocio.dto.parametro.IParametroProcesso;
import br.com.sicoob.sisbr.ged.api.proc.negocio.dto.retorno.IRetornoControle;
import br.com.sicoob.sisbr.ged.api.proc.negocio.dto.retorno.IRetornoProcedimento;

/**
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(GFTIntegracaoServicoLocal.class)
@Remote(GFTIntegracaoServicoRemote.class)
public class GFTIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements GFTIntegracaoServicoRemote, GFTIntegracaoServicoLocal {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean usuarioPossuiAtividadesNaoExecutadas(GFTIntegracaoDTO dados) throws BancoobException {
		try {
			IFiltroConsultaOcorrenciaProcesso filtro = GedApiFabricaFiltros.getInstance().criarFiltroOcorrenciaProcesso(dados.getIdRegistroControlado(), dados.getSiglaProcesso());
			GedApiConsultaOcorrenciaProcessoDelegate ocorrenciaProcessoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiConsultaOcorrenciaProcessoDelegate();
			return ocorrenciaProcessoDelegate.verificarExistenciaOcorrenciaProcessoEmAberto(filtro);
		} catch (NegocioException e) {
			throw new GFTIntegracaoNegocioException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OcorrenciaAtividadeVO> listarHistoricoAutorizacao(GFTIntegracaoDTO dados) throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada neste ambiente.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OcorrenciaAtividadeVO> obterAtividadesAtuais(String idRegistroControlado, String siglaProcesso) throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada neste ambiente.");
	}

	/**
	 * Instancia o fluxo e executa o procedimento padrão da primeira atividade
	 * 
	 * @return uma coleção de controles, caso exista algum
	 */
	@Override
	public void instanciarFluxo(GFTIntegracaoDTO dados) throws BancoobException {
		try {
			IParametroProcesso parametro = GedApiFabricaParametroProcessamento.getInstance().criarParametroProcessoInstanciarOcorrenciaProcessoEExecutarAtividadeInicial(
					dados.getSiglaProcesso(), 
					dados.getIdRegistroControlado(), 
					dados.getIdInstituicaoDestino(), 
					dados.getIdUnidadeInstDestino(),
					"Atividade inicial (executada automaticamente)", 
					obterUsuario().getLogin(),  
					Integer.valueOf(obterUsuario().getIdInstituicao()), 
					Integer.valueOf(obterUsuario().getIdUnidadeInstituicao()), 
					null, 
					"Atividade inicial do fluxo. Executada automaticamente para registrar a ação do usuário que realizou a alteração");

			GedApiProcessoDelegate processoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiProcessoDelegate();
			processoDelegate.instanciarOcorrenciaProcessoEExecutarAtividadeInicial(parametro);
		} catch (NegocioException e) {
			throw new GFTIntegracaoNegocioException(e, "Autorização ID: " + dados.getIdAutorizacao());
		}
	}

	/**
	 * Recupera um conjunto ({@link Set}) com {@code idRegistroControlado} das
	 * autorizações que estão na vez do {@code usuario}
	 * 
	 * @param usuarioBancoob
	 *            O usuário para o qual se deseja recuperar os
	 *            {@code idRegistroControlado}
	 * @param instituicao
	 * @return o conjunto com os {@code idRegistroControlado}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Set<String> obterIDRegistrosControlados() throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada neste ambiente.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executarProcedimento(GFTIntegracaoDTO dados, OcorrenciaAtividadeVO procedimentoVO, String justificativa) throws BancoobException {
		try {
			getLogger().debug("executarProcedimento: autorizacao#", dados.getIdAutorizacao().toString(), 
					", ocorrenciaAtividade#", procedimentoVO.getIdOcorrenciaAtividade().toString(), 
					", procedimento#", procedimentoVO.getNomeProcedimento(), ", justificativa@", justificativa);
			
			IParametroProcesso parametro = GedApiFabricaParametroProcessamento.getInstance().criarParametroExecutarAtividadeProcesso(
					dados.getSiglaProcesso(),
					dados.getIdRegistroControlado(),
					procedimentoVO.getIdOcorrenciaAtividade(), 
					new Date(), 
					procedimentoVO.getNomeProcedimento(),
					justificativa, 
					obterUsuario().getLogin(), 
					Integer.valueOf(obterUsuario().getIdInstituicao()), 
					Integer.valueOf(obterUsuario().getIdUnidadeInstituicao()));
			
			getLogger().debug("integracaoGFT: atividadeDelegate.executarAtividade(", parametro.toString(), ")");

			GedApiProcessoDelegate processoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiProcessoDelegate();
			processoDelegate.executarAtividadeProcesso(parametro);
		} catch (NegocioException e) {
			throw new GFTIntegracaoNegocioException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ControleVO> obterControles(OcorrenciaAtividadeVO procedimentoVO) throws BancoobException {
		getLogger().debug("obtendo controles: Procedimento#", procedimentoVO.getIdOcorrenciaAtividade().toString());
		getLogger().debug("integracaoGFT: procedimentoDelegate.obter(", procedimentoVO.toString(), ")");

		IRetornoProcedimento retorno = GedApiFabricaDelegates.getInstance().criarGedApiProcedimentoDelegate().obter(procedimentoVO.getIdProcedimento());
		List<IRetornoControle> listaProcedimentoControle = retorno.getListaProcedimentoControle();
		List<ControleVO> vos = new ArrayList<ControleVO>();
		if (listaProcedimentoControle != null) {
			for (IRetornoControle controle : listaProcedimentoControle) {
				ControleVO vo = new ControleVO(controle.getCodControle(), controle.getDescricaoControle());
				vos.add(vo);
				getLogger().debug("Controle obtido: " + vo.toString());
			}
		}
		return vos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OcorrenciaAtividadeVO> recuperarUltimaOcorrenciaFinalizada(String idRegistroControlado) throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada neste ambiente.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cancelarFluxo(String siglaProcesso, String idRegistroControlado) throws BancoobException {
		cancelarFluxo(siglaProcesso, idRegistroControlado, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cancelarFluxo(String siglaProcesso, String idRegistroControlado, String justificativa) throws BancoobException {
		IParametroProcesso parametro = GedApiFabricaParametroProcessamento.getInstance().criarParametroCancelamentoProcesso(siglaProcesso, idRegistroControlado, justificativa);
		GedApiFabricaDelegates.getInstance().criarGedApiProcessoDelegate().cancelarOcorrenciasProcesso(parametro);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFluxoAtivo(String siglaProcesso, String idRegistroControlado) throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada neste ambiente.");
	}
}