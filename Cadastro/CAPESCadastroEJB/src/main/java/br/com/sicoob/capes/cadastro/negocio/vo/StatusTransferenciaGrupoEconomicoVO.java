package br.com.sicoob.capes.cadastro.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe StatusTransferenciaGrupoEconomicoVO.
 */
public class StatusTransferenciaGrupoEconomicoVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -744074317180728131L;

	/** O atributo nomeGrupoEconomico. */
	private String nomeGrupoEconomico;
	
	/** O atributo quantidade. */
	private Long quantidade;

	/**
	 * Recupera o valor de nomeGrupoEconomico.
	 *
	 * @return o valor de nomeGrupoEconomico
	 */
	public String getNomeGrupoEconomico() {
		return nomeGrupoEconomico;
	}

	/**
	 * Define o valor de nomeGrupoEconomico.
	 *
	 * @param nomeGrupoEconomico o novo valor de nomeGrupoEconomico
	 */
	public void setNomeGrupoEconomico(String nomeGrupoEconomico) {
		this.nomeGrupoEconomico = nomeGrupoEconomico;
	}

	/**
	 * Recupera o valor de quantidade.
	 *
	 * @return o valor de quantidade
	 */
	public Long getQuantidade() {
		return quantidade;
	}

	/**
	 * Define o valor de quantidade.
	 *
	 * @param quantidade o novo valor de quantidade
	 */
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
