/*
 * SICOOB
 * 
 * Produtor.java(br.com.sicoob.capes.negocio.entidades.legado.Produtor)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entidade que mapeia a tabela: PessoaProdutor.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "PessoaProdutor")
public class Produtor extends EntidadeReplicavel<Integer> {

	/** Serial UID. */
	private static final long serialVersionUID = 5113108490998764101L;

	/** O atributo num pessoa. */
	@Id
	private Integer numPessoa;
	
	/** O atributo pessoa. */
	@OneToOne
	@PrimaryKeyJoinColumn(name = "NumPessoa", referencedColumnName = "NumPessoa")
	private Pessoa pessoa;

	/** O atributo codigo inscricao. */
	@Column(name = "CodInscricaoProdRural")
	private String codigoInscricao;

	/** O atributo categoria. */
	@ManyToOne
	@JoinColumn(name = "IDCategoriaProdutor", referencedColumnName = "IDCategoriaProdutor")
	private CategoriaProdutor categoria;
	
	@Column(name = "CODTIPOBENEFICIARIOSICOR")
	private Short codigoTipoBeneficiarioSicor;

	/**
	 * Recupera num pessoa.
	 * 
	 * @return num pessoa
	 */
	public Integer getNumPessoa() {
		return numPessoa;
	}

	/**
	 * Preenche num pessoa.
	 * 
	 * @param numPessoa
	 *            o novo num pessoa
	 */
	public void setNumPessoa(Integer numPessoa) {
		this.numPessoa = numPessoa;
	}

	/**
	 * Recupera pessoa.
	 * 
	 * @return pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * Preenche pessoa.
	 * 
	 * @param pessoa
	 *            o novo pessoa
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * Recupera codigo inscricao.
	 * 
	 * @return codigo inscricao
	 */
	public String getCodigoInscricao() {
		return codigoInscricao;
	}

	/**
	 * Preenche codigo inscricao.
	 * 
	 * @param codigoInscricao
	 *            o novo codigo inscricao
	 */
	public void setCodigoInscricao(String codigoInscricao) {
		this.codigoInscricao = codigoInscricao;
	}

	/**
	 * Recupera categoria.
	 * 
	 * @return categoria
	 */
	public CategoriaProdutor getCategoria() {
		return categoria;
	}

	/**
	 * Preenche categoria.
	 * 
	 * @param categoria
	 *            o novo categoria
	 */
	public void setCategoria(CategoriaProdutor categoria) {
		this.categoria = categoria;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof Integer){
			setNumPessoa((Integer)idSQL);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getNumPessoa();
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		setIdSQL(idDB2);
	}

	public Short getCodigoTipoBeneficiarioSicor() {
		return codigoTipoBeneficiarioSicor;
	}

	public void setCodigoTipoBeneficiarioSicor(Short codigoTipoBeneficiarioSicor) {
		this.codigoTipoBeneficiarioSicor = codigoTipoBeneficiarioSicor;
	}
	
}
