/*
 * SICOOB
 * 
 * PessoaInstituicao.java(br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.negocio.entidades.PessoaInstituicaoBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * 
 * Classe que representa a PessoaInstituicao.
 * @author juan.damasceno
 *
 */
@Entity
@Table(name="PESSOAINSTITUICAO", schema="CLI")
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "imprimirFichaCadastral")
})
public class PessoaInstituicao extends PessoaInstituicaoBase implements Vigente, Replicavel, CadastroValidavel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8564801326359050108L;

	/** O atributo id pessoa instituicao. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPessoaInstituicao;
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;
	
	/**
	 * Atributo não persistente utilizado no UC de grupo econômico para marcar
	 * se o cliente deve ou não ser trocado de grupo
	 */
	@Transient
	private Boolean trocarGrupo = Boolean.FALSE;
	
	/**
	 * Instancia um novo PessoaInstituicao.
	 */
	public PessoaInstituicao() {
		super();
	}
	
	/**
	 * Instancia um novo PessoaInstituicao.
	 *
	 * @param idPessoaInstituicao o valor de id pessoa instituicao
	 */
	public PessoaInstituicao(Integer idPessoaInstituicao) {
		super();
		this.idPessoaInstituicao = idPessoaInstituicao;
	}

	/**
	 * @return the gravarHistorico
	 */
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	/**
	 * @param gravarHistorico the gravarHistorico to set
	 */
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}

	/**
	 * @return the idPessoaInstituicao
	 */
	public Integer getIdPessoaInstituicao() {
		return idPessoaInstituicao;
	}

	/**
	 * @param idPessoaInstituicao the idPessoaInstituicao to set
	 */
	public void setIdPessoaInstituicao(Integer idPessoaInstituicao) {
		this.idPessoaInstituicao = idPessoaInstituicao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return this.idPessoaInstituicao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		this.idPessoaInstituicao = id;
	}

	/**
	 * Recupera em alteracao.
	 * 
	 * @return em alteracao
	 */
	public Boolean getEmAlteracao() {
		return Boolean.FALSE;
	}

	/**
	 * Preenche em alteracao.
	 * 
	 * @param emAlteracao
	 *            o novo em alteracao
	 */
	public void setEmAlteracao(Boolean emAlteracao) {
		
	}
	
	/**
	 * Gets the atributo não persistente utilizado no UC de grupo econômico para marcar se o cliente deve ou não ser
	 * trocado de grupo.
	 * 
	 * @return the atributo não persistente utilizado no UC de grupo econômico para marcar se o cliente deve ou não ser
	 *         trocado de grupo
	 */
	public Boolean getTrocarGrupo() {
		return trocarGrupo;
	}

	/**
	 * Sets the atributo não persistente utilizado no UC de grupo econômico para marcar se o cliente deve ou não ser
	 * trocado de grupo.
	 * 
	 * @param trocarGrupo
	 *            the new atributo não persistente utilizado no UC de grupo econômico para marcar se o cliente deve ou
	 *            não ser trocado de grupo
	 */
	public void setTrocarGrupo(Boolean trocarGrupo) {
		this.trocarGrupo = trocarGrupo;
	}

	/** 
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return getPessoa().getPessoaCompartilhamento();
	}
	
}