package br.com.sicoob.capes.relatorio.negocio.vo;

/**
 * 
 * @author Tiago.Stangarlin
 *
 */
public class GrauInstrucaoVO {

	
	private Short codigo;

	private String descricao;

	private Integer codigoGrauInstrucaoBNDES;

	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCodigoGrauInstrucaoBNDES() {
		return codigoGrauInstrucaoBNDES;
	}

	public void setCodigoGrauInstrucaoBNDES(Integer codigoGrauInstrucaoBNDES) {
		this.codigoGrauInstrucaoBNDES = codigoGrauInstrucaoBNDES;
	}
}
