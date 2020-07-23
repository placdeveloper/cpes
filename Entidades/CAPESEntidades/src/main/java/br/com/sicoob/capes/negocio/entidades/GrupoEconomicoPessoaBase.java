/*
 * SICOOB
 * 
 * GrupoEconomicoPessoaBase.java(br.com.sicoob.capes.negocio.entidades.GrupoEconomicoPessoaBase)
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
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import flexjson.JSON;

/**
 * The Class GrupoEconomicoPessoaBase.
 */
@MappedSuperclass
public abstract class GrupoEconomicoPessoaBase extends CAPESEntidade<Integer>
		implements Replicavel {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 7257406211877238803L;

	/** O atributo grupo economico. */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "IDGRUPOECONOMICO", nullable = false)
	private GrupoEconomico grupoEconomico;

	/** O atributo pessoa instituicao. */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "IDPESSOAINSTITUICAO", nullable = false)
	private PessoaInstituicao pessoaInstituicao;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO", nullable = false)
	private DateTimeDB dataHoraInicio = new DateTimeDB();
	
	/** O atributo id usuario aprovacao. */
	@Column(name = "IDUSUARIOAPROV", length = 40)
	private String idUsuarioAprovacao;

	/**
	 * @return the grupoEconomico
	 */
	public GrupoEconomico getGrupoEconomico() {
		return grupoEconomico;
	}

	/**
	 * @param grupoEconomico
	 *            the grupoEconomico to set
	 */
	public void setGrupoEconomico(GrupoEconomico grupoEconomico) {
		this.grupoEconomico = grupoEconomico;
	}

	/**
	 * @return the pessoaInstituicao
	 */
	public PessoaInstituicao getPessoaInstituicao() {
		return pessoaInstituicao;
	}

	/**
	 * @param pessoaInstituicao
	 *            the pessoaInstituicao to set
	 */
	public void setPessoaInstituicao(PessoaInstituicao pessoaInstituicao) {
		this.pessoaInstituicao = pessoaInstituicao;
	}

	/**
	 * @return the dataHoraInicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * @param dataHoraInicio
	 *            the dataHoraInicio to set
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/** 
	 * {@inheritDoc}
	 */
	@JSON(include = false)
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		throw new UnsupportedOperationException();
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
