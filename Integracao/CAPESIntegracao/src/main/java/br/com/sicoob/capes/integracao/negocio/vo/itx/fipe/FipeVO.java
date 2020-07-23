package br.com.sicoob.capes.integracao.negocio.vo.itx.fipe;

import br.com.bancoob.negocio.vo.BancoobVo;

import com.google.gson.annotations.SerializedName;

public abstract class FipeVO extends BancoobVo {
	private static final long serialVersionUID = 4616121266800722806L;

	@SerializedName("id")
	private Integer id;

	@SerializedName("name")
	private String nome;

	@SerializedName("fipe_name")
	private String nomeFipe;

	@SerializedName("key")
	private String chave;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFipe() {
		return nomeFipe;
	}

	public void setNomeFipe(String nomeFipe) {
		this.nomeFipe = nomeFipe;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

}