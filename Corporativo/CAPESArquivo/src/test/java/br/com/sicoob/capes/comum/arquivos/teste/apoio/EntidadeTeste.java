package br.com.sicoob.capes.comum.arquivos.teste.apoio;

import java.math.BigDecimal;
import java.util.Date;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * Entidade de teste que representa o detalhe de um arquivo fictício usado para testes do componente.
 * 
 * Criado em 22/7/2014
 * 
 * @author rodrigo.chaves
 */
public class EntidadeTeste implements RegistroArquivo {

	/** O atributo codigo. */
	@CampoArquivo(inicio = 0, tamanho = 4, complemento = '0', alinhamento = AlinhamentoCampo.DIREITA)
	private Integer codigo;

	/** O atributo nome. */
	@CampoArquivo(inicio = 4, tamanho = 30)
	private String nome;

	/** O atributo descricao. */
	@CampoArquivo(inicio = 34, tamanho = 150)
	private String descricao;

	/** O atributo dataNascimento. */
	@CampoArquivo(inicio = 184, tamanho = 8, formatoData = "ddMMyyyy")
	private Date dataNascimento;

	/** O atributo valor. */
	@CampoArquivo(inicio = 192, tamanho = 8, precisaoDecimal = 2, complemento = '0', alinhamento = AlinhamentoCampo.DIREITA)
	private BigDecimal valor;

	/** O atributo numeroLinha. */
	private int numeroLinha;

	/**
	 * Obtém o valor do atributo <code>codigo</code>.
	 * 
	 * @return Integer - O atributo codigo
	 */
	public Integer getCodigo() {

		return codigo;
	}

	/**
	 * Atribui o valor do atributo <code>codigo</code>.
	 * 
	 * @param codigo
	 *            O valor a ser atribuído.
	 */
	public void setCodigo(Integer codigo) {

		this.codigo = codigo;
	}

	/**
	 * Obtém o valor do atributo <code>nome</code>.
	 * 
	 * @return String - O atributo nome
	 */
	public String getNome() {

		return nome;
	}

	/**
	 * Atribui o valor do atributo <code>nome</code>.
	 * 
	 * @param nome
	 *            O valor a ser atribuído.
	 */
	public void setNome(String nome) {

		this.nome = nome;
	}

	/**
	 * Obtém o valor do atributo <code>descricao</code>.
	 * 
	 * @return String - O atributo descricao
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Atribui o valor do atributo <code>descricao</code>.
	 * 
	 * @param descricao
	 *            O valor a ser atribuído.
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	/**
	 * Obtém o valor do atributo <code>dataNascimento</code>.
	 * 
	 * @return Date - O atributo dataNascimento
	 */
	public Date getDataNascimento() {

		return dataNascimento;
	}

	/**
	 * Atribui o valor do atributo <code>dataNascimento</code>.
	 * 
	 * @param dataNascimento
	 *            O valor a ser atribuído.
	 */
	public void setDataNascimento(Date dataNascimento) {

		this.dataNascimento = dataNascimento;
	}

	/**
	 * Obtém o valor do atributo <code>valor</code>.
	 * 
	 * @return BigDecimal - O atributo valor
	 */
	public BigDecimal getValor() {

		return valor;
	}

	/**
	 * Atribui o valor do atributo <code>valor</code>.
	 * 
	 * @param valor
	 *            O valor a ser atribuído.
	 */
	public void setValor(BigDecimal valor) {

		this.valor = valor;
	}

	/**
	 * Obtém o valor do atributo <code>numeroLinha</code>.
	 * 
	 * @return int - O atributo numeroLinha
	 */
	public int getNumeroLinha() {

		return numeroLinha;
	}

	/**
	 * Atribui o valor do atributo <code>numeroLinha</code>.
	 * 
	 * @param numeroLinha
	 *            O valor a ser atribuído.
	 */
	public void setNumeroLinha(int numeroLinha) {

		this.numeroLinha = numeroLinha;
	}

}
