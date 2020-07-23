/*
 * SICOOB
 * 
 * Pessoa.java(br.com.sicoob.capes.negocio.entidades.legado.Pessoa)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * Entidade que representa a tabela Pessoa.
 * 
 * @author Erico.Junior
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Pessoa")
public abstract class Pessoa extends EntidadeReplicavel<Integer> {

	/** Serial UID. */
	private static final long serialVersionUID = -4079681453276842317L;

	/** O atributo id. */
	@Id
	@Column(name = "NumPessoa")
	@GeneratedValue(generator = "geradorID")
	@GenericGenerator(name = "geradorID", 
			strategy = "br.com.sicoob.capes.negocio.entidades.legado.id.PessoaIDGenerator")
	private Integer id;

	/** O atributo tipo pessoa. */
	@Column(name = "CodPF_PJ")
	private Short tipoPessoa;

	/** O atributo data cadastramento pessoa. */
	private DateTimeDB dataCadastramentoPessoa;
	
	/** O atributo numero cgc cpf. */
	@Column(name = "NumCGC_CPF")
	private String numeroCgcCpf;
	
	/** O atributo nome. */
	@Column(name = "DescNomePessoa")
	private String nome; 

	/** O atributo apelido. */
	@Column(name = "DescApelidoPessoa")
	private String apelido;

	/** O atributo ddd. */
	@Column(name = "NumDDD")
	private String ddd;
	
	/** O atributo num ramal. */
	@Column(name = "NumRamal")
	private String numRamal;
	
	/** O atributo num cel fax. */
	@Column(name = "NumCelFax")
	private String numCelFax;

	/** O atributo email. */
	@Column(name = "DescEndInternet")
	private String email;
	
	/** O atributo observacao. */
	@Column(name = "DescObsPessoa")
	private String observacao;
	
	/** O atributo id atividade economica. */
	@Column(name = "idAtividadeEconomica", nullable = false)
	private Short idAtividadeEconomica;
	
	/** O atributo data ultima atualizacao. */
	private Date dataUltimaAtualizacao;
	
	/** O atributo codigo cnae. */
	@Column(name = "CodCNAE")
	private String codigoCnae;
	
	/** O atributo nome completo. */
	@Column(name = "DescNomeCompleto")
	private String nomeCompleto;
	
	/** O atributo num coop origem pessoa. */
	private Integer numCoopOrigemPessoa;
	
	/** O atributo num pac origem pessoa. */
	private Short numPacOrigemPessoa;
	
	/** O atributo cooperativas. */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="NumPessoa", referencedColumnName="NumPessoa")
	private Set<CooperativaPessoa> cooperativas;
	
	/** O atributo data sfn. */
	@Transient
	private DateTimeDB dataSFN;

	/** O atributo nome alterado. */
	@Transient
	private boolean nomeAlterado;
	
	/** O atributo avaliacao financeira. */
	@Transient
	private AvaliacaoFinanceira avaliacaoFinanceira;
	
	/** Atributo utilizado para replicacao da informacao para o CLIENTE **/
	@Transient
	private boolean autorizaConsulta;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the numeroCgcCpf
	 */
	public String getNumeroCgcCpf() {
		return numeroCgcCpf;
	}

	/**
	 * @param numeroCgcCpf
	 *            the numeroCgcCpf to set
	 */
	public void setNumeroCgcCpf(String numeroCgcCpf) {
		this.numeroCgcCpf = numeroCgcCpf;
	}	
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the tipoPessoa
	 */
	public Short getTipoPessoa() {
		return tipoPessoa;
	}

	/**
	 * @param tipoPessoa the tipoPessoa to set
	 */
	public void setTipoPessoa(Short tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	/**
	 * @return the dataCadastramentoPessoa
	 */
	public DateTimeDB getDataCadastramentoPessoa() {
		return dataCadastramentoPessoa;
	}

	/**
	 * @param dataCadastramentoPessoa the dataCadastramentoPessoa to set
	 */
	public void setDataCadastramentoPessoa(DateTimeDB dataCadastramentoPessoa) {
		this.dataCadastramentoPessoa = dataCadastramentoPessoa;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the apelido
	 */
	public String getApelido() {
		return apelido;
	}

	/**
	 * @param apelido the apelido to set
	 */
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	/**
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return the idAtividadeEconomica
	 */
	public Short getIdAtividadeEconomica() {
		return idAtividadeEconomica;
	}

	/**
	 * @param idAtividadeEconomica the idAtividadeEconomica to set
	 */
	public void setIdAtividadeEconomica(Short idAtividadeEconomica) {
		this.idAtividadeEconomica = idAtividadeEconomica;
	}

	/**
	 * @return the dataUltimaAtualizacao
	 */
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	/**
	 * @param dataUltimaAtualizacao the dataUltimaAtualizacao to set
	 */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	/**
	 * @return the codigoCnae
	 */
	public String getCodigoCnae() {
		return codigoCnae;
	}

	/**
	 * @param codigoCnae the codigoCnae to set
	 */
	public void setCodigoCnae(String codigoCnae) {
		this.codigoCnae = codigoCnae;
	}

	/**
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getId();
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof Integer){
			setId((Integer)idSQL);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return null;
	}

	/**
	 * @return the dataSFN
	 */
	public DateTimeDB getDataSFN() {
		return dataSFN;
	}

	/**
	 * @param dataSFN the dataSFN to set
	 */
	public void setDataSFN(DateTimeDB dataSFN) {
		this.dataSFN = dataSFN;
	}

	/**
	 * @return the numCoopOrigemPessoa
	 */
	public Integer getNumCoopOrigemPessoa() {
		return numCoopOrigemPessoa;
	}

	/**
	 * @param numCoopOrigemPessoa the numCoopOrigemPessoa to set
	 */
	public void setNumCoopOrigemPessoa(Integer numCoopOrigemPessoa) {
		this.numCoopOrigemPessoa = numCoopOrigemPessoa;
	}

	/**
	 * @return the numPacOrigemPessoa
	 */
	public Short getNumPacOrigemPessoa() {
		return numPacOrigemPessoa;
	}

	/**
	 * @param numPacOrigemPessoa the numPacOrigemPessoa to set
	 */
	public void setNumPacOrigemPessoa(Short numPacOrigemPessoa) {
		this.numPacOrigemPessoa = numPacOrigemPessoa;
	}

	/**
	 * @return the cooperativas
	 */
	public Set<CooperativaPessoa> getCooperativas() {
		return cooperativas;
	}

	/**
	 * @param cooperativas the cooperativas to set
	 */
	public void setCooperativas(Set<CooperativaPessoa> cooperativas) {
		this.cooperativas = cooperativas;
	}

	/**
	 * @return the numRamal
	 */
	public String getNumRamal() {
		return numRamal;
	}

	/**
	 * @param numRamal the numRamal to set
	 */
	public void setNumRamal(String numRamal) {
		this.numRamal = numRamal;
	}

	/**
	 * @return the numCelFax
	 */
	public String getNumCelFax() {
		return numCelFax;
	}

	/**
	 * @param numCelFax the numCelFax to set
	 */
	public void setNumCelFax(String numCelFax) {
		this.numCelFax = numCelFax;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the nomeAlterado
	 */
	public boolean isNomeAlterado() {
		return nomeAlterado;
	}

	/**
	 * @param nomeAlterado the nomeAlterado to set
	 */
	public void setNomeAlterado(boolean nomeAlterado) {
		this.nomeAlterado = nomeAlterado;
	}

	/**
	 * Recupera avaliacao financeira.
	 * 
	 * @return avaliacao financeira
	 */
	public AvaliacaoFinanceira getAvaliacaoFinanceira() {
		return avaliacaoFinanceira;
	}

	/**
	 * Preenche avaliacao financeira.
	 * 
	 * @param avaliacaoFinanceira
	 *            o novo avaliacao financeira
	 */
	public void setAvaliacaoFinanceira(AvaliacaoFinanceira avaliacaoFinanceira) {
		this.avaliacaoFinanceira = avaliacaoFinanceira;
	}

	/**
	 * Verifica se eh autoriza consulta.
	 *
	 * @return {@code true}, se for autoriza consulta
	 */
	public boolean isAutorizaConsulta() {
		return autorizaConsulta;
	}

	/**
	 * Define o valor de autorizaConsulta.
	 *
	 * @param autorizaConsulta o novo valor de autorizaConsulta
	 */
	public void setAutorizaConsulta(boolean autorizaConsulta) {
		this.autorizaConsulta = autorizaConsulta;
	}

}
