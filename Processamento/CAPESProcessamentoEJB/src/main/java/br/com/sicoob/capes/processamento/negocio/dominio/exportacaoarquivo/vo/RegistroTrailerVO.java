package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class RegistroTrailerVO extends RegistroArquivoBaseVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7996647462478988697L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "99";

	/** O atributo filler. */
	@CampoArquivo(inicio = 2, tamanho = 987)
	private String filler;

	/**
	 * Construtor
	 */
	public RegistroTrailerVO() {
		super(CODIGO_REGISTRO);
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