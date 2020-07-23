/*
 * SICOOB
 * 
 * CertidaoPK.java(br.com.sicoob.capes.negocio.entidades.legado.pk.CertidaoPK)
 */
package br.com.sicoob.capes.negocio.entidades.legado.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;

/**
 * The Class CertidaoPK.
 */
@Embeddable
public class CertidaoPK implements Serializable {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** O atributo seq certidao. */
	@Column(name = "IDSEQCERTIDAO")
	private Short seqCertidao;

	/** O atributo pessoa. */
	@JoinColumn(name = "NUMPESSOA")
	@ManyToOne
	private Pessoa pessoa;

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
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "pessoa.id", "seqCertidao");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "pessoa.id", "seqCertidao");
	}
}