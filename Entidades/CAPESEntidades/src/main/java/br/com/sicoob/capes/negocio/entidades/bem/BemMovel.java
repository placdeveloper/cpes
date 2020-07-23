package br.com.sicoob.capes.negocio.entidades.bem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.NaoVerificarGestorResponsavel;

/**
 * Classe que representa a entidade de Bem móvel
 * 
 * @author bruno.carneiro
 */
@Entity
@Table(schema = "CLI", name = "BEMMOVEL")
@PrimaryKeyJoinColumn(referencedColumnName = "IDBEM")
@CamposAutorizacao(id = "idBem", camposExibicao = {
		@CampoAutorizacao(propriedade = "tipoBem.descricao", label = "TIPO DO BEM", ordenacao = 6),
		@CampoAutorizacao(propriedade = "tipoChassi.descricao", label = "TIPO DO CHASSI", ordenacao = 7),
		@CampoAutorizacao(propriedade = "numeroChassi", label = "NÚMERO DO CHASSI", ordenacao = 8),
		@CampoAutorizacao(propriedade = "renavam", label = "RENAVAM", ordenacao = 9),
		@CampoAutorizacao(propriedade = "placa", label = "PLACA", ordenacao = 10),
		@CampoAutorizacao(propriedade = "uf", label = "UF", ordenacao = 11),
		@CampoAutorizacao(propriedade = "anoFabricacaoAutomovel", label = "ANO FABRICAÇÃO AUTOMÓVEL", ordenacao = 12),
		@CampoAutorizacao(propriedade = "anoModeloAutomovel", label = "ANO MODELO AUTOMÓVEL", ordenacao = 13),
		@CampoAutorizacao(propriedade = "tipoCorAutomovel.descricao", label = "TIPO COR AUTOMÓVEL", ordenacao = 14),
		@CampoAutorizacao(propriedade = "inscricaoEmbarcacao", label = "INSCRIÇÃO DA EMBARCAÇÃO", ordenacao = 15),
		@CampoAutorizacao(propriedade = "anoConstrucaoEmbarcacao", label = "ANO CONSTRUÇÃO DA EMBARCAÇÃO", ordenacao = 16),
		@CampoAutorizacao(propriedade = "matriculaAeronave", label = "MATRÍCULA DA AERONAVE", ordenacao = 17),
		@CampoAutorizacao(propriedade = "anoConstrucaoAeronave", label = "ANO CONSTRUÇÃO AERONAVE", ordenacao = 18)
})

@NaoVerificarGestorResponsavel
public class BemMovel extends Bem {
	private static final long serialVersionUID = 8849310204761165487L;

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
	
	@Column(name = "ANOCONSTRUCAOAERONAVE")
	private Short anoConstrucaoAeronave;
	
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
	
	public Short getAnoConstrucaoAeronave() {
		return anoConstrucaoAeronave;
	}

	public void setAnoConstrucaoAeronave(Short anoConstrucaoAeronave) {
		this.anoConstrucaoAeronave = anoConstrucaoAeronave;
	}

	public TipoCorAutomovelBem getTipoCorAutomovel() {
		return tipoCorAutomovel;
	}

	public void setTipoCorAutomovel(TipoCorAutomovelBem tipoCorAutomovel) {
		this.tipoCorAutomovel = tipoCorAutomovel;
	}

}