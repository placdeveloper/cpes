package br.com.sicoob.capes.relatorio.negocio.vo;

import java.util.Date;

/**
 * A Classe RelatorioValidacaoCadastralAnaliticoVO.
 */
public class RelatorioValidacaoCadastralAnaliticoVO extends RelatorioValidacaoCadastralVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4179731157667778576L;

	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo nomePessoa. */
	private String nomePessoa;
	
	/** O atributo dataUltimaAtualizacao. */
	private Date dataUltimaAtualizacao;

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
	 * Recupera o valor de dataUltimaAtualizacao.
	 *
	 * @return o valor de dataUltimaAtualizacao
	 */
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	/**
	 * Define o valor de dataUltimaAtualizacao.
	 *
	 * @param dataUltimaAtualizacao o novo valor de dataUltimaAtualizacao
	 */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

}