/*
 * SICOOB
 * 
 * PessoaJuridica.java(br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.util.Constantes.Persistencia;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;
import flexjson.JSON;

/**
 * Classe que representa uma pessoa jurídica no cadastro único de clientes.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "PESSOAJURIDICA", schema = "CLI")
@Filter(name=Persistencia.FILTRO_COMPARTILHAMENTO_CADASTRO, condition="codCompartilhamentoCadastro = :codigo")
@PrimaryKeyJoinColumn(name = "idPessoaCompartilhamento")
@CamposAutorizacao(id="idPessoaCompartilhamento",camposExibicao={
		@CampoAutorizacao(propriedade = "dataInclusaoSistema", label = "DATA DO CADASTRO", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara="dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "nomePessoa", label = "RAZÃO SOCIAL"),
		@CampoAutorizacao(propriedade = "nomeCompleto", label = "RAZÃO SOCIAL COMPLETA"),
		@CampoAutorizacao(propriedade = "nomeApelido", label = "NOME FANTASIA"),
		@CampoAutorizacao(propriedade = "atividadeEconomica.descricao", label = "ATIVIDADE ECONÔMICA"),
		@CampoAutorizacao(propriedade = "cnae.codigo", label = "CÓDIGO CNAE"),
		@CampoAutorizacao(propriedade = "autorizaConsultaBacen", label = "AUTORIZA CONSULTA BACEN", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano"),
		@CampoAutorizacao(propriedade = "dataConstituicao", label = "DATA DA CONSTITUIÇÃO", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara="dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "valorCapitalSocial", label = "CAPITAL SOCIAL", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal"),
		@CampoAutorizacao(propriedade = "inscricaoEstadual", label = "INSCRIÇÃO ESTADUAL"),
		@CampoAutorizacao(propriedade = "codigoEsferaAdministrativa", label = "ESFERA ADMINISTRATIVA", formatador="br.com.sicoob.capes.cadastro.util.formatacao.FormatadorEsferaAdministrativa"),
		@CampoAutorizacao(propriedade = "nacionalidade.descricao", label = "PAÍS DE CONSTITUIÇÃO"),
		@CampoAutorizacao(propriedade = "formaConstituicao.descricao", label = "FORMA DE CONSTITUIÇÃO"),
		@CampoAutorizacao(propriedade = "numeroRegistroJuntaComercial", label = "Nº REGISTRO NO ÓRGÃO COMPETENTE"),
		@CampoAutorizacao(propriedade = "dataRegistroJuntaComercial", label = "DATA DE REGISTRO NO ÓRGÃO COMPETENTE", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara="dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "numeroUltimaAlteracaoContratoSocial", label = "Nº ÚLTIMA ALTERAÇÃO NO CONTRATO SOCIAL"),
		@CampoAutorizacao(propriedade = "dataUltimaAlteracaoContratoSocial", label = "DATA DA ÚLTIMA ALTERAÇÃO NO CONTRATO SOCIAL", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara="dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "numeroRegistroRepresentacao", label = "Nº REGISTRO DE REPRESENTAÇÃO"),
		@CampoAutorizacao(propriedade = "dataRegistroRepresentacao", label = "DATA DE  REGISTRO DE REPRESENTAÇÃO", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara="dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "importador", label = "IMPORTADOR(CÂMBIO)", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano"),
		@CampoAutorizacao(propriedade = "exportador", label = "EXPORTADOR(CÂMBIO", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano"),
		@CampoAutorizacao(propriedade = "inscricaoMunicipal", label="INSCRIÇÃO MUNICIPAL")}
)
public class PessoaJuridica extends PessoaCompartilhamento {

	/** Serial UID. */
	private static final long serialVersionUID = -9061034462192189855L;

	/** O atributo data constituicao. */
	private DateTimeDB dataConstituicao;

	/** O atributo valor capital social. */
	private BigDecimal valorCapitalSocial = BigDecimal.ZERO;

	/** O atributo inscricao estadual. */
	@Column(name = "NUMINSCRICAOESTADUAL")
	private String inscricaoEstadual;

	/** O atributo codigo esfera administrativa. */
	@Column(name = "CODESFERAADMINISTRATIVA")
	private Short codigoEsferaAdministrativa;

	/** O atributo tipo empresa. */
	@JoinColumn(name = "CODTIPOEMPRESA", referencedColumnName = "CODTIPOEMPRESA", insertable = false, updatable = false)
	@ManyToOne
	private TipoEmpresa tipoEmpresa;

	/** O atributo forma constituicao. */
	@JoinColumn(name = "CODTIPOFORMACONSTITUICAO", referencedColumnName = "CODTIPOFORMACONSTITUICAO")
	@ManyToOne
	private TipoFormaConstituicao formaConstituicao;

	/** O atributo numero registro junta comercial. */
	@Column(name = "NUMREGISTROJUNTACOMERCIAL")
	private String numeroRegistroJuntaComercial;

	/** O atributo data registro junta comercial. */
	private DateTimeDB dataRegistroJuntaComercial;

	/** O atributo numero ultima alteracao contrato social. */
	@Column(name = "NUMULTIMAALTERACAOCONTRATOSOCIAL")
	private String numeroUltimaAlteracaoContratoSocial;

	/** O atributo data ultima alteracao contrato social. */
	private DateTimeDB dataUltimaAlteracaoContratoSocial;

	/** O atributo numero registro representacao. */
	@Column(name = "NUMREGISTROREPRESENTACAO")
	private String numeroRegistroRepresentacao;

	/** O atributo data registro representacao. */
	private DateTimeDB dataRegistroRepresentacao;

	/** O atributo contrato social. */
	@Column(name = "DESCCONTRATOSOCIAL")
	private String contratoSocial;
	
	/** O atributo inscricaoMunicipal. */
	@Column(name="NUMINSCRICAOMUNICIPAL")
	private String inscricaoMunicipal;
	
	/** O atributo nacionalidade. */
	@JoinColumn(name = "CODNACIONALIDADE", referencedColumnName = "CODNACIONALIDADE")
	@ManyToOne
	private Nacionalidade nacionalidade;

	/**
	 * @return the dataConstituicao
	 */
	public DateTimeDB getDataConstituicao() {
		return dataConstituicao;
	}

	/**
	 * @param dataConstituicao
	 *            the dataConstituicao to set
	 */
	public void setDataConstituicao(DateTimeDB dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}

	/**
	 * @return the valorCapitalSocial
	 */
	public BigDecimal getValorCapitalSocial() {
		return valorCapitalSocial;
	}

	/**
	 * @param valorCapitalSocial
	 *            the valorCapitalSocial to set
	 */
	public void setValorCapitalSocial(BigDecimal valorCapitalSocial) {
		this.valorCapitalSocial = valorCapitalSocial;
	}

	/**
	 * @return the inscricaoEstadual
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * @param inscricaoEstadual
	 *            the inscricaoEstadual to set
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * @return the codigoEsferaAdministrativa
	 */
	public Short getCodigoEsferaAdministrativa() {
		return codigoEsferaAdministrativa;
	}

	/**
	 * @param codigoEsferaAdministrativa
	 *            the codigoEsferaAdministrativa to set
	 */
	public void setCodigoEsferaAdministrativa(Short codigoEsferaAdministrativa) {
		this.codigoEsferaAdministrativa = codigoEsferaAdministrativa;
	}

	/**
	 * @return the tipoEmpresa
	 */
	@JSON(include = false)
	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}

	/**
	 * @param tipoEmpresa
	 *            the tipoEmpresa to set
	 */
	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * @return the formaConstituicao
	 */
	public TipoFormaConstituicao getFormaConstituicao() {
		return formaConstituicao;
	}

	/**
	 * @param formaConstituicao
	 *            the formaConstituicao to set
	 */
	public void setFormaConstituicao(TipoFormaConstituicao formaConstituicao) {
		this.formaConstituicao = formaConstituicao;
	}

	/**
	 * @return the numeroRegistroJuntaComercial
	 */
	public String getNumeroRegistroJuntaComercial() {
		return numeroRegistroJuntaComercial;
	}

	/**
	 * @param numeroRegistroJuntaComercial
	 *            the numeroRegistroJuntaComercial to set
	 */
	public void setNumeroRegistroJuntaComercial(String numeroRegistroJuntaComercial) {
		this.numeroRegistroJuntaComercial = numeroRegistroJuntaComercial;
	}

	/**
	 * @return the dataRegistroJuntaComercial
	 */
	public DateTimeDB getDataRegistroJuntaComercial() {
		return dataRegistroJuntaComercial;
	}

	/**
	 * @param dataRegistroJuntaComercial
	 *            the dataRegistroJuntaComercial to set
	 */
	public void setDataRegistroJuntaComercial(DateTimeDB dataRegistroJuntaComercial) {
		this.dataRegistroJuntaComercial = dataRegistroJuntaComercial;
	}

	/**
	 * @return the numeroUltimaAlteracaoContratoSocial
	 */
	public String getNumeroUltimaAlteracaoContratoSocial() {
		return numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * @param numeroUltimaAlteracaoContratoSocial
	 *            the numeroUltimaAlteracaoContratoSocial to set
	 */
	public void setNumeroUltimaAlteracaoContratoSocial(String numeroUltimaAlteracaoContratoSocial) {
		this.numeroUltimaAlteracaoContratoSocial = numeroUltimaAlteracaoContratoSocial;
	}

	/**
	 * @return the dataUltimaAlteracaoContratoSocial
	 */
	public DateTimeDB getDataUltimaAlteracaoContratoSocial() {
		return dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * @param dataUltimaAlteracaoContratoSocial
	 *            the dataUltimaAlteracaoContratoSocial to set
	 */
	public void setDataUltimaAlteracaoContratoSocial(DateTimeDB dataUltimaAlteracaoContratoSocial) {
		this.dataUltimaAlteracaoContratoSocial = dataUltimaAlteracaoContratoSocial;
	}

	/**
	 * @return the numeroRegistroRepresentacao
	 */
	public String getNumeroRegistroRepresentacao() {
		return numeroRegistroRepresentacao;
	}

	/**
	 * @param numeroRegistroRepresentacao
	 *            the numeroRegistroRepresentacao to set
	 */
	public void setNumeroRegistroRepresentacao(String numeroRegistroRepresentacao) {
		this.numeroRegistroRepresentacao = numeroRegistroRepresentacao;
	}

	/**
	 * @return the dataRegistroRepresentacao
	 */
	public DateTimeDB getDataRegistroRepresentacao() {
		return dataRegistroRepresentacao;
	}

	/**
	 * @param dataRegistroRepresentacao
	 *            the dataRegistroRepresentacao to set
	 */
	public void setDataRegistroRepresentacao(DateTimeDB dataRegistroRepresentacao) {
		this.dataRegistroRepresentacao = dataRegistroRepresentacao;
	}

	/**
	 * @return the contratoSocial
	 */
	public String getContratoSocial() {
		return contratoSocial;
	}

	/**
	 * @param contratoSocial
	 *            the contratoSocial to set
	 */
	public void setContratoSocial(String contratoSocial) {
		this.contratoSocial = contratoSocial;
	}

	/**
	 * Recupera o valor de inscricaoMunicipal.
	 *
	 * @return o valor de inscricaoMunicipal
	 */
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	/**
	 * Define o valor de inscricaoMunicipal.
	 *
	 * @param inscricaoMunicipal o novo valor de inscricaoMunicipal
	 */
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
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

}
