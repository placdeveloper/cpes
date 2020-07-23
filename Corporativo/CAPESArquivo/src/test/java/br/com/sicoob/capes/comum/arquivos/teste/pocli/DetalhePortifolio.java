package br.com.sicoob.capes.comum.arquivos.teste.pocli;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * A Classe DetalhePortifolio.
 */
public class DetalhePortifolio implements RegistroArquivo {

	/** O atributo numeroLinha. */
	private int numeroLinha;

	/** O atributo tipoRegistro. */
	@CampoArquivo(inicio = 0, tamanho = 1)
	private Short tipoRegistro = 1;

	/** O atributo numeroCooperativa. */
	@CampoArquivo(inicio = 1, tamanho = 4, complemento = '0')
	private Integer numeroCooperativa;

	/** O atributo idInstituicao. */
	@CampoArquivo(inicio = 5, tamanho = 6, complemento = '0', alinhamento = AlinhamentoCampo.DIREITA)
	private Integer idInstituicao;

	/** O atributo cpfCnpj. */
	@CampoArquivo(inicio = 11, tamanho = 14, complemento = ' ')
	private String cpfCnpj;

	/** O atributo codTipoPessoa. */
	@CampoArquivo(inicio = 25, tamanho = 1)
	private Short codTipoPessoa;

	/** O atributo codPortifolio. */
	@CampoArquivo(inicio = 26, tamanho = 5, complemento = '0', alinhamento = AlinhamentoCampo.DIREITA)
	private Integer codPortifolio;

	/** O atributo codTipoPortifolio. */
	@CampoArquivo(inicio = 31, tamanho = 5, complemento = '0', alinhamento = AlinhamentoCampo.DIREITA)
	private Integer codTipoPortifolio;

	/** O atributo idPortifolio. */
	@CampoArquivo(inicio = 36, tamanho = 50, complemento = '0', alinhamento = AlinhamentoCampo.DIREITA)
	private Integer idPortifolio;

	/** O atributo sequencialTitular. */
	@CampoArquivo(inicio = 86, tamanho = 3, complemento = '0', alinhamento = AlinhamentoCampo.DIREITA)
	private Integer sequencialTitular;

	/** O atributo dataInicoPortifolio. */
	@CampoArquivo(inicio = 89, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataInicoPortifolio;

	/** O atributo dataFimPortifolio. */
	@CampoArquivo(inicio = 97, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataFimPortifolio;

	/** O atributo idEndereco. */
	@CampoArquivo(inicio = 105, tamanho = 10, complemento = '0', alinhamento = AlinhamentoCampo.DIREITA)
	private Long idEndereco;

	/**
	 * Recupera o valor de tipoRegistro.
	 *
	 * @return o valor de tipoRegistro
	 */
	public Short getTipoRegistro() {
	
		return tipoRegistro;
	}

	/**
	 * Define o valor de tipoRegistro.
	 *
	 * @param tipoRegistro o novo valor de tipoRegistro
	 */
	public void setTipoRegistro(Short tipoRegistro) {
	
		this.tipoRegistro = tipoRegistro;
	}

	/**
	 * Recupera o valor de numeroCooperativa.
	 *
	 * @return o valor de numeroCooperativa
	 */
	public Integer getNumeroCooperativa() {

		return numeroCooperativa;
	}

	/**
	 * Define o valor de numeroCooperativa.
	 *
	 * @param numeroCooperativa o novo valor de numeroCooperativa
	 */
	public void setNumeroCooperativa(Integer numeroCooperativa) {

		this.numeroCooperativa = numeroCooperativa;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {

		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {

		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera o valor de cpfCnpj.
	 *
	 * @return o valor de cpfCnpj
	 */
	public String getCpfCnpj() {

		return cpfCnpj;
	}

	/**
	 * Define o valor de cpfCnpj.
	 *
	 * @param cpfCnpj o novo valor de cpfCnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {

		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o valor de codTipoPessoa.
	 *
	 * @return o valor de codTipoPessoa
	 */
	public Short getCodTipoPessoa() {

		return codTipoPessoa;
	}

	/**
	 * Define o valor de codTipoPessoa.
	 *
	 * @param codTipoPessoa o novo valor de codTipoPessoa
	 */
	public void setCodTipoPessoa(Short codTipoPessoa) {

		this.codTipoPessoa = codTipoPessoa;
	}

	/**
	 * Recupera o valor de codPortifolio.
	 *
	 * @return o valor de codPortifolio
	 */
	public Integer getCodPortifolio() {

		return codPortifolio;
	}

	/**
	 * Define o valor de codPortifolio.
	 *
	 * @param codPortifolio o novo valor de codPortifolio
	 */
	public void setCodPortifolio(Integer codPortifolio) {

		this.codPortifolio = codPortifolio;
	}

	/**
	 * Recupera o valor de codTipoPortifolio.
	 *
	 * @return o valor de codTipoPortifolio
	 */
	public Integer getCodTipoPortifolio() {

		return codTipoPortifolio;
	}

	/**
	 * Define o valor de codTipoPortifolio.
	 *
	 * @param codTipoPortifolio o novo valor de codTipoPortifolio
	 */
	public void setCodTipoPortifolio(Integer codTipoPortifolio) {

		this.codTipoPortifolio = codTipoPortifolio;
	}

	/**
	 * Recupera o valor de idPortifolio.
	 *
	 * @return o valor de idPortifolio
	 */
	public Integer getIdPortifolio() {

		return idPortifolio;
	}

	/**
	 * Define o valor de idPortifolio.
	 *
	 * @param idPortifolio o novo valor de idPortifolio
	 */
	public void setIdPortifolio(Integer idPortifolio) {

		this.idPortifolio = idPortifolio;
	}

	/**
	 * Recupera o valor de sequencialTitular.
	 *
	 * @return o valor de sequencialTitular
	 */
	public Integer getSequencialTitular() {

		return sequencialTitular;
	}

	/**
	 * Define o valor de sequencialTitular.
	 *
	 * @param sequencialTitular o novo valor de sequencialTitular
	 */
	public void setSequencialTitular(Integer sequencialTitular) {

		this.sequencialTitular = sequencialTitular;
	}

	/**
	 * Recupera o valor de dataInicoPortifolio.
	 *
	 * @return o valor de dataInicoPortifolio
	 */
	public Date getDataInicoPortifolio() {

		return dataInicoPortifolio;
	}

	/**
	 * Define o valor de dataInicoPortifolio.
	 *
	 * @param dataInicoPortifolio o novo valor de dataInicoPortifolio
	 */
	public void setDataInicoPortifolio(Date dataInicoPortifolio) {

		this.dataInicoPortifolio = dataInicoPortifolio;
	}

	/**
	 * Recupera o valor de dataFimPortifolio.
	 *
	 * @return o valor de dataFimPortifolio
	 */
	public Date getDataFimPortifolio() {

		return dataFimPortifolio;
	}

	/**
	 * Define o valor de dataFimPortifolio.
	 *
	 * @param dataFimPortifolio o novo valor de dataFimPortifolio
	 */
	public void setDataFimPortifolio(Date dataFimPortifolio) {

		this.dataFimPortifolio = dataFimPortifolio;
	}

	/**
	 * Recupera o valor de idEndereco.
	 *
	 * @return o valor de idEndereco
	 */
	public Long getIdEndereco() {

		return idEndereco;
	}

	/**
	 * Define o valor de idEndereco.
	 *
	 * @param idEndereco o novo valor de idEndereco
	 */
	public void setIdEndereco(Long idEndereco) {

		this.idEndereco = idEndereco;
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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
}
