/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.Anotacao;

/**
 * DTO utilizado na consulta de anotações.
 * @author Erico.Junior
 */
public class ConsultaAnotacaoDTO extends ConsultaDto<Anotacao> {

	/** Serial UID. */
	private static final long serialVersionUID = 4656154311176360566L;
	
	/** O atributo anotacoesBaixadas. */
	private Boolean anotacoesBaixadas;

	/** O atributo dataFim. */
	private Date dataFim;

	/** O atributo dataInicio. */
	private Date dataInicio;

	/** O atributo idInstituicaoPessoa. */
	private Integer idInstituicaoPessoa;
	
	/** O atributo codigoCompartilhamento. */
	private Short codigoCompartilhamento;
	
	/**
	 * Recupera o valor de codigoCompartilhamento.
	 *
	 * @return o valor de codigoCompartilhamento
	 */
	public Short getCodigoCompartilhamento() {
		return codigoCompartilhamento;
	}

	/**
	 * Define o valor de codigoCompartilhamento.
	 *
	 * @param idSistemaCooperativo o novo valor de codigoCompartilhamento
	 */
	public void setCodigoCompartilhamento(Short idSistemaCooperativo) {
		this.codigoCompartilhamento = idSistemaCooperativo;
	}

	/**
	 * @return the anotacoesBaixadas
	 */
	public Boolean getAnotacoesBaixadas() {
		return anotacoesBaixadas;
	}

	/**
	 * @param anotacoesBaixadas the anotacoesBaixadas to set
	 */
	public void setAnotacoesBaixadas(Boolean anotacoesBaixadas) {
		this.anotacoesBaixadas = anotacoesBaixadas;
	}
	
	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicaoPessoa() {
		return idInstituicaoPessoa;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicaoPessoa(Integer idInstituicao) {
		this.idInstituicaoPessoa = idInstituicao;
	}

}
