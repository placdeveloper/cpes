package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.util.Date;

public class ClienteDTO extends RegistroInclusaoDTO<Integer> {
	private static final long serialVersionUID = 8883448025849633386L;

	private Integer idPessoaInstituicao;
	private Integer codigoFuncionario;
	private Integer codigoPerfilTarifario;
	private Integer codigoNucleo;
	private String parecerGerencia;
	private Boolean emiteAvisoLancamento = Boolean.FALSE;
	private Date dataEnquadramentoRisco;
	private String nivelRisco;
	private String motivoRisco;

	public Integer getIdPessoaInstituicao() {
		return idPessoaInstituicao;
	}

	public void setIdPessoaInstituicao(Integer idPessoaInstituicao) {
		this.idPessoaInstituicao = idPessoaInstituicao;
	}

	public Integer getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(Integer codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public Integer getCodigoPerfilTarifario() {
		return codigoPerfilTarifario;
	}

	public void setCodigoPerfilTarifario(Integer codigoPerfilTarifario) {
		this.codigoPerfilTarifario = codigoPerfilTarifario;
	}

	public Integer getCodigoNucleo() {
		return codigoNucleo;
	}

	public void setCodigoNucleo(Integer codigoNucleo) {
		this.codigoNucleo = codigoNucleo;
	}

	public String getParecerGerencia() {
		return parecerGerencia;
	}

	public void setParecerGerencia(String parecerGerencia) {
		this.parecerGerencia = parecerGerencia;
	}

	public Boolean getEmiteAvisoLancamento() {
		return emiteAvisoLancamento;
	}

	public void setEmiteAvisoLancamento(Boolean emiteAvisoLancamento) {
		this.emiteAvisoLancamento = emiteAvisoLancamento;
	}

	public Date getDataEnquadramentoRisco() {
		return dataEnquadramentoRisco;
	}

	public void setDataEnquadramentoRisco(Date dataEnquadramentoRisco) {
		this.dataEnquadramentoRisco = dataEnquadramentoRisco;
	}

	public String getNivelRisco() {
		return nivelRisco;
	}

	public void setNivelRisco(String nivelRisco) {
		this.nivelRisco = nivelRisco;
	}

	public String getMotivoRisco() {
		return motivoRisco;
	}

	public void setMotivoRisco(String motivoRisco) {
		this.motivoRisco = motivoRisco;
	}

	@Override
	public Integer getId() {
		return getIdPessoaInstituicao();
	}

	@Override
	public void setId(Integer id) {
		setIdPessoaInstituicao(id);
	}

}