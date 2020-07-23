package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public abstract class RegistroArquivoExportacaoVO extends BancoobVo implements RegistroArquivo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4505718970392472206L;

	/** O atributo numeroLinha. */
	private int numeroLinha;

	/** O atributo codigoRegistro. */
	@CampoArquivo(inicio = 0, tamanho = 2)
	private String codigoRegistro;

	/** O atributo sequencialRegistro. */
	@CampoArquivo(inicio = 989, tamanho = 11, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Long sequencialRegistro;

	/**
	 * @param codigoRegistro
	 */
	protected RegistroArquivoExportacaoVO(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getNumeroLinha() {
		return numeroLinha;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setNumeroLinha(int numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	/**
	 * @return the codigoRegistro
	 */
	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	/**
	 * @return the sequencialRegistro
	 */
	public Long getSequencialRegistro() {
		return sequencialRegistro;
	}

	/**
	 * @param sequencialRegistro
	 *            the sequencialRegistro to set
	 */
	public void setSequencialRegistro(Long sequencialRegistro) {
		this.sequencialRegistro = sequencialRegistro;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}