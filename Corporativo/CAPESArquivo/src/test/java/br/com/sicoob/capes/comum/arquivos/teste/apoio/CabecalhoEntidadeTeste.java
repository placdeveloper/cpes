package br.com.sicoob.capes.comum.arquivos.teste.apoio;

import java.util.Date;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * TODO documentar
 *
 * Criado em 22/7/2014
 * @author rodrigo.chaves
 */
public class CabecalhoEntidadeTeste implements RegistroArquivo {

	/** O atributo numeroLinha. */
	private int numeroLinha;
	
	/** O atributo tipoRegistro. */
	@CampoArquivo(inicio = 0, tamanho = 1)
	private Character tipoRegistro = 'C';

	/** O atributo nomeArquivo. */
	@CampoArquivo(inicio = 1, tamanho = 10)
	private String nomeArquivo;

	/** O atributo dataHoraGeracao. */
	@CampoArquivo(inicio = 11, tamanho = 14, formatoData = "ddMMyyyyHHmmss")
	private Date dataHoraGeracao;

	/** O atributo operadorGeracao. */
	@CampoArquivo(inicio = 25, tamanho = 175)
	private String operadorGeracao;

	/**
	 * Construtor padrão.
	 */
	public CabecalhoEntidadeTeste() {
	}
	
	/**
	 * Construtor.
	 * 
	 * @param dataHoraGeracao
	 * @param nomeArquivo
	 * @param operadorGeracao
	 * @param tipoRegistro
	 */
	public CabecalhoEntidadeTeste(Date dataHoraGeracao, String nomeArquivo,
			String operadorGeracao) {
		this.dataHoraGeracao = dataHoraGeracao;
		this.nomeArquivo = nomeArquivo;
		this.operadorGeracao = operadorGeracao;
	}

	/**
	 * Obtém o valor do atributo <code>numeroLinha</code>.
	 * @return int - O atributo numeroLinha
	 */
	public int getNumeroLinha() {
		return numeroLinha;
	}

	/**
	 * Atribui o valor do atributo <code>numeroLinha</code>.
	 * @param numeroLinha O valor a ser atribuído.
	 */
	public void setNumeroLinha(int numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	/**
	 * Obtém o valor do atributo <code>tipoRegistro</code>.
	 * @return Character - O atributo tipoRegistro
	 */
	public Character getTipoRegistro() {
		return tipoRegistro;
	}

	/**
	 * Atribui o valor do atributo <code>tipoRegistro</code>.
	 * @param tipoRegistro O valor a ser atribuído.
	 */
	public void setTipoRegistro(Character tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	/**
	 * Obtém o valor do atributo <code>nomeArquivo</code>.
	 * @return String - O atributo nomeArquivo
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	/**
	 * Atribui o valor do atributo <code>nomeArquivo</code>.
	 * @param nomeArquivo O valor a ser atribuído.
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	/**
	 * Obtém o valor do atributo <code>dataHoraGeracao</code>.
	 * @return Date - O atributo dataHoraGeracao
	 */
	public Date getDataHoraGeracao() {
		return dataHoraGeracao;
	}

	/**
	 * Atribui o valor do atributo <code>dataHoraGeracao</code>.
	 * @param dataHoraGeracao O valor a ser atribuído.
	 */
	public void setDataHoraGeracao(Date dataHoraGeracao) {
		this.dataHoraGeracao = dataHoraGeracao;
	}

	/**
	 * Obtém o valor do atributo <code>operadorGeracao</code>.
	 * @return String - O atributo operadorGeracao
	 */
	public String getOperadorGeracao() {
		return operadorGeracao;
	}

	/**
	 * Atribui o valor do atributo <code>operadorGeracao</code>.
	 * @param operadorGeracao O valor a ser atribuído.
	 */
	public void setOperadorGeracao(String operadorGeracao) {
		this.operadorGeracao = operadorGeracao;
	}

	
}
