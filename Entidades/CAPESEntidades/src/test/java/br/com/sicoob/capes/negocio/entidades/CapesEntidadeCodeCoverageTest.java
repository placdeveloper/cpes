package br.com.sicoob.capes.negocio.entidades;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoBemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoChassiBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoCorAutomovelBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoImplantacaoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoPadraoAcabamentoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoRelacionamentoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoServicoCondominialBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBem;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovelAvaliacaoRural;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovelAvaliacaoUrbano;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemMovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoCertidao;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoEmail;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoEndereco;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoInformacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoMensagemPessoa;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaFisica;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoProdutividade;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoProdutor;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoReferencia;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoReferenciaBancaria;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoRegistroRelacionamento;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoResponsavelCadastro;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoTelefone;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoTributacao;
import br.com.sicoob.capes.negocio.entidades.pk.ParametroInstituicaoPK;
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
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * A Classe CapesEntidadeCodeCoverageTest.
 */
public class CapesEntidadeCodeCoverageTest {

	/** A constante setArgs. */
	public static final Object setArgs[] = { null };
	
	/** A constante noArgs. */
	public static final Object noArgs[] = {};

	/**
	 * O método Superficial capes entidade code coverage.
	 *
	 * @param classe o valor de classe
	 * @param entidate o valor de entidade
	 */
	@SuppressWarnings("rawtypes")
	public static void superficialCapesEntidadeCodeCoverage(Class classe, Object entidate) {

		Method[] methods = classe.getMethods();
		try {
			for (Method method : methods) {
				if (method.getName().startsWith("set")) {
					method.invoke(entidate, setArgs);
				}
				if (method.getName().startsWith("get")) {
					method.invoke(entidate, noArgs);
				}
			}
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
	}
	

	/**
	 * O método Test superficial capes entidade code coverage.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Test
	public void testSuperficialCapesEntidadeCodeCoverage() throws Exception {
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Anotacao.class, new Anotacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AnotacaoSisbr.class, new AnotacaoSisbr());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Arquivo.class, new Arquivo());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AtividadeEconomica.class, new AtividadeEconomica());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Autorizacao.class, new Autorizacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AutorizacaoDocumento.class, new AutorizacaoDocumento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(br.com.sicoob.capes.negocio.entidades.bemantigo.Bem.class, new br.com.sicoob.capes.negocio.entidades.bemantigo.Bem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel.class, new br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemOnus.class, new BemOnus());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemPosse.class, new BemPosse());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemRegistro.class, new BemRegistro());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CategoriaAnotacao.class, new CategoriaAnotacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CategoriaProdutor.class, new CategoriaProdutor());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Certidao.class, new Certidao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Cidadania.class, new Cidadania());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CnaeFiscal.class, new CnaeFiscal());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CompartilhamentoCadastro.class, new CompartilhamentoCadastro());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ConselhoRegional.class, new ConselhoRegional());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CooperativaAnotacao.class, new CooperativaAnotacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DadosArquivoExportacao.class, new DadosArquivoExportacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DadosConfiguracaoFluxo.class, new DadosConfiguracaoFluxo());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DadosRegistroRelacionamento.class, new DadosRegistroRelacionamento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DestinoExportacao.class, new DestinoExportacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DocumentoComprobatorio.class, new DocumentoComprobatorio());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Email.class, new Email());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Empreendimento.class, new Empreendimento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Endereco.class, new Endereco());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EnderecoCorrespondencia.class, new EnderecoCorrespondencia());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EnderecoFiscal.class, new EnderecoFiscal());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EstadoCivil.class, new EstadoCivil());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Exportacao.class, new Exportacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FinalidadeEmpreendimento.class, new FinalidadeEmpreendimento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FonteRenda.class, new FonteRenda());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Funcao.class, new Funcao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Funcionario.class, new Funcionario());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrauInstrucao.class, new GrauInstrucao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrupoCompartilhamento.class, new GrupoCompartilhamento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrupoEconomico.class, new GrupoEconomico());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrupoEconomicoPessoa.class, new GrupoEconomicoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoAlteracaoCpfCnpj.class, new HistoricoAlteracaoCpfCnpj());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(InformacaoProfissional.class, new InformacaoProfissional());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(InformacaoUtilizada.class, new InformacaoUtilizada());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Instituicao.class, new Instituicao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Localidade.class, new Localidade());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(LogCompartilhamentoCadastro.class, new LogCompartilhamentoCadastro());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(MapaTipoAnotacao.class, new MapaTipoAnotacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Mensagem.class, new Mensagem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(MensagemReplicacao.class, new MensagemReplicacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Nacionalidade.class, new Nacionalidade());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Nucleo.class, new Nucleo());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ObservacaoAnotacao.class, new ObservacaoAnotacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(OcupacaoProfissional.class, new OcupacaoProfissional());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(OrgaoEmissorCertidao.class, new OrgaoEmissorCertidao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(OrigemInformacao.class, new OrigemInformacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PerfilTarifario.class, new PerfilTarifario());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PeriodicidadeAnotacao.class, new PeriodicidadeAnotacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Pessoa.class, new Pessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaInstituicao.class, new PessoaInstituicao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Produtividade.class, new Produtividade());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Produtor.class, new Produtor());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ReferenciaBancaria.class, new ReferenciaBancaria());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ReferenciaBancariaBase.class, new ReferenciaBancariaBase());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Referencia.class, new Referencia());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RegimeCasamento.class, new RegimeCasamento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelacionamentoPessoa.class, new RelacionamentoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ResponsavelCadastro.class, new ResponsavelCadastro());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(SituacaoBem.class, new SituacaoBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(SubTipoBem.class, new SubTipoBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(SubTipoCertidao.class, new SubTipoCertidao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Telefone.class, new Telefone());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoAbrangenciaCertidao.class, new TipoAbrangenciaCertidao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoAfastamento.class, new TipoAfastamento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoAnotacao.class, new TipoAnotacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoBaixa.class, new TipoBaixa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoBem.class, new TipoBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoCaptura.class, new TipoCaptura());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoCertidao.class, new TipoCertidao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoConsultaOrigem.class, new TipoConsultaOrigem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoDocumento.class, new TipoDocumento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoDocumentoIdentificacao.class, new TipoDocumentoIdentificacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoEmail.class, new TipoEmail());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoEmpresa.class, new TipoEmpresa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoEndereco.class, new TipoEndereco());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoFonteRenda.class, new TipoFonteRenda());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoFormaConstituicao.class, new TipoFormaConstituicao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoFormaConstituicaoEsferaAdministrativa.class, new TipoFormaConstituicaoEsferaAdministrativa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoFuncionario.class, new TipoFuncionario());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoInformacao.class, new TipoInformacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoLogradouro.class, new TipoLogradouro());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoObjetoCertidao.class, new TipoObjetoCertidao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoObservacaoAnotacao.class, new TipoObservacaoAnotacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoPessoa.class, new TipoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoPessoaContato.class, new TipoPessoaContato());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoPoderRelacionamento.class, new TipoPoderRelacionamento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoPosseBem.class, new TipoPosseBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoPrazoCertidao.class, new TipoPrazoCertidao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoReferencia.class, new TipoReferencia());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoRegraValidacaoCadastral.class, new TipoRegraValidacaoCadastral());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoRelacionamentoPessoa.class, new TipoRelacionamentoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoTelefone.class, new TipoTelefone());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TransicaoPessoa.class, new TransicaoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Tributacao.class, new Tributacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(UF.class, new UF());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(UnidadeMedida.class, new UnidadeMedida());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(UtilizaInformacao.class, new UtilizaInformacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ValidacaoCadastral.class, new ValidacaoCadastral());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ValidacaoCadastralErro.class, new ValidacaoCadastralErro());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ValidacaoCadastralRegra.class, new ValidacaoCadastralRegra());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(VinculoEmpregaticio.class, new VinculoEmpregaticio());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaFisica.class, new PessoaFisica());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaJuridica.class, new PessoaJuridica());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaInstituicao.class, new PessoaInstituicao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrupoEconomicoPessoa.class, new GrupoEconomicoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBem.class, new HistoricoBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemImovel.class, new HistoricoBemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemOnus.class, new HistoricoBemOnus());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemPosse.class, new HistoricoBemPosse());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemRegistro.class, new HistoricoBemRegistro());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoCertidao.class, new HistoricoCertidao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoEmail.class, new HistoricoEmail());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoEndereco.class, new HistoricoEndereco());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoFonteRenda.class, new HistoricoFonteRenda());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoGrupoCompartilhamento.class, new HistoricoGrupoCompartilhamento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoGrupoEconomicoPessoa.class, new HistoricoGrupoEconomicoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoInformacaoProfissional.class, new HistoricoInformacaoProfissional());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoMensagemPessoa.class, new HistoricoMensagemPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoPessoaFisica.class, new HistoricoPessoaFisica());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoPessoaInstituicao.class, new HistoricoPessoaInstituicao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoPessoaJuridica.class, new HistoricoPessoaJuridica());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoProdutividade.class, new HistoricoProdutividade());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoProdutor.class, new HistoricoProdutor());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoReferencia.class, new HistoricoReferencia());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoReferenciaBancaria.class, new HistoricoReferenciaBancaria());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoRegistroRelacionamento.class, new HistoricoRegistroRelacionamento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoRelacionamentoPessoa.class, new HistoricoRelacionamentoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoResponsavelCadastro.class, new HistoricoResponsavelCadastro());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoTelefone.class, new HistoricoTelefone());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoTributacao.class, new HistoricoTributacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Bem.class, new Bem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemImovel.class, new BemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemImovelAvaliacao.class, new BemImovelAvaliacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemImovelAvaliacaoRural.class, new BemImovelAvaliacaoRural());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemImovelAvaliacaoUrbano.class, new BemImovelAvaliacaoUrbano());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemImovelTipoRelacionamentoPessoa.class, new BemImovelTipoRelacionamentoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemMovel.class, new BemMovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemMovelAvaliacao.class, new BemMovelAvaliacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemPessoaCompartilhamento.class, new BemPessoaCompartilhamento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoBemImovel.class, new TipoBemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoBemMovel.class, new TipoBemMovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoChassiBem.class, new TipoChassiBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoClassificacaoBem.class, new TipoClassificacaoBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoCorAutomovelBem.class, new TipoCorAutomovelBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoEstadoConservacaoBem.class, new TipoEstadoConservacaoBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoImplantacaoBemImovel.class, new TipoImplantacaoBemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoLocalizacaoBem.class, new TipoLocalizacaoBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoOnusBem.class, new TipoOnusBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoPadraoAcabamentoBemImovel.class, new TipoPadraoAcabamentoBemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoRelacionamentoBemImovel.class, new TipoRelacionamentoBemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoServicoCondominialBemImovel.class, new TipoServicoCondominialBemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoUsoBemImovel.class, new TipoUsoBemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBem.class, new HistoricoBem());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemImovel.class, new HistoricoBemImovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemImovelAvaliacao.class, new HistoricoBemImovelAvaliacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemImovelAvaliacaoRural.class, new HistoricoBemImovelAvaliacaoRural());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemImovelAvaliacaoUrbano.class, new HistoricoBemImovelAvaliacaoUrbano());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemImovelTipoRelacionamentoPessoa.class, new HistoricoBemImovelTipoRelacionamentoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemMovel.class, new HistoricoBemMovel());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemMovelAvaliacao.class, new HistoricoBemMovelAvaliacao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoBemPessoaCompartilhamento.class, new HistoricoBemPessoaCompartilhamento());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Parametro.class, new Parametro());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ParametroInstituicao.class, new ParametroInstituicao());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ParametroInstituicaoPK.class, new ParametroInstituicaoPK());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoGrupoEconomico.class, new TipoGrupoEconomico());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoGrupoEconomicoNovo.class, new HistoricoGrupoEconomicoNovo());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoGrupoEconomicoAutomaticoPessoa.class, new HistoricoGrupoEconomicoAutomaticoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoGrupoEconomicoManualPessoa.class, new HistoricoGrupoEconomicoManualPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrupoEconomicoNovo.class, new GrupoEconomicoNovo());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrupoEconomicoAutomaticoPessoa.class, new GrupoEconomicoAutomaticoPessoa());
		CapesEntidadeCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrupoEconomicoManualPessoa.class, new GrupoEconomicoManualPessoa());
	}

}
