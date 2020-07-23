/*
 * SICOOB
 * 
 * TransicaoReplicacaoPK.java(br.com.sicoob.capes.negocio.entidades.legado.pk.TransicaoReplicacaoPK)
 */
package br.com.sicoob.capes.negocio.entidades.legado.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;

/**
 * 
 * Entidade Embeddable que representa a chave composta de {@link TransicaoReplicacao}.
 * 
 * @author Juan.Damasceno
 *
 */
@Embeddable
public class TransicaoReplicacaoPK implements Serializable {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo id unidade. */
	private Integer idUnidade;
	
	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo data atualizacao. */
	private Date dataAtualizacao;
	
	/** O atributo id sistema cooperativo. */
	private Integer idSistemaCooperativo;
	
	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	@Column(name="idInstituicao")
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
	 * Recupera id unidade.
	 * 
	 * @return id unidade
	 */
	@Column(name="IdUnidadeInst")
	public Integer getIdUnidade() {
		return idUnidade;
	}
	
	/**
	 * Preenche id unidade.
	 * 
	 * @param idUnidade
	 *            o novo id unidade
	 */
	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}
	
	/**
	 * Recupera id pessoa.
	 * 
	 * @return id pessoa
	 */
	@Column(name="IdPessoaLegado")
	public Integer getIdPessoa() {
		return idPessoa;
	}
	
	/**
	 * Preenche id pessoa.
	 * 
	 * @param idPessoa
	 *            o novo id pessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	/**
	 * Recupera data atualizacao.
	 * 
	 * @return data atualizacao
	 */
	@Column(name="dataAtualizacao")
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	
	/**
	 * Preenche data atualizacao.
	 * 
	 * @param dataAtualizacao
	 *            o novo data atualizacao
	 */
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	/**
	 * @return the idSistemaCooperativo
	 */
	public Integer getIdSistemaCooperativo() {
		return idSistemaCooperativo;
	}
	/**
	 * @param idSistemaCooperativo the idSistemaCooperativo to set
	 */
	public void setIdSistemaCooperativo(Integer idSistemaCooperativo) {
		this.idSistemaCooperativo = idSistemaCooperativo;
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idSistemaCooperativo == null) ? 0 : idSistemaCooperativo
						.hashCode());
		result = prime * result
				+ ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
		result = prime * result
				+ ((idInstituicao == null) ? 0 : idInstituicao.hashCode());
		result = prime * result
				+ ((idPessoa == null) ? 0 : idPessoa.hashCode());
		result = prime * result
				+ ((idUnidade == null) ? 0 : idUnidade.hashCode());
		return result;
	}
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		TransicaoReplicacaoPK other = (TransicaoReplicacaoPK) obj;
		if (idSistemaCooperativo == null) {
			if (other.idSistemaCooperativo != null) {
				return false;
			}
		} else if (!idSistemaCooperativo.equals(other.idSistemaCooperativo)){
			return false;
		}
		if (dataAtualizacao == null) {
			if (other.dataAtualizacao != null) {
				return false;
			}
		} else if (!dataAtualizacao.equals(other.dataAtualizacao)) {
			return false;
		}
		if (idInstituicao == null) {
			if (other.idInstituicao != null) {
				return false;
			}
		} else if (!idInstituicao.equals(other.idInstituicao)){
			return false;
		}
		if (idPessoa == null) {
			if (other.idPessoa != null) {
				return false;
			}
		} else if (!idPessoa.equals(other.idPessoa)) {
			return false;
		}
		if (idUnidade == null) {
			if (other.idUnidade != null) {
				return false;
			}
		} else if (!idUnidade.equals(other.idUnidade)) {
			return false;
		}
		return true;
	}
}
