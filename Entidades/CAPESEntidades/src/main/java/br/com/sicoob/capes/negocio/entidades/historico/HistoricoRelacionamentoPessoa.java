/*
 * SICOOB
 * 
 * HistoricoRelacionamentoPessoa.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoRelacionamentoPessoa)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * Entidade que representa o histório de relacionamento de pessoas
 * 
 * 24/08/2011
 * 
 * @author Rodrigo.Chaves
 */
@Entity
@Table(schema = "CLI", name = "HISTRELACIONAMENTOPESSOA")
@Inheritance(strategy = JOINED)
@Filters({ @Filter(name = "periodoHistorico"), @Filter(name = "periodoHistoricoAntiga"),@Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoRelacionamentoPessoa extends RelacionamentoPessoaBase implements Historico {

	/** Serial UID */
	private static final long serialVersionUID = 1097937233411924624L;

	/** O atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTRELACIONAMENTOPESSOA")
	private Long id;

	/** O atributo data hora fim. */
	@Column(name = "DATAHORAFIM", nullable = false)
	private Date dataHoraFim = new Date();
	
	/** O atributo id relacionamento. */
	@Column(name = "IDRELACIONAMENTOPESSOA", nullable = false)
	private Long idRelacionamento;
	
	/** O atributo poderes. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CLI", name = "HISTRELACIONAMENTOPESSOAPODER", 
			inverseJoinColumns =  @JoinColumn(name = "CODTIPOPODERRELACIONAMENTO"), 
			joinColumns = @JoinColumn(name = "IDHISTRELACIONAMENTOPESSOA"))
	private Set<TipoPoderRelacionamento> poderes;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Date getDataHoraFim() {
		return this.dataHoraFim;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	/**
	 * Recupera id relacionamento.
	 * 
	 * @return id relacionamento
	 */
	public Long getIdRelacionamento() {
		return idRelacionamento;
	}

	/**
	 * Preenche id relacionamento.
	 * 
	 * @param idRelacionamento
	 *            o novo id relacionamento
	 */
	public void setIdRelacionamento(Long idRelacionamento) {
		this.idRelacionamento = idRelacionamento;
	}

	/**
	 * Recupera poderes.
	 * 
	 * @return poderes
	 */
	public Set<TipoPoderRelacionamento> getPoderes() {
		return poderes;
	}

	/**
	 * Preenche poderes.
	 * 
	 * @param poderes
	 *            o novo poderes
	 */
	public void setPoderes(Set<TipoPoderRelacionamento> poderes) {
		this.poderes = poderes;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return idRelacionamento;
	}

	/**
	 * Recupera id usuario exclusao.
	 * 
	 * @return id usuario exclusao
	 */
	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}

}
