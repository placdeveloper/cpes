package br.com.sicoob.capes.negocio.entidades.bem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A classe que representa a entidade Tipos de serviço condominial do bem imóvel.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPOSERVICOCONDOMINIALBEMIMOVEL")
public class TipoServicoCondominialBemImovel extends CAPESEntidade<Short> {
	private static final long serialVersionUID = 7852006851561213147L;

	@Id
	@Column(name = "CODTIPOSERVICOCONDOMINIALBEMIMOVEL")
	private Short codigo;

	@Column(name = "DESCTIPOSERVICOCONDOMINIALBEMIMOVEL", length = 25)
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