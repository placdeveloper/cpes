package br.com.sicoob.capes.negocio.entidades.bem.historico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A classe que representa a entidade Histórico Bem.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "HISTBEM")
@Inheritance(strategy = InheritanceType.JOINED)
public class HistoricoBem extends EntidadeCadastroBase implements Historico {
	private static final long serialVersionUID = -6656516405851263293L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTBEM")
	private Long idHistorico;

	@Column(name = "IDBEM")
	private Long idBem;
	
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	@ManyToOne
	@JoinColumn(name = "CODTIPOCLASSIFICACAOBEM", referencedColumnName = "CODTIPOCLASSIFICACAOBEM")
	private TipoClassificacaoBem tipoClassificacaoBem;

	@Column(name = "BOLBEMINTERNO")
	private Boolean interno = Boolean.FALSE;

	@Column(name = "DESCBEM", length = 1000)
	private String descricao;

	@Column(name = "VALORBEM", precision = 19, scale = 2)
	private BigDecimal valor;

	@Column(name = "BOLVALORBEMNAOINFORMADO")
	private Boolean valorNaoInformado = Boolean.FALSE;

	@Column(name = "MESRENOVACAOSEGURO")
	private Short mesRenovacaoSeguro;

	@ManyToOne
	@JoinColumn(name = "CODCOMPARTILHAMENTOCADASTRO", referencedColumnName = "CODCOMPARTILHAMENTOCADASTRO")
	private CompartilhamentoCadastro compartilhamentoCadastro;
	
	@Column(name = "DATAHORAFIM")
	private Date dataHoraFim;

	@Column(name = "IDUSUARIOEXCLUSAO", length = 40)
	private String idUsuarioExclusao;
	
	@Column(name = "IDUSUARIOENVIO", length=40, nullable=true)
	private String idUsuarioEnvio;

	public Long getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	public Long getIdBem() {
		return idBem;
	}

	public void setIdBem(Long idBem) {
		this.idBem = idBem;
	}
	
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public TipoClassificacaoBem getTipoClassificacaoBem() {
		return tipoClassificacaoBem;
	}

	public void setTipoClassificacaoBem(TipoClassificacaoBem tipoClassificacaoBem) {
		this.tipoClassificacaoBem = tipoClassificacaoBem;
	}

	public Boolean getInterno() {
		return interno;
	}

	public void setInterno(Boolean interno) {
		this.interno = interno;
	}

	public Boolean getValorNaoInformado() {
		return valorNaoInformado;
	}

	public void setValorNaoInformado(Boolean valorNaoInformado) {
		this.valorNaoInformado = valorNaoInformado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public CompartilhamentoCadastro getCompartilhamentoCadastro() {
		return compartilhamentoCadastro;
	}

	public void setCompartilhamentoCadastro(CompartilhamentoCadastro compartilhamentoCadastro) {
		this.compartilhamentoCadastro = compartilhamentoCadastro;
	}

	public Short getMesRenovacaoSeguro() {
		return mesRenovacaoSeguro;
	}

	public void setMesRenovacaoSeguro(Short mesRenovacaoSeguro) {
		this.mesRenovacaoSeguro = mesRenovacaoSeguro;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

	public Serializable getIdEntidadeVigente() {
		return getIdBem();
	}

	@Override
	public Long getId() {
		return getIdHistorico();
	}

	@Override
	public void setId(Long id) {
		setIdHistorico(id);
	}

	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return null;
	}

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}

}