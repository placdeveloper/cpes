package br.com.sicoob.capes.negocio.entidades.bem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A classe que representa a entidade Tipo padrão acabamento bem imóvel.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPOPADRAOACABAMENTOBEMIMOVEL")
public class TipoPadraoAcabamentoBemImovel extends CAPESEntidade<Short> {
	private static final long serialVersionUID = 5689287869187338362L;

	@Id
	@Column(name = "CODTIPOPADRAOACABAMENTOBEMIMOVEL")
	private Short codigo;

	@Column(name = "DESCTIPOPADRAOACABAMENTOBEMIMOVEL", length = 25)
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