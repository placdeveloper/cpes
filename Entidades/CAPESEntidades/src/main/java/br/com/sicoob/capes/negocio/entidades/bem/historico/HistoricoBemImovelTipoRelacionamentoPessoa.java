package br.com.sicoob.capes.negocio.entidades.bem.historico;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.bem.TipoRelacionamentoBemImovel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A classe que representa a entidade Histórico bem imóvel tipo relacionamento pessoa.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "HISTBEMIMOVELTIPORELACIONAMENTOPESSOA")
public class HistoricoBemImovelTipoRelacionamentoPessoa extends CAPESEntidade<Long> {
	private static final long serialVersionUID = -1017435112057593180L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTBEMIMOVELTIPORELACIONAMENTOPESSOA")
	private Long idHistorico;

	@Column(name = "IDBEMIMOVELTIPORELACIONAMENTOPESSOA")
	private Integer idBemImovelRelacionamento;

	@ManyToOne
	@JoinColumn(name = "CODTIPORELACIONAMENTOBEMIMOVEL")
	private TipoRelacionamentoBemImovel tipoRelacionamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDHISTBEM")
	private HistoricoBemImovel historicoBemImovel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	private PessoaCompartilhamento pessoaCompartilhamento;

	@Column(name = "IDPESSOACOMPARTILHAMENTO", insertable = false, updatable = false)
	private Long idPessoaCompartilhamento;

	@Column(name = "DATAINICIORELACIONAMENTOIMOVEL")
	private Date dataInicioRelacionamento;

	@Column(name = "DATAFIMRELACIONAMENTOIMOVEL")
	private Date dataFimRelacionamento;

	@Column(name = "TAMAREAPOSSE", precision = 13, scale = 4)
	private BigDecimal areaPosse;

	public Long getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	public Integer getIdBemImovelRelacionamento() {
		return idBemImovelRelacionamento;
	}

	public void setIdBemImovelRelacionamento(Integer idBemImovelRelacionamento) {
		this.idBemImovelRelacionamento = idBemImovelRelacionamento;
	}

	public TipoRelacionamentoBemImovel getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public void setTipoRelacionamento(TipoRelacionamentoBemImovel tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

	public HistoricoBemImovel getHistoricoBemImovel() {
		return historicoBemImovel;
	}

	public void setHistoricoBemImovel(HistoricoBemImovel historicoBemImovel) {
		this.historicoBemImovel = historicoBemImovel;
	}

	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	public Date getDataInicioRelacionamento() {
		return dataInicioRelacionamento;
	}

	public void setDataInicioRelacionamento(Date dataInicioRelacionamento) {
		this.dataInicioRelacionamento = dataInicioRelacionamento;
	}

	public Date getDataFimRelacionamento() {
		return dataFimRelacionamento;
	}

	public void setDataFimRelacionamento(Date dataFimRelacionamento) {
		this.dataFimRelacionamento = dataFimRelacionamento;
	}

	public BigDecimal getAreaPosse() {
		return areaPosse;
	}

	public void setAreaPosse(BigDecimal areaPosse) {
		this.areaPosse = areaPosse;
	}

	@Override
	public Long getId() {
		return getIdHistorico();
	}

	@Override
	public void setId(Long id) {
		setIdHistorico(id);
	}

}