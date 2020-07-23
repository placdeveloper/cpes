package br.com.sicoob.capes.api.inclusao.negocio.dto;

/**
 * DTO que com os dados para a execução do procedimento.
 * 
 * @author Bruno.Carneiro
 */
public class ExecutarProcedimentoAutorizacaoDTO extends CAPESApiInclusaoDTO {
	private static final long serialVersionUID = -8621858058685161338L;

	private Long idRegistro;
	private Integer idInstituicaoRegistro;
	private String justificativa;

	private Short numeroVersaoRegistroAtividade;
	private Integer codigoOcorrenciaAtividade;
	private Integer idProcedimento;
	private String nomeProcedimento;

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Short getNumeroVersaoRegistroAtividade() {
		return numeroVersaoRegistroAtividade;
	}

	public void setNumeroVersaoRegistroAtividade(Short numeroVersaoRegistroAtividade) {
		this.numeroVersaoRegistroAtividade = numeroVersaoRegistroAtividade;
	}

	public Integer getCodigoOcorrenciaAtividade() {
		return codigoOcorrenciaAtividade;
	}

	public void setCodigoOcorrenciaAtividade(Integer codigoOcorrenciaAtividade) {
		this.codigoOcorrenciaAtividade = codigoOcorrenciaAtividade;
	}

	public Integer getIdProcedimento() {
		return idProcedimento;
	}

	public void setIdProcedimento(Integer idProcedimento) {
		this.idProcedimento = idProcedimento;
	}

	public String getNomeProcedimento() {
		return nomeProcedimento;
	}

	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}

	public Integer getIdInstituicaoRegistro() {
		return idInstituicaoRegistro;
	}

	public void setIdInstituicaoRegistro(Integer idInstituicaoRegistro) {
		this.idInstituicaoRegistro = idInstituicaoRegistro;
	}

}