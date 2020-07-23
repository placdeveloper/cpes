/*
 * SICOOB
 * 
 * HistoricoAlteracaoCnpjCpfPK.java(br.com.sicoob.capes.negocio.entidades.legado.pk.HistoricoAlteracaoCnpjCpfPK)
 */
package br.com.sicoob.capes.negocio.entidades.legado.pk;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;

/**
 * Chave primária do Histórico de alteração.
 * 
 * @author Erico.Junior
 */
@Embeddable
public class HistoricoAlteracaoCnpjCpfPK extends BancoobChavePrimaria {

	/** Serial UID. */
	private static final long serialVersionUID = -6806227593309164816L;

	/** O atributo pessoa. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NumPessoa", referencedColumnName = "NumPessoa")
	private Pessoa pessoa;

	/** O atributo cpf cnpj anterior. */
	@Column(name = "numCnpj_CpfAnterior")
	private String cpfCnpj;

	/** O atributo data alteracao. */
	private Date dataAlteracao;
	
	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa
	 *            the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj
	 *            the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	/**
	 * @return the dataAlteracao
	 */
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	/**
	 * @param dataAlteracao
	 *            the dataAlteracao to set
	 */
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
	
		return ReflexaoUtils.equals(this, obj, "pessoa.id","cpfCnpj", "dataAlteracao");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
	
		return ReflexaoUtils.hashCode(this, "pessoa.id","cpfCnpj", "dataAlteracao");
	}
	
}
