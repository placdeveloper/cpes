package br.com.sicoob.capes.relatorio.negocio.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sicoob.capes.negocio.entidades.EnderecoBase;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.ProdutividadeBase;
import br.com.sicoob.capes.negocio.entidades.ReferenciaBase;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TributacaoBase;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * A Classe FichaCadastralVO.
 */
public class FichaCadastralVO {

	/** O atributo pessoa. */
	private PessoaCompartilhamento pessoa;
	
	/** O atributo pessoaInstituicao. */
	private PessoaInstituicao pessoaInstituicao;
	
	/** O atributo tributacao. */
	private TributacaoBase tributacao;
	
	/** O atributo enderecos. */
	private List<EnderecoBase> enderecos;
	
	/** O atributo emails. */
	private List<Email> emails;
	
	/** O atributo telefones. */
	private List<Telefone> telefones;
	
	/** O atributo referencias. */
	private List<? extends ReferenciaBase> referencias;
	
	/** O atributo relacionamentos. */
	private List<RelacionamentoPessoaBase> relacionamentos;
	
	/** O atributo certidoes. */
	private List<Certidao> certidoes;
	
	/** O atributo fontesDeRenda. */
	private List<FonteRenda> fontesDeRenda;
	
	/** O atributo pessoasCompartilhamento. */
	private List<?> pessoasCompartilhamento;
	
	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo tributacoes. */
	private List<Tributacao> tributacoes;
	
	/** O atributo pessoaInstituicoes. */
	private List<PessoaInstituicao> pessoaInstituicoes;
	
	private List<FichaCadastralContatoVO> contatos;
	
	/** O atributo bemImoveisDTO. */
	private List<FichaCadastralBemVO> bemImoveisDTO;
	
	/** O atributo bensNovos. */
	private List<FichaCadastralBemNovoVO> bensNovos;
	
	/** O atributo bemOutrosDTO. */
	private List<FichaCadastralBemVO> bemOutrosDTO;
	
	/** O atributo bensDTO. */
	private List<FichaCadastralBemVO> bensDTO = new ArrayList<FichaCadastralBemVO>();
	
	/** O atributo produtores. */
	private List<Produtor> produtores;
	
	/** O atributo produtividades. */
	private List<? extends ProdutividadeBase> produtividades;
	
	/** O atributo labelFiltroData. */
	private String labelFiltroData;
	
	/** O atributo filiacao. */
	private String filiacao;
	
	/** O atributo exibirTotalProdutividade. */
	private Boolean exibirTotalProdutividade = Boolean.FALSE;
	
	/** O atributo dataUltimaAtualizacao. */
	private Date dataUltimaAtualizacao;
	
	/** O atributo descricaoEsferaAdministrativa. */
	private String descricaoEsferaAdministrativa;
	
	private BigDecimal totalFontesDeRendaMensal;

	private BigDecimal totalPatrimonio;
	
	private BigDecimal totalParticipacao;
	
	private Pessoa conjuge;
	
	private String loginUsuarioEnvioAprovacao;
	
	private String loginUsuarioAprovacao;

	/**
	 * Recupera o valor de labelFiltroData.
	 *
	 * @return o valor de labelFiltroData
	 */
	public String getLabelFiltroData() {
		return labelFiltroData;
	}

