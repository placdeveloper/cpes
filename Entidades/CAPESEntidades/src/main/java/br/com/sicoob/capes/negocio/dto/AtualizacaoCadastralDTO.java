/*
 * SICOOB
 * 
 * AtualizacaoCadastralDTO.java(br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO)
 */
package br.com.sicoob.capes.negocio.dto;

import java.io.Serializable;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;

/**
 * DTO utilizado para atualizações cadastrais.
 * 
 * @author Erico.Junior
 */
public class AtualizacaoCadastralDTO<R extends Replicavel> 
		extends BancoobDto {

	/** Serial UID.*/
	private static final long serialVersionUID = -2131367579545691983L;

	/** O atributo entidade cadastro. */
	private R entidadeCadastro;
	
	/** O atributo instituicao. */
	private Instituicao instituicao;
	
	/** O atributo tipo operacao. */
	private Character tipoOperacao;
	
	/** O atributo id pessoa legado. */
	private Integer idPessoaLegado;
	
	// Nova Replicação
	/** O atributo classe replicavel. */
	private Class<R> classeReplicavel;
	
	/** O atributo chave capes. */
	private Serializable chaveCapes;

	/**
	 * @return the idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}

	/**
	 * @param idPessoaLegado
	 *            the idPessoaLegado to set
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}

	/**
	 * @return the tipoOperacao
	 */
	public Character getTipoOperacao() {
		return tipoOperacao;
	}

	/**
	 * @param tipoOperacao
	 *            the tipoOperacao to set
	 */
	public void setTipoOperacao(Character tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	/**
	 * @return the entidadeCadastro
	 */
	public R getEntidadeCadastro() {
		return entidadeCadastro;
	}

	/**
	 * @param entidadeCadastro
	 *            the entidadeCadastro to set
	 */
	public void setEntidadeCadastro(R entidadeCadastro) {
		this.entidadeCadastro = entidadeCadastro;
	}

	/**
	 * @return the instituicao
	 */
	public Instituicao getInstituicao() {
		return instituicao;
	}

	/**
	 * @param instituicao
	 *            the instituicao to set
	 */
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	/**
	 * @return the classeReplicavel
	 */
	public Class<R> getClasseReplicavel() {
		return classeReplicavel;
	}

	/**
	 * @param classeReplicavel the classeReplicavel to set
	 */
	public void setClasseReplicavel(Class<R> classeReplicavel) {
		this.classeReplicavel = classeReplicavel;
	}

	/**
	 * @return the chaveCapes
	 */
	public Serializable getChaveCapes() {
		return chaveCapes;
	}

	/**
	 * @param chaveCapes the chaveCapes to set
	 */
	public void setChaveCapes(Serializable chaveCapes) {
		this.chaveCapes = chaveCapes;
	}

}
