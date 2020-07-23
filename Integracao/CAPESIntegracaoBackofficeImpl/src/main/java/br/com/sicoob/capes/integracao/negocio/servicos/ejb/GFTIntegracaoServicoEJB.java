package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.beanutils.BeanComparator;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.comum.negocio.dto.GFTIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.vo.ControleVO;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.comum.util.DataUtils;
import br.com.sicoob.capes.integracao.negocio.excecao.GFTIntegracaoNegocioException;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.GFTIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.GFTIntegracaoServicoRemote;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiAtividadeDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiConsultaOcorrenciaProcessoDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiFabricaDelegates;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiProcedimentoDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.delegates.GedApiProcessoDelegate;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.GedApiFabricaFiltros;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.IFiltroConsultaEstadoInstanciaProcesso;
import br.com.sicoob.sisbr.ged.api.negocio.dto.filtro.IFiltroConsultaOcorrenciaProcesso;
import br.com.sicoob.sisbr.ged.api.negocio.dto.parametro.GedApiFabricaParametro;
import br.com.sicoob.sisbr.ged.api.negocio.dto.parametro.IParametroProcesso;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoAtividade;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoControle;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoEstadoOcorrenciaProcesso;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoOcorrenciaDirecionamentoAtividade;
import br.com.sicoob.sisbr.ged.api.negocio.dto.retorno.IRetornoProcedimento;

