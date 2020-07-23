/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * Classe RelatorioProvaVidaDTO
 * 
 * @author Daniel.Domingues
 */
/**
 * @author Daniel.Domingues
 *
 */
public class RelatorioProvaVidaDTO extends BancoobDto {

	private static final long serialVersionUID = 9058407865485529680L;

	private Integer numeroCooperativa;
	private Integer unidadeInstituicao;
	
	private String nomeCooperativa;
	private String nomeBeneficiario;
	private String cpf;
	private String numeroDocumento;
	private String orgaoExpeditorDocumento;
	private String ufOrgaoExpedidorDocumento;
	private String cidade;
	private String localData;

	private String dataEmissaoDocumento;

	private Date dataRelatorio;
	
	/**
	 * @return the numeroCooperativa
	 */
	public Integer getNumeroCooperativa() {
		return numeroCooperativa;
	}
	/**
	 * @param numeroCooperativa the numeroCooperativa to set
	 */
	public void setNumeroCooperativa(Integer numeroCooperativa) {
		this.numeroCooperativa = numeroCooperativa;
	}
	/**
	 * @return the unidadeInstituicao
	 */
	public Integer getUnidadeInstituicao() {
		return unidadeInstituicao;
	}
	/**
	 * @param unidadeInstituicao the unidadeInstituicao to set
	 */
	public void setUnidadeInstituicao(Integer unidadeInstituicao) {
		this.unidadeInstituicao = unidadeInstituicao;
	}
	/**
	 * @return the nomeCooperativa
	 */
	public String getNomeCooperativa() {
		return nomeCooperativa;
	}
	/**
	 * @param nomeCooperativa the nomeCooperativa to set
	 */
	public void setNomeCooperativa(String nomeCooperativa) {
		this.nomeCooperativa = nomeCooperativa;
	}
	/**
	 * @return the nomeBeneficiario
	 */
	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}
	/**
	 * @param nomeBeneficiario the nomeBeneficiario to set
	 */
	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}
	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the orgaoExpeditorDocumento
	 */
	public String getOrgaoExpeditorDocumento() {
		return orgaoExpeditorDocumento;
	}
	/**
	 * @param orgaoExpeditorDocumento the orgaoExpeditorDocumento to set
	 */
	public void setOrgaoExpeditorDocumento(String orgaoExpeditorDocumento) {
		this.orgaoExpeditorDocumento = orgaoExpeditorDocumento;
	}
	/**
	 * @return the ufOrgaoExpedidorDocumento
	 */
	public String getUfOrgaoExpedidorDocumento() {
		return ufOrgaoExpedidorDocumento;
	}
	/**
	 * @param ufOrgaoExpedidorDocumento the ufOrgaoExpedidorDocumento to set
	 */
	public void setUfOrgaoExpedidorDocumento(String ufOrgaoExpedidorDocumento) {
		this.ufOrgaoExpedidorDocumento = ufOrgaoExpedidorDocumento;
	}
	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}
	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the dataEmissaoDocumento
	 */
	public String getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}
	
	/**
	 * @param dataEmissaoDocumento the dataEmissaoDocumento to set
	 */
	public void setDataEmissaoDocumento(String dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}
	
	/**
	 * @return the localData
	 */
	public String getLocalData() {
		return localData;
	}
	
	/**
	 * @param localData the localData to set
	 */
	public void setLocalData(String localData) {
		this.localData = localData;
	}
	
	/**
	 * @return the dataRelatorio
	 */
	public Date getDataRelatorio() {
		return dataRelatorio;
	}
	
	/**
	 * @param dataRelatorio the dataRelatorio to set
	 */
	public void setDataRelatorio(Date dataRelatorio) {
		this.dataRelatorio = dataRelatorio;
	}
}