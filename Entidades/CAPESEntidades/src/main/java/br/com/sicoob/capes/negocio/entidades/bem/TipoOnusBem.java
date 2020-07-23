package br.com.sicoob.capes.negocio.entidades.bem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A classe que representa a entidade Tipo ônus bem.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPOONUSBEM")
public class TipoOnusBem extends CAPESEntidade<Short> {
	private static final long serialVersionUID = -1025953624838461755L;

	@Id
	@Column(name = "CODTIPOONUSBEM")
	private Short codigo;

	@Column(name = "DESCTIPOONUSBEM", length = 25)
	private String descricao;

	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public Short getId() {
		return getCodigo();
	}

	@Override
	public void setId(Short id) {
		setCodigo(id);
	}
	
	@Override
	public boolean equals(Object objeto) {
		return ReflexaoUtils.equals(this, objeto, "codigo");
	}
	
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigo");
	}

}