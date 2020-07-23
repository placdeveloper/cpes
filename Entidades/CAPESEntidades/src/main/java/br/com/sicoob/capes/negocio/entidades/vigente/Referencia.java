/*
 * SICOOB
 * 
 * Referencia.java(br.com.sicoob.capes.negocio.entidades.vigente.Referencia)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.negocio.entidades.ReferenciaBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;

/**
 * Entidade que representa a tabela responsável por armazenar as referências de uma pessoa.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name="REFERENCIAPESSOA", schema = "CLI")
@EntityListeners({ReplicacaoListener.class})
@Inheritance(strategy=InheritanceType.JOINED)
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "dataVigenteDatasIguais"),
	@Filter(name = "imprimirFichaCadastral")
})
public class Referencia extends ReferenciaBase implements Replicavel, Vigente, CadastroValidavel {
		
	/** Serial UID. */
	private static final long serialVersionUID = 8802285131186186731L;

	/** O atributo id referencia pessoa. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idReferenciaPessoa;
	
	@Column(name="IDUSUARIOENVIO", length=40)
	private String idUsuarioEnvio; 
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/**
	 * @return the idReferenciaPessoa
	 */
	public Long getIdReferenciaPessoa() {
		return idReferenciaPessoa;
	}

	/**
	 * @param idReferenciaPessoa the idReferenciaPessoa to set
	 */
	public void setIdReferenciaPessoa(Long idReferenciaPessoa) {
		this.idReferenciaPessoa = idReferenciaPessoa;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdReferenciaPessoa();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdReferenciaPessoa(id);
	}
	
	/**
	 * @return the gravarHistorico
	 */
	@Override
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	/**
	 * @param gravarHistorico the gravarHistorico to set
	 */
	@Override
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}
	
}