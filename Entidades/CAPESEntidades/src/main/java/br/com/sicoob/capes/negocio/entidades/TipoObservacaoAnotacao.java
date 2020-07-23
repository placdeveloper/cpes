package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;

/**
 * @author Diego.Rezende
 */
@Entity
@Table(name = "TIPOOBSERVACAOANOTACAO", schema = "CLI")
@OrdenacaoPadrao(colunas="DESCTIPOOBSERVACAOANOTACAO",descendente=false)
public class TipoObservacaoAnotacao extends CAPESEntidade<Short> {
	
	/** Serial UID.*/
	private static final long serialVersionUID = 4228106230798408654L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOOBSERVACAOANOTACAO")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCTIPOOBSERVACAOANOTACAO", length = 50, nullable = false)
	private String descricao;
	
	/** O atributo descricao. */
	@Column(name = "BOLOBSERVACAOPRECADASTRADA")
	private Boolean observacaoPreCadastrada;
	
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
	 * @return the observacaoPreCadastrada
	 */
	public Boolean getObservacaoPreCadastrada() {
		return observacaoPreCadastrada;
	}

	/**
	 * @param observacaoPreCadastrada the observacaoPreCadastrada to set
	 */
	public void setObservacaoPreCadastrada(Boolean observacaoPreCadastrada) {
		this.observacaoPreCadastrada = observacaoPreCadastrada;
	}
}
