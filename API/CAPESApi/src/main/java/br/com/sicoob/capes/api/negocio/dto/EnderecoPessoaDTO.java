/*
 * SICOOB
 * 
 * EnderecoPessoaDTO.java(br.com.sicoob.capes.api.negocio.dto.EnderecoPessoaDTO)
 */
package br.com.sicoob.capes.api.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * @author Lucas.Borges
 */
public class EnderecoPessoaDTO extends BancoobDto {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -6820440130459867825L;

	/** O atributo id endereco. */
	private Long idEndereco;
	
	/** O atributo codigo tipo endereco. */
	private Short codigoTipoEndereco;
	
	/** O atributo id pessoa compartilhamento. */
	private Long idPessoaCompartilhamento;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo numero. */
	private String numero;
	
	/** O atributo complemento. */
	private String complemento;
	
	/** O atributo id tipo logradouro. */
	private Short idTipoLogradouro;
	
	/** O atributo bairro. */
	private String bairro;
	
	/** O atributo id localidade. */
	private Integer idLocalidade;
	
	/** O atributo cep. */
	private String cep;
	
	/** O atributo data hora inicio. */
	private DateTimeDB dataHoraInicio;
	
	/** O atributo gravar historico. */
	private Boolean gravarHistorico = Boolean.TRUE;
	
	/** O atributo id instituicao atualizacao. */
	private Integer idInstituicaoAtualizacao;
	
	/** O atributo id registro controlado. */
	private String idRegistroControlado;
	
	/** O atributo codigo situacao aprovacao. */
	private Short codigoSituacaoAprovacao;
	
	/** O atributo verificar autorizacao. */
	private Boolean verificarAutorizacao = Boolean.TRUE;

	/**
	 * Recupera codigo tipo endereco.
	 * 
	 * @return codigo tipo endereco
	 */
	public Short getCodigoTipoEndereco() {
		return codigoTipoEndereco;
	}

	/**
	 * Preenche codigo tipo endereco.
	 * 
	 * @param codigoTipoEndereco
	 *            o novo codigo tipo endereco
	 */
	public void setCodigoTipoEndereco(Short codigoTipoEndereco) {
		this.codigoTipoEndereco = codigoTipoEndereco;
	}

	/**
	 * Recupera id pessoa compartilhamento.
	 * 
	 * @return id pessoa compartilhamento
	 */
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	/**
	 * Preenche id pessoa compartilhamento.
	 * 
	 * @param idPessoaCompartilhamento
	 *            o novo id pessoa compartilhamento
	 */
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Preenche descricao.
	 * 
	 * @param descricao
	 *            o novo descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera numero.
	 * 
	 * @return numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Preenche numero.
	 * 
	 * @param numero
	 *            o novo numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Recupera complemento.
	 * 
	 * @return complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Preenche complemento.
	 * 
	 * @param complemento
	 *            o novo complemento
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Recupera id tipo logradouro.
	 * 
	 * @return id tipo logradouro
	 */
	public Short getIdTipoLogradouro() {
		return idTipoLogradouro;
	}

	/**
	 * Preenche id tipo logradouro.
	 * 
	 * @param idTipoLogradouro
	 *            o novo id tipo logradouro
	 */
	public void setIdTipoLogradouro(Short idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}

	/**
	 * Recupera bairro.
	 * 
	 * @return bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Preenche bairro.
	 * 
	 * @param bairro
	 *            o novo bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Recupera id localidade.
	 * 
	 * @return id localidade
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * Preenche id localidade.
	 * 
	 * @param idLocalidade
	 *            o novo id localidade
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * Recupera cep.
	 * 
	 * @return cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Preenche cep.
	 * 
	 * @param cep
	 *            o novo cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

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
	 * Recupera verificar autorizacao.
	 * 
	 * @return verificar autorizacao
	 */
	public Boolean getVerificarAutorizacao() {
		return verificarAutorizacao;
	}

	/**
	 * Preenche verificar autorizacao.
	 * 
	 * @param verificarAutorizacao
	 *            o novo verificar autorizacao
	 */
	public void setVerificarAutorizacao(Boolean verificarAutorizacao) {
		this.verificarAutorizacao = verificarAutorizacao;
	}

	/**
	 * Recupera id instituicao atualizacao.
	 * 
	 * @return id instituicao atualizacao
	 */
	public Integer getIdInstituicaoAtualizacao() {
		return idInstituicaoAtualizacao;
	}

	/**
	 * Preenche id instituicao atualizacao.
	 * 
	 * @param idInstituicaoAtualizacao
	 *            o novo id instituicao atualizacao
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
	 * Recupera id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return getIdEndereco();
	}

	/**
	 * Preenche id.
	 * 
	 * @param id
	 *            o novo id
	 */
	public void setId(Long id) {
		setIdEndereco(id);
	}

	/**
	 * Recupera id registro controlado.
	 * 
	 * @return id registro controlado
	 */
	public String getIdRegistroControlado() {
		return idRegistroControlado;
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
	 * Recupera codigo situacao aprovacao.
	 * 
	 * @return codigo situacao aprovacao
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
}
