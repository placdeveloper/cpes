package br.com.sicoob.capes.api.negocio.filtros;

public class FiltroProdutor extends FiltroConsultaAPIAbstrato {
	private static final long serialVersionUID = -7127122218907665372L;

	/** O atributo pesquisar por categoria ativa. */
	private boolean pesquisarPorCategoriaAtiva = Boolean.FALSE;

	public boolean isPesquisarPorCategoriaAtiva() {
		return pesquisarPorCategoriaAtiva;
	}

	public void setPesquisarPorCategoriaAtiva(boolean pesquisarPorCategoriaAtiva) {
		this.pesquisarPorCategoriaAtiva = pesquisarPorCategoriaAtiva;
	}

}
