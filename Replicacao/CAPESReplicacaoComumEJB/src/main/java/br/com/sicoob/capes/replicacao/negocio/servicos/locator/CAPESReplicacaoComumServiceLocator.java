package br.com.sicoob.capes.replicacao.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.replicacao.negocio.servicos.AvaliacaoFinanceiraServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemImovelServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemOnusServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemPosseServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemRegistroServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.BemServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.CertidaoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.ClienteServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.CooperativaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.EmpreendimentoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.EnderecoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.FonteRendaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.FuncionarioServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.HistoricoAlteracaoCnpjCpfServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.ListaItemServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.ListaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.MensagemServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.NucleoServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.PessoaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.ProdutividadeServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.ProdutorServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReferenciaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.RelacionamentoPessoaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.ReplicacaoCadastroServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.TelefoneServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.TipoBemServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.TipoEmpresaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.UnidadeMedidaServico;

/**
 * Service Locator usado pelo sistema ReplicacaoClientesBO.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESReplicacaoComumServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESReplicacaoComumServiceLocator locator;

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static CAPESReplicacaoComumServiceLocator getInstance() {
		if (locator == null) {
			synchronized (CAPESReplicacaoComumServiceLocator.class) {
				if(locator == null){
					locator = new CAPESReplicacaoComumServiceLocator();
				}
			}
		}

		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private CAPESReplicacaoComumServiceLocator() {
		super("capes.replicacao.comum");
	}

	/**
	 * Localiza o serviço de pessoas.
	 * 
	 * @return o serviço de pessoas a ser retornado.
	 */
	public PessoaServico localizarPessoaServico() {
		return (PessoaServico) localizar("locator.replicacao.PessoaServico");
	}

	/**
	 * Localiza o serviço de referências.
	 * 
	 * @return o serviço de referências a ser retornado.
	 */
	public ReferenciaServico localizarReferenciaServico() {
		return (ReferenciaServico) localizar("locator.replicacao.ReferenciaServico");
	}

	/**
	 * Localiza o serviço de fonte de rendas.
	 * 
	 * @return o serviço de fonte de rendas.
	 */
	public FonteRendaServico localizarFonteRendaServico() {
		return (FonteRendaServico) localizar("locator.replicacao.FonteRendaServico");
	}

	/**
	 * Localiza o serviço de bens.
	 * 
	 * @return o serviço de bens.
	 */
	public BemServico localizarBemServico() {
		return (BemServico) localizar("locator.replicacao.BemServico");
	}

	/**
	 * Localiza o serviço de produtores.
	 * 
	 * @return o serviço de produtores.
	 */
	public ProdutorServico localizarProdutorServico() {
		return (ProdutorServico) localizar("locator.replicacao.ProdutorServico");
	}

	/**
	 * Localiza o serviço para o histórico de alteração de cpf/cnpj.
	 * 
	 * @return o serviço para o histórico de alteração de cpf/cnpj.
	 */
	public HistoricoAlteracaoCnpjCpfServico localizarHistoricoAlteracaoCnpjCpfServico() {
		return (HistoricoAlteracaoCnpjCpfServico) localizar("locator.replicacao.HistoricoAlteracaoCnpjCpfServico");
	}

	/**
	 * Localiza o serviço para as listas.
	 * 
	 * @return o serviço para as listas.
	 */
	public ListaServico localizarListaServico() {
		return (ListaServico) localizar("locator.replicacao.ListaServico");
	}

	/**
	 * Localiza o serviço para os itens das listas.
	 * 
	 * @return o serviço para os itens das listas.
	 */
	public ListaItemServico localizarListaItemServico() {
		return (ListaItemServico) localizar("locator.replicacao.ListaItemServico");
	}

	/**
	 * Localiza o serviço para as unidades de medidas.
	 * 
	 * @return o serviço para as unidades de medidas.
	 */
	public UnidadeMedidaServico localizarUnidadeMedidaServico() {
		return (UnidadeMedidaServico) localizar("locator.replicacao.UnidadeMedidaServico");
	}

	/**
	 * Localiza o serviço para os tipos de bens.
	 * 
	 * @return o serviço para os tipos de bens.
	 */
	public TipoBemServico localizarTipoBemServico() {
		return (TipoBemServico) localizar("locator.replicacao.TipoBemServico");
	}

	/**
	 * Localiza o serviço para os tipos de empresa.
	 * 
	 * @return o serviço para os tipos de empresa.
	 */
	public TipoEmpresaServico localizarTipoEmpresaServico() {
		return (TipoEmpresaServico) localizar("locator.replicacao.TipoEmpresaServico");
	}
	
	/**
	 * Localiza o serviço para os bens onus.
	 * 
	 * @return o serviço para os bens onus.
	 */
	public BemOnusServico localizarBemOnusServico() {
		return (BemOnusServico) localizar("locator.replicacao.BemOnusServico");
	}

	/**
	 * Localiza o serviço para os bens de posse.
	 * 
	 * @return o serviço para os bens de posse.
	 */
	public BemPosseServico localizarBemPosseServico() {
		return (BemPosseServico) localizar("locator.replicacao.BemPosseServico");
	}

	/**
	 * Localiza o serviço para os bens de registro.
	 * 
	 * @return o serviço para os bens de registro.
	 */
	public BemRegistroServico localizarBemRegistroServico() {
		return (BemRegistroServico) localizar("locator.replicacao.BemRegistroServico");
	}

	/**
	 * Localiza o serviço para os bens de imovel.
	 * 
	 * @return o serviço para os bens de imovel.
	 */
	public BemImovelServico localizarBemImovelServico() {
		return (BemImovelServico) localizar("locator.replicacao.BemImovelServico");
	}

	/**
	 * Localiza o serviço para as mensagens.
	 * 
	 * @return o serviço para as mensagens.
	 */
	public MensagemServico localizarMensagemServico() {
		return (MensagemServico) localizar("locator.replicacao.MensagemServico");
	}

	/**
	 * Localiza o serviço para os endereços.
	 * 
	 * @return o serviço para os endereços.
	 */
	public EnderecoServico localizarEnderecoServico() {
		return (EnderecoServico) localizar("locator.replicacao.EnderecoServico");
	}

	/**
	 * Localiza o serviço para os telefones.
	 * 
	 * @return o serviço para os telefones.
	 */
	public TelefoneServico localizarTelefoneServico() {
		return (TelefoneServico) localizar("locator.replicacao.TelefoneServico");
	}

	/**
	 * Localiza o serviço para as certidoes.
	 * 
	 * @return o serviço para as certidoes.
	 */
	public CertidaoServico localizarCertidaoServico() {
		return (CertidaoServico) localizar("locator.replicacao.CertidaoServico");
	}

	/**
	 * Localiza o serviço para os clientes.
	 * 
	 * @return o serviço para os clientes.
	 */
	public ClienteServico localizarClienteServico() {
		return (ClienteServico) localizar("locator.replicacao.ClienteServico");
	}

	/**
	 * Localiza o serviço para os clientes.
	 * 
	 * @return o serviço para os clientes.
	 */
	public FuncionarioServico localizarFuncionarioServico() {
		return (FuncionarioServico) localizar("locator.replicacao.FuncionarioServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code RelacionamentoPessoaServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelacionamentoPessoaServico
	 */
	public RelacionamentoPessoaServico localizarRelacionamentoPessoaServico() {
		return (RelacionamentoPessoaServico) localizar("locator.replicacao.RelacionamentoPessoaServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code ProdutividadeServico}.
	 *
	 * @return O EJB solicitado
	 * @see ProdutividadeServico
	 */
	public ProdutividadeServico localizarProdutividadeServico() {
		return (ProdutividadeServico) localizar("locator.replicacao.ProdutividadeServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code EmpreendimentoServico}.
	 *
	 * @return O EJB solicitado
	 * @see EmpreendimentoServico
	 */
	public EmpreendimentoServico localizarEmpreendimentoServico() {
		return (EmpreendimentoServico) localizar("locator.replicacao.EmpreendimentoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code ReplicacaoCadastroServico}.
	 *
	 * @return O EJB solicitado
	 * @see ReplicacaoCadastroServico
	 */
	public ReplicacaoCadastroServico localizarReplicacaoCadastroServico() {
		return (ReplicacaoCadastroServico) localizar("locator.replicacao.ReplicacaoCadastroServico");
	}

	/**
	 * Localiza o serviço de nucleos.
	 * 
	 * @return o serviço de nucleos a ser retornado.
	 */
	public NucleoServico localizarNucleoServico() {
		return (NucleoServico) localizar("locator.replicacao.NucleoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code CooperativaServico}.
	 *
	 * @return O EJB solicitado
	 * @see CooperativaServico
	 */
	public CooperativaServico localizarCooperativaServico() {
		return (CooperativaServico) localizar("locator.replicacao.CooperativaServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code AvaliacaoFinanceiraServico}.
	 *
	 * @return O EJB solicitado
	 * @see AvaliacaoFinanceiraServico
	 */
	public AvaliacaoFinanceiraServico localizarAvaliacaoFinanceiraServico() {
		return (AvaliacaoFinanceiraServico) localizar(
				"locator.replicacao.AvaliacaoFinanceiraServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code InformacaoProfissionalServico}.
	 *
	 * @return O EJB solicitado
	 * @see InformacaoProfissionalServico
	 */
	public InformacaoProfissionalServico localizarInformacaoProfissionalServico() {
		return (InformacaoProfissionalServico) localizar(
				"locator.replicacao.InformacaoProfissionalServico");
	}
}