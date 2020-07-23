/*
 * SICOOB
 * 
 * Bem.java(br.com.sicoob.capes.negocio.entidades.legado.Bem)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Entidade que representa a tabela BemPessoa.
 * 
 * @author erico.junior
 */
@Entity
@Table(name = "BemPessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Bem extends EntidadeReplicavel<String> {

	/** Serial UID. */
	private static final long serialVersionUID = -2573771691240335569L;

	/** O atributo id. */
	@Id
	@Column(name = "UIDBemPessoa")
	@GeneratedValue(generator = "geradorGUID")
	@GenericGenerator(name = "geradorGUID", 
			strategy = "br.com.sicoob.capes.negocio.entidades.legado.id.GUIDGenerator")
	private String id;

	/** O atributo data cadastro. */
	private Date dataCadastro;

	/** O atributo nome seguradora. */
	@Column(name = "DescNomeSeguradora")
	private String nomeSeguradora;

	/** O atributo descricao. */
	@Column(name = "descBemPessoa")
	private String descricao;

	/** O atributo valor atual mercado. */
	private BigDecimal valorAtualMercado;

	/** O atributo valor seguro. */
	private BigDecimal valorSeguro;

	/** O atributo data vencimento seguro. */
	private Date dataVencimentoSeguro;
	
	/** O atributo data inativacao. */
	private Date dataInativacao;

	/** O atributo tipo bem. */
	@ManyToOne
	@JoinColumn(name = "IDTipoBem", referencedColumnName = "IDTipoBem")
	private TipoBem tipoBem;

	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn(name = "NumPessoa", referencedColumnName = "NumPessoa")
	private Pessoa pessoa;

	/** O atributo situacao. */
	@Column(name = "CodSituacao")
	private Short situacao;

	/** O atributo valor usado calculo limite. */
	@Column(name = "valorUsadoCalculoLimite")
	private BigDecimal valorUsadoCalculoLimite;

	/** O atributo id bem d b2. */
	@Column(name="IDBEMPESSOADB2")
	private Long idBemDB2;

	/** O atributo bens onus. */
	@OneToMany(mappedBy="bemOnusPK.bem", cascade=CascadeType.ALL, orphanRemoval = true, targetEntity = BemOnus.class)
	private List<BemOnus> bensOnus;

	/** O atributo bens posse. */
	@OneToMany(mappedBy="bemPossePK.bem", cascade=CascadeType.ALL, orphanRemoval = true, targetEntity = BemPosse.class)
	private List<BemPosse> bensPosse;
	
	/** O atributo bens registro. */
	@OneToMany(mappedBy="bemRegistroPK.bem", cascade=CascadeType.ALL, orphanRemoval = true, targetEntity = BemRegistro.class)
	private List<BemRegistro> bensRegistro;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Recupera data inativacao.
	 * 
	 * @return data inativacao
	 */
	public Date getDataInativacao() {
		return dataInativacao;
	}

	/**
	 * Preenche data inativacao.
	 * 
	 * @param dataInativacao
	 *            o novo data inativacao
	 */
	public void setDataInativacao(Date dataInativacao) {
		this.dataInativacao = dataInativacao;
	}

	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro
	 *            the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the nomeSeguradora
	 */
	public String getNomeSeguradora() {
		return nomeSeguradora;
	}

	/**
	 * @param nomeSeguradora
	 *            the nomeSeguradora to set
	 */
	public void setNomeSeguradora(String nomeSeguradora) {
		this.nomeSeguradora = nomeSeguradora;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the valorAtualMercado
	 */
	public BigDecimal getValorAtualMercado() {
		return valorAtualMercado;
	}

	/**
	 * @param valorAtualMercado
	 *            the valorAtualMercado to set
	 */
	public void setValorAtualMercado(BigDecimal valorAtualMercado) {
		this.valorAtualMercado = valorAtualMercado;
	}

	/**
	 * @return the valorSeguro
	 */
	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	/**
	 * @param valorSeguro
	 *            the valorSeguro to set
	 */
	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	/**
	 * @return the dataVencimentoSeguro
	 */
	public Date getDataVencimentoSeguro() {
		return dataVencimentoSeguro;
	}

	/**
	 * @param dataVencimentoSeguro
	 *            the dataVencimentoSeguro to set
	 */
	public void setDataVencimentoSeguro(Date dataVencimentoSeguro) {
		this.dataVencimentoSeguro = dataVencimentoSeguro;
	}

	/**
	 * @return the tipoBem
	 */
	public TipoBem getTipoBem() {
		return tipoBem;
	}

	/**
	 * @param tipoBem
	 *            the tipoBem to set
	 */
	public void setTipoBem(TipoBem tipoBem) {
		this.tipoBem = tipoBem;
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa
	 *            the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the situacao
	 */
	public Short getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao
	 *            the situacao to set
	 */
	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return the valorUsadoCalculoLimite
	 */
	public BigDecimal getValorUsadoCalculoLimite() {
		return valorUsadoCalculoLimite;
	}

	/**
	 * @param valorUsadoCalculoLimite
	 *            the valorUsadoCalculoLimite to set
	 */
	public void setValorUsadoCalculoLimite(BigDecimal valorUsadoCalculoLimite) {
		this.valorUsadoCalculoLimite = valorUsadoCalculoLimite;
	}

	/**
	 * @return the idBemDB2
	 */
	public Long getIdBemDB2() {
		return idBemDB2;
	}

	/**
	 * @return the bensOnus
	 */
	public List<BemOnus> getBensOnus() {
		return bensOnus;
	}

	/**
	 * @param bensOnus the bensOnus to set
	 */
	public void setBensOnus(List<BemOnus> bensOnus) {
		this.bensOnus = bensOnus;
	}

	/**
	 * @return the bensPosse
	 */
	public List<BemPosse> getBensPosse() {
		return bensPosse;
	}

	/**
	 * @param bensPosse the bensPosse to set
	 */
	public void setBensPosse(List<BemPosse> bensPosse) {
		this.bensPosse = bensPosse;
	}

	/**
	 * @return the bensRegistro
	 */
	public List<BemRegistro> getBensRegistro() {
		return bensRegistro;
	}

	/**
	 * @param bensRegistro the bensRegistro to set
	 */
	public void setBensRegistro(List<BemRegistro> bensRegistro) {
		this.bensRegistro = bensRegistro;
	}

	/**
	 * @param idBemDB2
	 *            the idBemDB2 to set
	 */
	public void setIdBemDB2(Long idBemDB2) {
		this.idBemDB2 = idBemDB2;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Serializable getIdDB2() {
		return getIdBemDB2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof String){
			setId((String) idSQL);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getIdSQL() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Long){
			setIdBemDB2((Long) idDB2);
		}
	}
	
}
