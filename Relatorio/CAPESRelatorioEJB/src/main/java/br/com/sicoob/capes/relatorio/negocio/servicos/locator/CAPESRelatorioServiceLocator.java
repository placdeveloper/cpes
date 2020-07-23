package br.com.sicoob.capes.relatorio.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoCompartilhamentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoEconomicoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.PessoaCompartilhamentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoAnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoEmailServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoEnderecoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoFonteRendaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoRelacionamentoPessoaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoTelefoneServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.FichaCadastralServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.FormularioVisitaServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioAnotacaoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioCadastroCompartilhadoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioCompartilhamentoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioDeclaracaoPropositoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioFichaCadastralServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioGrupoCompartilhamentoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioGrupoEconomicoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioPessoaCompartilhamentoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioProvaVidaServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoAnotacaoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoEmailServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoEnderecoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoFonteRendaServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoRelacionamentoPessoaServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTipoTelefoneServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioTributacaoServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioValidacaoCadastralServico;
import br.com.sicoob.capes.relatorio.negocio.servicos.RelatorioVencimentoCadastroServico;

/**
 * Service Locator usado pelo sistema CAPESRelatorio.
 * 
 * @author Stefanini IT Solutions
 */
public final class CAPESRelatorioServiceLocator extends BancoobServiceLocator {

	/** O atributo locator. */
	private static CAPESRelatorioServiceLocator locator;

	/**
	 * Singleton da class
	 * 
	 * @return A instancia da classe
	 */
	public static CAPESRelatorioServiceLocator getInstance() {
		if (locator == null) {
			synchronized (CAPESRelatorioServiceLocator.class) {
				if (locator == null) {
					locator = new CAPESRelatorioServiceLocator();
				}
			}
		}

		return locator;
	}

