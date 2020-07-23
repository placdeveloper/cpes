/*
 * SICOOB
 * 
 * Email.java(br.com.sicoob.capes.negocio.entidades.vigente.Email)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.negocio.entidades.EmailBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;

/**
 * Entidade que representa os endereços das pessoas.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "EMAILPESSOA", schema = "CLI")
@EntityListeners({ReplicacaoListener.class})
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "dataVigenteDatasIguais"),
	@Filter(name = "imprimirFichaCadastral")
})
public class Email extends EmailBase implements Vigente, Replicavel, CadastroValidavel {

	/** Serial UID. */
	private static final long serialVersionUID = 6883395542484433527L;

	/** O atributo id email. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDEMAILPESSOA")
	private Long idEmail;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;

	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

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
	 * @return the idEmail
	 */
	public Long getIdEmail() {
		return idEmail;
	}

	/**
	 * @param idEmail
	 *            the idEmail to set
	 */
	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdEmail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdEmail(id);
	}

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}

}