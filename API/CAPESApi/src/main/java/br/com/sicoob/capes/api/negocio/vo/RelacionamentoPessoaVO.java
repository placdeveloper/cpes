/*
 * SICOOB
 * 
 * RelacionamentoPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Lucas.Borges
 */
public class RelacionamentoPessoaVO extends AbstractPessoaVO{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -3313171645464992280L;

	// RELACIONAMENTO PESSOA
	/** O atributo id relacionamento. */
	private Long idRelacionamento;
	
	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo id pessoa relacionada. */
	private Integer idPessoaRelacionada;
	
	/** O atributo percentual capital empresa. */
	private BigDecimal percentualCapitalEmpresa;
	
	/** O atributo data inicio relacionamento. */
	private Date dataInicioRelacionamento;
	
	/** O atributo data fim relacionamento. */
	private Date dataFimMandato;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	// TIPO RELACIONAMENTO
	/** O atributo codigo tipo relacionamento. */
	private Short codigoTipoRelacionamento;

	/** O atributo descricao tipo relacionamento. */
	private String descricaoTipoRelacionamento;
	
	/** O atributo habilita dados registro. */
	private Boolean habilitaDadosRegistro;
	
	/** O atributo habilita poderes. */
	private Boolean habilitaPoderes;
	
	//DADOS REGISTRO
	/** O atributo dados registro. */
	private DadosRegistroVO dadosRegistro;
	
	//PODERES
	/** O atributo poderes. */
	private List<RelacionamentoPessoaPoderVO> poderes;
	

	/**
	 * Cria uma nova instância de relacionamento pessoa vo.
	 */
	public RelacionamentoPessoaVO() {

	}
	
	/**
	 * Cria uma nova instância de relacionamento pessoa vo.
	 * 
	 * @param idRelacionamento
	 *            the id relacionamento
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param idPessoa
	 *            the id pessoa
	 * @param idPessoaRelacionada
	 *            the id pessoa relacionada
	 * @param percentualCapitalEmpresa
	 *            the percentual capital empresa
	 * @param dataInicioRelacionamento
	 *            the data inicio relacionamento
	 * @param dataFimMandato
	 *            the data fim relacionamento
	 * @param idInstituicao
	 *            the id instituicao
	 * @param codigoTipoRelacionamento
	 *            the codigo tipo relacionamento
	 * @param descricaoTipoRelacionamento
	 *            the descricao tipo relacionamento
	 * @param habilitaDadosRegistro
	 *            the habilita dados registro
	 * @param habilitaPoderes
	 *            the habilita poderes
	 */
	public RelacionamentoPessoaVO(Long idRelacionamento,
			Date dataHoraInicio, Integer idPessoa, Integer idPessoaRelacionada,
			BigDecimal percentualCapitalEmpresa, Date dataInicioRelacionamento,
			Date dataFimMandato, Integer idInstituicao,
			Short codigoTipoRelacionamento, String descricaoTipoRelacionamento,
			Boolean habilitaDadosRegistro, Boolean habilitaPoderes) {
		this.idRelacionamento = idRelacionamento;
		this.dataHoraInicio = dataHoraInicio;
		this.idPessoa = idPessoa;
		this.idPessoaRelacionada = idPessoaRelacionada;
		this.percentualCapitalEmpresa = percentualCapitalEmpresa;
		this.dataInicioRelacionamento = dataInicioRelacionamento;
		this.dataFimMandato = dataFimMandato;
		this.idInstituicao = idInstituicao;
		this.codigoTipoRelacionamento = codigoTipoRelacionamento;
		this.descricaoTipoRelacionamento = descricaoTipoRelacionamento;	
		this.habilitaDadosRegistro = habilitaDadosRegistro;
		this.setHabilitaPoderes(habilitaPoderes);
	}

	/**
	 * Recupera id relacionamento.
	 * 
	 * @return id relacionamento
	 */
	public Long getIdRelacionamento() {
		return idRelacionamento;
	}

