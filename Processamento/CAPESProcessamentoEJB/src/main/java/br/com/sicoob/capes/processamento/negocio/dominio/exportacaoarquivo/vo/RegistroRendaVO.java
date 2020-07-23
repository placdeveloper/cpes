package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * A Classe RegistroRendaVO.
 */
public class RegistroRendaVO extends RegistroDadosArquivoBaseVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -9142171690212435074L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "06";

	/** O atributo tipoRenda. */
	@CampoArquivo(inicio = 21, tamanho = 5, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short tipoRenda;

	/** O atributo dataValidade. */
	@CampoArquivo(inicio = 26, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataValidade;

	/** O atributo descricao. */
	@CampoArquivo(inicio = 34, tamanho = 400)
	private String descricao;

	/** O atributo valorBruto. */
	@CampoArquivo(inicio = 434, tamanho = 19, precisaoDecimal = 2, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private BigDecimal valorBruto;

	/** O atributo rendaFixa. */
	@CampoArquivo(inicio = 453, tamanho = 1, descBooleanTrue = "1", descBooleanFalse = "0")
	private Boolean rendaFixa;

	/** O atributo filler. */
	@CampoArquivo(inicio = 454, tamanho = 535)
	private String filler;

	/**
	 * Construtor
	 */
	public RegistroRendaVO() {
		super(CODIGO_REGISTRO);
	}

	/**
	 * Constutor
	 * 
	 * @param codigoRegistro
	 * @param tipoRenda
	 * @param dataValidade
	 * @param descricao
	 * @param valorBruto
	 * @param rendaFixa
	 */
	public RegistroRendaVO(Long idPessoaCompartilhamento, Short tipoRenda, Date dataValidade, String descricao, BigDecimal valorBruto, Boolean rendaFixa) {
		super(CODIGO_REGISTRO, idPessoaCompartilhamento);
		this.tipoRenda = tipoRenda;
		this.dataValidade = dataValidade;
		this.descricao = descricao;
		this.valorBruto = valorBruto;
		this.rendaFixa = rendaFixa;
	}

	/**
	 * @return the tipoRenda
	 */
	public Short getTipoRenda() {
		return tipoRenda;
	}

	/**
	 * @param tipoRenda
	 *            the tipoRenda to set
	 */
	public void setTipoRenda(Short tipoRenda) {
		this.tipoRenda = tipoRenda;
	}

	/**
	 * @return the dataValidade
	 */
	public Date getDataValidade() {
		return dataValidade;
	}

	/**
	 * @param dataValidade
	 *            the dataValidade to set
	 */
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
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
	 * @return the valorBruto
	 */
	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	/**
	 * @param valorBruto
	 *            the valorBruto to set
	 */
	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	/**
	 * @return the rendaFixa
	 */
	public Boolean getRendaFixa() {
		return rendaFixa;
	}

	/**
	 * @param rendaFixa
	 *            the rendaFixa to set
	 */
	public void setRendaFixa(Boolean rendaFixa) {
		this.rendaFixa = rendaFixa;
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