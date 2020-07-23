package br.com.sicoob.capes.negocio.entidades.bem.historico;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A classe que representa a entidade Histórico bem imóvel avaliação rural.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "HISTBEMIMOVELAVALIACAORURAL")
public class HistoricoBemImovelAvaliacaoRural extends HistoricoBemImovelAvaliacao {
	private static final long serialVersionUID = -8084184178330402168L;

	@Column(name = "DESCBENFEITORIA", length = 250)
	private String benfeitoria;

	@Column(name = "VALORBENFEITORIA", precision = 19, scale = 2)
	private BigDecimal valorBenfeitoria;

	public String getBenfeitoria() {
		return benfeitoria;
	}

	public void setBenfeitoria(String benfeitoria) {
		this.benfeitoria = benfeitoria;
	}

	public BigDecimal getValorBenfeitoria() {
		return valorBenfeitoria;
	}

	public void setValorBenfeitoria(BigDecimal valorBenfeitoria) {
		this.valorBenfeitoria = valorBenfeitoria;
	}
}