	/**
	 * Preenche id relacionamento.
	 * 
	 * @param idRelacionamento
	 *            o novo id relacionamento
	 */
	public void setIdRelacionamento(Long idRelacionamento) {
		this.idRelacionamento = idRelacionamento;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera id pessoa.
	 * 
	 * @return id pessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Preenche id pessoa.
	 * 
	 * @param idPessoa
	 *            o novo id pessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera id pessoa relacionada.
	 * 
	 * @return id pessoa relacionada
	 */
	public Integer getIdPessoaRelacionada() {
		return idPessoaRelacionada;
	}

	/**
	 * Preenche id pessoa relacionada.
	 * 
	 * @param idPessoaRelacionada
	 *            o novo id pessoa relacionada
	 */
	public void setIdPessoaRelacionada(Integer idPessoaRelacionada) {
		this.idPessoaRelacionada = idPessoaRelacionada;
	}

	/**
	 * Recupera percentual capital empresa.
	 * 
	 * @return percentual capital empresa
	 */
	public BigDecimal getPercentualCapitalEmpresa() {
		return percentualCapitalEmpresa;
	}

	/**
	 * Preenche percentual capital empresa.
	 * 
	 * @param percentualCapitalEmpresa
	 *            o novo percentual capital empresa
	 */
	public void setPercentualCapitalEmpresa(BigDecimal percentualCapitalEmpresa) {
		this.percentualCapitalEmpresa = percentualCapitalEmpresa;
	}

	/**
	 * Recupera data inicio relacionamento.
	 * 
	 * @return data inicio relacionamento
	 */
	public Date getDataInicioRelacionamento() {
		return dataInicioRelacionamento;
	}

	/**
	 * Preenche data inicio relacionamento.
	 * 
	 * @param dataInicioRelacionamento
	 *            o novo data inicio relacionamento
	 */
	public void setDataInicioRelacionamento(Date dataInicioRelacionamento) {
		this.dataInicioRelacionamento = dataInicioRelacionamento;
	}

	/**
	 * Recupera data fim relacionamento.
	 * 
	 * @return data fim relacionamento
	 */
	public Date getDataFimMandato() {
		return dataFimMandato;
	}

	/**
	 * Preenche data fim relacionamento.
	 * 
	 * @param dataFimMandato
	 *            o novo data fim relacionamento
	 */
	public void setDataFimMandato(Date dataFimMandato) {
		this.dataFimMandato = dataFimMandato;
	}
	
	/**
	 * Recupera data fim relacionamento.
	 * @deprecated Utilizar dataFimMandato esse metodo será removido na versão 1.3.0 do CAPES
	 * 
	 * @return data fim relacionamento
	 */
	@Deprecated
	public Date getDataFimRelacionamento() {
		return getDataFimMandato();
	}

	/**
	 * Preenche data fim relacionamento.
	 * @deprecated Utilizar dataFimMandato esse metodo será removido na versão 1.3.0 do CAPES
	 * 
	 * @param dataFimMandato
	 *            o novo data fim relacionamento
	 */
	@Deprecated
	public void setDataFimRelacionamento(Date dataFimMandato) {
		this.dataFimMandato = dataFimMandato;
	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera codigo tipo relacionamento.
	 * 
	 * @return codigo tipo relacionamento
	 */
	public Short getCodigoTipoRelacionamento() {
		return codigoTipoRelacionamento;
	}

	/**
	 * Preenche codigo tipo relacionamento.
	 * 
	 * @param codigoTipoRelacionamento
	 *            o novo codigo tipo relacionamento
	 */
	public void setCodigoTipoRelacionamento(Short codigoTipoRelacionamento) {
		this.codigoTipoRelacionamento = codigoTipoRelacionamento;
	}

	/**
	 * Recupera descricao tipo relacionamento.
	 * 
	 * @return descricao tipo relacionamento
	 */
	public String getDescricaoTipoRelacionamento() {
		return descricaoTipoRelacionamento;
	}

	/**
	 * Preenche descricao tipo relacionamento.
	 * 
	 * @param descricaoTipoRelacionamento
	 *            o novo descricao tipo relacionamento
	 */
	public void setDescricaoTipoRelacionamento(String descricaoTipoRelacionamento) {
		this.descricaoTipoRelacionamento = descricaoTipoRelacionamento;
	}

	/**
	 * Recupera habilita dados registro.
	 * 
	 * @return habilita dados registro
	 */
	public Boolean getHabilitaDadosRegistro() {
		return habilitaDadosRegistro;
	}

	/**
	 * Preenche habilita dados registro.
	 * 
	 * @param habilitaDadosRegistro
	 *            o novo habilita dados registro
	 */
	public void setHabilitaDadosRegistro(Boolean habilitaDadosRegistro) {
		this.habilitaDadosRegistro = habilitaDadosRegistro;
	}

	/**
	 * @return the habilitaPoderes
	 */
	public Boolean getHabilitaPoderes() {
		return habilitaPoderes;
	}

	/**
	 * @param habilitaPoderes the habilitaPoderes to set
	 */
	public void setHabilitaPoderes(Boolean habilitaPoderes) {
		this.habilitaPoderes = habilitaPoderes;
	}

	/**
	 * Recupera poderes.
	 * 
	 * @return poderes
	 */
	public List<RelacionamentoPessoaPoderVO> getPoderes() {
		return poderes;
	}

	/**
	 * Preenche poderes.
	 * 
	 * @param poderes
	 *            o novo poderes
	 */
	public void setPoderes(List<RelacionamentoPessoaPoderVO> poderes) {
		this.poderes = poderes;
	}

	/**
	 * @return the dadosRegistro
	 */
	public DadosRegistroVO getDadosRegistro() {
		return dadosRegistro;
	}

	/**
	 * @param dadosRegistro the dadosRegistro to set
	 */
	public void setDadosRegistro(DadosRegistroVO dadosRegistro) {
		this.dadosRegistro = dadosRegistro;
	}

	
}