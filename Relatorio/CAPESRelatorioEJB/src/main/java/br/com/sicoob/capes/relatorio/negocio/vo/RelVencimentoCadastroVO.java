package br.com.sicoob.capes.relatorio.negocio.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe RelVencimentoCadastroVO.
 */
public class RelVencimentoCadastroVO extends BancoobVo implements Serializable {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** O atributo central. */
	private Integer central;
	
	/** O atributo instituicao. */
	private Integer instituicao;
	
	/** O atributo unidade. */
	private Integer unidade;
	
	/** O atributo nomeUnidade. */
	private String nomeUnidade;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo dataUltimaRenovacao. */
	private Timestamp dataUltimaRenovacao;
	
	/** O atributo instResponsavel. */
	private Integer instResponsavel;

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
	 * Recupera o valor de instituicao.
	 *
	 * @return o valor de instituicao
	 */
	public Integer getInstituicao() {
		return instituicao;
	}

	/**
	 * Define o valor de instituicao.
	 *
	 * @param instituicao o novo valor de instituicao
	 */
	public void setInstituicao(Integer instituicao) {
		this.instituicao = instituicao;
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
	 * Recupera o valor de nomeUnidade.
	 *
	 * @return o valor de nomeUnidade
	 */
	public String getNomeUnidade() {
		return nomeUnidade;
	}

	/**
	 * Define o valor de nomeUnidade.
	 *
	 * @param nomeUnidade o novo valor de nomeUnidade
	 */
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
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
	 * Recupera o valor de nome.
	 *
	 * @return o valor de nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Define o valor de nome.
	 *
	 * @param nome o novo valor de nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Recupera o valor de dataUltimaRenovacao.
	 *
	 * @return o valor de dataUltimaRenovacao
	 */
	public Timestamp getDataUltimaRenovacao() {
		return dataUltimaRenovacao;
	}

	/**
	 * Define o valor de dataUltimaRenovacao.
	 *
	 * @param dataUltimaRenovacao o novo valor de dataUltimaRenovacao
	 */
	public void setDataUltimaRenovacao(Timestamp dataUltimaRenovacao) {
		this.dataUltimaRenovacao = dataUltimaRenovacao;
	}

	/**
	 * Recupera o valor de instResponsavel.
	 *
	 * @return o valor de instResponsavel
	 */
	public Integer getInstResponsavel() {
		return instResponsavel;
	}

	/**
	 * Define o valor de instResponsavel.
	 *
	 * @param instResponsavel o novo valor de instResponsavel
	 */
	public void setInstResponsavel(Integer instResponsavel) {
		this.instResponsavel = instResponsavel;
	}

}