package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.util.Date;

/**
 * Classe com as informações profissionais.
 *
 * @author Bruno.Carneiro
 */
public class InformacaoProfissionalDTO extends RegistroInclusaoDTO<Integer> {
	private static final long serialVersionUID = 4282259879218248074L;

	private Integer idInformacaoProfissional;
	private Integer idPessoaEmpregador;
	private String numMatricula;
	private Short codigoTipoFuncionario;
	private Short codigoTipoAfastamento;
	private Short codSituacao;
	private String cargo;
	private Date dataAdmissao;
	private Date dataDesligamento;
	private String diaMesFerias;
	private Short codigoConselhoRegional;
	private String ufConselho;
	private String numeroInscricaoConselho;

	public Integer getIdInformacaoProfissional() {
		return idInformacaoProfissional;
	}

	public void setIdInformacaoProfissional(Integer idInformacaoProfissional) {
		this.idInformacaoProfissional = idInformacaoProfissional;
	}

	public Integer getIdPessoaEmpregador() {
		return idPessoaEmpregador;
	}

	public void setIdPessoaEmpregador(Integer idPessoaEmpregador) {
		this.idPessoaEmpregador = idPessoaEmpregador;
	}

	public String getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(String numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Short getCodigoTipoFuncionario() {
		return codigoTipoFuncionario;
	}

	public void setCodigoTipoFuncionario(Short codigoTipoFuncionario) {
		this.codigoTipoFuncionario = codigoTipoFuncionario;
	}

	public Short getCodigoTipoAfastamento() {
		return codigoTipoAfastamento;
	}

	public void setCodigoTipoAfastamento(Short codigoTipoAfastamento) {
		this.codigoTipoAfastamento = codigoTipoAfastamento;
	}

	public Short getCodSituacao() {
		return codSituacao;
	}

	public void setCodSituacao(Short codSituacao) {
		this.codSituacao = codSituacao;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getDiaMesFerias() {
		return diaMesFerias;
	}

	public void setDiaMesFerias(String diaMesFerias) {
		this.diaMesFerias = diaMesFerias;
	}

	public Date getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	public Short getCodigoConselhoRegional() {
		return codigoConselhoRegional;
	}

	public void setCodigoConselhoRegional(Short codigoConselhoRegional) {
		this.codigoConselhoRegional = codigoConselhoRegional;
	}

	public String getUfConselho() {
		return ufConselho;
	}

	public void setUfConselho(String ufConselho) {
		this.ufConselho = ufConselho;
	}

	public String getNumeroInscricaoConselho() {
		return numeroInscricaoConselho;
	}

	public void setNumeroInscricaoConselho(String numeroInscricaoConselho) {
		this.numeroInscricaoConselho = numeroInscricaoConselho;
	}

	@Override
	public Integer getId() {
		return getIdInformacaoProfissional();
	}

	@Override
	public void setId(Integer id) {
		setIdInformacaoProfissional(id);
	}

}