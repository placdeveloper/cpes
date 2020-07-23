package br.com.sicoob.capes.negocio.entidades.bem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A classe que representa a entidade Tipo bem móvel.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPOBEMMOVEL")
public class TipoBemMovel extends CAPESEntidade<Short> {
	private static final long serialVersionUID = 8590051331458750517L;

	@Id
	@Column(name = "CODTIPOBEMMOVEL")
	private Short codigo;

	@Column(name = "DESCTIPOBEMMOVEL", length = 25)
	private String descricao;

	@Column(name = "BOLHABILITATIPOESTADOCONSERVACAO")
	private Boolean habilitaTipoEstadoConservacao = Boolean.FALSE;
	
	@Column(name = "BOLBEMCADASTROCOMPLETO")
	private Boolean possuiDadosAvancados = Boolean.TRUE;
	
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

	public Boolean getHabilitaTipoEstadoConservacao() {
		return habilitaTipoEstadoConservacao;
	}

	public void setHabilitaTipoEstadoConservacao(Boolean habilitaTipoEstadoConservacao) {
		this.habilitaTipoEstadoConservacao = habilitaTipoEstadoConservacao;
	}
	
	public Boolean getPossuiDadosAvancados() {
		return possuiDadosAvancados;
	}

	public void setPossuiDadosAvancados(Boolean possuiDadosAvancados) {
		this.possuiDadosAvancados = possuiDadosAvancados;
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
	public boolean equals(Object objeto) {
		return ReflexaoUtils.equals(this, objeto, "codigo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigo");
	}

}