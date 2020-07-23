/*
 * SICOOB
 * 
 * Mensagem.java(br.com.sicoob.capes.negocio.entidades.Mensagem)
 */
package br.com.sicoob.capes.negocio.entidades;

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

import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * The Class Mensagem.
 */
@Entity
@Table(name = "MensagemPessoa", schema = "CLI")
@EntityListeners(ReplicacaoListener.class)
public class Mensagem extends MensagemBase implements Replicavel, Vigente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo id mensagem. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDMENSAGEMPESSOA")
	private Long idMensagem;
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;
	
	/** O atributo tipoMensagem. */
	@ManyToOne
	@JoinColumn(name="CODTIPOMENSAGEM", referencedColumnName="CODTIPOMENSAGEM", insertable=false, updatable=false)
	private TipoMensagem tipoMensagem;
	
	/** O atributo tipoDestinoExibicao. */
	@ManyToOne
	@JoinColumn(name="CODTIPODESTINOEXIBICAO", referencedColumnName="CODTIPODESTINOEXIBICAO", insertable=false, updatable=false)
	private TipoDestinoExibicao tipoDestinoExibicao;
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdMensagem();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdMensagem(id);
	}
	
	/**
	 * @return the idMensagem
	 */
	public Long getIdMensagem() {
		return idMensagem;
	}

	/**
	 * @param idMensagem the idMensagem to set
	 */
	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return getPessoa().getPessoaCompartilhamento();
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
	public TipoMensagem getTipoMensagem() {
		return tipoMensagem;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTipoMensagem(TipoMensagem tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoDestinoExibicao getTipoDestinoExibicao() {
		return tipoDestinoExibicao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTipoDestinoExibicao(TipoDestinoExibicao tipoDestinoExibicao) {
		this.tipoDestinoExibicao = tipoDestinoExibicao;
	}



}
