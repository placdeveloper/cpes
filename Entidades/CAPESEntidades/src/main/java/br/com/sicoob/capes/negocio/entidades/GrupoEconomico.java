/*
 * SICOOB
 * 
 * GrupoEconomico.java(br.com.sicoob.capes.negocio.entidades.GrupoEconomico)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import flexjson.JSON;

/**
 * Classe que responsavel por armarzenar as informacoes de GrupoEconomico.
 * 
 * @author juan.damasceno
 * 
 */
@Entity
@Table(name = "GRUPOECONOMICO", schema = "CLI")
@SQLDelete(sql = "UPDATE CLI.GRUPOECONOMICO SET DATAHORAEXCLUSAO = CURRENT TIMESTAMP WHERE IDGRUPOECONOMICO = ?")
@EntityListeners(ReplicacaoListener.class)
public class GrupoEconomico extends CAPESEntidade<Integer> implements
		Replicavel {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** O atributo id grupo economico. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGRUPOECONOMICO")
	private Integer idGrupoEconomico;

	/** O atributo descricao. */
	@Column(name = "DESCGRUPOECONOMICO", length = 100, nullable = false)
	private String descricao;

	/** O atributo id instituicao. */
	@Column(name = "IDINSTITUICAO", precision = Integer.MAX_VALUE, scale = 0, nullable = false)
	private Integer idInstituicao;

	/** O atributo data hora cadastro. */
	@Column(name = "DATAHORACADASTRO", nullable = false, updatable = false)
	private DateTimeDB dataHoraCadastro = new DateTimeDB();

	/** O atributo data hora exclusao. */
	@Column(name = "DATAHORAEXCLUSAO")
	private DateTimeDB dataHoraExclusao;

	/** O atributo integrantes. */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "grupoEconomico")
	private Set<GrupoEconomicoPessoa> integrantes;

	/**
	 * Cria uma nova instância de grupo economico.
	 */
	public GrupoEconomico() {
		super();
	}
	
	/**
	 * Cria uma nova instância de grupo economico.
	 * 
	 * @param idGrupoEconomico
	 *            the id grupo economico
	 * @param descricao
	 *            the descricao
	 */
	public GrupoEconomico(Integer idGrupoEconomico, String descricao) {
		super();
		this.idGrupoEconomico = idGrupoEconomico;
		this.descricao = descricao;
	}
	
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao
	 *            the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the idGrupoEconomico
	 */
	public Integer getIdGrupoEconomico() {
		return idGrupoEconomico;
	}

	/**
	 * @param idGrupoEconomico
	 *            the idGrupoEconomico to set
	 */
	public void setIdGrupoEconomico(Integer idGrupoEconomico) {
		this.idGrupoEconomico = idGrupoEconomico;
	}

	/**
	 * @return the integrantes
	 */
	public Set<GrupoEconomicoPessoa> getIntegrantes() {
		return integrantes;
	}

	/**
	 * @param integrantes
	 *            the integrantes to set
	 */
	public void setIntegrantes(Set<GrupoEconomicoPessoa> integrantes) {
		this.integrantes = integrantes;
	}

	/**
	 * @return the dataHoraCadastro
	 */
	public DateTimeDB getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	/**
	 * @param dataHoraCadastro
	 *            the dataHoraCadastro to set
	 */
	public void setDataHoraCadastro(DateTimeDB dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	/**
	 * @return the dataHoraExclusao
	 */
	public DateTimeDB getDataHoraExclusao() {
		return dataHoraExclusao;
	}

	/**
	 * @param dataHoraExclusao
	 *            the dataHoraExclusao to set
	 */
	public void setDataHoraExclusao(DateTimeDB dataHoraExclusao) {
		this.dataHoraExclusao = dataHoraExclusao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdGrupoEconomico();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdGrupoEconomico(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idGrupoEconomico", "descricao", "idInstituicao");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idGrupoEconomico", "descricao", "idInstituicao");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@JSON(include = false)
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		throw new UnsupportedOperationException();
	}

}
