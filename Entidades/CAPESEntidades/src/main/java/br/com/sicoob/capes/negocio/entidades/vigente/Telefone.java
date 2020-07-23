/*
 * SICOOB
 * 
 * Telefone.java(br.com.sicoob.capes.negocio.entidades.vigente.Telefone)
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

import br.com.sicoob.capes.negocio.entidades.TelefoneBase;
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
@Table(name = "TELEFONEPESSOA", schema = "CLI")
@EntityListeners({ ReplicacaoListener.class })
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "dataVigenteDatasIguais"),
	@Filter(name = "imprimirFichaCadastral")
})
public class Telefone extends TelefoneBase implements Replicavel, Vigente, CadastroValidavel {

	/** Serial UID. */
	private static final long serialVersionUID = -6069953499791969152L;

	/** O atributo id telefone pessoa. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTelefonePessoa;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;

	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/**
	 * @return the idTelefonePessoa
	 */
	public Long getIdTelefonePessoa() {
		return idTelefonePessoa;
	}

	/**
	 * @param idTelefonePessoa
	 *            the idTelefonePessoa to set
	 */
	public void setIdTelefonePessoa(Long idTelefonePessoa) {
		this.idTelefonePessoa = idTelefonePessoa;
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
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdTelefonePessoa();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdTelefonePessoa(id);
	}

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}
}