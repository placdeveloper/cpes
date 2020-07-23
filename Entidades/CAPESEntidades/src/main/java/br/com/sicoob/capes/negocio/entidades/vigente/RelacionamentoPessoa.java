/*
 * SICOOB
 * 
 * RelacionamentoPessoa.java(br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.InheritanceType.JOINED;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Formula;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.NaoVerificarGestorResponsavel;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;
import flexjson.JSON;

/**
 * Entidade que representa os relacionamentos entre pessoas
 * 
 * 24/08/2011
 * 
 * @author Rodrigo.Chaves
 */
@Entity
@Table(schema = "CLI", name = "RELACIONAMENTOPESSOA")
@Inheritance(strategy = JOINED)
@EntityListeners({ ReplicacaoListener.class })
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "dataVigenteDatasIguais"),
	@Filter(name = "imprimirFichaCadastral")
})
@CamposAutorizacao(id = "idRelacionamento", camposExibicao = {
		@CampoAutorizacao(propriedade = "dataHoraInicio", label = "DATA DO CADASTRO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "pessoaRelacionada.cpfCnpj", label = "PESSOA RELACIONADA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorCpfCnpj"),
		@CampoAutorizacao(propriedade = "tipoRelacionamento.descricao", label = "TIPO DE RELACIONAMENTO"),
		@CampoAutorizacao(propriedade = "percentualCapitalEmpresa", label = "PERCENTUAL DO CAPITAL SOCIAL", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal"),
		@CampoAutorizacao(propriedade = "dataInicioMandato", label = "DATA DE INÍCIO DO MANDATO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "dataFimMandato", label = "DATA DE FIM DO MANDATO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy") })
@NaoVerificarGestorResponsavel
public class RelacionamentoPessoa extends RelacionamentoPessoaBase implements Vigente, Replicavel, Comprovavel, CadastroValidavel {

	/** Serial UID */
	private static final long serialVersionUID = -4577699465497846904L;

	/** O atributo id relacionamento. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDRELACIONAMENTOPESSOA")
	private Long idRelacionamento;

	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/** O atributo poderes. */
	@ManyToMany(fetch = EAGER)
	@JoinTable(schema = "CLI", name = "RELACIONAMENTOPESSOAPODER", 
			inverseJoinColumns =  @JoinColumn(name = "CODTIPOPODERRELACIONAMENTO"), 
			joinColumns = @JoinColumn(name = "IDRELACIONAMENTOPESSOA"))
	private Set<TipoPoderRelacionamento> poderes;

	/** O atributo id instituicao atualizacao. */
	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;

	/** O atributo id registro controlado. */
	@Formula("COALESCE((SELECT A.DESCTIPOAUTORIZACAO || '" + SEPARADOR_ID_REGISTRO_CONTROLADO
			+ "' || TO_CHAR(A.IDREGISTROANTIGO) FROM CLI.AUTORIZACAO A "
			+ "WHERE A.DESCTIPOAUTORIZACAO = 'RELACIONAMENTO' AND A.CODTIPOOPERACAO = 'A' "
			+ "AND A.IDREGISTRONOVO = IDRELACIONAMENTOPESSOA FETCH FIRST 1 ROWS ONLY), "
			+ "'RELACIONAMENTO" + SEPARADOR_ID_REGISTRO_CONTROLADO + "' || IDRELACIONAMENTOPESSOA)")
	private String idRegistroControlado;
	
	/** O atributo codigo situacao aprovacao. */
	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL "
			+ "THEN 1 ELSE COALESCE((SELECT CASE WHEN A.DATAHORASOLICITACAO IS NOT NULL AND "
			+ "A.BOLDEVOLVIDO = 0 THEN 2 WHEN A.DATAHORASOLICITACAO IS NOT NULL AND "
			+ "A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 END FROM CLI.AUTORIZACAO A WHERE "
			+ "A.DESCTIPOAUTORIZACAO = 'RELACIONAMENTO' AND("
			+ "A.IDREGISTROANTIGO = IDRELACIONAMENTOPESSOA OR "
			+ "A.IDREGISTRONOVO = IDRELACIONAMENTOPESSOA)), 0) END")
	private Short codigoSituacaoAprovacao;
	
	/** O atributo relacionamento reverso. */
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "IDRELACIONAMENTOOPOSTO")
	private RelacionamentoPessoa relacionamentoReverso;
	
	/** O atributo situacao aprovacao. */
	@Transient
	private SituacaoRegistroEnum situacaoAprovacao;

	/** O atributo verificar autorizacao. */
	@Transient
	private Boolean verificarAutorizacao = Boolean.TRUE;
	
	/** O atributo documentos comprobatorios. */
	@Transient
	private Set<DocumentoComprobatorio> documentosComprobatorios;
	
	/** O atributo produto bancoob. */
	@Transient
	private Boolean produtoBancoob = Boolean.FALSE;
	
	/** O atributo usado na validação do percentual capital. O registro antigo não pode ser somado no capital total */
	@Transient
	private transient Long idRelacionamentoAntigo;
	
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
	 * Recupera id relacionamento.
	 * 
	 * @return id relacionamento
	 */
	public Long getIdRelacionamento() {
		return this.idRelacionamento;
	}
	
	/**
	 * Preenche id relacionamento.
	 * 
	 * @param id
	 *            o novo id relacionamento
	 */
	public void setIdRelacionamento(Long id) {
		this.idRelacionamento = id;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdRelacionamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdRelacionamento(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getGravarHistorico() {
		return this.gravarHistorico;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}
	
	/**
	 * Recupera relacionamento reverso.
	 * 
	 * @return relacionamento reverso
	 */
	public RelacionamentoPessoa getRelacionamentoReverso() {
		return this.relacionamentoReverso;
	}
 
	/**
	 * Preenche relacionamento reverso.
	 * 
	 * @param relacionamentoReverso
	 *            o novo relacionamento reverso
	 */
	public void setRelacionamentoReverso(RelacionamentoPessoa relacionamentoReverso) {
		this.relacionamentoReverso = relacionamentoReverso;
	}
	

	/**
	 * @return o valor de poderes
	 */
	@JSON(include = false)
	public Set<TipoPoderRelacionamento> getPoderes() {
		return poderes;
	}

	/**
	 * @param poderes o valor de poderes
	 */
	public void setPoderes(Set<TipoPoderRelacionamento> poderes) {
		this.poderes = poderes;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idRelacionamento");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idRelacionamento");
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum getTipoAutorizacao() {
		return TipoAutorizacaoEnum.RELACIONAMENTO;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getIdRegistroControlado() {
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
		if ((situacaoAprovacao== null) && (codigoSituacaoAprovacao != null)) {
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
	 * Recupera produto bancoob.
	 * 
	 * @return produto bancoob
	 */
	public Boolean getProdutoBancoob() {
		return produtoBancoob;
	}

	/**
	 * Preenche produto bancoob.
	 * 
	 * @param produtoBancoob
	 *            o novo produto bancoob
	 */
	public void setProdutoBancoob(Boolean produtoBancoob) {
		this.produtoBancoob = produtoBancoob;
	}

	/**
	 * Recupera o IdRelacionamentoAntigo
	 * 
	 * @return
	 */
	public Long getIdRelacionamentoAntigo() {
		return idRelacionamentoAntigo;
	}

	/**
	 * Preenche o idRelacionamentoAntigo
	 * 
	 * @param idRelacionamentoAntigo
	 */
	public void setIdRelacionamentoAntigo(Long idRelacionamentoAntigo) {
		this.idRelacionamentoAntigo = idRelacionamentoAntigo;
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
	
}