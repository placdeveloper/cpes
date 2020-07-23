/*
 * SICOOB
 * 
 * Autorizacao.java(br.com.sicoob.capes.negocio.entidades.Autorizacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe que representa a Autorização
 *
 * @author Rodrigo.Chaves
 */
@Entity
@Table(name = "AUTORIZACAO", schema = "CLI")
public class Autorizacao extends CAPESEntidade<Long> {

	/** Serial UID */
	private static final long serialVersionUID = -7594634789402466300L;

	/** O atributo id autorizacao. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDAUTORIZACAO")
	private Long idAutorizacao;

	/** O atributo pessoa. */
	@ManyToOne(optional = false)
	@JoinColumn(name = "IDPESSOA")
	private Pessoa pessoa;

	/** O atributo instituicao origem. */
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "idInstituicao", column = @Column(name = "IDINSTITUICAOORIGEM", nullable = false)),
		@AttributeOverride(name = "idUnidadeInst", column = @Column(name = "IDUNIDADEINSTORIGEM", nullable = false)) })
	private Instituicao instituicaoOrigem;

	/** O atributo instituicao destino. */
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "idInstituicao", column = @Column(name = "IDINSTITUICAODESTINO", nullable = false)),
		@AttributeOverride(name = "idUnidadeInst", column = @Column(name = "IDUNIDADEINSTDESTINO", nullable = false)) })
	private Instituicao instituicaoDestino;

	/** O atributo tipo autorizacao. */
	@Column(name = "DESCTIPOAUTORIZACAO", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoAutorizacaoEnum tipoAutorizacao;

	/** O atributo tipo operacao. */
	@Column(name = "CODTIPOOPERACAO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoOperacaoEnum tipoOperacao;

	/** O atributo alteracao. */
	@Column(name = "DESCALTERACAO")
	private String alteracao;

	/** O atributo data hora cadastro. */
	@Column(name = "DATAHORACADASTRO", nullable = false)
	private DateTimeDB dataHoraCadastro;

	/** O atributo data hora solicitacao. */
	@Column(name = "DATAHORASOLICITACAO")
	private DateTimeDB dataHoraSolicitacao;

	/** O atributo id registro antigo. */
	@Column(name = "IDREGISTROANTIGO")
	private Long idRegistroAntigo;

	/** O atributo id registro novo. */
	@Column(name = "IDREGISTRONOVO")
	private Long idRegistroNovo;

	/** O atributo documentos. */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "autorizacao")
	private Set<AutorizacaoDocumento> documentos;

	/** O atributo sigla processo. */
	@Column(name = "SIGLAPROCESSO", length = 40, nullable = true)
	private String siglaProcesso;

	/** O atributo devolvido. */
	@Column(name = "BOLDEVOLVIDO", nullable = false)
	private Boolean devolvido = Boolean.FALSE;

	/** O atributo id registro controlado. */
	@Column(name = "CODREGISTROCONTROLADO", nullable = false, length = 40, updatable = false)
	private String idRegistroControlado;

	/**
	 * Nome da pessoa. Utilizado para exibição na grid de 'Autorizacoes nao encaminhadas'.
	 * @see PessoaCompartilhamento#getNomePessoa()
	 */
	@Transient
	private String nomePessoa;

	/**
	 * Construtor
	 */
	public Autorizacao() {
	}

	/**
	 * Construtor
	 *
	 * @param cpfCnpj
	 * @param dataHoraCadastro
	 */
	public Autorizacao(String cpfCnpj, Date dataHoraCadastro) {
		this.pessoa = new Pessoa();
		this.pessoa.setCpfCnpj(cpfCnpj);
		this.dataHoraCadastro = new DateTimeDB(dataHoraCadastro.getTime());
	}

	/**
	 * Construtor
	 *
	 * @param idPessoa
	 * @param cpfCnpj
	 * @param nomePessoa
	 * @param dataHoraCadastro
	 */
	public Autorizacao(Integer idPessoa, String cpfCnpj, String nomePessoa, Date dataHoraCadastro) {
		this.pessoa = new Pessoa();
		this.pessoa.setIdPessoa(idPessoa);
		this.pessoa.setCpfCnpj(cpfCnpj);
		this.nomePessoa = nomePessoa;
		this.dataHoraCadastro = new DateTimeDB(dataHoraCadastro.getTime());
	}

	/**
	 * Recupera id autorizacao.
	 * 
	 * @return id autorizacao
	 */
	public Long getIdAutorizacao() {
		return this.idAutorizacao;
	}

	/**
	 * Preenche id autorizacao.
	 * 
	 * @param idAutorizacao
	 *            o novo id autorizacao
	 */
	public void setIdAutorizacao(Long idAutorizacao) {
		this.idAutorizacao = idAutorizacao;
	}

	/**
	 * Recupera pessoa.
	 * 
	 * @return pessoa
	 */
	public Pessoa getPessoa() {
		return this.pessoa;
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
	 * Recupera instituicao origem.
	 * 
	 * @return instituicao origem
	 */
	public Instituicao getInstituicaoOrigem() {
		return this.instituicaoOrigem;
	}

	/**
	 * Preenche instituicao origem.
	 * 
	 * @param instituicaoOrigem
	 *            o novo instituicao origem
	 */
	public void setInstituicaoOrigem(Instituicao instituicaoOrigem) {
		this.instituicaoOrigem = instituicaoOrigem;
	}

	/**
	 * Recupera instituicao destino.
	 * 
	 * @return instituicao destino
	 */
	public Instituicao getInstituicaoDestino() {
		return this.instituicaoDestino;
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
	 * Recupera tipo autorizacao.
	 * 
	 * @return tipo autorizacao
	 */
	public TipoAutorizacaoEnum getTipoAutorizacao() {
		return this.tipoAutorizacao;
	}

	/**
	 * Preenche tipo autorizacao.
	 * 
	 * @param tipoAutorizacao
	 *            o novo tipo autorizacao
	 */
	public void setTipoAutorizacao(TipoAutorizacaoEnum tipoAutorizacao) {
		this.tipoAutorizacao = tipoAutorizacao;
	}

	/**
	 * Recupera tipo operacao.
	 * 
	 * @return tipo operacao
	 */
	public TipoOperacaoEnum getTipoOperacao() {
		return this.tipoOperacao;
	}

	/**
	 * Preenche tipo operacao.
	 * 
	 * @param tipoOperacao
	 *            o novo tipo operacao
	 */
	public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	/**
	 * Recupera alteracao.
	 * 
	 * @return alteracao
	 */
	public String getAlteracao() {
		return this.alteracao;
	}

	/**
	 * Preenche alteracao.
	 * 
	 * @param alteracao
	 *            o novo alteracao
	 */
	public void setAlteracao(String alteracao) {
		this.alteracao = alteracao;
	}

	/**
	 * Recupera data hora solicitacao.
	 * 
	 * @return data hora solicitacao
	 */
	public DateTimeDB getDataHoraSolicitacao() {
		return this.dataHoraSolicitacao;
	}

	/**
	 * Preenche data hora solicitacao.
	 * 
	 * @param dataHoraSolicitacao
	 *            o novo data hora solicitacao
	 */
	public void setDataHoraSolicitacao(DateTimeDB dataHoraSolicitacao) {
		this.dataHoraSolicitacao = dataHoraSolicitacao;
	}

	/**
	 * Recupera o ID do registro antigo conforme a seguinte tabela:
	 *
	 * <pre>
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#I}	: <code>null</code>
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#E}	: o ID do registro a excluir
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#A}	: o ID do registro contendo as informações antigas
	 * </pre>
	 *
	 * @return o ID do registro antigo
	 */
	public Long getIdRegistroAntigo() {
		return this.idRegistroAntigo;
	}

	/**
	 * Define o ID do registro antigo conforme a seguinte tabela:
	 *
	 * <pre>
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#I}	: <code>null</code>
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#E}	: o ID do registro a excluir
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#A}	: o ID do registro contendo as informações antigas
	 * </pre>
	 */
	public void setIdRegistroAntigo(Long idRegistroAntigo) {
		this.idRegistroAntigo = idRegistroAntigo;
	}

	/**
	 * Recupera o ID do registro novo conforme a seguinte tabela:
	 *
	 * <pre>
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#I}	: o ID do novo registro
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#E}	: {@code null}
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#A}	: o ID do registro contendo as novas informações
	 * </pre>
	 *
	 * @return o ID do novo registro
	 */
	public Long getIdRegistroNovo() {
		return this.idRegistroNovo;
	}

	/**
	 * Define o ID do registro novo conforme a seguinte tabela:
	 *
	 * <pre>
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#I}	: o ID do novo registro
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#E}	: {@code null}
	 * {@link #getTipoOperacao()} == {@link TipoOperacaoEnum#A}	: o ID do registro contendo as novas informações
	 * </pre>
	 */
	public void setIdRegistroNovo(Long idRegistroNovo) {
		this.idRegistroNovo = idRegistroNovo;
	}

	/**
	 * Recupera documentos.
	 * 
	 * @return documentos
	 */
	public Set<AutorizacaoDocumento> getDocumentos() {
		return this.documentos;
	}

	/**
	 * Preenche documentos.
	 * 
	 * @param documentos
	 *            o novo documentos
	 */
	public void setDocumentos(Set<AutorizacaoDocumento> documentos) {
		this.documentos = documentos;
	}

	/**
	 * Recupera data hora cadastro.
	 * 
	 * @return data hora cadastro
	 */
	public DateTimeDB getDataHoraCadastro() {
		return this.dataHoraCadastro;
	}

	/**
	 * Preenche data hora cadastro.
	 * 
	 * @param dataHoraCadastro
	 *            o novo data hora cadastro
	 */
	public void setDataHoraCadastro(DateTimeDB dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	/**
	 * Recupera sigla processo.
	 * 
	 * @return sigla processo
	 */
	public String getSiglaProcesso() {
		return this.siglaProcesso;
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
	 * Recupera devolvido.
	 * 
	 * @return devolvido
	 */
	public Boolean getDevolvido() {
		return this.devolvido;
	}

	/**
	 * Preenche devolvido.
	 * 
	 * @param devolvido
	 *            o novo devolvido
	 */
	public void setDevolvido(Boolean devolvido) {
		this.devolvido = devolvido;
	}

	/**
	 * Gets the nome da pessoa. Utilizado para exibição na grid de 'Autorizacoes nao encaminhadas'.
	 * 
	 * @return the nome da pessoa
	 */
	public String getNomePessoa() {
		return this.nomePessoa;
	}

	/**
	 * Sets the nome da pessoa. Utilizado para exibição na grid de 'Autorizacoes nao encaminhadas'.
	 * 
	 * @param nomePessoa
	 *            the new nome da pessoa
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * Recupera id registro controlado.
	 * 
	 * @return id registro controlado
	 */
	public String getIdRegistroControlado() {
		return this.idRegistroControlado;
	}

	/**
	 * Preenche id registro controlado.
	 * 
	 * @param idRegistroControlado
	 *            o novo id registro controlado
	 */
	public void setIdRegistroControlado(String idRegistroControlado) {
		this.idRegistroControlado = idRegistroControlado;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Long getId() {
		return getIdAutorizacao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdAutorizacao(id);
	}

	/**
	 * Recupera id registro em autorizacao.
	 * 
	 * @return id registro em autorizacao
	 */
	@Transient
	public Long getIdRegistroEmAutorizacao() {
		Long id = null;
		if (TipoOperacaoEnum.E.equals(getTipoOperacao())) {
			id = getIdRegistroAntigo();
		} else {
			id = getIdRegistroNovo();
		}
		return id;
	}

	/**
	 * Verifica se é solicitado.
	 * 
	 * @return true, se for solicitado
	 */
	@Transient
	public boolean isSolicitado() {
		return getDataHoraSolicitacao() != null;
	}

	/**
	 * Obter situacao.
	 * 
	 * @return situacao registro enum
	 */
	public SituacaoRegistroEnum obterSituacao(){
		SituacaoRegistroEnum situacao = null;
		if (getDataHoraSolicitacao() != null) {
			if (!getDevolvido()) {
				situacao = SituacaoRegistroEnum.EM_AUTORIZACAO;
			} else {
				situacao = SituacaoRegistroEnum.DEVOLVIDO;
			}
		} else {
			situacao = SituacaoRegistroEnum.A_ENCAMINHAR;
		}
        return situacao;
	}

	/**
	 * On create.
	 */
	@PrePersist
	public void onCreate() {
		String id = getTipoAutorizacao().name() + Aprovavel.SEPARADOR_ID_REGISTRO_CONTROLADO;
		if (TipoOperacaoEnum.I.equals(getTipoOperacao())) {
			id += getIdRegistroNovo();
		} else {
			id += getIdRegistroAntigo();
		}
		setIdRegistroControlado(id);
	}
}