package br.com.sicoob.capes.negocio.entidades.bem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.ParamDef;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.NaoVerificarGestorResponsavel;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum;
import br.com.sicoob.capes.comum.util.Constantes.Persistencia;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.CadastroValidavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Comprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe base dos bens móveis e imóveis.
 * 
 * O histórico de bens está com as informações da entidade, não seguindo uma
 * estrutura base, pois as entidades seguem uma herança de bem.
 * 
 * @author bruno.carneiro
 */
@Entity
@Table(schema = "CLI", name = "BEM")
@Inheritance(strategy = InheritanceType.JOINED)
@FilterDef(name = Persistencia.FILTRO_COMPARTILHAMENTO_CADASTRO, parameters = { @ParamDef(type = "short", name = "codigo") })
@Filter(name = Persistencia.FILTRO_COMPARTILHAMENTO_CADASTRO, condition = "codCompartilhamentoCadastro = :codigo")
@CamposAutorizacao(id = "idBem", camposExibicao = {
		@CampoAutorizacao(propriedade = "dataHoraInicio", label = "DATA DO CADASTRO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy", ordenacao = 1),
		@CampoAutorizacao(propriedade = "tipoClassificacaoBem.descricao", label = "TIPO DE CLASSIFICAÇÃO", ordenacao = 2),
		@CampoAutorizacao(propriedade = "descricao", label = "DESCRIÇÃO DO BEM", ordenacao = 3),
		@CampoAutorizacao(propriedade = "valor", label = "VALOR DO BEM", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario", ordenacao = 4),
		@CampoAutorizacao(propriedade = "mesRenovacaoSeguro", label = "MÊS DE RENOVAÇÃO DO SEGURO", ordenacao = 5),
		@CampoAutorizacao(propriedade = "proprietarios", label = "PROPRIETÁRIOS", isLista = true, propriedadesComparacaoLista = {"idPessoaCompartilhamento"})
})

@NaoVerificarGestorResponsavel
public class Bem extends EntidadeCadastroBase implements Comprovavel, Vigente, CadastroValidavel {
	private static final long serialVersionUID = -2067732965930431418L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDBEM")
	private Long idBem;
	
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	@ManyToOne
	@JoinColumn(name = "CODTIPOCLASSIFICACAOBEM")
	private TipoClassificacaoBem tipoClassificacaoBem;
	
	@Column(name = "DESCBEM", length = 1000)
	private String descricao;

	@Column(name = "BOLBEMINTERNO")
	private Boolean interno = Boolean.FALSE;

	@Column(name = "VALORBEM", precision = 19, scale = 2)
	private BigDecimal valor;

	@Column(name = "BOLVALORBEMNAOINFORMADO")
	private Boolean valorNaoInformado = Boolean.FALSE;

	@Column(name = "MESRENOVACAOSEGURO")
	private Short mesRenovacaoSeguro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODCOMPARTILHAMENTOCADASTRO", referencedColumnName = "CODCOMPARTILHAMENTOCADASTRO")
	private CompartilhamentoCadastro compartilhamentoCadastro;

	@Column(name = "CODCOMPARTILHAMENTOCADASTRO", insertable = false, updatable = false)
	private Short codCompartilhamentoCadastro;

	@OneToMany(mappedBy = "bem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = BemPessoaCompartilhamento.class)
	private List<BemPessoaCompartilhamento> proprietarios;

	@Column(name = "IDINSTITUICAOATUALIZACAO")
	private Integer idInstituicaoAtualizacao;

	@Formula("CASE WHEN DATAHORAINICIO IS NOT NULL AND IDINSTITUICAOATUALIZACAO IS NOT NULL THEN 1 "
			+ "ELSE COALESCE((SELECT CASE WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 0 THEN 2 "
			+ "WHEN A.DATAHORASOLICITACAO IS NOT NULL AND A.BOLDEVOLVIDO = 1 THEN 3 ELSE 4 END "
			+ "FROM CLI.AUTORIZACAO A WHERE A.DESCTIPOAUTORIZACAO = 'BEM_NOVO' "
			+ "AND (A.IDREGISTROANTIGO = IDBEM OR A.IDREGISTRONOVO = IDBEM)), 0) END")
	private Short codigoSituacaoAprovacao;
	
	@Formula("COALESCE((SELECT A.DESCTIPOAUTORIZACAO || '" + SEPARADOR_ID_REGISTRO_CONTROLADO
			+ "' || TO_CHAR(A.IDREGISTROANTIGO) FROM CLI.AUTORIZACAO A "
			+ "WHERE A.DESCTIPOAUTORIZACAO = 'BEM_NOVO' AND A.CODTIPOOPERACAO = 'A' "
			+ "AND A.IDREGISTRONOVO = IDBEM FETCH FIRST 1 ROWS ONLY), 'BEM_NOVO"
			+ SEPARADOR_ID_REGISTRO_CONTROLADO + "' || IDBEM)")
	private String idRegistroControlado;
	
//	@Formula("COALESCE((SELECT CASE WHEN (TI.IDINFORMACAOPESSOA IS NOT NULL) THEN 'Em Garantia'"
//            + " WHEN (BIA.BOLEMPROCESSOAQUISICAO = 1 OR BMA.BOLEMPROCESSOAQUISICAO = 1) THEN 'Em Aquisição'"
//            + " WHEN (BI.IDPESSOACOMPARTILHAMENTOCARTORIO IS NOT NULL OR BM.NUMCHASSI IS NOT NULL OR BM.DESCINCRICAOEMBARCACAO IS NOT NULL OR BM.DESCMATRICULAAERONAVE IS NOT NULL) THEN 'Avançado'"
//            + " WHEN (BI.IDPESSOACOMPARTILHAMENTOCARTORIO IS NULL AND BM.NUMCHASSI IS NULL AND BM.DESCINCRICAOEMBARCACAO IS NULL AND BM.DESCMATRICULAAERONAVE IS NULL) THEN 'Simples'"
//            + " END FROM CLI.BEM B"
//            + " LEFT JOIN CLI.BEMIMOVEL BI ON BI.IDBEM = B.IDBEM"
//            + " LEFT JOIN CLI.BEMIMOVELAVALIACAO BIA ON BIA.IDBEM = BI.IDBEM"
//            + " LEFT JOIN CLI.BEMMOVEL BM ON BM.IDBEM = B.IDBEM"
//            + " LEFT JOIN CLI.BEMMOVELAVALIACAO BMA ON BMA.IDBEM = BM.IDBEM"
//            + " LEFT JOIN CLI.TIPOINFUTILIZAINFPESSOA TI ON TI.IDINFORMACAOPESSOA = B.IDBEM AND TI.IDUTILIZAINFORMACAOPESSOA = 2 AND TI.IDTIPOINFORMACAOPESSOA = 9"
//            + " WHERE B.IDBEM = IDBEM), NULL)")
	@Transient
	private String status;
	
	@Transient
	private SituacaoRegistroEnum situacaoAprovacao;

	@Transient
	private Boolean verificarAutorizacao = Boolean.TRUE;

	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	@Transient
	private Set<DocumentoComprobatorio> documentosComprobatorios;
	
	@Column(name="IDUSUARIOENVIO")
	private String idUsuarioEnvio;

	public Long getIdBem() {
		return idBem;
	}

	public void setIdBem(Long idBem) {
		this.idBem = idBem;
	}
	
	@Override
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	@Override
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
	
	public Short getCodCompartilhamentoCadastro() {
		return codCompartilhamentoCadastro;
	}

	public void setCodCompartilhamentoCadastro(Short codCompartilhamentoCadastro) {
		this.codCompartilhamentoCadastro = codCompartilhamentoCadastro;
	}

	public Short getMesRenovacaoSeguro() {
		return mesRenovacaoSeguro;
	}

	public void setMesRenovacaoSeguro(Short mesRenovacaoSeguro) {
		this.mesRenovacaoSeguro = mesRenovacaoSeguro;
	}
	
	public List<BemPessoaCompartilhamento> getProprietarios() {
		return proprietarios;
	}

	public void setProprietarios(List<BemPessoaCompartilhamento> proprietarios) {
		this.proprietarios = proprietarios;
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
		if (idRegistroControlado == null) {
			idRegistroControlado = getTipoAutorizacao().name() + SEPARADOR_ID_REGISTRO_CONTROLADO + getId();
		}
		return idRegistroControlado;
	}

	@Override
	public void setIdRegistroControlado(String idRegistroControlado) {
		this.idRegistroControlado = idRegistroControlado;
	}

	@Override
	@Transient
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

	@Override
	public Set<DocumentoComprobatorio> getDocumentosComprobatorios() {
		return documentosComprobatorios;
	}

	@Override
	public void setDocumentosComprobatorios(Set<DocumentoComprobatorio> documentosComprobatorios) {
		this.documentosComprobatorios = documentosComprobatorios;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Obtém a pessoaCompartilhamento responsável pelo {@link Bem}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		for (BemPessoaCompartilhamento bemPessoaCompartilhamento : getProprietarios()) {
			if (bemPessoaCompartilhamento.getPessoaResponsavel()) {
				return bemPessoaCompartilhamento.getPessoaCompartilhamento();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdBem();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdBem(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIdUsuarioEnvio() {
		return this.idUsuarioEnvio;
	}
	
	/**
	 * Preenche idUsuarioEnvio.
	 * 
	 * @param idUsuarioEnvio
	 */
	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object objeto) {
		return ReflexaoUtils.equals(this, objeto, "idBem", "tipoBem");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idBem", "tipoBem");
	}

}