/*
 * SICOOB
 * 
 * Produtividade.java(br.com.sicoob.capes.negocio.entidades.vigente.Produtividade)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.ProdutividadeBase;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;
import flexjson.JSON;

/**
 * The Class Produtividade.
 */
@Entity
@Table(name="PESSOAPRODUTIVIDADE", schema = "CLI")
@EntityListeners({ReplicacaoListener.class})
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "dataVigenteDatasIguais"),
	@Filter(name = "imprimirFichaCadastral")
})
@CamposAutorizacao(id = "idProdutividade", camposExibicao = {
		@CampoAutorizacao(propriedade = "dataHoraInicio", label = "DATA DO CADASTRO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "anoInicioSafra", label = "ANO SAFRA"),
		@CampoAutorizacao(propriedade = "empreendimento.descricao", label = "EMPREENDIMENTO"),
		@CampoAutorizacao(propriedade = "bemImovel.denominacao", label = "DENOMINAÇÃO DO IMÓVEL"),
		@CampoAutorizacao(propriedade = "descricao", label = "DESCRIÇÃO"),
		@CampoAutorizacao(propriedade = "producao", label = "PRODUÇÃO"),
		@CampoAutorizacao(propriedade = "quantidadeOuArea", label = "ÁREA/QUANTIDADE", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal"),
		@CampoAutorizacao(propriedade = "percentualRebate", label = "PERCENTUAL DE REBATE", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario"),
		@CampoAutorizacao(propriedade = "valorPrecoMedio", label = "PREÇO MÉDIO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario"),
		@CampoAutorizacao(propriedade = "valorRendaBruta", label = "RENDA BRUTA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario"),
		@CampoAutorizacao(propriedade = "situacao", label = "SITUAÇÃO") })
public class Produtividade extends ProdutividadeBase implements Replicavel, Vigente, Comprovavel, CadastroValidavel {

	/** Serial UID.*/
	private static final long serialVersionUID = 3841821981259270200L;

	/** O atributo id produtividade. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDPESSOAPRODUTIVIDADE")
	private Long idProdutividade;

	/** O atributo id registro controlado. */
	@Formula("COALESCE((SELECT A.DESCTIPOAUTORIZACAO || '" + SEPARADOR_ID_REGISTRO_CONTROLADO
			+ "' || TO_CHAR(A.IDREGISTROANTIGO) FROM CLI.AUTORIZACAO A "
			+ "WHERE A.DESCTIPOAUTORIZACAO = 'PRODUTIVIDADE' AND A.CODTIPOOPERACAO = 'A' "
			+ "AND A.IDREGISTRONOVO = IDPESSOAPRODUTIVIDADE FETCH FIRST 1 ROWS ONLY), 'PRODUTIVIDADE"
			+ SEPARADOR_ID_REGISTRO_CONTROLADO + "' || IDPESSOAPRODUTIVIDADE)")
	private String idRegistroControlado;
	
	/** O atributo codigo situacao aprovacao. */
	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL THEN 1 "
	        + "ELSE COALESCE ((SELECT case WHEN A.DATAHORASOLICITACAO IS NOT NULL "
	        + "AND A.BOLDEVOLVIDO = 0 THEN 2 "
	        + "WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 END "
	        + "FROM CLI.AUTORIZACAO A WHERE A.DESCTIPOAUTORIZACAO = 'PRODUTIVIDADE' "
	        + "AND (A.IDREGISTROANTIGO = IDPESSOAPRODUTIVIDADE "
	        + "OR A.IDREGISTRONOVO = IDPESSOAPRODUTIVIDADE)), 0) END")
	private Short codigoSituacaoAprovacao;
	
	/** O atributo situacao aprovacao. */
	@Transient
	private SituacaoRegistroEnum situacaoAprovacao;

	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/** O atributo data finalizacao. */
	@Transient
	private DateTimeDB dataFinalizacao;

	/** O atributo bem imovel. */
	@ManyToOne
	@JoinColumn(name = "IDBEM", insertable = false, updatable = false)
	private BemImovel bemImovel;
	
	/** O atributo bem imovel. */
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "IDBEMPESSOA", insertable = false, updatable = false)
	private br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel bemImovelAntigo;

	/** O atributo id instituicao atualizacao. */
	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;

	/** O atributo verificar autorizacao. */
	@Transient
	private Boolean verificarAutorizacao = Boolean.TRUE;
	
	/** O atributo documentos comprobatorios. */
	@Transient
	private Set<DocumentoComprobatorio> documentosComprobatorios;

	/** O atributo produtor. */
	@ManyToOne
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	private Produtor produtor;
	
	/** O atributo idUsuarioEnvio. */
	@Column(name="IDUSUARIOENVIO")
	private String idUsuarioEnvio;

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
	 * Recupera id produtividade.
	 * 
	 * @return id produtividade
	 */
	public Long getIdProdutividade() {
		return idProdutividade;
	}

	/**
	 * Preenche id produtividade.
	 * 
	 * @param idProdutividade
	 *            o novo id produtividade
	 */
	public void setIdProdutividade(Long idProdutividade) {
		this.idProdutividade = idProdutividade;
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
		return getIdProdutividade();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long identificador) {
		setIdProdutividade(identificador);
	}

	/**
	 * @return the dataFinalizacao
	 */
	public DateTimeDB getDataFinalizacao() {
		return dataFinalizacao;
	}

	/**
	 * @param dataFinalizacao the dataFinalizacao to set
	 */
	public void setDataFinalizacao(DateTimeDB dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	/**
	 * @return the transicoes
	 */
	@JSON(include = false)
	public BemImovel getBemImovel() {
		return bemImovel;
	}

	/**
	 * Preenche bem imovel.
	 * 
	 * @param bemImovel
	 *            o novo bem imovel
	 */
	public void setBemImovel(BemImovel bemImovel) {
		this.bemImovel = bemImovel;
	}
	
	/**
	 * @return the transicoes
	 */
	@JSON(include = false)
	public br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel getBemImovelAntigo() {
		return bemImovelAntigo;
	}

	/**
	 * Preenche bem imovel.
	 * 
	 * @param bemImovelAntigo
	 *            o novo bem imovel
	 */
	public void setBemImovelAntigo(br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel bemImovelAntigo) {
		this.bemImovelAntigo = bemImovelAntigo;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Transient
	public br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum getTipoAutorizacao() {
		return TipoAutorizacaoEnum.PRODUTIVIDADE;
	}

	/** 
	 * {@inheritDoc}
	 */
	public String getIdRegistroControlado() {
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

	/**
	 * Recupera produtor.
	 * 
	 * @return produtor
	 */
	public Produtor getProdutor() {
		return produtor;
	}

	/**
	 * Preenche produtor.
	 * 
	 * @param produtor
	 *            o novo produtor
	 */
	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
	
	/**
	 * {@inheritDoc}
	 */
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