/*
 * SICOOB
 * 
 * HistoricoPessoaCompartilhamento.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaCompartilhamento)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa o histórico de pessoa.
 * @author Erico.Junior
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="HISTPESSOACOMPARTILHAMENTO", schema="CLI")
@Filters({ @Filter(name = "periodoHistorico"),@Filter(name = "periodoHistoricoAntiga"), @Filter(name = "periodoHistoricoDatasIguais") })
public abstract class HistoricoPessoaCompartilhamento extends EntidadeCadastroBase implements Historico {
	
	/** Serial UID.*/
	private static final long serialVersionUID = -1532299283233805654L;

	/** O atributo id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDHISTPESSOACOMPARTILHAMENTO")
	private Long id;
	
	/** O atributo pessoa. */
	@JoinColumn(name = "IDPESSOA")
	@ManyToOne
	private Pessoa pessoa;
	
	/** O atributo pessoa compartilhamento. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/** O atributo compartilhamento. */
	@JoinColumn(name = "CODCOMPARTILHAMENTOCADASTRO", referencedColumnName = "CODCOMPARTILHAMENTOCADASTRO")
	@ManyToOne
	private CompartilhamentoCadastro compartilhamento;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;
	
	/** O atributo nome pessoa. */
	private String nomePessoa;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** O atributo nome apelido. */
	private String nomeApelido;
	
	/** O atributo nome completo. */
	private String nomeCompleto;
	
	/** O atributo cnae. */
	@JoinColumn(name = "CODCNAE", referencedColumnName = "CODCNAE")
	@ManyToOne
	private CnaeFiscal cnae;	
	
	/** O atributo descricao. */
	@Column(name = "DESCOBSERVACAOPESSOA")
	private String descricao;
	
	/** O atributo perfil cadastro. */
	@JoinColumn(name = "CODPERFILCADASTRO", referencedColumnName = "CODPERFILCADASTRO")
	@ManyToOne
	private PerfilCadastro perfilCadastro;	
	
	/** O atributo data inclusao sfn. */
	@Column(name = "DATAINCLUSAOSFN")
	private Date dataInclusaoSFN;
	
	/** O atributo data inclusao sistema. */
	@Column(name = "DATAINCLUSAOSISTEMA")
	private Date dataInclusaoSistema;
	
	/** O atributo atividade economica. */
	@JoinColumn(name = "CODATIVIDADEECONOMICA", referencedColumnName = "CODATIVIDADEECONOMICA")
	@ManyToOne
	private AtividadeEconomica atividadeEconomica;
	
	/** O atributo autoriza consulta bacen. */
	@Column(name = "BOLAUTORIZACONSULTABACEN")
	private Boolean autorizaConsultaBacen;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	/** O atributo data renovacao cadastral. */
	@Column(name = "DATARENOVACAOCADASTRAL")
	private DateTimeDB dataRenovacaoCadastral;
	
	/** O atributo id usuario renovacao. */
	@Column(name = "IDUSUARIORENOVACAO")
	private String idUsuarioRenovacao;
	
	/** O atributo id instituicao renovacao. */
	@Column(name = "IDINSTITUICAORENOVACAO")
	private Short idInstituicaoRenovacao;

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Recupera pessoa.
	 * 
	 * @return pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * Preenche pessoa.
	 * 
	 * @param pessoa
	 *            o novo pessoa
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @param nomePessoa the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * @return the dataHoraFim
	 */
	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	/**
	 * @param dataHoraFim the dataHoraFim to set
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
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
	 * @return the perfilCadastro
	 */
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
	 * @return the dataInclusaoSFN
	 */
	public Date getDataInclusaoSFN() {
		return dataInclusaoSFN;
	}

	/**
	 * @param dataInclusaoSFN the dataInclusaoSFN to set
	 */
	public void setDataInclusaoSFN(Date dataInclusaoSFN) {
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
	 * @return the dataInclusaoSistema
	 */
	public Date getDataInclusaoSistema() {
		return dataInclusaoSistema;
	}

	/**
	 * @param dataInclusaoSistema the dataInclusaoSistema to set
	 */
	public void setDataInclusaoSistema(Date dataInclusaoSistema) {
		this.dataInclusaoSistema = dataInclusaoSistema;
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
	 * Recupera compartilhamento.
	 * 
	 * @return compartilhamento
	 */
	public CompartilhamentoCadastro getCompartilhamento() {
		return compartilhamento;
	}

	/**
	 * Preenche compartilhamento.
	 * 
	 * @param compartilhamento
	 *            o novo compartilhamento
	 */
	public void setCompartilhamento(CompartilhamentoCadastro compartilhamento) {
		this.compartilhamento = compartilhamento;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	/**
	 * Preenche pessoa compartilhamento.
	 * 
	 * @param pessoaCompartilhamento
	 *            o novo pessoa compartilhamento
	 */
	public void setPessoaCompartilhamento(
			PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	/** 
	 * {@inheritDoc}
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	
	/**
	 * Recupera id usuario exclusao.
	 * 
	 * @return id usuario exclusao
	 */
	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
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

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}
}