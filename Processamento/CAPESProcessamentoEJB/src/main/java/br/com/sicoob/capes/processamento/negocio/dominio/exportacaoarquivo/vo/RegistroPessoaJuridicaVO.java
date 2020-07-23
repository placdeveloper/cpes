package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class RegistroPessoaJuridicaVO extends RegistroPessoaVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 8615635544586254129L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "02";

	/** O atributo dataConstituicao. */
	@CampoArquivo(inicio = 593, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataConstituicao;

	/** O atributo valorCapitalSocial. */
	@CampoArquivo(inicio = 601, tamanho = 19, precisaoDecimal = 2, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private BigDecimal valorCapitalSocial;

	/** O atributo inscricaoEstadual. */
	@CampoArquivo(inicio = 620, tamanho = 15)
	private String inscricaoEstadual;

	/** O atributo tipoEmpresa. */
	@CampoArquivo(inicio = 635, tamanho = 2, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short tipoEmpresa;

	/** O atributo formaConstituicao. */
	@CampoArquivo(inicio = 637, tamanho = 5, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short formaConstituicao;

	/** O atributo numeroRegistroJuntaComercial. */
	@CampoArquivo(inicio = 642, tamanho = 20)
	private String numeroRegistroJuntaComercial;

	/** O atributo dataRegistroJuntaComercial. */
	@CampoArquivo(inicio = 662, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataRegistroJuntaComercial;

	/** O atributo numeroUltimaAlteracaoContratual. */
	@CampoArquivo(inicio = 670, tamanho = 20)
	private String numeroUltimaAlteracaoContratual;

	/** O atributo dataUltimaAlteracaoContratual. */
	@CampoArquivo(inicio = 690, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataUltimaAlteracaoContratual;

	/** O atributo filler. */
	@CampoArquivo(inicio = 698, tamanho = 291)
	private String filler;

	/**
	 * Construtor
	 */
	public RegistroPessoaJuridicaVO() {
		super(CODIGO_REGISTRO);
	}

	/**
	 * Construtor
	 * 
	 * @param codigoRegistro
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
	 * @param dataConstituicao
	 * @param valorCapitalSocial
	 * @param inscricaoEstadual
	 * @param tipoEmpresa
	 * @param formaConstituicao
	 * @param numeroRegistroJuntaComercial
	 * @param dataRegistroJuntaComercial
	 * @param numeroUltimaAlteracaoContratual
	 * @param dataUltimaAlteracaoContratual
	 */
	public RegistroPessoaJuridicaVO(Long idPessoaCompartilhamento, String cpfCnpj, String nome,
	        String apelido, String nomeCompleto, String cnae, Date dataInclusao,
	        Date dataUltimaRenovacao, Date dataInclusaoSFN, Boolean autorizaConsultaBacen,
	        Date dataConstituicao, BigDecimal valorCapitalSocial, String inscricaoEstadual,
	        Short tipoEmpresa, Short formaConstituicao, String numeroRegistroJuntaComercial,
	        Date dataRegistroJuntaComercial, String numeroUltimaAlteracaoContratual,
	        Date dataUltimaAlteracaoContratual) {

		super(CODIGO_REGISTRO, idPessoaCompartilhamento, cpfCnpj, nome, apelido, nomeCompleto,
				cnae, dataInclusao, dataUltimaRenovacao, dataInclusaoSFN, autorizaConsultaBacen);
		this.dataConstituicao = dataConstituicao;
		this.valorCapitalSocial = valorCapitalSocial;
		this.inscricaoEstadual = inscricaoEstadual;
		this.tipoEmpresa = tipoEmpresa;
		this.formaConstituicao = formaConstituicao;
		this.numeroRegistroJuntaComercial = numeroRegistroJuntaComercial;
		this.dataRegistroJuntaComercial = dataRegistroJuntaComercial;
		this.numeroUltimaAlteracaoContratual = numeroUltimaAlteracaoContratual;
		this.dataUltimaAlteracaoContratual = dataUltimaAlteracaoContratual;
	}

	/**
	 * @return the dataConstituicao
	 */
	public Date getDataConstituicao() {
		return dataConstituicao;
	}

	/**
	 * @param dataConstituicao
	 *            the dataConstituicao to set
	 */
	public void setDataConstituicao(Date dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}

	/**
	 * @return the valorCapitalSocial
	 */
	public BigDecimal getValorCapitalSocial() {
		return valorCapitalSocial;
	}

	/**
	 * @param valorCapitalSocial
	 *            the valorCapitalSocial to set
	 */
	public void setValorCapitalSocial(BigDecimal valorCapitalSocial) {
		this.valorCapitalSocial = valorCapitalSocial;
	}

	/**
	 * @return the inscricaoEstadual
	 */
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	/**
	 * @param inscricaoEstadual
	 *            the inscricaoEstadual to set
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * @return the tipoEmpresa
	 */
	public Short getTipoEmpresa() {
		return tipoEmpresa;
	}

	/**
	 * @param tipoEmpresa
	 *            the tipoEmpresa to set
	 */
	public void setTipoEmpresa(Short tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * @return the formaConstituicao
	 */
	public Short getFormaConstituicao() {
		return formaConstituicao;
	}

	/**
	 * @param formaConstituicao
	 *            the formaConstituicao to set
	 */
	public void setFormaConstituicao(Short formaConstituicao) {
		this.formaConstituicao = formaConstituicao;
	}

	/**
	 * @return the numeroRegistroJuntaComercial
	 */
	public String getNumeroRegistroJuntaComercial() {
		return numeroRegistroJuntaComercial;
	}

	/**
	 * @param numeroRegistroJuntaComercial
	 *            the numeroRegistroJuntaComercial to set
	 */
	public void setNumeroRegistroJuntaComercial(String numeroRegistroJuntaComercial) {
		this.numeroRegistroJuntaComercial = numeroRegistroJuntaComercial;
	}

	/**
	 * @return the dataRegistroJuntaComercial
	 */
	public Date getDataRegistroJuntaComercial() {
		return dataRegistroJuntaComercial;
	}

	/**
	 * @param dataRegistroJuntaComercial
	 *            the dataRegistroJuntaComercial to set
	 */
	public void setDataRegistroJuntaComercial(Date dataRegistroJuntaComercial) {
		this.dataRegistroJuntaComercial = dataRegistroJuntaComercial;
	}

	/**
	 * @return the numeroUltimaAlteracaoContratual
	 */
	public String getNumeroUltimaAlteracaoContratual() {
		return numeroUltimaAlteracaoContratual;
	}

	/**
	 * @param numeroUltimaAlteracaoContratual
	 *            the numeroUltimaAlteracaoContratual to set
	 */
	public void setNumeroUltimaAlteracaoContratual(String numeroUltimaAlteracaoContratual) {
		this.numeroUltimaAlteracaoContratual = numeroUltimaAlteracaoContratual;
	}

	/**
	 * @return the dataUltimaAlteracaoContratual
	 */
	public Date getDataUltimaAlteracaoContratual() {
		return dataUltimaAlteracaoContratual;
	}

	/**
	 * @param dataUltimaAlteracaoContratual
	 *            the dataUltimaAlteracaoContratual to set
	 */
	public void setDataUltimaAlteracaoContratual(Date dataUltimaAlteracaoContratual) {
		this.dataUltimaAlteracaoContratual = dataUltimaAlteracaoContratual;
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