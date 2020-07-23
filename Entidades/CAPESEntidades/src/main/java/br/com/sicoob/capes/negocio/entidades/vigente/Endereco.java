/*
 * SICOOB
 * 
 * Endereco.java(br.com.sicoob.capes.negocio.entidades.vigente.Endereco)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import br.com.sicoob.capes.negocio.entidades.EnderecoBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;

/**
 * Entidade que representa os endereços das pessoas.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "ENDERECOPESSOA", schema = "CLI")
@EntityListeners({ ReplicacaoListener.class })
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "dataVigenteDatasIguais"),
	@Filter(name = "imprimirFichaCadastral")
})
@CamposAutorizacao(id ="idEndereco", camposExibicao={
		@CampoAutorizacao(propriedade = "dataHoraInicio", label = "DATA DO CADASTRO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "tipoEndereco.descricao", label = "TIPO DE ENDEREÇO"),
		@CampoAutorizacao(propriedade = "cep", label = "CEP", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMascara", mascara = "##.###-###"),
		@CampoAutorizacao(propriedade = "tipoLogradouro.idTipoLogradouro", label = "TIPO DE LOGRADOURO", formatador = "br.com.sicoob.capes.cadastro.util.formatacao.FormatadorTipoLogradouro"),
		@CampoAutorizacao(propriedade = "descricao", label = "LOGRADOURO"),
		@CampoAutorizacao(propriedade = "numero", label = "NÚMERO"),
		@CampoAutorizacao(propriedade = "complemento", label = "COMPLEMENTO"),
		@CampoAutorizacao(propriedade = "bairro", label = "BAIRRO"),
		@CampoAutorizacao(propriedade = "localidade.idLocalidade", label = "MUNICÍPIO", formatador="br.com.sicoob.capes.cadastro.util.formatacao.FormatadorLocalidade") })
public class Endereco extends EnderecoBase implements Replicavel, Comprovavel, Vigente, CadastroValidavel {

	/** Serial UID. */
	private static final long serialVersionUID = 3926350904263145909L;

	/** O atributo id endereco. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENDERECOPESSOA")
	private Long idEndereco;

	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/** O atributo id instituicao atualizacao. */
	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;

	/** O atributo id registro controlado. */
	@Formula("COALESCE((SELECT A.DESCTIPOAUTORIZACAO || '" + SEPARADOR_ID_REGISTRO_CONTROLADO
			+ "' || TO_CHAR(A.IDREGISTROANTIGO) FROM CLI.AUTORIZACAO A "
			+ "WHERE A.DESCTIPOAUTORIZACAO = 'ENDERECO' AND A.CODTIPOOPERACAO = 'A' "
			+ "AND A.IDREGISTRONOVO = IDENDERECOPESSOA FETCH FIRST 1 ROWS ONLY), 'ENDERECO"
			+ SEPARADOR_ID_REGISTRO_CONTROLADO + "' || IDENDERECOPESSOA)")
	private String idRegistroControlado;
	
	/** O atributo codigo situacao aprovacao. */
	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL THEN 1 "
	        + "ELSE COALESCE ((SELECT CASE WHEN A.DATAHORASOLICITACAO IS NOT NULL "
	        + "AND A.BOLDEVOLVIDO = 0 THEN 2 "
	        + "WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 END "
	        + "FROM CLI.AUTORIZACAO A WHERE A.DESCTIPOAUTORIZACAO = 'ENDERECO' "
	        + "AND (A.IDREGISTROANTIGO = IDENDERECOPESSOA OR A.IDREGISTRONOVO = IDENDERECOPESSOA)), 0) END")
	private Short codigoSituacaoAprovacao;
	
	/** O atributo situacao aprovacao. */
	@Transient
	private SituacaoRegistroEnum situacaoAprovacao;
	
	/** O atributo verificar autorizacao. */
	@Transient
	private Boolean verificarAutorizacao = Boolean.TRUE;

	/** O atributo documentos comprobatorios. */
	@Transient
	private Set<DocumentoComprobatorio> documentosComprobatorios;
	
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
	 * @return the idEndereco
	 */
	public Long getIdEndereco() {
		return idEndereco;
	}

	/**
	 * @param idEndereco
	 *            the idEndereco to set
	 */
	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
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
		return getIdEndereco();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdEndereco(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Transient
	public br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum getTipoAutorizacao() {
		return TipoAutorizacaoEnum.ENDERECO;
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