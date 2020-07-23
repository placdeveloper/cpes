/*
 * SICOOB
 * 
 * PessoaFisica.java(br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.util.Constantes.Persistencia;
import br.com.sicoob.capes.negocio.entidades.Cidadania;
import br.com.sicoob.capes.negocio.entidades.EnderecoFiscal;
import br.com.sicoob.capes.negocio.entidades.EstadoCivil;
import br.com.sicoob.capes.negocio.entidades.GrauInstrucao;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional;
import br.com.sicoob.capes.negocio.entidades.RegimeCasamento;
import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;

/**
 * Classe que representa uma pessoa física no cadastro único de clientes.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "PESSOAFISICA", schema = "CLI")
@Filter(name=Persistencia.FILTRO_COMPARTILHAMENTO_CADASTRO, condition="codCompartilhamentoCadastro = :codigo")
@PrimaryKeyJoinColumn(name = "idPessoaCompartilhamento")
@CamposAutorizacao(id="idPessoaCompartilhamento",camposExibicao={
		@CampoAutorizacao(propriedade = "dataInclusaoSistema", label = "DATA DO CADASTRO", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara="dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "nomePessoa", label = "NOME"),
		@CampoAutorizacao(propriedade = "nomeCompleto", label = "NOME COMPLETO"),
		@CampoAutorizacao(propriedade = "nomeApelido", label = "NOME TRATAMENTO"),
		@CampoAutorizacao(propriedade = "atividadeEconomica.descricao", label = "ATIVIDADE ECONÔMICA"),
		@CampoAutorizacao(propriedade = "cnae.codigo", label = "CÓDIGO CNAE"),
		@CampoAutorizacao(propriedade = "autorizaConsultaBacen", label = "AUTORIZA CONSULTA BACEN", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano"),
		@CampoAutorizacao(propriedade = "tipoDocumento.descricao", label = "TIPO DE DOCUMENTO"),
		@CampoAutorizacao(propriedade = "numeroDocumento", label = "Nº DO DOCUMENTO"),
		@CampoAutorizacao(propriedade = "orgaoExpedidorDocumento", label = "ÓRGÃO EXPEDIDOR"),
		@CampoAutorizacao(propriedade = "ufOrgaoExpedidorDocumento", label = "UF DO ÓRGÃO EXPEDIDOR"),
		@CampoAutorizacao(propriedade = "dataEmissaoDocumento", label = "DATA DE EMISSÃO", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara="dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "dataNascimento", label = "DATA DE NASCIMENTO", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara="dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "tipoSexo", label = "SEXO"),
		@CampoAutorizacao(propriedade = "nacionalidade.descricao", label = "NACIONALIDADE"),
		@CampoAutorizacao(propriedade = "idNaturalidade", label = "NATURALIDADE BRASILEIRA", formatador="br.com.sicoob.capes.cadastro.util.formatacao.FormatadorLocalidade"),
		@CampoAutorizacao(propriedade = "descNaturalidade", label = "NATURALIDADE ESTRANGEIRA"),
		@CampoAutorizacao(propriedade = "grauInstrucao.descricao", label = "ESCOLARIDADE"),
		@CampoAutorizacao(propriedade = "ocupacaoProfissional.descricao", label = "PROFISSÃO"),
		@CampoAutorizacao(propriedade = "vinculoEmpregaticio.descricao", label = "NATUREZA DA OCUPAÇÃO"),
		@CampoAutorizacao(propriedade = "nomePai", label = "NOME DO PAI"),
		@CampoAutorizacao(propriedade = "nomeMae", label = "NOME DA MÃE"),
		@CampoAutorizacao(propriedade = "estadoCivil.descricao", label = "ESTADO CIVIL"),
		@CampoAutorizacao(propriedade = "regimeCasamento.descricao", label = "REGIME DE CASAMENTO"),
		@CampoAutorizacao(propriedade = "conjuge.nomePessoa", label = "CÔNJUGE"),
		@CampoAutorizacao(propriedade = "listaCidadania", label = "CIDADANIA", formatador="br.com.sicoob.capes.cadastro.util.formatacao.FormatadorCidadania"),
		@CampoAutorizacao(propriedade = "importador", label = "IMPORTADOR(CÂMBIO)", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano"),
		@CampoAutorizacao(propriedade = "exportador", label = "EXPORTADOR(CÂMBIO)", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano"),
		@CampoAutorizacao(propriedade = "listaEnderecoFiscal", label = "ENDEREÇO FISCAL", formatador="br.com.sicoob.capes.cadastro.util.formatacao.FormatadorEnderecoFiscal")}
		//@CampoAutorizacao(propriedade = "uniaoEstavel", label = "UNIÃO ESTÁVEL", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano"),
)
public class PessoaFisica extends PessoaCompartilhamento {

	/** Serial UID. */
	private static final long serialVersionUID = -6049996793783326331L;

	/** O atributo data nascimento. */
	private DateTimeDB dataNascimento;

	/** O atributo menor emancipado. */
	@Column(name = "BOLMENOREMANCIPADO")
	private Boolean menorEmancipado = Boolean.FALSE;

	/** O atributo nome pai. */
	@Column(name = "NOMEPAI", length = 50)
	private String nomePai;

	/** O atributo nome mae. */
	@Column(name = "NOMEMAE", length = 50)
	private String nomeMae;

	/** O atributo tipo documento. */
	@JoinColumn(name = "CODTIPODOCUMENTOIDENTIFICACAO", referencedColumnName = "CODTIPODOCUMENTOIDENTIFICACAO")
	@ManyToOne
	private TipoDocumentoIdentificacao tipoDocumento;

	/** O atributo numero documento. */
	@Column(name = "NUMDOCUMENTOIDENTIFICACAO", length = 35)
	private String numeroDocumento;

	/** O atributo orgao expedidor documento. */
	@Column(name = "DESCORGAOEXPDOCUMENTOIDENTIFICACAO")
	private String orgaoExpedidorDocumento;

	/** O atributo uf orgao expedidor documento. */
	@Column(name = "SIGLAUFORGEXPDOCUMENTOIDENTIFICACAO")
	private String ufOrgaoExpedidorDocumento;

	/** O atributo data emissao documento. */
	@Column(name = "DATAEMISSAODOCUMENTOIDENTIFICACAO")
	private DateTimeDB dataEmissaoDocumento;

	/** O atributo tipo sexo. */
	@Column(name = "CODTIPOSEXO")
	private Character tipoSexo;

	/** O atributo ocupacao profissional. */
	@JoinColumn(name = "IDOCUPACAOPROFISSIONAL", referencedColumnName = "idOcupacaoProfissional")
	@ManyToOne
	private OcupacaoProfissional ocupacaoProfissional;

	/** O atributo estado civil. */
	@JoinColumn(name = "CODESTADOCIVIL", referencedColumnName = "CODESTADOCIVIL")
	@ManyToOne
	private EstadoCivil estadoCivil;

	/** O atributo regime casamento. */
	@JoinColumn(name = "CODREGIMECASAMENTO", referencedColumnName = "CODREGIMECASAMENTO")
	@ManyToOne
	private RegimeCasamento regimeCasamento;

	/** O atributo quantidade dependentes. */
	@Column(name = "QTDDEPENDENTE")
	private Short quantidadeDependentes;

	/** O atributo uniao estavel. */
	@Column(name = "BOLUNIAOESTAVEL")
	private Boolean uniaoEstavel = Boolean.FALSE;

	/** O atributo grau instrucao. */
	@JoinColumn(name = "CODGRAUINSTRUCAO", referencedColumnName = "CODGRAUINSTRUCAO")
	@ManyToOne
	private GrauInstrucao grauInstrucao;

	/** O atributo desc naturalidade. */
	@Column(name="descNaturalidade", length = 40)
	private String descNaturalidade;	
	
	/** O atributo id naturalidade. */
	private Integer idNaturalidade;
	
	/** O atributo vinculo empregaticio. */
	@JoinColumn(name = "CODVINCULOEMPREGATICIO", referencedColumnName = "CODVINCULOEMPREGATICIO")
	@ManyToOne
	private VinculoEmpregaticio vinculoEmpregaticio;
	
	/** O atributo nacionalidade. */
	@JoinColumn(name = "CODNACIONALIDADE", referencedColumnName = "CODNACIONALIDADE")
	@ManyToOne
	private Nacionalidade nacionalidade;
	
	/** O atributo pessoa compartilhamento. */
	@ManyToOne
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTOCONJUGE")
	private PessoaCompartilhamento conjuge;
	
	/** O atributo listaCidadania. */
	@OneToMany(mappedBy = "pessoaFisica", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Cidadania> listaCidadania;
	
	/** O atributo listaEnderecoFiscal. */
	@OneToMany(mappedBy = "pessoaFisica", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<EnderecoFiscal> listaEnderecoFiscal;

	/**
	 * @return the dataNascimento
	 */
	public DateTimeDB getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento
	 *            the dataNascimento to set
	 */
	public void setDataNascimento(DateTimeDB dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the menorEmancipado
	 */
	public Boolean getMenorEmancipado() {
		return menorEmancipado;
	}

	/**
	 * @param menorEmancipado
	 *            the menorEmancipado to set
	 */
	public void setMenorEmancipado(Boolean menorEmancipado) {
		this.menorEmancipado = menorEmancipado;
	}

	/**
	 * @return the nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * @param nomePai
	 *            the nomePai to set
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * @return the nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * @param nomeMae
	 *            the nomeMae to set
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumentoIdentificacao getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumentoIdentificacao tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento
	 *            the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the orgaoExpedidorDocumento
	 */
	public String getOrgaoExpedidorDocumento() {
		return orgaoExpedidorDocumento;
	}

	/**
	 * @param orgaoExpedidorDocumento
	 *            the orgaoExpedidorDocumento to set
	 */
	public void setOrgaoExpedidorDocumento(String orgaoExpedidorDocumento) {
		this.orgaoExpedidorDocumento = orgaoExpedidorDocumento;
	}

	/**
	 * @return the ufOrgaoExpedidorDocumento
	 */
	public String getUfOrgaoExpedidorDocumento() {
		return ufOrgaoExpedidorDocumento;
	}

	/**
	 * @param ufOrgaoExpedidorDocumento
	 *            the ufOrgaoExpedidorDocumento to set
	 */
	public void setUfOrgaoExpedidorDocumento(String ufOrgaoExpedidorDocumento) {
		this.ufOrgaoExpedidorDocumento = ufOrgaoExpedidorDocumento;
	}

	/**
	 * @return the dataEmissaoDocumento
	 */
	public DateTimeDB getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	/**
	 * @param dataEmissaoDocumento
	 *            the dataEmissaoDocumento to set
	 */
	public void setDataEmissaoDocumento(DateTimeDB dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	/**
	 * @return the tipoSexo
	 */
	public Character getTipoSexo() {
		return tipoSexo;
	}

	/**
	 * @param tipoSexo
	 *            the tipoSexo to set
	 */
	public void setTipoSexo(Character tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	/**
	 * @return the ocupacaoProfissional
	 */
	public OcupacaoProfissional getOcupacaoProfissional() {
		return ocupacaoProfissional;
	}

	/**
	 * @param ocupacaoProfissional
	 *            the ocupacaoProfissional to set
	 */
	public void setOcupacaoProfissional(OcupacaoProfissional ocupacaoProfissional) {
		this.ocupacaoProfissional = ocupacaoProfissional;
	}

	/**
	 * @return the estadoCivil
	 */
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil
	 *            the estadoCivil to set
	 */
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the regimeCasamento
	 */
	public RegimeCasamento getRegimeCasamento() {
		return regimeCasamento;
	}

	/**
	 * @param regimeCasamento
	 *            the regimeCasamento to set
	 */
	public void setRegimeCasamento(RegimeCasamento regimeCasamento) {
		this.regimeCasamento = regimeCasamento;
	}

	/**
	 * @return the quantidadeDependentes
	 */
	public Short getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	/**
	 * @param quantidadeDependentes
	 *            the quantidadeDependentes to set
	 */
	public void setQuantidadeDependentes(Short quantidadeDependentes) {
		this.quantidadeDependentes = quantidadeDependentes;
	}

	/**
	 * @return the uniaoEstavel
	 */
	public Boolean getUniaoEstavel() {
		return uniaoEstavel;
	}

	/**
	 * @param uniaoEstavel
	 *            the uniaoEstavel to set
	 */
	public void setUniaoEstavel(Boolean uniaoEstavel) {
		this.uniaoEstavel = uniaoEstavel;
	}

	/**
	 * @return the grauInstrucao
	 */
	public GrauInstrucao getGrauInstrucao() {
		return grauInstrucao;
	}

	/**
	 * @param grauInstrucao
	 *            the grauInstrucao to set
	 */
	public void setGrauInstrucao(GrauInstrucao grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	/**
	 * @return the idNaturalidade
	 */
	public Integer getIdNaturalidade() {
		return idNaturalidade;
	}

	/**
	 * @param idNaturalidade
	 *            the idNaturalidade to set
	 */
	public void setIdNaturalidade(Integer idNaturalidade) {
		this.idNaturalidade = idNaturalidade;
	}

	/**
	 * @return the descNaturalidade
	 */
	public String getDescNaturalidade() {
		return descNaturalidade;
	}

	/**
	 * @param descNaturalidade
	 *            the descNaturalidade to set
	 */
	public void setDescNaturalidade(String descNaturalidade) {
		this.descNaturalidade = descNaturalidade;
	}

	/**
	 * @return the vinculoEmpregaticio
	 */
	public VinculoEmpregaticio getVinculoEmpregaticio() {
		return vinculoEmpregaticio;
	}

	/**
	 * @param vinculoEmpregaticio the vinculoEmpregaticio to set
	 */
	public void setVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) {
		this.vinculoEmpregaticio = vinculoEmpregaticio;
	}

	/**
	 * @return the nacionalidade
	 */
	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	/**
	 * @param nacionalidade the nacionalidade to set
	 */
	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	/**
	 * Recupera o valor de listaCidadania.
	 *
	 * @return o valor de listaCidadania
	 */
	public Set<Cidadania> getListaCidadania() {
		return listaCidadania;
	}

	/**
	 * Define o valor de listaCidadania.
	 *
	 * @param listaCidadania o novo valor de listaCidadania
	 */
	public void setListaCidadania(Set<Cidadania> listaCidadania) {
		this.listaCidadania = listaCidadania;
	}

	/**
	 * Recupera o valor de listaEnderecoFiscal.
	 *
	 * @return o valor de listaEnderecoFiscal
	 */
	public Set<EnderecoFiscal> getListaEnderecoFiscal() {
		return listaEnderecoFiscal;
	}

	/**
	 * Define o valor de listaEnderecoFiscal.
	 *
	 * @param listaEnderecoFiscal o novo valor de listaEnderecoFiscal
	 */
	public void setListaEnderecoFiscal(Set<EnderecoFiscal> listaEnderecoFiscal) {
		this.listaEnderecoFiscal = listaEnderecoFiscal;
	}

	public PessoaCompartilhamento getConjuge() {
		return conjuge;
	}

	public void setConjuge(PessoaCompartilhamento conjuge) {
		this.conjuge = conjuge;
	}

}