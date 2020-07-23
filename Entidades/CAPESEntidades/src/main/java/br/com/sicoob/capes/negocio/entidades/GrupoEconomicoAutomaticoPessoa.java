package br.com.sicoob.capes.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import flexjson.JSON;

/**
 * Classe que responsavel por armarzenar as informacoes de GrupoEconomicoAutomaticoPessoa.
 * 
 * @author valdemar.xavier
 * 
 */
@Entity
@Table(name = "GRUPOECONOMICOAUTOMATICOPESSOA", schema = "CLI")
public class GrupoEconomicoAutomaticoPessoa extends CAPESEntidade<Integer> implements Vigente {

	private static final long serialVersionUID = -8791917616942539041L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGRUPOECONOMICOAUTOMATICOPESSOA")
	private Integer id;

	@ManyToOne(optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "IDRELACIONAMENTOPESSOA", nullable = false)
	private RelacionamentoPessoa relacionamentoPessoa;

	@ManyToOne(optional = false)
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	private PessoaCompartilhamento pessoaCompartilhamento;

	@Column(name = "DATAHORAINICIO", nullable = false)
	private DateTimeDB dataHoraInicio;

	@Column(name = "BOLCONTROLADOR", nullable = false)
	private Boolean bolControlador;

	@Column(name = "PERCSOCIO", precision = 5, scale = 2, nullable = true)
	private BigDecimal percentualSocio;

	@ManyToOne(optional = false)
	@JoinColumn(name = "IDGRUPOECONOMICOCENTRALIZADO", nullable = false)
	private GrupoEconomicoNovo grupoEconomico;

	@Column(length = 40, name = "IDUSUARIOCADASTRO")
	private String idUsuarioCadastro;

	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;
	
	@Transient
	private String idUsuarioExclusao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GrupoEconomicoNovo getGrupoEconomico() {
		return grupoEconomico;
	}

	public void setGrupoEconomico(GrupoEconomicoNovo grupoEconomico) {
		this.grupoEconomico = grupoEconomico;
	}

	@JSON(include=false)
	public RelacionamentoPessoa getRelacionamentoPessoa() {
		return relacionamentoPessoa;
	}

	public void setRelacionamentoPessoa(RelacionamentoPessoa relacionamentoPessoa) {
		this.relacionamentoPessoa = relacionamentoPessoa;
	}

	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Boolean getBolControlador() {
		return bolControlador;
	}

	public void setBolControlador(Boolean bolControlador) {
		this.bolControlador = bolControlador;
	}

	public BigDecimal getPercentualSocio() {
		return percentualSocio;
	}

	public void setPercentualSocio(BigDecimal percentualSocio) {
		this.percentualSocio = percentualSocio;
	}

	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}

	public String getIdUsuarioCadastro() {
		return idUsuarioCadastro;
	}

	public void setIdUsuarioCadastro(String idUsuarioCadastro) {
		this.idUsuarioCadastro = idUsuarioCadastro;
	}

	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}
	
}
