package br.com.sicoob.capes.relatorio.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum;

/**
 * A Classe RelatorioValidacaoCadastralDTO.
 */
public class RelatorioValidacaoCadastralDTO extends BancoobDto {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 8690080421896107346L;

	/** O atributo central. */
	private Integer central;
	
	/** O atributo singular. */
	private Integer singular;
	
	/** O atributo idSingular. */
	private Integer idSingular;
	
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
	
	/** O atributo funcionalidade. */
	private FuncionalidadeValidacaoCadastralEnum funcionalidade;
	
	/** O atributo idTipoErro. */
	private Integer idTipoErro;
	
	/** O atributo descricaoTipoErro. */
	private String descricaoTipoErro;
	
	/** O atributo codigoTipoRegra. */
	private String codigoTipoRegra;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	
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
	 * Recupera o valor de idSingular.
	 *
	 * @return o valor de idSingular
	 */
	public Integer getIdSingular() {
		return idSingular;
	}

	/**
	 * Define o valor de idSingular.
	 *
	 * @param idSingular o novo valor de idSingular
	 */
	public void setIdSingular(Integer idSingular) {
		this.idSingular = idSingular;
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
	 * Recupera o valor de idTipoErro.
	 *
	 * @return o valor de idTipoErro
	 */
	public Integer getIdTipoErro() {
		return idTipoErro;
	}

	/**
	 * Define o valor de idTipoErro.
	 *
	 * @param idTipoErro o novo valor de idTipoErro
	 */
	public void setIdTipoErro(Integer idTipoErro) {
		this.idTipoErro = idTipoErro;
	}

	/**
	 * Recupera o valor de descricaoTipoErro.
	 *
	 * @return o valor de descricaoTipoErro
	 */
	public String getDescricaoTipoErro() {
		return descricaoTipoErro;
	}

	/**
	 * Define o valor de descricaoTipoErro.
	 *
	 * @param descricaoTipoErro o novo valor de descricaoTipoErro
	 */
	public void setDescricaoTipoErro(String descricaoTipoErro) {
		this.descricaoTipoErro = descricaoTipoErro;
	}

	/**
	 * Recupera o valor de codigoTipoRegra.
	 *
	 * @return o valor de codigoTipoRegra
	 */
	public String getCodigoTipoRegra() {
		return codigoTipoRegra;
	}

	/**
	 * Define o valor de codigoTipoRegra.
	 *
	 * @param codigoTipoRegra o novo valor de codigoTipoRegra
	 */
	public void setCodigoTipoRegra(String codigoTipoRegra) {
		this.codigoTipoRegra = codigoTipoRegra;
	}

	/**
	 * Recupera o valor de cpfCnpj.
	 *
	 * @return o valor de cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define o valor de cpfCnpj.
	 *
	 * @param cpfCnpj o novo valor de cpfCnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o valor de funcionalidade.
	 *
	 * @return o valor de funcionalidade
	 */
	public FuncionalidadeValidacaoCadastralEnum getFuncionalidade() {
		return funcionalidade;
	}

	/**
	 * Define o valor de funcionalidade.
	 *
	 * @param funcionalidade o novo valor de funcionalidade
	 */
	public void setFuncionalidade(FuncionalidadeValidacaoCadastralEnum funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	
	/**
	 * Recupera o valor de nomeFuncionalidade.
	 *
	 * @return o valor de nomeFuncionalidade
	 */
	public String getNomeFuncionalidade() {
		return funcionalidade != null ? funcionalidade.name() : null;
	}

}