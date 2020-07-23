package br.com.sicoob.capes.negocio.entidades.bem.historico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A classe que representa a entidade Histórico bem pessoa compartilhamento.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "HISTBEMPESSOACOMPARTILHAMENTO")
public class HistoricoBemPessoaCompartilhamento extends EntidadeCadastroBase implements Historico {
	private static final long serialVersionUID = -5325426401217445504L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTBEMPESSOACOMPARTILHAMENTO")
	private Long idHistorico;

	@Column(name = "IDBEMPESSOACOMPARTILHAMENTO")
	private Long idBemPessoaCompartilhamento;

	@ManyToOne
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	private PessoaCompartilhamento pessoaCompartilhamento;

	@ManyToOne
	@JoinColumn(name = "IDBEM")
	private Bem bem;

	@Column(name = "PERCPROPRIETARIO", precision = 5, scale = 2)
	private BigDecimal percentualProprietario;
	
	@Column(name = "TAMAREAPOSSE", precision = 13, scale = 4)
	private BigDecimal areaPosse;

	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	@Column(name = "DATAHORAFIM")
	private Date dataHoraFim;

	@Column(name = "IDUSUARIOEXCLUSAO", length = 40)
	private String idUsuarioExclusao;

	public Long getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	public Long getIdBemPessoaCompartilhamento() {
		return idBemPessoaCompartilhamento;
	}

	public void setIdBemPessoaCompartilhamento(Long idBemPessoaCompartilhamento) {
		this.idBemPessoaCompartilhamento = idBemPessoaCompartilhamento;
	}

	public Bem getBem() {
		return bem;
	}

	public void setBem(Bem bem) {
		this.bem = bem;
	}

	public BigDecimal getPercentualProprietario() {
		return percentualProprietario;
	}

	public void setPercentualProprietario(BigDecimal percentualProprietario) {
		this.percentualProprietario = percentualProprietario;
	}
	
	public BigDecimal getAreaPosse() {
		return areaPosse;
	}

	public void setAreaPosse(BigDecimal areaPosse) {
		this.areaPosse = areaPosse;
	}

	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	@Override
	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	@Override
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

	@Override
	public Long getId() {
		return getIdHistorico();
	}

	@Override
	public void setId(Long id) {
		setIdHistorico(id);
	}

	public Serializable getIdEntidadeVigente() {
		return getIdBemPessoaCompartilhamento();
	}

}