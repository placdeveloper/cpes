/*
 * SICOOB
 * 
 * InformacaoProfissionalBase.java(br.com.sicoob.capes.negocio.entidades.InformacaoProfissionalBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Erico.Junior
 * 
 */
@MappedSuperclass
public abstract class InformacaoProfissionalBase extends CAPESEntidade<Integer> {

	/** Serial UID. */
	private static final long serialVersionUID = -4668231605621359135L;

	/** O atributo data hora inicio. */
	private DateTimeDB dataHoraInicio;

	/** O atributo id instituicao. */
	private Integer idInstituicao;

	/** O atributo pessoa. */
	@JoinColumn(name = "IDPESSOA")
	@ManyToOne
	private Pessoa pessoa;

	/** O atributo pessoa empregador. */
	@JoinColumn(name = "IDPESSOAEMPREGADOR")
	@ManyToOne
	private Pessoa pessoaEmpregador;

	/** O atributo num matricula. */
	@Column(name = "NUMMATRICULA", length = 80, nullable = false)
	private String numMatricula;

	/** O atributo tipo funcionario. */
	@JoinColumn(name = "CODTIPOFUNCIONARIO", referencedColumnName = "CODTIPOFUNCIONARIO")
	@ManyToOne
	private TipoFuncionario tipoFuncionario;

	/** O atributo tipo afastamento. */
	@JoinColumn(name = "CODTIPOAFASTAMENTO", referencedColumnName = "CODTIPOAFASTAMENTO")
	@ManyToOne
	private TipoAfastamento tipoAfastamento;

	/** O atributo cod situacao. */
	@Column(name = "CODSITUACAOFUNCIONARIO")
	private Short codSituacao;

	/** O atributo cargo. */
	@Column(name = "DESCCARGO", length = 70, nullable = true)
	private String cargo;

	/** O atributo data admissao. */
	private DateTimeDB dataAdmissao;

	/** O atributo dia mes ferias. */
	@Column(name = "DIAMESAQUISICAOFERIAS", length = 4, nullable = true)
	private String diaMesFerias;

	/** O atributo data desligamento. */
	private DateTimeDB dataDesligamento;

	/** O atributo conselho regional. */
	@JoinColumn(name = "CODCONSELHOREGIONAL", referencedColumnName = "CODCONSELHOREGIONAL")
	@ManyToOne
	private ConselhoRegional conselhoRegional;

	/** O atributo uf conselho. */
	@Column(name = "SIGLAUFCONSELHORREGIONAL", length = 2, nullable = true)
	private String ufConselho;

	/** O atributo numero inscricao conselho. */
	@Column(name = "NUMINSCRICAOCONSELHOREGIONAL", length = 7, nullable = true)
	private String numeroInscricaoConselho;

