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
	 * Localiza o servi�o de pessoas.
	 * 
	 * @return o servi�o de pessoas a ser retornado.
	 */
	public PessoaServico localizarPessoaServico() {
		return (PessoaServico) localizar("locator.replicacao.PessoaServico");
	}

	/**
	 * Localiza o servi�o de refer�ncias.
	 * 
	 * @return o servi�o de refer�ncias a ser retornado.
	 */
	public ReferenciaServico localizarReferenciaServico() {
		return (ReferenciaServico) localizar("locator.replicacao.ReferenciaServico");
	}

	/**
	 * Localiza o servi�o de fonte de rendas.
	 * 
	 * @return o servi�o de fonte de rendas.
	 */
	public FonteRendaServico localizarFonteRendaServico() {
		return (FonteRendaServico) localizar("locator.replicacao.FonteRendaServico");
	}

	/**
	 * Localiza o servi�o de bens.
	 * 
	 * @return o servi�o de bens.
	 */
	public BemServico localizarBemServico() {
		return (BemServico) localizar("locator.replicacao.BemServico");
	}

	/**
	 * Localiza o servi�o de produtores.
	 * 
	 * @return o servi�o de produtores.
	 */
	public ProdutorServico localizarProdutorServico() {
		return (ProdutorServico) localizar("locator.replicacao.ProdutorServico");
	}

	/**
	 * Localiza o servi�o para o hist�rico de altera��o de cpf/cnpj.
	 * 
	 * @return o servi�o para o hist�rico de altera��o de cpf/cnpj.
	 */
	public HistoricoAlteracaoCnpjCpfServico localizarHistoricoAlteracaoCnpjCpfServico() {
		return (HistoricoAlteracaoCnpjCpfServico) localizar("locator.replicacao.HistoricoAlteracaoCnpjCpfServico");
	}

	/**
	 * Localiza o servi�o para as listas.
	 * 
	 * @return o servi�o para as listas.
	 */
	public ListaServico localizarListaServico() {
		return (ListaServico) localizar("locator.replicacao.ListaServico");
	}

	/**
	 * Localiza o servi�o para os itens das listas.
	 * 
	 * @return o servi�o para os itens das listas.
	 */
	public ListaItemServico localizarListaItemServico() {
		return (ListaItemServico) localizar("locator.replicacao.ListaItemServico");
	}

	/**
	 * Localiza o servi�o para as unidades de medidas.
	 * 
	 * @return o servi�o para as unidades de medidas.
	 */
	public UnidadeMedidaServico localizarUnidadeMedidaServico() {
		return (UnidadeMedidaServico) localizar("locator.replicacao.UnidadeMedidaServico");
	}

	/**
	 * Localiza o servi�o para os tipos de bens.
	 * 
	 * @return o servi�o para os tipos de bens.
	 */
	public TipoBemServico localizarTipoBemServico() {
		return (TipoBemServico) localizar("locator.replicacao.TipoBemServico");
	}

	/**
	 * Localiza o servi�o para os tipos de empresa.
	 * 
	 * @return o servi�o para os tipos de empresa.
	 */
	public TipoEmpresaServico localizarTipoEmpresaServico() {
		return (TipoEmpresaServico) localizar("locator.replicacao.TipoEmpresaServico");
	}
	
	/**
	 * Localiza o servi�o para os bens onus.
	 * 
	 * @return o servi�o para os bens onus.
	 */
	public BemOnusServico localizarBemOnusServico() {
		return (BemOnusServico) localizar("locator.replicacao.BemOnusServico");
	}

	/**
	 * Localiza o servi�o para os bens de posse.
	 * 
	 * @return o servi�o para os bens de posse.
	 */
	public BemPosseServico localizarBemPosseServico() {
		return (BemPosseServico) localizar("locator.replicacao.BemPosseServico");
	}

	/**
	 * Localiza o servi�o para os bens de registro.
	 * 
	 * @return o servi�o para os bens de registro.
	 */
	public BemRegistroServico localizarBemRegistroServico() {
		return (BemRegistroServico) localizar("locator.replicacao.BemRegistroServico");
	}

	/**
	 * Localiza o servi�o para os bens de imovel.
	 * 
	 * @return o servi�o para os bens de imovel.
	 */
	public BemImovelServico localizarBemImovelServico() {
		return (BemImovelServico) localizar("locator.replicacao.BemImovelServico");
	}

	/**
	 * Localiza o servi�o para as mensagens.
	 * 
	 * @return o servi�o para as mensagens.
	 */
	public MensagemServico localizarMensagemServico() {
		return (MensagemServico) localizar("locator.replicacao.MensagemServico");
	}

	/**
	 * Localiza o servi�o para os endere�os.
	 * 
	 * @return o servi�o para os endere�os.
	 */
	public EnderecoServico localizarEnderecoServico() {
		return (EnderecoServico) localizar("locator.replicacao.EnderecoServico");
	}

	/**
	 * Localiza o servi�o para os telefones.
	 * 
	 * @return o servi�o para os telefones.
	 */
	public TelefoneServico localizarTelefoneServico() {
		return (TelefoneServico) localizar("locator.replicacao.TelefoneServico");
	}

	/**
	 * Localiza o servi�o para as certidoes.
	 * 
	 * @return o servi�o para as certidoes.
	 */
	public CertidaoServico localizarCertidaoServico() {
		return (CertidaoServico) localizar("locator.replicacao.CertidaoServico");
	}

	/**
	 * Localiza o servi�o para os clientes.
	 * 
	 * @return o servi�o para os clientes.
	 */
	public ClienteServico localizarClienteServico() {
		return (ClienteServico) localizar("locator.replicacao.ClienteServico");
	}

	/**
	 * Localiza o servi�o para os clientes.
	 * 
	 * @return o servi�o para os clientes.
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
	 * Localiza o servi�o de nucleos.
	 * 
	 * @return o servi�o de nucleos a ser retornado.
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