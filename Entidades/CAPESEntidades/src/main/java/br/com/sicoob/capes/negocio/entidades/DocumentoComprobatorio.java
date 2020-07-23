/*
 * SICOOB
 * 
 * DocumentoComprobatorio.java(br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import br.com.bancoob.negocio.entidades.BancoobEmbeddableObject;

/**
 * Entidade que representa o documento comprobatório da atualização cadastral.
 * @author Erico.Junior
 */
@Embeddable
public class DocumentoComprobatorio extends BancoobEmbeddableObject {

	/** Serial UID.*/
	private static final long serialVersionUID = -4068150073455223239L;
	
	/** O atributo id documento. */
	@Column(name = "IDDOCUMENTO", nullable = false)
	private Long idDocumento;
	
	/** O atributo nome. */
	@Transient
	private String nome;
	
	/** O atributo arquivos. */
	@Transient
	private Set<Arquivo> arquivos;
	
	/** O atributo tipo documento. */
	@Transient
	private TipoDocumento tipoDocumento;

	/**
	 * Recupera id documento.
	 * 
	 * @return id documento
	 */
	public Long getIdDocumento() {
		return idDocumento;
	}

	/**
	 * Preenche id documento.
	 * 
	 * @param idDocumento
	 *            o novo id documento
	 */
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * Recupera nome.
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Preenche nome.
	 * 
	 * @param nome
	 *            o novo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Recupera arquivos.
	 * 
	 * @return arquivos
	 */
	public Set<Arquivo> getArquivos() {
		return arquivos;
	}

	/**
	 * Preenche arquivos.
	 * 
	 * @param arquivos
	 *            o novo arquivos
	 */
	public void setArquivos(Set<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	/**
	 * Recupera tipo documento.
	 * 
	 * @return tipo documento
	 */
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Preenche tipo documento.
	 * 
	 * @param tipoDocumento
	 *            o novo tipo documento
	 */
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