	/**
	 * Define o valor de labelFiltroData.
	 *
	 * @param labelFiltroData o novo valor de labelFiltroData
	 */
	public void setLabelFiltroData(String labelFiltroData) {
		this.labelFiltroData = labelFiltroData;
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
	 * Gets the produtividades.
	 *
	 * @return the produtividades
	 */
	public List<? extends ProdutividadeBase> getProdutividades() {
		return produtividades;
	}

	/**
	 * Define o valor de produtividades.
	 *
	 * @param produtividades o novo valor de produtividades
	 */
	public void setProdutividades(List<? extends ProdutividadeBase> produtividades) {
		this.produtividades = produtividades;
	}

	/**
	 * Recupera o valor de produtores.
	 *
	 * @return o valor de produtores
	 */
	public List<Produtor> getProdutores() {
		return produtores;
	}

	/**
	 * Define o valor de produtores.
	 *
	 * @param produtores o novo valor de produtores
	 */
	public void setProdutores(List<Produtor> produtores) {
		this.produtores = produtores;
	}

	/**
	 * Recupera o valor de bemOutrosDTO.
	 *
	 * @return o valor de bemOutrosDTO
	 */
	public List<FichaCadastralBemVO> getBemOutrosDTO() {
		return bemOutrosDTO;
	}

	/**
	 * Define o valor de bemOutrosDTO.
	 *
	 * @param bemOutrosDTO o novo valor de bemOutrosDTO
	 */
	public void setBemOutrosDTO(List<FichaCadastralBemVO> bemOutrosDTO) {
		this.bemOutrosDTO = bemOutrosDTO;
	}

	/**
	 * Recupera o valor de bemImoveisDTO.
	 *
	 * @return o valor de bemImoveisDTO
	 */
	public List<FichaCadastralBemVO> getBemImoveisDTO() {
		return bemImoveisDTO;
	}

	/**
	 * Define o valor de bemImoveisDTO.
	 *
	 * @param bemImoveisDTO o novo valor de bemImoveisDTO
	 */
	public void setBemImoveisDTO(List<FichaCadastralBemVO> bemImoveisDTO) {
		this.bemImoveisDTO = bemImoveisDTO;
	}

	/**
	 * Recupera o valor de pessoaInstituicoes.
	 *
	 * @return o valor de pessoaInstituicoes
	 */
	public List<PessoaInstituicao> getPessoaInstituicoes() {
		return pessoaInstituicoes;
	}

	/**
	 * Define o valor de pessoaInstituicoes.
	 *
	 * @param pessoaInstituicoes o novo valor de pessoaInstituicoes
	 */
	public void setPessoaInstituicoes(List<PessoaInstituicao> pessoaInstituicoes) {
		this.pessoaInstituicoes = pessoaInstituicoes;
	}

	/**
	 * Recupera o valor de tributacoes.
	 *
	 * @return o valor de tributacoes
	 */
	public List<Tributacao> getTributacoes() {
		return tributacoes;
	}

	/**
	 * Define o valor de tributacoes.
	 *
	 * @param tributacoes o novo valor de tributacoes
	 */
	public void setTributacoes(List<Tributacao> tributacoes) {
		this.tributacoes = tributacoes;
	}

	/**
	 * Recupera o valor de pessoasCompartilhamento.
	 *
	 * @return o valor de pessoasCompartilhamento
	 */
	public List<?> getPessoasCompartilhamento() {
		return pessoasCompartilhamento;
	}

	/**
	 * Define o valor de pessoasCompartilhamento.
	 *
	 * @param pessoasCompartilhamento o novo valor de pessoasCompartilhamento
	 */
	public void setPessoasCompartilhamento(List<?> pessoasCompartilhamento) {
		this.pessoasCompartilhamento = pessoasCompartilhamento;
	}

	/**
	 * @return the idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * @param idUnidadeInst
	 *            the idUnidadeInst to set
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * @return the pessoa
	 */
	public PessoaCompartilhamento getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa
	 *            the pessoa to set
	 */
	public void setPessoa(PessoaCompartilhamento pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the pessoaInstituicao
	 */
	public PessoaInstituicao getPessoaInstituicao() {
		return pessoaInstituicao;
	}

	/**
	 * @param pessoaInstituicao
	 *            the pessoaInstituicao to set
	 */
	public void setPessoaInstituicao(PessoaInstituicao pessoaInstituicao) {
		this.pessoaInstituicao = pessoaInstituicao;
	}

	/**
	 * @return the tributacao
	 */
	public TributacaoBase getTributacao() {
		return tributacao;
	}

	/**
	 * @param tributacao
	 *            the tributacao to set
	 */
	public void setTributacao(TributacaoBase tributacao) {
		this.tributacao = tributacao;
	}

	/**
	 * @return the enderecos
	 */
	public List<EnderecoBase> getEnderecos() {
		return enderecos;
	}

	/**
	 * @param enderecos
	 *            the enderecos to set
	 */
	public void setEnderecos(List<EnderecoBase> enderecos) {
		this.enderecos = enderecos;
	}

	/**
	 * @return the emails
	 */
	public List<Email> getEmails() {
		return emails;
	}

	/**
	 * @param emails
	 *            the emails to set
	 */
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	/**
	 * @return the telefones
	 */
	public List<Telefone> getTelefones() {
		return telefones;
	}

	/**
	 * @param telefones
	 *            the telefones to set
	 */
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	/**
	 * @return the referencias
	 */
	public List<? extends ReferenciaBase> getReferencias() {
		return referencias;
	}

	/**
	 * @param referencias
	 *            the referencias to set
	 */
	public void setReferencias(List<? extends ReferenciaBase> referencias) {
		this.referencias = referencias;
	}

	/**
	 * @return the relacionamentos
	 */
	public List<RelacionamentoPessoaBase> getRelacionamentos() {
		return relacionamentos;
	}

	/**
	 * @param relacionamentos
	 *            the relacionamentos to set
	 */
	public void setRelacionamentos(List<RelacionamentoPessoaBase> relacionamentos) {
		this.relacionamentos = relacionamentos;
	}

	/**
	 * @return the certidoes
	 */
	public List<Certidao> getCertidoes() {
		return certidoes;
	}

	/**
	 * @param certidoes
	 *            the certidoes to set
	 */
	public void setCertidoes(List<Certidao> certidoes) {
		this.certidoes = certidoes;
	}

	/**
	 * @return the fontesDeRenda
	 */
	public List<FonteRenda> getFontesDeRenda() {
		return fontesDeRenda;
	}

	/**
	 * @param fontesDeRenda
	 *            the fontesDeRenda to set
	 */
	public void setFontesDeRenda(List<FonteRenda> fontesDeRenda) {
		this.fontesDeRenda = fontesDeRenda;
	}

	/**
	 * Recupera o valor de exibirTotalProdutividade.
	 *
	 * @return o valor de exibirTotalProdutividade
	 */
	public Boolean getExibirTotalProdutividade() {
		return exibirTotalProdutividade;
	}

	/**
	 * Define o valor de exibirTotalProdutividade.
	 *
	 * @param exibirTotalProdutividade o novo valor de exibirTotalProdutividade
	 */
	public void setExibirTotalProdutividade(Boolean exibirTotalProdutividade) {
		this.exibirTotalProdutividade = exibirTotalProdutividade;
	}

	/**
	 * Recupera o valor de dataUltimaAtualizacao.
	 *
	 * @return o valor de dataUltimaAtualizacao
	 */
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	/**
	 * Define o valor de dataUltimaAtualizacao.
	 *
	 * @param dataUltimaAtualizacao o novo valor de dataUltimaAtualizacao
	 */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	/**
	 * @return the bensDTO
	 */
	public List<FichaCadastralBemVO> getBensDTO() {
		return bensDTO;
	}

	/**
	 * @param bensDTO the bensDTO to set
	 */
	public void setBensDTO(List<FichaCadastralBemVO> bensDTO) {
		this.bensDTO = bensDTO;
	}

	/**
	 * Recupera o valor de descricaoEsferaAdministrativa.
	 *
	 * @return o valor de descricaoEsferaAdministrativa
	 */
	public String getDescricaoEsferaAdministrativa() {
		return descricaoEsferaAdministrativa;
	}

	/**
	 * Define o valor de descricaoEsferaAdministrativa.
	 *
	 * @param descricaoEsferaAdministrativa o novo valor de descricaoEsferaAdministrativa
	 */
	public void setDescricaoEsferaAdministrativa(String descricaoEsferaAdministrativa) {
		this.descricaoEsferaAdministrativa = descricaoEsferaAdministrativa;
	}

	public List<FichaCadastralContatoVO> getContatos() {
		if(contatos == null){
			contatos = new ArrayList<FichaCadastralContatoVO>();
		}
		return contatos;
	}

	public Pessoa getConjuge() {
		return conjuge;
	}

	public void setConjuge(Pessoa conjuge) {
		this.conjuge = conjuge;
	}

	public BigDecimal getTotalFontesDeRendaMensal() {
		return totalFontesDeRendaMensal;
	}

	public void setTotalFontesDeRendaMensal(BigDecimal totalFontesDeRendaMensal) {
		this.totalFontesDeRendaMensal = totalFontesDeRendaMensal;
	}

	public BigDecimal getTotalPatrimonio() {
		return totalPatrimonio;
	}

	public void setTotalPatrimonio(BigDecimal totalPatrimonio) {
		this.totalPatrimonio = totalPatrimonio;
	}

	public String getFiliacao() {
		return filiacao;
	}

	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}

	public BigDecimal getTotalParticipacao() {
		return totalParticipacao;
	}

	public void setTotalParticipacao(BigDecimal totalParticipacao) {
		this.totalParticipacao = totalParticipacao;
	}

	public List<FichaCadastralBemNovoVO> getBensNovos() {
		return bensNovos;
	}

	public void setBensNovos(List<FichaCadastralBemNovoVO> bensNovos) {
		this.bensNovos = bensNovos;
	}

	public String getLoginUsuarioEnvioAprovacao() {
		return loginUsuarioEnvioAprovacao;
	}

	public void setLoginUsuarioEnvioAprovacao(String loginUsuarioEnvioAprovacao) {
		this.loginUsuarioEnvioAprovacao = loginUsuarioEnvioAprovacao;
	}

	public String getLoginUsuarioAprovacao() {
		return loginUsuarioAprovacao;
	}

	public void setLoginUsuarioAprovacao(String loginUsuarioAprovacao) {
		this.loginUsuarioAprovacao = loginUsuarioAprovacao;
	}
	
	public void setDadoLoginEnvioAprovacao(){
		
	}
	
}