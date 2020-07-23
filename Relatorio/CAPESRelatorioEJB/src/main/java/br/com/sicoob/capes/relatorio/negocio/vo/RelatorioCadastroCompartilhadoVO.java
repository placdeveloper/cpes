package br.com.sicoob.capes.relatorio.negocio.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe RelCompartilhamentoVO.
 */
public class RelatorioCadastroCompartilhadoVO extends BancoobVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo central. */
	private Integer central;
	
	/** O atributo singular. */
	private Integer singular;
	
	/** O atributo nomePessoa. */
	private String nomePessoa;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo dataCadastro. */
	private Timestamp dataCadastro;
	
	/** O atributo dataCompartilhamento. */
	private Timestamp dataCompartilhamento;
	
	/** O atributo responsavel. */
	private String responsavel;
	
	/** O atributo coopSigla. */
	private String coopSigla;
	
	public String getCoopSigla() {
		return coopSigla;
	}

	public void setCoopSigla(String coopSigla) {
		this.coopSigla = coopSigla;
	}

	public Integer getCentral() {
		return central;
	}

	public void setCentral(Integer central) {
		this.central = central;
	}

	public Integer getSingular() {
		return singular;
	}

	public void setSingular(Integer singular) {
		this.singular = singular;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Timestamp getDataCompartilhamento() {
		return dataCompartilhamento;
	}

	public void setDataCompartilhamento(Timestamp dataCompartilhamento) {
		this.dataCompartilhamento = dataCompartilhamento;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
}