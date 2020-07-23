/*
 * SICOOB
 * 
 * DadosConfiguracaoFluxo.java(br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The Class DadosConfiguracaoFluxo.
 */
@Entity
@org.hibernate.annotations.Immutable
@Table(schema = "CLI", name = "DADOSCONFIGURACAOFLUXO", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"BOLRESPONSAVEL", "BOLGESTOR", "BOLDOCUMENTO" }) })
public class DadosConfiguracaoFluxo extends CAPESEntidade<Short> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 2546335296162964236L;

	/** O atributo id dados configuracao fluxo. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDDADOSCONFIGURACAOFLUXO")
	private Short idDadosConfiguracaoFluxo;

	/** O atributo is responsavel. */
	@Column(name = "BOLRESPONSAVEL", nullable = false)
	private Boolean isResponsavel;

	/** O atributo is gestor. */
	@Column(name = "BOLGESTOR", nullable = false)
	private Boolean isGestor;

	/** O atributo possui documento. */
	@Column(name = "BOLDOCUMENTO", nullable = false)
	private Boolean possuiDocumento;

	/** O atributo sigla processo. */
	@Column(name = "SIGLAPROCESSO", nullable = false, length = 40)
	private String siglaProcesso;

	/** O atributo instituicao destino. */
	@AttributeOverrides({
		@AttributeOverride(name = "idInstituicao", column = @Column(name = "IDINSTITUICAODESTINO")),
		@AttributeOverride(name = "idUnidadeInst", column = @Column(name = "IDUNIDADEINSTDESTINO")) })
	private Instituicao instituicaoDestino;
	
	/**
	 * Recupera id dados configuracao fluxo.
	 * 
	 * @return id dados configuracao fluxo
	 */
	public Short getIdDadosConfiguracaoFluxo() {
		return idDadosConfiguracaoFluxo;
	}

	/**
	 * Preenche id dados configuracao fluxo.
	 * 
	 * @param idDadosConfiguracaoFluxo
	 *            o novo id dados configuracao fluxo
	 */
	public void setIdDadosConfiguracaoFluxo(Short idDadosConfiguracaoFluxo) {
		this.idDadosConfiguracaoFluxo = idDadosConfiguracaoFluxo;
	}

	/**
	 * Recupera sigla processo.
	 * 
	 * @return sigla processo
	 */
	public String getSiglaProcesso() {
		return siglaProcesso;
	}

	/**
	 * Recupera checks if is responsavel.
	 * 
	 * @return checks if is responsavel
	 */
	public Boolean getIsResponsavel() {
		return isResponsavel;
	}

	/**
	 * Preenche checks if is responsavel.
	 * 
	 * @param isResponsavel
	 *            o novo checks if is responsavel
	 */
	public void setIsResponsavel(Boolean isResponsavel) {
		this.isResponsavel = isResponsavel;
	}

	/**
	 * Recupera checks if is gestor.
	 * 
	 * @return checks if is gestor
	 */
	public Boolean getIsGestor() {
		return isGestor;
	}

	/**
	 * Preenche checks if is gestor.
	 * 
	 * @param isGestor
	 *            o novo checks if is gestor
	 */
	public void setIsGestor(Boolean isGestor) {
		this.isGestor = isGestor;
	}

	/**
	 * Recupera possui documento.
	 * 
	 * @return possui documento
	 */
	public Boolean getPossuiDocumento() {
		return possuiDocumento;
	}

	/**
	 * Preenche possui documento.
	 * 
	 * @param possuiDocumento
	 *            o novo possui documento
	 */
	public void setPossuiDocumento(Boolean possuiDocumento) {
		this.possuiDocumento = possuiDocumento;
	}

	/**
	 * Preenche sigla processo.
	 * 
	 * @param siglaProcesso
	 *            o novo sigla processo
	 */
	public void setSiglaProcesso(String siglaProcesso) {
		this.siglaProcesso = siglaProcesso;
	}

	/**
	 * Recupera instituicao destino.
	 * 
	 * @return instituicao destino
	 */
	public Instituicao getInstituicaoDestino() {
		return instituicaoDestino;
	}

	/**
	 * Preenche instituicao destino.
	 * 
	 * @param instituicaoDestino
	 *            o novo instituicao destino
	 */
	public void setInstituicaoDestino(Instituicao instituicaoDestino) {
		this.instituicaoDestino = instituicaoDestino;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getIdDadosConfiguracaoFluxo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setIdDadosConfiguracaoFluxo(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		return getClass().getSimpleName() + "(ID:" + getId() + "; instituicaoDestino:"
				+ getInstituicaoDestino() + "; isGestor:" + getIsGestor()
				+ "; isResponsavel:" + getIsResponsavel() + "; possuiDocumento:"
				+ getPossuiDocumento() + "; siglaProcesso:\"" + getSiglaProcesso() + "\")";
	}
	
}
