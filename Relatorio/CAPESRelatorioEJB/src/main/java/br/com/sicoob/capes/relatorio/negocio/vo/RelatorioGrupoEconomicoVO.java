package br.com.sicoob.capes.relatorio.negocio.vo;

import java.io.Serializable;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe RelatorioGrupoEconomicoVO.
 */
public class RelatorioGrupoEconomicoVO extends BancoobVo implements Serializable {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -550770425248240677L;

	/** O atributo dataInicio. */
	private DateTime dataInicio;
	
	/** O atributo dataFim. */
	private DateTime dataFim;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo numCentral. */
	private Integer numCentral;
	
	/** O atributo numSingular. */
	private Integer numSingular;
	
	/** O atributo numTipoRegistro. */
	private Integer numTipoRegistro;
	
	/** O atributo unidade. */
	private Integer unidade;
	
	/** O atributo nucleo. */
	private Integer nucleo;
	
	/** O atributo gerente. */
	private Integer gerente;

	/** O atributo descGrupoEconomico. */
	private String descGrupoEconomico;
	
	/** O atributo nomePessoa. */
	private String nomePessoa;
	
	/** O atributo usuarioResponsavel. */
	private String usuarioResponsavel;
	
	/** O atributo dataCadastro. */
	private DateTime dataCadastro;

	/**
	 * Recupera o valor de dataInicio.
	 *
	 * @return o valor de dataInicio
	 */
	public DateTime getDataInicio() {
		return dataInicio;
	}

	/**
	 * Define o valor de dataInicio.
	 *
	 * @param dataInicio o novo valor de dataInicio
	 */
	public void setDataInicio(DateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * Recupera o valor de dataFim.
	 *
	 * @return o valor de dataFim
	 */
	public DateTime getDataFim() {
		return dataFim;
	}

	/**
	 * Define o valor de dataFim.
	 *
	 * @param dataFim o novo valor de dataFim
	 */
	public void setDataFim(DateTime dataFim) {
		this.dataFim = dataFim;
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
	 * Recupera o valor de numCentral.
	 *
	 * @return o valor de numCentral
	 */
	public Integer getNumCentral() {
		return numCentral;
	}

	/**
	 * Define o valor de numCentral.
	 *
	 * @param numCentral o novo valor de numCentral
	 */
	public void setNumCentral(Integer numCentral) {
		this.numCentral = numCentral;
	}

	/**
	 * Recupera o valor de numSingular.
	 *
	 * @return o valor de numSingular
	 */
	public Integer getNumSingular() {
		return numSingular;
	}

	/**
	 * Define o valor de numSingular.
	 *
	 * @param numSingular o novo valor de numSingular
	 */
	public void setNumSingular(Integer numSingular) {
		this.numSingular = numSingular;
	}

	/**
	 * Recupera o valor de numTipoRegistro.
	 *
	 * @return o valor de numTipoRegistro
	 */
	public Integer getNumTipoRegistro() {
		return numTipoRegistro;
	}

	/**
	 * Define o valor de numTipoRegistro.
	 *
	 * @param numTipoRegistro o novo valor de numTipoRegistro
	 */
	public void setNumTipoRegistro(Integer numTipoRegistro) {
		this.numTipoRegistro = numTipoRegistro;
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
	 * Recupera o valor de descGrupoEconomico.
	 *
	 * @return o valor de descGrupoEconomico
	 */
	public String getDescGrupoEconomico() {
		return descGrupoEconomico;
	}

	/**
	 * Define o valor de descGrupoEconomico.
	 *
	 * @param descGrupoEconomico o novo valor de descGrupoEconomico
	 */
	public void setDescGrupoEconomico(String descGrupoEconomico) {
		this.descGrupoEconomico = descGrupoEconomico;
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
	 * Recupera o valor de usuarioResponsavel.
	 *
	 * @return o valor de usuarioResponsavel
	 */
	public String getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	/**
	 * Define o valor de usuarioResponsavel.
	 *
	 * @param usuarioResponsavel o novo valor de usuarioResponsavel
	 */
	public void setUsuarioResponsavel(String usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	/**
	 * Recupera o valor de dataCadastro.
	 *
	 * @return o valor de dataCadastro
	 */
	public DateTime getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * Define o valor de dataCadastro.
	 *
	 * @param dataCadastro o novo valor de dataCadastro
	 */
	public void setDataCadastro(DateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
