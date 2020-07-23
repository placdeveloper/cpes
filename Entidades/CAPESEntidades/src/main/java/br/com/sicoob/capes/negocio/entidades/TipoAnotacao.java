/*
 * SICOOB
 * 
 * TipoAnotacao.java(br.com.sicoob.capes.negocio.entidades.TipoAnotacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import flexjson.JSON;

/**
 * Classe que representa o tipo de anotação.
 * @author Erico.Junior
 */
@Entity
@Table(name="TIPOANOTACAO", schema = "CLI")
@OrdenacaoPadrao(colunas = "DESCTIPOANOTACAO", descendente = false)
public class TipoAnotacao extends CAPESEntidade<Integer> {

	/** Serial UID. */
	private static final long serialVersionUID = -7066338794688562225L;
	
	/** A Constante NADA_CONSTA contendo o codigo para o tipo "Nada Consta". */
	public static final Integer NADA_CONSTA = 1;

	/** O atributo cod tipo anotacao. */
	@Id
	private Integer codTipoAnotacao;

	/** O atributo desc tipo anotacao. */
	private String descTipoAnotacao;
	
	/** O atributo tipo captura. */
	@JoinColumn( name = "IDTIPOCAPTURA", referencedColumnName = "IDTIPOCAPTURA" )
	@ManyToOne
	private TipoCaptura tipoCaptura;

	/** O atributo categoria anotacao. */
	@JoinColumn( name = "IDCATEGORIAANOTACAO", referencedColumnName = "IDCATEGORIAANOTACAO" )
	@ManyToOne
	private CategoriaAnotacao categoriaAnotacao;

	/** O atributo periodicidade anotacao. */
	@JoinColumn( name = "IDPERIODICIDADEANOTACAO", referencedColumnName = "IDPERIODICIDADEANOTACAO" )
	@ManyToOne
	private PeriodicidadeAnotacao periodicidadeAnotacao;
	
	/** O atributo id tipo aplicacao. */
	private Short idTipoAplicacao;
	
	/** O atributo usuario inclusao. */
	@Column(name = "IDUSUARIOINC")
	private String usuarioInclusao;
	
	/** O atributo usuario alteracao. */
	@Column(name = "IDUSUARIOALT")
	private String usuarioAlteracao;
	
	/** O atributo desc saida anotacao. */
	@Column(name = "DESCSAIDAANOTACAO")
	private String descSaidaAnotacao;
	
	/** O atributo data hora inclusao. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAHORAINC")
	private Date dataHoraInclusao;
	
	/** O atributo data hora alteracao. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAHORAALT")
	private Date dataHoraAlteracao;
	
	/** O atributo ativo. */
	@Column(name = "BOLATIVO", nullable = false)
	private Boolean ativo;

	/** O atributo registra valor. */
	@Column(name = "BOLREGISTRAVALOR", nullable = false)
	private Boolean registraValor;
	
	/** Define se as anotacoes deste tipo devem registrar quantidade */
	@Column(name = "BOLREGISTRAQUANTIDADE", nullable = false)
	private Boolean registraQuantidade;

	/** O atributo mapas tipo anotacao. */
	@OneToMany(mappedBy = "tipoAnotacao", fetch = FetchType.EAGER)
	private Set<MapaTipoAnotacao> mapasTipoAnotacao;
	
	/** O atributo tipo observação. */
	@JoinColumn( name = "CODTIPOOBSERVACAOANOTACAO", referencedColumnName = "CODTIPOOBSERVACAOANOTACAO" )
	@ManyToOne
	private TipoObservacaoAnotacao tipoObservacaoAnotacao;
	
	/**
	 * Atributo indica se o tipo da anotação deve verificar a restrição de uso
	 * para alguma cooperativa (CLI.COOPERATIVAANOTACAO).
	 */
	@Column(name = "BOLVALIDAUSOANOTACAO ", nullable = false)
	private Boolean validaUsoAnotacao;
	
