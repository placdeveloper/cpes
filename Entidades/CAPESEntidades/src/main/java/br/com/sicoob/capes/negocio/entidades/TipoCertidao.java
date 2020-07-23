/*
 * SICOOB
 * 
 * TipoCertidao.java(br.com.sicoob.capes.negocio.entidades.TipoCertidao)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;


/**
 * Classe que representa o tipo de certidão
 * 
 * 12/07/2011
 * @author Rodrigo.Chaves
 */
@Entity
@Table(name = "TIPOCERTIDAO", schema = "CLI")
@OrdenacaoPadrao(colunas = {"SIGLATIPOCERTIDAO", "NOMETIPOCERTIDAO"}, descendente = false)
public class TipoCertidao extends CAPESEntidade<Short> {

	/** Serial UID */
	private static final long serialVersionUID = 4327274608519198002L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOCERTIDAO")
	private Short codigo;
	
	/** O atributo sigla. */
	@Column(name = "SIGLATIPOCERTIDAO", length = 6, nullable = false, unique = true)
	private String sigla;
	
	/** O atributo nome. */
	@Column(name = "NOMETIPOCERTIDAO", length = 200, nullable = false)
	private String nome;
	
	/** O atributo finalidade. */
	@Column(name = "DESCFINALIDADECERTIDAO", length = 2000)
	private String finalidade;
	
	/** O atributo prazo validade. */
	@Column(name = "PRAZOVALIDADE")
	private Short prazoValidade;
	
	/** O atributo data cancelamento. */
	@Column(name = "DATACANCELAMENTO")
	private Date dataCancelamento;
	
	// -------------------
	// Relacionamentos
	// -------------------
	
	/** O atributo sub tipo. */
	@ManyToOne
	@JoinColumn(name = "CODSUBTIPOCERTIDAO", referencedColumnName = "CODSUBTIPOCERTIDAO")
	private SubTipoCertidao subTipo;
	
	/** O atributo tipo prazo. */
	@ManyToOne
	@JoinColumn(name = "CODTIPOPRAZOCERTIDAO", referencedColumnName = "CODTIPOPRAZOCERTIDAO")
	private TipoPrazoCertidao tipoPrazo;
	
	/** O atributo tipo abrangencia. */
	@ManyToOne
	@JoinColumn(name = "CODTIPOABRANGENCIACERTIDAO", referencedColumnName = "CODTIPOABRANGENCIACERTIDAO")
	private TipoAbrangenciaCertidao tipoAbrangencia;
	
	/** O atributo orgao emissor. */
	@ManyToOne
	@JoinColumn(name = "CODORGAOEMISSORCERTIDAO", referencedColumnName = "CODORGAOEMISSORCERTIDAO")
	private OrgaoEmissorCertidao orgaoEmissor;
	
	/** O atributo tipo objeto. */
	@ManyToOne
	@JoinColumn(name = "CODTIPOOBJETOCERTIDAO", referencedColumnName = "CODTIPOOBJETOCERTIDAO")
	private TipoObjetoCertidao tipoObjeto;
	
	/**
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo o valor de codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return o valor de sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla o valor de sigla
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return o valor de nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome o valor de nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return o valor de finalidade
	 */
	public String getFinalidade() {
		return finalidade;
	}

	/**
	 * @param finalidade o valor de finalidade
	 */
	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	/**
	 * @return o valor de prazoValidade
	 */
	public Short getPrazoValidade() {
		return prazoValidade;
	}

	/**
	 * @param prazoValidade o valor de prazoValidade
	 */
	public void setPrazoValidade(Short prazoValidade) {
		this.prazoValidade = prazoValidade;
	}

	/**
	 * @return o valor de dataCancelamento
	 */
	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	/**
	 * @param dataCancelamento o valor de dataCancelamento
	 */
	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	/**
	 * @return o valor de subTipo
	 */
	public SubTipoCertidao getSubTipo() {
		return subTipo;
	}

	/**
	 * @param subTipo o valor de subTipo
	 */
	public void setSubTipo(SubTipoCertidao subTipo) {
		this.subTipo = subTipo;
	}

	/**
	 * @return o valor de tipoPrazo
	 */
	public TipoPrazoCertidao getTipoPrazo() {
		return tipoPrazo;
	}

	/**
	 * @param tipoPrazo o valor de tipoPrazo
	 */
	public void setTipoPrazo(TipoPrazoCertidao tipoPrazo) {
		this.tipoPrazo = tipoPrazo;
	}

	/**
	 * @return o valor de tipoAbrangencia
	 */
	public TipoAbrangenciaCertidao getTipoAbrangencia() {
		return tipoAbrangencia;
	}

	/**
	 * @param tipoAbrangencia o valor de tipoAbrangencia
	 */
	public void setTipoAbrangencia(TipoAbrangenciaCertidao tipoAbrangencia) {
		this.tipoAbrangencia = tipoAbrangencia;
	}

	/**
	 * @return o valor de orgaoEmissor
	 */
	public OrgaoEmissorCertidao getOrgaoEmissor() {
		return orgaoEmissor;
	}

	/**
	 * @param orgaoEmissor o valor de orgaoEmissor
	 */
	public void setOrgaoEmissor(OrgaoEmissorCertidao orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	/**
	 * @return o valor de tipoObjeto
	 */
	public TipoObjetoCertidao getTipoObjeto() {
		return tipoObjeto;
	}

	/**
	 * @param tipoObjeto o valor de tipoObjeto
	 */
	public void setTipoObjeto(TipoObjetoCertidao tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
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

}
