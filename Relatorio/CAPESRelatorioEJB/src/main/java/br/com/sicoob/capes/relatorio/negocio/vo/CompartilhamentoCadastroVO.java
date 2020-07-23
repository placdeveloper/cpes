package br.com.sicoob.capes.relatorio.negocio.vo;

/**
 * 
 * @author Tiago.Stangarlin
 * 
 */
public class CompartilhamentoCadastroVO {

	private Short codigo;

	private String descricao;

	private String nomeGrupoCta;

	private Boolean utilizaGedGft = Boolean.FALSE;

	private String nomeGrupoCtaCadastroInstituicao;

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

	public String getNomeGrupoCta() {
		return nomeGrupoCta;
	}

	public void setNomeGrupoCta(String nomeGrupoCta) {
		this.nomeGrupoCta = nomeGrupoCta;
	}

	public Boolean getUtilizaGedGft() {
		return utilizaGedGft;
	}

	public void setUtilizaGedGft(Boolean utilizaGedGft) {
		this.utilizaGedGft = utilizaGedGft;
	}

	public String getNomeGrupoCtaCadastroInstituicao() {
		return nomeGrupoCtaCadastroInstituicao;
	}

	public void setNomeGrupoCtaCadastroInstituicao(
			String nomeGrupoCtaCadastroInstituicao) {
		this.nomeGrupoCtaCadastroInstituicao = nomeGrupoCtaCadastroInstituicao;
	}

}
