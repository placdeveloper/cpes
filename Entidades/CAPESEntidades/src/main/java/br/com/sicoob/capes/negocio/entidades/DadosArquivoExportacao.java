/**
 * 
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.pk.DadosArquivoExportacaoPK;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
@Entity
@Table(schema = "CLI", name = "DADOSARQUIVOEXPORTACAO")
public class DadosArquivoExportacao extends CAPESEntidade<DadosArquivoExportacaoPK> {

	/** Serial UID */
	private static final long serialVersionUID = -8919626367519852200L;

	/** O atributo pk. */
	@EmbeddedId
	private DadosArquivoExportacaoPK pk;

	/** O atributo linha. */
	@Column(name = "DESCLINHA", length = 1000, nullable = false)
	private String linha;

	/** O atributo exportacao. */
	@ManyToOne
	@JoinColumn(name = "IDEXPORTACAO", insertable = false, updatable = false)
	private Exportacao exportacao;

	/**
	 * Construtor
	 */
	public DadosArquivoExportacao() {
	}

	/**
	 * Construtor
	 * 
	 * @param exportacao
	 * @param numeroLinha
	 * @param linha
	 */
	public DadosArquivoExportacao(Exportacao exportacao, Integer numeroLinha, String linha) {
		this.pk = new DadosArquivoExportacaoPK(exportacao.getIdExportacao(), numeroLinha);
		this.linha = linha;
		this.exportacao = exportacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DadosArquivoExportacaoPK getId() {
		return getPk();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(DadosArquivoExportacaoPK id) {
		setPk(id);
	}

	/**
	 * @return the pk
	 */
	public DadosArquivoExportacaoPK getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(DadosArquivoExportacaoPK pk) {
		this.pk = pk;
	}

	/**
	 * @param idExportacao
	 * @param numeroLinha
	 */
	public void setPk(Integer idExportacao, Integer numeroLinha) {
		setPk(new DadosArquivoExportacaoPK(idExportacao, numeroLinha));
	}

	/**
	 * @return the linha
	 */
	public String getLinha() {
		return linha;
	}

	/**
	 * @param linha
	 *            the linha to set
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}

	/**
	 * @return the exportacao
	 */
	public Exportacao getExportacao() {
		return exportacao;
	}

    /**
	 * {@inheritDoc}
	 */
    @Override
    public int hashCode() {
	    return ReflexaoUtils.hashCode(this, "pk", "linha");
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public boolean equals(Object obj) {
	    return ReflexaoUtils.equals(this, obj, "pk", "linha");
    }

}