/*
 * SICOOB
 * 
 * Arquivo.java(br.com.sicoob.capes.negocio.entidades.Arquivo)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import br.com.bancoob.negocio.entidades.BancoobEmbeddableObject;

/**
 * Entidade que representa um arquivo para os documentos comprobatórios.
 * @author Erico.Junior
 */
@Embeddable
public class Arquivo extends BancoobEmbeddableObject {

	/** Serial UID. */
	private static final long serialVersionUID = -2470809025083212389L;

	/** O atributo path. */
	@Transient
	private String path;

	/**
	 * Cria uma nova instância de arquivo.
	 */
	public Arquivo() {
	}

	/**
	 * Cria uma nova instância de arquivo.
	 * 
	 * @param path
	 *            the path
	 */
	public Arquivo(String path) {
		this.path = path;
	}

	/**
	 * Recupera path.
	 * 
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Preenche path.
	 * 
	 * @param path
	 *            o novo path
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
