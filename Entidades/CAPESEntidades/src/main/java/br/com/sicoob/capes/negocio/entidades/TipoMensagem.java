package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;


/**
 * A Classe TipoMensagem.
 */
@Entity
@Table(name = "TIPOMENSAGEM", schema = "CLI")
public class TipoMensagem extends CAPESEntidade<Short>{

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2561584895004074491L;

	/** O atributo codTipoMensagem. */
	@Id
	@Column(name = "CODTIPOMENSAGEM")
	private Short codTipoMensagem;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOMENSAGEM")
	private String descTipoMensagem;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getCodTipoMensagem();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodTipoMensagem(id);
	}

	
	
	/**
	 * Recupera o valor de codTipoMensagem.
	 *
	 * @return o valor de codTipoMensagem
	 */
	public Short getCodTipoMensagem() {
		return codTipoMensagem;
	}

	/**
	 * Define o valor de codTipoMensagem.
	 *
	 * @param codTipoMensagem o novo valor de codTipoMensagem
	 */
	public void setCodTipoMensagem(Short codTipoMensagem) {
		this.codTipoMensagem = codTipoMensagem;
	}

	/**
	 * Recupera o valor de descTipoMensagem.
	 *
	 * @return o valor de descTipoMensagem
	 */
	public String getDescTipoMensagem() {
		return descTipoMensagem;
	}

	/**
	 * Define o valor de descTipoMensagem.
	 *
	 * @param descTipoMensagem o novo valor de descTipoMensagem
	 */
	public void setDescTipoMensagem(String descTipoMensagem) {
		this.descTipoMensagem = descTipoMensagem;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codTipoMensagem");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codTipoMensagem");
	}
	

}
