/*
 * SICOOB
 * 
 * EntidadeCadastroBase.java(br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Superclasse para as entidades que precisão de aprovação para atualização
 * cadastral.
 * 
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class EntidadeCadastroBase extends CAPESEntidade<Long> {

	/** Serial UID. */
	private static final long serialVersionUID = -4598166764310608842L;

	/** O atributo id usuario aprovacao. */
	@Column(name = "IDUSUARIOAPROV", length = 40)
	private String idUsuarioAprovacao;
	
	@Transient
	private String idUsuarioExclusao;

	/** 
	 * {@inheritDoc}
	 */
	public abstract PessoaCompartilhamento getPessoaCompartilhamento();

	/**
	 * Recupera id usuario aprovacao.
	 * 
	 * @return id usuario aprovacao
	 */
	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}

	/**
	 * Preenche id usuario aprovacao.
	 * 
	 * @param idUsuarioAprovacao
	 *            o novo id usuario aprovacao
	 */
	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}
	
	/**
	 * Recupera id usuario exclusao.
	 * 
	 * @return idUsuarioExclusao
	 */
	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	/**
	 * Preenche id usuario exclusão.
	 * 
	 * @param idUsuarioExclusao
	 *            o novo id usuario exclusao
	 */
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

	/**
	 * Atualizar id usuario.
	 */
	@PrePersist	@PreUpdate
	public void atualizarIdUsuario() {
		if(!(this instanceof Historico) && InformacoesUsuarioCAPES.getInstance() != null){
			idUsuarioAprovacao = InformacoesUsuarioCAPES.getInstance().getLogin();
		}
	}
	
	/**
	 * Atualiza o id do usuário da exclusão
	 */
	@PreRemove
	public void atualizarIdUsuarioExclusao() {
		if (InformacoesUsuarioCAPES.getInstance() != null) {
			idUsuarioExclusao = InformacoesUsuarioCAPES.getInstance().getLogin();
		}
	}
}