package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class RegistroTelefoneVO extends RegistroDadosArquivoBaseVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6473219810394427011L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "04";

	/** O atributo tipoTelefone. */
	@CampoArquivo(inicio = 21, tamanho = 5, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short tipoTelefone;

	/** O atributo ddd. */
	@CampoArquivo(inicio = 26, tamanho = 3, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private String ddd;

	/** O atributo numero. */
	@CampoArquivo(inicio = 29, tamanho = 12, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private String numero;

	/** O atributo ramal. */
	@CampoArquivo(inicio = 41, tamanho = 4, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private String ramal;

	/** O atributo filler. */
	@CampoArquivo(inicio = 45, tamanho = 944)
	private String filler;

	/**
	 * Construtor
	 */
	public RegistroTelefoneVO() {
		super(CODIGO_REGISTRO);
	}

	/**
	 * Construtor
	 * 
	 * @param codigoRegistro
	 * @param tipoTelefone
	 * @param ddd
	 * @param numero
	 * @param ramal
	 */
	public RegistroTelefoneVO(Long idPessoaCompartilhamento, Short tipoTelefone, String ddd, String numero, String ramal) {
		super(CODIGO_REGISTRO, idPessoaCompartilhamento);
		this.tipoTelefone = tipoTelefone;
		this.ddd = ddd;
		this.numero = numero;
		this.ramal = ramal;
	}

	/**
	 * @return the tipoTelefone
	 */
	public Short getTipoTelefone() {
		return tipoTelefone;
	}

	/**
	 * @param tipoTelefone
	 *            the tipoTelefone to set
	 */
	public void setTipoTelefone(Short tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	/**
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * @param ddd
	 *            the ddd to set
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
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
	 * @return the ramal
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * @param ramal
	 *            the ramal to set
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
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