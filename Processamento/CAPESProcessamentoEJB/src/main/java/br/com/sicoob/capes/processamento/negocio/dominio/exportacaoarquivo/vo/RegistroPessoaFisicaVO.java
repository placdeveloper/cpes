package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class RegistroPessoaFisicaVO extends RegistroPessoaVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8654842137991612872L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "01";

	/** O atributo dataNascimento. */
	@CampoArquivo(inicio = 593, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataNascimento;

	/** O atributo menorEmancipado. */
	@CampoArquivo(inicio = 601, tamanho = 1, descBooleanFalse = "0", descBooleanTrue = "1")
	private Boolean menorEmancipado;

	/** O atributo nomePai. */
	@CampoArquivo(inicio = 602, tamanho = 80)
	private String nomePai;

	/** O atributo nomeMae. */
	@CampoArquivo(inicio = 682, tamanho = 80)
	private String nomeMae;

	/** O atributo tipoDocumento. */
	@CampoArquivo(inicio = 762, tamanho = 3, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short tipoDocumento;

	/** O atributo numeroDocumento. */
	@CampoArquivo(inicio = 765, tamanho = 45)
	private String numeroDocumento;

	/** O atributo orgaoEmissorDocumento. */
	@CampoArquivo(inicio = 810, tamanho = 16)
	private String orgaoEmissorDocumento;

	/** O atributo ufOrgaoEmissorDocumento. */
	@CampoArquivo(inicio = 826, tamanho = 2)
	private String ufOrgaoEmissorDocumento;

	/** O atributo dataEmissaoDocumento. */
	@CampoArquivo(inicio = 828, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataEmissaoDocumento;

	/** O atributo estadoCivil. */
	@CampoArquivo(inicio = 836, tamanho = 2, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short estadoCivil;

	/** O atributo sexo. */
	@CampoArquivo(inicio = 838, tamanho = 1)
	private Character sexo;

	/** O atributo grauInstrucao. */
	@CampoArquivo(inicio = 839, tamanho = 3, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short grauInstrucao;

	/** O atributo dependentes. */
	@CampoArquivo(inicio = 842, tamanho = 3, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short dependentes;

	/** O atributo regimeCasamento. */
	@CampoArquivo(inicio = 845, tamanho = 2, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short regimeCasamento;
	
	@CampoArquivo(inicio = 847, tamanho = 8)
	private String codigoOcupacaoProfissional;

	/** O atributo filler. */
	@CampoArquivo(inicio = 855, tamanho = 134)
	private String filler;

	/**
	 * Construtor
	 */
	public RegistroPessoaFisicaVO() {
		super(CODIGO_REGISTRO);
	}

	/**
	 * Construtor
	 * 
	 * @param idPessoaCompartilhamento
	 * @param cpfCnpj
	 * @param nome
	 * @param apelido
	 * @param nomeCompleto
	 * @param cnae
	 * @param dataInclusao
	 * @param dataUltimaRenovacao
	 * @param dataInclusaoSFN
	 * @param autorizaConsultaBacen
	 * @param dataNascimento
	 * @param menorEmancipado
	 * @param nomePai
	 * @param nomeMae
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param orgaoEmissorDocumento
	 * @param ufOrgaoEmissorDocumento
	 * @param dataEmissaoDocumento
	 * @param estadoCivil
	 * @param sexo
	 * @param grauInstrucao
	 * @param dependentes
	 * @param regimeCasamento
	 */
	public RegistroPessoaFisicaVO(Long idPessoaCompartilhamento, String cpfCnpj, String nome,
	        String apelido, String nomeCompleto, String cnae, Date dataInclusao,
	        Date dataUltimaRenovacao, Date dataInclusaoSFN, Boolean autorizaConsultaBacen,
	        Date dataNascimento, Boolean menorEmancipado, String nomePai, String nomeMae,
	        Short tipoDocumento, String numeroDocumento, String orgaoEmissorDocumento,
	        String ufOrgaoEmissorDocumento, Date dataEmissaoDocumento, Short estadoCivil,
	        Character sexo, Short grauInstrucao, Short dependentes, Short regimeCasamento) {

		super(CODIGO_REGISTRO, idPessoaCompartilhamento, cpfCnpj, nome, apelido, nomeCompleto,
		        cnae, dataInclusao, dataUltimaRenovacao, dataInclusaoSFN, autorizaConsultaBacen);
		this.dataNascimento = dataNascimento;
		this.menorEmancipado = menorEmancipado;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.orgaoEmissorDocumento = orgaoEmissorDocumento;
		this.ufOrgaoEmissorDocumento = ufOrgaoEmissorDocumento;
		this.dataEmissaoDocumento = dataEmissaoDocumento;
		this.estadoCivil = estadoCivil;
		this.sexo = sexo;
		this.grauInstrucao = grauInstrucao;
		this.dependentes = dependentes;
		this.regimeCasamento = regimeCasamento;
	}

	/**
	 * @return the dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento
	 *            the dataNascimento to set
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the menorEmancipado
	 */
	public Boolean getMenorEmancipado() {
		return menorEmancipado;
	}

	/**
	 * @param menorEmancipado
	 *            the menorEmancipado to set
	 */
	public void setMenorEmancipado(Boolean menorEmancipado) {
		this.menorEmancipado = menorEmancipado;
	}

	/**
	 * @return the nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * @param nomePai
	 *            the nomePai to set
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * @return the nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * @param nomeMae
	 *            the nomeMae to set
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * @return the tipoDocumento
	 */
	public Short getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(Short tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento
	 *            the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the orgaoEmissorDocumento
	 */
	public String getOrgaoEmissorDocumento() {
		return orgaoEmissorDocumento;
	}

	/**
	 * @param orgaoEmissorDocumento
	 *            the orgaoEmissorDocumento to set
	 */
	public void setOrgaoEmissorDocumento(String orgaoEmissorDocumento) {
		this.orgaoEmissorDocumento = orgaoEmissorDocumento;
	}

	/**
	 * @return the ufOrgaoEmissorDocumento
	 */
	public String getUfOrgaoEmissorDocumento() {
		return ufOrgaoEmissorDocumento;
	}

	/**
	 * @param ufOrgaoEmissorDocumento
	 *            the ufOrgaoEmissorDocumento to set
	 */
	public void setUfOrgaoEmissorDocumento(String ufOrgaoEmissorDocumento) {
		this.ufOrgaoEmissorDocumento = ufOrgaoEmissorDocumento;
	}

	/**
	 * @return the dataEmissaoDocumento
	 */
	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	/**
	 * @param dataEmissaoDocumento
	 *            the dataEmissaoDocumento to set
	 */
	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	/**
	 * @return the estadoCivil
	 */
	public Short getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil
	 *            the estadoCivil to set
	 */
	public void setEstadoCivil(Short estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the sexo
	 */
	public Character getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the grauInstrucao
	 */
	public Short getGrauInstrucao() {
		return grauInstrucao;
	}

	/**
	 * @param grauInstrucao
	 *            the grauInstrucao to set
	 */
	public void setGrauInstrucao(Short grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	/**
	 * @return the dependentes
	 */
	public Short getDependentes() {
		return dependentes;
	}

	/**
	 * @param dependentes
	 *            the dependentes to set
	 */
	public void setDependentes(Short dependentes) {
		this.dependentes = dependentes;
	}

	/**
	 * @return the regimeCasamento
	 */
	public Short getRegimeCasamento() {
		return regimeCasamento;
	}

	/**
	 * @param regimeCasamento
	 *            the regimeCasamento to set
	 */
	public void setRegimeCasamento(Short regimeCasamento) {
		this.regimeCasamento = regimeCasamento;
	}
	
	public String getCodigoOcupacaoProfissional() {
		return codigoOcupacaoProfissional;
	}

	public void setCodigoOcupacaoProfissional(String codigoOcupacaoProfissional) {
		this.codigoOcupacaoProfissional = codigoOcupacaoProfissional;
	}

	/**
	 * @return the filler
	 */
	public String getFiller() {
		return filler;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}