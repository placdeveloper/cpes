package br.com.sicoob.capes.negocio.entidades.bem;

import java.math.BigDecimal;

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

import org.hibernate.annotations.Formula;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa o relacionamento entre o {@link Bem} e a
 * {@link PessoaCompartilhamento}
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "BEMPESSOACOMPARTILHAMENTO")
@CamposAutorizacao(id = "idBemPessoaCompartilhamento", camposExibicao = {
		@CampoAutorizacao(propriedade = "pessoaCompartilhamento.nomePessoa", label = "NOME DA PESSOA"),
		@CampoAutorizacao(propriedade = "pessoaCompartilhamento.pessoa.cpfCnpj", label = "CPF / CNPJ", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorCpfCnpj"),
		@CampoAutorizacao(propriedade = "percentualProprietario", label = "PERCENTUAL DE PROPRIEDADE", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal") 
	}
)
public class BemPessoaCompartilhamento extends EntidadeCadastroBase implements Aprovavel, CadastroValidavel {
	private static final long serialVersionUID = -8947270105615246528L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDBEMPESSOACOMPARTILHAMENTO")
	private Long idBemPessoaCompartilhamento;
	
	@ManyToOne
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	@Column(name = "IDPESSOACOMPARTILHAMENTO", insertable = false, updatable = false)
	private Long idPessoaCompartilhamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDBEM")
	private Bem bem;
	
	@Column(name = "IDBEM", insertable = false, updatable = false)
	private Long idBem;

	@Column(name = "PERCPROPRIETARIO", precision = 5, scale = 2)
	private BigDecimal percentualProprietario;
	
	@Column(name = "TAMAREAPOSSE", precision = 13, scale = 4)
	private BigDecimal areaPosse;
	
	@Column(name = "BOLPESSOARESPONSAVEL")
	private Boolean pessoaResponsavel = Boolean.FALSE;
	
	@Column(name = "BOLMARCADOEXCLUSAO")
	private Boolean marcadoExclusao = Boolean.FALSE;

	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;

	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL THEN 1 "
			+ "ELSE COALESCE((SELECT CASE WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 0 THEN 2 "
			+ "WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 END "
			+ "FROM CLI.AUTORIZACAO A WHERE A.DESCTIPOAUTORIZACAO = 'BEM_NOVO' AND (A.IDREGISTROANTIGO = IDBEM OR A.IDREGISTRONOVO = IDBEM)), 0) END")
	private Short codigoSituacaoAprovacao;

	@Transient
	private SituacaoRegistroEnum situacaoAprovacao;

	@Transient
	private Boolean verificarAutorizacao = Boolean.TRUE;

	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

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
	
	public Long getIdBem() {
		return idBem;
	}

	public void setIdBem(Long idBem) {
		this.idBem = idBem;
	}

	/**
	 * Solução para utilização do equals para comparação do objeto utilizando
	 * atributos do tipo BigDecimal.
	 * 
	 * O flex não utiliza a escala com duas casas, quebrando a comparação do
	 * equals do BigDecimal
	 * 
	 * @return
	 */
	public BigDecimal getPercentualProprietario() {
		if (percentualProprietario != null && percentualProprietario.scale() != 2) {
			percentualProprietario = percentualProprietario.setScale(2);
		}
		return percentualProprietario;
	}

	/**
	 * Solução para utilização do equals para comparação do objeto utilizando
	 * atributos do tipo BigDecimal.
	 * 
	 * O flex não utiliza a escala com duas casas, quebrando a comparação do
	 * equals do BigDecimal
	 * 
	 * @param percentualProprietario
	 */
	public void setPercentualProprietario(BigDecimal percentualProprietario) {
		if (percentualProprietario != null && percentualProprietario.scale() != 2) {
			this.percentualProprietario = percentualProprietario.setScale(2);
		} else {
			this.percentualProprietario = percentualProprietario;
		}
	}
	
	public BigDecimal getAreaPosse() {
		return areaPosse;
	}

	public void setAreaPosse(BigDecimal areaPosse) {
		this.areaPosse = areaPosse;
	}

	public Boolean getPessoaResponsavel() {
		return pessoaResponsavel;
	}

	public void setPessoaResponsavel(Boolean pessoaResponsavel) {
		this.pessoaResponsavel = pessoaResponsavel;
	}

	public Boolean getMarcadoExclusao() {
		return marcadoExclusao;
	}

	public void setMarcadoExclusao(Boolean marcadoExclusao) {
		this.marcadoExclusao = marcadoExclusao;
	}

	@Override
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	@Override
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
	
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	public void setIdPessoaCompartilhamento(Long idpessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idpessoaCompartilhamento;
	}

	@Override
	public Integer getIdInstituicaoAtualizacao() {
		return idInstituicaoAtualizacao;
	}

	@Override
	public void setIdInstituicaoAtualizacao(Integer idInstituicaoAtualizacao) {
		this.idInstituicaoAtualizacao = idInstituicaoAtualizacao;
	}

	@Override
	public String getIdRegistroControlado() {
		return getBem().getIdRegistroControlado();
	}

	@Override
	public void setIdRegistroControlado(String idRegistroControlado) {
	}

	@Override
	public TipoAutorizacaoEnum getTipoAutorizacao() {
		return TipoAutorizacaoEnum.BEM_NOVO;
	}

	@Override
	public void setVerificarAutorizacao(Boolean verificarAutorizacao) {
		this.verificarAutorizacao = verificarAutorizacao;
	}

	@Override
	public Boolean getVerificarAutorizacao() {
		return verificarAutorizacao;
	}

	@Override
	public Short getCodigoSituacaoAprovacao() {
		return codigoSituacaoAprovacao;
	}

	@Override
	public void setCodigoSituacaoAprovacao(Short codigoSituacaoAprovacao) {
		this.codigoSituacaoAprovacao = codigoSituacaoAprovacao;
	}

	@Override
	public SituacaoRegistroEnum getSituacaoAprovacao() {
		if ((situacaoAprovacao == null) && (codigoSituacaoAprovacao != null)) {
			situacaoAprovacao = SituacaoRegistroEnum.valueOf(codigoSituacaoAprovacao);
		}
		return situacaoAprovacao;
	}

	@Override
	public void setSituacaoAprovacao(SituacaoRegistroEnum situacaoAprovacao) {
		this.situacaoAprovacao = situacaoAprovacao;
	}

	@Override
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	@Override
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdBemPessoaCompartilhamento();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdBemPessoaCompartilhamento(id);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * Implementado devido a interface Aprovavel,
	 * portanto retorna null por  a entidade não 
	 * conter o campo IdUsuarioEnvio.  
	 *
	 */
	@Override
	public String getIdUsuarioEnvio() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object objeto) {
		return ReflexaoUtils.equals(this, objeto, "idPessoaCompartilhamento", "percentualProprietario");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idPessoaCompartilhamento", "percentualProprietario");
	}

}