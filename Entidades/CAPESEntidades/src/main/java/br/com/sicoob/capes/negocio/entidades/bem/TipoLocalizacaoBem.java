package br.com.sicoob.capes.negocio.entidades.bem;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;

/**
 * A classe que representa a entidade Tipo localização bem.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPOLOCALIZACAOBEM")
public class TipoLocalizacaoBem extends CAPESEntidade<Short> {
	private static final long serialVersionUID = -3448789130141304996L;

	@Id
	@Column(name = "CODTIPOLOCALIZACAOBEM")
	private Short codigo;

	@Column(name = "DESCTIPOLOCALIZACAOBEM", length = 25)
	private String descricao;

	@JoinColumn(name = "CODTIPOCLASSIFICACAOBEM", referencedColumnName = "CODTIPOCLASSIFICACAOBEM")
	@ManyToOne
	private TipoClassificacaoBem tipoClassificacaoBem;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CLI", name = "TIPOLOCALIZACAOBEMUNIDADEMEDIDA",
			joinColumns = { @JoinColumn(name = "CODTIPOLOCALIZACAOBEM") }, 
			inverseJoinColumns = { @JoinColumn(name = "CODUNIDADEMEDIDA") })
	private Set<UnidadeMedida> unidadesMedida;

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

	public TipoClassificacaoBem getTipoClassificacaoBem() {
		return tipoClassificacaoBem;
	}

	public void setTipoClassificacaoBem(TipoClassificacaoBem tipoClassificacaoBem) {
		this.tipoClassificacaoBem = tipoClassificacaoBem;
	}
	
	public Set<UnidadeMedida> getUnidadesMedida() {
		return unidadesMedida;
	}

	public void setUnidadesMedida(Set<UnidadeMedida> unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
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