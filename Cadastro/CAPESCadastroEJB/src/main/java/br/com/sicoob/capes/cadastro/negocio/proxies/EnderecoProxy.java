/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.proxies;

import java.io.Serializable;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * Proxy para os endereços.
 * @author Erico.Junior
 */
public class EnderecoProxy implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = 3277737739220710489L;

	/** O atributo idEndereco. */
	private Long idEndereco;
	
	/** O atributo idInstituicaoAtualizacao. */
	private Integer idInstituicaoAtualizacao;
	
	/** O atributo dataHoraInicio. */
	private DateTimeDB dataHoraInicio;
	
	/** O atributo descricaoTipoEndereco. */
	private String descricaoTipoEndereco;
	
	/** O atributo descricaoEndereco. */
	private String descricaoEndereco;
	
	/** O atributo numero. */
	private String numero;
	
	/** O atributo complemento. */
	private String complemento;
	
	/** O atributo bairro. */
	private String bairro;
	
	/** O atributo nomeCidade. */
	private String nomeCidade;
	
	/** O atributo siglaUF. */
	private String siglaUF;
	
	/** O atributo padraoCorrespondencia. */
	private Boolean padraoCorrespondencia = Boolean.FALSE;
	
	/** O atributo cooperativasCorrespondencia. */
	private String cooperativasCorrespondencia;
	
	/** O atributo idRegistroControlado. */
	private String idRegistroControlado;
	
	/** O atributo idUsuarioAprovacao. */
	private String idUsuarioAprovacao;
	
	/** O atributo codigoSituacaoAprovacao. */
	private Short codigoSituacaoAprovacao;
	
	/** O atributo situacaoAprovacao. */
	private SituacaoRegistroEnum situacaoAprovacao;
	
	/**
	 * @return the dataHoraInicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * @param dataHoraInicio the dataHoraInicio to set
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * @return the descricaoTipoEndereco
	 */
	public String getDescricaoTipoEndereco() {
		return descricaoTipoEndereco;
	}

	/**
	 * @param descricaoTipoEndereco the descricaoTipoEndereco to set
	 */
	public void setDescricaoTipoEndereco(String descricaoTipoEndereco) {
		this.descricaoTipoEndereco = descricaoTipoEndereco;
	}

	/**
	 * @return the descricaoEndereco
	 */
	public String getDescricaoEndereco() {
		return descricaoEndereco;
	}

	/**
	 * @param descricaoEndereco the descricaoEndereco to set
	 */
	public void setDescricaoEndereco(String descricaoEndereco) {
		this.descricaoEndereco = descricaoEndereco;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the nomeCidade
	 */
	public String getNomeCidade() {
		return nomeCidade;
	}

	/**
	 * @param nomeCidade the nomeCidade to set
	 */
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	/**
	 * @return the padraoCorrespondencia
	 */
	public Boolean getPadraoCorrespondencia() {
		return padraoCorrespondencia;
	}

	/**
	 * @param padraoCorrespondencia the padraoCorrespondencia to set
	 */
	public void setPadraoCorrespondencia(Boolean padraoCorrespondencia) {
		this.padraoCorrespondencia = padraoCorrespondencia;
	}

	/**
	 * @return the siglaUF
	 */
	public String getSiglaUF() {
		return siglaUF;
	}

	/**
	 * @param siglaUF the siglaUF to set
	 */
	public void setSiglaUF(String siglaUF) {
		this.siglaUF = siglaUF;
	}

	/**
	 * @return the idEndereco
	 */
	public Long getIdEndereco() {
		return idEndereco;
	}

	/**
	 * @param idEndereco the idEndereco to set
	 */
	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	/**
	 * Recupera o valor de cooperativasCorrespondencia.
	 *
	 * @return o valor de cooperativasCorrespondencia
	 */
	public String getCooperativasCorrespondencia() {
		return cooperativasCorrespondencia;
	}

	/**
	 * Define o valor de cooperativasCorrespondencia.
	 *
	 * @param cooperativasCorrespondencia o novo valor de cooperativasCorrespondencia
	 */
	public void setCooperativasCorrespondencia(String cooperativasCorrespondencia) {
		this.cooperativasCorrespondencia = cooperativasCorrespondencia;
	}

	/**
	 * Recupera o valor de idInstituicaoAtualizacao.
	 *
	 * @return o valor de idInstituicaoAtualizacao
	 */
	public Integer getIdInstituicaoAtualizacao() {
		return idInstituicaoAtualizacao;
	}

	/**
	 * Define o valor de idInstituicaoAtualizacao.
	 *
	 * @param idInstituicaoAtualizacao o novo valor de idInstituicaoAtualizacao
	 */
	public void setIdInstituicaoAtualizacao(Integer idInstituicaoAtualizacao) {
		this.idInstituicaoAtualizacao = idInstituicaoAtualizacao;
	}

	/**
	 * Retornar o IdRegistroControlado como uma entidade, para uso do componente
	 * de visualização GED/GFT
	 * 
	 * @return idRegistroControlado
	 */
	public String getIdRegistroControlado() {
		if (idRegistroControlado != null && !"".equals(idRegistroControlado)) {
			return idRegistroControlado;
		}
		return TipoAutorizacaoEntidadeEnum.ENDERECO.name() + Aprovavel.SEPARADOR_ID_REGISTRO_CONTROLADO + getIdEndereco();
	}

	/**
	 * Define o valor de idRegistroControlado.
	 *
	 * @param idRegistroControlado o novo valor de idRegistroControlado
	 */
	public void setIdRegistroControlado(String idRegistroControlado) {
		this.idRegistroControlado = idRegistroControlado;
	}

	/**
	 * Recupera o valor de codigoSituacaoAprovacao.
	 *
	 * @return o valor de codigoSituacaoAprovacao
	 */
	public Short getCodigoSituacaoAprovacao() {
		return codigoSituacaoAprovacao;
	}

	/**
	 * Define o valor de codigoSituacaoAprovacao.
	 *
	 * @param codigoSituacaoAprovacao o novo valor de codigoSituacaoAprovacao
	 */
	public void setCodigoSituacaoAprovacao(Short codigoSituacaoAprovacao) {
		this.codigoSituacaoAprovacao = codigoSituacaoAprovacao;
	}

	/**
	 * Recupera o valor de situacaoAprovacao.
	 *
	 * @return o valor de situacaoAprovacao
	 */
	public SituacaoRegistroEnum getSituacaoAprovacao() {
		if ((situacaoAprovacao == null) && (codigoSituacaoAprovacao != null)) {
			situacaoAprovacao = SituacaoRegistroEnum.valueOf(codigoSituacaoAprovacao);
		}
		return situacaoAprovacao;
	}

	/**
	 * Define o valor de situacaoAprovacao.
	 *
	 * @param situacaoAprovacao o novo valor de situacaoAprovacao
	 */
	public void setSituacaoAprovacao(SituacaoRegistroEnum situacaoAprovacao) {
		this.situacaoAprovacao = situacaoAprovacao;
	}

	/**
	 * Recupera o valor de idUsuarioAprovacao.
	 *
	 * @return o valor de idUsuarioAprovacao
	 */
	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}

	/**
	 * Define o valor de idUsuarioAprovacao.
	 *
	 * @param idUsuarioAprovacao o novo valor de idUsuarioAprovacao
	 */
	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}

}