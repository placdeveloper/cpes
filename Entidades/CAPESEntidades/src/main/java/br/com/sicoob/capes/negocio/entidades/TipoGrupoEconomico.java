package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Entity
@Table(name = "TIPOGRUPOECONOMICO", schema = "CLI")
public class TipoGrupoEconomico extends CAPESEntidade<Short> {

	private static final long serialVersionUID = -9111750544534711031L;

	@Id
	@Column(name = "CODTIPOGRUPOECONOMICO")
	private Short codigo;

	@Column(name = "DESCTIPOGRUPOECONOMICO", length = 100, nullable = false)
	private String descricao;

	/**
	 * Contrutor default
	 */
	public TipoGrupoEconomico() {
	}

	/**
	 * 
	 * @param codigo
	 */
	public TipoGrupoEconomico(Short codigo) {
		this.codigo = codigo;
	}

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

}
