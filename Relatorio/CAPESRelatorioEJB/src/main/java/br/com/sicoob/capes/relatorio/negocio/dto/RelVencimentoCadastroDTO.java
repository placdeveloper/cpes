package br.com.sicoob.capes.relatorio.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe RelVencimentoCadastroDTO.
 */
public class RelVencimentoCadastroDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4300123543135458283L;

	/** O atributo central. */
	private Integer central;
	
	/** O atributo singular. */
	private Integer singular;
	
	/** O atributo unidade. */
	private Integer unidade;
	
	/** O atributo nucleo. */
	private Integer nucleo;
	
	/** O atributo nomeNucleo. */
	private String nomeNucleo;
	
	/** O atributo gerente. */
	private Integer gerente;
	
	/** O atributo nomeGerente. */
	private String nomeGerente;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;

	/** O atributo dataInicio. */
	private Date dataInicio;
	
	/** O atributo dataFim. */
	private Date dataFim;

	/** O atributo somenteResponsavel. */
	private Boolean somenteResponsavel;
	
	/** O atributo ordenacao. */
	private Integer ordenacao;

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera o valor de central.
	 *
	 * @return o valor de central
	 */
	public Integer getCentral() {
		return central;
	}

	/**
	 * Define o valor de central.
	 *
	 * @param central o novo valor de central
	 */
	public void setCentral(Integer central) {
		this.central = central;
	}

	/**
	 * Recupera o valor de singular.
	 *
	 * @return o valor de singular
	 */
	public Integer getSingular() {
		return singular;
	}

	/**
	 * Define o valor de singular.
	 *
	 * @param singular o novo valor de singular
	 */
	public void setSingular(Integer singular) {
		this.singular = singular;
	}

	/**
	 * Recupera o valor de unidade.
	 *
	 * @return o valor de unidade
	 */
	public Integer getUnidade() {
		return unidade;
	}

	/**
	 * Define o valor de unidade.
	 *
	 * @param unidade o novo valor de unidade
	 */
	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}

	/**
	 * Recupera o valor de nucleo.
	 *
	 * @return o valor de nucleo
	 */
	public Integer getNucleo() {
		return nucleo;
	}

	/**
	 * Define o valor de nucleo.
	 *
	 * @param nucleo o novo valor de nucleo
	 */
	public void setNucleo(Integer nucleo) {
		this.nucleo = nucleo;
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
	 * Recupera o valor de gerente.
	 *
	 * @return o valor de gerente
	 */
	public Integer getGerente() {
		return gerente;
	}

	/**
	 * Define o valor de gerente.
	 *
	 * @param gerente o novo valor de gerente
	 */
	public void setGerente(Integer gerente) {
		this.gerente = gerente;
	}

	/**
	 * Recupera o valor de nomeGerente.
	 *
	 * @return o valor de nomeGerente
	 */
	public String getNomeGerente() {
		return nomeGerente;
	}

	/**
	 * Define o valor de nomeGerente.
	 *
	 * @param nomeGerente o novo valor de nomeGerente
	 */
	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	/**
	 * Recupera o valor de dataInicio.
	 *
	 * @return o valor de dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * Define o valor de dataInicio.
	 *
	 * @param dataInicio o novo valor de dataInicio
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * Recupera o valor de dataFim.
	 *
	 * @return o valor de dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * Define o valor de dataFim.
	 *
	 * @param dataFim o novo valor de dataFim
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * Recupera o valor de somenteResponsavel.
	 *
	 * @return o valor de somenteResponsavel
	 */
	public Boolean getSomenteResponsavel() {
		return somenteResponsavel;
	}

	/**
	 * Define o valor de somenteResponsavel.
	 *
	 * @param somenteResponsavel o novo valor de somenteResponsavel
	 */
	public void setSomenteResponsavel(Boolean somenteResponsavel) {
		this.somenteResponsavel = somenteResponsavel;
	}

	/**
	 * Recupera o valor de ordenacao.
	 *
	 * @return o valor de ordenacao
	 */
	public Integer getOrdenacao() {
		return ordenacao;
	}

	/**
	 * Define o valor de ordenacao.
	 *
	 * @param ordenacao o novo valor de ordenacao
	 */
	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}

}