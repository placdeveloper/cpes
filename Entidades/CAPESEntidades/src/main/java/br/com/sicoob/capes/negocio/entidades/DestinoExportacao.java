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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * A Classe DestinoExportacao.
 */
@Entity
@Table(schema = "CLI", name = "DESTINOEXPORTACAO")
public class DestinoExportacao extends CAPESEntidade<Short> {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4406988538682347546L;

	/** O atributo codigo. */
	@Id
	@Column(name = "IDDESTINOEXPORTACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCDESTINOEXPORTACAO", length = 100, nullable = false)
	private String descricao;

	/** O atributo diretorio. */
	@Column(name = "DESCDIRETORIO", length = 200, nullable = false)
	private String diretorio;

	/** O atributo ativo. */
	@Column(name = "BOLATIVO", length = 100, nullable = false)
	private Boolean ativo;
	
	/** O atributo tiposInformacao. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CLI", name = "DESTINOEXPORTACAOTIPOINFORMACAOPESSOA",
				joinColumns = { @JoinColumn(name = "IDDESTINOEXPORTACAO") }, 
				inverseJoinColumns = { @JoinColumn(name = "IDTIPOINFORMACAOPESSOA") })
	@OrderBy
	private List<TipoInformacao> tiposInformacao;
	
	@Column(name = "DESCCODIFICACAO", nullable = false)
	private String codificacaoArquivo;

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * Define o valor de codigo.
	 *
	 * @param codigo o novo valor de codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera o valor de descricao.
	 *
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Define o valor de descricao.
	 *
	 * @param descricao o novo valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera o valor de diretorio.
	 *
	 * @return o valor de diretorio
	 */
	public String getDiretorio() {
		return diretorio;
	}

	/**
	 * Define o valor de diretorio.
	 *
	 * @param diretorio o novo valor de diretorio
	 */
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	/**
	 * Recupera o valor de ativo.
	 *
	 * @return o valor de ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * Define o valor de ativo.
	 *
	 * @param ativo o novo valor de ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	/**
	 * Recupera o valor de tiposInformacao.
	 *
	 * @return o valor de tiposInformacao
	 */
	public List<TipoInformacao> getTiposInformacao() {
		return tiposInformacao;
	}

	/**
	 * Define o valor de tiposInformacao.
	 *
	 * @param tiposInformacao o novo valor de tiposInformacao
	 */
	public void setTiposInformacao(List<TipoInformacao> tiposInformacao) {
		this.tiposInformacao = tiposInformacao;
	}
	
	/**
	 * Recupera o valor da codificacao do arquivo
	 * 
	 * @return o valor da codificacao
	 */
	public String getCodificacaoArquivo() {
		return codificacaoArquivo;
	}

	/**
	 * Define o valor da codificacao do arquivo
	 * 
	 * @param codificacaoArquivo
	 *            o novo valor da codificacao
	 */
	public void setCodificacaoArquivo(String codificacaoArquivo) {
		this.codificacaoArquivo = codificacaoArquivo;
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
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codigo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.descricao != null ? this.descricao : "";
	}

}