/*
 * SICOOB
 * 
 * HistoricoPessoaCompartilhamento.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaCompartilhamento)
 */
package br.com.sicoob.capes.relatorio.negocio.vo;

import java.util.Date;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * Entidade que representa o histórico de pessoa.
 * @author Tiago.Stangarlin
 */
public abstract class HistoricoPessoaCompartilhamentoVO {
	
	private Long id;
	
	private Pessoa pessoa;
	
	private String idUsuarioEnvio;
	
	private String nomePessoa;
	
	private Date dataHoraFim;
	
	private String nomeApelido;
	
	private String nomeCompleto;
	
	private CnaeFiscal cnae;	
	
	private String descricao;
	
	private PerfilCadastro perfilCadastro;	
	
	private Date dataInclusaoSFN;
	
	private Date dataInclusaoSistema;
	
	private AtividadeEconomica atividadeEconomica;
	
	private Boolean autorizaConsultaBacen;

	private DateTimeDB dataHoraInicio;
	
	private String idUsuarioExclusao;
	
	private DateTimeDB dataRenovacaoCadastral;
	
	private String idUsuarioRenovacao;
	
	private Short idInstituicaoRenovacao;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
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