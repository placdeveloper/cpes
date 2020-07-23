package br.com.sicoob.capes.comum.arquivos.negocio.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Classe respons�vel por guardar dados estat�sticos sobre a importa��o de
 * um arquivo de texto.
 *
 * Criado em 22/7/2014
 * @author rodrigo.chaves
 */
public class ResumoImportacaoVo {

	/** O atributo quantidadeTotalRegistros. */
	private Integer quantidadeTotalRegistros;
	
	/** O atributo quantidadeTotalImportados. */
	private Integer quantidadeTotalImportados;
	
	/** O atributo quantidadeTotalRejeitados. */
	private Integer quantidadeTotalRejeitados;

	/**
	 * Construtor que recebe os dados ap�s a importa��o de um arquivo.
	 * A quantidade de registros importados � calculada com base no total
	 * e nos rejeitados.
	 * 
	 * @param quantidadeTotalRegistros - Representa o total de registros do 
	 * arquivo.
	 * @param quantidadeTotalRejeitados - Representa o total de registros
	 * rejeitados.
	 */
	public ResumoImportacaoVo(Integer quantidadeTotalRegistros,
			Integer quantidadeTotalRejeitados) {
		this.quantidadeTotalRegistros = quantidadeTotalRegistros;
		this.quantidadeTotalRejeitados = quantidadeTotalRejeitados;
		this.quantidadeTotalImportados = quantidadeTotalRegistros
				- quantidadeTotalRejeitados;
	}

	/**
	 * Obt�m o valor do atributo <code>quantidadeTotalRegistros</code>.
	 * @return int - O atributo quantidadeTotalRegistros
	 */
	public int getQuantidadeTotalRegistros() {
		return quantidadeTotalRegistros;
	}

	/**
	 * Obt�m o valor do atributo <code>quantidadeTotalImportados</code>.
	 * @return int - O atributo quantidadeTotalImportados
	 */
	public int getQuantidadeTotalImportados() {
		return quantidadeTotalImportados;
	}

	/**
	 * Obt�m o valor do atributo <code>quantidadeTotalRejeitados</code>.
	 * @return int - O atributo quantidadeTotalRejeitados
	 */
	public int getQuantidadeTotalRejeitados() {
		return quantidadeTotalRejeitados;
	}

	/**
	 * Gera a representa��o em {@link String} desta inst�ncia.
	 * 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
