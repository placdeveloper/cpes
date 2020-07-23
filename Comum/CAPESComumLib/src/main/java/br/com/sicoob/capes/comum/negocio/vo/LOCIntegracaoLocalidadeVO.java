/*
 * SICOOB
 * 
 * LOCIntegracaoLocalidadeVO.java(br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;


/**
 * The Class LOCIntegracaoLocalidadeVO.
 */
public class LOCIntegracaoLocalidadeVO extends BancoobVo {

	/** Serial UID */
	private static final long serialVersionUID = 8870669119580294888L;

	/** O atributo id localidade. */
	private Integer idLocalidade;
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo nome limpo. */
	private String nomeLimpo;
	
	/** O atributo numero cep. */
	private String numeroCep;
	
	/** O atributo codigo ibge. */
	private String codigoIBGE;
	
	/** O atributo uf. */
	private LOCIntegracaoUFVO uf = new LOCIntegracaoUFVO();

	/**
	 * Recupera id localidade.
	 * 
	 * @return id localidade
	 */
	public Integer getIdLocalidade() {

		return this.idLocalidade;
	}

	/**
	 * Preenche id localidade.
	 * 
	 * @param idLocalidade
	 *            o novo id localidade
	 */
	public void setIdLocalidade(Integer idLocalidade) {

		this.idLocalidade = idLocalidade;
	}

	/**
	 * Recupera nome.
	 * 
	 * @return nome
	 */
	public String getNome() {

		return this.nome;
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
	 * Recupera nome limpo.
	 * 
	 * @return nome limpo
	 */
	public String getNomeLimpo() {

		return this.nomeLimpo;
	}

	/**
	 * Preenche nome limpo.
	 * 
	 * @param nomeLimpo
	 *            o novo nome limpo
	 */
	public void setNomeLimpo(String nomeLimpo) {

		this.nomeLimpo = nomeLimpo;
	}

	/**
	 * Recupera numero cep.
	 * 
	 * @return numero cep
	 */
	public String getNumeroCep() {

		return this.numeroCep;
	}

	/**
	 * Preenche numero cep.
	 * 
	 * @param numeroCep
	 *            o novo numero cep
	 */
	public void setNumeroCep(String numeroCep) {

		this.numeroCep = numeroCep;
	}

	/**
	 * Recupera codigo ibge.
	 * 
	 * @return codigo ibge
	 */
	public String getCodigoIBGE() {

		return this.codigoIBGE;
	}

	/**
	 * Preenche codigo ibge.
	 * 
	 * @param codigoIBGE
	 *            o novo codigo ibge
	 */
	public void setCodigoIBGE(String codigoIBGE) {

		this.codigoIBGE = codigoIBGE;
	}

	/**
	 * Recupera uf.
	 * 
	 * @return uf
	 */
	public LOCIntegracaoUFVO getUf() {

		return this.uf;
	}

	/**
	 * Preenche uf.
	 * 
	 * @param uf
	 *            o novo uf
	 */
	public void setUf(LOCIntegracaoUFVO uf) {

		this.uf = uf;
	}

}
