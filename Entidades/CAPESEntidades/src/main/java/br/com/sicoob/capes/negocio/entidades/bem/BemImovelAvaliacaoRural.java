package br.com.sicoob.capes.negocio.entidades.bem;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;

/**
 * A classe que representa a entidade Bem imóvel avaliação rural
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "BEMIMOVELAVALIACAORURAL")
@PrimaryKeyJoinColumn(referencedColumnName = "IDBEM")
@CamposAutorizacao(id = "idBem", camposExibicao = {
		@CampoAutorizacao(propriedade = "benfeitoria", label = "BENFEITORIA", ordenacao = 31),
		@CampoAutorizacao(propriedade = "valorBenfeitoria", label = "VALOR BENFEITORIA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario", ordenacao = 32)
})
public class BemImovelAvaliacaoRural extends BemImovelAvaliacao {
	private static final long serialVersionUID = 6952382904903449883L;

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