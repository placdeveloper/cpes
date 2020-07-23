package br.com.sicoob.capes.negocio.entidades.historico;

import java.io.Serializable;
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
import br.com.sicoob.capes.negocio.entidades.interfaces.MotivoExclusao;

/**
 * Classe que responsavel por armarzenar as informacoes de HistoricoGrupoEconomicoManualPessoa.
 * 
 * @author valdemar.xavier
 * 
 */
@Entity
@Table(name = "HISTGRUPOECONOMICOMANUALPESSOA", schema = "CLI")
public class HistoricoGrupoEconomicoManualPessoa extends CAPESEntidade<Integer> implements Historico, MotivoExclusao {

	private static final long serialVersionUID = -8371826362239149056L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTGRUPOECONOMICOMANUALPESSOA")
	private Integer id;

	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	@Column(name = "IDUSUARIOCADASTRO")
	private String idUsuarioCadastro;

	@Column(name = "IDPESSOACOMPARTILHAMENTO")
	private Long idPessoaCompartilhamento;

	@Column(name = "DATAHORAFIM")
	private Date dataHoraFim;

	@Column(name = "DESCMOTIVOEXCLUSAO")
	private String motivoExclusao;


	@Column(name = "IDHISTGRUPOECONOMICOCENTRALIZADO")
	private Integer idHistoricoGrupoEconomico;

	@Column(length = 40, name = "IDUSUARIOEXCLUSAO", updatable = false)
	private String idUsuarioExclusao;

	@Column(name = "IDGRUPOECONOMICOMANUALPESSOA")
	private Integer idGrupoManualPessoa;
	
	@Column(name = "IDGRUPOECONOMICOAUTOMATICO")
	private Integer idGrupoEconomicoAutomatico;

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

	public String getIdUsuarioCadastro() {
		return idUsuarioCadastro;
	}

	public void setIdUsuarioCadastro(String idUsuarioCadastro) {
		this.idUsuarioCadastro = idUsuarioCadastro;
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

	public Integer getIdGrupoManualPessoa() {
		return idGrupoManualPessoa;
	}

	public void setIdGrupoManualPessoa(Integer idGrupoManualPessoa) {
		this.idGrupoManualPessoa = idGrupoManualPessoa;
	}
	
	public Integer getIdGrupoEconomicoAutomatico() {
		return idGrupoEconomicoAutomatico;
	}

	public void setIdGrupoEconomicoAutomatico(Integer idGrupoEconomicoAutomatico) {
		this.idGrupoEconomicoAutomatico = idGrupoEconomicoAutomatico;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	@Override
	public Serializable getIdEntidadeVigente() {
		return getIdGrupoManualPessoa();
	}

}
