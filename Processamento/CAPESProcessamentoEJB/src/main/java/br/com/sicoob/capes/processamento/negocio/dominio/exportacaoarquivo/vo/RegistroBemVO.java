package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class RegistroBemVO extends RegistroDadosArquivoBaseVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 7834115575159384077L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "07";

	/** O atributo situacao. */
	@CampoArquivo(inicio = 21, tamanho = 3, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short situacao;

	/** O atributo descricao. */
	@CampoArquivo(inicio = 24, tamanho = 200)
	private String descricao;

	/** O atributo percentual. */
	@CampoArquivo(inicio = 224, tamanho = 5, precisaoDecimal = 2, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private BigDecimal percentual;

	/** O atributo valorAtualMercado. */
	@CampoArquivo(inicio = 229, tamanho = 19, precisaoDecimal = 2, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private BigDecimal valorAtualMercado;

	/** O atributo filler. */
	@CampoArquivo(inicio = 248, tamanho = 741)
	private String filler;

	/**
	 * Construtor
	 */
	public RegistroBemVO() {
		super(CODIGO_REGISTRO);
	}

	/**
	 * Construtor
	 * 
	 * @param codigoRegistro
	 * @param situacao
	 * @param descricao
	 * @param percentual
	 * @param valorAtualMercado
	 */
	public RegistroBemVO(Long idPessoaCompartilhamento, Short situacao, String descricao, BigDecimal percentual, BigDecimal valorAtualMercado) {
		super(CODIGO_REGISTRO, idPessoaCompartilhamento);
		this.situacao = situacao;
		this.descricao = descricao;
		this.percentual = percentual;
		this.valorAtualMercado = valorAtualMercado;
	}

	/**
	 * @return the situacao
	 */
	public Short getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao
	 *            the situacao to set
	 */
	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the percentual
	 */
	public BigDecimal getPercentual() {
		return percentual;
	}

	/**
	 * @param percentual
	 *            the percentual to set
	 */
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	/**
	 * @return the valorAtualMercado
	 */
	public BigDecimal getValorAtualMercado() {
		return valorAtualMercado;
	}

	/**
	 * @param valorAtualMercado
	 *            the valorAtualMercado to set
	 */
	public void setValorAtualMercado(BigDecimal valorAtualMercado) {
		this.valorAtualMercado = valorAtualMercado;
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