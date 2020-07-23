/*
 * SICOOB
 * 
 * AnotacaoExternaDTO.java(br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

import java.util.Date;
import java.util.List;

/**
 * The Class AnotacaoExternaDTO.
 */
public class AnotacaoExternaDTO extends AnotacaoDTO {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -6398904935521763049L;

	/** O atributo num cpf cnpj. */
	private String numCpfCnpj;
	
	/** O atributo nome receita. */
	private String nomeReceita;
	
	/** O atributo nome orgao origem. */
	private String nomeOrgaoOrigem;
	
	/** O atributo nome tipo consulta. */
	private String nomeTipoConsulta;
	
	/** O atributo percentual processado bacen. */
	private Double percentualProcessadoBacen;
	
	/** O atributo data base bacen. */
	private Date dataBaseBacen;
	
	/** O atributo id consulta. */
	private Integer idConsulta;

	/** O atributo lista anotacoes. */
	private List<AnotacaoInformacaoDTO> listaAnotacoes;

	/**
	 * Recupera num cpf cnpj.
	 * 
	 * @return num cpf cnpj
	 */
	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}

	/**
	 * Preenche num cpf cnpj.
	 * 
	 * @param numCpfCnpj
	 *            o novo num cpf cnpj
	 */
	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}
	
	/**
	 * Recupera nome receita.
	 * 
	 * @return nome receita
	 */
	public String getNomeReceita() {
		return nomeReceita;
	}

	/**
	 * Preenche nome receita.
	 * 
	 * @param nomeReceita
	 *            o novo nome receita
	 */
	public void setNomeReceita(String nomeReceita) {
		this.nomeReceita = nomeReceita;
	}

	/**
	 * Recupera nome orgao origem.
	 * 
	 * @return nome orgao origem
	 */
	public String getNomeOrgaoOrigem() {
		return nomeOrgaoOrigem;
	}

	/**
	 * Preenche nome orgao origem.
	 * 
	 * @param nomeOrgaoOrigem
	 *            o novo nome orgao origem
	 */
	public void setNomeOrgaoOrigem(String nomeOrgaoOrigem) {
		this.nomeOrgaoOrigem = nomeOrgaoOrigem;
	}

	/**
	 * Recupera nome tipo consulta.
	 * 
	 * @return nome tipo consulta
	 */
	public String getNomeTipoConsulta() {
		return nomeTipoConsulta;
	}

	/**
	 * Preenche nome tipo consulta.
	 * 
	 * @param nomeTipoConsulta
	 *            o novo nome tipo consulta
	 */
	public void setNomeTipoConsulta(String nomeTipoConsulta) {
		this.nomeTipoConsulta = nomeTipoConsulta;
	}
	
	/**
	 * Recupera percentual processado bacen.
	 * 
	 * @return percentual processado bacen
	 */
	public Double getPercentualProcessadoBacen() {
		return percentualProcessadoBacen;
	}

	/**
	 * Preenche percentual processado bacen.
	 * 
	 * @param percentualProcessadoBacen
	 *            o novo percentual processado bacen
	 */
	public void setPercentualProcessadoBacen(Double percentualProcessadoBacen) {
		this.percentualProcessadoBacen = percentualProcessadoBacen;
	}

	/**
	 * Recupera data base bacen.
	 * 
	 * @return data base bacen
	 */
	public Date getDataBaseBacen() {
		return dataBaseBacen;
	}

	/**
	 * Preenche data base bacen.
	 * 
	 * @param dataBaseBacen
	 *            o novo data base bacen
	 */
	public void setDataBaseBacen(Date dataBaseBacen) {
		this.dataBaseBacen = dataBaseBacen;
	}

	/**
	 * Recupera id consulta.
	 * 
	 * @return id consulta
	 */
	public Integer getIdConsulta() {
		return idConsulta;
	}

	/**
	 * Preenche id consulta.
	 * 
	 * @param idConsulta
	 *            o novo id consulta
	 */
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	/**
	 * Recupera lista anotacoes.
	 * 
	 * @return lista anotacoes
	 */
	public List<AnotacaoInformacaoDTO> getListaAnotacoes() {
		return listaAnotacoes;
	}

	/**
	 * Preenche lista anotacoes.
	 * 
	 * @param listaAnotacoes
	 *            o novo lista anotacoes
	 */
	public void setListaAnotacoes(List<AnotacaoInformacaoDTO> listaAnotacoes) {
		this.listaAnotacoes = listaAnotacoes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "\"" + getNumCpfCnpj() + "\": " + getNomeTipoConsulta() + "(" + getNomeOrgaoOrigem() + ")";
	}
	
}