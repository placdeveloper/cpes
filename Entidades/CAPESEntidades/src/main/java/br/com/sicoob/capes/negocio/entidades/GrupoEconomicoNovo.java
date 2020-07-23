package br.com.sicoob.capes.negocio.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.interfaces.MotivoExclusao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Entity
@Table(name = "GRUPOECONOMICOCENTRALIZADO", schema = "CLI")
public class GrupoEconomicoNovo extends CAPESEntidade<Integer> implements Vigente, MotivoExclusao {

	private static final long serialVersionUID = 5614138065700726278L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGRUPOECONOMICOCENTRALIZADO", nullable = false)
	private Integer id;

	@Column(name = "NOMEGRUPOECONOMICOCENTRALIZADO", length = 200, nullable = false)
	private String nome;

	@Column(name = "DATAHORACADASTRO", nullable = false)
	private DateTimeDB dataHoraCadastro;

	@Column(name = "DATAHORAINICIO", nullable = false)
	private DateTimeDB dataHoraInicio;

	@Column(length = 40, name = "IDUSUARIOCADASTRO")
	private String idUsuarioCadastro;

	@ManyToOne(optional = false)
	@JoinColumn(name = "CODTIPOGRUPOECONOMICO", nullable = false)
	private TipoGrupoEconomico tipo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoEconomico", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SUBSELECT)
	private List<GrupoEconomicoManualPessoa> integrantesManual;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "grupoEconomico", orphanRemoval = false)
	@Fetch(FetchMode.SUBSELECT)
	private List<GrupoEconomicoAutomaticoPessoa> integrantesAutomatico;

	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	@Transient
	private String idUsuarioExclusao;

	@Transient
	private List<GrupoEconomicoManualPessoa> integrantesManualExclusao;

	@Transient
	private String motivoExclusao;

	@Transient
	private Integer idInstituicao;

	/**
	 * 
	 */
	public GrupoEconomicoNovo() {
	}

	/**
	 * 
	 * @param id
	 */
	public GrupoEconomicoNovo(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 */
	public GrupoEconomicoNovo(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdUsuarioCadastro() {
		return idUsuarioCadastro;
	}

	public void setIdUsuarioCadastro(String idUsuarioCadastro) {
		this.idUsuarioCadastro = idUsuarioCadastro;
	}

	public TipoGrupoEconomico getTipo() {
		return tipo;
	}

	public void setTipo(TipoGrupoEconomico tipo) {
		this.tipo = tipo;
	}

	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}

	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public DateTimeDB getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(DateTimeDB dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public List<GrupoEconomicoManualPessoa> getIntegrantesManual() {
		return integrantesManual;
	}

	public void setIntegrantesManual(List<GrupoEconomicoManualPessoa> integrantesManual) {
		this.integrantesManual = integrantesManual;
	}

	public List<GrupoEconomicoAutomaticoPessoa> getIntegrantesAutomatico() {
		return integrantesAutomatico;
	}

	public void setIntegrantesAutomatico(List<GrupoEconomicoAutomaticoPessoa> integrantesAutomatico) {
		this.integrantesAutomatico = integrantesAutomatico;
	}

	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

	public List<GrupoEconomicoManualPessoa> getIntegrantesManualExclusao() {
		return integrantesManualExclusao;
	}

	public void setIntegrantesManualExclusao(List<GrupoEconomicoManualPessoa> integrantesManualExclusao) {
		this.integrantesManualExclusao = integrantesManualExclusao;
	}

	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "id", "nome");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "id", "nome");
	}

}
