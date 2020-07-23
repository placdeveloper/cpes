/*
 * SICOOB
 * 
 * PessoaFisica.java(br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * Entidade que representa a tabela PessoaFisica.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "PessoaFisica")
@PrimaryKeyJoinColumn(name = "NumPessoa")
public class PessoaFisica extends Pessoa {

	/** Serial UID. */
	private static final long serialVersionUID = -6696510881215599106L;

	/** O atributo nome pai. */
	@Column(name = "DescNomePai")
	private String nomePai;
	
	/** O atributo nome mae. */
	@Column(name = "DescNomeMae")
	private String nomeMae;
	
	/** O atributo data nascimento. */
	private DateTimeDB dataNascimento;
	
	/** O atributo naturalidade. */
	@Column(name = "DescNaturalidade")
	private String naturalidade;

	/** O atributo nacionalidade. */
	@Column(name = "DescNacionalidade")
	private String nacionalidade;	
	
	/** O atributo codigo profissao. */
	@Column(name = "CodProfissao")
	private Integer codigoProfissao;
	
	/** O atributo numero rg. */
	@Column(name = "NumRG")
	private String numeroRG;
	
	/** O atributo orgao expedidor. */
	@Column(name = "DescOrgaoExpRG")
	private String orgaoExpedidor;
	
	/** O atributo uf orgao expedidor. */
	@Column(name = "DescUfOrgExpRG")
	private String ufOrgaoExpedidor;
	
	/** O atributo data emissao rg. */
	private DateTimeDB dataEmissaoRG;

	/** O atributo nome conjuge. */
	@Column(name = "DescNomeConjuge")
	private String nomeConjuge;

	/** O atributo codigo profissao conjuge. */
	@Column(name = "CodProfissaoConjuge")
	private Integer codigoProfissaoConjuge;

	/** O atributo quantidade dependentes. */
	@Column(name = "NumDependentes")
	private Short quantidadeDependentes;

	/** O atributo estado civil. */
	@Column(name = "CodEstadoCivil")
	private Short estadoCivil;

	/** O atributo regime casamento. */
	@Column(name = "CodRegimeCasamento")
	private Short regimeCasamento;
	
	/** O atributo tipo sexo. */
	@Column(name = "CodTipoSexo")
	private Short tipoSexo;

	/** O atributo grau instrucao. */
	@Column(name = "CodNivelEducacional")
	private Short grauInstrucao;

	/** O atributo vinculo empregaticio. */
	@Column(name = "CodVinculoEmpregaticio")
	private Short vinculoEmpregaticio;

	/** O atributo cpf conjuge. */
	@Column(name = "NumCPFConjuge")
	private String cpfConjuge;

	/** O atributo mantem vinculo estavel. */
	@Column(name = "BolMantemVinculoEstavel")
	private Boolean mantemVinculoEstavel;
	
	/** O atributo pessoa exposta. */
	@Column(name = "BolPessoaExposta")
	private Boolean pessoaExposta = Boolean.FALSE;

	/** O atributo tipo exposicao. */
	@Column(name = "CodTipoExposicao")
	private Short tipoExposicao;

	/** O atributo falecido. */
	@Column(name = "BolFalecido")
	private Boolean falecido = Boolean.FALSE;

	/** O atributo codigo nacionalidade. */
	@Column(name = "CodNacionalidade")
	private Short codigoNacionalidade;

	/** O atributo emancipado. */
	@Column(name = "BolEmancipado")
	private Boolean emancipado;

	/** O atributo tipo documento. */
	@Column(name = "CodTipoDocumento")
	private Short tipoDocumento;

	/** O atributo profissao alterada. */
	@Transient
	private boolean profissaoAlterada;
	
	/**
	 * @return the profissaoAlterada
	 */
	public boolean isProfissaoAlterada() {
		return profissaoAlterada;
	}

	/**
	 * @param profissaoAlterada the profissaoAlterada to set
	 */
	public void setProfissaoAlterada(boolean profissaoAlterada) {
		this.profissaoAlterada = profissaoAlterada;
	}

	
	/**
	 * @return the nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * @param nomePai the nomePai to set
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
	 * @param nomeMae the nomeMae to set
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * @return the dataNascimento
	 */
	public DateTimeDB getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(DateTimeDB dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the naturalidade
	 */
	public String getNaturalidade() {
		return naturalidade;
	}

	/**
	 * @param naturalidade the naturalidade to set
	 */
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	/**
	 * @return the nacionalidade
	 */
	public String getNacionalidade() {
		return nacionalidade;
	}

	/**
	 * @param nacionalidade the nacionalidade to set
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	/**
	 * @return the codigoProfissao
	 */
	public Integer getCodigoProfissao() {
		return codigoProfissao;
	}

	/**
	 * @param codigoProfissao the codigoProfissao to set
	 */
	public void setCodigoProfissao(Integer codigoProfissao) {
		this.codigoProfissao = codigoProfissao;
	}

	/**
	 * @return the numeroRG
	 */
	public String getNumeroRG() {
		return numeroRG;
	}

	/**
	 * @param numeroRG the numeroRG to set
	 */
	public void setNumeroRG(String numeroRG) {
		this.numeroRG = numeroRG;
	}



	/**
	 * @return the dataEmissaoRG
	 */
	public DateTimeDB getDataEmissaoRG() {
		return dataEmissaoRG;
	}

	/**
	 * @param dataEmissaoRG the dataEmissaoRG to set
	 */
	public void setDataEmissaoRG(DateTimeDB dataEmissaoRG) {
		this.dataEmissaoRG = dataEmissaoRG;
	}

	/**
	 * @return the nomeConjuge
	 */
	public String getNomeConjuge() {
		return nomeConjuge;
	}

	/**
	 * @param nomeConjuge the nomeConjuge to set
	 */
	public void setNomeConjuge(String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}

	/**
	 * @return the codigoProfissaoConjuge
	 */
	public Integer getCodigoProfissaoConjuge() {
		return codigoProfissaoConjuge;
	}

	/**
	 * @param codigoProfissaoConjuge the codigoProfissaoConjuge to set
	 */
	public void setCodigoProfissaoConjuge(Integer codigoProfissaoConjuge) {
		this.codigoProfissaoConjuge = codigoProfissaoConjuge;
	}

	/**
	 * @return the quantidadeDependentes
	 */
	public Short getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	/**
	 * @param quantidadeDependentes the quantidadeDependentes to set
	 */
	public void setQuantidadeDependentes(Short quantidadeDependentes) {
		this.quantidadeDependentes = quantidadeDependentes;
	}

	/**
	 * @return the estadoCivil
	 */
	public Short getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil the estadoCivil to set
	 */
	public void setEstadoCivil(Short estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the regimeCasamento
	 */
	public Short getRegimeCasamento() {
		return regimeCasamento;
	}

	/**
	 * @param regimeCasamento the regimeCasamento to set
	 */
	public void setRegimeCasamento(Short regimeCasamento) {
		this.regimeCasamento = regimeCasamento;
	}

	/**
	 * @return the tipoSexo
	 */
	public Short getTipoSexo() {
		return tipoSexo;
	}

	/**
	 * @param tipoSexo the tipoSexo to set
	 */
	public void setTipoSexo(Short tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	/**
	 * @return the grauInstrucao
	 */
	public Short getGrauInstrucao() {
		return grauInstrucao;
	}

	/**
	 * @param grauInstrucao the grauInstrucao to set
	 */
	public void setGrauInstrucao(Short grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	/**
	 * @return the vinculoEmpregaticio
	 */
	public Short getVinculoEmpregaticio() {
		return vinculoEmpregaticio;
	}

	/**
	 * @param vinculoEmpregaticio the vinculoEmpregaticio to set
	 */
	public void setVinculoEmpregaticio(Short vinculoEmpregaticio) {
		this.vinculoEmpregaticio = vinculoEmpregaticio;
	}

	/**
	 * @return the cpfConjuge
	 */
	public String getCpfConjuge() {
		return cpfConjuge;
	}

	/**
	 * @param cpfConjuge the cpfConjuge to set
	 */
	public void setCpfConjuge(String cpfConjuge) {
		this.cpfConjuge = cpfConjuge;
	}

	/**
	 * @return the mantemVinculoEstavel
	 */
	public Boolean getMantemVinculoEstavel() {
		return mantemVinculoEstavel;
	}

	/**
	 * @param mantemVinculoEstavel the mantemVinculoEstavel to set
	 */
	public void setMantemVinculoEstavel(Boolean mantemVinculoEstavel) {
		this.mantemVinculoEstavel = mantemVinculoEstavel;
	}

	/**
	 * @return the pessoaExposta
	 */
	public Boolean getPessoaExposta() {
		return pessoaExposta;
	}

	/**
	 * @param pessoaExposta the pessoaExposta to set
	 */
	public void setPessoaExposta(Boolean pessoaExposta) {
		this.pessoaExposta = pessoaExposta;
	}

	/**
	 * @return the tipoExposicao
	 */
	public Short getTipoExposicao() {
		return tipoExposicao;
	}

	/**
	 * @param tipoExposicao the tipoExposicao to set
	 */
	public void setTipoExposicao(Short tipoExposicao) {
		this.tipoExposicao = tipoExposicao;
	}

	/**
	 * @return the falecido
	 */
	public Boolean getFalecido() {
		return falecido;
	}

	/**
	 * @param falecido the falecido to set
	 */
	public void setFalecido(Boolean falecido) {
		this.falecido = falecido;
	}

	/**
	 * @return the codigoNacionalidade
	 */
	public Short getCodigoNacionalidade() {
		return codigoNacionalidade;
	}

	/**
	 * @param codigoNacionalidade the codigoNacionalidade to set
	 */
	public void setCodigoNacionalidade(Short codigoNacionalidade) {
		this.codigoNacionalidade = codigoNacionalidade;
	}

	/**
	 * @return the emancipado
	 */
	public Boolean getEmancipado() {
		return emancipado;
	}

	/**
	 * @param emancipado the emancipado to set
	 */
	public void setEmancipado(Boolean emancipado) {
		this.emancipado = emancipado;
	}

	/**
	 * @return the tipoDocumento
	 */
	public Short getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(Short tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the orgaoExpedidor
	 */
	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	/**
	 * @param orgaoExpedidor the orgaoExpedidor to set
	 */
	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	/**
	 * @return the ufOrgaoExpedidor
	 */
	public String getUfOrgaoExpedidor() {
		return ufOrgaoExpedidor;
	}

	/**
	 * @param ufOrgaoExpedidor the ufOrgaoExpedidor to set
	 */
	public void setUfOrgaoExpedidor(String ufOrgaoExpedidor) {
		this.ufOrgaoExpedidor = ufOrgaoExpedidor;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Integer){
			setId((Integer)idDB2);
		}
	}
}
