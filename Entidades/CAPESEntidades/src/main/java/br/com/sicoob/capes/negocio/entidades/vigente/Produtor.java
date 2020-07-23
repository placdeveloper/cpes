/*
 * SICOOB
 * 
 * Produtor.java(br.com.sicoob.capes.negocio.entidades.vigente.Produtor)
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
import br.com.sicoob.capes.negocio.entidades.ProdutorBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;

/**
 * The Class Produtor.
 */
@Entity
@Table(name="PESSOAPRODUTOR", schema = "CLI")
@EntityListeners({ReplicacaoListener.class})
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "dataVigenteDatasIguais"),
	@Filter(name = "imprimirFichaCadastral")
})
@CamposAutorizacao(id = "idPessoaCompartilhamento", camposExibicao = {
		@CampoAutorizacao(propriedade = "dataHoraInicio", label = "DATA DO CADASTRO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "codigoInscricao", label = "CÓDIGO DE INSCRIÇÃO"),
		@CampoAutorizacao(propriedade = "categoria.descricao", label = "CATEGORIA DO PRODUTOR"),
		@CampoAutorizacao(propriedade = "qtdTemporario", label = "QUANTIDADE DE FUNCIONÁRIOS TEMPORÁRIOS"),
		@CampoAutorizacao(propriedade = "mesAnoTemporario", label = "DATA DO CONTRATO DE FUNCIONÁRIOS TEMPORÁRIOS", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMascara", mascara = "##/####"),
		@CampoAutorizacao(propriedade = "qtdPermanente", label = "QUANTIDADE DE FUNCIONÁRIOS PERMANENTES"),
		@CampoAutorizacao(propriedade = "mesAnoPermanente", label = "DATA DO CONTRATO DE FUNCIONÁRIOS PERMANENTES", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMascara", mascara = "##/####"),
		@CampoAutorizacao(propriedade = "tipoBeneficiarioSicor.descricao", label = "TIPO BENEFICIÁRIO SICOR")})
public class Produtor extends ProdutorBase implements Replicavel, Vigente, Comprovavel, CadastroValidavel {
		
	/** Serial UID.*/
	private static final long serialVersionUID = 4118547670484786461L;

	/** O atributo id pessoa compartilhamento. */
	@Id
	private Long idPessoaCompartilhamento;

	/** O atributo pessoa compartilhamento. */
	@OneToOne
	@PrimaryKeyJoinColumn
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/** O atributo id instituicao atualizacao. */
	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;
	
	/** O atributo codigo situacao aprovacao. */
	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL "
			+ "THEN 1 ELSE COALESCE((SELECT CASE WHEN A.DATAHORASOLICITACAO IS NOT NULL AND "
			+ "A.BOLDEVOLVIDO = 0 THEN 2 WHEN A.DATAHORASOLICITACAO IS NOT NULL AND "
			+ "A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 END FROM CLI.AUTORIZACAO A WHERE "
			+ "A.DESCTIPOAUTORIZACAO = 'PRODUTOR' AND("
			+ "A.IDREGISTROANTIGO = IDPESSOACOMPARTILHAMENTO OR "
			+ "A.IDREGISTRONOVO = IDPESSOACOMPARTILHAMENTO)), 0) END")
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
	 * @return the pessoaCompartilhamento
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	/**
	 * @param pessoaCompartilhamento the pessoaCompartilhamento to set
	 */
	public void setPessoaCompartilhamento(
			PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	/** 
	 * {@inheritDoc}
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
	public void setId(Long identificador) {
		setIdPessoaCompartilhamento(identificador);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Transient
	public br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum getTipoAutorizacao() {
		return TipoAutorizacaoEnum.PRODUTOR;
	}

	/** 
	 * {@inheritDoc}
	 */
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
	public void setSituacaoAprovacao(SituacaoRegistroEnum situacaoAprovacao) {
		this.situacaoAprovacao = situacaoAprovacao;
	}

}