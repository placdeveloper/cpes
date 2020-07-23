/*
 * SICOOB
 * 
 * PessoaPlataformaVO.java(br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * A Classe PessoaPlataformaVO.
 *
 * @author Erico.Junior
 */
public class PessoaPlataformaVO implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = -5543023506564020177L;

	/** O atributo id pessoa. */
	private Integer idPessoa;

	/** O atributo cpf cnpj. */
	private String cpfCnpj;

	/** O atributo cod tipo pessoa. */
	private Short codTipoPessoa;

	/** O atributo desc tipo pessoa. */
	private String descTipoPessoa;

	/* Compartilhamento */
	/** O atributo id compartilhamento. */
	private Long idCompartilhamento;

	/** O atributo cod compartilhamento cadastro. */
	private Short codCompartilhamentoCadastro;

	/** O atributo nome pessoa. */
	private String nomePessoa;

	/** O atributo nome apelido. */
	private String nomeApelido;

	/** O atributo nome completo. */
	private String nomeCompleto;

	/** O atributo data inclusao sfn. */
	private Date dataInclusaoSFN;

	/** O atributo autoriza consulta bacen. */
	private Boolean autorizaConsultaBacen;

	/** O atributo utiliza ged gft. */
	private Boolean utilizaGedGft;

	/* Transição */
	/** O atributo id instituicao. */
	private Integer idInstituicao;

	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;

	/** O atributo id pessoa legado. */
	private Integer idPessoaLegado;

	/* PRODUTOS BANCOOB */
	/** O atributo possui relacionamento bancoob. */
	private Boolean possuiRelacionamentoBancoob;// atributo utilizado apenas para filtro

	/* PessoaInstituicao */
	/** O atributo is cliente. */
	private Boolean isCliente;// atributo utilizado apenas para filtro

	/** O atributo id pessoa instituicao. */
	private Integer idPessoaInstituicao;

	/** O atributo codigoPerfilCadastro. */
	private Short codigoPerfilCadastro;

	/**
	 * Construtor sem parâmetros.
	 */
	public PessoaPlataformaVO() {
		super();
	}

	/**
	 * Cria uma nova instância de pessoa plataforma vo.
	 *
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idPessoa
	 *            the id pessoa
	 * @param nomeApelido
	 *            the nome apelido
	 * @param nomeCompleto
	 *            the nome completo
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param idCompartilhamento
	 *            the id compartilhamento
	 */

	public PessoaPlataformaVO(String cpfCnpj, Integer idInstituicao, Integer idPessoa, String nomeApelido, String nomeCompleto, String nomePessoa, Long idCompartilhamento) {

		this.cpfCnpj = cpfCnpj;
		this.idInstituicao = idInstituicao;
		this.idPessoa = idPessoa;
		this.nomeApelido = nomeApelido;
		this.nomeCompleto = nomeCompleto;
		this.nomePessoa = nomePessoa;
		this.idCompartilhamento = idCompartilhamento;
	}

	/**
	 * Cria uma nova instância de pessoa plataforma vo.
	 * 
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param descTipoPessoa
	 *            the desc tipo pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idPessoa
	 *            the id pessoa
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idUnidadeInst
	 *            the id unidade inst
	 * @param nomeApelido
	 *            the nome apelido
	 * @param nomeCompleto
	 *            the nome completo
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param idCompartilhamento
	 *            the id compartilhamento
	 * @param idPessoaInstituicao
	 *            the id pessoa instituicao
	 */
	public PessoaPlataformaVO(Short codTipoPessoa, String cpfCnpj, String descTipoPessoa, Integer idInstituicao, Integer idPessoa, Integer idPessoaLegado, Integer idUnidadeInst, String nomeApelido, String nomeCompleto, String nomePessoa,
			Long idCompartilhamento, Integer idPessoaInstituicao) {

		this.codTipoPessoa = codTipoPessoa;
		this.cpfCnpj = cpfCnpj;
		this.descTipoPessoa = descTipoPessoa;
		this.idInstituicao = idInstituicao;
		this.idPessoa = idPessoa;
		this.idPessoaLegado = idPessoaLegado;
		this.idUnidadeInst = idUnidadeInst;
		this.nomeApelido = nomeApelido;
		this.nomeCompleto = nomeCompleto;
		this.nomePessoa = nomePessoa;
		this.idCompartilhamento = idCompartilhamento;
		this.idPessoaInstituicao = idPessoaInstituicao;
	}

	/**
	 * Cria uma nova instância de pessoa plataforma vo.
	 * 
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param descTipoPessoa
	 *            the desc tipo pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idPessoa
	 *            the id pessoa
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idUnidadeInst
	 *            the id unidade inst
	 * @param nomeApelido
	 *            the nome apelido
	 * @param nomeCompleto
	 *            the nome completo
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param idCompartilhamento
	 *            the id compartilhamento
	 * @param idPessoaInstituicao
	 *            the id pessoa instituicao
	 * @param dataInclusaoSFN
	 *            the data inclusao sfn
	 * @param autorizaConsultaBacen
	 *            the autoriza consulta bacen
	 */
	public PessoaPlataformaVO(Short codTipoPessoa, String cpfCnpj, String descTipoPessoa, Integer idInstituicao, Integer idPessoa, Integer idPessoaLegado, Integer idUnidadeInst, String nomeApelido, String nomeCompleto, String nomePessoa,
			Long idCompartilhamento, Integer idPessoaInstituicao, Date dataInclusaoSFN, Boolean autorizaConsultaBacen) {

		this(codTipoPessoa, cpfCnpj, descTipoPessoa, idInstituicao, idPessoa, idPessoaLegado, idUnidadeInst, nomeApelido, nomeCompleto, nomePessoa, idCompartilhamento, idPessoaInstituicao);

		this.dataInclusaoSFN = dataInclusaoSFN;
		this.autorizaConsultaBacen = autorizaConsultaBacen;
	}

	/**
	 * Cria uma nova instância de pessoa plataforma vo.
	 *
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param descTipoPessoa
	 *            the desc tipo pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idPessoa
	 *            the id pessoa
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idUnidadeInst
	 *            the id unidade inst
	 * @param nomeApelido
	 *            the nome apelido
	 * @param nomeCompleto
	 *            the nome completo
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param idCompartilhamento
	 *            the id compartilhamento
	 * @param idPessoaInstituicao
	 *            the id pessoa instituicao
	 * @param dataInclusaoSFN
	 *            the data inclusao sfn
	 * @param autorizaConsultaBacen
	 *            the autoriza consulta bacen
	 * @param codCompartilhamentoCadastro
	 *            the cod compartilhamento cadastro
	 * @param utilizaGedGft
	 *            the utiliza ged gft
	 * @param codigoPerfilCadastro
	 *            o valor de codigo perfil cadastro
	 */
	public PessoaPlataformaVO(Short codTipoPessoa, String cpfCnpj, String descTipoPessoa, Integer idInstituicao, Integer idPessoa, Integer idPessoaLegado, Integer idUnidadeInst, String nomeApelido, String nomeCompleto, String nomePessoa,
			Long idCompartilhamento, Integer idPessoaInstituicao, Date dataInclusaoSFN, Boolean autorizaConsultaBacen, Short codCompartilhamentoCadastro, Boolean utilizaGedGft, Short codigoPerfilCadastro) {

		this(codTipoPessoa, cpfCnpj, descTipoPessoa, idInstituicao, idPessoa, idPessoaLegado, idUnidadeInst, nomeApelido, nomeCompleto, nomePessoa, idCompartilhamento, idPessoaInstituicao);

		this.dataInclusaoSFN = dataInclusaoSFN;
		this.autorizaConsultaBacen = autorizaConsultaBacen;
		this.codCompartilhamentoCadastro = codCompartilhamentoCadastro;
		this.utilizaGedGft = utilizaGedGft;
		this.codigoPerfilCadastro = codigoPerfilCadastro;
	}

	/**
	 * Recupera o valor de idPessoa.
	 *
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Define o valor de idPessoa.
	 *
	 * @param idPessoa
	 *            the idPessoa to set
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera o valor de nomePessoa.
	 *
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Define o valor de nomePessoa.
	 *
	 * @param nomePessoa
	 *            the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * Recupera o valor de nomeApelido.
	 *
	 * @return the nomeApelido
	 */
	public String getNomeApelido() {
		return nomeApelido;
	}

	/**
	 * Define o valor de nomeApelido.
	 *
	 * @param nomeApelido
	 *            the nomeApelido to set
	 */
	public void setNomeApelido(String nomeApelido) {
		this.nomeApelido = nomeApelido;
	}

	/**
	 * Recupera o valor de nomeCompleto.
	 *
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * Define o valor de nomeCompleto.
	 *
	 * @param nomeCompleto
	 *            the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * Recupera o valor de cpfCnpj.
	 *
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define o valor de cpfCnpj.
	 *
	 * @param cpfCnpj
	 *            the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o valor de codTipoPessoa.
	 *
	 * @return the codTipoPessoa
	 */
	public Short getCodTipoPessoa() {
		return codTipoPessoa;
	}

	/**
	 * Define o valor de codTipoPessoa.
	 *
	 * @param codTipoPessoa
	 *            the codTipoPessoa to set
	 */
	public void setCodTipoPessoa(Short codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao
	 *            the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera o valor de idUnidadeInst.
	 *
	 * @return the idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Define o valor de idUnidadeInst.
	 *
	 * @param idUnidadeInst
	 *            the idUnidadeInst to set
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Recupera o valor de idPessoaLegado.
	 *
	 * @return the idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}

	/**
	 * Define o valor de idPessoaLegado.
	 *
	 * @param idPessoaLegado
	 *            the idPessoaLegado to set
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}

	/**
	 * Recupera o valor de isCliente.
	 *
	 * @return the cliente
	 */
	public Boolean getIsCliente() {
		return isCliente;
	}

	/**
	 * Define o valor de isCliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setIsCliente(Boolean cliente) {
		this.isCliente = cliente;
	}

	/**
	 * Recupera o valor de idPessoaInstituicao.
	 *
	 * @return the idPessoaInstituicao
	 */
	public Integer getIdPessoaInstituicao() {
		return idPessoaInstituicao;
	}

	/**
	 * Define o valor de idPessoaInstituicao.
	 *
	 * @param idPessoaInstituicao
	 *            the idPessoaInstituicao to set
	 */
	public void setIdPessoaInstituicao(Integer idPessoaInstituicao) {
		this.idPessoaInstituicao = idPessoaInstituicao;
	}

	/**
	 * Recupera o valor de descTipoPessoa.
	 *
	 * @return the descTipoPessoa
	 */
	public String getDescTipoPessoa() {
		return descTipoPessoa;
	}

	/**
	 * Define o valor de descTipoPessoa.
	 *
	 * @param descTipoPessoa
	 *            the descTipoPessoa to set
	 */
	public void setDescTipoPessoa(String descTipoPessoa) {
		this.descTipoPessoa = descTipoPessoa;
	}

	/**
	 * Recupera o valor de idCompartilhamento.
	 *
	 * @return the idCompartilhamento
	 */
	public Long getIdCompartilhamento() {
		return idCompartilhamento;
	}

	/**
	 * Define o valor de idCompartilhamento.
	 *
	 * @param idCompartilhamento
	 *            the idCompartilhamento to set
	 */
	public void setIdCompartilhamento(Long idCompartilhamento) {
		this.idCompartilhamento = idCompartilhamento;
	}

	/**
	 * Recupera cod compartilhamento cadastro.
	 * 
	 * @return cod compartilhamento cadastro
	 */
	public Short getCodCompartilhamentoCadastro() {
		return codCompartilhamentoCadastro;
	}

	/**
	 * Preenche cod compartilhamento cadastro.
	 * 
	 * @param codCompartilhamentoCadastro
	 *            o novo cod compartilhamento cadastro
	 */
	public void setCodCompartilhamentoCadastro(Short codCompartilhamentoCadastro) {
		this.codCompartilhamentoCadastro = codCompartilhamentoCadastro;
	}

	/**
	 * Recupera o valor de dataInclusaoSFN.
	 *
	 * @return the dataInclusaoSFN
	 */
	public Date getDataInclusaoSFN() {
		return dataInclusaoSFN;
	}

	/**
	 * Define o valor de dataInclusaoSFN.
	 *
	 * @param dataInclusaoSFN
	 *            the dataInclusaoSFN to set
	 */
	public void setDataInclusaoSFN(Date dataInclusaoSFN) {
		this.dataInclusaoSFN = dataInclusaoSFN;
	}

	/**
	 * Recupera o valor de autorizaConsultaBacen.
	 *
	 * @return the autorizaConsultaBacen
	 */
	public Boolean getAutorizaConsultaBacen() {
		return autorizaConsultaBacen;
	}

	/**
	 * Define o valor de autorizaConsultaBacen.
	 *
	 * @param autorizaConsultaBacen
	 *            the autorizaConsultaBacen to set
	 */
	public void setAutorizaConsultaBacen(Boolean autorizaConsultaBacen) {
		this.autorizaConsultaBacen = autorizaConsultaBacen;
	}

	/**
	 * Recupera possui relacionamento bancoob.
	 * 
	 * @return possui relacionamento bancoob
	 */
	public Boolean getPossuiRelacionamentoBancoob() {
		return possuiRelacionamentoBancoob;
	}

	/**
	 * Preenche possui relacionamento bancoob.
	 * 
	 * @param possuiRelacionamentoBancoob
	 *            o novo possui relacionamento bancoob
	 */
	public void setPossuiRelacionamentoBancoob(Boolean possuiRelacionamentoBancoob) {
		this.possuiRelacionamentoBancoob = possuiRelacionamentoBancoob;
	}

	/**
	 * Recupera utiliza ged gft.
	 * 
	 * @return utiliza ged gft
	 */
	public Boolean getUtilizaGedGft() {
		return utilizaGedGft;
	}

	/**
	 * Preenche utiliza ged gft.
	 * 
	 * @param utilizaGedGft
	 *            o novo utiliza ged gft
	 */
	public void setUtilizaGedGft(Boolean utilizaGedGft) {
		this.utilizaGedGft = utilizaGedGft;
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

}