	/**
	 * @param nomeAplicacao
	 */
	private CAPESRelatorioServiceLocator() {
		super("capes.relatorio");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code FichaCadastralServico}.
	 *
	 * @return O EJB solicitado
	 * @see FichaCadastralServico
	 */
	public FichaCadastralServico localizarFichaCadastralServico() {
		return (FichaCadastralServico) localizar("locator.capes.FichaCadastralServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code GrupoCompartilhamentoServico}.
	 *
	 * @return O EJB solicitado
	 * @see GrupoCompartilhamentoServico
	 */
	public RelatorioGrupoCompartilhamentoServico localizarGrupoCompartilhamentoServico() {
		return (RelatorioGrupoCompartilhamentoServico) localizar("locator.capes.RelatorioGrupoCompartilhamentoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code RelatorioAnotacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelatorioAnotacaoServico
	 */
	public RelatorioAnotacaoServico localizarRelatorioAnotacaoServico() {
		return (RelatorioAnotacaoServico) localizar("locator.capes.RelatorioAnotacaoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code RelatorioProvaVidaServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelatorioProvaVidaServico
	 */
	public RelatorioProvaVidaServico localizarRelatorioProvaVidaServico() {
		return (RelatorioProvaVidaServico) localizar("locator.capes.RelatorioProvaVidaServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code RelatorioFichaCadastralServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelatorioFichaCadastralServico
	 */
	public RelatorioFichaCadastralServico localizarRelatorioFichaCadastralServico() {
		return (RelatorioFichaCadastralServico) localizar("locator.capes.RelatorioFichaCadastralServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code RelatorioFichaCadastralEmBrancoServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelatorioFichaCadastralEmBrancoServico
	 */
	public RelatorioFichaCadastralServico localizarRelatorioFichaCadastralEmBrancoServico() {
		return (RelatorioFichaCadastralServico) localizar("locator.capes.RelatorioFichaCadastralEmBrancoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code RelCompartilhamentoServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelCompartilhamentoServico
	 */
	public RelatorioCompartilhamentoServico localizarRelCompartilhamentoServico() {
		return (RelatorioCompartilhamentoServico) localizar("locator.capes.RelatorioCompartilhamentoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code RelatorioCadastroCompartilhadoServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelatorioCadastroCompartilhadoServico
	 */
	public RelatorioCadastroCompartilhadoServico localizarRelatorioCadastroCompartilhadoServico() {
		return (RelatorioCadastroCompartilhadoServico) localizar("locator.capes.RelatorioCadastroCompartilhadoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code RelVencimentoCadastroServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelVencimentoCadastroServico
	 */
	public RelatorioVencimentoCadastroServico localizarRelVencimentoCadastroServico() {
		return (RelatorioVencimentoCadastroServico) localizar("locator.capes.RelatorioVencimentoCadastroServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code TipoAnotacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoAnotacaoServico
	 */
	public RelatorioTipoAnotacaoServico localizarTipoAnotacaoServico() {
		return (RelatorioTipoAnotacaoServico) localizar("locator.capes.RelatorioTipoAnotacaoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code TipoEmailServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoEmailServico
	 */
	public RelatorioTipoEmailServico localizarTipoEmailServico() {
		return (RelatorioTipoEmailServico) localizar("locator.capes.RelatorioTipoEmailServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code TipoEnderecoServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoEnderecoServico
	 */
	public RelatorioTipoEnderecoServico localizarTipoEnderecoServico() {
		return (RelatorioTipoEnderecoServico) localizar("locator.capes.RelatorioTipoEnderecoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code TipoFonteRendaServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoFonteRendaServico
	 */
	public RelatorioTipoFonteRendaServico localizarTipoFonteRendaServico() {
		return (RelatorioTipoFonteRendaServico) localizar("locator.capes.RelatorioTipoFonteRendaServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code TipoRelacionamentoPessoaServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoRelacionamentoPessoaServico
	 */
	public RelatorioTipoRelacionamentoPessoaServico localizarTipoRelacionamentoPessoaServico() {
		return (RelatorioTipoRelacionamentoPessoaServico) localizar("locator.capes.RelatorioTipoRelacionamentoPessoaServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code TipoTelefoneServico}.
	 *
	 * @return O EJB solicitado
	 * @see TipoTelefoneServico
	 */
	public RelatorioTipoTelefoneServico localizarTipoTelefoneServico() {
		return (RelatorioTipoTelefoneServico) localizar("locator.capes.RelatorioTipoTelefoneServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code PessoaCompartilhamentoServico}.
	 *
	 * @return O EJB solicitado
	 * @see PessoaCompartilhamentoServico
	 */
	public RelatorioPessoaCompartilhamentoServico localizarPessoaCompartilhamentoServico() {
		return (RelatorioPessoaCompartilhamentoServico) localizar("locator.capes.RelatorioPessoaCompartilhamentoServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code GrupoEconomicoServico}.
	 *
	 * @return O EJB solicitado
	 * @see GrupoEconomicoServico
	 */
	public RelatorioGrupoEconomicoServico localizarGrupoEconomicoServico() {
		return (RelatorioGrupoEconomicoServico) localizar("locator.capes.RelatorioGrupoEconomicoServico");
	}
	
	/**
	 * Localiza o EJB que implementa a interface {@code RelatorioValidacaoCadastralServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelatorioValidacaoCadastralServico
	 */
	public RelatorioValidacaoCadastralServico localizarRelatorioValidacaoCadastralServico(){
		return (RelatorioValidacaoCadastralServico) localizar("locator.capes.RelatorioValidacaoCadastralServico");
	}

	/**
	 * Localiza o EJB que implementa a interface {@code RelatorioTributacaoServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelatorioTributacaoServico
	 */
	public RelatorioTributacaoServico localizarRelatorioTributacaoServico() {
	    return (RelatorioTributacaoServico) localizar("locator.capes.RelatorioTributacaoServico");
    }
	
	/**
	 * Localiza o EJB que implementa a interface {@code RelatorioDeclaracaoPropositoServico}.
	 *
	 * @return O EJB solicitado
	 * @see RelatorioDeclaracaoPropositoServico
	 */
	public RelatorioDeclaracaoPropositoServico localizarRelatorioDeclaracaoPropositoServico() {
		return (RelatorioDeclaracaoPropositoServico) localizar("locator.capes.RelatorioDeclaracaoPropositoServico");
	}
	
	public FormularioVisitaServico localizarRelatorioFormularioVisitaServico() {
		return (FormularioVisitaServico) localizar("locator.capes.RelatorioFormularioVisitaServico");
	}

}