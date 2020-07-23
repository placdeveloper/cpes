/*
 * SICOOB
 * 
 * Tributacao.java(br.com.sicoob.capes.negocio.entidades.vigente.Tributacao)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Formula;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.TributacaoBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoClienteListener;

/**
 * Classe responsavel por representar os dados da tributação
 * 
 * @author Juan.Damasceno
 * 
 */
@Entity
@Table(name = "PESSOATRIBUTACAO", schema="CLI")
@EntityListeners({ ReplicacaoClienteListener.class })
@Filters({ @Filter(name = "dataVigente"),@Filter(name = "dataVigenteAntiga"), @Filter(name = "dataVigenteDatasIguais"), @Filter(name = "imprimirFichaCadastralPrevia"),
    @Filter(name = "imprimirFichaCadastral") })
@CamposAutorizacao(id = "idPessoaCompartilhamento", camposExibicao = {
        @CampoAutorizacao(propriedade = "dataHoraInicio", label = "DATA DO CADASTRO",
                formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData",
                mascara = "dd/MM/yyyy"),
        @CampoAutorizacao(propriedade = "cobrarIR", label = "IRRF",
                formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano"),
        @CampoAutorizacao(propriedade = "cobrarIOF", label = "IOF",
                formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano") })
// @CampoAutorizacao(propriedade = "cobrarCPMF", label = "CPMF", formatador =
// "br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano")
public class Tributacao extends TributacaoBase implements Vigente, Replicavel, Comprovavel,
        CadastroValidavel {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 6645652303659754470L;

	/** O atributo id pessoa compartilhamento. */
	@Id
	@Column(name = "IDPESSOACOMPARTILHAMENTO")
	private Long idPessoaCompartilhamento;

	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/** O atributo pessoa compartilhamento. */
	@OneToOne
	@PrimaryKeyJoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	private PessoaCompartilhamento pessoaCompartilhamento;

	/** O atributo id instituicao atualizacao. */
	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;

	/** O atributo codigo situacao aprovacao. */
	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL THEN 1 "
	        + "ELSE COALESCE ((SELECT CASE WHEN A.DATAHORASOLICITACAO IS NOT NULL "
	        + "AND A.BOLDEVOLVIDO = 0 THEN 2 "
	        + "WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 END "
	        + "FROM CLI.AUTORIZACAO A WHERE A.DESCTIPOAUTORIZACAO = 'TRIBUTACAO' "
	        + "AND (A.IDREGISTROANTIGO = IDPESSOACOMPARTILHAMENTO "
	        + "OR A.IDREGISTRONOVO = IDPESSOACOMPARTILHAMENTO)), 0) END")
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
	public Set<DocumentoComprobatorio> getDocumentosComprobatorios() {
		return documentosComprobatorios;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setDocumentosComprobatorios(Set<DocumentoComprobatorio> documentosComprobatorios) {
		this.documentosComprobatorios = documentosComprobatorios;
	}

	/**
	 * {@inheritDoc}
	 */
	public Boolean getVerificarAutorizacao() {
		return verificarAutorizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVerificarAutorizacao(Boolean verificarAutorizacao) {
		this.verificarAutorizacao = verificarAutorizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer getIdInstituicaoAtualizacao() {
		return idInstituicaoAtualizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setIdInstituicaoAtualizacao(Integer idInstituicaoAtualizacao) {
		this.idInstituicaoAtualizacao = idInstituicaoAtualizacao;
	}

	/**
	 * @return the pessoa
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	/**
	 * @param pessoa
	 *            the pessoa to set
	 */
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	/**
	 * @return the gravarHistorico
	 */
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	/**
	 * @param gravarHistorico
	 *            the gravarHistorico to set
	 */
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}

	/**
	 * {@inheritDoc}
	 */
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
	 * {@inheritDoc}
	 */
	@Transient
	public br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum getTipoAutorizacao() {
		return TipoAutorizacaoEnum.TRIBUTACAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getIdRegistroControlado() {
		if (idRegistroControlado == null) {
			idRegistroControlado = getTipoAutorizacao().name() + SEPARADOR_ID_REGISTRO_CONTROLADO
			        + getId();
		}
		return idRegistroControlado;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setIdRegistroControlado(String idRegistroControlado) {
		this.idRegistroControlado = idRegistroControlado;
	}

	/**
	 * {@inheritDoc}
	 */
	public Short getCodigoSituacaoAprovacao() {
		return codigoSituacaoAprovacao;
	}

	/**
	 * Preenche codigo situacao aprovacao.
	 * 
	 * @param codigoSituacaoAprovacao
	 *            o novo codigo situacao aprovacao
	 */
	public void setCodigoSituacaoAprovacao(Short codigoSituacaoAprovacao) {
		this.codigoSituacaoAprovacao = codigoSituacaoAprovacao;
	}

	/**
	 * Recupera situacao aprovacao.
	 * 
	 * @return situacao aprovacao
	 */
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
	public void setSituacaoAprovacao(SituacaoRegistroEnum situacaoAprovacao) {
		this.situacaoAprovacao = situacaoAprovacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	public String getIdUsuarioEnvio() {
		return null;
	}


}