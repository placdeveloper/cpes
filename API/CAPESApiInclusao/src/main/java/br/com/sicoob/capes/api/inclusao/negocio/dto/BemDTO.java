package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.math.BigDecimal;

/**
 * A Classe BemDTO.
 * 
 * @author bruno.carneiro
 */
public class BemDTO extends RegistroInclusaoDTO<Long> {
	private static final long serialVersionUID = -7564208745812845845L;

	private Long idBem;
	private Short codigoTipoClassificacaoBem;
	private String descricao;
	private BigDecimal valor;
	private Boolean valorNaoInformado = Boolean.FALSE;
	private Short mesRenovacaoSeguro;
	
	public BemDTO() {
		super();
	}
	
	public BemDTO(Short codigoTipoClassificacaoBem) {
		super();
		this.codigoTipoClassificacaoBem = codigoTipoClassificacaoBem;
	}

	public Long getIdBem() {
		return idBem;
	}

	public void setIdBem(Long idBem) {
		this.idBem = idBem;
	}

	public Short getCodigoTipoClassificacaoBem() {
		return codigoTipoClassificacaoBem;
	}

	public void setCodigoTipoClassificacaoBem(Short codigoTipoClassificacaoBem) {
		this.codigoTipoClassificacaoBem = codigoTipoClassificacaoBem;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getValorNaoInformado() {
		return valorNaoInformado;
	}

	public void setValorNaoInformado(Boolean valorNaoInformado) {
		this.valorNaoInformado = valorNaoInformado;
	}

	public Short getMesRenovacaoSeguro() {
		return mesRenovacaoSeguro;
	}

	public void setMesRenovacaoSeguro(Short mesRenovacaoSeguro) {
		this.mesRenovacaoSeguro = mesRenovacaoSeguro;
	}

	@Override
	public Long getId() {
		return getIdBem();
	}

	@Override
	public void setId(Long id) {
		setIdBem(id);
	}

}