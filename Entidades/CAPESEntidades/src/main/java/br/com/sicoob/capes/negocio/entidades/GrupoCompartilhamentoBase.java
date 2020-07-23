/*
 * SICOOB
 * 
 * GrupoCompartilhamentoBase.java(br.com.sicoob.capes.negocio.entidades.GrupoCompartilhamentoBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * The Class GrupoCompartilhamentoBase.
 */
@MappedSuperclass
public abstract class GrupoCompartilhamentoBase extends CAPESEntidade<Integer> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 591820540514698306L;
	
	/** O atributo compartilhamento cadastro. */
	@JoinColumn(name = "CODCOMPARTILHAMENTOCADASTRO",
			referencedColumnName = "CODCOMPARTILHAMENTOCADASTRO")
	@ManyToOne(fetch = FetchType.EAGER)
	private CompartilhamentoCadastro compartilhamentoCadastro;
	
	/** O atributo id instituicao. */
	@Column(name = "IDINSTITUICAO", nullable = false)
	private Integer idInstituicao;
	
	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO", nullable = false)
	private DateTimeDB dataHoraInicio;
	
	/** O atributo id usuario aprovacao. */
	@Column(name = "IDUSUARIOAPROV", length = 40)
	private String idUsuarioAprovacao;

	/**
	 * Recupera compartilhamento cadastro.
	 * 
	 * @return compartilhamento cadastro
	 */
	public CompartilhamentoCadastro getCompartilhamentoCadastro() {
		return compartilhamentoCadastro;
	}

	/**
	 * Preenche compartilhamento cadastro.
	 * 
	 * @param compartilhamentoCadastro
	 *            o novo compartilhamento cadastro
	 */
	public void setCompartilhamentoCadastro(CompartilhamentoCadastro compartilhamentoCadastro) {
		this.compartilhamentoCadastro = compartilhamentoCadastro;
	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
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
	 * @param dataHoraInicial
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicial) {
		this.dataHoraInicio = dataHoraInicial;
	}

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
	 * Atualizar id usuario.
	 */
	@PrePersist	@PreUpdate
	public void atualizarIdUsuario() {
		if(!(this instanceof Historico)){
			if(InformacoesUsuarioCAPES.getInstance() != null){
				idUsuarioAprovacao = InformacoesUsuarioCAPES.getInstance().getLogin();
			}
		}
	}

}