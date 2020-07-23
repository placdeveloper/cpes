package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;

/**
 * A Classe RegistroHeaderVO.
 */
public class RegistroHeaderVO extends RegistroArquivoBaseVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 513904808624383205L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "00";

	/** O atributo numeroCooperativa. */
	@CampoArquivo(inicio = 2, tamanho = 5, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short numeroCooperativa;

	/** O atributo siglaCooperativa. */
	@CampoArquivo(inicio = 7, tamanho = 60)
	private String siglaCooperativa;

	/** O atributo nomeCooperativa. */
	@CampoArquivo(inicio = 67, tamanho = 200)
	private String nomeCooperativa;

	/** O atributo cnpj. */
	@CampoArquivo(inicio = 267, tamanho = 14, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private String cnpj;

	/** O atributo dataMovimento. */
	@CampoArquivo(inicio = 281, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataMovimento;

	/** O atributo identificadorArquivo. */
	@CampoArquivo(inicio = 289, tamanho = 38)
	private String identificadorArquivo;

	/** O atributo contadorArquivo. */
	@CampoArquivo(inicio = 327, tamanho = 5, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private String contadorArquivo;

	/** O atributo codigoEnvio. */
	@CampoArquivo(inicio = 332, tamanho = 1)
	private Character codigoEnvio;

	/** O atributo filler. */
	@CampoArquivo(inicio = 333, tamanho = 656)
	private String filler;

	/**
	 * @param codigoRegistro
	 */
	public RegistroHeaderVO() {
		super(CODIGO_REGISTRO);
	}

	/**
	 * Construtor
	 * 
	 * @param numeroCooperativa
	 * @param nomeCooperativa
	 * @param siglaCooperativa
	 * @param cnpj
	 * @param codigoRegistro
	 */
	public RegistroHeaderVO(Short numeroCooperativa, String nomeCooperativa, String siglaCooperativa, String cnpj) {
		this();
		this.numeroCooperativa = numeroCooperativa;
		this.siglaCooperativa = siglaCooperativa;
		this.nomeCooperativa = nomeCooperativa;
		this.cnpj = cnpj;
	}

	/**
	 * Construtor
	 * 
	 * @param numeroCooperativa
	 * @param nomeCooperativa
	 * @param siglaCooperativa
	 * @param cnpj
	 * @param dataMovimento
	 * @param identificadorArquivo
	 * @param contadorArquivo
	 * @param codigoEnvio
	 * @param filler
	 */
	public RegistroHeaderVO(Short numeroCooperativa, String nomeCooperativa, String siglaCooperativa, String cnpj, 
			Date dataMovimento, String identificadorArquivo, String contadorArquivo, Character codigoEnvio) {
		this(numeroCooperativa, nomeCooperativa, siglaCooperativa, cnpj);
		this.dataMovimento = dataMovimento;
		this.identificadorArquivo = identificadorArquivo;
		this.contadorArquivo = contadorArquivo;
		this.codigoEnvio = codigoEnvio;
	}

	/**
	 * Instancia um novo RegistroHeaderVO.
	 *
	 * @param sicoob o valor de sicoob
	 * @param dataMovimento o valor de data movimento
	 * @param identificadorArquivo o valor de identificador arquivo
	 * @param contadorArquivo o valor de contador arquivo
	 * @param codigoEnvio o valor de codigo envio
	 */
	public RegistroHeaderVO(InstituicaoVO sicoob, Date dataMovimento, String identificadorArquivo, String contadorArquivo, Character codigoEnvio) {
		this(sicoob.getNumero().shortValue(), sicoob.getNome(), sicoob.getSiglaInstituicao(), 
				sicoob.getCnpj(), dataMovimento, identificadorArquivo, contadorArquivo, codigoEnvio);
	}

	/**
	 * @return the numeroCooperativa
	 */
	public Short getNumeroCooperativa() {
		return numeroCooperativa;
	}

	/**
	 * @param numeroCooperativa
	 *            the numeroCooperativa to set
	 */
	public void setNumeroCooperativa(Short numeroCooperativa) {
		this.numeroCooperativa = numeroCooperativa;
	}

	/**
	 * @return the siglaCooperativa
	 */
	public String getSiglaCooperativa() {
		return siglaCooperativa;
	}

	/**
	 * @param siglaCooperativa
	 *            the siglaCooperativa to set
	 */
	public void setSiglaCooperativa(String siglaCooperativa) {
		this.siglaCooperativa = siglaCooperativa;
	}

	/**
	 * @return the nomeCooperativa
	 */
	public String getNomeCooperativa() {
		return nomeCooperativa;
	}

	/**
	 * @param nomeCooperativa
	 *            the nomeCooperativa to set
	 */
	public void setNomeCooperativa(String nomeCooperativa) {
		this.nomeCooperativa = nomeCooperativa;
	}

	/**
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj
	 *            the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return the dataMovimento
	 */
	public Date getDataMovimento() {
		return dataMovimento;
	}

	/**
	 * @param dataMovimento
	 *            the dataMovimento to set
	 */
	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	/**
	 * @return the identificadorArquivo
	 */
	public String getIdentificadorArquivo() {
		return identificadorArquivo;
	}

	/**
	 * @param identificadorArquivo
	 *            the identificadorArquivo to set
	 */
	public void setIdentificadorArquivo(String identificadorArquivo) {
		this.identificadorArquivo = identificadorArquivo;
	}

	/**
	 * @return the contadorArquivo
	 */
	public String getContadorArquivo() {
		return contadorArquivo;
	}

	/**
	 * @param contadorArquivo
	 *            the contadorArquivo to set
	 */
	public void setContadorArquivo(String contadorArquivo) {
		this.contadorArquivo = contadorArquivo;
	}

	/**
	 * @return the codigoEnvio
	 */
	public Character getCodigoEnvio() {
		return codigoEnvio;
	}

	/**
	 * @param codigoEnvio
	 *            the codigoEnvio to set
	 */
	public void setCodigoEnvio(Character codigoEnvio) {
		this.codigoEnvio = codigoEnvio;
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