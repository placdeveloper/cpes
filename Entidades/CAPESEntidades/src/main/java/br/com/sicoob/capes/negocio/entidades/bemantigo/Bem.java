/*
 * SICOOB
 * 
 * Bem.java(br.com.sicoob.capes.negocio.entidades.bemantigo.Bem)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.comum.negocio.annotation.NaoVerificarGestorResponsavel;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;
import flexjson.JSON;

/**
 * Entidade que representa um bem.
 * 
 * @author Erico.Junior
 */
@Entity(name = "BEMANTIGO")
@Table(name = "BEMPESSOA", schema = "CLI")
@EntityListeners({ ReplicacaoListener.class })
@Inheritance(strategy = InheritanceType.JOINED)
@Filters({ @Filter(name = "dataVigente"),@Filter(name = "dataVigenteAntiga"), @Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "imprimirFichaCadastral") })

@NaoVerificarGestorResponsavel
public class Bem extends BemBase implements Replicavel, Comprovavel, Vigente, CadastroValidavel{

	/** Serial UID. */
	private static final long serialVersionUID = 6425895014552054242L;

	/** O atributo id bem. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDBEMPESSOA")
	private Long idBem;
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/** O atributo bens onus. */
	@OneToMany(mappedBy = "bem", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = BemOnus.class)
	private List<BemOnus> bensOnus;

	/** O atributo bens posse. */
	@OneToMany(mappedBy = "bem", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = BemPosse.class)
	private List<BemPosse> bensPosse;

	/** O atributo bens registro. */
	@OneToMany(mappedBy = "bem", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = BemRegistro.class)
	private List<BemRegistro> bensRegistro;

	/** O atributo id instituicao atualizacao. */
	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;

//	/** O atributo codigo situacao aprovacao. */
//	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL THEN 1 "
//	        + "ELSE COALESCE((SELECT CASE WHEN A.DATAHORASOLICITACAO IS NOT NULL AND "
//	        + "A.BOLDEVOLVIDO = 0 THEN 2 "
//			+ "WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 END "
//			+ "FROM CLI.AUTORIZACAO A WHERE A.DESCTIPOAUTORIZACAO = 'BEM' "
//			+ "AND (A.IDREGISTROANTIGO = IDBEMPESSOA OR A.IDREGISTRONOVO = IDBEMPESSOA)), 0 ) END")
	@Transient
	private Short codigoSituacaoAprovacao;

	/** O atributo situacao aprovacao. */
	@Transient
	private SituacaoRegistroEnum situacaoAprovacao;

	/** O atributo id registro controlado. */
	@Transient
	private String idRegistroControlado;

	/** O atributo verificar autorizacao. */
	@Transient
	private Boolean verificarAutorizacao = Boolean.TRUE;

	/** O atributo documentos comprobatorios. */
	@Transient
	private Set<DocumentoComprobatorio> documentosComprobatorios;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Set<DocumentoComprobatorio> getDocumentosComprobatorios() {

		return documentosComprobatorios;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setDocumentosComprobatorios(Set<DocumentoComprobatorio> documentosComprobatorios) {

		this.documentosComprobatorios = documentosComprobatorios;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getVerificarAutorizacao() {

		return verificarAutorizacao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setVerificarAutorizacao(Boolean verificarAutorizacao) {

		this.verificarAutorizacao = verificarAutorizacao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdInstituicaoAtualizacao() {

		return idInstituicaoAtualizacao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setIdInstituicaoAtualizacao(Integer idInstituicaoAtualizacao) {

		this.idInstituicaoAtualizacao = idInstituicaoAtualizacao;
	}

	/**
	 * @return the idBem
	 */
	public Long getIdBem() {

		return idBem;
	}

	/**
	 * @param idBem
	 *            the idBem to set
	 */
	public void setIdBem(Long idBem) {

		this.idBem = idBem;
	}

	/**
	 * @return the gravarHistorico
	 */
	@Override
	public Boolean getGravarHistorico() {

		return gravarHistorico;
	}

	/**
	 * @param gravarHistorico
	 *            the gravarHistorico to set
	 */
	@Override
	public void setGravarHistorico(Boolean gravarHistorico) {

		this.gravarHistorico = gravarHistorico;
	}

	/**
	 * @return the bensOnus
	 */
	@JSON(include=false)
	public List<BemOnus> getBensOnus() {

		return bensOnus;
	}

	/**
	 * @param bensOnus
	 *            the bensOnus to set
	 */
	public void setBensOnus(List<BemOnus> bensOnus) {

		this.bensOnus = bensOnus;
	}

	/**
	 * @return the bensPosse
	 */
	@JSON(include=false)
	public List<BemPosse> getBensPosse() {

		return bensPosse;
	}

	/**
	 * @param bensPosse
	 *            the bensPosse to set
	 */
	public void setBensPosse(List<BemPosse> bensPosse) {

		this.bensPosse = bensPosse;
	}

	/**
	 * @return the bensRegistro
	 */
	@JSON(include=false)
	public List<BemRegistro> getBensRegistro() {

		return bensRegistro;
	}

	/**
	 * @param bensRegistro
	 *            the bensRegistro to set
	 */
	public void setBensRegistro(List<BemRegistro> bensRegistro) {

		this.bensRegistro = bensRegistro;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {

		return getIdBem();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {

		setIdBem(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum getTipoAutorizacao() {

		return TipoAutorizacaoEnum.BEM;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getIdRegistroControlado() {

		if (idRegistroControlado == null) {
			idRegistroControlado = getTipoAutorizacao().name() + SEPARADOR_ID_REGISTRO_CONTROLADO + getId();
		}
		return idRegistroControlado;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setIdRegistroControlado(String idRegistroControlado) {

		this.idRegistroControlado = idRegistroControlado;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getCodigoSituacaoAprovacao() {

		return codigoSituacaoAprovacao;
	}

	/**
	 * Preenche codigo situacao aprovacao.
	 * 
	 * @param codigoSituacaoAprovacao
	 *            o novo codigo situacao aprovacao
	 */
	@Override
	public void setCodigoSituacaoAprovacao(Short codigoSituacaoAprovacao) {

		this.codigoSituacaoAprovacao = codigoSituacaoAprovacao;
	}

	/**
	 * Recupera situacao aprovacao.
	 * 
	 * @return situacao aprovacao
	 */
	@Override
	public SituacaoRegistroEnum getSituacaoAprovacao() {

		if ((situacaoAprovacao == null) && (codigoSituacaoAprovacao != null)) {
			situacaoAprovacao = SituacaoRegistroEnum.valueOf(codigoSituacaoAprovacao);
		}
		return situacaoAprovacao;
	}

	/**
	 * Preenche situacao aprovacao.
	 * 
	 * @param situacaoAprovacao
	 *            o novo situacao aprovacao
	 */
	@Override
	public void setSituacaoAprovacao(SituacaoRegistroEnum situacaoAprovacao) {

		this.situacaoAprovacao = situacaoAprovacao;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Implementado devido a interface Aprovavel,
	 * portanto retorna null por  a entidade não 
	 * conter o campo IdUsuarioEnvio.  
	 *
	 */
	@Override
	public String getIdUsuarioEnvio() {
		return null;
	}

}