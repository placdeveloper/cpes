package br.com.sicoob.capes.integracao.negocio.servicos.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.comum.negocio.dto.GFTIntegracaoDTO;
import br.com.sicoob.capes.comum.negocio.vo.ControleVO;
import br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO;
import br.com.sicoob.capes.integracao.negocio.excecao.GFTIntegracaoNegocioException;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.GFTIntegracaoServicoLocal;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.GFTIntegracaoServicoRemote;
import br.com.sicoob.gftfo.negocio.delegates.GFTFOFabricaDelegates;
import br.com.sicoob.gftfo.negocio.delegates.ProcessoDelegate;
import br.com.sicoob.gftfo.negocio.delegates.ProcessoInstituicaoDelegate;
import br.com.sicoob.gftfo.negocio.dto.OcorrenciaProcessoDTO;
import br.com.sicoob.gftfo.negocio.dto.ProcessoDTO;
import br.com.sicoob.gftfo.negocio.dto.ProcessoInstituicaoDTO;
import br.com.sicoob.gftfo.negocio.dto.UsuarioDTO;

/**
 * Classe com os serviços do GFT.
 * 
 * @author Bruno.Carneiro
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
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OcorrenciaAtividadeVO> listarHistoricoAutorizacao(GFTIntegracaoDTO dados) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OcorrenciaAtividadeVO> obterAtividadesAtuais(String idRegistroControlado, String siglaProcesso) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Instancia o fluxo e executa o procedimento padrão da primeira atividade
	 * 
	 * @return uma coleção de controles, caso exista algum
	 */
	@Override
	public void instanciarFluxo(GFTIntegracaoDTO dados) throws BancoobException {
		try {
			ProcessoDelegate processoDelegate = GFTFOFabricaDelegates.getInstance().criarProcessoDelegate();
			ProcessoDTO processoDTO = processoDelegate.recuperarProcessoPorSigla(dados.getSiglaProcesso());

			ProcessoInstituicaoDelegate processoInstituicaoDelegate = GFTFOFabricaDelegates.getInstance().criarProcessoInstituicaoDelegate();
			List<ProcessoInstituicaoDTO> listaProcessos = processoInstituicaoDelegate.procurarProcessoInstituicao(processoDTO.getId(),
					dados.getIdInstituicaoDestino(), dados.getIdUnidadeInstDestino());
			ProcessoInstituicaoDTO processoInstituicaoDTO = listaProcessos.get(0);

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("justificativaOcorDirAtividade", "Atividade inicial do fluxo. " +
					"Executada automaticamente para registrar a ação do usuário que realizou a alteração");
			parametros.put("descInstanciaAtividade", "Atividade inicial (executada automaticamente)");
			
			OcorrenciaProcessoDTO ocorrencia = new OcorrenciaProcessoDTO();
			ocorrencia.setIdProcesso(processoInstituicaoDTO.getId());
			processoInstituicaoDTO.setProcesso(processoDTO);
			ocorrencia.setProcessoInstituicao(processoInstituicaoDTO);
			ocorrencia.setIdRegistroControlado(dados.getIdRegistroControlado());
			ocorrencia.setIdInstituicaoProcesso(dados.getIdInstituicaoOrigem());
			ocorrencia.setIdUnidadeInstituicaoProcesso(dados.getIdUnidadeInstOrigem());
			
			UsuarioBancoob usuarioBancoob = obterUsuario();
			UsuarioDTO usuario = new UsuarioDTO();
			usuario.setLoginUsuario(usuarioBancoob.getLogin());
			usuario.setIdInstituicao(Integer.valueOf(usuarioBancoob.getIdInstituicao()));
			usuario.setIdUnidadeInstituicao(Integer.valueOf(usuarioBancoob.getIdUnidadeInstituicao()));

			/*Collection<Controle> controles =*/ processoDelegate.iniciarNovoProcessoCAPES(ocorrencia, usuario, parametros);

//			List<ControleVO> vos = new ArrayList<ControleVO>();
//			if ((controles != null) && !controles.isEmpty()) {
//				for (Controle controle : controles) {
//					vos.add(new ControleVO(controle.getCodigo(), controle.getDescricao()));
//				}
//			}
//			return vos;
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
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executarProcedimento(GFTIntegracaoDTO dados, OcorrenciaAtividadeVO procedimentoVO, String justificativa) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ControleVO> obterControles(OcorrenciaAtividadeVO procedimentoVO) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OcorrenciaAtividadeVO> recuperarUltimaOcorrenciaFinalizada(String idRegistroControlado) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cancelarFluxo(String siglaProcesso, String idRegistroControlado) throws BancoobException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cancelarFluxo(String siglaProcesso, String idRegistroControlado, String justificativa) throws BancoobException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFluxoAtivo(String siglaProcesso, String idRegistroControlado) throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada neste ambiente.");
	}

}