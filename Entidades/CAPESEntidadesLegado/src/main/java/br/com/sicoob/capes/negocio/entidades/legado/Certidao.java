/*
 * SICOOB
 * 
 * Certidao.java(br.com.sicoob.capes.negocio.entidades.legado.Certidao)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sicoob.capes.negocio.entidades.legado.pk.CertidaoPK;

/**
 * Entidade que representa a tabela responsável por armazenar as certidôes de uma pessoa.
 * 
 * @author juan.damasceno
 */
@Entity
@Table(name="CERTIDAO")
public class Certidao extends EntidadeReplicavel<CertidaoPK>{

	/** Serial UID. */
	private static final long serialVersionUID = 2825633013234059016L;
	
	/** O atributo certidao pk. */
	@EmbeddedId
	private CertidaoPK certidaoPK;
	
	/** O atributo seq certidao. */
	@Column(name = "IDSEQCERTIDAO", updatable=false, insertable=false)
	private Short seqCertidao;
	
	/** O atributo id tipo certidao. */
	@JoinColumn( name = "IDTIPOCERTIDAO")
	private Short idTipoCertidao;
	
	/** O atributo numero. */
	@Column(name = "NUMCERTIDAO")
	private String numero;
	
	/** O atributo observacao. */
	@Column(name = "DESCOBSERVACAO")
	private String observacao;
	
	/** O atributo data emissao. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmissao;
	
	/** O atributo data cadastro. */
	private Date dataCadastro;
	
	/** O atributo data vencimento documento. */
	private Date dataVencimentoDocumento;
	
	/** O atributo id certidao d b2. */
	private Long idCertidaoDB2;
	
	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * @return the dataEmissao
	 */
	public Date getDataEmissao() {
		return dataEmissao;
	}

	/**
	 * @param dataEmissao the dataEmissao to set
	 */
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	/**
	 * @return the idTipoCertidao
	 */
	public Short getIdTipoCertidao() {
		return idTipoCertidao;
	}

	/**
	 * @param idTipoCertidao the idTipoCertidao to set
	 */
	public void setIdTipoCertidao(Short idTipoCertidao) {
		this.idTipoCertidao = idTipoCertidao;
	}

	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the dataVencimentoDocumento
	 */
	public Date getDataVencimentoDocumento() {
		return dataVencimentoDocumento;
	}

	/**
	 * @param dataVencimentoDocumento the dataVencimentoDocumento to set
	 */
	public void setDataVencimentoDocumento(Date dataVencimentoDocumento) {
		this.dataVencimentoDocumento = dataVencimentoDocumento;
	}

	/**
	 * @return the idCertidaoDB2
	 */
	public Long getIdCertidaoDB2() {
		return idCertidaoDB2;
	}

	/**
	 * @param idCertidaoDB2 the idCertidaoDB2 to set
	 */
	public void setIdCertidaoDB2(Long idCertidaoDB2) {
		this.idCertidaoDB2 = idCertidaoDB2;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public CertidaoPK getIdSQL() {
		return getCertidaoPK();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdCertidaoDB2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof CertidaoPK){
			setCertidaoPK((CertidaoPK) idSQL);
		}
	}

	/**
	 * @return the certidaoPK
	 */
	public CertidaoPK getCertidaoPK() {
		return certidaoPK;
	}

	/**
	 * @param certidaoPK the certidaoPK to set
	 */
	public void setCertidaoPK(CertidaoPK certidaoPK) {
		this.certidaoPK = certidaoPK;
	}

	/**
	 * @return the seqCertidao
	 */
	public Short getSeqCertidao() {
		return seqCertidao;
	}

	/**
	 * @param seqCertidao the seqCertidao to set
	 */
	public void setSeqCertidao(Short seqCertidao) {
		this.seqCertidao = seqCertidao;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Long){
			setIdCertidaoDB2((Long) idDB2);
		}
	}	
}