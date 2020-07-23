/*
 * SICOOB
 * 
 * PessoaCompartilhamento.java(br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.ParamDef;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.comum.util.Constantes.Persistencia;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import flexjson.JSON;

/**
 * Entidade que representa a tabela de pessoas no Cadastro único dos clientes.
 * 
 * @author Erico.Junior
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PESSOACOMPARTILHAMENTO", schema = "CLI")
@FilterDef(name=Persistencia.FILTRO_COMPARTILHAMENTO_CADASTRO, parameters={@ParamDef(type="short",name="codigo")})
@Filter(name=Persistencia.FILTRO_COMPARTILHAMENTO_CADASTRO, condition="codCompartilhamentoCadastro = :codigo")
public abstract class PessoaCompartilhamento extends EntidadeCadastroBase implements Vigente, Replicavel, Comprovavel, CadastroValidavel {

	/** Serial UID. */
	private static final long serialVersionUID = -4094724261136255306L;

	/** O atributo id pessoa compartilhamento. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPessoaCompartilhamento;

	/** O atributo compartilhamento. */
	@JoinColumn(name = "CODCOMPARTILHAMENTOCADASTRO", referencedColumnName = "CODCOMPARTILHAMENTOCADASTRO")
	@ManyToOne
	private CompartilhamentoCadastro compartilhamento;
	
	/** O atributo pessoa. */
	@JoinColumn(name = "IDPESSOA", nullable = false)
	@ManyToOne
	private Pessoa pessoa;
	
	/** O atributo nome pessoa. */
	@Column(name = "NOMEPESSOA", length = 50)
	private String nomePessoa;
	
	/** O atributo transicoes. */
	@OneToMany(mappedBy = "pessoaCompartilhamento", fetch = FetchType.EAGER, targetEntity = TransicaoPessoa.class)
	private Set<TransicaoPessoa> transicoes;
	
	/** O atributo nome apelido. */
	@Column(name = "NOMEAPELIDO", length = 30)
	private String nomeApelido;
	
	/** O atributo nome completo. */
	@Column(name = "NOMECOMPLETO", length = 250)
	private String nomeCompleto;
	
	/** O atributo cod compartilhamento cadastro. */
	@Column(name="CODCOMPARTILHAMENTOCADASTRO", insertable=false, updatable=false)
	private Short codCompartilhamentoCadastro;
	
	/** O atributo descricao. */
	@Column(name = "DESCOBSERVACAOPESSOA", length = 200)
	private String descricao;
	
	/** O atributo cnae. */
	@JoinColumn(name = "CODCNAE", referencedColumnName = "CODCNAE")
	@ManyToOne
	private CnaeFiscal cnae;
	
	/** O atributo atividade economica. */
	@JoinColumn(name = "CODATIVIDADEECONOMICA", referencedColumnName = "CODATIVIDADEECONOMICA")
	@ManyToOne
	private AtividadeEconomica atividadeEconomica;
	
	/** O atributo perfil cadastro. */
	@JoinColumn(name = "CODPERFILCADASTRO", referencedColumnName = "CODPERFILCADASTRO", insertable = false, updatable = false)
	@ManyToOne
	private PerfilCadastro perfilCadastro;
	
	/** O atributo data inclusao sistema. */
	@Column(name = "DATAINCLUSAOSISTEMA")
	private DateTimeDB dataInclusaoSistema;

	/** O atributo data inclusao sfn. */
	@Column(name = "DATAINCLUSAOSFN")
	private DateTimeDB dataInclusaoSFN;
	
	/** O atributo autoriza consulta bacen. */
	@Column(name = "BOLAUTORIZACONSULTABACEN")
	private Boolean autorizaConsultaBacen = Boolean.TRUE;
	
	/** O atributo . */
	@Column(name = "BOLIMPORTADOR")
	private Boolean importador = Boolean.FALSE;
	
	/** O atributo . */
	@Column(name = "BOLEXPORTADOR")
	private Boolean exportador = Boolean.FALSE;
	
	/** O atributo codigo situacao aprovacao. */
	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL THEN 1 "
	        + "ELSE COALESCE ((SELECT CASE WHEN A.DATAHORASOLICITACAO IS NOT NULL "
	        + "AND A.BOLDEVOLVIDO = 0 THEN 2 "
	        + "WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 END "
	        + "FROM CLI.AUTORIZACAO A WHERE A.DESCTIPOAUTORIZACAO = 'PESSOA' "
	        + "AND (A.IDREGISTROANTIGO = IDPESSOACOMPARTILHAMENTO "
	        + "OR A.IDREGISTRONOVO = IDPESSOACOMPARTILHAMENTO)), 0) END")
	private Short codigoSituacaoAprovacao;
	
	/** O atributo situacao aprovacao. */
	@Transient
	private SituacaoRegistroEnum situacaoAprovacao;
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	/** O atributo id instituicao atualizacao. */
	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;

	/** O atributo id registro controlado. */
	@Transient
	private String idRegistroControlado;
	
	/** O atributo verificar autorizacao. */
	@Transient
	private Boolean verificarAutorizacao = Boolean.TRUE;
	
	/** O atributo documentos comprobatorios. */
	@Transient
	private Set<DocumentoComprobatorio> documentosComprobatorios;
	
	/** O atributo data renovacao cadastral. */
	@Column(name = "DATARENOVACAOCADASTRAL")
	private DateTimeDB dataRenovacaoCadastral;
	
	/** O atributo id usuario renovacao. */
	@Column(name = "IDUSUARIORENOVACAO")
	private String idUsuarioRenovacao;
	
	/** O atributo id instituicao renovacao. */
	@Column(name = "IDINSTITUICAORENOVACAO")
	private Short idInstituicaoRenovacao;
	
	/** O atributo id cooperativa renovacao. */
	@Transient
	private Integer idCooperativaRenovacao;
	
	/** O atributo codTipoAtualizacaoRenovacao renovacao cadastral. */
	@Column(name = "CODTIPOATUALIZACAORENOVACAO")
	private Integer codigoTipoAtualizacaoRenovacao;
	
	@Column(name="IDUSUARIOENVIO")
	private String idUsuarioEnvio;

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
	 * @return the transicoes
	 */
	@JSON(include=false)
	public Set<TransicaoPessoa> getTransicoes() {
		return transicoes;
	}

	/**
	 * @param transicoes the transicoes to set
	 */
	public void setTransicoes(Set<TransicaoPessoa> transicoes) {
		this.transicoes = transicoes;
	}

	/**
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * @param nomePessoa the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * @return the nomeApelido
	 */
	public String getNomeApelido() {
		return nomeApelido;
	}

	/**
	 * @param nomeApelido the nomeApelido to set
	 */
	public void setNomeApelido(String nomeApelido) {
		this.nomeApelido = nomeApelido;
	}

	/**
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the dataInclusaoSistema
	 */
	public DateTimeDB getDataInclusaoSistema() {
		return dataInclusaoSistema;
	}

	/**
	 * @param dataInclusaoSistema the dataInclusaoSistema to set
	 */
	public void setDataInclusaoSistema(DateTimeDB dataInclusaoSistema) {
		this.dataInclusaoSistema = dataInclusaoSistema;
	}

	/**
	 * @return the dataInclusaoSFN
	 */
	public DateTimeDB getDataInclusaoSFN() {
		return dataInclusaoSFN;
	}

	/**
	 * @param dataInclusaoSFN the dataInclusaoSFN to set
	 */
	public void setDataInclusaoSFN(DateTimeDB dataInclusaoSFN) {
		this.dataInclusaoSFN = dataInclusaoSFN;
	}

	/**
	 * @return the autorizaConsultaBacen
	 */
	public Boolean getAutorizaConsultaBacen() {
		return autorizaConsultaBacen;
	}

	/**
	 * @param autorizaConsultaBacen the autorizaConsultaBacen to set
	 */
	public void setAutorizaConsultaBacen(Boolean autorizaConsultaBacen) {
		this.autorizaConsultaBacen = autorizaConsultaBacen;
	}

	/**
	 * @return the gravarHistorico
	 */
	@Override
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	/**
	 * @param gravarHistorico the gravarHistorico to set
	 */
	@Override
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}

	/**
	 * @return the cnae
	 */
	public CnaeFiscal getCnae() {
		return cnae;
	}

	/**
	 * @param cnae the cnae to set
	 */
	public void setCnae(CnaeFiscal cnae) {
		this.cnae = cnae;
	}

	/**
	 * @return the atividadeEconomica
	 */
	public AtividadeEconomica getAtividadeEconomica() {
		return atividadeEconomica;
	}

	/**
	 * @param atividadeEconomica the atividadeEconomica to set
	 */
	public void setAtividadeEconomica(AtividadeEconomica atividadeEconomica) {
		this.atividadeEconomica = atividadeEconomica;
	}
	
	/**
	 * @return the perfilCadastro
	 */	
	@JSON(include = false)
	public PerfilCadastro getPerfilCadastro() {
		return perfilCadastro;
	}

	/**
	 * @param perfilCadastro the perfilCadastro to set
	 */
	public void setPerfilCadastro(PerfilCadastro perfilCadastro) {
		this.perfilCadastro = perfilCadastro;
	}


	/**
	 * @return the idPessoaCompartilhamento
	 */
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	/**
	 * @param idPessoaCompartilhamento the idPessoaCompartilhamento to set
	 */
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	/**
	 * @return the compartilhamento
	 */
	//@JSON(include=false)
	public CompartilhamentoCadastro getCompartilhamento() {
		return compartilhamento;
	}

	/**
	 * @param compartilhamento the compartilhamento to set
	 */
	public void setCompartilhamento(CompartilhamentoCadastro compartilhamento) {
		this.compartilhamento = compartilhamento;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return this;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public Long getId() {
		return getIdPessoaCompartilhamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdPessoaCompartilhamento(id);
	}	
	
	/**
	 * @return the codCompartilhamentoCadastro
	 */
	public Short getCodCompartilhamentoCadastro() {
		return codCompartilhamentoCadastro;
	}

	/**
	 * @param codCompartilhamentoCadastro the codCompartilhamentoCadastro to set
	 */
	public void setCodCompartilhamentoCadastro(Short codCompartilhamentoCadastro) {
		this.codCompartilhamentoCadastro = codCompartilhamentoCadastro;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idPessoaCompartilhamento");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object objeto) {
		return ReflexaoUtils.equals(this, objeto, "pessoa", "compartilhamento");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	@JSON(include=false)
	@Transient
	public br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum getTipoAutorizacao() {
		return TipoAutorizacaoEnum.PESSOA;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getIdRegistroControlado() {
		if (idRegistroControlado == null) {
			idRegistroControlado = 			
					getTipoAutorizacao().name() + SEPARADOR_ID_REGISTRO_CONTROLADO + getId();
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
	 * Recupera data renovacao cadastral.
	 * 
	 * @return data renovacao cadastral
	 */
	public DateTimeDB getDataRenovacaoCadastral() {
		return dataRenovacaoCadastral;
	}

	/**
	 * Preenche data renovacao cadastral.
	 * 
	 * @param dataRenovacaoCadastral
	 *            o novo data renovacao cadastral
	 */
	public void setDataRenovacaoCadastral(DateTimeDB dataRenovacaoCadastral) {
		this.dataRenovacaoCadastral = dataRenovacaoCadastral;
	}

	/**
	 * Recupera id usuario renovacao.
	 * 
	 * @return id usuario renovacao
	 */
	public String getIdUsuarioRenovacao() {
		return idUsuarioRenovacao;
	}

	/**
	 * Preenche id usuario renovacao.
	 * 
	 * @param idUsuarioRenovacao
	 *            o novo id usuario renovacao
	 */
	public void setIdUsuarioRenovacao(String idUsuarioRenovacao) {
		this.idUsuarioRenovacao = idUsuarioRenovacao;
	}

	/**
	 * Recupera id instituicao renovacao.
	 * 
	 * @return id instituicao renovacao
	 */
	public Short getIdInstituicaoRenovacao() {
		return idInstituicaoRenovacao;
	}

	/**
	 * Preenche id instituicao renovacao.
	 * 
	 * @param idInstituicaoRenovacao
	 *            o novo id instituicao renovacao
	 */
	public void setIdInstituicaoRenovacao(Short idInstituicaoRenovacao) {
		this.idInstituicaoRenovacao = idInstituicaoRenovacao;
	}

	/**
	 * Recupera id cooperativa renovacao.
	 * 
	 * @return id cooperativa renovacao
	 */
	public Integer getIdCooperativaRenovacao() {
		return idCooperativaRenovacao;
	}

	/**
	 * Preenche id cooperativa renovacao.
	 * 
	 * @param idCooperativaRenovacao
	 *            o novo id cooperativa renovacao
	 */
	public void setIdCooperativaRenovacao(Integer idCooperativaRenovacao) {
		this.idCooperativaRenovacao = idCooperativaRenovacao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIdUsuarioEnvio() {
		return this.idUsuarioEnvio;
	}
	
	/**
	 * Preenche idUsuarioEnvio.
	 * 
	 * @param idUsuarioEnvio
	 */
	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}

	public Integer getCodigoTipoAtualizacaoRenovacao() {
		return codigoTipoAtualizacaoRenovacao;
	}

	public void setCodigoTipoAtualizacaoRenovacao(
			Integer codigoTipoAtualizacaoRenovacao) {
		this.codigoTipoAtualizacaoRenovacao = codigoTipoAtualizacaoRenovacao;
	}

	/**
	 * Recupera importador.
	 * 
	 * @return importador
	 */
	public Boolean getImportador() {
		return importador;
	}

	/**
	 * Preenche importaodr.
	 * 
	 * @param importador
	 *            o novo importador
	 */
	public void setImportador(Boolean importador) {
		this.importador = importador;
	}

	/**
	 * Recupera exportador.
	 * 
	 * @return exportador
	 */
	public Boolean getExportador() {
		return exportador;
	}

	/**
	 * Preenche exportador.
	 * 
	 * @param importador
	 *            o novo exportador
	 */
	public void setExportador(Boolean exportador) {
		this.exportador = exportador;
	}
	
}