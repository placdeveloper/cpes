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

/**
 * A classe que representa a entidade Tipo uso bem imóvel.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPOUSOBEM")
public class TipoUsoBemImovel extends CAPESEntidade<Short> {
	private static final long serialVersionUID = -4131778863269835180L;

	@Id
	@Column(name = "CODTIPOUSOBEM")
	private Short codigo;

	@Column(name = "DESCTIPOUSOBEM", length = 25)
	private String descricao;

	@JoinColumn(name = "CODTIPOLOCALIZACAOBEM", referencedColumnName = "CODTIPOLOCALIZACAOBEM")
	@ManyToOne
	private TipoLocalizacaoBem tipoLocalizacaoBem;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CLI", name = "TIPOBEMIMOVELTIPOUSO",
			joinColumns = { @JoinColumn(name = "CODTIPOUSOBEM") }, 
			inverseJoinColumns = { @JoinColumn(name = "CODTIPOBEMIMOVEL") })
	private Set<TipoBemImovel> tiposBemImovel;

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

	public TipoLocalizacaoBem getTipoLocalizacaoBem() {
		return tipoLocalizacaoBem;
	}

	public void setTipoLocalizacaoBem(TipoLocalizacaoBem tipoLocalizacaoBem) {
		this.tipoLocalizacaoBem = tipoLocalizacaoBem;
	}
	
	public Set<TipoBemImovel> getTiposBemImovel() {
		return tiposBemImovel;
	}

	public void setTipoBemImovel(Set<TipoBemImovel> tiposBemImovel) {
		this.tiposBemImovel = tiposBemImovel;
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