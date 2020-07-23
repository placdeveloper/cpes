/*
 * SICOOB
 * 
 * TipoRelacionamentoPessoa.java(br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;
import flexjson.JSON;

/**
 * Entidade que representa os tipos de relacionamento entre pessoas
 * 
 * 04/08/2011
 * 
 * @author Rodrigo.Chaves
 */
@Entity
@Table(schema = "CLI", name = "TIPORELACIONAMENTOPESSOA")
@OrdenacaoPadrao(colunas = "DESCTIPORELACIONAMENTOPESSOA", descendente = false)
public class TipoRelacionamentoPessoa extends CAPESEntidade<Short> {

	/** Serial UID */
	private static final long serialVersionUID = 4839989355347080913L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPORELACIONAMENTOPESSOA")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPORELACIONAMENTOPESSOA", length = 200, nullable = false)
	private String descricao;

	/** O atributo relacionamento reverso. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CODTIPORELACIONAMENTOOPOSTO", nullable = true)
	private TipoRelacionamentoPessoa relacionamentoReverso;

	/** O atributo tipos pessoa relacionamento. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CLI",name="TIPOPESSOATIPORELACIONAMENTO", 
			joinColumns=@JoinColumn(name="CODTIPORELACIONAMENTO"),
			inverseJoinColumns = @JoinColumn(name="CODTIPOPESSOARELACIONAMENTO"))
	private Set<TipoPessoa> tiposPessoaRelacionamento;
	
	/** O atributo tipos pessoa relacionada. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CLI", name = "TIPOPESSOATIPORELACIONADA", 
			joinColumns = @JoinColumn(name = "CODTIPORELACIONAMENTO"),
			inverseJoinColumns = @JoinColumn(name="CODTIPOPESSOARELACIONADA"))
	private Set<TipoPessoa> tiposPessoaRelacionada;
	
	/** O atributo habilita capital social. */
	@Column(name = "BOLPREENCHEPERCENTUALCAPITAL", nullable = false)
	private Boolean habilitaCapitalSocial;

	/** O atributo habilita poderes. */
	@Column(name = "BOLCADASTRAPODER", nullable = false)
	private Boolean habilitaPoderes;

	/** O atributo habilita envio ccs. */
	@Column(name = "BOLENVIACCS", nullable = false)
	private Boolean habilitaEnvioCCS;

	/** O atributo habilita dados registro. */
	@Column(name = "BOLPREENCHEDADOREGISTRO", nullable = false)
	private Boolean habilitaDadosRegistro;

	/** O atributo compoe assinatura. */
	@Column(name = "BOLCOMPOSICAOASSINATURA", nullable = false)
	private Boolean compoeAssinatura = Boolean.FALSE;
	
	/** O atributo permite duplicidade. */
	@Column(name = "BOLPERMITEDUPLICIDADE", nullable = false)
	private Boolean permiteDuplicidade = Boolean.TRUE;
	
	/** O atributo habilita dados registro. */
	@Column(name = "BOLPERMITECOMPARTILHAMENTO", nullable = false)
	private Boolean permiteCompartilhamento;
	
	/** O atributo habilita dados registro. */
	@Column(name = "BOLHABILITAPRODUTOSBANCOOB", nullable = false)
	private Boolean habilitaProdutosBancoob;
	
	/** O atributo habilita dados registro. */
	@Column(name = "BOLHABILITAVERIFICACAOPENDENCIA", nullable = false)
	private Boolean habilitaVerificacaoPendencia;
	
	/** O atributo ativo. */
	@Column(name="BOLATIVO")
	private Boolean ativo;
	
	/**
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            o valor de codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            o valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return o valor de relacionamentoReverso
	 */
	public TipoRelacionamentoPessoa getRelacionamentoReverso() {
		return relacionamentoReverso;
	}

	/**
	 * @param relacionamentoReverso
	 *            o valor de relacionamentoReverso
	 */
	public void setRelacionamentoReverso(TipoRelacionamentoPessoa relacionamentoReverso) {
		this.relacionamentoReverso = relacionamentoReverso;
	}

	/**
	 * @return o valor de tiposPessoaRelacionamento
	 */
	@JSON(include = false)
	public Set<TipoPessoa> getTiposPessoaRelacionamento() {
		return tiposPessoaRelacionamento;
	}

	/**
	 * @param tiposPessoaRelacionamento o valor de tiposPessoaRelacionamento
	 */
	public void setTiposPessoaRelacionamento(Set<TipoPessoa> tiposPessoaRelacionemanto) {
		this.tiposPessoaRelacionamento = tiposPessoaRelacionemanto;
	}

	/**
	 * @return o valor de tiposPessoaRelacionada
	 */
	@JSON(include = false)
	public Set<TipoPessoa> getTiposPessoaRelacionada() {
		return tiposPessoaRelacionada;
	}

	/**
	 * @param tiposPessoaRelacionada o valor de tiposPessoaRelacionada
	 */
	public void setTiposPessoaRelacionada(Set<TipoPessoa> tiposPessoaRelacionada) {
		this.tiposPessoaRelacionada = tiposPessoaRelacionada;
	}

	/**
	 * @return o valor de habilitaCapitalSocial
	 */
	public Boolean getHabilitaCapitalSocial() {
		return habilitaCapitalSocial;
	}

	/**
	 * @param habilitaCapitalSocial
	 *            o valor de habilitaCapitalSocial
	 */
	public void setHabilitaCapitalSocial(Boolean habilitaCapitalSocial) {
		this.habilitaCapitalSocial = habilitaCapitalSocial;
	}

	/**
	 * @return o valor de habilitaPoderes
	 */
	public Boolean getHabilitaPoderes() {
		return habilitaPoderes;
	}

	/**
	 * @param habilitaPoderes
	 *            o valor de habilitaPoderes
	 */
	public void setHabilitaPoderes(Boolean habilitaPoderes) {
		this.habilitaPoderes = habilitaPoderes;
	}

	/**
	 * @return o valor de habilitaEnvioCCS
	 */
	public Boolean getHabilitaEnvioCCS() {
		return habilitaEnvioCCS;
	}

	/**
	 * @param habilitaEnvioCCS
	 *            o valor de habilitaEnvioCCS
	 */
	public void setHabilitaEnvioCCS(Boolean habilitaEnvioCCS) {
		this.habilitaEnvioCCS = habilitaEnvioCCS;
	}

	/**
	 * @return o valor de habilitaDadosRegistro
	 */
	public Boolean getHabilitaDadosRegistro() {
		return habilitaDadosRegistro;
	}

	/**
	 * @param habilitaDadosRegistro
	 *            o valor de habilitaDadosRegistro
	 */
	public void setHabilitaDadosRegistro(Boolean habilitaDadosRegistro) {
		this.habilitaDadosRegistro = habilitaDadosRegistro;
	}

	/**
	 * @return o valor de compoeAssinatura
	 */
	public Boolean getCompoeAssinatura() {
		return compoeAssinatura;
	}

	/**
	 * @param compoeAssinatura o valor de compoeAssinatura
	 */
	public void setCompoeAssinatura(Boolean compoeAssinatura) {
		this.compoeAssinatura = compoeAssinatura;
	}

	/**
	 * @return the permiteDuplicidade
	 */
	public Boolean getPermiteDuplicidade() {
		return permiteDuplicidade;
	}

	/**
	 * @param permiteDuplicidade the permiteDuplicidade to set
	 */
	public void setPermiteDuplicidade(Boolean permiteDuplicidade) {
		this.permiteDuplicidade = permiteDuplicidade;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getCodigo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodigo(id);
	}

	/**
	 * @return the permiteCompartilhamento
	 */
	public Boolean getPermiteCompartilhamento() {
		return permiteCompartilhamento;
	}

	/**
	 * @param permiteCompartilhamento the permiteCompartilhamento to set
	 */
	public void setPermiteCompartilhamento(Boolean permiteCompartilhamento) {
		this.permiteCompartilhamento = permiteCompartilhamento;
	}

	
	/**
	 * @return the habilitaProdutosBancoob
	 */
	public Boolean getHabilitaProdutosBancoob() {
		return habilitaProdutosBancoob;
	}
	
	/**
	 * @param habilitaProdutosBancoob the habilitaProdutosBancoob to set
	 */
	public void setHabilitaProdutosBancoob(Boolean habilitaProdutosBancoob) {
		this.habilitaProdutosBancoob = habilitaProdutosBancoob;
	}

	/**
	 * @return the habilitaVerificacaoPendencia
	 */
	public Boolean getHabilitaVerificacaoPendencia() {
		return habilitaVerificacaoPendencia;
	}

	/**
	 * @param habilitaVerificacaoPendencia the habilitaVerificacaoPendencia to set
	 */
	public void setHabilitaVerificacaoPendencia(Boolean habilitaVerificacaoPendencia) {
		this.habilitaVerificacaoPendencia = habilitaVerificacaoPendencia;
	}
	
	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
