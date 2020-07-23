/*
 * SICOOB
 * 
 * BemOnusBase.java(br.com.sicoob.capes.negocio.entidades.BemOnusBase)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * Classe que representa o bem onus
 * 
 * @author Juan.Damasceno
 * 
 */
@MappedSuperclass
public abstract class BemOnusBase extends CAPESEntidade<Integer> implements Ibem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo bem. */
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "IDBEMPESSOA")
	private Bem bem;

	/** O atributo numero grau. */
	@Column(name = "NUMGRAU")
	private Integer numeroGrau;

	/** O atributo descricao. */
	@Column(name = "DESCONUS", length = 200)
	private String descricao;

	/** O atributo nome instituicao credora. */
	@Column(name = "NOMEINSTITUICAOCREDONUS", length = 100)
	private String nomeInstituicaoCredora;

	/** O atributo valor. */
	@Column(name = "VALORONUSBEM", precision = 19, scale = 4)
	private BigDecimal valor;

	/** O atributo data prevista liberacao. */
	@Column(name = "DATAPREVISTALIBERACAOONUS")
	private DateTimeDB dataPrevistaLiberacao;

	/** O atributo data hora inicio. */
	private DateTimeDB dataHoraInicio;

	/**
	 * @return the bem
	 */
	public Bem getBem() {

		return bem;
	}

	/**
	 * @param bem
	 *            the bem to set
	 */
	public void setBem(Bem bem) {

		this.bem = bem;
	}

	/**
	 * @return the numeroGrau
	 */
	public Integer getNumeroGrau() {

		return numeroGrau;
	}

	/**
	 * @param numeroGrau
	 *            the numeroGrau to set
	 */
	public void setNumeroGrau(Integer numeroGrau) {

		this.numeroGrau = numeroGrau;
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
	 * @return the nomeInstituicaoCredora
	 */
	public String getNomeInstituicaoCredora() {

		return nomeInstituicaoCredora;
	}

	/**
	 * @param nomeInstituicaoCredora
	 *            the nomeInstituicaoCredora to set
	 */
	public void setNomeInstituicaoCredora(String nomeInstituicaoCredora) {

		this.nomeInstituicaoCredora = nomeInstituicaoCredora;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {

		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {

		this.valor = valor;
	}

	/**
	 * @return the dataPrevistaLiberacao
	 */
	public DateTimeDB getDataPrevistaLiberacao() {

		return dataPrevistaLiberacao;
	}

	/**
	 * @param dataPrevistaLiberacao
	 *            the dataPrevistaLiberacao to set
	 */
	public void setDataPrevistaLiberacao(DateTimeDB dataPrevistaLiberacao) {

		this.dataPrevistaLiberacao = dataPrevistaLiberacao;
	}

	/**
	 * @return the dataHoraInicio
	 */
	public DateTimeDB getDataHoraInicio() {

		return dataHoraInicio;
	}

	/**
	 * @param dataHoraInicio
	 *            the dataHoraInicio to set
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {

		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {

		return ReflexaoUtils.hashCode(this, "bem", "numeroGrau", "descricao", "nomeInstituicaoCredora", "valor",
				"dataPrevistaLiberacao", "dataHoraInicio");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {

		return ReflexaoUtils.equals(this, obj, "bem", "numeroGrau", "descricao", "nomeInstituicaoCredora", "valor",
				"dataPrevistaLiberacao", "dataHoraInicio");
	}
}