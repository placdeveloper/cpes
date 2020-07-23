/*
 * SICOOB
 * 
 * HistoricoProdutividade.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoProdutividade)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import br.com.sicoob.capes.negocio.entidades.ProdutividadeBase;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import flexjson.JSON;

/**
 * @author Erico.Junior
 * 
 */
@Entity
@Table(name = "HISTPESSOAPRODUTIVIDADE", schema = "CLI")
public class HistoricoProdutividade extends ProdutividadeBase implements Historico {

	/** Serial UID. */
	private static final long serialVersionUID = -3883269138693030589L;

	/** O atributo id historico. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTPESSOAPRODUTIVIDADE")
	private Long idHistorico;

	/** O atributo id produtividade. */
	@Column(name = "IDPESSOAPRODUTIVIDADE")
	private Integer idProdutividade;

	/** O atributo data hora fim. */
	@Column(name = "DATAHORAFIM")
	private Date dataHoraFim;

	/** O atributo bem imovel. */
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "IDBEMPESSOA", insertable = false, updatable = false)
	private br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel bemImovelAntigo;
	
	/** O atributo bem imovel. */
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "IDBEM", insertable = false, updatable = false)
	private BemImovel bemImovel;
	
	/** O atributo id pessoa compartilhamento. */
	@Column(name = "IDPESSOACOMPARTILHAMENTO")
	private Long idPessoaCompartilhamento;

	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;

	/**
	 * Recupera id historico.
	 * 
	 * @return id historico
	 */
	public Long getIdHistorico() {
		return idHistorico;
	}

	/**
	 * Preenche id historico.
	 * 
	 * @param idHistorico
	 *            o novo id historico
	 */
	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	/**
	 * Recupera id produtividade.
	 * 
	 * @return id produtividade
	 */
	public Integer getIdProdutividade() {
		return idProdutividade;
	}

	/**
	 * Preenche id produtividade.
	 * 
	 * @param idProdutividade
	 *            o novo id produtividade
	 */
	public void setIdProdutividade(Integer idProdutividade) {
		this.idProdutividade = idProdutividade;
	}

	/**
	 * {@inheritDoc}
	 */
	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdHistorico();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long identificador) {
		setIdHistorico(identificador);
	}

	/**
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return idProdutividade;
	}

	/**
	 * @return the transicoes
	 */
	@JSON(include = false)
	public br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel getBemImovelAntigo() {
		return bemImovelAntigo;
	}

	/**
	 * Preenche bem imovel.
	 * 
	 * @param bemImovelAntigo
	 *            o novo bem imovel
	 */
	public void setBemImovelAntigo(br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel bemImovelAntigo) {
		this.bemImovelAntigo = bemImovelAntigo;
	}

	/**
	 * Recupera id pessoa compartilhamento.
	 * 
	 * @return id pessoa compartilhamento
	 */
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	/**
	 * Preenche id pessoa compartilhamento.
	 * 
	 * @param idPessoaCompartilhamento
	 *            o novo id pessoa compartilhamento
	 */
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	/**
	 * Recupera id usuario exclusao.
	 * 
	 * @return id usuario exclusao
	 */
	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}

	public BemImovel getBemImovel() {
		return bemImovel;
	}

	public void setBemImovel(BemImovel bemImovel) {
		this.bemImovel = bemImovel;
	}
}
