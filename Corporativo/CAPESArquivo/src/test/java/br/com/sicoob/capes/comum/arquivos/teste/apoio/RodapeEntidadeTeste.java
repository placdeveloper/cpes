package br.com.sicoob.capes.comum.arquivos.teste.apoio;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * Criado em 22/7/2014
 * @author rodrigo.chaves
 */
public class RodapeEntidadeTeste implements RegistroArquivo {

	/** O atributo numeroLinha. */
	private int numeroLinha;
	
	/** O atributo tipoRegistro. */
	@CampoArquivo(inicio = 0, tamanho = 1)
	private Character tipoRegistro = 'R';

	/** O atributo quantidadeRegistros. */
	@CampoArquivo(inicio = 1, tamanho = 199, complemento = '0',
			alinhamento = AlinhamentoCampo.DIREITA)
	private Long quantidadeRegistros;

	/**
	 * Construtor padr�o.
	 */
	public RodapeEntidadeTeste() {
	}
	
	/**
	 * Construtor.
	 * @param quantidadeRegistros
	 */
	public RodapeEntidadeTeste(Long quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}

	/**
	 * Obt�m o valor do atributo <code>numeroLinha</code>.
	 * @return int - O atributo numeroLinha
	 */
	public int getNumeroLinha() {
		return numeroLinha;
	}

	/**
	 * Atribui o valor do atributo <code>numeroLinha</code>.
	 * @param numeroLinha O valor a ser atribu�do.
	 */
	public void setNumeroLinha(int numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	/**
	 * Obt�m o valor do atributo <code>tipoRegistro</code>.
	 * @return Character - O atributo tipoRegistro
	 */
	public Character getTipoRegistro() {
		return tipoRegistro;
	}

	/**
	 * Atribui o valor do atributo <code>tipoRegistro</code>.
	 * @param tipoRegistro O valor a ser atribu�do.
	 */
	public void setTipoRegistro(Character tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	/**
	 * Obt�m o valor do atributo <code>quantidadeRegistros</code>.
	 * @return Long - O atributo quantidadeRegistros
	 */
	public Long getQuantidadeRegistros() {
		return quantidadeRegistros;
	}

	/**
	 * Atribui o valor do atributo <code>quantidadeRegistros</code>.
	 * @param quantidadeRegistros O valor a ser atribu�do.
	 */
	public void setQuantidadeRegistros(Long quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}
	
}
