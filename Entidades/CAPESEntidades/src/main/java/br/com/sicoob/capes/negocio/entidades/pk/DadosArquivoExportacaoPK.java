package br.com.sicoob.capes.negocio.entidades.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Exportacao;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
@Embeddable
public class DadosArquivoExportacaoPK extends BancoobChavePrimaria {

	/** Serial UID */
	private static final long serialVersionUID = 1279598849590960207L;

	/** O atributo idExportacao. */
	@Column(name = "IDEXPORTACAO")
	private Integer idExportacao;

	/** O atributo numeroLinha. */
	@Column(name = "NUMLINHA")
	private Integer numeroLinha;

	/**
	 * Construtor
	 */
	public DadosArquivoExportacaoPK() {
	}

	/**
	 * Construtor
	 * 
	 * @param idExportacao
	 *            o ID da {@link Exportacao}
	 * @param numeroLinha
	 *            o numero da linha
	 */
	public DadosArquivoExportacaoPK(Integer idExportacao, Integer numeroLinha) {
		super();
		this.idExportacao = idExportacao;
		this.numeroLinha = numeroLinha;
	}

	/**
	 * @return the idExportacao
	 */
	public Integer getIdExportacao() {
		return idExportacao;
	}

	/**
	 * @param idExportacao
	 *            the idExportacao to set
	 */
	public void setIdExportacao(Integer idExportacao) {
		this.idExportacao = idExportacao;
	}

	/**
	 * @return the numeroLinha
	 */
	public Integer getNumeroLinha() {
		return numeroLinha;
	}

	/**
	 * @param numeroLinha
	 *            the numeroLinha to set
	 */
	public void setNumeroLinha(Integer numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    public int hashCode() {
	    return ReflexaoUtils.hashCode(this, "idExportacao", "numeroLinha");
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    public boolean equals(Object obj) {
	    return ReflexaoUtils.equals(this, obj, "idExportacao", "numeroLinha");
    }

}
