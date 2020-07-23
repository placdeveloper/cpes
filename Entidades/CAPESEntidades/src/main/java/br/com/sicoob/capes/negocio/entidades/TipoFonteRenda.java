/*
 * SICOOB
 * 
 * TipoFonteRenda.java(br.com.sicoob.capes.negocio.entidades.TipoFonteRenda)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavelLista;
import flexjson.JSON;

/**
 * Entidade que representa a tabela responsável por armazenar os tipos de fonte de renda.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name="TIPOFONTERENDA", schema = "CLI")
public class TipoFonteRenda extends CAPESEntidade<Short> 
	implements DominioReplicavelLista{

	/** Serial UID.*/
	private static final long serialVersionUID = 4380468310767404051L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOFONTERENDA")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOFONTERENDA")
	private String descricao;
	
	/** O atributo valorObrigatorio. */
	@Column(name = "BOLVALOROBRIGATORIO")
	private Boolean valorObrigatorio = Boolean.FALSE;

	/** O atributo tipos pessoa. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TIPOPESSOAFONTERENDA", schema="CLI" , 
			joinColumns = { @JoinColumn(name = "CODTIPOFONTERENDA") }, 
			inverseJoinColumns = { @JoinColumn(name = "CODTIPOPESSOA") })
	private Set<TipoPessoa> tiposPessoa;
	
	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * @return the tiposPessoa
	 */
	@JSON(include=false)	
	public Set<TipoPessoa> getTiposPessoa() {
		return tiposPessoa;
	}

	/**
	 * @param tiposPessoa the tiposPessoa to set
	 */
	public void setTiposPessoa(Set<TipoPessoa> tiposPessoa) {
		this.tiposPessoa = tiposPessoa;
	}	

	/**
	 * Verifica se é vinculado pessoa fisica.
	 * 
	 * @return true, se for vinculado pessoa fisica
	 */
	public boolean isVinculadoPessoaFisica(){
		if(getTiposPessoa() != null){
			for (TipoPessoa p : getTiposPessoa()) {
				if(p.getCodTipoPessoa().equals(Short.valueOf("0"))){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Verifica se é vinculado pessoa juridica.
	 * 
	 * @return true, se for vinculado pessoa juridica
	 */
	public boolean isVinculadoPessoaJuridica(){
		if(getTiposPessoa() != null){
			for (TipoPessoa p : getTiposPessoa()) {
				if(p.getCodTipoPessoa().equals(Short.valueOf("1"))){
					return true;
				}
			}
		}
		return false;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Short getId() {
		return getCodigo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodigo(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	public String getCodigoListaItem() {
		return String.valueOf(getCodigo());
	}

	/**
	 * Recupera o valor de valorObrigatorio.
	 *
	 * @return o valor de valorObrigatorio
	 */
	public Boolean getValorObrigatorio() {
		return valorObrigatorio;
	}

	/**
	 * Define o valor de valorObrigatorio.
	 *
	 * @param valorObrigatorio o novo valor de valorObrigatorio
	 */
	public void setValorObrigatorio(Boolean valorObrigatorio) {
		this.valorObrigatorio = valorObrigatorio;
	}

}