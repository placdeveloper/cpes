package br.com.sicoob.capes.relatorio.negocio.relatorios;

public enum Relatorios {

	//@formatter:off
	GRUPO_ECONOMICO_VIGENTE		(25,"/grupoEconomicoNovo/RelatorioGrupoEconomicoVigente.jasper"), 
	GRUPO_ECONOMICO_HISTORICO	(25,"/grupoEconomicoNovo/RelatorioGrupoEconomicoHistorico.jasper");
	//@formatter:on

	private final String nomeArquivo;
	private final Integer codigo;

	private Relatorios(Integer codigo, String nomeArquivo) {
		this.codigo = codigo;
		this.nomeArquivo = nomeArquivo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public Integer getCodigo() {
		return codigo;
	}

}
