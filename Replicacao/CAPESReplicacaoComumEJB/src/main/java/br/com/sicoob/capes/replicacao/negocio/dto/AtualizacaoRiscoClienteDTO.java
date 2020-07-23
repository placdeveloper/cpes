// 30/04/2013 - 10:33:54
package br.com.sicoob.capes.replicacao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Rodrigo.Chaves
 */
public class AtualizacaoRiscoClienteDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -3023672813640170718L;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numCliente. */
	private Integer numCliente;
	
	/** O atributo motivoRisco. */
	private String motivoRisco;
	
	/** O atributo idNivelRisco. */
	private String idNivelRisco;

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
	 * Recupera o valor de numCliente.
	 *
	 * @return o valor de numCliente
	 */
	public Integer getNumCliente() {
		return numCliente;
	}

	/**
	 * Define o valor de numCliente.
	 *
	 * @param numCliente o novo valor de numCliente
	 */
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	/**
	 * Recupera o valor de motivoRisco.
	 *
	 * @return o valor de motivoRisco
	 */
	public String getMotivoRisco() {
		return motivoRisco;
	}

	/**
	 * Define o valor de motivoRisco.
	 *
	 * @param motivoRisco o novo valor de motivoRisco
	 */
	public void setMotivoRisco(String motivoRisco) {
		this.motivoRisco = motivoRisco;
	}

	/**
	 * Recupera o valor de idNivelRisco.
	 *
	 * @return o valor de idNivelRisco
	 */
	public String getIdNivelRisco() {
		return idNivelRisco;
	}

	/**
	 * Define o valor de idNivelRisco.
	 *
	 * @param idNivelRisco o novo valor de idNivelRisco
	 */
	public void setIdNivelRisco(String idNivelRisco) {
		this.idNivelRisco = idNivelRisco;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "[idNivelRisco=" + getIdNivelRisco() + "], [motivoRisco="
				+ getMotivoRisco() + "], [idInstituicao=" + getIdInstituicao()
				+ "], [numCliente=" + getNumCliente() + "]";
	}
	
	/**
	 * O método de acesso.
	 *
	 * @param args os argumentos.
	 */
	public static void main(String[] args) {
		System.out.println(new AtualizacaoRiscoClienteDTO());
	}
}
