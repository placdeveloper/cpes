package br.com.sicoob.capes.cadastro.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe StatusTransferenciaClienteVO.
 */
public class StatusTransferenciaClienteVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 7420104451448343241L;

	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;
	
	/** O atributo numeroNucleo. */
	private Integer numeroNucleo;
	
	/** O atributo nomeNucleo. */
	private String nomeNucleo;
	
	/** O atributo nomePessoa. */
	private String nomePessoa;
	
	/** O atributo quantidade. */
	private Long quantidade;

	/**
	 * Recupera o valor de idUnidadeInst.
	 *
	 * @return o valor de idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Define o valor de idUnidadeInst.
	 *
	 * @param idUnidadeInst o novo valor de idUnidadeInst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Recupera o valor de numeroNucleo.
	 *
	 * @return o valor de numeroNucleo
	 */
	public Integer getNumeroNucleo() {
		return numeroNucleo;
	}

	/**
	 * Define o valor de numeroNucleo.
	 *
	 * @param numeroNucleo o novo valor de numeroNucleo
	 */
	public void setNumeroNucleo(Integer numeroNucleo) {
		this.numeroNucleo = numeroNucleo;
	}

	/**
	 * Recupera o valor de nomeNucleo.
	 *
	 * @return o valor de nomeNucleo
	 */
	public String getNomeNucleo() {
		return nomeNucleo;
	}

	/**
	 * Define o valor de nomeNucleo.
	 *
	 * @param nomeNucleo o novo valor de nomeNucleo
	 */
	public void setNomeNucleo(String nomeNucleo) {
		this.nomeNucleo = nomeNucleo;
	}

	/**
	 * Recupera o valor de nomePessoa.
	 *
	 * @return o valor de nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Define o valor de nomePessoa.
	 *
	 * @param nomePessoa o novo valor de nomePessoa
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * Recupera o valor de quantidade.
	 *
	 * @return o valor de quantidade
	 */
	public Long getQuantidade() {
		return quantidade;
	}

	/**
	 * Define o valor de quantidade.
	 *
	 * @param quantidade o novo valor de quantidade
	 */
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}