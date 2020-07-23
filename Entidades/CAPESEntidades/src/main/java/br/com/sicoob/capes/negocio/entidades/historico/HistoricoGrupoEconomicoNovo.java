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
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.interfaces.MotivoExclusao;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Entity
@Table(name = "HISTGRUPOECONOMICOCENTRALIZADO", schema = "CLI")
public class HistoricoGrupoEconomicoNovo extends CAPESEntidade<Integer> implements Historico, MotivoExclusao {

	private static final long serialVersionUID = 7940239710313329273L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTGRUPOECONOMICOCENTRALIZADO")
	private Integer id;

	@Column(name = "IDGRUPOECONOMICOCENTRALIZADO", nullable = false, updatable = false)
	private Integer idGrupo;

	@Column(name = "NOMEGRUPOECONOMICOCENTRALIZADO", length = 200, nullable = false, updatable = false)
	private String nome;

	@Column(name = "DATAHORACADASTRO", nullable = false, updatable = false)
	private DateTimeDB dataHoraInicio;

	@Column(name = "DATAHORAFIM", updatable = false)
	private Date dataHoraFim;

	@Column(name = "DESCMOTIVOEXCLUSAO", updatable = false)
	private String motivoExclusao;

	@Column(length = 40, name = "IDUSUARIOCADASTRO", nullable = false, updatable = false)
	private String idUsuarioCadastro;

	@Column(name = "CODTIPOGRUPOECONOMICO", nullable = false, updatable = false)
	private Short codTipoGrupoEconomico;

	@Column(length = 40, name = "IDUSUARIOEXCLUSAO", updatable = false)
	private String idUsuarioExclusao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getIdUsuarioCadastro() {
		return idUsuarioCadastro;
	}

	public void setIdUsuarioCadastro(String idUsuarioCadastro) {
		this.idUsuarioCadastro = idUsuarioCadastro;
	}

	public Short getCodTipoGrupoEconomico() {
		return codTipoGrupoEconomico;
	}

	public void setCodTipoGrupoEconomico(Short codTipoGrupoEconomico) {
		this.codTipoGrupoEconomico = codTipoGrupoEconomico;
	}

	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "id");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "id");
	}

	@Override
	public Serializable getIdEntidadeVigente() {
		return getIdGrupo();
	}

	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

}
