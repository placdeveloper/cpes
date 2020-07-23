package br.com.sicoob.capes.comum.arquivos.teste.migracao;

import java.math.BigDecimal;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * A Classe CliVO.
 */
public class CliVO implements RegistroArquivo {

	/** O atributo numeroLinha. */
	private int numeroLinha;

	/** O atributo numeroPessoa. */
	@CampoArquivo(inicio = 0, tamanho = 6)
	private Integer numeroPessoa;

	/** O atributo codTipoPessoa. */
	@CampoArquivo(inicio = 6, tamanho = 1)
	private Short codTipoPessoa;

	/** O atributo cliente. */
	@CampoArquivo(inicio = 7, tamanho = 1, descBooleanTrue = "S", descBooleanFalse = "N")
	private Boolean cliente;

	/** O atributo dataCadastramentoPessoa. */
	@CampoArquivo(inicio = 8, tamanho = 8, formatoData = "yyyyMMdd")
	private DateTimeDB dataCadastramentoPessoa;

	/** O atributo cpfCnpj. */
	@CampoArquivo(inicio = 16, tamanho = 14)
	private String cpfCnpj;

	/** O atributo nomeCliente. */
	@CampoArquivo(inicio = 30, tamanho = 50)
	private String nomeCliente;

	/** O atributo apelido. */
	@CampoArquivo(inicio = 80, tamanho = 30)
	private String apelido;

	/** O atributo codAtividadeEconomica. */
	@CampoArquivo(inicio = 156, tamanho = 4)
	private Short codAtividadeEconomica;

	/** O atributo nomePai. */
	@CampoArquivo(inicio = 160, tamanho = 50)
	private String nomePai;

	/** O atributo nomeMae. */
	@CampoArquivo(inicio = 210, tamanho = 50)
	private String nomeMae;

	/** O atributo dataNascimento. */
	@CampoArquivo(inicio = 260, tamanho = 8, formatoData = "yyyyMMdd")
	private DateTimeDB dataNascimento;

	/** O atributo codNacionalidade. */
	@CampoArquivo(inicio = 268, tamanho = 3)
	private Short codNacionalidade;

	/** O atributo codNaturalidade. */
	@CampoArquivo(inicio = 271, tamanho = 6)
	private Short codNaturalidade;

	/** O atributo descNaturalidade. */
	@CampoArquivo(inicio = 277, tamanho = 4)
	private String descNaturalidade;

	/** O atributo codCnae. */
	@CampoArquivo(inicio = 317, tamanho = 7)
	private String codCnae;

	/** O atributo codTipoDocumentoIdentificacao. */
	@CampoArquivo(inicio = 328, tamanho = 2)
	private Short codTipoDocumentoIdentificacao;

	/** O atributo codEsferaAdministrativa. */
	@CampoArquivo(inicio = 330, tamanho = 2)
	private Short codEsferaAdministrativa;

	/** O atributo numDocumentoIdentificacao. */
	@CampoArquivo(inicio = 332, tamanho = 15)
	private String numDocumentoIdentificacao;

	/** O atributo orgaoExpedidor. */
	@CampoArquivo(inicio = 347, tamanho = 10)
	private String orgaoExpedidor;

	/** O atributo ufOrgaoExpedidor. */
	@CampoArquivo(inicio = 357, tamanho = 2)
	private String ufOrgaoExpedidor;

	/** O atributo dataEmissaoIdentidade. */
	@CampoArquivo(inicio = 359, tamanho = 8, formatoData = "yyyyMMdd")
	private DateTimeDB dataEmissaoIdentidade;

	/** O atributo codEstadoCivil. */
	@CampoArquivo(inicio = 367, tamanho = 1)
	private Short codEstadoCivil;

	/** O atributo codRegimeCasamento. */
	@CampoArquivo(inicio = 368, tamanho = 1)
	private Short codRegimeCasamento;

	/** O atributo codSexo. */
	@CampoArquivo(inicio = 369, tamanho = 1)
	private Short codSexo;

	/** O atributo codEscolaridade. */
	@CampoArquivo(inicio = 370, tamanho = 2)
	private Short codEscolaridade;

	/** O atributo codVinculoEmpregaticio. */
	@CampoArquivo(inicio = 372, tamanho = 2)
	private Short codVinculoEmpregaticio;

	/** O atributo uniaoEstavel. */
	@CampoArquivo(inicio = 374, tamanho = 1)
	private Boolean uniaoEstavel;

	/** O atributo qtdDependentes. */
	@CampoArquivo(inicio = 426, tamanho = 2)
	private Short qtdDependentes;

	/** O atributo codInscricaoProdutorRural. */
	@CampoArquivo(inicio = 428, tamanho = 12)
	private String codInscricaoProdutorRural;

	/** O atributo codCategoriaProdutor. */
	@CampoArquivo(inicio = 440, tamanho = 4)
	private Short codCategoriaProdutor;

