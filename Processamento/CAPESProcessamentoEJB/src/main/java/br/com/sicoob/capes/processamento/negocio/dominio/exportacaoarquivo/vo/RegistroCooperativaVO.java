package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class RegistroCooperativaVO extends RegistroDadosArquivoBaseVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6531774223822648407L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "20";

	/** O atributo numero. */
	@CampoArquivo(inicio = 21, tamanho = 5, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private String numero;

	/** O atributo sigla. */
	@CampoArquivo(inicio = 26, tamanho = 60)
	private String sigla;

	/** O atributo nome. */
	@CampoArquivo(inicio = 86, tamanho = 200)
	private String nome;

	/** O atributo pac. */
	@CampoArquivo(inicio = 286, tamanho = 5, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Integer pac;

	/** O atributo responsavelCadastro. */
	@CampoArquivo(inicio = 291, tamanho = 1, descBooleanFalse = "0", descBooleanTrue = "1")
	private Boolean responsavelCadastro;

	/** O atributo filler. */
	@CampoArquivo(inicio = 292, tamanho = 697)
	private String filler;

	/**
	 * Construtor
	 */
	public RegistroCooperativaVO() {
		super(CODIGO_REGISTRO);
	}

	/**
	 * Construtor
	 * 
	 * @param codigoRegistro
	 * @param numero
	 * @param sigla
	 * @param nome
	 * @param pac
	 * @param responsavelCadastro
	 */
	public RegistroCooperativaVO(Long idPessoaCompartilhamento, String numero, String sigla, String nome, Integer pac, Boolean responsavelCadastro) {
		super(CODIGO_REGISTRO, idPessoaCompartilhamento);
		this.numero = numero;
		this.sigla = sigla;
		this.nome = nome;
		this.pac = pac;
		this.responsavelCadastro = responsavelCadastro;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla
	 *            the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the pac
	 */
	public Integer getPac() {
		return pac;
	}

	/**
	 * @param pac
	 *            the pac to set
	 */
	public void setPac(Integer pac) {
		this.pac = pac;
	}

	/**
	 * @return the responsavelCadastro
	 */
	public Boolean getResponsavelCadastro() {
		return responsavelCadastro;
	}

	/**
	 * @param responsavelCadastro
	 *            the responsavelCadastro to set
	 */
	public void setResponsavelCadastro(Boolean responsavelCadastro) {
		this.responsavelCadastro = responsavelCadastro;
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