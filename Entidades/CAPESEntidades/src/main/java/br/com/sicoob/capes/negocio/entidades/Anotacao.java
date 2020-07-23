/*
 * SICOOB
 * 
 * Anotacao.java(br.com.sicoob.capes.negocio.entidades.Anotacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa a tabela responsável por armazenar as anotações
 * dos clientes.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name="ANOTACAO", schema = "CLI")
@Inheritance(strategy=InheritanceType.JOINED)
@EntityListeners({ReplicacaoListener.class})
public class Anotacao extends CAPESEntidade<Long> implements Replicavel, CadastroValidavel {

	/** Serial UID */
	private static final long serialVersionUID = -1956611749065326504L;

	/** O atributo id anotacao. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAnotacao;

	/** O atributo pessoa compartilhamento. */
	@JoinColumn( name = "idPessoaCompartilhamento", referencedColumnName = "idPessoaCompartilhamento" )
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/** O atributo tipo anotacao. */
	@JoinColumn( name = "IDTIPOANOTACAO", referencedColumnName = "CODTIPOANOTACAO" )
	@ManyToOne
	private TipoAnotacao tipoAnotacao;
	
	/** O atributo tipo baixa. */
	@JoinColumn( name = "IDTIPOBAIXA", referencedColumnName = "IDTIPOBAIXA" )
	@ManyToOne
	private TipoBaixa tipoBaixa;
	
	/** O atributo usuario inclusao. */
	@Column(name = "IDUSUARIOINC")
	private String usuarioInclusao;
	
	/** O atributo usuario alteracao. */
	@Column(name = "IDUSUARIOALT")
	private String usuarioAlteracao;
	
	/** O atributo data hora anotacao. */
	@Column(name = "DATAHORAANOTACAO")
	private DateTimeDB dataHoraAnotacao;
	
	/** O atributo data hora ocorrencia. */
	@Column(name = "DATAHORAOCORRENCIA")
	private DateTimeDB dataHoraOcorrencia;	
	
	/** O atributo data hora alteracao. */
	@Column(name = "DATAHORAALTERACAO")
	private DateTimeDB dataHoraAlteracao;
	
	/** O atributo data hora baixa. */
	@Column(name = "DATAHORABAIXA")
	private DateTimeDB dataHoraBaixa;	
	
	/** O atributo desc observacao. */
	private String descObservacao;
	
	/** O atributo valor. */
	@Column(name = "VALORANOTACAO")
	private BigDecimal valor = BigDecimal.ZERO;
	
	/** O atributo flexibilidade. */
	@Column(name = "BOLFLEXIBILIDADE")
	private Boolean flexibilidade = Boolean.FALSE;

	/** O atributo id consulta externa. */
	private Integer idConsultaExterna;
	
	/** O atributo quantidade. */
	@Column(name = "QTDANOTACAO")	
	private Short quantidade = 0;
	
	/** O atributo origem informacao. */
	@ManyToOne
	@JoinColumn(name = "IDORIGEMINFO", referencedColumnName = "IDORIGEMINFO")
	private OrigemInformacao origemInformacao;
	
	/** O atributo tipo consulta origem. */
	@ManyToOne
	@JoinColumn(name = "CODTIPOCONSULTAORIGEM", referencedColumnName = "CODTIPOCONSULTAORIGEM")
	private TipoConsultaOrigem tipoConsultaOrigem;
	
	/** O atributo observacaoAnotacao. */
	@ManyToOne
	@JoinColumn(name="CODOBSERVACAOANOTACAO", referencedColumnName="CODOBSERVACAOANOTACAO")
	private ObservacaoAnotacao observacaoAnotacao;

	/** O atributo instituicao. */
	@Embedded
	private Instituicao instituicao;	
	
	/** O atributo codigo tipo exposicao. */
	@Transient
	private Short codigoTipoExposicao;
	
	/**
	 * @return the idAnotacao
	 */
	public Long getIdAnotacao() {
		return idAnotacao;
	}

	/**
	 * @param idAnotacao the idAnotacao to set
	 */
	public void setIdAnotacao(Long idAnotacao) {
		this.idAnotacao = idAnotacao;
	}

	/**
	 * @return the pessoaCompartilhamento
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	/**
	 * @param pessoaCompartilhamento the pessoaCompartilhamento to set
	 */
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoa) {
		this.pessoaCompartilhamento = pessoa;
	}

	/**
	 * @return the tipoAnotacao
	 */
	public TipoAnotacao getTipoAnotacao() {
		return tipoAnotacao;
	}

	/**
	 * @param tipoAnotacao the tipoAnotacao to set
	 */
	public void setTipoAnotacao(TipoAnotacao tipoAnotacao) {
		this.tipoAnotacao = tipoAnotacao;
	}

	/**
	 * @return the tipoBaixa
	 */
	public TipoBaixa getTipoBaixa() {
		return tipoBaixa;
	}

	/**
	 * @param tipoBaixa the tipoBaixa to set
	 */
	public void setTipoBaixa(TipoBaixa tipoBaixa) {
		this.tipoBaixa = tipoBaixa;
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
	 * @return the dataHoraAnotacao
	 */
	public DateTimeDB getDataHoraAnotacao() {
		return dataHoraAnotacao;
	}

	/**
	 * @param dataHoraAnotacao the dataHoraAnotacao to set
	 */
	public void setDataHoraAnotacao(DateTimeDB dataHoraAnotacao) {
		this.dataHoraAnotacao = dataHoraAnotacao;
	}

	/**
	 * @return the dataHoraOcorrencia
	 */
	public DateTimeDB getDataHoraOcorrencia() {
		return dataHoraOcorrencia;
	}

	/**
	 * @param dataHoraOcorrencia the dataHoraOcorrencia to set
	 */
	public void setDataHoraOcorrencia(DateTimeDB dataHoraOcorrencia) {
		this.dataHoraOcorrencia = dataHoraOcorrencia;
	}

	/**
	 * @return the dataHoraAlteracao
	 */
	public DateTimeDB getDataHoraAlteracao() {
		return dataHoraAlteracao;
	}

	/**
	 * @param dataHoraAlteracao the dataHoraAlteracao to set
	 */
	public void setDataHoraAlteracao(DateTimeDB dataHoraAlteracao) {
		this.dataHoraAlteracao = dataHoraAlteracao;
	}

	/**
	 * @return the dataHoraBaixa
	 */
	public DateTimeDB getDataHoraBaixa() {
		return dataHoraBaixa;
	}

	/**
	 * @param dataHoraBaixa the dataHoraBaixa to set
	 */
	public void setDataHoraBaixa(DateTimeDB dataHoraBaixa) {
		this.dataHoraBaixa = dataHoraBaixa;
	}

	/**
	 * @return the descObservacao
	 */
	public String getDescObservacao() {
		return descObservacao;
	}

	/**
	 * @param descObservacao the descObservacao to set
	 */
	public void setDescObservacao(String descObservacao) {
		this.descObservacao = descObservacao;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the flexibilidade
	 */
	public Boolean getFlexibilidade() {
		return flexibilidade;
	}

	/**
	 * @param flexibilidade the flexibilidade to set
	 */
	public void setFlexibilidade(Boolean flexibilidade) {
		this.flexibilidade = flexibilidade;
	}

	/**
	 * @return the idConsultaExterna
	 */
	public Integer getIdConsultaExterna() {
		return idConsultaExterna;
	}

	/**
	 * @param idConsultaExterna the idConsultaExterna to set
	 */
	public void setIdConsultaExterna(Integer idConsultaExterna) {
		this.idConsultaExterna = idConsultaExterna;
	}

	/**
	 * @return the quantidade
	 */
	public Short getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Long getId() {
		return getIdAnotacao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdAnotacao(id);
	}
	
	/**
	 * Recupera origem informacao.
	 * 
	 * @return origem informacao
	 */
	public OrigemInformacao getOrigemInformacao() {
		return origemInformacao;
	}

	/**
	 * Preenche origem informacao.
	 * 
	 * @param origemInformacao
	 *            o novo origem informacao
	 */
	public void setOrigemInformacao(OrigemInformacao origemInformacao) {
		this.origemInformacao = origemInformacao;
	}
	
	/**
	 * Recupera tipo consulta origem.
	 * 
	 * @return tipo consulta origem
	 */
	public TipoConsultaOrigem getTipoConsultaOrigem() {
		return tipoConsultaOrigem;
	}

	/**
	 * Preenche tipo consulta origem.
	 * 
	 * @param tipoConsultaOrigem
	 *            o novo tipo consulta origem
	 */
	public void setTipoConsultaOrigem(TipoConsultaOrigem tipoConsultaOrigem) {
		this.tipoConsultaOrigem = tipoConsultaOrigem;
	}

	/**
	 * @return the instituicao
	 */
	public Instituicao getInstituicao() {
		return instituicao;
	}

	/**
	 * @param instituicao the instituicao to set
	 */
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	/**
	 * @return the codigoTipoExposicao
	 */
	public Short getCodigoTipoExposicao() {
		return codigoTipoExposicao;
	}

	/**
	 * @param codigoTipoExposicao the codigoTipoExposicao to set
	 */
	public void setCodigoTipoExposicao(Short codigoTipoExposicao) {
		this.codigoTipoExposicao = codigoTipoExposicao;
	}

	/**
	 * Recupera o valor de observacaoAnotacao.
	 *
	 * @return o valor de observacaoAnotacao
	 */
	public ObservacaoAnotacao getObservacaoAnotacao() {
		return observacaoAnotacao;
	}

	/**
	 * Define o valor de observacaoAnotacao.
	 *
	 * @param observacaoAnotacao o novo valor de observacaoAnotacao
	 */
	public void setObservacaoAnotacao(ObservacaoAnotacao observacaoAnotacao) {
		this.observacaoAnotacao = observacaoAnotacao;
	}

}
