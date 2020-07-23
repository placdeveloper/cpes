package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * A Classe TipoInformacao.
 */
@Entity
@Table(schema = "CLI", name = "TIPOINFORMACAOPESSOA")
public class TipoInformacao extends CAPESEntidade<Short> {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 746770576984730506L;

	/** O atributo codigo. */
	@Id
	@Column(name = "IDTIPOINFORMACAOPESSOA")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOINFORMACAOPESSOA", length = 100, nullable = false)
	private String descricao;

	/** O atributo informacaoExportacao. */
	@Column(name = "BOLINFORMACAOEXPORTACAO", nullable = false)
	private Boolean informacaoExportacao;

	/** O atributo informacaoObrigatoriaExportacao. */
	@Column(name = "BOLINFORMACAOOBRIGATORIAEXPORTACAO", nullable = false)
	private Boolean informacaoObrigatoriaExportacao;
	
	/**
	 * Construtor
	 */
	public TipoInformacao() {
	}

	/**
	 * Construtor
	 */
	public TipoInformacao(Integer codigo) {
		if(codigo != null){
			this.codigo = codigo.shortValue();
		}
	}

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
	 * Recupera o valor de informacaoExportacao.
	 *
	 * @return o valor de informacaoExportacao
	 */
	public Boolean getInformacaoExportacao() {
		return informacaoExportacao;
	}

	/**
	 * Define o valor de informacaoExportacao.
	 *
	 * @param informacaoExportacao o novo valor de informacaoExportacao
	 */
	public void setInformacaoExportacao(Boolean informacaoExportacao) {
		this.informacaoExportacao = informacaoExportacao;
	}

	/**
	 * Recupera o valor de informacaoObrigatoriaExportacao.
	 *
	 * @return o valor de informacaoObrigatoriaExportacao
	 */
	public Boolean getInformacaoObrigatoriaExportacao() {
		return informacaoObrigatoriaExportacao;
	}

	/**
	 * Define o valor de informacaoObrigatoriaExportacao.
	 *
	 * @param informacaoObrigatoriaExportacao o novo valor de informacaoObrigatoriaExportacao
	 */
	public void setInformacaoObrigatoriaExportacao(Boolean informacaoObrigatoriaExportacao) {
		this.informacaoObrigatoriaExportacao = informacaoObrigatoriaExportacao;
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
	public void setId(Short codigo) {
		setCodigo(codigo);
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