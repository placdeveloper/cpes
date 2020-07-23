/*
 * SICOOB
 * 
 * Mensagem.java(br.com.sicoob.capes.negocio.entidades.legado.Mensagem)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Classe que representa a mensagem.
 * @author juan.damasceno
 *
 */
@Entity
@Table(name="MENSAGEMPESSOA")
public class Mensagem extends EntidadeReplicavel<String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo id mensagem. */
	@Id
	@Column(name = "UIDMensagemPessoa")
	@GeneratedValue(generator="geradorGUID")
	@GenericGenerator(name="geradorGUID", 
			strategy = "br.com.sicoob.capes.negocio.entidades.legado.id.GUIDGenerator")    	
	private String idMensagem;
	
	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn(name="NumPessoa")
	private Pessoa pessoa;
	
	/** O atributo mensagem. */
	@Column(name="DESCMENSAGEM")
	private String mensagem;
	
	/** O atributo data cadastro. */
	@Column(name="DATACADASTROMSG")
	private Date dataCadastro;
	
	/** O atributo validade. */
	@Column(name="DATAVALIDADEMSG")
	private Date validade;
	
	/** O atributo codigo destino exibicao. */
	@Column(name="CODDESTINOEXIBICAO")
	private Short codigoDestinoExibicao;
	
	/** O atributo id mensagem d b2. */
	@Column(name="IDMENSAGEMPESSOADB2")
	private Long idMensagemDB2;

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getIdSQL() {
		return getIdMensagem();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdMensagemDB2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof String){
			this.idMensagem = (String) idSQL;
		}
	}

	/**
	 * @return the idMensagem
	 */
	public String getIdMensagem() {
		return idMensagem;
	}

	/**
	 * @param idMensagem the idMensagem to set
	 */
	public void setIdMensagem(String idMensagem) {
		this.idMensagem = idMensagem;
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the validade
	 */
	public Date getValidade() {
		return validade;
	}

	/**
	 * @param validade the validade to set
	 */
	public void setValidade(Date validade) {
		this.validade = validade;
	}

	/**
	 * @return the codigoDestinoExibicao
	 */
	public Short getCodigoDestinoExibicao() {
		return codigoDestinoExibicao;
	}

	/**
	 * @param codigoDestinoExibicao the codigoDestinoExibicao to set
	 */
	public void setCodigoDestinoExibicao(Short codigoDestinoExibicao) {
		this.codigoDestinoExibicao = codigoDestinoExibicao;
	}

	/**
	 * @return the idMensagemDB2
	 */
	public Long getIdMensagemDB2() {
		return idMensagemDB2;
	}

	/**
	 * @param idMensagemDB2 the idMensagemDB2 to set
	 */
	public void setIdMensagemDB2(Long idMensagemDB2) {
		this.idMensagemDB2 = idMensagemDB2;
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Long){
			setIdMensagemDB2((Long)idDB2);
		}
	}	
}