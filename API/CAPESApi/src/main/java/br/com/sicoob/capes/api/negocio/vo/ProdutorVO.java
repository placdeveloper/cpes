/*
 * SICOOB
 *
 * ProdutorVO.java(br.com.sicoob.capes.api.negocio.vo.ProdutorVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

/**
 * @author Marcelo.Onofre
 *
 */
public class ProdutorVO extends PessoaVO {

	/** Serial UID.*/
	private static final long serialVersionUID = -6079055313517674708L;

	/** O atributo pesquisar por categoria ativa. */
	private boolean pesquisarPorCategoriaAtiva = Boolean.FALSE;

	/** O atributo dataHoraInicio. */
	private Date dataHoraInicio;
	
	/** O atributo codigoInscricao. */
	private String codigoInscricao;
	
	/** O atributo codigoCategoria. */
	private Integer codigoCategoria;
	
	/** O atributo descricaoCategoria. */
	private String descricaoCategoria;

	/** Atributo utilizado apenas como filtro.*/
	private Boolean cliente;
	
	private Short codigoTipoBeneficiarioSicor;
	
	private String descricaoTipoBeneficiarioSicor;

	/**
	 * Cria uma nova instância de produtor vo.
	 */
	public ProdutorVO() {
	}

	/**
	 * Cria uma nova instância de produtor vo.
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
	public ProdutorVO(Integer idPessoa, String cpfCnpj, Short codTipoPessoa,
			Short codigoCompartilhamentoCadastro, String nomePessoa,
			String nomeCompleto, String nomeApelido,
			String descricaoObservacaoPessoa, Short codigoAtividadeEconomica,
			String codigoCnaeFiscal, Date dataInclusaoSistema, Boolean autorizaConsultaBacen,
			Integer idPessoaLegado, Integer idInstituicao, Date dataInclusaoSFN, Short codigoTipoBeneficiarioSicor, String descricaoTipoBeneficiarioSicor) {
		super(idPessoa, cpfCnpj, codTipoPessoa, codigoCompartilhamentoCadastro, nomePessoa, nomeCompleto, nomeApelido,
				descricaoObservacaoPessoa, codigoAtividadeEconomica, codigoCnaeFiscal, dataInclusaoSistema,
				autorizaConsultaBacen, idPessoaLegado, idInstituicao, dataInclusaoSFN);
		this.codigoTipoBeneficiarioSicor = codigoTipoBeneficiarioSicor;
		this.descricaoTipoBeneficiarioSicor = descricaoTipoBeneficiarioSicor;
	}
	
	/**
	 * Instancia um novo ProdutorVO.
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
	 * @param datahoraInicio o valor de datahora inicio
	 * @param codigoInscricao o valor de codigo inscricao
	 * @param codigoCategoria o valor de codigo categoria
	 * @param descricaoCategoria o valor de descricao categoria
	 */
	public ProdutorVO(Integer idPessoa, String cpfCnpj, Short codTipoPessoa,
			Short codigoCompartilhamentoCadastro, String nomePessoa,
			String nomeCompleto, String nomeApelido,
			String descricaoObservacaoPessoa, Short codigoAtividadeEconomica,
			String codigoCnaeFiscal, Date dataInclusaoSistema, Boolean autorizaConsultaBacen,
			Integer idPessoaLegado, Integer idInstituicao, Date dataInclusaoSFN,
			Date datahoraInicio, String codigoInscricao, Short codigoCategoria,
			String descricaoCategoria, Short codigoTipoBeneficiarioSicor, String descricaoTipoBeneficiarioSicor) {
		super(idPessoa, cpfCnpj, codTipoPessoa, codigoCompartilhamentoCadastro, nomePessoa, nomeCompleto, nomeApelido,
				descricaoObservacaoPessoa, codigoAtividadeEconomica, codigoCnaeFiscal, dataInclusaoSistema,
				autorizaConsultaBacen, idPessoaLegado, idInstituicao, dataInclusaoSFN);
		this.dataHoraInicio = datahoraInicio;
		this.codigoInscricao = codigoInscricao;
		this.codigoCategoria = codigoCategoria.intValue();
		this.descricaoCategoria = descricaoCategoria;
		this.codigoTipoBeneficiarioSicor = codigoTipoBeneficiarioSicor;
		this.descricaoTipoBeneficiarioSicor = descricaoTipoBeneficiarioSicor;
	}
	
	/**
	 * Verifica se é pesquisar por categoria ativa.
	 *
	 * @return true, se for pesquisar por categoria ativa
	 */
	public boolean isPesquisarPorCategoriaAtiva() {
		return pesquisarPorCategoriaAtiva;
	}

	/**
	 * Preenche pesquisar por categoria ativa.
	 *
	 * @param pesquisarPorCategoriaAtiva
	 *            o novo pesquisar por categoria ativa
	 */
	public void setPesquisarPorCategoriaAtiva(boolean pesquisarPorCategoriaAtiva) {
		this.pesquisarPorCategoriaAtiva = pesquisarPorCategoriaAtiva;
	}

	/**
	 * Recupera o valor de dataHoraInicio.
	 *
	 * @return o valor de dataHoraInicio
	 */
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Define o valor de dataHoraInicio.
	 *
	 * @param dataHoraInicio o novo valor de dataHoraInicio
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera o valor de codigoInscricao.
	 *
	 * @return o valor de codigoInscricao
	 */
	public String getCodigoInscricao() {
		return codigoInscricao;
	}

	/**
	 * Define o valor de codigoInscricao.
	 *
	 * @param codigoInscricao o novo valor de codigoInscricao
	 */
	public void setCodigoInscricao(String codigoInscricao) {
		this.codigoInscricao = codigoInscricao;
	}

	/**
	 * Recupera o valor de codigoCategoria.
	 *
	 * @return o valor de codigoCategoria
	 */
	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}

	/**
	 * Define o valor de codigoCategoria.
	 *
	 * @param codigoCategoria o novo valor de codigoCategoria
	 */
	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	/**
	 * Recupera o valor de descricaoCategoria.
	 *
	 * @return o valor de descricaoCategoria
	 */
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	/**
	 * Define o valor de descricaoCategoria.
	 *
	 * @param descricaoCategoria o novo valor de descricaoCategoria
	 */
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	/**
	 * Recupera o valor de cliente.
	 *
	 * @return o valor de cliente
	 */
	public Boolean getCliente() {
		return cliente;
	}

	/**
	 * Define o valor de cliente.
	 *
	 * @param cliente o novo valor de cliente
	 */
	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}

	public Short getCodigoTipoBeneficiarioSicor() {
		return codigoTipoBeneficiarioSicor;
	}

	public void setCodigoTipoBeneficiarioSicor(Short codigoTipoBeneficiarioSicor) {
		this.codigoTipoBeneficiarioSicor = codigoTipoBeneficiarioSicor;
	}

	public String getDescricaoTipoBeneficiarioSicor() {
		return descricaoTipoBeneficiarioSicor;
	}

	public void setDescricaoTipoBeneficiarioSicor(String descricaoTipoBeneficiarioSicor) {
		this.descricaoTipoBeneficiarioSicor = descricaoTipoBeneficiarioSicor;
	}

}