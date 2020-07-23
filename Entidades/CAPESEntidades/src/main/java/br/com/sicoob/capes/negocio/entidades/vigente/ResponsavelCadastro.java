/*
 * SICOOB
 * 
 * ResponsavelCadastro.java(br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.negocio.entidades.ResponsavelCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * Classe que representa o responsável pelo cadastro da pessoa. 
 * @author Juan.Damasceno
 *
 */
@Entity
@Table(name = "INSTITUICAORESPONSAVELCADASTRO", schema = "CLI")
@CamposAutorizacao(id = "idPessoaCompartilhamento", camposExibicao = { 
		@CampoAutorizacao(propriedade = "dataHoraInicio", label = "DATA DO CADASTRO", 
				formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy") })
public class ResponsavelCadastro extends ResponsavelCadastroBase implements Aprovavel, Vigente, CadastroValidavel {
	
	/** Serial UID. */
	private static final long serialVersionUID = 6081486222089142399L;
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/** O atributo id instituicao atualizacao. */
	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;
	
	/** O atributo codigo situacao aprovacao. */
	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL THEN 1 "
	        + "ELSE COALESCE((SELECT CASE WHEN A.DATAHORASOLICITACAO IS NOT NULL AND "
	        + "A.BOLDEVOLVIDO = 0 THEN 2 "
	        + "WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 end "
	        + "FROM CLI.AUTORIZACAO A WHERE A.DESCTIPOAUTORIZACAO = 'RESPONSAVEL' "
	        + "AND (A.IDREGISTROANTIGO = IDPESSOACOMPARTILHAMENTO OR "
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
	 * @return the gravarHistorico
	 */
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	/**
	 * @param gravarHistorico the gravarHistorico to set
	 */
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Transient
	public br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum getTipoAutorizacao() {
		return TipoAutorizacaoEnum.RESPONSAVEL;
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

	/**
	 * Implementado devido a interface Aprovavel,
	 * portanto retorna null por  a entidade não 
	 * conter o campo IdUsuarioEnvio.  
	 */
	public String getIdUsuarioEnvio() {
		return null;
	}
	
}