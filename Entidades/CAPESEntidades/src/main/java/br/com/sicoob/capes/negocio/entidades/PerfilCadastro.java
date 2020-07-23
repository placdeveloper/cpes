/*
 * SICOOB
 * 
 * PerfilCadastro.java(br.com.sicoob.capes.negocio.entidades.PerfilCadastro)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entidade para o perfil do cadastro.
 * @author Isaac.Pessoa
 */
@Entity
@Table(name="PERFILCADASTRO", schema = "CLI")
public class PerfilCadastro extends CAPESEntidade<Short> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Serial UID.*/
	

	@Id
	@Column(name = "CODPERFILCADASTRO")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCPERFILCADASTRO")
	private String descricao;

	/** O atributo ordem. */
	@Column(name = "NUMORDEM")
	private Short ordem;
	
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
	 * @return the ordem
	 */
	public Short getOrdem() {
		return ordem;
	}

	/**
	 * @param ordem the ordem to set
	 */
	public void setOrdem(Short ordem) {
		this.ordem = ordem;
	}

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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.descricao != null ? this.descricao : "";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PerfilCadastro other = (PerfilCadastro) obj;
		if (codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!codigo.equals(other.codigo)) {
			return false;
		}
		return true;
	}
	
	
	
}
