/*
 * SICOOB
 * 
 * PessoaVO.java(br.com.sicoob.capes.api.negocio.vo.PessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;


/**
 * @author Lucas.Borges
 */
public class PessoaVO extends PessoaBasicaVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 9074419517036614938L;

	/** O atributo cod tipo pessoa. */
	private Short codTipoPessoa;
	
	// PESSOA COMPARTILHAMENTO
	/** O atributo codigo compartilhamento cadastro. */
	private Short codigoCompartilhamentoCadastro;
	
	/** O atributo nome completo. */
	private String nomeCompleto;

	/** O atributo nome apelido. */
	private String nomeApelido;
	
	/** O atributo descricao observacao pessoa. */
	private String descricaoObservacaoPessoa;
	
	/** O atributo codigo atividade economica. */
	private Short codigoAtividadeEconomica;
	
	/** O atributo descricao atividade economica. */
	private String descricaoAtividadeEconomica;
	
	/** O atributo codigo cnae fiscal. */
	private String codigoCnaeFiscal;
	
	/** O atributo codigo cnae fiscal. */
	private String descricaoCnaeFiscal;
	
	/** O atributo data inclusao sistema. */
	private Date dataInclusaoSistema;
	
	/** O atributo autoriza consulta bacen. */
	private Boolean autorizaConsultaBacen;
	
	/** O atributo data inclusao sfn. */
	private Date dataInclusaoSFN;

	// Transição
	/** O atributo id pessoa legado. */
	private Integer idPessoaLegado;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	//
	/** O atributo data renovacao cadastral. */
	private Date dataRenovacaoCadastral;
	
	/** O atributo id usuario renovacao. */
	private String idUsuarioRenovacao;
	
	/** O atributo id instituicao renovacao. */
	private Short idInstituicaoRenovacao;
	
	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;
	
	/**
	 * Cria uma nova instância de pessoa vo.
	 */
	public PessoaVO() {

	}
	
	/**
	 * Cria uma nova instância de pessoa vo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @param codigoCompartilhamentoCadastro
	 *            the codigo compartilhamento cadastro
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param nomeCompleto
	 *            the nome completo
	 * @param nomeApelido
	 *            the nome apelido
	 * @param descricaoObservacaoPessoa
	 *            the descricao observacao pessoa
	 * @param codigoAtividadeEconomica
	 *            the codigo atividade economica
	 * @param codigoCnaeFiscal
	 *            the codigo cnae fiscal
	 * @param dataInclusaoSistema
	 *            the data inclusao sistema
	 * @param autorizaConsultaBacen
	 *            the autoriza consulta bacen
	 */
	public PessoaVO(Integer idPessoa, String cpfCnpj, Short codTipoPessoa,
			Short codigoCompartilhamentoCadastro, String nomePessoa,
			String nomeCompleto, String nomeApelido,
			String descricaoObservacaoPessoa, Short codigoAtividadeEconomica,
			String codigoCnaeFiscal, Date dataInclusaoSistema,
			Boolean autorizaConsultaBacen) {
		super(idPessoa, cpfCnpj, nomePessoa);
		this.codTipoPessoa = codTipoPessoa;
		this.codigoCompartilhamentoCadastro = codigoCompartilhamentoCadastro;
		this.nomeCompleto = nomeCompleto;
		this.nomeApelido = nomeApelido;
		this.descricaoObservacaoPessoa = descricaoObservacaoPessoa;
		this.codigoAtividadeEconomica = codigoAtividadeEconomica;
		this.codigoCnaeFiscal = codigoCnaeFiscal;
		this.dataInclusaoSistema = dataInclusaoSistema;
		this.autorizaConsultaBacen = autorizaConsultaBacen;		
	}

	/* Contrutor utilizado pelo ClienteVO */
	/**
	 * Cria uma nova instância de pessoa vo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @param codigoCompartilhamentoCadastro
	 *            the codigo compartilhamento cadastro
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param nomeCompleto
	 *            the nome completo
	 * @param nomeApelido
	 *            the nome apelido
	 * @param descricaoObservacaoPessoa
	 *            the descricao observacao pessoa
	 * @param codigoAtividadeEconomica
	 *            the codigo atividade economica
	 * @param codigoCnaeFiscal
	 *            the codigo cnae fiscal
	 * @param dataInclusaoSistema
	 *            the data inclusao sistema
	 * @param autorizaConsultaBacen
	 *            the autoriza consulta bacen
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idInstituicao
	 *            the id instituicao
	 * @param dataInclusaoSFN
	 *            the data inclusao sfn
	 */
	public PessoaVO(Integer idPessoa, String cpfCnpj, Short codTipoPessoa,
			Short codigoCompartilhamentoCadastro, String nomePessoa,
			String nomeCompleto, String nomeApelido,
			String descricaoObservacaoPessoa, Short codigoAtividadeEconomica,
			String codigoCnaeFiscal, Date dataInclusaoSistema,
			Boolean autorizaConsultaBacen, Integer idPessoaLegado, Integer idInstituicao,
			Date dataInclusaoSFN) {
		this(idPessoa, cpfCnpj, codTipoPessoa, codigoCompartilhamentoCadastro, nomePessoa, nomeCompleto, 
				nomeApelido, descricaoObservacaoPessoa, codigoAtividadeEconomica, codigoCnaeFiscal, 
				dataInclusaoSistema, autorizaConsultaBacen);
		this.dataInclusaoSFN = dataInclusaoSFN;
		this.idPessoaLegado = idPessoaLegado;
		this.idInstituicao = idInstituicao;
	}
	
	/**
	 * Instancia um novo PessoaVO.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param codTipoPessoa o valor de cod tipo pessoa
	 * @param codigoCompartilhamentoCadastro o valor de codigo compartilhamento cadastro
	 * @param nomePessoa o valor de nome pessoa
	 * @param nomeCompleto o valor de nome completo
	 * @param nomeApelido o valor de nome apelido
	 * @param descricaoObservacaoPessoa o valor de descricao observacao pessoa
	 * @param codigoAtividadeEconomica o valor de codigo atividade economica
	 * @param codigoCnaeFiscal o valor de codigo cnae fiscal
	 * @param dataInclusaoSistema o valor de data inclusao sistema
	 * @param autorizaConsultaBacen o valor de autoriza consulta bacen
	 * @param idPessoaLegado o valor de id pessoa legado
	 * @param idInstituicao o valor de id instituicao
	 * @param dataInclusaoSFN o valor de data inclusao sfn
	 * @param idUnidadeInst o valor de id unidade inst
	 */
	public PessoaVO(Integer idPessoa, String cpfCnpj, Short codTipoPessoa,
			Short codigoCompartilhamentoCadastro, String nomePessoa,
			String nomeCompleto, String nomeApelido,
			String descricaoObservacaoPessoa, Short codigoAtividadeEconomica,
			String codigoCnaeFiscal, Date dataInclusaoSistema,
			Boolean autorizaConsultaBacen, Integer idPessoaLegado, Integer idInstituicao,
			Date dataInclusaoSFN, Integer idUnidadeInst) {
		this(idPessoa, cpfCnpj, codTipoPessoa, codigoCompartilhamentoCadastro, nomePessoa, nomeCompleto, 
				nomeApelido, descricaoObservacaoPessoa, codigoAtividadeEconomica, codigoCnaeFiscal, 
				dataInclusaoSistema, autorizaConsultaBacen, idPessoaLegado, idInstituicao, dataInclusaoSFN);
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Instancia um novo PessoaVO.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param codTipoPessoa o valor de cod tipo pessoa
	 * @param codigoCompartilhamentoCadastro o valor de codigo compartilhamento cadastro
	 * @param nomePessoa o valor de nome pessoa
	 * @param nomeCompleto o valor de nome completo
	 * @param nomeApelido o valor de nome apelido
	 * @param descricaoObservacaoPessoa o valor de descricao observacao pessoa
	 * @param codigoAtividadeEconomica o valor de codigo atividade economica
	 * @param codigoCnaeFiscal o valor de codigo cnae fiscal
	 * @param dataInclusaoSistema o valor de data inclusao sistema
	 * @param autorizaConsultaBacen o valor de autoriza consulta bacen
	 * @param idPessoaLegado o valor de id pessoa legado
	 * @param idInstituicao o valor de id instituicao
	 * @param idUnidadeInst o valor de id unidade inst
	 * @param dataInclusaoSFN o valor de data inclusao sfn
	 * @param dataRenovacaoCadastral o valor de data renovacao cadastral
	 * @param idUsuarioRenovacao o valor de id usuario renovacao
	 * @param idInstituicaoRenovacao o valor de id instituicao renovacao
	 */
	public PessoaVO(Integer idPessoa, String cpfCnpj, Short codTipoPessoa, Short codigoCompartilhamentoCadastro, String nomePessoa, String nomeCompleto, 
			String nomeApelido, String descricaoObservacaoPessoa, Short codigoAtividadeEconomica, String codigoCnaeFiscal, Date dataInclusaoSistema, 
			Boolean autorizaConsultaBacen, Integer idPessoaLegado, Integer idInstituicao, Integer idUnidadeInst, Date dataInclusaoSFN, 
			Date dataRenovacaoCadastral, String idUsuarioRenovacao, Short idInstituicaoRenovacao) {
		this(idPessoa, cpfCnpj, codTipoPessoa, codigoCompartilhamentoCadastro, nomePessoa, nomeCompleto, nomeApelido, descricaoObservacaoPessoa, 
				codigoAtividadeEconomica, codigoCnaeFiscal, dataInclusaoSistema, autorizaConsultaBacen, idPessoaLegado, idInstituicao, dataInclusaoSFN, idUnidadeInst);
		this.dataRenovacaoCadastral = dataRenovacaoCadastral;
		this.idUsuarioRenovacao = idUsuarioRenovacao;
		this.idInstituicaoRenovacao = idInstituicaoRenovacao;
	}
	
	public PessoaVO(Integer idPessoa, String cpfCnpj, Short codTipoPessoa, Short codigoCompartilhamentoCadastro, String nomePessoa, String nomeCompleto, 
			String nomeApelido, String descricaoObservacaoPessoa, Short codigoAtividadeEconomica, String codigoCnaeFiscal, Date dataInclusaoSistema, 
			Boolean autorizaConsultaBacen, Integer idPessoaLegado, Integer idInstituicao, Integer idUnidadeInst, Date dataInclusaoSFN, 
			Date dataRenovacaoCadastral, String idUsuarioRenovacao, Short idInstituicaoRenovacao, String descricaoCNAEFiscal, String descricaoAtividadeEconomica) {
		this(idPessoa, cpfCnpj, codTipoPessoa, codigoCompartilhamentoCadastro, nomePessoa, nomeCompleto, 
				nomeApelido, descricaoObservacaoPessoa, codigoAtividadeEconomica, codigoCnaeFiscal, dataInclusaoSistema, 
				autorizaConsultaBacen, idPessoaLegado, idInstituicao, idUnidadeInst, dataInclusaoSFN, 
				dataRenovacaoCadastral, idUsuarioRenovacao, idInstituicaoRenovacao);
		this.descricaoAtividadeEconomica = descricaoAtividadeEconomica;
		this.descricaoCnaeFiscal = descricaoCNAEFiscal; 
	}

	/**
	 * Recupera cod tipo pessoa.
	 * 
	 * @return cod tipo pessoa
	 */
	public Short getCodTipoPessoa() {
		return codTipoPessoa;
	}

	/**
	 * Preenche cod tipo pessoa.
	 * 
	 * @param codTipoPessoa
	 *            o novo cod tipo pessoa
	 */
	public void setCodTipoPessoa(Short codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}

	/**
	 * Recupera codigo compartilhamento cadastro.
	 * 
	 * @return codigo compartilhamento cadastro
	 */
	public Short getCodigoCompartilhamentoCadastro() {
		return codigoCompartilhamentoCadastro;
	}

	/**
	 * Preenche codigo compartilhamento cadastro.
	 * 
	 * @param codigoCompartilhamentoCadastro
	 *            o novo codigo compartilhamento cadastro
	 */
	public void setCodigoCompartilhamentoCadastro(
			Short codigoCompartilhamentoCadastro) {
		this.codigoCompartilhamentoCadastro = codigoCompartilhamentoCadastro;
	}

	/**
	 * Recupera nome completo.
	 * 
	 * @return nome completo
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * Preenche nome completo.
	 * 
	 * @param nomeCompleto
	 *            o novo nome completo
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * Recupera nome apelido.
	 * 
	 * @return nome apelido
	 */
	public String getNomeApelido() {
		return nomeApelido;
	}

	/**
	 * Preenche nome apelido.
	 * 
	 * @param nomeApelido
	 *            o novo nome apelido
	 */
	public void setNomeApelido(String nomeApelido) {
		this.nomeApelido = nomeApelido;
	}

	/**
	 * Recupera descricao observacao pessoa.
	 * 
	 * @return descricao observacao pessoa
	 */
	public String getDescricaoObservacaoPessoa() {
		return descricaoObservacaoPessoa;
	}

	/**
	 * Preenche descricao observacao pessoa.
	 * 
	 * @param descricaoObservacaoPessoa
	 *            o novo descricao observacao pessoa
	 */
	public void setDescricaoObservacaoPessoa(String descricaoObservacaoPessoa) {
		this.descricaoObservacaoPessoa = descricaoObservacaoPessoa;
	}

	/**
	 * Recupera codigo atividade economica.
	 * 
	 * @return codigo atividade economica
	 */
	public Short getCodigoAtividadeEconomica() {
		return codigoAtividadeEconomica;
	}

	/**
	 * Preenche codigo atividade economica.
	 * 
	 * @param codigoAtividadeEconomica
	 *            o novo codigo atividade economica
	 */
	public void setCodigoAtividadeEconomica(Short codigoAtividadeEconomica) {
		this.codigoAtividadeEconomica = codigoAtividadeEconomica;
	}
	
	public String getDescricaoAtividadeEconomica() {
		return descricaoAtividadeEconomica;
	}

	public void setDescricaoAtividadeEconomica(String descricaoAtividadeEconomica) {
		this.descricaoAtividadeEconomica = descricaoAtividadeEconomica;
	}

	/**
	 * Recupera codigo cnae fiscal.
	 * 
	 * @return codigo cnae fiscal
	 */
	public String getCodigoCnaeFiscal() {
		return codigoCnaeFiscal;
	}

	/**
	 * Preenche codigo cnae fiscal.
	 * 
	 * @param codigoCnaeFiscal
	 *            o novo codigo cnae fiscal
	 */
	public void setCodigoCnaeFiscal(String codigoCnaeFiscal) {
		this.codigoCnaeFiscal = codigoCnaeFiscal;
	}

	/**
	 * Recupera data inclusao sistema.
	 * 
	 * @return data inclusao sistema
	 */
	public Date getDataInclusaoSistema() {
		return dataInclusaoSistema;
	}

	/**
	 * Preenche data inclusao sistema.
	 * 
	 * @param dataInclusaoSistema
	 *            o novo data inclusao sistema
	 */
	public void setDataInclusaoSistema(Date dataInclusaoSistema) {
		this.dataInclusaoSistema = dataInclusaoSistema;
	}

	/**
	 * Recupera autoriza consulta bacen.
	 * 
	 * @return autoriza consulta bacen
	 */
	public Boolean getAutorizaConsultaBacen() {
		return autorizaConsultaBacen;
	}

	/**
	 * Preenche autoriza consulta bacen.
	 * 
	 * @param autorizaConsultaBacen
	 *            o novo autoriza consulta bacen
	 */
	public void setAutorizaConsultaBacen(Boolean autorizaConsultaBacen) {
		this.autorizaConsultaBacen = autorizaConsultaBacen;
	}
	
	/**
	 * Recupera data inclusao sfn.
	 * 
	 * @return data inclusao sfn
	 */
	public Date getDataInclusaoSFN() {
		return dataInclusaoSFN;
	}

	/**
	 * Preenche data inclusao sfn.
	 * 
	 * @param dataInclusaoSFN
	 *            o novo data inclusao sfn
	 */
	public void setDataInclusaoSFN(Date dataInclusaoSFN) {
		this.dataInclusaoSFN = dataInclusaoSFN;
	}

	/**
	 * @return the idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}

	/**
	 * @param idPessoaLegado the idPessoaLegado to set
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera data renovacao cadastral.
	 * 
	 * @return data renovacao cadastral
	 */
	public Date getDataRenovacaoCadastral() {
		return dataRenovacaoCadastral;
	}

	/**
	 * Preenche data renovacao cadastral.
	 * 
	 * @param dataRenovacaoCadastral
	 *            o novo data renovacao cadastral
	 */
	public void setDataRenovacaoCadastral(Date dataRenovacaoCadastral) {
		this.dataRenovacaoCadastral = dataRenovacaoCadastral;
	}

	/**
	 * Recupera id usuario renovacao.
	 * 
	 * @return id usuario renovacao
	 */
	public String getIdUsuarioRenovacao() {
		return idUsuarioRenovacao;
	}

	/**
	 * Preenche id usuario renovacao.
	 * 
	 * @param idUsuarioRenovacao
	 *            o novo id usuario renovacao
	 */
	public void setIdUsuarioRenovacao(String idUsuarioRenovacao) {
		this.idUsuarioRenovacao = idUsuarioRenovacao;
	}

	/**
	 * Recupera id instituicao renovacao.
	 * 
	 * @return id instituicao renovacao
	 */
	public Short getIdInstituicaoRenovacao() {
		return idInstituicaoRenovacao;
	}

	/**
	 * Preenche id instituicao renovacao.
	 * 
	 * @param idInstituicaoRenovacao
	 *            o novo id instituicao renovacao
	 */
	public void setIdInstituicaoRenovacao(Short idInstituicaoRenovacao) {
		this.idInstituicaoRenovacao = idInstituicaoRenovacao;
	}

	/**
	 * Recupera o valor de idUnidadeInst.
	 *
	 * @return o valor de idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Define o valor de idUnidadeInst.
	 *
	 * @param idUnidadeInst o novo valor de idUnidadeInst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Recupera o valor de descricaoCnaeFiscal.
	 *
	 * @return o valor de descricaoCnaeFiscal
	 */
	public String getDescricaoCnaeFiscal() {
		return descricaoCnaeFiscal;
	}

	/**
	 * Define o valor de descricaoCnaeFiscal.
	 *
	 * @param descricaoCnaeFiscal o novo valor de descricaoCnaeFiscal
	 */
	public void setDescricaoCnaeFiscal(String descricaoCnaeFiscal) {
		this.descricaoCnaeFiscal = descricaoCnaeFiscal;
	}

}
