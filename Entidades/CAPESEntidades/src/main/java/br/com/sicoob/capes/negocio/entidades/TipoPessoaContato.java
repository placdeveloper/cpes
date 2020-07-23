package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A Classe TipoPessoaContato.
 */
@Entity
@Table(name="TIPOPESSOACONTATO", schema = "CLI")
public class TipoPessoaContato extends CAPESEntidade<Short> {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4674026908210223601L;

	/** O atributo codigo. */
	@Id
	@Column(name = "IDTIPOPESSOACONTATO")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCTIPOPESSOACONTATO")
	private String descricao;

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
	 * @return
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
