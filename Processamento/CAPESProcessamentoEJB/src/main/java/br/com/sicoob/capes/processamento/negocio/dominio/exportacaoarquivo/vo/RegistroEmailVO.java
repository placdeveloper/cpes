package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class RegistroEmailVO extends RegistroDadosArquivoBaseVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1357915660709004999L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "05";

	/** O atributo tipoEmail. */
	@CampoArquivo(inicio = 21, tamanho = 5, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short tipoEmail;

	/** O atributo endereco. */
	@CampoArquivo(inicio = 26, tamanho = 200)
	private String endereco;

	/** O atributo filler. */
	@CampoArquivo(inicio = 226, tamanho = 763)
	private String filler;

	/**
	 * Construtor
	 */
	public RegistroEmailVO() {
		super(CODIGO_REGISTRO);
	}

	/**
	 * Constutor
	 * 
	 * @param tipoEmail
	 * @param endereco
	 */
	public RegistroEmailVO(Long idPessoaCompartilhamento, Short tipoEmail, String endereco) {
		super(CODIGO_REGISTRO, idPessoaCompartilhamento);
		this.tipoEmail = tipoEmail;
		this.endereco = endereco;
	}

	/**
	 * @return the tipoEmail
	 */
	public Short getTipoEmail() {
		return tipoEmail;
	}

	/**
	 * @param tipoEmail
	 *            the tipoEmail to set
	 */
	public void setTipoEmail(Short tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
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