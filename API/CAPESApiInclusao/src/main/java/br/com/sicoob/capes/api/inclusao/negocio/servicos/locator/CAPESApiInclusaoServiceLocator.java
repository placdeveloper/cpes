package br.com.sicoob.capes.api.inclusao.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.AnotacaoServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.BemServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.ClienteServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.EmailServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.EnderecoServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.FonteRendaServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.InformacaoProfissionalServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.MensagemServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.PessoaServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.PlataformaContabilServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.ReferenciaServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.RelacionamentoServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.TelefoneServico;

/**
 * Classe responsável por localizar um EJB.
 *
 * @author bruno.carneiro
 */
public final class CAPESApiInclusaoServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESApiInclusaoServiceLocator locator = new CAPESApiInclusaoServiceLocator();

	/**
	 * Recupera a unica instancia de CAPESApiInclusaoServiceLocator.
	 *
	 * @return uma instancia de CAPESApiInclusaoServiceLocator
	 */
	public static CAPESApiInclusaoServiceLocator getInstance() {
		return locator;
	}

	/**
	 * Instancia um novo CAPESApiInclusaoServiceLocator.
	 */
	private CAPESApiInclusaoServiceLocator() {
		super("capes.api.inclusao");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code AnotacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see AnotacaoServico
	 */
	public AnotacaoServico localizarAnotacaoServico() {
		return (AnotacaoServico) localizar("locator.capes.AnotacaoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code BemServico}.
	 *
	 * @return O EJB solicitado
	 * @see BemServico
	 */
	public BemServico localizarBemServico() {
		return (BemServico) localizar("locator.capes.BemServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code ClienteServico}.
	 *
	 * @return O EJB solicitado
	 * @see ClienteServico
	 */
	public ClienteServico localizarClienteServico() {
		return (ClienteServico) localizar("locator.capes.ClienteServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code EmailServico}.
	 *
	 * @return O EJB solicitado
	 * @see EmailServico
	 */
	public EmailServico localizarEmailServico() {
		return (EmailServico) localizar("locator.capes.EmailServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code EnderecoServico}.
	 *
	 * @return O EJB solicitado
	 * @see EnderecoServico
	 */
	public EnderecoServico localizarEnderecoServico() {
		return (EnderecoServico) localizar("locator.capes.EnderecoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code FonteRendaServico}.
	 *
	 * @return O EJB solicitado
	 * @see FonteRendaServico
	 */
	public FonteRendaServico localizarFonteRendaServico() {
		return (FonteRendaServico) localizar("locator.capes.FonteRendaServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code MensagemServico}.
	 *
	 * @return O EJB solicitado
	 * @see MensagemServico
	 */
	public MensagemServico localizarMensagemServico() {
		return (MensagemServico) localizar("locator.capes.MensagemServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code PessoaServico}.
	 *
	 * @return O EJB solicitado
	 * @see PessoaServico
	 */
	public PessoaServico localizarPessoaServico() {
		return (PessoaServico) localizar("locator.capes.PessoaServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code ReferenciaServico}.
	 *
	 * @return O EJB solicitado
	 * @see ReferenciaServico
	 */
	public ReferenciaServico localizarReferenciaServico() {
		return (ReferenciaServico) localizar("locator.capes.ReferenciaServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code RelacionamentoServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelacionamentoServico
	 */
	public RelacionamentoServico localizarRelacionamentoServico() {
		return (RelacionamentoServico) localizar("locator.capes.RelacionamentoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code TelefoneServico}.
	 *
	 * @return O EJB solicitado
	 * @see TelefoneServico
	 */
	public TelefoneServico localizarTelefoneServico() {
		return (TelefoneServico) localizar("locator.capes.TelefoneServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code InformacaoProfissionalServico}.
	 *
	 * @return O EJB solicitado
	 * @see InformacaoProfissionalServico
	 */
	public InformacaoProfissionalServico localizarInformacaoProfissionalServico() {
		return (InformacaoProfissionalServico) localizar("locator.capes.InformacaoProfissionalServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code PlataformaContabilServico}.
	 *
	 * @return O EJB solicitado
	 * @see PlataformaContabilServico
	 */
	public PlataformaContabilServico localizarPlataformaContabilServico() {
		return (PlataformaContabilServico) localizar("locator.capes.PlataformaContabilServico");
	}

}