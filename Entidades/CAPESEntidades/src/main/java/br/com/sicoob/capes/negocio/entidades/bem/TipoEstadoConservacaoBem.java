package br.com.sicoob.capes.negocio.entidades.bem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A classe que representa a entidade Tipo conservação bem.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPOESTADOCONSERVACAOBEM")
public class TipoEstadoConservacaoBem extends CAPESEntidade<Short> {
	private static final long serialVersionUID = -8530903334077998752L;

	@Id
	@Column(name = "CODTIPOESTADOCONSERVACAOBEM")
	private Short codigo;

	@Column(name = "DESCTIPOESTADOCONSERVACAOBEM", length = 25)
	private String descricao;

	@Column(name = "BOLBEMIMOVEL")
	private Boolean bemImovel = Boolean.FALSE;

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

	public Boolean getBemImovel() {
		return bemImovel;
	}

	public void setBemImovel(Boolean bemImovel) {
		this.bemImovel = bemImovel;
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