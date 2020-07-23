/*
 * SICOOB
 * 
 * PessoaInstituicaoBase.java(br.com.sicoob.capes.negocio.entidades.PessoaInstituicaoBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import flexjson.JSON;

/**
 * Entidade responsável por armazenar as informacoes de pessoa instituicao.
 * 
 * @author juan.damasceno
 */
@MappedSuperclass
public abstract class PessoaInstituicaoBase extends CAPESEntidade<Integer> {

	/** Serial UID. */
	private static final long serialVersionUID = -6257459239840096734L;

	/** O atributo pessoa. */
	@JoinColumn(name = "IDPESSOA")
	@ManyToOne
	private Pessoa pessoa;

	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;

	/** O atributo id instituicao. */
	@Column(insertable=false, updatable=false) //Já está mapeado no relacionamento com PerfilTarifario
	private Integer idInstituicao;
	
	/** O atributo funcionario. */
	@ManyToOne
	@JoinColumn(name="IDFUNCIONARIORESPONSAVEL", referencedColumnName ="IDFUNCIONARIO")
	private Funcionario funcionario;

	/** O atributo grupos economicos. */
	@OneToMany(mappedBy = "pessoaInstituicao")
	private Set<GrupoEconomicoPessoa> gruposEconomicos;

	/** O atributo perfil tarifario. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CODPERFILTARIFARIO", referencedColumnName = "CODPERFILTARIFARIO"),
		@JoinColumn(name="IDINSTITUICAO", referencedColumnName = "IDINSTITUICAO")
	})
	private PerfilTarifario perfilTarifario;

	/** O atributo parecer gerencia. */
	@Column(name="DESCPARECERGERENCIA")
	private String parecerGerencia;

	/** O atributo emite aviso lancamento. */
	@Column(name="BOLEMITEAVISOLANCAMENTO")
	private Boolean emiteAvisoLancamento;
	
	/** O atributo nucleo. */
	@ManyToOne
	@JoinColumn(name="IDNUCLEO")
	private Nucleo nucleo;
	
	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;
	
	/** O atributo dataCadastro. */
	@Column(name = "DATACADASTRO")
	private DateTimeDB dataCadastro;
	
	/** O atributo id usuario aprovacao. */
	@Column(name = "IDUSUARIOAPROV", length = 40)
	private String idUsuarioAprovacao;
	
	/**
	 * Transients, são persistidos apenas na replicação para o SQL server.
	 */
	@Transient
	private DateTimeDB dataEnquadramentoRisco;
	
	/** O atributo nivel risco. */
	@Transient
	private String nivelRisco;
	
	/** O atributo motivo risco. */
	@Transient
	private String motivoRisco;
	
	
	/**
	 * Gets the transients, são persistidos apenas na replicação para o SQL server.
	 * 
	 * @return the transients, são persistidos apenas na replicação para o SQL server
	 */
	public DateTimeDB getDataEnquadramentoRisco() {
		return dataEnquadramentoRisco;
	}

	/**
	 * Sets the transients, são persistidos apenas na replicação para o SQL server.
	 * 
	 * @param dataEnquadramentoRisco
	 *            the new transients, são persistidos apenas na replicação para o SQL server
	 */
	public void setDataEnquadramentoRisco(DateTimeDB dataEnquadramentoRisco) {
		this.dataEnquadramentoRisco = dataEnquadramentoRisco;
	}

	/**
	 * Recupera nivel risco.
	 * 
	 * @return nivel risco
	 */
	public String getNivelRisco() {
		return nivelRisco;
	}

	/**
	 * Preenche nivel risco.
	 * 
	 * @param nivelRisco
	 *            o novo nivel risco
	 */
	public void setNivelRisco(String nivelRisco) {
		this.nivelRisco = nivelRisco;
	}

	/**
	 * Recupera motivo risco.
	 * 
	 * @return motivo risco
	 */
	public String getMotivoRisco() {
		return motivoRisco;
	}

	/**
	 * Preenche motivo risco.
	 * 
	 * @param motivoRisco
	 *            o novo motivo risco
	 */
	public void setMotivoRisco(String motivoRisco) {
		this.motivoRisco = motivoRisco;
	}

	/**
	 * @return the dataHoraInicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * @param dataHoraInicio the dataHoraInicio to set
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	
	/**
	 * Recupera o valor de dataCadastro.
	 *
	 * @return o valor de dataCadastro
	 */
	public DateTimeDB getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * Define o valor de dataCadastro.
	 *
	 * @param dataCadastro o novo valor de dataCadastro
	 */
	public void setDataCadastro(DateTimeDB dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the nucleo
	 */
	public Nucleo getNucleo() {
		return nucleo;
	}

	/**
	 * @param nucleo the nucleo to set
	 */
	public void setNucleo(Nucleo nucleo) {
		this.nucleo = nucleo;
	}

	/**
	 * @return the perfilTarifario
	 */
	public PerfilTarifario getPerfilTarifario() {
		return perfilTarifario;
	}

	/**
	 * @param perfilTarifario the perfilTarifario to set
	 */
	public void setPerfilTarifario(PerfilTarifario perfilTarifario) {
		this.perfilTarifario = perfilTarifario;
	}

	/**
	 * @return the parecerGerencia
	 */
	public String getParecerGerencia() {
		return parecerGerencia;
	}

	/**
	 * @param parecerGerencia the parecerGerencia to set
	 */
	public void setParecerGerencia(String parecerGerencia) {
		this.parecerGerencia = parecerGerencia;
	}

	/**
	 * @return the emiteAvisoLancamento
	 */
	public Boolean getEmiteAvisoLancamento() {
		return emiteAvisoLancamento;
	}

	/**
	 * @param emiteAvisoLancamento the emiteAvisoLancamento to set
	 */
	public void setEmiteAvisoLancamento(Boolean emiteAvisoLancamento) {
		this.emiteAvisoLancamento = emiteAvisoLancamento;
	}

	/**
	 * Recupera id unidade inst.
	 * 
	 * @return id unidade inst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Preenche id unidade inst.
	 * 
	 * @param idUnidadeInst
	 *            o novo id unidade inst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the gruposEconomicos
	 */
	@JSON(include = false)
	public Set<GrupoEconomicoPessoa> getGruposEconomicos() {
		return gruposEconomicos;
	}

	/**
	 * @param gruposEconomicos the gruposEconomicos to set
	 */
	public void setGruposEconomicos(Set<GrupoEconomicoPessoa> gruposEconomicos) {
		this.gruposEconomicos = gruposEconomicos;
	}

	/**
	 * {@inheritDoc}
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera id usuario aprovacao.
	 * 
	 * @return id usuario aprovacao
	 */
	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}

	/**
	 * Preenche id usuario aprovacao.
	 * 
	 * @param idUsuarioAprovacao
	 *            o novo id usuario aprovacao
	 */
	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}
	
	/**
	 * Atualizar id usuario.
	 */
	@PrePersist	@PreUpdate
	public void atualizarIdUsuario() {
		if(!(this instanceof Historico)){
			if(InformacoesUsuarioCAPES.getInstance() != null){
				idUsuarioAprovacao = InformacoesUsuarioCAPES.getInstance().getLogin();
			}
		}
	}

}