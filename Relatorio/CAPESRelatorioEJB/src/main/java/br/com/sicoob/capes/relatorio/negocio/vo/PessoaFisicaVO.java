/*
 * SICOOB
 * 
 * PessoaFisicaVO.java(br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO)
 */
package br.com.sicoob.capes.relatorio.negocio.vo;

import java.util.Date;
import java.util.Set;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;

/**
 * @author Lucas.Borges
 */
public class PessoaFisicaVO extends BancoobDto{
	
	/** Serial UID.*/
	private static final long serialVersionUID = 8888078881722588939L;
	
	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	private String idUsuarioAprovacao;
	
	/** O atributo cpf cnpj. */
	private String cpfCnpj;
	
	/** O atributo data nascimento. */
	private Date dataNascimento;

	/** O atributo menor emancipado. */
	private Boolean menorEmancipado;

	/** O atributo nome pai. */
	private String nomePai;

	/** O atributo nome mae. */
	private String nomeMae;
	
	/** O atributo numero documento. */
	private String numeroDocumento;

	/** O atributo orgao expedidor documento. */
	private String orgaoExpedidorDocumento;

	/** O atributo uf orgao expedidor documento. */
	private String ufOrgaoExpedidorDocumento;

	/** O atributo data emissao documento. */
	private Date dataEmissaoDocumento;

	/** O atributo tipo sexo. */
	private Character tipoSexo;
	
	/** O atributo quantidade dependentes. */
	private Short quantidadeDependentes;
	
	/** O atributo desc naturalidade. */
	private String descNaturalidade;	
	
	/** O atributo id naturalidade. */
	private Integer idNaturalidade;
	
	// Tipo Documento
	/** O atributo codigo tipo documento. */
	private Short codigoTipoDocumento;

	/** O atributo descricao tipo documento. */
	private String descricaoTipoDocumento;

	// Ocupacao Profissional
	/** O atributo id ocupacao profissional. */
	private Integer idOcupacaoProfissional;
	
	/** O atributo codigo ocupacao profissional. */
	private String codigoOcupacaoProfissional;
	
	/** O atributo descricao ocupacao profissional. */
	private String descricaoOcupacaoProfissional;
	
	// Estado Civil 
	/** O atributo codigo estado civil. */
	private Short codigoEstadoCivil;

	/** O atributo descricao estado civil. */
	private String descricaoEstadoCivil;
	
	// Regime Casamento
	/** O atributo codigo regime casamento. */
	private Short codigoRegimeCasamento;

	/** O atributo descricao regime casamento. */
	private String descricaoRegimeCasamento;
	
	// Grau Instrucao
	/** O atributo codigo grau instrucao. */
	private Short codigoGrauInstrucao;

	/** O atributo descricao grau instrucao. */
	private String descricaoGrauInstrucao;

	/** O atributo codigo grau instrucao bndes. */
	private Integer codigoGrauInstrucaoBNDES;
	
	// Vinculo Empregaticio
	/** O atributo codigo vinculo empregaticio. */
	private Short codigoVinculoEmpregaticio;

	/** O atributo descricao vinculo empregaticio. */
	private String descricaoVinculoEmpregaticio;
	
	// Nacionalidade
	/** O atributo codigo nacionalidade. */
	private Short codigoNacionalidade;

	/** O atributo descricao nacionalidade. */
	private String descricaoNacionalidade;
	
	/** O atributo uniao estavel. */
	private Boolean uniaoEstavel;
	
	/** O atributo id PessoaConjuge. */
	private Integer idPessoaConjuge;
	
	private PessoaVO pessoa;
	
	private NacionalidadeVO nacionalidade;
	
	private PessoaVO conjuge;
	
	private EstadoCivilVO estadoCivil;
	
	private Long idPessoaCompartilhamento;

	private CompartilhamentoCadastroVO compartilhamento;
	
	private String nomePessoa;
	
	private Set<TransicaoPessoa> transicoes;
	
	private String nomeApelido;
	
	private String nomeCompleto;
	
	private Short codCompartilhamentoCadastro;
	
