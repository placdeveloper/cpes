/*
 * SICOOB
 * 
 * Empreendimento.java(br.com.sicoob.capes.negocio.entidades.Empreendimento)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade que representa a tabela responsável por armazenar os empreendimentos.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name="EMPREENDIMENTO", schema = "CLI")
public class Empreendimento extends CAPESEntidade<Integer> {
	
	/** Serial UID. */
	private static final long serialVersionUID = 1780598930264271345L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODEMPREENDIMENTO")
	private Integer codigo;

	/** O atributo descricao. */
	@Column(name = "DESCEMPREENDIMENTO")
	private String descricao;

	/** O atributo unidade area. */
	@JoinColumn(name = "CODUNIDADEMEDIDAAREAQTDE")
	@ManyToOne
	private UnidadeMedida unidadeArea;

	/** O atributo unidade previsao. */
	@JoinColumn(name = "CODUNIDADEMEDIDAPREVPROD")
	@ManyToOne
	private UnidadeMedida unidadePrevisao;
	
	/** O atributo finalidade. */
	@JoinColumn(name = "CODFINALIDADEEMPREENDIMENTO")
	@ManyToOne
	private FinalidadeEmpreendimento finalidade;
	
	/** O atributo exige imovel. */
	@Column(name = "BOLEXIGEIMOVEL")
	private Boolean exigeImovel = Boolean.FALSE;
	
	/** O atributo exige area. */
	@Column(name = "BOLEXIGEAREA")
	private Boolean exigeArea = Boolean.FALSE;
	
	/** O atributo codigo bacen. */
	@Column(name = "CODEMPREENDIMENTOBACEN")
	private String codigoBacen;
	
	/** O atributo ativo. */
	@Column(name = "BOLATIVO")
	private Boolean ativo;
	
	/**
	 * Recupera codigo.
	 * 
	 * @return codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Preenche codigo.
	 * 
	 * @param codigo
	 *            o novo codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Preenche descricao.
	 * 
	 * @param descricao
	 *            o novo descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera unidade area.
	 * 
	 * @return unidade area
	 */
	public UnidadeMedida getUnidadeArea() {
		return unidadeArea;
	}

	/**
	 * Preenche unidade area.
	 * 
	 * @param unidadeArea
	 *            o novo unidade area
	 */
	public void setUnidadeArea(UnidadeMedida unidadeArea) {
		this.unidadeArea = unidadeArea;
	}

	/**
	 * Recupera unidade previsao.
	 * 
	 * @return unidade previsao
	 */
	public UnidadeMedida getUnidadePrevisao() {
		return unidadePrevisao;
	}

	/**
	 * Preenche unidade previsao.
	 * 
	 * @param unidadePrevisao
	 *            o novo unidade previsao
	 */
	public void setUnidadePrevisao(UnidadeMedida unidadePrevisao) {
		this.unidadePrevisao = unidadePrevisao;
	}

	/**
	 * Recupera finalidade.
	 * 
	 * @return finalidade
	 */
	public FinalidadeEmpreendimento getFinalidade() {
		return finalidade;
	}

	/**
	 * Preenche finalidade.
	 * 
	 * @param finalidade
	 *            o novo finalidade
	 */
	public void setFinalidade(FinalidadeEmpreendimento finalidade) {
		this.finalidade = finalidade;
	}

	/**
	 * Recupera exige imovel.
	 * 
	 * @return exige imovel
	 */
	public Boolean getExigeImovel() {
		return exigeImovel;
	}

	/**
	 * Preenche exige imovel.
	 * 
	 * @param exigeImovel
	 *            o novo exige imovel
	 */
	public void setExigeImovel(Boolean exigeImovel) {
		this.exigeImovel = exigeImovel;
	}

	/**
	 * Recupera exige area.
	 * 
	 * @return exige area
	 */
	public Boolean getExigeArea() {
		return exigeArea;
	}

	/**
	 * Preenche exige area.
	 * 
	 * @param exigeArea
	 *            o novo exige area
	 */
	public void setExigeArea(Boolean exigeArea) {
		this.exigeArea = exigeArea;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getCodigo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer identificador) {
		setCodigo(identificador);
	}

	/**
	 * Recupera codigo bacen.
	 * 
	 * @return codigo bacen
	 */
	public String getCodigoBacen() {
		return codigoBacen;
	}

	/**
	 * Preenche codigo bacen.
	 * 
	 * @param codigoBacen
	 *            o novo codigo bacen
	 */
	public void setCodigoBacen(String codigoBacen) {
		this.codigoBacen = codigoBacen;
	}

	/**
	 * Recupera ativo.
	 * 
	 * @return ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * Preenche ativo.
	 * 
	 * @param ativo
	 *            o novo ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
