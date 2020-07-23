/*
 * SICOOB
 * 
 * GrupoEconomicoPessoa.java(br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;

/**
 * The Class GrupoEconomicoPessoa.
 */
@Entity
@Table(schema = "CLI", name = "GRUPOECONOMICOPESSOA")
@Inheritance(strategy = JOINED)
@EntityListeners(ReplicacaoListener.class)
public class GrupoEconomicoPessoa extends GrupoEconomicoPessoaBase implements Vigente {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 7944376121550569339L;
	
	/** O atributo id grupo economico pessoa. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGRUPOECONOMICOPESSOA")
	private Integer idGrupoEconomicoPessoa;

	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;
	
	/**
	 * @return the idGrupoEconomicoPessoa
	 */
	public Integer getIdGrupoEconomicoPessoa() {
		return idGrupoEconomicoPessoa;
	}

	/**
	 * @param id the idGrupoEconomicoPessoa to set
	 */
	public void setIdGrupoEconomicoPessoa(Integer idGrupoEconomicoPessoa) {
		this.idGrupoEconomicoPessoa = idGrupoEconomicoPessoa;
	}

	/**
	 * Recupera em alteracao.
	 * 
	 * @return em alteracao
	 */
	public Boolean getEmAlteracao() {
		return Boolean.FALSE;
	}

	/**
	 * Preenche em alteracao.
	 * 
	 * @param emAlteracao
	 *            o novo em alteracao
	 */
	public void setEmAlteracao(Boolean emAlteracao) {
	}

	/** 
	 * {@inheritDoc}
	 */
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdGrupoEconomicoPessoa();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdGrupoEconomicoPessoa(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idGrupoEconomicoPessoa", "grupoEconomico",
				"pessoaInstituicao");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idGrupoEconomicoPessoa", "grupoEconomico",
				"pessoaInstituicao");
	}

}
