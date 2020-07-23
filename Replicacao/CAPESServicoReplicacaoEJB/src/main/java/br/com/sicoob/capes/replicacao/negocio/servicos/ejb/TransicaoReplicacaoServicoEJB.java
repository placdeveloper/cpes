package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TransicaoReplicacaoServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TransicaoReplicacaoServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.TransicaoReplicacaoDao;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;

/**
 * 
 * EJB reponsável por disponibilizar os serviços da entidade TransicaoReplicacao.
 * 
 * @author Juan.Damasceno
 *
 */
@Stateless
@Local({TransicaoReplicacaoServicoLocal.class})
@Remote({TransicaoReplicacaoServicoRemote.class})
public class TransicaoReplicacaoServicoEJB extends
		CAPESServicoReplicacaoCrudServicoEJB<TransicaoReplicacao> 
	implements TransicaoReplicacaoServicoRemote, TransicaoReplicacaoServicoLocal{
	
	/** O atributo logger. */
	private ISicoobLogger logger = getLogger();
	
	@Inject
	@Default
	private TransicaoReplicacaoDao transicaoReplicacaoDao;
	
	/**
	 * {@inheritDoc}
	 */
	public List<TransicaoReplicacao> listarNaoReplicados() {
		return getDAO().listarNaoReplicados();
	}
	
	/**
	 * {@inheritDoc}
	 * @throws BancoobException 
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void centralizaTransicoesReplicacao(BancoServidor bancoServidor) throws BancoobException {
		GrupoCompartilhamentoDelegate grupoCompartilhamentoDelegate = criarGrupoCompartilhamentoDelegate();
		GrupoCompartilhamento grupoCompartilhamento = grupoCompartilhamentoDelegate.obterPorCooperativa(bancoServidor.getNumCooperativa());

		if (existeCompartilhamentoCadastro(grupoCompartilhamento)) {
			CompartilhamentoCadastro compartilhamentoCadastro = grupoCompartilhamento.getCompartilhamentoCadastro();

			bancoServidor.setCodGrupoCompartilhamento(compartilhamentoCadastro.getCodigo());
			bancoServidor.setIdInstituicao(grupoCompartilhamento.getIdInstituicao());
			getDAO().centralizaTransicoesReplicacao(bancoServidor);			
		} else {
			logger.alerta("A cooperativa " , bancoServidor.getNumCooperativa().toString(), 
			" nao possui sistema cooperativo cadastrado no SCI");
			getDAO().centralizaTransicoesReplicacaoSemSistemaCooperativo(bancoServidor);
		}
	}

	/**
	 * Criar grupo compartilhamento delegate.
	 *
	 * @return GrupoCompartilhamentoDelegate
	 */
	private GrupoCompartilhamentoDelegate criarGrupoCompartilhamentoDelegate() {
		return obterFabricaDelegateCUC().criarGrupoCompartilhamentoDelegate();
	}

	/**
	 * Obter fabrica delegate cuc.
	 *
	 * @return CAPESCadastroFabricaDelegates
	 */
	private CAPESCadastroFabricaDelegates obterFabricaDelegateCUC() {
		return CAPESCadastroFabricaDelegates.getInstance();
	}

	/**
	 * Existe compartilhamento cadastro.
	 *
	 * @param grupoCompartilhamento o valor de grupo compartilhamento
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean existeCompartilhamentoCadastro(
			GrupoCompartilhamento grupoCompartilhamento) {
		return grupoCompartilhamento != null && grupoCompartilhamento.getCompartilhamentoCadastro() != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public int centralizaTransicoesReplicacaoSemSistemaCooperativo(BancoServidor bancoServidor) {
		return getDAO().centralizaTransicoesReplicacaoSemSistemaCooperativo(bancoServidor);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public int marcaTransicoesReplicacaoParaCentralizacao(BancoServidor bancoServidor) {
		return getDAO().marcaTransicoesReplicacaoParaCentralizacao(bancoServidor);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void removeTransicoesReplicacaoCentralizadas(BancoServidor bancoServidor) {
		getDAO().removeTransicoesReplicacaoCentralizadas(bancoServidor);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removerReplicados(Date data) throws BancoobException {
		getDAO().removerReplicados(data);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TransicaoReplicacaoDao getDAO() {
		return transicaoReplicacaoDao;
	}

}
