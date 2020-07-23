/*
 * SICOOB
 * 
 * EnderecoBase.java(br.com.sicoob.capes.negocio.entidades.EnderecoBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa a tabela responsável por armazenar os endereços de uma pessoaCompartilhamento.
 * 
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class EnderecoBase extends EntidadeCadastroBase {

	/** Serial UID.*/
	private static final long serialVersionUID = 4621608107047180147L;

	/** O atributo tipo endereco. */
	@JoinColumn( name = "CODTIPOENDERECO", referencedColumnName = "CODTIPOENDERECO" )
	@ManyToOne
	private TipoEndereco tipoEndereco;

	/** O atributo pessoa compartilhamento. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/** O atributo descricao. */
	@Column(name = "DESCLOGRADOURO")
	private String descricao;

	/** O atributo numero. */
	@Column(name = "DESCNUMERO")
	private String numero;
	
	/** O atributo complemento. */
	@Column(name = "DESCCOMPLEMENTO")
	private String complemento;
	
	/** O atributo tipo logradouro. */
	@Embedded
	private TipoLogradouro tipoLogradouro;	
	
	/** O atributo bairro. */
	@Column(name = "NOMEBAIRRO")
	private String bairro;

	/** O atributo localidade. */
	@Embedded
	private Localidade localidade;	
	
	/** O atributo cep. */
	@Column(name = "CODCEP")
	private String cep;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	/**
	 * @return the tipoEndereco
	 */
	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	/**
	 * @param tipoEndereco the tipoEndereco to set
	 */
	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	/**
	 * @return the pessoaCompartilhamento
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	/**
	 * @param pessoaCompartilhamento the pessoaCompartilhamento to set
	 */
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoa) {
		this.pessoaCompartilhamento = pessoa;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the tipoLogradouro
	 */
	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	/**
	 * @param tipoLogradouro the tipoLogradouro to set
	 */
	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the localidade
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade the localidade to set
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

}
