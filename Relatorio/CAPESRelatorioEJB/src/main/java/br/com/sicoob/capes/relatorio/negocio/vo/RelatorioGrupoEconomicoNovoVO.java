package br.com.sicoob.capes.relatorio.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.tipos.DateTime;

/**
 * 
 * @author paulo.stoppa
 *
 */
public final class RelatorioGrupoEconomicoNovoVO extends BancoobVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3830140476264067861L;

	private String descricao;
	private String cpfCnpj;
	private String nomePessoa;
	private DateTime dataInicioGrupo;
	private DateTime dataInicioPessoaGrupo;
	private DateTime dataFimPessoaGrupo;
	private String origem;
	private String usuarioResponsavel;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public DateTime getDataInicioGrupo() {
		return dataInicioGrupo;
	}

	public void setDataInicioGrupo(DateTime dataInicioGrupo) {
		this.dataInicioGrupo = dataInicioGrupo;
	}

	public DateTime getDataInicioPessoaGrupo() {
		return dataInicioPessoaGrupo;
	}

	public void setDataInicioPessoaGrupo(DateTime dataInicioPessoaGrupo) {
		this.dataInicioPessoaGrupo = dataInicioPessoaGrupo;
	}

	public DateTime getDataFimPessoaGrupo() {
		return dataFimPessoaGrupo;
	}

	public void setDataFimPessoaGrupo(DateTime dataFimPessoaGrupo) {
		this.dataFimPessoaGrupo = dataFimPessoaGrupo;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(String usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

}
