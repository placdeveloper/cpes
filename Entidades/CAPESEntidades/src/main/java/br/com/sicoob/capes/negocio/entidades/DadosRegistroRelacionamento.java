/*
 * SICOOB
 * 
 * DadosRegistroRelacionamento.java(br.com.sicoob.capes.negocio.entidades.DadosRegistroRelacionamento)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobEmbeddableObject;

/**
 * Dados de registro de relacionamento
 *
 * 24/08/2011
 * @author Rodrigo.Chaves
 */
@Embeddable
public class DadosRegistroRelacionamento extends BancoobEmbeddableObject {

	/** Serial UID */
	private static final long serialVersionUID = 3387462092569448273L;

	/** O atributo numero registro. */
	@Column(name = "NUMREGISTRO", length = 20)
	private String numeroRegistro;
	
	/** O atributo numero livro. */
	@Column(name = "NUMLIVRO", length = 10)
	private String numeroLivro;
	
	/** O atributo numero folha. */
	@Column(name = "NUMFOLHA", length = 4)
	private String numeroFolha;
	
	/** O atributo nome cartorio. */
	@Column(name = "NOMECARTORIO", length = 100)
	private String nomeCartorio;

	/**
	 * Recupera numero registro.
	 * 
	 * @return numero registro
	 */
	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	/**
	 * Preenche numero registro.
	 * 
	 * @param numeroRegistro
	 *            o novo numero registro
	 */
	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	/**
	 * Recupera numero livro.
	 * 
	 * @return numero livro
	 */
	public String getNumeroLivro() {
		return numeroLivro;
	}

	/**
	 * Preenche numero livro.
	 * 
	 * @param numeroLivro
	 *            o novo numero livro
	 */
	public void setNumeroLivro(String numeroLivro) {
		this.numeroLivro = numeroLivro;
	}

	/**
	 * Recupera numero folha.
	 * 
	 * @return numero folha
	 */
	public String getNumeroFolha() {
		return numeroFolha;
	}

	/**
	 * Preenche numero folha.
	 * 
	 * @param numeroFolha
	 *            o novo numero folha
	 */
	public void setNumeroFolha(String numeroFolha) {
		this.numeroFolha = numeroFolha;
	}

	/**
	 * Recupera nome cartorio.
	 * 
	 * @return nome cartorio
	 */
	public String getNomeCartorio() {
		return nomeCartorio;
	}

	/**
	 * Preenche nome cartorio.
	 * 
	 * @param nomeCartorio
	 *            o novo nome cartorio
	 */
	public void setNomeCartorio(String nomeCartorio) {
		this.nomeCartorio = nomeCartorio;
	}
	
}
