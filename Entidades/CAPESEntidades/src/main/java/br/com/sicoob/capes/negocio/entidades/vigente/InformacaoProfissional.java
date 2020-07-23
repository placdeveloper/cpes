/*
 * SICOOB
 * 
 * InformacaoProfissional.java(br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sicoob.capes.negocio.entidades.InformacaoProfissionalBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * @author Erico.Junior
 * 
 */
@Entity
@Table(name = "INFORMACAOPROFISSIONAL", schema = "CLI")
public class InformacaoProfissional extends InformacaoProfissionalBase implements Vigente, CadastroValidavel {

	/** Serial UID. */
	private static final long serialVersionUID = -5629243612022964795L;

	/** O atributo id informacao. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDINFORMACAOPROFISSIONAL")
	private Integer idInformacao;

	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/**
	 * Recupera id informacao.
	 * 
	 * @return id informacao
	 */
	public Integer getIdInformacao() {

		return idInformacao;
	}

	/**
	 * Preenche id informacao.
	 * 
	 * @param idInformacao
	 *            o novo id informacao
	 */
	public void setIdInformacao(Integer idInformacao) {

		this.idInformacao = idInformacao;
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

		return getIdInformacao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {

		setIdInformacao(id);
	}

}