	/** O atributo capitalSocialPJ. */
	@CampoArquivo(inicio = 444, tamanho = 15, precisaoDecimal = 2)
	private BigDecimal capitalSocialPJ;

	/** O atributo numeroRegistroJuntaComercial. */
	@CampoArquivo(inicio = 459, tamanho = 12)
	private String numeroRegistroJuntaComercial;

	/** O atributo dataRegistroJuntaComercial. */
	@CampoArquivo(inicio = 471, tamanho = 8, formatoData = "yyyyMMdd")
	private DateTimeDB dataRegistroJuntaComercial;

	/** O atributo numeroRegistroRepresentacao. */
	@CampoArquivo(inicio = 479, tamanho = 12)
	private String numeroRegistroRepresentacao;

	/** O atributo dataRegistroRepresentacao. */
	@CampoArquivo(inicio = 491, tamanho = 8, formatoData = "yyyyMMdd")
	private DateTimeDB dataRegistroRepresentacao;

	/** O atributo numeroUltimaAlteracaoContratoSocial. */
	@CampoArquivo(inicio = 499, tamanho = 12)
	private String numeroUltimaAlteracaoContratoSocial;

	/** O atributo dataUltimaAlteracaoContratoSocial. */
	@CampoArquivo(inicio = 511, tamanho = 8, formatoData = "yyyyMMdd")
	private DateTimeDB dataUltimaAlteracaoContratoSocial;

	/** O atributo inscricaoEstadual. */
	@CampoArquivo(inicio = 519, tamanho = 15)
	private String inscricaoEstadual;

	/** O atributo dataConstituicao. */
	@CampoArquivo(inicio = 534, tamanho = 8, formatoData = "yyyyMMdd")
	private DateTimeDB dataConstituicao;

	/** O atributo codTipoEmpresa. */
	@CampoArquivo(inicio = 544, tamanho = 1)
	private Short codTipoEmpresa;

	/** O atributo cobraCPMF. */
	@CampoArquivo(inicio = 545, tamanho = 1, descBooleanTrue = "S", descBooleanFalse = "N")
	private Boolean cobraCPMF;

	/** O atributo cobraIOF. */
	@CampoArquivo(inicio = 546, tamanho = 1, descBooleanTrue = "S", descBooleanFalse = "N")
	private Boolean cobraIOF;

	/** O atributo cobraIRRF. */
	@CampoArquivo(inicio = 547, tamanho = 1, descBooleanTrue = "S", descBooleanFalse = "N")
	private Boolean cobraIRRF;

	/** O atributo codIsencaoCPMF. */
	@CampoArquivo(inicio = 548, tamanho = 1)
	private Short codIsencaoCPMF;

	/** O atributo numPacOrigem. */
	@CampoArquivo(inicio = 549, tamanho = 2)
	private Integer numPacOrigem;

	/** O atributo nivelRisco. */
	@CampoArquivo(inicio = 551, tamanho = 2)
	private String nivelRisco;

	/** O atributo dataRisco. */
	@CampoArquivo(inicio = 553, tamanho = 8, formatoData = "yyyyMMdd")
	private DateTimeDB dataRisco;

	/** O atributo motivoRisco. */
	@CampoArquivo(inicio = 561, tamanho = 200)
	private String motivoRisco;

	/** O atributo emiteAvisoLancamento. */
	@CampoArquivo(inicio = 761, tamanho = 1, descBooleanTrue = "S", descBooleanFalse = "N")
	private Boolean emiteAvisoLancamento;

	/** O atributo valorLimiteTecnicoCurtoPrazo. */
	@CampoArquivo(inicio = 762, tamanho = 15, precisaoDecimal = 2)
	private BigDecimal valorLimiteTecnicoCurtoPrazo;

	/** O atributo valorLimiteTecnicoLongoPrazo. */
	@CampoArquivo(inicio = 777, tamanho = 15, precisaoDecimal = 2)
	private BigDecimal valorLimiteTecnicoLongoPrazo;

	/** O atributo valorLimiteTecnicoRotativo. */
	@CampoArquivo(inicio = 792, tamanho = 15, precisaoDecimal = 2)
	private BigDecimal valorLimiteTecnicoRotativo;

	/** O atributo valorComprometimentoMensalRenda. */
	@CampoArquivo(inicio = 807, tamanho = 15, precisaoDecimal = 2)
	private BigDecimal valorComprometimentoMensalRenda;

	/** O atributo codTipoFormaConstituicao. */
	@CampoArquivo(inicio = 822, tamanho = 3)
	private Short codTipoFormaConstituicao;

	/** O atributo codProfissao. */
	@CampoArquivo(inicio = 825, tamanho = 5)
	private Short codProfissao;

	/** O atributo reservadoSicocob1. */
	@CampoArquivo(inicio = 110, tamanho = 46)
	private String reservadoSicocob1;
	
	/** O atributo reservadoSicocob2. */
	@CampoArquivo(inicio = 324, tamanho = 4)
	private String reservadoSicocob2;
	
