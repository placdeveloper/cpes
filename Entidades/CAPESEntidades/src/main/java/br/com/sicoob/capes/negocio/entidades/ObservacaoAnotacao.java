package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * @author Diego.Rezende
 */
@Entity
@Table(name = "OBSERVACAOANOTACAO", schema = "CLI")
@OrdenacaoPadrao(colunas="DESCOBSERVACAOANOTACAO",descendente=false)
public class ObservacaoAnotacao extends CAPESEntidade<Short> {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2018475738871679508L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODOBSERVACAOANOTACAO")
	private Short codigo;
	
	/** O atributo sigla. */
	@Column(name="SIGLAOBSERVACAOANOTACAO")
	private String siglaObservacaoAnotacao;
	
	/** O atributo descricao. */
	@Column(name = "DESCOBSERVACAOANOTACAO", length = 50, nullable = false)
	private String descricao;
	
	/** O atributo tipoObservacaoAnotacao. */
	@ManyToOne
	@JoinColumn(name="CODTIPOOBSERVACAOANOTACAO")
	private TipoObservacaoAnotacao tipoObservacaoAnotacao;
	
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
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
	 * @return the tipoObservacaoAnotacao
	 */
	public TipoObservacaoAnotacao getTipoObservacaoAnotacao() {
		return tipoObservacaoAnotacao;
	}

	/**
	 * @param tipoObservacaoAnotacao the tipoObservacaoAnotacao to set
	 */
	public void setTipoObservacaoAnotacao(TipoObservacaoAnotacao tipoObservacaoAnotacao) {
		this.tipoObservacaoAnotacao = tipoObservacaoAnotacao;
	}

	/**
	 * Recupera o valor de siglaObservacaoAnotacao.
	 *
	 * @return o valor de siglaObservacaoAnotacao
	 */
	public String getSiglaObservacaoAnotacao() {
		return siglaObservacaoAnotacao;
	}

	/**
	 * Define o valor de siglaObservacaoAnotacao.
	 *
	 * @param siglaObservacaoAnotacao o novo valor de siglaObservacaoAnotacao
	 */
	public void setSiglaObservacaoAnotacao(String siglaObservacaoAnotacao) {
		this.siglaObservacaoAnotacao = siglaObservacaoAnotacao;
	}

	

}
