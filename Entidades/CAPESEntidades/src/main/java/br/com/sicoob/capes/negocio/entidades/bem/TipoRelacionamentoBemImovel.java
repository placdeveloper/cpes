package br.com.sicoob.capes.negocio.entidades.bem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A classe que representa a entidade Tipo relacionamento bem imóvel
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPORELACIONAMENTOBEMIMOVEL")
public class TipoRelacionamentoBemImovel extends CAPESEntidade<Short> {
	private static final long serialVersionUID = 7887863521205044657L;

	@Id
	@Column(name = "CODTIPORELACIONAMENTOBEMIMOVEL")
	private Short codigo;

	@Column(name = "DESCTIPORELACIONAMENTOBEMIMOVEL", length = 25)
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
}