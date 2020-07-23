package br.com.sicoob.capes.relatorio.negocio.vo;

import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;


/**
 * VO que será usado no preenchimento do Jasper do Formulário de Visita de PF ou PJ.
 * */
public class FormularioVisitaVO {
	
	private final String ARQUIVO_RELATORIO_PF = "FormularioConhecaSeuClientePF.jasper";
	private final String TITULO_RELATORIO_PF = "Formulário de Visita: Conheça seu cliente Pessoa Fisíca";
	
	private final String ARQUIVO_RELATORIO_PJ = "FormularioConhecaSeuClientePJ.jasper";
	private final String TITULO_RELATORIO_PJ = "Formulário de Visita: Conheça seu cliente Pessoa Jurídica";

	/** O atributo nomePessoa. */
	private String nomeCompleto;
	
	/** O atributo razaoSocial. */
	private String razaoSocial;

	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo descricaoAtividade. */
	private String descricaoAtividade;
	
	/** O atributo profissao. */
	private String profissao;
	
	/** O atributo politicamenteExposta. */
	private boolean politicamenteExposta;
	
	/** O atributo endereco. */
	private String endereco;
	
	/** O atributo telefone. */
	private String telefone;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	private Short codTipoPessoa;

	public String getNomePessoa() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public boolean isPoliticamenteExposta() {
		return politicamenteExposta;
	}

	public void setPoliticamenteExposta(boolean politicamenteExposta) {
		this.politicamenteExposta = politicamenteExposta;
	}

	public Short getCodTipoPessoa() {
		return codTipoPessoa;
	}

	public void setCodTipoPessoa(Short codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}
	
	public String getTituloRelatorio(){
		return TipoPessoaEnum.isPessoaFisica(getCodTipoPessoa()) ? TITULO_RELATORIO_PF : TITULO_RELATORIO_PJ; 
	}
	
	public String getArquivoRelatorio (){
		return TipoPessoaEnum.isPessoaFisica(getCodTipoPessoa()) ? ARQUIVO_RELATORIO_PF : ARQUIVO_RELATORIO_PJ; 
	}
	
}
