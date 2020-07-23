/*
 * SICOOB
 * 
 * ProdutorBase.java(br.com.sicoob.capes.negocio.entidades.ProdutorBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * 
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class ProdutorBase extends EntidadeCadastroBase {

	/** Serial UID. */
	private static final long serialVersionUID = -3658232021323313255L;

	/** O atributo categoria. */
	@JoinColumn(name = "CODCATEGORIAPRODUTOR", referencedColumnName = "CODCATEGORIAPRODUTOR")
	@ManyToOne
	private CategoriaProdutor categoria;

	/** O atributo codigo inscricao. */
	@Column(name = "CODINSCRICAOPRODUTORRURAL")
	private String codigoInscricao;

	/** O atributo mes ano temporario. */
	@Column(name = "MESANOREFERENCIAET")
	private String mesAnoTemporario;

	/** O atributo mes ano permanente. */
	@Column(name = "MESANOREFERENCIAEP")
	private String mesAnoPermanente;

	/** O atributo qtd temporario. */
	@Column(name = "QTDEMPREGADOTEMPORARIO")
	private Integer qtdTemporario;

	/** O atributo qtd permanente. */
	@Column(name = "QTDEMPREGADOPERMANENTE")
	private Integer qtdPermanente;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	@JoinColumn(name = "CODTIPOBENEFICIARIOSICOR", referencedColumnName = "CODTIPOBENEFICIARIOSICOR")
	@ManyToOne
	private TipoBeneficiarioSicor tipoBeneficiarioSicor;
	
	@Column(name="IDUSUARIOENVIO")
	private String idUsuarioEnvio;

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
	 * Recupera mes ano temporario.
	 * 
	 * @return mes ano temporario
	 */
	public String getMesAnoTemporario() {
		return mesAnoTemporario;
	}

	/**
	 * Preenche mes ano temporario.
	 * 
	 * @param mesAnoTemporario
	 *            o novo mes ano temporario
	 */
	public void setMesAnoTemporario(String mesAnoTemporario) {
		this.mesAnoTemporario = mesAnoTemporario;
	}

	/**
	 * Recupera mes ano permanente.
	 * 
	 * @return mes ano permanente
	 */
	public String getMesAnoPermanente() {
		return mesAnoPermanente;
	}

	/**
	 * Preenche mes ano permanente.
	 * 
	 * @param mesAnoPermanente
	 *            o novo mes ano permanente
	 */
	public void setMesAnoPermanente(String mesAnoPermanente) {
		this.mesAnoPermanente = mesAnoPermanente;
	}

	/**
	 * Recupera qtd temporario.
	 * 
	 * @return qtd temporario
	 */
	public Integer getQtdTemporario() {
		return qtdTemporario;
	}

	/**
	 * Preenche qtd temporario.
	 * 
	 * @param qtdTemporario
	 *            o novo qtd temporario
	 */
	public void setQtdTemporario(Integer qtdTemporario) {
		this.qtdTemporario = qtdTemporario;
	}

	/**
	 * Recupera qtd permanente.
	 * 
	 * @return qtd permanente
	 */
	public Integer getQtdPermanente() {
		return qtdPermanente;
	}

	/**
	 * Preenche qtd permanente.
	 * 
	 * @param qtdPermanente
	 *            o novo qtd permanente
	 */
	public void setQtdPermanente(Integer qtdPermanente) {
		this.qtdPermanente = qtdPermanente;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public TipoBeneficiarioSicor getTipoBeneficiarioSicor() {
		return tipoBeneficiarioSicor;
	}

	public void setTipoBeneficiarioSicor(TipoBeneficiarioSicor tipoBeneficiarioSicor) {
		this.tipoBeneficiarioSicor = tipoBeneficiarioSicor;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getIdUsuarioEnvio() {
		return this.idUsuarioEnvio;
	}

	/**
	 * Preenche idUsuarioEnvio.
	 * 
	 * @param idUsuarioEnvio
	 */
	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}

}