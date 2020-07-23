package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.math.BigDecimal;

/**
 * A Classe PlataformaContabilDTO.
 * 
 * @author valdemar.xavier
 */
public class PlataformaContabilDTO extends RegistroInclusaoDTO<Integer> {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7934753233652030152L;

	/** O atributo valorPatrimonio. */
	private BigDecimal valorPatrimonio;
	
	/** O atributo valorFaturamento. */
	private BigDecimal valorFaturamento;

	public BigDecimal getValorPatrimonio() {
		return valorPatrimonio;
	}

	public void setValorPatrimonio(BigDecimal valorPatrimonio) {
		this.valorPatrimonio = valorPatrimonio;
	}

	public BigDecimal getValorFaturamento() {
		return valorFaturamento;
	}

	public void setValorFaturamento(BigDecimal valorFaturamento) {
		this.valorFaturamento = valorFaturamento;
	}

	@Override
	public Integer getId() {
		return getIdInstituicao();
	}

	@Override
	public void setId(Integer id) {
		setIdInstituicao(id);
	}
	
	
	
	

	

}