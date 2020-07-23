/*
 * SICOOB
 * 
 * AutorizacaoDocumento.java(br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * The Class AutorizacaoDocumento.
 */
@Entity
@Table(schema = "CLI", name = "AUTORIZACAODOCUMENTO", uniqueConstraints = @UniqueConstraint(columnNames = {
		"IDAUTORIZACAO", "IDDOCUMENTO" }))
public class AutorizacaoDocumento extends CAPESEntidade<Long> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -843699072241256852L;

	/** O atributo id autorizacao documento. */
	@Id
	@Column(name = "IDAUTORIZACAODOCUMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAutorizacaoDocumento;
	
	/** O atributo id autorizacao. */
	@Column(name = "IDAUTORIZACAO", nullable = false, insertable = false, updatable = false)
	private Long idAutorizacao;
	
	@ManyToOne
	@JoinColumn(name = "IDAUTORIZACAO")
	private Autorizacao autorizacao;
	
	/** O atributo documento. */
	@Embedded
	private DocumentoComprobatorio documento;
	
	/**
	 * Construtor
	 */
	public AutorizacaoDocumento() {
	}

	/**
	 * Construtor
	 * 
	 * @param idAutorizacao
	 * @param idDocumento
	 */
	public AutorizacaoDocumento(Long idAutorizacao, DocumentoComprobatorio documento) {
		this.idAutorizacao = idAutorizacao;
		this.documento = documento;
	}

	/**
	 * Recupera id autorizacao documento.
	 * 
	 * @return id autorizacao documento
	 */
	public Long getIdAutorizacaoDocumento() {
		return idAutorizacaoDocumento;
	}

	/**
	 * Preenche id autorizacao documento.
	 * 
	 * @param idAutorizacaoDocumento
	 *            o novo id autorizacao documento
	 */
	public void setIdAutorizacaoDocumento(Long idAutorizacaoDocumento) {
		this.idAutorizacaoDocumento = idAutorizacaoDocumento;
	}

	/**
	 * Recupera id autorizacao.
	 * 
	 * @return id autorizacao
	 */
	public Long getIdAutorizacao() {
		return idAutorizacao;
	}

	/**
	 * Preenche id autorizacao.
	 * 
	 * @param idAutorizacao
	 *            o novo id autorizacao
	 */
	public void setIdAutorizacao(Long idAutorizacao) {
		this.idAutorizacao = idAutorizacao;
	}
	
	public Autorizacao getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdAutorizacaoDocumento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdAutorizacaoDocumento(id);
	}

	/**
	 * Recupera documento.
	 * 
	 * @return documento
	 */
	public DocumentoComprobatorio getDocumento() {
		return documento;
	}

	/**
	 * Preenche documento.
	 * 
	 * @param documento
	 *            o novo documento
	 */
	public void setDocumento(DocumentoComprobatorio documento) {
		this.documento = documento;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		
		return ReflexaoUtils.hashCode(this, "idAutorizacaoDocumento", "idAutorizacao", "documento.idDocumento");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		
		return ReflexaoUtils.equals(this, obj, "idAutorizacaoDocumento", "idAutorizacao", "documento.idDocumento");
	}

}
