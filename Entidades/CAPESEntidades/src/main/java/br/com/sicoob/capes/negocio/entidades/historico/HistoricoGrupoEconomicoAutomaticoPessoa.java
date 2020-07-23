package br.com.sicoob.capes.negocio.entidades.historico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * Classe que responsavel por armarzenar as informacoes de HistoricoGrupoEconomicoAutomaticoPessoa.
 * 
 * @author valdemar.xavier
 * 
 */
@Entity
@Table(name = "HISTGRUPOECONOMICOAUTOMATICOPESSOA", schema = "CLI")
public class HistoricoGrupoEconomicoAutomaticoPessoa extends CAPESEntidade<Integer> implements Historico {

	private static final long serialVersionUID = -6513991222705560135L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTGRUPOECONOMICOAUTOMATICOPESSOA")
	private Integer id;

	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	@Column(name = "BOLCONTROLADOR")
	private Boolean bolControlador;

	@Column(name = "PERCSOCIO")
	private BigDecimal percentualSocio;

	@Column(name = "IDRELACIONAMENTOPESSOA")
	private Long idRelacionamento;

	@Column(name = "IDPESSOACOMPARTILHAMENTO")
	private Long idPessoaCompartilhamento;

	@Column(name = "DATAHORAFIM")
	private Date dataHoraFim;

	@Column(name = "DESCMOTIVOEXCLUSAO")
	private String motivoExclusao;

	@Column(name = "IDGRUPOECONOMICOAUTOMATICOPESSOA")
	private Integer idGrupoAutomaticoPessoa;

	@Column(name = "IDUSUARIOCADASTRO")
	private String idUsuarioCadastro;

	@Column(name = "IDHISTGRUPOECONOMICOCENTRALIZADO")
	private Integer idHistoricoGrupoEconomico;

	@Column(length = 40, name = "IDUSUARIOEXCLUSAO", updatable = false)
	private String idUsuarioExclusao;

	@Column(name = "IDGRUPOECONOMICOCENTRALIZADO", nullable = false, updatable = false)
	private Integer idGrupo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Long getIdRelacionamento() {
		return idRelacionamento;
	}

	public void setIdRelacionamento(Long idRelacionamento) {
		this.idRelacionamento = idRelacionamento;
	}

	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	public Integer getIdGrupoAutomaticoPessoa() {
		return idGrupoAutomaticoPessoa;
	}

	public void setIdGrupoAutomaticoPessoa(Integer idGrupoAutomaticoPessoa) {
		this.idGrupoAutomaticoPessoa = idGrupoAutomaticoPessoa;
	}

	public String getIdUsuarioCadastro() {
		return idUsuarioCadastro;
	}

	public void setIdUsuarioCadastro(String idUsuarioCadastro) {
		this.idUsuarioCadastro = idUsuarioCadastro;
	}

	public Integer getIdHistoricoGrupoEconomico() {
		return idHistoricoGrupoEconomico;
	}

	public void setIdHistoricoGrupoEconomico(Integer idHistoricoGrupoEconomico) {
		this.idHistoricoGrupoEconomico = idHistoricoGrupoEconomico;
	}

	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	@Override
	public Serializable getIdEntidadeVigente() {
		return getIdGrupoAutomaticoPessoa();
	}

}
