package br.com.sicoob.capes.cadastro.negocio.dto;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * A Classe TransfInformacoesDTO.
 */
public class TransfInformacoesDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -6135298284779062374L;

	/** O atributo criterios. */
	private ConsultaDto<?> criterios;

	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;

	/** O atributo gerente. */
	private Funcionario gerente;

	/** O atributo nucleo. */
	private Nucleo nucleo;

	/** O atributo grupo. */
	private GrupoEconomico grupo;

	/** O atributo pessoaInstituicao. */
	private boolean pessoaInstituicao;
	
	/** O atributo listaCpfCnpj. */
	private List<String> listaCpfCnpj;
	
	/** O atributo buscaPorCpfCnpj. */
	private Boolean buscaPorCpfCnpj = false;

	/** O atributo idInstituicaoUsuarioLogado. */
	private Integer idInstituicaoUsuarioLogado;
	
	/** O atributo loginUsuarioLogado. */
	private String idUsuarioLogado;
	
	/**
	 * Instancia um novo TransfInformacoesDTO.
	 *
	 * @param criterios o valor de criterios
	 * @param idUnidadeInst o valor de id unidade inst
	 * @param gerente o valor de gerente
	 * @param nucleo o valor de nucleo
	 */
	public TransfInformacoesDTO(ConsultaDto<PessoaInstituicao> criterios, Integer idUnidadeInst, Funcionario gerente, Nucleo nucleo) {
		super();
		this.criterios = criterios;
		this.idUnidadeInst = idUnidadeInst;
		this.gerente = gerente;
		this.nucleo = nucleo;
		this.grupo = null;
		this.pessoaInstituicao = true;
	}

	/**
	 * Instancia um novo TransfInformacoesDTO.
	 *
	 * @param criterios o valor de criterios
	 * @param grupo o valor de grupo
	 */
	public TransfInformacoesDTO(ConsultaDto<GrupoEconomicoPessoa> criterios, GrupoEconomico grupo) {
		super(); 
		this.criterios = criterios;
		this.grupo = grupo;
		this.idUnidadeInst = null;
		this.gerente = null;
		this.nucleo = null;
		this.pessoaInstituicao = false;
	}

	/**
	 * Instancia um novo TransfInformacoesDTO.
	 *
	 * @param listaCpfCnpj o valor de listaCpfCnpj
	 * @param idUnidadeInst o valor de id unidade inst
	 * @param gerente o valor de gerente
	 * @param idInstituicaoUsuarioLogado o valor do idInstituicaoUsuarioLogado
	 */
	public TransfInformacoesDTO(List<String> listaCpfCnpj, Integer idUnidadeInst, Funcionario gerente, Integer idInstituicaoUsuarioLogado, Nucleo nucleoDestino) {
		super();
		this.listaCpfCnpj = listaCpfCnpj;
		this.idUnidadeInst = idUnidadeInst;
		this.gerente = gerente;
		this.idInstituicaoUsuarioLogado = idInstituicaoUsuarioLogado;
		this.nucleo = nucleoDestino;
		this.buscaPorCpfCnpj = true;
		
	}
	
	/**
	 * Recupera o valor de criterios.
	 *
	 * @return o valor de criterios
	 */
	public ConsultaDto<?> getCriterios() {
		return criterios;
	}

	/**
	 * Recupera o valor de idUnidadeInst.
	 *
	 * @return o valor de idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Recupera o valor de gerente.
	 *
	 * @return o valor de gerente
	 */
	public Funcionario getGerente() {
		return gerente;
	}

	/**
	 * Recupera o valor de nucleo.
	 *
	 * @return o valor de nucleo
	 */
	public Nucleo getNucleo() {
		return nucleo;
	}

	/**
	 * Recupera o valor de grupo.
	 *
	 * @return o valor de grupo
	 */
	public GrupoEconomico getGrupo() {
		return grupo;
	}

	/**
	 * Verifica se eh pessoa instituicao.
	 *
	 * @return {@code true}, se for pessoa instituicao
	 */
	public boolean isPessoaInstituicao() {
		return pessoaInstituicao;
	}

	/**
	 * Define o valor de criterios.
	 *
	 * @param criterios o novo valor de criterios
	 */
	public void setCriterios(ConsultaDto<?> criterios) {
		this.criterios = criterios;
	}

	/**
	 * Define o valor de idUnidadeInst.
	 *
	 * @param idUnidadeInst o novo valor de idUnidadeInst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Define o valor de gerente.
	 *
	 * @param gerente o novo valor de gerente
	 */
	public void setGerente(Funcionario gerente) {
		this.gerente = gerente;
	}

	/**
	 * Define o valor de nucleo.
	 *
	 * @param nucleo o novo valor de nucleo
	 */
	public void setNucleo(Nucleo nucleo) {
		this.nucleo = nucleo;
	}

	/**
	 * Define o valor de grupo.
	 *
	 * @param grupo o novo valor de grupo
	 */
	public void setGrupo(GrupoEconomico grupo) {
		this.grupo = grupo;
	}

	/**
	 * Define o valor de pessoaInstituicao.
	 *
	 * @param pessoaInstituicao o novo valor de pessoaInstituicao
	 */
	public void setPessoaInstituicao(boolean pessoaInstituicao) {
		this.pessoaInstituicao = pessoaInstituicao;
	}

	/**
	 * Recupera o valor de listaCpfCnpj.
	 *
	 * @return o valor de listaCpfCnpj
	 */
	public List<String> getListaCpfCnpj() {
		return listaCpfCnpj;
	}

	/**
	 * Define o valor de listaCpfCnpj.
	 *
	 * @param listaCpfCnpj o novo valor de listaCpfCnpj
	 */
	public void setListaCpfCnpj(List<String> listaCpfCnpj) {
		this.listaCpfCnpj = listaCpfCnpj;
	}

	/**
	 * Recupera o valor de buscaPorCpfCnpj.
	 *
	 * @return o valor de buscaPorCpfCnpj
	 */
	public Boolean getBuscaPorCpfCnpj() {
		return buscaPorCpfCnpj;
	}

	/**
	 * Define o valor de buscaPorCpfCnpj.
	 *
	 * @param buscaPorCpfCnpj o novo valor de buscaPorCpfCnpj
	 */
	public void setBuscaPorCpfCnpj(Boolean buscaPorCpfCnpj) {
		this.buscaPorCpfCnpj = buscaPorCpfCnpj;
	}

	public Integer getIdInstituicaoUsuarioLogado() {
		return idInstituicaoUsuarioLogado;
	}

	public void setIdInstituicaoUsuarioLogado(Integer idInstituicaoUsuarioLogado) {
		this.idInstituicaoUsuarioLogado = idInstituicaoUsuarioLogado;
	}

	public String getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(String idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}