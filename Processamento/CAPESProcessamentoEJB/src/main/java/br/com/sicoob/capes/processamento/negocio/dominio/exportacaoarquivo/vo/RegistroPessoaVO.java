package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public abstract class RegistroPessoaVO extends RegistroDadosArquivoBaseVO {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8020573918341643462L;

	/** O atributo cpfCnpj. */
	@CampoArquivo(inicio = 21, tamanho = 14, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private String cpfCnpj;

	/** O atributo nome. */
	@CampoArquivo(inicio = 35, tamanho = 75)
	private String nome;

	/** O atributo apelido. */
	@CampoArquivo(inicio = 110, tamanho = 50)
	private String apelido;

	/** O atributo nomeCompleto. */
	@CampoArquivo(inicio = 160, tamanho = 400)
	private String nomeCompleto;

	/** O atributo cnae. */
	@CampoArquivo(inicio = 560, tamanho = 8)
	private String cnae;

	/** O atributo dataInclusao. */
	@CampoArquivo(inicio = 568, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataInclusao;

	/** O atributo dataUltimaRenovacao. */
	@CampoArquivo(inicio = 576, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataUltimaRenovacao;

	/** O atributo dataInclusaoSFN. */
	@CampoArquivo(inicio = 584, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataInclusaoSFN;

	/** O atributo autorizaConsultaBacen. */
	@CampoArquivo(inicio = 592, tamanho = 1, descBooleanFalse = "0", descBooleanTrue = "1")
	private Boolean autorizaConsultaBacen;

	/**
	 * Construtor
	 * 
	 * @param codigoRegistro
	 */
	protected RegistroPessoaVO(String codigoRegistro) {
		super(codigoRegistro);
	}

	/**
	 * Constutor
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
	 */
	protected RegistroPessoaVO(String codigoRegistro, Long idPessoaCompartilhamento, String cpfCnpj, String nome, String apelido, String nomeCompleto,
			String cnae, Date dataInclusao, Date dataUltimaRenovacao, Date dataInclusaoSFN, Boolean autorizaConsultaBacen) {
		super(codigoRegistro, idPessoaCompartilhamento);
		this.cpfCnpj = cpfCnpj;
		this.nome = nome;
		this.apelido = apelido;
		this.nomeCompleto = nomeCompleto;
		this.cnae = cnae;
		this.dataInclusao = dataInclusao;
		this.dataUltimaRenovacao = dataUltimaRenovacao;
		this.dataInclusaoSFN = dataInclusaoSFN;
		this.autorizaConsultaBacen = autorizaConsultaBacen;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj
	 *            the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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
	 * @return the apelido
	 */
	public String getApelido() {
		return apelido;
	}

	/**
	 * @param apelido
	 *            the apelido to set
	 */
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	/**
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * @param nomeCompleto
	 *            the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * @return the cnae
	 */
	public String getCnae() {
		return cnae;
	}

	/**
	 * @param cnae
	 *            the cnae to set
	 */
	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	/**
	 * @return the dataInclusao
	 */
	public Date getDataInclusao() {
		return dataInclusao;
	}

	/**
	 * @param dataInclusao
	 *            the dataInclusao to set
	 */
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	/**
	 * @return the dataUltimaRenovacao
	 */
	public Date getDataUltimaRenovacao() {
		return dataUltimaRenovacao;
	}

	/**
	 * @param dataUltimaRenovacao
	 *            the dataUltimaRenovacao to set
	 */
	public void setDataUltimaRenovacao(Date dataUltimaRenovacao) {
		this.dataUltimaRenovacao = dataUltimaRenovacao;
	}

	/**
	 * @return the dataInclusaoSFN
	 */
	public Date getDataInclusaoSFN() {
		return dataInclusaoSFN;
	}

	/**
	 * @param dataInclusaoSFN
	 *            the dataInclusaoSFN to set
	 */
	public void setDataInclusaoSFN(Date dataInclusaoSFN) {
		this.dataInclusaoSFN = dataInclusaoSFN;
	}

	/**
	 * @return the autorizaConsultaBacen
	 */
	public Boolean getAutorizaConsultaBacen() {
		return autorizaConsultaBacen;
	}

	/**
	 * @param autorizaConsultaBacen
	 *            the autorizaConsultaBacen to set
	 */
	public void setAutorizaConsultaBacen(Boolean autorizaConsultaBacen) {
		this.autorizaConsultaBacen = autorizaConsultaBacen;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}