/**
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(GFTIntegracaoServicoLocal.class)
@Remote(GFTIntegracaoServicoRemote.class)
public class GFTIntegracaoServicoEJB extends CAPESIntegracaoServicoEJB implements GFTIntegracaoServicoRemote, GFTIntegracaoServicoLocal {

	/**
	 * Código 1 Descrição: Iniciado
	 * Código que identifica que o processo esta iniciado o fluxo no GED.
	 */
	private static final String INICIADO = "1";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean usuarioPossuiAtividadesNaoExecutadas(GFTIntegracaoDTO dados) throws BancoobException {
		try {
			IFiltroConsultaOcorrenciaProcesso filtro = GedApiFabricaFiltros.getInstance().criarFiltroOcorrenciaProcesso(dados.getSiglaProcesso(), dados.getIdRegistroControlado());
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
		try {
			GedApiAtividadeDelegate atividadeDelegate = GedApiFabricaDelegates.getInstance().criarGedApiAtividadeDelegate();
			List<IRetornoAtividade> atividades = atividadeDelegate.listarHistoricoAtividadesProcesso(dados.getIdRegistroControlado(), dados.getSiglaProcesso());
			return converterAtividades(atividades);
		} catch (NegocioException e) {
			throw new GFTIntegracaoNegocioException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OcorrenciaAtividadeVO> obterAtividadesAtuais(String idRegistroControlado, String siglaProcesso) throws BancoobException {
		try {
			GedApiProcedimentoDelegate procedimentoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiProcedimentoDelegate();
			List<IRetornoProcedimento> procedimentos = procedimentoDelegate.listarProcedimentosAtividadeComPermissao(idRegistroControlado, siglaProcesso);
			return converterProcedimentos(procedimentos);
		} catch (NegocioException e) {
			throw new GFTIntegracaoNegocioException(e);
		}
	}

	/**
	 * Instancia o fluxo e executa o procedimento padrão da primeira atividade
	 * 
	 * @return uma coleção de controles, caso exista algum
	 */
	@Override
	public void instanciarFluxo(GFTIntegracaoDTO dados) throws BancoobException {
		try {
			IParametroProcesso parametro = GedApiFabricaParametro.getInstance().criarParametroProcessoInstanciarOcorrenciaProcessoEExecutarAtividadeInicial(
					dados.getSiglaProcesso(), 
					dados.getIdRegistroControlado(), 
					dados.getIdInstituicaoDestino(), 
					dados.getIdUnidadeInstDestino(), 
					"Atividade inicial (executada automaticamente)", 
					null, 
					"Atividade inicial do fluxo. Executada automaticamente para registrar a ação do usuário que realizou a alteração");

			GedApiProcessoDelegate processoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiProcessoDelegate();
			processoDelegate.instanciarOcorrenciaProcessoEExecutarAtividadeInicial(parametro);
		} catch (NegocioException e) {
			throw new GFTIntegracaoNegocioException(e, "Autorização ID: " + dados.getIdAutorizacao());
		}
	}

	/**
	 * Recupera um conjunto ({@link Set}) com {@code idRegistroControlado} das autorizações que estão na vez do {@code usuario}
	 * 
	 * @param usuarioBancoob
	 *            O usuário para o qual se deseja recuperar os {@code idRegistroControlado}
	 * @param instituicao
	 * @return o conjunto com os {@code idRegistroControlado}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Set<String> obterIDRegistrosControlados() throws BancoobException {
		try {
			getLogger().info("Obtendo os IDs dos registros controlados no GFT");

			GedApiAtividadeDelegate atividadeDelegate = GedApiFabricaDelegates.getInstance().criarGedApiAtividadeDelegate();
			Set<String> ids = atividadeDelegate.listarRegistrosControladosPendentesCapes();

			getLogger().debug("IDs dos registros controlados obtidos do GFT: ", String.valueOf(ids));
			getLogger().info("IDs dos registros controlados obtidos do GFT");

			return ids;
		} catch (NegocioException e) {
			throw new GFTIntegracaoNegocioException(e);
		}
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

			IParametroProcesso parametro = GedApiFabricaParametro.getInstance()
					.criarParametroExecutarAtividadeProcesso(
						dados.getSiglaProcesso(), 
						dados.getIdRegistroControlado(), 
						procedimentoVO.getIdOcorrenciaAtividade(), 
						new Date(), 
						procedimentoVO.getNomeProcedimento(), 
						justificativa);
			
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
		try {
			List<OcorrenciaAtividadeVO> retorno = new ArrayList<OcorrenciaAtividadeVO>();

			GedApiAtividadeDelegate atividadeDelegate = GedApiFabricaDelegates.getInstance().criarGedApiAtividadeDelegate();
			List<IRetornoOcorrenciaDirecionamentoAtividade> atividades = atividadeDelegate.recuperarUltimaOcorrenciaFinalizada(idRegistroControlado, Constantes.Negocio.CTA_SIGLA_SISTEMA);

			if (atividades != null && !atividades.isEmpty()) {
				for (IRetornoOcorrenciaDirecionamentoAtividade ocorrencia : atividades) {
					retorno.add(criarOcorrenciaDirecionamentoAtividadeVO(ocorrencia));
				}
				Collections.sort(retorno, new BeanComparator<OcorrenciaAtividadeVO>("data"));
			}

			return retorno;
		} catch (NegocioException e) {
			throw new GFTIntegracaoNegocioException(e);
		}
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
		IParametroProcesso parametro = GedApiFabricaParametro.getInstance().criarParametroCancelamentoProcesso(siglaProcesso, idRegistroControlado, justificativa);
		GedApiProcessoDelegate processoDelegate = GedApiFabricaDelegates.getInstance().criarGedApiProcessoDelegate();
		processoDelegate.cancelarOcorrenciasProcesso(parametro);
	}
	
	private List<OcorrenciaAtividadeVO> converterProcedimentos(List<IRetornoProcedimento> procedimentos) {
		List<OcorrenciaAtividadeVO> retorno = new ArrayList<OcorrenciaAtividadeVO>();

		if (procedimentos != null && procedimentos.size() > 0) {
			for(IRetornoProcedimento procedimento : procedimentos) {
				OcorrenciaAtividadeVO vo = new OcorrenciaAtividadeVO();
				vo.setIdOcorrenciaAtividade(procedimento.getIdOcorrenciaDirecionamentoAtividade());
//				vo.setIdUsuario(procedimento.get);
//				vo.setJustificativa(procedimento.get);
				vo.setIdProcedimento(procedimento.getIdProcedimento());
				vo.setNomeProcedimento(procedimento.getNomeProcedimento());
				
				retorno.add(vo);
			}
			
			Collections.sort(retorno, new BeanComparator<OcorrenciaAtividadeVO>("nomeProcedimento"));
		}
		
		return retorno;
	}
	
	/**
	 * Converter ocorrencia atividade.
	 * 
	 * @param atividades
	 *            o valor de atividades
	 * @return List
	 * @throws BancoobException
	 *             lançaa a exceção BancoobException
	 */
	private List<OcorrenciaAtividadeVO> converterAtividades(List<IRetornoAtividade> atividades) throws BancoobException {
		List<OcorrenciaAtividadeVO> resultado = new ArrayList<OcorrenciaAtividadeVO>();
		if (atividades != null && atividades.size() > 0) {
			for (IRetornoAtividade oAtividade : atividades) {
				
				if(oAtividade.getDataHoraFimAtividade() != null) {
					OcorrenciaAtividadeVO vo = criarOcorrenciaAtividadeVO(oAtividade);
					resultado.add(vo);
				}
			}
//			Collections.sort(resultado, new BeanComparator<OcorrenciaAtividadeVO>("data"));
		}
		return resultado;
	}

	/**
	 * Criar ocorrencia atividade vo.
	 * 
	 * @param oAtividade
	 *            o valor de o atividade
	 * @return OcorrenciaAtividadeVO
	 */
	private OcorrenciaAtividadeVO criarOcorrenciaAtividadeVO(IRetornoAtividade oAtividade) {
		OcorrenciaAtividadeVO vo = new OcorrenciaAtividadeVO();
		vo.setData(oAtividade.getDataHoraFimAtividade());
		vo.setIdUsuario(oAtividade.getIdUsuario());
		vo.setJustificativa(oAtividade.getDescJustificativa());
		vo.setNomeProcedimento(oAtividade.getProcedimento());
		return vo;
	}

	private OcorrenciaAtividadeVO criarOcorrenciaDirecionamentoAtividadeVO(IRetornoOcorrenciaDirecionamentoAtividade ocorrencia) {
		OcorrenciaAtividadeVO retorno = new OcorrenciaAtividadeVO();
		retorno.setData(DataUtils.converterDateTimeDBParaDate(ocorrencia.getDataHoraFimAtividade()));
		retorno.setIdOcorrenciaProcesso(ocorrencia.getIdProcesso());
		retorno.setIdUsuario(ocorrencia.getIdUsuario());
		retorno.setJustificativa(ocorrencia.getDescJustificativa());
		retorno.setNomeProcedimento(ocorrencia.getNomeProcedimento());
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isFluxoAtivo(String siglaProcesso, String idRegistroControlado) throws BancoobException {
		IFiltroConsultaEstadoInstanciaProcesso  filtro = GedApiFabricaFiltros.getInstance().criarFiltroFiltroConsultaOcorrenciaProcesso(null, siglaProcesso, idRegistroControlado);
		boolean retorno = false;
		try {
			IRetornoEstadoOcorrenciaProcesso retornoGED = GedApiFabricaDelegates.getInstance().criarGedApiConsultaOcorrenciaProcessoDelegate().consultarEstadoInstanciaProcesso(filtro);
			getLogger().info("RETORNO GED CODIGO DO FLUXO: " + retornoGED.getCodigo());
			retorno = retornoGED.getCodigo().equals(Integer.valueOf(INICIADO));
		} catch (BancoobException ex) {
			return retorno;
		} catch (Exception e) {
			getLogger().info("Exceção ao verificar se existe fluxo idRegistroControlado:  " + idRegistroControlado);
		}
		
		return retorno;
	}
}