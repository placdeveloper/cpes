/*
 * SICOOB
 * 
 * DadosRegistroVO.java(br.com.sicoob.capes.api.negocio.vo.DadosRegistroVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.io.Serializable;

/**
 * Dados de registro DTO
 *
 * @author Marcelo.Onofre
 */
public class DadosRegistroVO implements Serializable{

	/** Serial UID */
	private static final long serialVersionUID = 3387462092569448273L;

	/** O atributo numero registro. */
	private String numeroRegistro;
	
	/** O atributo numero livro. */
	private String numeroLivro;
	
	/** O atributo numero folha. */
	private String numeroFolha;
	
	/** O atributo nome cartorio. */
	private String nomeCartorio;
	
	/**
	 * Cria uma nova instância de dados registro vo.
	 */
	public DadosRegistroVO(){
		
	}
	
	/**
	 * Cria uma nova instância de dados registro vo.
	 * 
	 * @param numeroRegistro
	 *            the numero registro
	 * @param numeroLivro
	 *            the numero livro
	 * @param numeroFolha
	 *            the numero folha
	 * @param nomeCartorio
	 *            the nome cartorio
	 */
	public DadosRegistroVO(String numeroRegistro, String numeroLivro,
			String numeroFolha, String nomeCartorio){
		this.numeroRegistro = numeroRegistro;
		this.numeroLivro = numeroLivro;
		this.numeroFolha = numeroFolha;
		this.nomeCartorio = nomeCartorio;
	}

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