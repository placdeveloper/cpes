/*
 * SICOOB
 * 
 * TransicaoReplicacao.java(br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.legado.CAPESEntidadeLegado;


/**
 * Classe que representa a TransicaoReplicacao.
 * 
 * @author Juan.Damasceno
 */
@Entity
@Table(name = "REPLICAPESSOASQLDB2")
public class TransicaoReplicacao extends CAPESEntidadeLegado<Integer> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** O atributo id replica pessoa. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idReplicaPessoa;
	
	/** O atributo id instituicao. */
	@Column(name="idInstituicao")	
	private Integer idInstituicao;

	/** O atributo id unidade. */
	@Column(name="IdUnidadeInst")
	private Integer idUnidade;
	
	/** O atributo id pessoa. */
	@Column(name="IdPessoaLegado")
	private Integer idPessoa;
	
	/** O atributo data atualizacao. */
	private Date dataAtualizacao;
	
	/** O atributo cod compartilhamento cadastro. */
	private Short codCompartilhamentoCadastro;
	
	/** O atributo cpf cnpj. */
	@Column(name = "NumCpfCnpj")
	private String cpfCnpj;
	
	/** O atributo nome. */
	@Column(name = "NomePessoa")
	private String nome;
	
	/** O atributo tipo pessoa. */
	@Column(name="CodTipoPessoa")
	private Short tipoPessoa;
	
	/** O atributo tipo operacao. */
	@Column(name="CodTipoOperacao")
	private Character tipoOperacao;
	
	/** O atributo data processamento. */
	private Date dataProcessamento;
	
	/** O atributo descricao erro. */
	@Column(name="DescErro")
	private String descricaoErro;

	
	/**
	 * Cria uma nova instância de transicao replicacao.
	 */
	public TransicaoReplicacao() {
		super();
	}

	/**
	 * Cria uma nova instância de transicao replicacao.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idUnidade
	 *            the id unidade
	 * @param idPessoa
	 *            the id pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param nome
	 *            the nome
	 * @param descricaoErro
	 *            the descricao erro
	 * @param tipoPessoa
	 *            the tipo pessoa
	 * @param tipoOperacao
	 *            the tipo operacao
	 * @param dataAtualizacao
	 *            the data atualizacao
	 */
	public TransicaoReplicacao(
			Integer idInstituicao,
			Integer idUnidade,
			Integer idPessoa,
			String cpfCnpj,
			String nome,
			String descricaoErro, 
			Short tipoPessoa,
			Character tipoOperacao,
			Date dataAtualizacao) {
		
		this.idInstituicao = idInstituicao;
		this.idUnidade = idUnidade;
		this.idPessoa = idPessoa;
		this.dataAtualizacao = dataAtualizacao;
		
		this.cpfCnpj = cpfCnpj;

		this.descricaoErro = descricaoErro;
		this.nome = nome;
		this.tipoOperacao = tipoOperacao;
		this.tipoPessoa = tipoPessoa;
	}

	/**
	 * Recupera id replica pessoa.
	 * 
	 * @return id replica pessoa
	 */
	public Integer getIdReplicaPessoa() {
		return idReplicaPessoa;
	}

	/**
	 * Preenche id replica pessoa.
	 * 
	 * @param idReplicaPessoa
	 *            o novo id replica pessoa
	 */
	public void setIdReplicaPessoa(Integer idReplicaPessoa) {
		this.idReplicaPessoa = idReplicaPessoa;
	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera id unidade.
	 * 
	 * @return id unidade
	 */
	public Integer getIdUnidade() {
		return idUnidade;
	}

	/**
	 * Preenche id unidade.
	 * 
	 * @param idUnidade
	 *            o novo id unidade
	 */
	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	/**
	 * Recupera id pessoa.
	 * 
	 * @return id pessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Preenche id pessoa.
	 * 
	 * @param idPessoa
	 *            o novo id pessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera data atualizacao.
	 * 
	 * @return data atualizacao
	 */
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	/**
	 * Preenche data atualizacao.
	 * 
	 * @param dataAtualizacao
	 *            o novo data atualizacao
	 */
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
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
	 * @param idSistemaCooperativo
	 *            o novo cod compartilhamento cadastro
	 */
	public void setCodCompartilhamentoCadastro(Short idSistemaCooperativo) {
		codCompartilhamentoCadastro = idSistemaCooperativo;
	}

	/**
	 * Recupera cpf cnpj.
	 * 
	 * @return cpf cnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Preenche cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            o novo cpf cnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	

	/**
	 * Recupera nome.
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Preenche nome.
	 * 
	 * @param nome
	 *            o novo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * Recupera tipo pessoa.
	 * 
	 * @return tipo pessoa
	 */
	public Short getTipoPessoa() {
		return tipoPessoa;
	}

	/**
	 * Preenche tipo pessoa.
	 * 
	 * @param tipoPessoa
	 *            o novo tipo pessoa
	 */
	public void setTipoPessoa(Short tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}


	/**
	 * Recupera tipo operacao.
	 * 
	 * @return tipo operacao
	 */
	public Character getTipoOperacao() {
		return tipoOperacao;
	}

	/**
	 * Preenche tipo operacao.
	 * 
	 * @param tipoOperacao
	 *            o novo tipo operacao
	 */
	public void setTipoOperacao(Character tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	/**
	 * Recupera data processamento.
	 * 
	 * @return data processamento
	 */
	@Column(name="DataProcessamento")
	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	/**
	 * Preenche data processamento.
	 * 
	 * @param dataProcessamento
	 *            o novo data processamento
	 */
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	/**
	 * Recupera descricao erro.
	 * 
	 * @return descricao erro
	 */
	@Column(name="DescErro")
	public String getDescricaoErro() {
		return descricaoErro;
	}

	/**
	 * Preenche descricao erro.
	 * 
	 * @param descricaoErro
	 *            o novo descricao erro
	 */
	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

	/**
	 * Verifica se é pessoa fisica.
	 * 
	 * @return true, se for pessoa fisica
	 */
	@Transient
	public boolean isPessoaFisica() {
		return isPessoa(TipoPessoaEnum.PESSOA_FISICA);
	}

	/**
	 * Verifica se é pessoa juridica.
	 * 
	 * @return true, se for pessoa juridica
	 */
	@Transient
	public boolean isPessoaJuridica() {
		return isPessoa(TipoPessoaEnum.PESSOA_JURIDICA);
	}
	
	/**
	 * Verifica se é pessoa.
	 * 
	 * @param tipoPessoaEnum
	 *            the tipo pessoa enum
	 * @return true, se for pessoa
	 */
	@Transient
	private boolean isPessoa(TipoPessoaEnum tipoPessoaEnum) {
		return tipoPessoa.shortValue() == tipoPessoaEnum.getCodigo().shortValue();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getIdReplicaPessoa();
	}
}
