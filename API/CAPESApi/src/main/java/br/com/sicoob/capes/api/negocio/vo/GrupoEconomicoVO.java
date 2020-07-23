/*
 * SICOOB
 * 
 * GrupoEconomicoVO.java(br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Lucas.Borges
 */
public class GrupoEconomicoVO extends BancoobDto{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 3427996523937888891L;

	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;

	/** O atributo id grupo economico pessoa. */
	private Integer idGrupoEconomicoPessoa;
	
	/** O atributo id grupo economico. */
	private Integer idGrupoEconomico;
	
	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo descricao grupo. */
	private String descricaoGrupo;
	
	/** O atributo data hora cadastro. */
	private Date dataHoraCadastro;
	
	/**
	 * Cria uma nova instância de grupo economico vo.
	 */
	public GrupoEconomicoVO(){
		
	}
	
	/**
	 * @param idPessoa
	 * @param idInstituicao
	 * @param idGrupoEconomicoPessoa
	 * @param idGrupoEconomico
	 * @param dataHoraInicio
	 * @param descricaoGrupo
	 * @param dataHoraCadastro
	 */
	public GrupoEconomicoVO(Integer idPessoa, Integer idInstituicao,
			Integer idGrupoEconomicoPessoa, Date dataHoraInicio,
			Integer idGrupoEconomico, String descricaoGrupo, Date dataHoraCadastro) {
		super();
		this.idPessoa = idPessoa;
		this.idInstituicao = idInstituicao;
		this.idGrupoEconomicoPessoa = idGrupoEconomicoPessoa;
		this.idGrupoEconomico = idGrupoEconomico;
		this.dataHoraInicio = dataHoraInicio;
		this.descricaoGrupo = descricaoGrupo;
		this.dataHoraCadastro = dataHoraCadastro;
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
	 * Recupera id grupo economico pessoa.
	 * 
	 * @return id grupo economico pessoa
	 */
	public Integer getIdGrupoEconomicoPessoa() {
		return idGrupoEconomicoPessoa;
	}

	/**
	 * Preenche id grupo economico pessoa.
	 * 
	 * @param idGrupoEconomicoPessoa
	 *            o novo id grupo economico pessoa
	 */
	public void setIdGrupoEconomicoPessoa(Integer idGrupoEconomicoPessoa) {
		this.idGrupoEconomicoPessoa = idGrupoEconomicoPessoa;
	}

	/**
	 * Recupera id grupo economico.
	 * 
	 * @return id grupo economico
	 */
	public Integer getIdGrupoEconomico() {
		return idGrupoEconomico;
	}

	/**
	 * Preenche id grupo economico.
	 * 
	 * @param idGrupoEconomico
	 *            o novo id grupo economico
	 */
	public void setIdGrupoEconomico(Integer idGrupoEconomico) {
		this.idGrupoEconomico = idGrupoEconomico;
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
	 * Recupera descricao grupo.
	 * 
	 * @return descricao grupo
	 */
	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}

	/**
	 * Preenche descricao grupo.
	 * 
	 * @param descricaoGrupo
	 *            o novo descricao grupo
	 */
	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}

	/**
	 * Recupera data hora cadastro.
	 * 
	 * @return data hora cadastro
	 */
	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	/**
	 * Preenche data hora cadastro.
	 * 
	 * @param dataHoraCadastro
	 *            o novo data hora cadastro
	 */
	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
	

	
}
