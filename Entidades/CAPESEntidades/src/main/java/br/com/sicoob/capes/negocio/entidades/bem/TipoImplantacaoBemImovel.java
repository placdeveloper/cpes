package br.com.sicoob.capes.negocio.entidades.bem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A classe que representa a entidade Tipo implantação bem imóvel.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPOIMPLANTACAOBEMIMOVEL")
public class TipoImplantacaoBemImovel extends CAPESEntidade<Short> {
	private static final long serialVersionUID = 1844788871081805130L;

	@Id
	@Column(name = "CODTIPOIMPLANTACAOBEMIMOVEL")
	private Short codigo;

	@Column(name = "DESCTIPOIMPLANTACAOBEMIMOVEL", length = 25)
	private String descricao;

	@Column(name = "BOLOBRIGATORIOAREA")
	private Boolean areaObrigatoria = Boolean.FALSE;

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

	public Boolean getAreaObrigatoria() {
		return areaObrigatoria;
	}

	public void setAreaObrigatoria(Boolean areaObrigatoria) {
		this.areaObrigatoria = areaObrigatoria;
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