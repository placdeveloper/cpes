package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.util.Date;

import br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO;

/**
 * A Classe PessoaDTO.
 * 
 * @author bruno.carneiro
 */
public abstract class PessoaDTO extends RegistroInclusaoDTO<Long> {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 7507913597009721070L;
	
	/** O atributo idPessoaCompartilhamento. */
	private Long idPessoaCompartilhamento;

	/** O atributo cpfCnpj. */
	private String cpfCnpj;

	// PESSOA COMPARTILHAMENTO
	/** O atributo nomePessoa. */
	private String nomePessoa;

	/** O atributo nomeApelido. */
	private String nomeApelido;

	/** O atributo nomeCompleto. */
	private String nomeCompleto;

	/** O atributo descricao. */
	private String descricao;

	/** O atributo codigoCnae. */
	private String codigoCnae;

	/** O atributo codigoAtividadeEconomica. */
	private Short codigoAtividadeEconomica;

	/** O atributo dataInclusaoSistema. */
	private Date dataInclusaoSistema;

	/** O atributo dataInclusaoSFN. */
	private Date dataInclusaoSFN;

	/** O atributo autorizaConsultaBacen. */
	private Boolean autorizaConsultaBacen = Boolean.TRUE;

	/** O atributo dataRenovacaoCadastral. */
	private Date dataRenovacaoCadastral;
	
	/** O atributo codigoPerfilCadastro. */
	private Short codigoPerfilCadastro;

	/** O atributo dadosReceita. */
	private DadosReceitaFederalVO dadosReceita;
	
	/**
	 * Atributo que indica a origem da pessoa para o compartilhamento de
	 * cadastro
	 **/
	private Integer idInstituicaoOrigem;
	
	/**
	 * 
	 * @return
	 */
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	/**
	 * 
	 * @param idPessoaCompartilhamento
	 */
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
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
	 * @param cpfCnpj
	 *            o novo valor de cpfCnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o valor de nomePessoa.
	 * 
	 * @return o valor de nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Define o valor de nomePessoa.
	 * 
	 * @param nomePessoa
	 *            o novo valor de nomePessoa
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * Recupera o valor de nomeApelido.
	 * 
	 * @return o valor de nomeApelido
	 */
	public String getNomeApelido() {
		return nomeApelido;
	}

	/**
	 * Define o valor de nomeApelido.
	 * 
	 * @param nomeApelido
	 *            o novo valor de nomeApelido
	 */
	public void setNomeApelido(String nomeApelido) {
		this.nomeApelido = nomeApelido;
	}

	/**
	 * Recupera o valor de nomeCompleto.
	 * 
	 * @return o valor de nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * Define o valor de nomeCompleto.
	 * 
	 * @param nomeCompleto
	 *            o novo valor de nomeCompleto
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * Recupera o valor de descricao.
	 * 
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Define o valor de descricao.
	 * 
	 * @param descricao
	 *            o novo valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera o valor de codigoCnae.
	 * 
	 * @return o valor de codigoCnae
	 */
	public String getCodigoCnae() {
		return codigoCnae;
	}

	/**
	 * Define o valor de codigoCnae.
	 * 
	 * @param codigoCnae
	 *            o novo valor de codigoCnae
	 */
	public void setCodigoCnae(String codigoCnae) {
		this.codigoCnae = codigoCnae;
	}

	/**
	 * Recupera o valor de codigoAtividadeEconomica.
	 * 
	 * @return o valor de codigoAtividadeEconomica
	 */
	public Short getCodigoAtividadeEconomica() {
		return codigoAtividadeEconomica;
	}

	/**
	 * Define o valor de codigoAtividadeEconomica.
	 * 
	 * @param codigoAtividadeEconomica
	 *            o novo valor de codigoAtividadeEconomica
	 */
	public void setCodigoAtividadeEconomica(Short codigoAtividadeEconomica) {
		this.codigoAtividadeEconomica = codigoAtividadeEconomica;
	}

