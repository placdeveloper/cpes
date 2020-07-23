package br.com.sicoob.capes.negocio.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Entity(name = "GrupoEconomicoNovoLimpo")
@Table(name = "GRUPOECONOMICOCENTRALIZADO", schema = "CLI")
@Immutable
public class GrupoEconomicoNovoLimpo extends CAPESEntidade<Integer> {

	private static final long serialVersionUID = 3498338214547705278L;

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

	@OneToMany(mappedBy = "grupoEconomico")
	private List<GrupoEconomicoManualPessoa> integrantesManual;

	@OneToMany(mappedBy = "grupoEconomico")
	private List<GrupoEconomicoAutomaticoPessoa> integrantesAutomatico;

	@Transient
	private Integer idInstituicao;

	/**
	 * 
	 */
	public GrupoEconomicoNovoLimpo() {
	}

	/**
	 * 
	 * @param id
	 */
	public GrupoEconomicoNovoLimpo(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 */
	public GrupoEconomicoNovoLimpo(Integer id, String nome) {
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

	public DateTimeDB getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(DateTimeDB dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
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

	public TipoGrupoEconomico getTipo() {
		return tipo;
	}

	public void setTipo(TipoGrupoEconomico tipo) {
		this.tipo = tipo;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
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
