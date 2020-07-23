/*
 * SICOOB
 * 
 * TipoDocumento.java(br.com.sicoob.capes.negocio.entidades.TipoDocumento)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobEmbeddableObject;

/**
 * The Class TipoDocumento.
 */
@Embeddable
public class TipoDocumento extends BancoobEmbeddableObject {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -3878768775743079385L;

	/** O atributo id tipo documento. */
	private Integer idTipoDocumento;
	
	/** O atributo nome tipo doc. */
	private String nomeTipoDoc;
	
	/** O atributo sigla tipo documento. */
	private String siglaTipoDocumento;

	/**
	 * Recupera id tipo documento.
	 * 
	 * @return id tipo documento
	 */
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * Preenche id tipo documento.
	 * 
	 * @param idTipoDocumento
	 *            o novo id tipo documento
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * Recupera nome tipo doc.
	 * 
	 * @return nome tipo doc
	 */
	public String getNomeTipoDoc() {
		return nomeTipoDoc;
	}

	/**
	 * Preenche nome tipo doc.
	 * 
	 * @param nomeTipoDoc
	 *            o novo nome tipo doc
	 */
	public void setNomeTipoDoc(String nomeTipoDoc) {
		this.nomeTipoDoc = nomeTipoDoc;
	}

	/**
	 * Recupera sigla tipo documento.
	 * 
	 * @return sigla tipo documento
	 */
	public String getSiglaTipoDocumento() {
		return siglaTipoDocumento;
	}

	/**
	 * Preenche sigla tipo documento.
	 * 
	 * @param siglaTipoDocumento
	 *            o novo sigla tipo documento
	 */
	public void setSiglaTipoDocumento(String siglaTipoDocumento) {
		this.siglaTipoDocumento = siglaTipoDocumento;
	}
}
