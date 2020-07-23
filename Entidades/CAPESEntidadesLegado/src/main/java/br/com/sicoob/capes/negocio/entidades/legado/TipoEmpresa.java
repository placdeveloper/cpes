package br.com.sicoob.capes.negocio.entidades.legado;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade que representa a entidade de Tipo Empresa.
 * @author kelisson.leite
 */
@Entity
@Table (name = "TipoEmpresa")
public class TipoEmpresa extends EntidadeDominioReplicavel<Short> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 238016577082491514L;

	
	/** O atributo codigo. */
	@Id
	@Column(name = "CODTIPOEMPRESA")
	private Short codigo;

	/** O atributo descricao. */
	@Column(name = "DESCTIPOEMPRESA")
	private String descricao;
	
	/** O atributo valorLimiteInferior. */
	@Column(name = "VALORLIMITEINFERIORFATURAMENTO")
	private BigDecimal valorLimiteInferior;
	
	/** O atributo valorLimiteSuperior. */
	@Column(name = "VALORLIMITESUPERIORFATURAMENTO")
	private BigDecimal valorLimiteSuperior;

	
	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValorLimiteInferior() {
		return valorLimiteInferior;
	}

	public void setValorLimiteInferior(BigDecimal valorLimiteInferior) {
		this.valorLimiteInferior = valorLimiteInferior;
	}

	public BigDecimal getValorLimiteSuperior() {
		return valorLimiteSuperior;
	}

	public void setValorLimiteSuperior(BigDecimal valorLimiteSuperior) {
		this.valorLimiteSuperior = valorLimiteSuperior;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

	@Override
	public Short getIdSQL() {
		return getCodigo();
	}

}
