package br.com.sicoob.capes.cadastro.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe DadosAlteracaoGrupoVO.
 */
public class DadosAlteracaoGrupoVO extends BancoobVo {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 5698589042348297717L;

	/** O atributo idTransicaoOrigem. */
	private Integer idTransicaoOrigem;
	
	/** O atributo idPessoaCompartilhamentoDestino. */
	private Long idPessoaCompartilhamentoDestino;
	
	/** O atributo idPessoaLegadoOrigem. */
	private Integer idPessoaLegadoOrigem;
	
	/** O atributo idPessoaLegadoDestino. */
	private Integer idPessoaLegadoDestino;
	
	/** O atributo idInstituicaoOrigem. */
	private Integer idInstituicaoOrigem;
	
	/** O atributo idInstituicaoDestino. */
	private Integer idInstituicaoDestino;

	/**
	 * Instancia um novo DadosAlteracaoGrupoVO.
	 *
	 * @param idTransicaoOrigem o valor de id transicao origem
	 * @param idPessoaCompartilhamentoDestino o valor de id pessoa compartilhamento destino
	 * @param idPessoaLegadoOrigem o valor de id pessoa legado origem
	 * @param idInstituicaoOrigem o valor de id instituicao origem
	 * @param idPessoaLegadoDestino o valor de id pessoa legado destino
	 * @param idInstituicaoDestino o valor de id instituicao destino
	 */
	public DadosAlteracaoGrupoVO(Integer idTransicaoOrigem,
			Long idPessoaCompartilhamentoDestino, Integer idPessoaLegadoOrigem,
			Integer idInstituicaoOrigem, Integer idPessoaLegadoDestino, Integer idInstituicaoDestino) {

		this.idTransicaoOrigem = idTransicaoOrigem;
		this.idPessoaCompartilhamentoDestino = idPessoaCompartilhamentoDestino;
		this.idPessoaLegadoOrigem = idPessoaLegadoOrigem;
		this.idPessoaLegadoDestino = idPessoaLegadoDestino;
		this.idInstituicaoOrigem = idInstituicaoOrigem;
		this.idInstituicaoDestino = idInstituicaoDestino;
	}

	/**
	 * Recupera o valor de idTransicaoOrigem.
	 *
	 * @return o valor de idTransicaoOrigem
	 */
	public Integer getIdTransicaoOrigem() {
		return idTransicaoOrigem;
	}

	/**
	 * Define o valor de idTransicaoOrigem.
	 *
	 * @param idTransicaoOrigem o novo valor de idTransicaoOrigem
	 */
	public void setIdTransicaoOrigem(Integer idTransicaoOrigem) {
		this.idTransicaoOrigem = idTransicaoOrigem;
	}

	/**
	 * Recupera o valor de idPessoaCompartilhamentoDestino.
	 *
	 * @return o valor de idPessoaCompartilhamentoDestino
	 */
	public Long getIdPessoaCompartilhamentoDestino() {
		return idPessoaCompartilhamentoDestino;
	}

	/**
	 * Define o valor de idPessoaCompartilhamentoDestino.
	 *
	 * @param idPessoaCompartilhamentoDestino o novo valor de idPessoaCompartilhamentoDestino
	 */
	public void setIdPessoaCompartilhamentoDestino(Long idPessoaCompartilhamentoDestino) {
		this.idPessoaCompartilhamentoDestino = idPessoaCompartilhamentoDestino;
	}

	/**
	 * Recupera o valor de idPessoaLegadoOrigem.
	 *
	 * @return o valor de idPessoaLegadoOrigem
	 */
	public Integer getIdPessoaLegadoOrigem() {
		return idPessoaLegadoOrigem;
	}

	/**
	 * Define o valor de idPessoaLegadoOrigem.
	 *
	 * @param idPessoaLegadoOrigem o novo valor de idPessoaLegadoOrigem
	 */
	public void setIdPessoaLegadoOrigem(Integer idPessoaLegadoOrigem) {
		this.idPessoaLegadoOrigem = idPessoaLegadoOrigem;
	}

	/**
	 * Recupera o valor de idPessoaLegadoDestino.
	 *
	 * @return o valor de idPessoaLegadoDestino
	 */
	public Integer getIdPessoaLegadoDestino() {
		return idPessoaLegadoDestino;
	}

	/**
	 * Define o valor de idPessoaLegadoDestino.
	 *
	 * @param idPessoaLegadoDestino o novo valor de idPessoaLegadoDestino
	 */
	public void setIdPessoaLegadoDestino(Integer idPessoaLegadoDestino) {
		this.idPessoaLegadoDestino = idPessoaLegadoDestino;
	}

	/**
	 * Recupera o valor de idInstituicaoOrigem.
	 *
	 * @return o valor de idInstituicaoOrigem
	 */
	public Integer getIdInstituicaoOrigem() {
		return idInstituicaoOrigem;
	}

	/**
	 * Define o valor de idInstituicaoOrigem.
	 *
	 * @param idInstituicaoOrigem o novo valor de idInstituicaoOrigem
	 */
	public void setIdInstituicaoOrigem(Integer idInstituicaoOrigem) {
		this.idInstituicaoOrigem = idInstituicaoOrigem;
	}

	/**
	 * Recupera o valor de idInstituicaoDestino.
	 *
	 * @return o valor de idInstituicaoDestino
	 */
	public Integer getIdInstituicaoDestino() {
		return idInstituicaoDestino;
	}

	/**
	 * Define o valor de idInstituicaoDestino.
	 *
	 * @param idInstituicaoDestino o novo valor de idInstituicaoDestino
	 */
	public void setIdInstituicaoDestino(Integer idInstituicaoDestino) {
		this.idInstituicaoDestino = idInstituicaoDestino;
	}

}