	/** O atributo reservadoSicocob3. */
	@CampoArquivo(inicio = 375, tamanho = 51)
	private String reservadoSicocob3;
	
	/** O atributo reservadoSicocob4. */
	@CampoArquivo(inicio = 542, tamanho = 2)
	private String reservadoSicocob4;
	
	/** O atributo filler. */
	@CampoArquivo(inicio = 830, tamanho = 70)
	private String filler;
	
	/**
	 * Recupera o valor de numeroPessoa.
	 *
	 * @return o valor de numeroPessoa
	 */
	public Integer getNumeroPessoa() {
		return numeroPessoa;
	}

	/**
	 * Define o valor de numeroPessoa.
	 *
	 * @param numeroPessoa o novo valor de numeroPessoa
	 */
	public void setNumeroPessoa(Integer numeroPessoa) {
		this.numeroPessoa = numeroPessoa;
	}

	/**
	 * Recupera o valor de codTipoPessoa.
	 *
	 * @return o valor de codTipoPessoa
	 */
	public Short getCodTipoPessoa() {
		return codTipoPessoa;
	}

	/**
	 * Define o valor de codTipoPessoa.
	 *
	 * @param codTipoPessoa o novo valor de codTipoPessoa
	 */
	public void setCodTipoPessoa(Short codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}

	/**
	 * Recupera o valor de cliente.
	 *
	 * @return o valor de cliente
	 */
	public Boolean getCliente() {
		return cliente;
	}

