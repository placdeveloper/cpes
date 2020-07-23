package br.com.sicoob.capes.negocio.entidades.bem.historico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.bem.TipoBemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoChassiBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoCorAutomovelBem;

/**
 * A classe que representa a entidade Histórico bem móvel.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "HISTBEMMOVEL")
public class HistoricoBemMovel extends HistoricoBem {
	private static final long serialVersionUID = -6096930602199770007L;

	@JoinColumn(name = "CODTIPOBEMMOVEL", referencedColumnName = "CODTIPOBEMMOVEL")
	@ManyToOne
	private TipoBemMovel tipoBem;

	@JoinColumn(name = "CODTIPOCHASSIBEM", referencedColumnName = "CODTIPOCHASSIBEM")
	@ManyToOne
	private TipoChassiBem tipoChassi;

	@Column(name = "NUMCHASSI", length = 50)
	private String numeroChassi;

	@Column(name = "NUMRENAVAN", length = 11)
	private String renavam;

	@Column(name = "DESCPLACA", length = 7)
	private String placa;

	@Column(name = "SIGLAUF", length = 2)
	private String uf;

	@Column(name = "ANOFABRICACAOAUTOMOVEL")
	private Short anoFabricacaoAutomovel;

	@Column(name = "ANOMODELOAUTOMOVEL")
	private Short anoModeloAutomovel;

	@Column(name = "DESCINCRICAOEMBARCACAO", length = 50)
	private String inscricaoEmbarcacao;

	@Column(name = "ANOCONSTRUCAOEMBARCACAO")
	private Short anoConstrucaoEmbarcacao;

	@Column(name = "DESCMATRICULAAERONAVE", length = 50)
	private String matriculaAeronave;
	
	@ManyToOne
	@JoinColumn(name = "CODTIPOCORAUTOMOVEL", referencedColumnName = "CODTIPOCORAUTOMOVEL")
	private TipoCorAutomovelBem tipoCorAutomovel;

	public TipoBemMovel getTipoBem() {
		return tipoBem;
	}

	public void setTipoBem(TipoBemMovel tipoBem) {
		this.tipoBem = tipoBem;
	}

	public TipoChassiBem getTipoChassi() {
		return tipoChassi;
	}

	public void setTipoChassi(TipoChassiBem tipoChassi) {
		this.tipoChassi = tipoChassi;
	}

	public String getNumeroChassi() {
		return numeroChassi;
	}

	public void setNumeroChassi(String numeroChassi) {
		this.numeroChassi = numeroChassi;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Short getAnoFabricacaoAutomovel() {
		return anoFabricacaoAutomovel;
	}

	public void setAnoFabricacaoAutomovel(Short anoFabricacaoAutomovel) {
		this.anoFabricacaoAutomovel = anoFabricacaoAutomovel;
	}

	public Short getAnoModeloAutomovel() {
		return anoModeloAutomovel;
	}

	public void setAnoModeloAutomovel(Short anoModeloAutomovel) {
		this.anoModeloAutomovel = anoModeloAutomovel;
	}

	public TipoCorAutomovelBem getTipoCorAutomovel() {
		return tipoCorAutomovel;
	}

	public void setTipoCorAutomovel(TipoCorAutomovelBem tipoCorAutomovel) {
		this.tipoCorAutomovel = tipoCorAutomovel;
	}

	public String getInscricaoEmbarcacao() {
		return inscricaoEmbarcacao;
	}

	public void setInscricaoEmbarcacao(String inscricaoEmbarcacao) {
		this.inscricaoEmbarcacao = inscricaoEmbarcacao;
	}

	public Short getAnoConstrucaoEmbarcacao() {
		return anoConstrucaoEmbarcacao;
	}

	public void setAnoConstrucaoEmbarcacao(Short anoConstrucaoEmbarcacao) {
		this.anoConstrucaoEmbarcacao = anoConstrucaoEmbarcacao;
	}

	public String getMatriculaAeronave() {
		return matriculaAeronave;
	}

	public void setMatriculaAeronave(String matriculaAeronave) {
		this.matriculaAeronave = matriculaAeronave;
	}

}