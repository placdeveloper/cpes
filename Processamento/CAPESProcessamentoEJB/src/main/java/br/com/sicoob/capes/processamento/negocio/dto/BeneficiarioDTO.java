/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * @author Erico.Junior
 *
 */
public class BeneficiarioDTO extends BancoobDto{

	/** Serial UID.*/
	private static final long serialVersionUID = -4687475894480399563L;
	
	/** O atributo numCooperativa. */
	private Integer numCooperativa;
	
	/** O atributo numPac. */
	private Integer numPac;
	
	/** O atributo numContaCorrente. */
	private String numContaCorrente;
	
	/** O atributo numBeneficiario. */
	private String numBeneficiario;
	
	/** O atributo numTrabalhador. */
	private String numTrabalhador;
	
	/** O atributo numCpf. */
	private String numCpf;
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo nomeMae. */
	private String nomeMae;
	
	/** O atributo dataNascimento. */
	private DateTimeDB dataNascimento;
	
	/** O atributo endereco. */
	private String endereco;
	
	/** O atributo bairro. */
	private String bairro;
	
	/** O atributo cep. */
	private String cep;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;
	
	/** O atributo idLocalidade. */
	private Integer idLocalidade;
	
	/** O atributo uf. */
	private String uf;
	
	/** O atributo enderecoCooperativa. */
	private String enderecoCooperativa;
	
	/**
	 * @return the numCooperativa
	 */
	public Integer getNumCooperativa() {
		return numCooperativa;
	}
	/**
	 * @param numCooperativa the numCooperativa to set
	 */
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}
	/**
	 * @return the numPac
	 */
	public Integer getNumPac() {
		return numPac;
	}
	/**
	 * @param numPac the numPac to set
	 */
	public void setNumPac(Integer numPac) {
		this.numPac = numPac;
	}
	/**
	 * @return the numContaCorrente
	 */
	public String getNumContaCorrente() {
		return numContaCorrente;
	}
	/**
	 * @param numContaCorrente the numContaCorrente to set
	 */
	public void setNumContaCorrente(String numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
	/**
	 * @return the numBeneficiario
	 */
	public String getNumBeneficiario() {
		return numBeneficiario;
	}
	/**
	 * @param numBeneficiario the numBeneficiario to set
	 */
	public void setNumBeneficiario(String numBeneficiario) {
		this.numBeneficiario = numBeneficiario;
	}
	/**
	 * @return the numTrabalhador
	 */
	public String getNumTrabalhador() {
		return numTrabalhador;
	}
	/**
	 * @param numTrabalhador the numTrabalhador to set
	 */
	public void setNumTrabalhador(String numTrabalhador) {
		this.numTrabalhador = numTrabalhador;
	}
	/**
	 * @return the numCpf
	 */
	public String getNumCpf() {
		return numCpf;
	}
	/**
	 * @param numCpf the numCpf to set
	 */
	public void setNumCpf(String numCpf) {
		this.numCpf = numCpf;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}
	/**
	 * @param nomeMae the nomeMae to set
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	/**
	 * @return the dataNascimento
	 */
	public DateTimeDB getDataNascimento() {
		return dataNascimento;
	}
	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(DateTimeDB dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}
	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}
	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	/**
	 * @return the idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}
	/**
	 * @param idUnidadeInst the idUnidadeInst to set
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}
	/**
	 * @return the idLocalidade
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade the idLocalidade to set
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}
	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}
	/**
	 * @return the enderecoCooperativa
	 */
	public String getEnderecoCooperativa() {
		return enderecoCooperativa;
	}
	/**
	 * @param enderecoCooperativa the enderecoCooperativa to set
	 */
	public void setEnderecoCooperativa(String enderecoCooperativa) {
		this.enderecoCooperativa = enderecoCooperativa;
	}
	
}
