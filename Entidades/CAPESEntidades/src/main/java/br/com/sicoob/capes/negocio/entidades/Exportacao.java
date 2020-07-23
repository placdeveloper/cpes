package br.com.sicoob.capes.negocio.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
@Entity
@Table(schema = "CLI", name = "EXPORTACAO")
public class Exportacao extends CAPESEntidade<Integer> {

	/** Serial UID */
	private static final long serialVersionUID = -2617848949619281950L;

	/** O atributo idExportacao. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDEXPORTACAO")
	private Integer idExportacao;

	/** O atributo destino. */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "IDDESTINOEXPORTACAO", nullable = false)
	private DestinoExportacao destino;

	/** O atributo numeroArquivo. */
	@Column(name = "NUMARQUIVO", nullable = false)
	private Short numeroArquivo = NumberUtils.SHORT_ZERO;

	/** O atributo dataMovimento. */
	@Column(name = "DATAMOVIMENTO", nullable = false)
	private DateTimeDB dataMovimento = new DateTimeDB();

	/** O atributo finalizado. */
	@Column(name = "BOLFINALIZADO", nullable = false)
	private Boolean finalizado = Boolean.FALSE;

	/** O atributo dados. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exportacao")
	@OrderBy
	private List<DadosArquivoExportacao> dados;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdExportacao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdExportacao(id);
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
	 * @return the destino
	 */
	public DestinoExportacao getDestino() {
		return destino;
	}

	/**
	 * @param destino
	 *            the destino to set
	 */
	public void setDestino(DestinoExportacao destino) {
		this.destino = destino;
	}

	/**
	 * @return the numeroArquivo
	 */
	public Short getNumeroArquivo() {
		return numeroArquivo;
	}

	/**
	 * @param numeroArquivo
	 *            the numeroArquivo to set
	 */
	public void setNumeroArquivo(Short numeroArquivo) {
		this.numeroArquivo = numeroArquivo;
	}

	/**
	 * @return the dataMovimento
	 */
	public DateTimeDB getDataMovimento() {
		return dataMovimento;
	}

	/**
	 * @param dataMovimento
	 *            the dataMovimento to set
	 */
	public void setDataMovimento(DateTimeDB dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	/**
	 * Recupera o valor de finalizado.
	 *
	 * @return o valor de finalizado
	 */
	public Boolean getFinalizado() {
		return finalizado;
	}

	/**
	 * Define o valor de finalizado.
	 *
	 * @param finalizado o novo valor de finalizado
	 */
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	/**
	 * @return the dados
	 */
	public List<DadosArquivoExportacao> getDados() {
		if (dados == null) {
			dados = new ArrayList<DadosArquivoExportacao>();
		}
		return dados;
	}

	/**
	 * @param dados
	 *            the dados to set
	 */
	public void setDados(List<DadosArquivoExportacao> dados) {
		this.dados = dados;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idExportacao", "destino", "numeroArquivo", "dataMovimento");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idExportacao", "destino", "numeroArquivo", "dataMovimento");
	}

}