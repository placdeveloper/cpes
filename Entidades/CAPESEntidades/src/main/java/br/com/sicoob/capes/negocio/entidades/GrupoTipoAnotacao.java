package br.com.sicoob.capes.negocio.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * Entidade que representa o grupo de tipos de anotação.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "GRUPOTIPOANOTACAO")
public class GrupoTipoAnotacao extends CAPESEntidade<Short> {
	private static final long serialVersionUID = -3996692717172129763L;

	@Id
	@Column(name = "IDGRUPOTIPOANOTACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short idGrupoTipoAnotacao;
	
	@Column(name = "NOMEGRUPOTIPOANOTACAO")
	private String nome;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CLI", name = "GRUPOTIPOANOTACAOTIPOANOTACAO", 
		joinColumns = { @JoinColumn(name = "IDGRUPOTIPOANOTACAO") }, 
		inverseJoinColumns = { @JoinColumn(name = "CODTIPOANOTACAO") })
	private List<TipoAnotacao> tiposAnotacao;

	public Short getIdGrupoTipoAnotacao() {
		return idGrupoTipoAnotacao;
	}

	public void setIdGrupoTipoAnotacao(Short idGrupoTipoAnotacao) {
		this.idGrupoTipoAnotacao = idGrupoTipoAnotacao;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TipoAnotacao> getTiposAnotacao() {
		return tiposAnotacao;
	}

	public void setTiposAnotacao(List<TipoAnotacao> tiposAnotacao) {
		this.tiposAnotacao = tiposAnotacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getIdGrupoTipoAnotacao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setIdGrupoTipoAnotacao(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object objeto) {
		return ReflexaoUtils.equals(this, objeto, "id");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "id");
	}

}