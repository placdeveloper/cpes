/*
 * SICOOB
 * 
 * FabricaHistoricoBuilder.java(br.com.sicoob.capes.negocio.historico.FabricaHistoricoBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.Mensagem;
import br.com.sicoob.capes.negocio.entidades.ReferenciaBancaria;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;
import br.com.sicoob.capes.negocio.entidades.vigente.RegistroRelacionamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;


/**
 * Fábrica para os builders de histórico das entidades.
 * @author Erico.Junior
 */
public final class FabricaHistoricoBuilder<H extends Historico, V extends Vigente> {

	/** Instancia do Fabrica. */
	private static FabricaHistoricoBuilder<Historico, Vigente> fabrica = new FabricaHistoricoBuilder<Historico, Vigente>();

	/**
	 * Construtor privado para garantir a instancia unica.
	 */
	private FabricaHistoricoBuilder() {
	}

	/**
	 * Retorna a fabrica a ser utilizada.
	 *
	 * @return a fabrica a ser utilizada.
	 */
	public static FabricaHistoricoBuilder<Historico, Vigente> getInstance() {
		return fabrica;
	}

	/**
	 * Recupera o Builder para o historico da entidade vigente.
	 * @param vigente A entidade vigente.
	 * @return o Builder para o historico da entidade vigente.
	 */
	public HistoricoBuilder<?, ?> obterBuilder(V vigente) {

		HistoricoBuilder<?, ?> builder = null;

		if(vigente instanceof ReferenciaBancaria) {
			builder = new HistoricoReferenciaBancariaBuilder();
		} else if(vigente instanceof Referencia) {
			builder = new HistoricoReferenciaBuilder();
		} else if(vigente instanceof ResponsavelCadastro) {
			builder = new HistoricoResponsavelCadastroBuilder();
		} else if(vigente instanceof FonteRenda) {
			builder = new HistoricoFonteRendaBuilder();
		} else if (vigente instanceof BemPosse) {
			builder = new HistoricoBemPosseAntigoBuilder();
		} else if (vigente instanceof BemRegistro) {
			builder = new HistoricoBemRegistroAntigoBuilder();
		} else if (vigente instanceof BemOnus) {
			builder = new HistoricoBemOnusAntigoBuilder();
		} else if (vigente instanceof BemImovel) {
			builder = new HistoricoBemImovelAntigoBuilder();
		} else if (vigente instanceof br.com.sicoob.capes.negocio.entidades.bemantigo.Bem) {
			builder = new br.com.sicoob.capes.negocio.historico.HistoricoBemAntigoBuilder();
		} else if (vigente instanceof Certidao) {
			builder = new HistoricoCertidaoBuilder();
		} else if ((vigente instanceof RelacionamentoPessoa)
				|| (vigente instanceof RegistroRelacionamento)) {
			builder = new HistoricoRelacionamentoPessoaBuilder();
		} else if (vigente instanceof Tributacao) {
			builder = new HistoricoTributacaoBuilder();
		} else if (vigente instanceof PessoaInstituicao) {
			builder = new HistoricoPessoaInstituicaoBuilder();
		} else if( vigente instanceof PessoaFisica) {
			builder = new HistoricoPessoaFisicaBuilder();
		} else if( vigente instanceof PessoaJuridica) {
			builder = new HistoricoPessoaJuridicaBuilder();
		} else if (vigente instanceof Endereco) {
			builder = new HistoricoEnderecoBuilder();
		} else if (vigente instanceof Telefone) {
			builder = new HistoricoTelefoneBuilder();
		} else if (vigente instanceof Email) {
			builder = new HistoricoEmailBuilder();
		} else if (vigente instanceof Produtor) {
			builder = new HistoricoProdutorBuilder();
		} else if(vigente instanceof Produtividade) {
			builder = new HistoricoProdutividadeBuilder();
		} else if (vigente instanceof GrupoEconomicoPessoa) {
			builder = new HistoricoGrupoEconomicoPessoaBuilder();
		} else if (vigente instanceof GrupoCompartilhamento) {
			builder = new HistoricoGrupoCompartilhamentoBuilder();
		} else if (vigente instanceof InformacaoProfissional) {
			builder = new HistoricoInformacaoProfissionalBuilder();
		} else if (vigente instanceof Mensagem) {
			builder = new HistoricoMensagemPessoaBuilder();
		} else if (vigente instanceof Bem) {
			builder = new HistoricoBemBuilder();
		} else if (vigente instanceof BemPessoaCompartilhamento) {
			builder = new HistoricoBemPessoaCompartilhamentoBuilder();
		} else if (vigente instanceof GrupoEconomicoNovo) {
			builder = new HistoricoGrupoEconomicoNovoBuilder();
		} else if (vigente instanceof GrupoEconomicoAutomaticoPessoa) {
			builder = new HistoricoGrupoEconomicoAutomaticoPessoaBuilder();
		} else if (vigente instanceof GrupoEconomicoManualPessoa) {
			builder = new HistoricoGrupoEconomicoManualPessoaBuilder();
		}

		return builder;
	}
}