	/**
	 * Lista das instituicoes com restrição ao tipo de anotação.
	 */
	@OneToMany(mappedBy = "tipoAnotacao", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<CooperativaAnotacao> cooperativasAnotacao;
	
	/** O atributo anotarFilial. */
	@Column(name = "BOLANOTARFILIAL", nullable = false)
	private Boolean anotarFilial;
	
	/** O atributo compartilhar. */
	@Column(name = "BOLCOMPARTILHAR", nullable = false)
	private Boolean compartilhar;
	
	/** O atributo instituicoes. */
	@Transient
	private List<InstituicaoVO> instituicoes;
	
	/** O atributo instituicao. */
	@Transient
	private Instituicao instituicao;

	/**
	 * Cria uma nova instância de tipo anotacao.
	 */
	public TipoAnotacao() {
	}

	/**
	 * Cria uma nova instância de tipo anotacao.
	 * 
	 * @param codTipoAnotacao
	 *            the cod tipo anotacao
	 */
	public TipoAnotacao(Integer codTipoAnotacao) {

		this.codTipoAnotacao = codTipoAnotacao;
	}

	/**
	 * @return the codTipoAnotacao
	 */
	public Integer getCodTipoAnotacao() {
		return codTipoAnotacao;
	}

	/**
	 * @param codTipoAnotacao the codTipoAnotacao to set
	 */
	public void setCodTipoAnotacao(Integer codTipoAnotacao) {
		this.codTipoAnotacao = codTipoAnotacao;
	}

	/**
	 * @return the descTipoAnotacao
	 */
	public String getDescTipoAnotacao() {
		return descTipoAnotacao;
	}

	/**
	 * @param descTipoAnotacao the descTipoAnotacao to set
	 */
	public void setDescTipoAnotacao(String descTipoAnotacao) {
		this.descTipoAnotacao = descTipoAnotacao;
	}

	/**
	 * @return the descSaidaAnotacao
	 */
	public String getDescSaidaAnotacao() {
		return descSaidaAnotacao;
	}

	/**
	 * @param descSaidaAnotacao the descSaidaAnotacao to set
	 */
	public void setDescSaidaAnotacao(String descSaidaAnotacao) {
		this.descSaidaAnotacao = descSaidaAnotacao;
	}

	/**
	 * @return the tipoCaptura
	 */
	public TipoCaptura getTipoCaptura() {
		return tipoCaptura;
	}

	/**
	 * @param tipoCaptura the tipoCaptura to set
	 */
	public void setTipoCaptura(TipoCaptura tipoCaptura) {
		this.tipoCaptura = tipoCaptura;
	}

	/**
	 * @return the categoriaAnotacao
	 */
	public CategoriaAnotacao getCategoriaAnotacao() {
		return categoriaAnotacao;
	}

	/**
	 * @param categoriaAnotacao the categoriaAnotacao to set
	 */
	public void setCategoriaAnotacao(CategoriaAnotacao categoriaAnotacao) {
		this.categoriaAnotacao = categoriaAnotacao;
	}

	/**
	 * @return the periodicidadeAnotacao
	 */
	public PeriodicidadeAnotacao getPeriodicidadeAnotacao() {
		return periodicidadeAnotacao;
	}

	/**
	 * @param periodicidadeAnotacao the periodicidadeAnotacao to set
	 */
	public void setPeriodicidadeAnotacao(PeriodicidadeAnotacao periodicidadeAnotacao) {
		this.periodicidadeAnotacao = periodicidadeAnotacao;
	}

	/**
	 * @return the idTipoAplicacao
	 */
	public Short getIdTipoAplicacao() {
		return idTipoAplicacao;
	}

	/**
	 * @param idTipoAplicacao the idTipoAplicacao to set
	 */
	public void setIdTipoAplicacao(Short idTipoAplicacao) {
		this.idTipoAplicacao = idTipoAplicacao;
	}

	/**
	 * @return the usuarioInclusao
	 */
	public String getUsuarioInclusao() {
		return usuarioInclusao;
	}

	/**
	 * @param usuarioInclusao the usuarioInclusao to set
	 */
	public void setUsuarioInclusao(String usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}

	/**
	 * @return the usuarioAlteracao
	 */
	public String getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	/**
	 * @param usuarioAlteracao the usuarioAlteracao to set
	 */
	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	/**
	 * @return the dataHoraInclusao
	 */
	public Date getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	/**
	 * @param dataHoraInclusao the dataHoraInclusao to set
	 */
	public void setDataHoraInclusao(Date dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}

	/**
	 * @return the dataHoraAlteracao
	 */
	public Date getDataHoraAlteracao() {
		return dataHoraAlteracao;
	}

	/**
	 * @param dataHoraAlteracao the dataHoraAlteracao to set
	 */
	public void setDataHoraAlteracao(Date dataHoraAlteracao) {
		this.dataHoraAlteracao = dataHoraAlteracao;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return the registraValor
	 */
	public Boolean getRegistraValor() {
		return registraValor;
	}

	/**
	 * @param registraValor the registraValor to set
	 */
	public void setRegistraValor(Boolean registraValor) {
		this.registraValor = registraValor;
	}
	
	/**
     * @return the registraQuantidade
     */
    public Boolean getRegistraQuantidade() {
    	return registraQuantidade;
    }

	/**
     * @param registraQuantidade the registraQuantidade to set
     */
    public void setRegistraQuantidade(Boolean registraQuantidade) {
    	this.registraQuantidade = registraQuantidade;
    }

	/**
	 * Recupera mapas tipo anotacao.
	 * 
	 * @return mapas tipo anotacao
	 */
	public Set<MapaTipoAnotacao> getMapasTipoAnotacao() {
		return mapasTipoAnotacao;
	}

	/**
	 * Preenche mapas tipo anotacao.
	 * 
	 * @param mapasTipoAnotacao
	 *            o novo mapas tipo anotacao
	 */
	public void setMapasTipoAnotacao(Set<MapaTipoAnotacao> mapasTipoAnotacao) {
		this.mapasTipoAnotacao = mapasTipoAnotacao;
	}
	
	/**
	 * Recupera o valor de validaUsoAnotacao.
	 *
	 * @return o valor de validaUsoAnotacao
	 */
	public Boolean getValidaUsoAnotacao() {
		return validaUsoAnotacao;
	}

	/**
	 * Define o valor de validaUsoAnotacao.
	 *
	 * @param validaUsoAnotacao o novo valor de validaUsoAnotacao
	 */
	public void setValidaUsoAnotacao(Boolean validaUsoAnotacao) {
		this.validaUsoAnotacao = validaUsoAnotacao;
	}
	
	/**
	 * Recupera o valor de cooperativasAnotacao.
	 *
	 * @return o valor de cooperativasAnotacao
	 */
	@JSON(include=false)
	public Set<CooperativaAnotacao> getCooperativasAnotacao() {
		return cooperativasAnotacao;
	}

	/**
	 * Define o valor de cooperativasAnotacao.
	 *
	 * @param cooperativasAnotacao o novo valor de cooperativasAnotacao
	 */
	public void setCooperativasAnotacao(Set<CooperativaAnotacao> cooperativasAnotacao) {
		this.cooperativasAnotacao = cooperativasAnotacao;
	}
	
	/**
	 * Recupera o valor de anotarFilial.
	 *
	 * @return o valor de anotarFilial
	 */
	public Boolean getAnotarFilial() {
		return anotarFilial;
	}

	/**
	 * Define o valor de anotarFilial.
	 *
	 * @param anotarFilial o novo valor de anotarFilial
	 */
	public void setAnotarFilial(Boolean anotarFilial) {
		this.anotarFilial = anotarFilial;
	}
	
	/**
	 * Recupera o valor de compartilhar.
	 *
	 * @return o valor de compartilhar
	 */
	public Boolean getCompartilhar() {
		return compartilhar;
	}

	/**
	 * Define o valor de compartilhar.
	 *
	 * @param compartilhar o novo valor de compartilhar
	 */
	public void setCompartilhar(Boolean compartilhar) {
		this.compartilhar = compartilhar;
	}

	/**
	 * Recupera o valor de instituicoes.
	 *
	 * @return o valor de instituicoes
	 */
	@JSON(include=false)
	public List<InstituicaoVO> getInstituicoes() {
		return instituicoes;
	}

	/**
	 * Define o valor de instituicoes.
	 *
	 * @param instituicoes o novo valor de instituicoes
	 */
	public void setInstituicoes(List<InstituicaoVO> instituicoes) {
		this.instituicoes = instituicoes;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Integer getId() {
		return getCodTipoAnotacao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setCodTipoAnotacao(id);
	}
	
	/**
	 * Incluir.
	 */
	@PrePersist
	public void incluir() {
		dataHoraInclusao = new Date();
		usuarioInclusao = InformacoesUsuarioCAPES.getInstance().getLogin();
	}

	/**
	 * Atualizar data.
	 */
	@PreUpdate
	public void atualizarData() {
		dataHoraAlteracao = new Date();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getCodTipoAnotacao() + " - " + getDescTipoAnotacao();
	}

	/**
	 * Recupera o valor de instituicao.
	 *
	 * @return o valor de instituicao
	 */
	public Instituicao getInstituicao() {
		return instituicao;
	}

	/**
	 * Define o valor de instituicao.
	 *
	 * @param instituicao o novo valor de instituicao
	 */
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	/**
	 * @return the tipoObservacaoAnotacao
	 */
	public TipoObservacaoAnotacao getTipoObservacaoAnotacao() {
		return tipoObservacaoAnotacao;
	}

	/**
	 * @param tipoObservacaoAnotacao the tipoObservacaoAnotacao to set
	 */
	public void setTipoObservacaoAnotacao(TipoObservacaoAnotacao tipoObservacaoAnotacao) {
		this.tipoObservacaoAnotacao = tipoObservacaoAnotacao;
	}

}