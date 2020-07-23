package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Classe base para os registros que passam por inclusão/alteração.
 * 
 * @param <ID>
 *            O id do DTO
 * 
 * @author Bruno.Carneiro
 */
public abstract class RegistroInclusaoDTO<ID extends Serializable> extends CAPESApiInclusaoDTO {
	private static final long serialVersionUID = 6606812050952489020L;

	/** O atributo verificarAutorizacao. */
	private Boolean verificarAutorizacao = Boolean.TRUE;

	/** O atributo idsDocumentos. */
	private List<Long> idsDocumentos;
	
	/**
	 * Recupera o valor de verificarAutorizacao.
	 * 
	 * @return o valor de verificarAutorizacao
	 */
	public Boolean getVerificarAutorizacao() {
		return verificarAutorizacao;
	}

	/**
	 * Define o valor de verificarAutorizacao.
	 * 
	 * @param verificarAutorizacao
	 *            o novo valor de verificarAutorizacao
	 */
	public void setVerificarAutorizacao(Boolean verificarAutorizacao) {
		this.verificarAutorizacao = verificarAutorizacao;
	}

	/**
	 * Recupera o valor de idsDocumentos.
	 * 
	 * @return o valor de idsDocumentos
	 */
	public List<Long> getIdsDocumentos() {
		return idsDocumentos;
	}

	/**
	 * Define o valor de idsDocumentos.
	 * 
	 * @param idsDocumentos
	 *            o novo valor de idsDocumentos
	 */
	public void setIdsDocumentos(List<Long> idsDocumentos) {
		this.idsDocumentos = idsDocumentos;
	}
	
	/**
	 * Recupera o id.
	 * 
	 * @return id
	 */
	public abstract ID getId();

	/**
	 * Preenche o id.
	 * 
	 * @param id
	 *            o novo id
	 */
	public abstract void setId(ID id);

}