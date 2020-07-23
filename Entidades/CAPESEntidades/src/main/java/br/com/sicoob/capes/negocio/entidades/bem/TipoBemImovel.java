package br.com.sicoob.capes.negocio.entidades.bem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A classe que representa a entidade Tipo bem imóvel.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "TIPOBEMIMOVEL")
public class TipoBemImovel extends CAPESEntidade<Short> {
	private static final long serialVersionUID = -5408684708038642993L;

	@Id
	@Column(name = "CODTIPOBEMIMOVEL")
	private Short codigo;

	@Column(name = "DESCTIPOBEMIMOVEL", length = 25)
	private String descricao;

	@Column(name = "BOLHABILITATIPOESTADOCONSERVACAO")
	private Boolean habilitaTipoEstadoConservacao = Boolean.FALSE;

	@Column(name = "BOLHABILITATIPOPADRAOACABAMENTO")
	private Boolean habilitaTipoPadraoAcabamento = Boolean.FALSE;

	@Column(name = "BOLHABILITATIPOSERVICOCONDOMINIAL")
	private Boolean habilitaTipoServicoCondominial = Boolean.FALSE;

	@Column(name = "BOLHABILITAAREAPRIVATIVA")
	private Boolean habilitaAreaPrivativa = Boolean.FALSE;

	@Column(name = "BOLHABILITAQTDDORMITORIO")
	private Boolean habilitaQuantidadeDormitorio = Boolean.FALSE;

	@Column(name = "BOLHABILITAQTDVAGAGARAGEM")
	private Boolean habilitaQuantidadeVagasGaragem = Boolean.FALSE;

	@Column(name = "BOLHABILITAAREATERRENO")
	private Boolean habilitaAreaTerreno = Boolean.FALSE;
	
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

	public Boolean getHabilitaTipoPadraoAcabamento() {
		return habilitaTipoPadraoAcabamento;
	}

	public void setHabilitaTipoPadraoAcabamento(Boolean habilitaTipoPadraoAcabamento) {
		this.habilitaTipoPadraoAcabamento = habilitaTipoPadraoAcabamento;
	}

	public Boolean getHabilitaTipoServicoCondominial() {
		return habilitaTipoServicoCondominial;
	}

	public void setHabilitaTipoServicoCondominial(Boolean habilitaTipoServicoCondominial) {
		this.habilitaTipoServicoCondominial = habilitaTipoServicoCondominial;
	}

	public Boolean getHabilitaAreaPrivativa() {
		return habilitaAreaPrivativa;
	}

	public void setHabilitaAreaPrivativa(Boolean habilitaaAreaPrivativa) {
		this.habilitaAreaPrivativa = habilitaaAreaPrivativa;
	}

	public Boolean getHabilitaQuantidadeDormitorio() {
		return habilitaQuantidadeDormitorio;
	}

	public void setHabilitaQuantidadeDormitorio(Boolean habilitaaQuantidadeDormitorio) {
		this.habilitaQuantidadeDormitorio = habilitaaQuantidadeDormitorio;
	}

	public Boolean getHabilitaQuantidadeVagasGaragem() {
		return habilitaQuantidadeVagasGaragem;
	}

	public void setHabilitaQuantidadeVagasGaragem(Boolean habilitaaQuantidadeVagasGaragem) {
		this.habilitaQuantidadeVagasGaragem = habilitaaQuantidadeVagasGaragem;
	}

	public Boolean getHabilitaaAreaTerreno() {
		return habilitaAreaTerreno;
	}

	public void setHabilitaaAreaTerreno(Boolean habilitaaAreaTerreno) {
		this.habilitaAreaTerreno = habilitaaAreaTerreno;
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