	/**
	 * Recupera o valor de dataInclusaoSistema.
	 * 
	 * @return o valor de dataInclusaoSistema
	 */
	public Date getDataInclusaoSistema() {
		return dataInclusaoSistema;
	}

	/**
	 * Define o valor de dataInclusaoSistema.
	 * 
	 * @param dataInclusaoSistema
	 *            o novo valor de dataInclusaoSistema
	 */
	public void setDataInclusaoSistema(Date dataInclusaoSistema) {
		this.dataInclusaoSistema = dataInclusaoSistema;
	}

	/**
	 * Recupera o valor de dataInclusaoSFN.
	 * 
	 * @return o valor de dataInclusaoSFN
	 */
	public Date getDataInclusaoSFN() {
		return dataInclusaoSFN;
	}

	/**
	 * Define o valor de dataInclusaoSFN.
	 * 
	 * @param dataInclusaoSFN
	 *            o novo valor de dataInclusaoSFN
	 */
	public void setDataInclusaoSFN(Date dataInclusaoSFN) {
		this.dataInclusaoSFN = dataInclusaoSFN;
	}

	/**
	 * Recupera o valor de autorizaConsultaBacen.
	 * 
	 * @return o valor de autorizaConsultaBacen
	 */
	public Boolean getAutorizaConsultaBacen() {
		return autorizaConsultaBacen;
	}

	/**
	 * Define o valor de autorizaConsultaBacen.
	 * 
	 * @param autorizaConsultaBacen
	 *            o novo valor de autorizaConsultaBacen
	 */
	public void setAutorizaConsultaBacen(Boolean autorizaConsultaBacen) {
		this.autorizaConsultaBacen = autorizaConsultaBacen;
	}

	/**
	 * Recupera o valor de dataRenovacaoCadastral.
	 * 
	 * @return o valor de dataRenovacaoCadastral
	 */
	public Date getDataRenovacaoCadastral() {
		return dataRenovacaoCadastral;
	}

	/**
	 * Define o valor de dataRenovacaoCadastral.
	 * 
	 * @param dataRenovacaoCadastral
	 *            o novo valor de dataRenovacaoCadastral
	 */
	public void setDataRenovacaoCadastral(Date dataRenovacaoCadastral) {
		this.dataRenovacaoCadastral = dataRenovacaoCadastral;
	}
	
	/**
	 * Recupera o valor de codigoPerfilCadastro.
	 * 
	 * @return o valor de codigoPerfilCadastro
	 */
	public Short getCodigoPerfilCadastro() {
		return codigoPerfilCadastro;
	}

	/**
	 * Define o valor de codigoPerfilCadastro.
	 * 
	 * @param codigoPerfilCadastro
	 *            o novo valor de codigoPerfilCadastro
	 */
	public void setCodigoPerfilCadastro(Short codigoPerfilCadastro) {
		this.codigoPerfilCadastro = codigoPerfilCadastro;
	}

	/**
	 * Recupera o valor de dadosReceita.
	 * 
	 * @return o valor de dadosReceita
	 */
	public DadosReceitaFederalVO getDadosReceita() {
		return dadosReceita;
	}

	/**
	 * Define o valor de dadosReceita.
	 * 
	 * @param dadosReceita
	 *            o novo valor de dadosReceita
	 */
	public void setDadosReceita(DadosReceitaFederalVO dadosReceita) {
		this.dadosReceita = dadosReceita;
	}
	
	public Integer getIdInstituicaoOrigem() {
		return idInstituicaoOrigem;
	}

	public void setIdInstituicaoOrigem(Integer idInstituicaoOrigem) {
		this.idInstituicaoOrigem = idInstituicaoOrigem;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdPessoaCompartilhamento();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdPessoaCompartilhamento(id);
	}
	
	/**
	 * Recupera o valor de codigoTipoPessoa
	 * 
	 * @return {@code Short} o código do tipo de pessoa.
	 */
	public abstract Short getCodigoTipoPessoa();

}