package br.com.sicoob.capes.relatorio.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe RelatorioDeclaracaoPropositoVO.
 */
public class RelatorioDeclaracaoPropositoVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1539544465478546896L;
	
	/** O atributo nomeInstituicao. */
	private String nomeInstituicao;
	
	/** O atributo siglaInstituicao. */
	private String siglaInstituicao;
	
	/** O atributo nomeCompleto. */
	private String nomeCompleto;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo identificacao. */
	private String identificacao;
	
	/** O atributo telefone. */
	private String telefone;

	/**
	 * Recupera o valor de nomeInstituicao.
	 *
	 * @return o valor de nomeInstituicao
	 */
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	/**
	 * Define o valor de nomeInstituicao.
	 *
	 * @param nomeInstituicao o novo valor de nomeInstituicao
	 */
	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	/**
	 * Recupera o valor de siglaInstituicao.
	 *
	 * @return o valor de siglaInstituicao
	 */
	public String getSiglaInstituicao() {
		return siglaInstituicao;
	}

	/**
	 * Define o valor de siglaInstituicao.
	 *
	 * @param siglaInstituicao o novo valor de siglaInstituicao
	 */
	public void setSiglaInstituicao(String siglaInstituicao) {
		this.siglaInstituicao = siglaInstituicao;
	}

	/**
	 * Recupera o valor de nomeCompleto.
	 *
	 * @return o valor de nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * Define o valor de nomeCompleto.
	 *
	 * @param nomeCompleto o novo valor de nomeCompleto
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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
	 * Recupera o valor de identificacao.
	 *
	 * @return o valor de identificacao
	 */
	public String getIdentificacao() {
		return identificacao;
	}

	/**
	 * Define o valor de identificacao.
	 *
	 * @param identificacao o novo valor de identificacao
	 */
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	/**
	 * Recupera o valor de telefone.
	 *
	 * @return o valor de telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Define o valor de telefone.
	 *
	 * @param telefone o novo valor de telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}