	/** O atributo id usuario aprovacao. */
	@Column(name = "IDUSUARIOAPROV", length = 40)
	private String idUsuarioAprovacao;

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public DateTimeDB getDataHoraInicio() {

		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {

		this.dataHoraInicio = dataHoraInicio;
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
	 * Recupera pessoa empregador.
	 * 
	 * @return pessoa empregador
	 */
	public Pessoa getPessoaEmpregador() {

		return pessoaEmpregador;
	}

	/**
	 * Preenche pessoa empregador.
	 * 
	 * @param pessoaEmpregador
	 *            o novo pessoa empregador
	 */
	public void setPessoaEmpregador(Pessoa pessoaEmpregador) {

		this.pessoaEmpregador = pessoaEmpregador;
	}

	/**
	 * Recupera num matricula.
	 * 
	 * @return num matricula
	 */
	public String getNumMatricula() {

		return numMatricula;
	}

	/**
	 * Preenche num matricula.
	 * 
	 * @param numMatricula
	 *            o novo num matricula
	 */
	public void setNumMatricula(String numMatricula) {

		this.numMatricula = numMatricula;
	}

	/**
	 * Recupera tipo funcionario.
	 * 
	 * @return tipo funcionario
	 */
	public TipoFuncionario getTipoFuncionario() {

		return tipoFuncionario;
	}

	/**
	 * Preenche tipo funcionario.
	 * 
	 * @param tipoFuncionario
	 *            o novo tipo funcionario
	 */
	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {

		this.tipoFuncionario = tipoFuncionario;
	}

	/**
	 * Recupera tipo afastamento.
	 * 
	 * @return tipo afastamento
	 */
	public TipoAfastamento getTipoAfastamento() {

		return tipoAfastamento;
	}

	/**
	 * Preenche tipo afastamento.
	 * 
	 * @param tipoAfastamento
	 *            o novo tipo afastamento
	 */
	public void setTipoAfastamento(TipoAfastamento tipoAfastamento) {

		this.tipoAfastamento = tipoAfastamento;
	}

	/**
	 * Recupera cod situacao.
	 * 
	 * @return cod situacao
	 */
	public Short getCodSituacao() {

		return codSituacao;
	}

	/**
	 * Preenche cod situacao.
	 * 
	 * @param codSituacao
	 *            o novo cod situacao
	 */
	public void setCodSituacao(Short codSituacao) {

		this.codSituacao = codSituacao;
	}

	/**
	 * Recupera cargo.
	 * 
	 * @return cargo
	 */
	public String getCargo() {

		return cargo;
	}

	/**
	 * Preenche cargo.
	 * 
	 * @param cargo
	 *            o novo cargo
	 */
	public void setCargo(String cargo) {

		this.cargo = cargo;
	}

	/**
	 * Recupera data admissao.
	 * 
	 * @return data admissao
	 */
	public DateTimeDB getDataAdmissao() {

		return dataAdmissao;
	}

	/**
	 * Preenche data admissao.
	 * 
	 * @param dataAdmissao
	 *            o novo data admissao
	 */
	public void setDataAdmissao(DateTimeDB dataAdmissao) {

		this.dataAdmissao = dataAdmissao;
	}

	/**
	 * Recupera dia mes ferias.
	 * 
	 * @return dia mes ferias
	 */
	public String getDiaMesFerias() {

		return diaMesFerias;
	}

	/**
	 * Preenche dia mes ferias.
	 * 
	 * @param diaMesFerias
	 *            o novo dia mes ferias
	 */
	public void setDiaMesFerias(String diaMesFerias) {

		this.diaMesFerias = diaMesFerias;
	}

	/**
	 * Recupera data desligamento.
	 * 
	 * @return data desligamento
	 */
	public DateTimeDB getDataDesligamento() {

		return dataDesligamento;
	}

	/**
	 * Preenche data desligamento.
	 * 
	 * @param dataDesligamento
	 *            o novo data desligamento
	 */
	public void setDataDesligamento(DateTimeDB dataDesligamento) {

		this.dataDesligamento = dataDesligamento;
	}

	/**
	 * Recupera conselho regional.
	 * 
	 * @return conselho regional
	 */
	public ConselhoRegional getConselhoRegional() {

		return conselhoRegional;
	}

	/**
	 * Preenche conselho regional.
	 * 
	 * @param conselhoRegional
	 *            o novo conselho regional
	 */
	public void setConselhoRegional(ConselhoRegional conselhoRegional) {

		this.conselhoRegional = conselhoRegional;
	}

	/**
	 * Recupera uf conselho.
	 * 
	 * @return uf conselho
	 */
	public String getUfConselho() {

		return ufConselho;
	}

	/**
	 * Preenche uf conselho.
	 * 
	 * @param ufConselho
	 *            o novo uf conselho
	 */
	public void setUfConselho(String ufConselho) {

		this.ufConselho = ufConselho;
	}

	/**
	 * Recupera numero inscricao conselho.
	 * 
	 * @return numero inscricao conselho
	 */
	public String getNumeroInscricaoConselho() {

		return numeroInscricaoConselho;
	}

	/**
	 * Preenche numero inscricao conselho.
	 * 
	 * @param numeroInscricaoConselho
	 *            o novo numero inscricao conselho
	 */
	public void setNumeroInscricaoConselho(String numeroInscricaoConselho) {

		this.numeroInscricaoConselho = numeroInscricaoConselho;
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
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		
		PessoaCompartilhamento pessoaCompartilhamento = null;
		Pessoa p = getPessoa();
		if (p != null) {
			pessoaCompartilhamento = p.getPessoaCompartilhamento();
		}
		return pessoaCompartilhamento;
	}

	/**
	 * Atualizar id usuario.
	 */
	@PrePersist
	@PreUpdate
	public void atualizarIdUsuario() {

		if (!(this instanceof Historico)) {
			if (InformacoesUsuarioCAPES.getInstance() != null) {
				idUsuarioAprovacao = InformacoesUsuarioCAPES.getInstance().getLogin();
			}
		}
	}

}
