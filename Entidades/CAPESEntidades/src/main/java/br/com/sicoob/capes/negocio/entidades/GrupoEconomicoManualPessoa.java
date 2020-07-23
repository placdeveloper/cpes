package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.interfaces.MotivoExclusao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Entity
@Table(name = "GRUPOECONOMICOMANUALPESSOA", schema = "CLI")
public class GrupoEconomicoManualPessoa extends CAPESEntidade<Integer> implements Vigente, MotivoExclusao {

	private static final long serialVersionUID = 6230950944859080738L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGRUPOECONOMICOMANUALPESSOA")
	private Integer id;

	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;

	@Column(name = "DATAHORAINICIO", nullable = false)
	private DateTimeDB dataHoraInicio;

	@Column(length = 40, name = "IDUSUARIOCADASTRO")
	private String idUsuarioCadastro;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDGRUPOECONOMICOCENTRALIZADO", nullable = false)
	private GrupoEconomicoNovo grupoEconomico;

	@ManyToOne(optional = true)
	@JoinColumn(name = "IDGRUPOECONOMICOAUTOMATICO", nullable = true)
	private GrupoEconomicoNovo grupoEconomicoAutomatico;

	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	@Transient
	private String idUsuarioExclusao;

	@Transient
	private String motivoExclusao;

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

	public GrupoEconomicoNovo getGrupoEconomicoAutomatico() {
		return grupoEconomicoAutomatico;
	}

	public void setGrupoEconomicoAutomatico(GrupoEconomicoNovo grupoEconomicoAutomatico) {
		this.grupoEconomicoAutomatico = grupoEconomicoAutomatico;
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

	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

}