	/**
	 * Define o valor de cliente.
	 *
	 * @param cliente o novo valor de cliente
	 */
	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}

	/**
	 * Recupera o valor de dataCadastramentoPessoa.
	 *
	 * @return o valor de dataCadastramentoPessoa
	 */
	public DateTimeDB getDataCadastramentoPessoa() {
		return dataCadastramentoPessoa;
	}

	/**
	 * Define o valor de dataCadastramentoPessoa.
	 *
	 * @param dataCadastramentoPessoa o novo valor de dataCadastramentoPessoa
	 */
	public void setDataCadastramentoPessoa(DateTimeDB dataCadastramentoPessoa) {
		this.dataCadastramentoPessoa = dataCadastramentoPessoa;
	}

	/**
	 * Recupera o valor de cpfCnpj.
	 *
	 * @return o valor de cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define o valor de cpfCnpj.
	 *
	 * @param cpfCnpj o novo valor de cpfCnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o valor de nomeCliente.
	 *
	 * @return o valor de nomeCliente
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * Define o valor de nomeCliente.
	 *
	 * @param nomeCliente o novo valor de nomeCliente
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * Recupera o valor de apelido.
	 *
	 * @return o valor de apelido
	 */
	public String getApelido() {
		return apelido;
	}

	/**
	 * Define o valor de apelido.
	 *
	 * @param apelido o novo valor de apelido
	 */
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	/**
	 * Recupera o valor de codAtividadeEconomica.
	 *
	 * @return o valor de codAtividadeEconomica
	 */
	public Short getCodAtividadeEconomica() {
		return codAtividadeEconomica;
	}

	/**
	 * Define o valor de codAtividadeEconomica.
	 *
	 * @param codAtividadeEconomica o novo valor de codAtividadeEconomica
	 */
	public void setCodAtividadeEconomica(Short codAtividadeEconomica) {
		this.codAtividadeEconomica = codAtividadeEconomica;
	}

	/**
	 * Recupera o valor de nomePai.
	 *
	 * @return o valor de nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * Define o valor de nomePai.
	 *
	 * @param nomePai o novo valor de nomePai
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * Recupera o valor de nomeMae.
	 *
	 * @return o valor de nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * Define o valor de nomeMae.
	 *
	 * @param nomeMae o novo valor de nomeMae
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * Recupera o valor de dataNascimento.
	 *
	 * @return o valor de dataNascimento
	 */
	public DateTimeDB getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Define o valor de dataNascimento.
	 *
	 * @param dataNascimento o novo valor de dataNascimento
	 */
	public void setDataNascimento(DateTimeDB dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Recupera o valor de codNacionalidade.
	 *
	 * @return o valor de codNacionalidade
	 */
	public Short getCodNacionalidade() {
		return codNacionalidade;
	}

	/**
	 * Define o valor de codNacionalidade.
	 *
	 * @param codNacionalidade o novo valor de codNacionalidade
	 */
	public void setCodNacionalidade(Short codNacionalidade) {
		this.codNacionalidade = codNacionalidade;
	}

	/**
	 * Recupera o valor de codNaturalidade.
	 *
	 * @return o valor de codNaturalidade
	 */
	public Short getCodNaturalidade() {
		return codNaturalidade;
	}

	/**
	 * Define o valor de codNaturalidade.
	 *
	 * @param codNaturalidade o novo valor de codNaturalidade
	 */
	public void setCodNaturalidade(Short codNaturalidade) {
		this.codNaturalidade = codNaturalidade;
	}

	/**
	 * Recupera o valor de descNaturalidade.
	 *
	 * @return o valor de descNaturalidade
	 */
	public String getDescNaturalidade() {
		return descNaturalidade;
	}

	/**
	 * Define o valor de descNaturalidade.
	 *
	 * @param descNaturalidade o novo valor de descNaturalidade
	 */
	public void setDescNaturalidade(String descNaturalidade) {
		this.descNaturalidade = descNaturalidade;
	}

	/**
	 * Recupera o valor de codCnae.
	 *
	 * @return o valor de codCnae
	 */
	public String getCodCnae() {
		return codCnae;
	}

	/**
	 * Define o valor de codCnae.
	 *
	 * @param codCnae o novo valor de codCnae
	 */
	public void setCodCnae(String codCnae) {
		this.codCnae = codCnae;
	}

	/**
	 * Recupera o valor de codTipoDocumentoIdentificacao.
	 *
	 * @return o valor de codTipoDocumentoIdentificacao
	 */
	public Short getCodTipoDocumentoIdentificacao() {
		return codTipoDocumentoIdentificacao;
	}

	/**
	 * Define o valor de codTipoDocumentoIdentificacao.
	 *
	 * @param codTipoDocumentoIdentificacao o novo valor de codTipoDocumentoIdentificacao
	 */
	public void setCodTipoDocumentoIdentificacao(Short codTipoDocumentoIdentificacao) {
		this.codTipoDocumentoIdentificacao = codTipoDocumentoIdentificacao;
	}

	/**
	 * Recupera o valor de codEsferaAdministrativa.
	 *
	 * @return o valor de codEsferaAdministrativa
	 */
	public Short getCodEsferaAdministrativa() {
		return codEsferaAdministrativa;
	}

	/**
	 * Define o valor de codEsferaAdministrativa.
	 *
	 * @param codEsferaAdministrativa o novo valor de codEsferaAdministrativa
	 */
	public void setCodEsferaAdministrativa(Short codEsferaAdministrativa) {
		this.codEsferaAdministrativa = codEsferaAdministrativa;
	}

	/**
	 * Recupera o valor de numDocumentoIdentificacao.
	 *
	 * @return o valor de numDocumentoIdentificacao
	 */
	public String getNumDocumentoIdentificacao() {
		return numDocumentoIdentificacao;
	}

	/**
	 * Define o valor de numDocumentoIdentificacao.
	 *
	 * @param numDocumentoIdentificacao o novo valor de numDocumentoIdentificacao
	 */
	public void setNumDocumentoIdentificacao(String numDocumentoIdentificacao) {
		this.numDocumentoIdentificacao = numDocumentoIdentificacao;
	}

	/**
	 * Recupera o valor de orgaoExpedidor.
	 *
	 * @return o valor de orgaoExpedidor
	 */
	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	/**
	 * Define o valor de orgaoExpedidor.
	 *
	 * @param orgaoExpedidor o novo valor de orgaoExpedidor
	 */
	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	/**
	 * Recupera o valor de ufOrgaoExpedidor.
	 *
	 * @return o valor de ufOrgaoExpedidor
	 */
	public String getUfOrgaoExpedidor() {
		return ufOrgaoExpedidor;
	}

	/**
	 * Define o valor de ufOrgaoExpedidor.
	 *
	 * @param ufOrgaoExpedidor o novo valor de ufOrgaoExpedidor
	 */
	public void setUfOrgaoExpedidor(String ufOrgaoExpedidor) {
		this.ufOrgaoExpedidor = ufOrgaoExpedidor;
	}

	/**
	 * Recupera o valor de dataEmissaoIdentidade.
	 *
	 * @return o valor de dataEmissaoIdentidade
	 */
	public DateTimeDB getDataEmissaoIdentidade() {
		return dataEmissaoIdentidade;
	}

	/**
	 * Define o valor de dataEmissaoIdentidade.
	 *
	 * @param dataEmissaoIdentidade o novo valor de dataEmissaoIdentidade
	 */
	public void setDataEmissaoIdentidade(DateTimeDB dataEmissaoIdentidade) {
		this.dataEmissaoIdentidade = dataEmissaoIdentidade;
	}

	/**
	 * Recupera o valor de codEstadoCivil.
	 *
	 * @return o valor de codEstadoCivil
	 */
	public Short getCodEstadoCivil() {
		return codEstadoCivil;
	}

	/**
	 * Define o valor de codEstadoCivil.
	 *
	 * @param codEstadoCivil o novo valor de codEstadoCivil
	 */
	public void setCodEstadoCivil(Short codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}

	/**
	 * Recupera o valor de codRegimeCasamento.
	 *
	 * @return o valor de codRegimeCasamento
	 */
	public Short getCodRegimeCasamento() {
		return codRegimeCasamento;
	}

	/**
	 * Define o valor de codRegimeCasamento.
	 *
	 * @param codRegimeCasamento o novo valor de codRegimeCasamento
	 */
	public void setCodRegimeCasamento(Short codRegimeCasamento) {
		this.codRegimeCasamento = codRegimeCasamento;
	}

	/**
	 * Recupera o valor de codSexo.
	 *
	 * @return o valor de codSexo
	 */
	public Short getCodSexo() {
		return codSexo;
	}

	/**
	 * Define o valor de codSexo.
	 *
	 * @param codSexo o novo valor de codSexo
	 */
	public void setCodSexo(Short codSexo) {
		this.codSexo = codSexo;
	}

	/**
	 * Recupera o valor de codEscolaridade.
	 *
	 * @return o valor de codEscolaridade
	 */
	public Short getCodEscolaridade() {
		return codEscolaridade;
	}

	/**
	 * Define o valor de codEscolaridade.
	 *
	 * @param codEscolaridade o novo valor de codEscolaridade
	 */
	public void setCodEscolaridade(Short codEscolaridade) {
		this.codEscolaridade = codEscolaridade;
	}

	/**
	 * Recupera o valor de codVinculoEmpregaticio.
	 *
	 * @return o valor de codVinculoEmpregaticio
	 */
	public Short getCodVinculoEmpregaticio() {
		return codVinculoEmpregaticio;
	}

	/**
	 * Define o valor de codVinculoEmpregaticio.
	 *
	 * @param codVinculoEmpregaticio o novo valor de codVinculoEmpregaticio
	 */
	public void setCodVinculoEmpregaticio(Short codVinculoEmpregaticio) {
		this.codVinculoEmpregaticio = codVinculoEmpregaticio;
	}

	/**
	 * Recupera o valor de uniaoEstavel.
	 *
	 * @return o valor de uniaoEstavel
	 */
	public Boolean getUniaoEstavel() {
		return uniaoEstavel;
	}

	/**
	 * Define o valor de uniaoEstavel.
	 *
	 * @param uniaoEstavel o novo valor de uniaoEstavel
	 */
	public void setUniaoEstavel(Boolean uniaoEstavel) {
		this.uniaoEstavel = uniaoEstavel;
	}

	/**
	 * Recupera o valor de qtdDependentes.
	 *
	 * @return o valor de qtdDependentes
	 */
	public Short getQtdDependentes() {
		return qtdDependentes;
	}

	/**
	 * Define o valor de qtdDependentes.
	 *
	 * @param qtdDependentes o novo valor de qtdDependentes
	 */
	public void setQtdDependentes(Short qtdDependentes) {
		this.qtdDependentes = qtdDependentes;
	}

	/**
	 * Recupera o valor de codInscricaoProdutorRural.
	 *
	 * @return o valor de codInscricaoProdutorRural
	 */
	public String getCodInscricaoProdutorRural() {
		return codInscricaoProdutorRural;
	}

	/**
	 * Define o valor de codInscricaoProdutorRural.
	 *
	 * @param codInscricaoProdutorRural o novo valor de codInscricaoProdutorRural
	 */
	public void setCodInscricaoProdutorRural(String codInscricaoProdutorRural) {
		this.codInscricaoProdutorRural = codInscricaoProdutorRural;
	}

	/**
	 * Recupera o valor de codCategoriaProdutor.
	 *
	 * @return o valor de codCategoriaProdutor
	 */
	public Short getCodCategoriaProdutor() {
		return codCategoriaProdutor;
	}

	/**
	 * Define o valor de codCategoriaProdutor.
	 *
	 * @param codCategoriaProdutor o novo valor de codCategoriaProdutor
	 */
	public void setCodCategoriaProdutor(Short codCategoriaProdutor) {
		this.codCategoriaProdutor = codCategoriaProdutor;
	}

	/**
	 * Recupera o valor de capitalSocialPJ.
	 *
	 * @return o valor de capitalSocialPJ
	 */
	public BigDecimal getCapitalSocialPJ() {
		return capitalSocialPJ;
	}

	/**
	 * Define o valor de capitalSocialPJ.
	 *
	 * @param capitalSocialPJ o novo valor de capitalSocialPJ
	 */
	public void setCapitalSocialPJ(BigDecimal capitalSocialPJ) {
		this.capitalSocialPJ = capitalSocialPJ;
	}

	/**
	 * Recupera o valor de numeroRegistroJuntaComercial.
	 *
	 * @return o valor de numeroRegistroJuntaComercial
	 */
	public String getNumeroRegistroJuntaComercial() {
		return numeroRegistroJuntaComercial;
	}

	/**
	 * Define o valor de numeroRegistroJuntaComercial.
	 *
	 * @param numeroRegistroJuntaComercial o novo valor de numeroRegistroJuntaComercial
	 */
	public void setNumeroRegistroJuntaComercial(String numeroRegistroJuntaComercial) {
		this.numeroRegistroJuntaComercial = numeroRegistroJuntaComercial;
	}

	/**
	 * Recupera o valor de dataRegistroJuntaComercial.
	 *
	 * @return o valor de dataRegistroJuntaComercial
	 */
	public DateTimeDB getDataRegistroJuntaComercial() {
		return dataRegistroJuntaComercial;
	}

	/**
	 * Define o valor de dataRegistroJuntaComercial.
	 *
	 * @param dataRegistroJuntaComercial o novo valor de dataRegistroJuntaComercial
	 */
	public void setDataRegistroJuntaComercial(DateTimeDB dataRegistroJuntaComercial) {
		this.dataRegistroJuntaComercial = dataRegistroJuntaComercial;
	}

	/**
	 * Recupera o valor de numeroRegistroRepresentacao.
	 *
	 * @return o valor de numeroRegistroRepresentacao
	 */
	public String getNumeroRegistroRepresentacao() {
		return numeroRegistroRepresentacao;
	}

	/**
	 * Define o valor de numeroRegistroRepresentacao.
	 *
	 * @param numeroRegistroRepresentacao o novo valor de numeroRegistroRepresentacao
	 */
	public void setNumeroRegistroRepresentacao(String numeroRegistroRepresentacao) {
		this.numeroRegistroRepresentacao = numeroRegistroRepresentacao;
	}

	/**
	 * Recupera o valor de dataRegistroRepresentacao.
	 *
	 * @return o valor de dataRegistroRepresentacao
	 */
	public DateTimeDB getDataRegistroRepresentacao() {
		return dataRegistroRepresentacao;
	}

	/**
	 * Define o valor de dataRegistroRepresentacao.
	 *
	 * @param dataRegistroRepresentacao o novo valor de dataRegistroRepresentacao
	 */
	public void setDataRegistroRepresentacao(DateTimeDB dataRegistroRepresentacao) {
		this.dataRegistroRepresentacao = dataRegistroRepresentacao;
	}

	/**
	 * Recupera o valor de numeroUltimaAlteracaoContratoSocial.
	 *
	 * @return o valor de numeroUltimaAlteracaoContratoSocial
	 */
	public String getNumeroUltimaAlteracaoContratoSocial() {
		return numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * Define o valor de numeroUltimaAlteracaoContratoSocial.
	 *
	 * @param numeroUltimaAlteracaoContratoSocial o novo valor de numeroUltimaAlteracaoContratoSocial
	 */
	public void setNumeroUltimaAlteracaoContratoSocial(String numeroUltimaAlteracaoContratoSocial) {
		this.numeroUltimaAlteracaoContratoSocial = numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * Recupera o valor de dataUltimaAlteracaoContratoSocial.
	 *
	 * @return o valor de dataUltimaAlteracaoContratoSocial
	 */
	public DateTimeDB getDataUltimaAlteracaoContratoSocial() {
		return dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * Define o valor de dataUltimaAlteracaoContratoSocial.
	 *
	 * @param dataUltimaAlteracaoContratoSocial o novo valor de dataUltimaAlteracaoContratoSocial
	 */
	public void setDataUltimaAlteracaoContratoSocial(DateTimeDB dataUltimaAlteracaoContratoSocial) {
		this.dataUltimaAlteracaoContratoSocial = dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * Recupera o valor de inscricaoEstadual.
	 *
	 * @return o valor de inscricaoEstadual
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * Define o valor de inscricaoEstadual.
	 *
	 * @param inscricaoEstadual o novo valor de inscricaoEstadual
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * Recupera o valor de dataConstituicao.
	 *
	 * @return o valor de dataConstituicao
	 */
	public DateTimeDB getDataConstituicao() {
		return dataConstituicao;
	}

	/**
	 * Define o valor de dataConstituicao.
	 *
	 * @param dataConstituicao o novo valor de dataConstituicao
	 */
	public void setDataConstituicao(DateTimeDB dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}

	/**
	 * Recupera o valor de codTipoEmpresa.
	 *
	 * @return o valor de codTipoEmpresa
	 */
	public Short getCodTipoEmpresa() {
		return codTipoEmpresa;
	}

	/**
	 * Define o valor de codTipoEmpresa.
	 *
	 * @param codTipoEmpresa o novo valor de codTipoEmpresa
	 */
	public void setCodTipoEmpresa(Short codTipoEmpresa) {
		this.codTipoEmpresa = codTipoEmpresa;
	}

	/**
	 * Recupera o valor de cobraCPMF.
	 *
	 * @return o valor de cobraCPMF
	 */
	public Boolean getCobraCPMF() {
		return cobraCPMF;
	}

	/**
	 * Define o valor de cobraCPMF.
	 *
	 * @param cobraCPMF o novo valor de cobraCPMF
	 */
	public void setCobraCPMF(Boolean cobraCPMF) {
		this.cobraCPMF = cobraCPMF;
	}

	/**
	 * Recupera o valor de cobraIOF.
	 *
	 * @return o valor de cobraIOF
	 */
	public Boolean getCobraIOF() {
		return cobraIOF;
	}

	/**
	 * Define o valor de cobraIOF.
	 *
	 * @param cobraIOF o novo valor de cobraIOF
	 */
	public void setCobraIOF(Boolean cobraIOF) {
		this.cobraIOF = cobraIOF;
	}

	/**
	 * Recupera o valor de cobraIRRF.
	 *
	 * @return o valor de cobraIRRF
	 */
	public Boolean getCobraIRRF() {
		return cobraIRRF;
	}

	/**
	 * Define o valor de cobraIRRF.
	 *
	 * @param cobraIRRF o novo valor de cobraIRRF
	 */
	public void setCobraIRRF(Boolean cobraIRRF) {
		this.cobraIRRF = cobraIRRF;
	}

	/**
	 * Recupera o valor de codIsencaoCPMF.
	 *
	 * @return o valor de codIsencaoCPMF
	 */
	public Short getCodIsencaoCPMF() {
		return codIsencaoCPMF;
	}

	/**
	 * Define o valor de codIsencaoCPMF.
	 *
	 * @param codIsencaoCPMF o novo valor de codIsencaoCPMF
	 */
	public void setCodIsencaoCPMF(Short codIsencaoCPMF) {
		this.codIsencaoCPMF = codIsencaoCPMF;
	}

	/**
	 * Recupera o valor de numPacOrigem.
	 *
	 * @return o valor de numPacOrigem
	 */
	public Integer getNumPacOrigem() {
		return numPacOrigem;
	}

	/**
	 * Define o valor de numPacOrigem.
	 *
	 * @param numPacOrigem o novo valor de numPacOrigem
	 */
	public void setNumPacOrigem(Integer numPacOrigem) {
		this.numPacOrigem = numPacOrigem;
	}

	/**
	 * Recupera o valor de nivelRisco.
	 *
	 * @return o valor de nivelRisco
	 */
	public String getNivelRisco() {
		return nivelRisco;
	}

	/**
	 * Define o valor de nivelRisco.
	 *
	 * @param nivelRisco o novo valor de nivelRisco
	 */
	public void setNivelRisco(String nivelRisco) {
		this.nivelRisco = nivelRisco;
	}

	/**
	 * Recupera o valor de dataRisco.
	 *
	 * @return o valor de dataRisco
	 */
	public DateTimeDB getDataRisco() {
		return dataRisco;
	}

	/**
	 * Define o valor de dataRisco.
	 *
	 * @param dataRisco o novo valor de dataRisco
	 */
	public void setDataRisco(DateTimeDB dataRisco) {
		this.dataRisco = dataRisco;
	}

	/**
	 * Recupera o valor de motivoRisco.
	 *
	 * @return o valor de motivoRisco
	 */
	public String getMotivoRisco() {
		return motivoRisco;
	}

	/**
	 * Define o valor de motivoRisco.
	 *
	 * @param motivoRisco o novo valor de motivoRisco
	 */
	public void setMotivoRisco(String motivoRisco) {
		this.motivoRisco = motivoRisco;
	}

	/**
	 * Recupera o valor de emiteAvisoLancamento.
	 *
	 * @return o valor de emiteAvisoLancamento
	 */
	public Boolean getEmiteAvisoLancamento() {
		return emiteAvisoLancamento;
	}

	/**
	 * Define o valor de emiteAvisoLancamento.
	 *
	 * @param emiteAvisoLancamento o novo valor de emiteAvisoLancamento
	 */
	public void setEmiteAvisoLancamento(Boolean emiteAvisoLancamento) {
		this.emiteAvisoLancamento = emiteAvisoLancamento;
	}

	/**
	 * Recupera o valor de valorLimiteTecnicoCurtoPrazo.
	 *
	 * @return o valor de valorLimiteTecnicoCurtoPrazo
	 */
	public BigDecimal getValorLimiteTecnicoCurtoPrazo() {
		return valorLimiteTecnicoCurtoPrazo;
	}

	/**
	 * Define o valor de valorLimiteTecnicoCurtoPrazo.
	 *
	 * @param valorLimiteTecnicoCurtoPrazo o novo valor de valorLimiteTecnicoCurtoPrazo
	 */
	public void setValorLimiteTecnicoCurtoPrazo(BigDecimal valorLimiteTecnicoCurtoPrazo) {
		this.valorLimiteTecnicoCurtoPrazo = valorLimiteTecnicoCurtoPrazo;
	}

	/**
	 * Recupera o valor de valorLimiteTecnicoLongoPrazo.
	 *
	 * @return o valor de valorLimiteTecnicoLongoPrazo
	 */
	public BigDecimal getValorLimiteTecnicoLongoPrazo() {
		return valorLimiteTecnicoLongoPrazo;
	}

	/**
	 * Define o valor de valorLimiteTecnicoLongoPrazo.
	 *
	 * @param valorLimiteTecnicoLongoPrazo o novo valor de valorLimiteTecnicoLongoPrazo
	 */
	public void setValorLimiteTecnicoLongoPrazo(BigDecimal valorLimiteTecnicoLongoPrazo) {
		this.valorLimiteTecnicoLongoPrazo = valorLimiteTecnicoLongoPrazo;
	}

	/**
	 * Recupera o valor de valorLimiteTecnicoRotativo.
	 *
	 * @return o valor de valorLimiteTecnicoRotativo
	 */
	public BigDecimal getValorLimiteTecnicoRotativo() {
		return valorLimiteTecnicoRotativo;
	}

	/**
	 * Define o valor de valorLimiteTecnicoRotativo.
	 *
	 * @param valorLimiteTecnicoRotativo o novo valor de valorLimiteTecnicoRotativo
	 */
	public void setValorLimiteTecnicoRotativo(BigDecimal valorLimiteTecnicoRotativo) {
		this.valorLimiteTecnicoRotativo = valorLimiteTecnicoRotativo;
	}

	/**
	 * Recupera o valor de valorComprometimentoMensalRenda.
	 *
	 * @return o valor de valorComprometimentoMensalRenda
	 */
	public BigDecimal getValorComprometimentoMensalRenda() {
		return valorComprometimentoMensalRenda;
	}

	/**
	 * Define o valor de valorComprometimentoMensalRenda.
	 *
	 * @param valorComprometimentoMensalRenda o novo valor de valorComprometimentoMensalRenda
	 */
	public void setValorComprometimentoMensalRenda(BigDecimal valorComprometimentoMensalRenda) {
		this.valorComprometimentoMensalRenda = valorComprometimentoMensalRenda;
	}

	/**
	 * Recupera o valor de codTipoFormaConstituicao.
	 *
	 * @return o valor de codTipoFormaConstituicao
	 */
	public Short getCodTipoFormaConstituicao() {
		return codTipoFormaConstituicao;
	}

	/**
	 * Define o valor de codTipoFormaConstituicao.
	 *
	 * @param codTipoFormaConstituicao o novo valor de codTipoFormaConstituicao
	 */
	public void setCodTipoFormaConstituicao(Short codTipoFormaConstituicao) {
		this.codTipoFormaConstituicao = codTipoFormaConstituicao;
	}

	/**
	 * Recupera o valor de codProfissao.
	 *
	 * @return o valor de codProfissao
	 */
	public Short getCodProfissao() {
		return codProfissao;
	}

	/**
	 * Define o valor de codProfissao.
	 *
	 * @param codProfissao o novo valor de codProfissao
	 */
	public void setCodProfissao(Short codProfissao) {
		this.codProfissao = codProfissao;
	}

	/**
	 * Recupera o valor de reservadoSicocob1.
	 *
	 * @return o valor de reservadoSicocob1
	 */
	public String getReservadoSicocob1() {
    	return reservadoSicocob1;
    }

	/**
	 * Define o valor de reservadoSicocob1.
	 *
	 * @param reservadoSicocob1 o novo valor de reservadoSicocob1
	 */
	public void setReservadoSicocob1(String reservadoSicocob1) {
    	this.reservadoSicocob1 = reservadoSicocob1;
    }

	/**
	 * Recupera o valor de reservadoSicocob2.
	 *
	 * @return o valor de reservadoSicocob2
	 */
	public String getReservadoSicocob2() {
    	return reservadoSicocob2;
    }

	/**
	 * Define o valor de reservadoSicocob2.
	 *
	 * @param reservadoSicocob2 o novo valor de reservadoSicocob2
	 */
	public void setReservadoSicocob2(String reservadoSicocob2) {
    	this.reservadoSicocob2 = reservadoSicocob2;
    }

	/**
	 * Recupera o valor de reservadoSicocob3.
	 *
	 * @return o valor de reservadoSicocob3
	 */
	public String getReservadoSicocob3() {
    	return reservadoSicocob3;
    }

	/**
	 * Define o valor de reservadoSicocob3.
	 *
	 * @param reservadoSicocob3 o novo valor de reservadoSicocob3
	 */
	public void setReservadoSicocob3(String reservadoSicocob3) {
    	this.reservadoSicocob3 = reservadoSicocob3;
    }

	/**
	 * Recupera o valor de reservadoSicocob4.
	 *
	 * @return o valor de reservadoSicocob4
	 */
	public String getReservadoSicocob4() {
    	return reservadoSicocob4;
    }

	/**
	 * Define o valor de reservadoSicocob4.
	 *
	 * @param reservadoSicocob4 o novo valor de reservadoSicocob4
	 */
	public void setReservadoSicocob4(String reservadoSicocob4) {
    	this.reservadoSicocob4 = reservadoSicocob4;
    }

	/**
	 * Recupera o valor de filler.
	 *
	 * @return o valor de filler
	 */
	public String getFiller() {
    	return filler;
    }

	/**
	 * Define o valor de filler.
	 *
	 * @param filler o novo valor de filler
	 */
	public void setFiller(String filler) {
    	this.filler = filler;
    }

	/**
	 * {@inheritDoc}
	 */
	public int getNumeroLinha() {
		return this.numeroLinha;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setNumeroLinha(int numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

}
