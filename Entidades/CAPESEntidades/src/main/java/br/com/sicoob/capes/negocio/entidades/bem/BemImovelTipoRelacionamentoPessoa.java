package br.com.sicoob.capes.negocio.entidades.bem;

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

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A classe que representa a entidade bem imóvel tipo relacionamento pessoa.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "BEMIMOVELTIPORELACIONAMENTOPESSOA")
public class BemImovelTipoRelacionamentoPessoa extends CAPESEntidade<Integer> {
	private static final long serialVersionUID = -1017435112057593180L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDBEMIMOVELTIPORELACIONAMENTOPESSOA")
	private Integer idBemImovelRelacionamento;

	@ManyToOne
	@JoinColumn(name = "CODTIPORELACIONAMENTOBEMIMOVEL")
	private TipoRelacionamentoBemImovel tipoRelacionamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDBEM")
	private BemImovel bemImovel;

	@ManyToOne(fetch = FetchType.EAGER)
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
	
	public BemImovelTipoRelacionamentoPessoa() {
		
	}
	
	public BemImovelTipoRelacionamentoPessoa(Integer idBemImovelRelacionamento, TipoRelacionamentoBemImovel tipoRelacionamento, 
			PessoaCompartilhamento pessoaCompartilhamento, Long idPessoaCompartilhamento, Date dataInicioRelacionamento, 
			Date dataFimRelacionamento, BigDecimal areaPosse) {
		super();
		this.idBemImovelRelacionamento = idBemImovelRelacionamento;
		this.tipoRelacionamento = tipoRelacionamento;
		this.pessoaCompartilhamento = pessoaCompartilhamento;
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
		this.dataInicioRelacionamento = dataInicioRelacionamento;
		this.dataFimRelacionamento = dataFimRelacionamento;
		this.areaPosse = areaPosse;
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

	public BemImovel getBemImovel() {
		return bemImovel;
	}

	public void setBemImovel(BemImovel bemImovel) {
		this.bemImovel = bemImovel;
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
	public Integer getId() {
		return getIdBemImovelRelacionamento();
	}

	@Override
	public void setId(Integer id) {
		setIdBemImovelRelacionamento(id);
	}
	
	@Override
	public boolean equals(Object objeto) {
		return ReflexaoUtils.equals(this, objeto, "idPessoaCompartilhamento", "tipoRelacionamento.codigo");
	}

	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idPessoaCompartilhamento", "tipoRelacionamento.codigo");
	}

}