	private OcupacaoProfissionalVO ocupacaoProfissional;
	
	private String descricao;
	
	private RegimeCasamentoVO regimeCasamento;
	
	private CnaeFiscalVO cnae;
	
	private GrauInstrucaoVO grauInstrucao;
	
	private VinculoEmpregaticio vinculoEmpregaticio;
	
	private TipoDocumentoIdentificacaoVO tipoDocumento;
	
	private AtividadeEconomicaVO atividadeEconomica;
	
	private PerfilCadastroVO perfilCadastro;
	
	private DateTimeDB dataInclusaoSistema;

	private DateTimeDB dataInclusaoSFN;
	
	private Boolean autorizaConsultaBacen = Boolean.TRUE;
	
	private Short codigoSituacaoAprovacao;
	
	private SituacaoRegistroEnum situacaoAprovacao;
	
	private Boolean gravarHistorico = Boolean.TRUE;

	private DateTimeDB dataHoraInicio;

	private Integer idInstituicaoAtualizacao;

	private String idRegistroControlado;
	
	private Boolean verificarAutorizacao = Boolean.TRUE;
	
	private Set<DocumentoComprobatorio> documentosComprobatorios;
	
	private DateTimeDB dataRenovacaoCadastral;
	
	private String idUsuarioRenovacao;
	
	private Short idInstituicaoRenovacao;
	
	private Integer idCooperativaRenovacao;
	
	private Integer codigoTipoAtualizacaoRenovacao;
	
	private String idUsuarioEnvio;
	
	/**
	 * Cria uma nova instância de pessoa fisica vo.
	 */
	public PessoaFisicaVO() {

	}

	
	
	/**
	 * Recupera data nascimento.
	 * 
	 * @return data nascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Preenche data nascimento.
	 * 
	 * @param dataNascimento
	 *            o novo data nascimento
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Recupera menor emancipado.
	 * 
	 * @return menor emancipado
	 */
	public Boolean getMenorEmancipado() {
		return menorEmancipado;
	}

	/**
	 * Preenche menor emancipado.
	 * 
	 * @param menorEmancipado
	 *            o novo menor emancipado
	 */
	public void setMenorEmancipado(Boolean menorEmancipado) {
		this.menorEmancipado = menorEmancipado;
	}

	/**
	 * Recupera nome pai.
	 * 
	 * @return nome pai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * Preenche nome pai.
	 * 
	 * @param nomePai
	 *            o novo nome pai
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * Recupera nome mae.
	 * 
	 * @return nome mae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * Preenche nome mae.
	 * 
	 * @param nomeMae
	 *            o novo nome mae
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * Recupera numero documento.
	 * 
	 * @return numero documento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Preenche numero documento.
	 * 
	 * @param numeroDocumento
	 *            o novo numero documento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Recupera orgao expedidor documento.
	 * 
	 * @return orgao expedidor documento
	 */
	public String getOrgaoExpedidorDocumento() {
		return orgaoExpedidorDocumento;
	}

	/**
	 * Preenche orgao expedidor documento.
	 * 
	 * @param orgaoExpedidorDocumento
	 *            o novo orgao expedidor documento
	 */
	public void setOrgaoExpedidorDocumento(String orgaoExpedidorDocumento) {
		this.orgaoExpedidorDocumento = orgaoExpedidorDocumento;
	}

	/**
	 * Recupera uf orgao expedidor documento.
	 * 
	 * @return uf orgao expedidor documento
	 */
	public String getUfOrgaoExpedidorDocumento() {
		return ufOrgaoExpedidorDocumento;
	}

	/**
	 * Preenche uf orgao expedidor documento.
	 * 
	 * @param ufOrgaoExpedidorDocumento
	 *            o novo uf orgao expedidor documento
	 */
	public void setUfOrgaoExpedidorDocumento(String ufOrgaoExpedidorDocumento) {
		this.ufOrgaoExpedidorDocumento = ufOrgaoExpedidorDocumento;
	}

	/**
	 * Recupera data emissao documento.
	 * 
	 * @return data emissao documento
	 */
	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	/**
	 * Preenche data emissao documento.
	 * 
	 * @param dataEmissaoDocumento
	 *            o novo data emissao documento
	 */
	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	/**
	 * Recupera tipo sexo.
	 * 
	 * @return tipo sexo
	 */
	public Character getTipoSexo() {
		return tipoSexo;
	}

	/**
	 * Preenche tipo sexo.
	 * 
	 * @param tipoSexo
	 *            o novo tipo sexo
	 */
	public void setTipoSexo(Character tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	/**
	 * Recupera quantidade dependentes.
	 * 
	 * @return quantidade dependentes
	 */
	public Short getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	/**
	 * Preenche quantidade dependentes.
	 * 
	 * @param quantidadeDependentes
	 *            o novo quantidade dependentes
	 */
	public void setQuantidadeDependentes(Short quantidadeDependentes) {
		this.quantidadeDependentes = quantidadeDependentes;
	}

	/**
	 * Recupera desc naturalidade.
	 * 
	 * @return desc naturalidade
	 */
	public String getDescNaturalidade() {
		return descNaturalidade;
	}

	/**
	 * Preenche desc naturalidade.
	 * 
	 * @param descNaturalidade
	 *            o novo desc naturalidade
	 */
	public void setDescNaturalidade(String descNaturalidade) {
		this.descNaturalidade = descNaturalidade;
	}

	/**
	 * Recupera id naturalidade.
	 * 
	 * @return id naturalidade
	 */
	public Integer getIdNaturalidade() {
		return idNaturalidade;
	}

	/**
	 * Preenche id naturalidade.
	 * 
	 * @param idNaturalidade
	 *            o novo id naturalidade
	 */
	public void setIdNaturalidade(Integer idNaturalidade) {
		this.idNaturalidade = idNaturalidade;
	}

	/**
	 * Recupera codigo tipo documento.
	 * 
	 * @return codigo tipo documento
	 */
	public Short getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * Preenche codigo tipo documento.
	 * 
	 * @param codigoTipoDocumento
	 *            o novo codigo tipo documento
	 */
	public void setCodigoTipoDocumento(Short codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * Recupera descricao tipo documento.
	 * 
	 * @return descricao tipo documento
	 */
	public String getDescricaoTipoDocumento() {
		return descricaoTipoDocumento;
	}

	/**
	 * Preenche descricao tipo documento.
	 * 
	 * @param descricaoTipoDocumento
	 *            o novo descricao tipo documento
	 */
	public void setDescricaoTipoDocumento(String descricaoTipoDocumento) {
		this.descricaoTipoDocumento = descricaoTipoDocumento;
	}

	/**
	 * Recupera id ocupacao profissional.
	 * 
	 * @return id ocupacao profissional
	 */
	public Integer getIdOcupacaoProfissional() {
		return idOcupacaoProfissional;
	}

	/**
	 * Preenche id ocupacao profissional.
	 * 
	 * @param idOcupacaoProfissional
	 *            o novo id ocupacao profissional
	 */
	public void setIdOcupacaoProfissional(Integer idOcupacaoProfissional) {
		this.idOcupacaoProfissional = idOcupacaoProfissional;
	}

	/**
	 * Recupera descricao ocupacao profissional.
	 * 
	 * @return descricao ocupacao profissional
	 */
	public String getDescricaoOcupacaoProfissional() {
		return descricaoOcupacaoProfissional;
	}

	/**
	 * Preenche descricao ocupacao profissional.
	 * 
	 * @param descricaoOcupacaoProfissional
	 *            o novo descricao ocupacao profissional
	 */
	public void setDescricaoOcupacaoProfissional(
			String descricaoOcupacaoProfissional) {
		this.descricaoOcupacaoProfissional = descricaoOcupacaoProfissional;
	}

	/**
	 * Recupera codigo estado civil.
	 * 
	 * @return codigo estado civil
	 */
	public Short getCodigoEstadoCivil() {
		return codigoEstadoCivil;
	}

	/**
	 * Preenche codigo estado civil.
	 * 
	 * @param codigoEstadoCivil
	 *            o novo codigo estado civil
	 */
	public void setCodigoEstadoCivil(Short codigoEstadoCivil) {
		this.codigoEstadoCivil = codigoEstadoCivil;
	}

	/**
	 * Recupera descricao estado civil.
	 * 
	 * @return descricao estado civil
	 */
	public String getDescricaoEstadoCivil() {
		return descricaoEstadoCivil;
	}

	/**
	 * Preenche descricao estado civil.
	 * 
	 * @param descricaoEstadoCivil
	 *            o novo descricao estado civil
	 */
	public void setDescricaoEstadoCivil(String descricaoEstadoCivil) {
		this.descricaoEstadoCivil = descricaoEstadoCivil;
	}

	/**
	 * Recupera codigo regime casamento.
	 * 
	 * @return codigo regime casamento
	 */
	public Short getCodigoRegimeCasamento() {
		return codigoRegimeCasamento;
	}

	/**
	 * Preenche codigo regime casamento.
	 * 
	 * @param codigoRegimeCasamento
	 *            o novo codigo regime casamento
	 */
	public void setCodigoRegimeCasamento(Short codigoRegimeCasamento) {
		this.codigoRegimeCasamento = codigoRegimeCasamento;
	}

	/**
	 * Recupera descricao regime casamento.
	 * 
	 * @return descricao regime casamento
	 */
	public String getDescricaoRegimeCasamento() {
		return descricaoRegimeCasamento;
	}

	/**
	 * Preenche descricao regime casamento.
	 * 
	 * @param descricaoRegimeCasamento
	 *            o novo descricao regime casamento
	 */
	public void setDescricaoRegimeCasamento(String descricaoRegimeCasamento) {
		this.descricaoRegimeCasamento = descricaoRegimeCasamento;
	}

	/**
	 * Recupera codigo grau instrucao.
	 * 
	 * @return codigo grau instrucao
	 */
	public Short getCodigoGrauInstrucao() {
		return codigoGrauInstrucao;
	}

	/**
	 * Preenche codigo grau instrucao.
	 * 
	 * @param codigoGrauInstrucao
	 *            o novo codigo grau instrucao
	 */
	public void setCodigoGrauInstrucao(Short codigoGrauInstrucao) {
		this.codigoGrauInstrucao = codigoGrauInstrucao;
	}

	/**
	 * Recupera descricao grau instrucao.
	 * 
	 * @return descricao grau instrucao
	 */
	public String getDescricaoGrauInstrucao() {
		return descricaoGrauInstrucao;
	}

	/**
	 * Preenche descricao grau instrucao.
	 * 
	 * @param descricaoGrauInstrucao
	 *            o novo descricao grau instrucao
	 */
	public void setDescricaoGrauInstrucao(String descricaoGrauInstrucao) {
		this.descricaoGrauInstrucao = descricaoGrauInstrucao;
	}

	/**
	 * Recupera codigo grau instrucao bndes.
	 * 
	 * @return codigo grau instrucao bndes
	 */
	public Integer getCodigoGrauInstrucaoBNDES() {
		return codigoGrauInstrucaoBNDES;
	}

	/**
	 * Preenche codigo grau instrucao bndes.
	 * 
	 * @param codigoGrauInstrucaoBNDES
	 *            o novo codigo grau instrucao bndes
	 */
	public void setCodigoGrauInstrucaoBNDES(Integer codigoGrauInstrucaoBNDES) {
		this.codigoGrauInstrucaoBNDES = codigoGrauInstrucaoBNDES;
	}

	/**
	 * Recupera codigo vinculo empregaticio.
	 * 
	 * @return codigo vinculo empregaticio
	 */
	public Short getCodigoVinculoEmpregaticio() {
		return codigoVinculoEmpregaticio;
	}

	/**
	 * Preenche codigo vinculo empregaticio.
	 * 
	 * @param codigoVinculoEmpregaticio
	 *            o novo codigo vinculo empregaticio
	 */
	public void setCodigoVinculoEmpregaticio(Short codigoVinculoEmpregaticio) {
		this.codigoVinculoEmpregaticio = codigoVinculoEmpregaticio;
	}

	/**
	 * Recupera descricao vinculo empregaticio.
	 * 
	 * @return descricao vinculo empregaticio
	 */
	public String getDescricaoVinculoEmpregaticio() {
		return descricaoVinculoEmpregaticio;
	}

	/**
	 * Preenche descricao vinculo empregaticio.
	 * 
	 * @param descricaoVinculoEmpregaticio
	 *            o novo descricao vinculo empregaticio
	 */
	public void setDescricaoVinculoEmpregaticio(String descricaoVinculoEmpregaticio) {
		this.descricaoVinculoEmpregaticio = descricaoVinculoEmpregaticio;
	}

	/**
	 * Recupera codigo nacionalidade.
	 * 
	 * @return codigo nacionalidade
	 */
	public Short getCodigoNacionalidade() {
		return codigoNacionalidade;
	}

	/**
	 * Preenche codigo nacionalidade.
	 * 
	 * @param codigoNacionalidade
	 *            o novo codigo nacionalidade
	 */
	public void setCodigoNacionalidade(Short codigoNacionalidade) {
		this.codigoNacionalidade = codigoNacionalidade;
	}

	/**
	 * Recupera descricao nacionalidade.
	 * 
	 * @return descricao nacionalidade
	 */
	public String getDescricaoNacionalidade() {
		return descricaoNacionalidade;
	}

	/**
	 * Preenche descricao nacionalidade.
	 * 
	 * @param descricaoNacionalidade
	 *            o novo descricao nacionalidade
	 */
	public void setDescricaoNacionalidade(String descricaoNacionalidade) {
		this.descricaoNacionalidade = descricaoNacionalidade;
	}

	/**
	 * Recupera uniao estavel.
	 * 
	 * @return uniao estavel
	 */
	public Boolean getUniaoEstavel() {
		return uniaoEstavel;
	}

	/**
	 * Preenche uniao estavel.
	 * 
	 * @param uniaoEstavel
	 *            o novo uniao estavel
	 */
	public void setUniaoEstavel(Boolean uniaoEstavel) {
		this.uniaoEstavel = uniaoEstavel;
	}

	/**
	 * Recupera o atributo idPessoa
	 * @return o atributo idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Define o valor para o atributo idPessoa
	 * @param idPessoa o idPessoa a ser definido.
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera o atributo cpfCnpj
	 * @return o atributo cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define o valor para o atributo cpfCnpj
	 * @param cpfCnpj o cpfCnpj a ser definido.
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o atributo nomePessoa
	 * @return o atributo nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Define o valor para o atributo nomePessoa
	 * @param nomePessoa o nomePessoa a ser definido.
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * Recupera o atributo nomeCompleto
	 * @return o atributo nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * Define o valor para o atributo nomeCompleto
	 * @param nomeCompleto o nomeCompleto a ser definido.
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * Recupera codigo ocupacao profissional.
	 * 
	 * @return codigo ocupacao profissional
	 */
	public String getCodigoOcupacaoProfissional() {
		return codigoOcupacaoProfissional;
	}

	/**
	 * Preenche codigo ocupacao profissional.
	 * 
	 * @param codigoOcupacaoProfissional
	 *            o novo codigo ocupacao profissional
	 */
	public void setCodigoOcupacaoProfissional(String codigoOcupacaoProfissional) {
		this.codigoOcupacaoProfissional = codigoOcupacaoProfissional;
	}

	public Integer getIdPessoaConjuge() {
		return idPessoaConjuge;
	}

	public void setIdPessoaConjuge(Integer idPessoaConjuge) {
		this.idPessoaConjuge = idPessoaConjuge;
	}

	public PessoaVO getPessoa() {
		if (pessoa == null) {
			pessoa = new PessoaVO();
		}
		return pessoa;
	}

	public void setPessoa(PessoaVO pessoa) {
		this.pessoa = pessoa;
	}

	public NacionalidadeVO getNacionalidade() {
		if (nacionalidade == null) {
			nacionalidade = new NacionalidadeVO();
		}
		return nacionalidade;
	}

	public void setNacionalidade(NacionalidadeVO nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public PessoaVO getConjuge() {
		if (this.conjuge == null) {
			this.conjuge = new PessoaVO();
		}
			
		return conjuge;
	}

	public void setConjuge(PessoaVO conjuge) {
		this.conjuge = conjuge;
	}

	public EstadoCivilVO getEstadoCivil() {
		if (estadoCivil == null) {
			estadoCivil = new EstadoCivilVO();
		}
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilVO estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	public CompartilhamentoCadastroVO getCompartilhamento() {
		if (compartilhamento == null) {
			compartilhamento = new CompartilhamentoCadastroVO(); 
		}
		return compartilhamento;
	}

	public void setCompartilhamento(CompartilhamentoCadastroVO compartilhamento) {
		this.compartilhamento = compartilhamento;
	}

	public Set<TransicaoPessoa> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(Set<TransicaoPessoa> transicoes) {
		this.transicoes = transicoes;
	}

	public String getNomeApelido() {
		return nomeApelido;
	}

	public void setNomeApelido(String nomeApelido) {
		this.nomeApelido = nomeApelido;
	}

	public Short getCodCompartilhamentoCadastro() {
		return codCompartilhamentoCadastro;
	}

	public void setCodCompartilhamentoCadastro(Short codCompartilhamentoCadastro) {
		this.codCompartilhamentoCadastro = codCompartilhamentoCadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CnaeFiscalVO getCnae() {
		if (cnae == null) {
			cnae = new CnaeFiscalVO();
		}
		return cnae;
	}

	public void setCnae(CnaeFiscalVO cnae) {
		this.cnae = cnae;
	}

	public AtividadeEconomicaVO getAtividadeEconomica() {
		if (atividadeEconomica == null) {
			atividadeEconomica  = new AtividadeEconomicaVO();
		}
		return atividadeEconomica;
	}

	public void setAtividadeEconomica(AtividadeEconomicaVO atividadeEconomica) {
		this.atividadeEconomica = atividadeEconomica;
	}

	public PerfilCadastroVO getPerfilCadastro() {
		if (perfilCadastro == null) {
			perfilCadastro = new PerfilCadastroVO();
		}
		return perfilCadastro;
	}

	public void setPerfilCadastro(PerfilCadastroVO perfilCadastro) {
		this.perfilCadastro = perfilCadastro;
	}

	public DateTimeDB getDataInclusaoSistema() {
		return dataInclusaoSistema;
	}

	public void setDataInclusaoSistema(DateTimeDB dataInclusaoSistema) {
		this.dataInclusaoSistema = dataInclusaoSistema;
	}

	public DateTimeDB getDataInclusaoSFN() {
		return dataInclusaoSFN;
	}

	public void setDataInclusaoSFN(DateTimeDB dataInclusaoSFN) {
		this.dataInclusaoSFN = dataInclusaoSFN;
	}

	public Boolean getAutorizaConsultaBacen() {
		return autorizaConsultaBacen;
	}

	public void setAutorizaConsultaBacen(Boolean autorizaConsultaBacen) {
		this.autorizaConsultaBacen = autorizaConsultaBacen;
	}

	public Short getCodigoSituacaoAprovacao() {
		return codigoSituacaoAprovacao;
	}

	public void setCodigoSituacaoAprovacao(Short codigoSituacaoAprovacao) {
		this.codigoSituacaoAprovacao = codigoSituacaoAprovacao;
	}

	public SituacaoRegistroEnum getSituacaoAprovacao() {
		return situacaoAprovacao;
	}

	public void setSituacaoAprovacao(SituacaoRegistroEnum situacaoAprovacao) {
		this.situacaoAprovacao = situacaoAprovacao;
	}

	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}

	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Integer getIdInstituicaoAtualizacao() {
		return idInstituicaoAtualizacao;
	}

	public void setIdInstituicaoAtualizacao(Integer idInstituicaoAtualizacao) {
		this.idInstituicaoAtualizacao = idInstituicaoAtualizacao;
	}

	public String getIdRegistroControlado() {
		return idRegistroControlado;
	}

	public void setIdRegistroControlado(String idRegistroControlado) {
		this.idRegistroControlado = idRegistroControlado;
	}

	public Boolean getVerificarAutorizacao() {
		return verificarAutorizacao;
	}

	public void setVerificarAutorizacao(Boolean verificarAutorizacao) {
		this.verificarAutorizacao = verificarAutorizacao;
	}

	public Set<DocumentoComprobatorio> getDocumentosComprobatorios() {
		return documentosComprobatorios;
	}

	public void setDocumentosComprobatorios(
			Set<DocumentoComprobatorio> documentosComprobatorios) {
		this.documentosComprobatorios = documentosComprobatorios;
	}

	public DateTimeDB getDataRenovacaoCadastral() {
		return dataRenovacaoCadastral;
	}

	public void setDataRenovacaoCadastral(DateTimeDB dataRenovacaoCadastral) {
		this.dataRenovacaoCadastral = dataRenovacaoCadastral;
	}

	public String getIdUsuarioRenovacao() {
		return idUsuarioRenovacao;
	}

	public void setIdUsuarioRenovacao(String idUsuarioRenovacao) {
		this.idUsuarioRenovacao = idUsuarioRenovacao;
	}

	public Short getIdInstituicaoRenovacao() {
		return idInstituicaoRenovacao;
	}

	public void setIdInstituicaoRenovacao(Short idInstituicaoRenovacao) {
		this.idInstituicaoRenovacao = idInstituicaoRenovacao;
	}

	public Integer getIdCooperativaRenovacao() {
		return idCooperativaRenovacao;
	}

	public void setIdCooperativaRenovacao(Integer idCooperativaRenovacao) {
		this.idCooperativaRenovacao = idCooperativaRenovacao;
	}

	public Integer getCodigoTipoAtualizacaoRenovacao() {
		return codigoTipoAtualizacaoRenovacao;
	}

	public void setCodigoTipoAtualizacaoRenovacao(
			Integer codigoTipoAtualizacaoRenovacao) {
		this.codigoTipoAtualizacaoRenovacao = codigoTipoAtualizacaoRenovacao;
	}

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}

	public void setOcupacaoProfissional(OcupacaoProfissionalVO ocupacaoProfissional) {
		this.ocupacaoProfissional = ocupacaoProfissional;
	}



	public OcupacaoProfissionalVO getOcupacaoProfissional() {
		if (ocupacaoProfissional == null) {
			ocupacaoProfissional = new OcupacaoProfissionalVO();
		}
		return ocupacaoProfissional;
	}



	public RegimeCasamentoVO getRegimeCasamento() {
		if (regimeCasamento ==  null) {
			regimeCasamento = new RegimeCasamentoVO();
		}
		return regimeCasamento;
	}



	public void setRegimeCasamento(RegimeCasamentoVO regimeCasamento) {
		this.regimeCasamento = regimeCasamento;
	}



	public GrauInstrucaoVO getGrauInstrucao() {
		if (grauInstrucao == null) {
			grauInstrucao = new GrauInstrucaoVO();
		}
		return grauInstrucao;
	}



	public void setGrauInstrucao(GrauInstrucaoVO grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}



	public VinculoEmpregaticio getVinculoEmpregaticio() {
		if (vinculoEmpregaticio == null) {
			vinculoEmpregaticio = new VinculoEmpregaticio();
		}
		return vinculoEmpregaticio;
	}



	public void setVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) {
		this.vinculoEmpregaticio = vinculoEmpregaticio;
	}



	public TipoDocumentoIdentificacaoVO getTipoDocumento() {
		if (tipoDocumento == null) {
			tipoDocumento = new TipoDocumentoIdentificacaoVO();
		}
		return tipoDocumento;
	}



	public void setTipoDocumento(TipoDocumentoIdentificacaoVO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}



	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}



	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}

}