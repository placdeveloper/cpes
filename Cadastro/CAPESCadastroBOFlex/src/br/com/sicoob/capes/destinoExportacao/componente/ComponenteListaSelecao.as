package br.com.sicoob.capes.destinoExportacao.componente {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.listaSelecao.ListaSelecaoView;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.entidades.TipoInformacaoVO;
	
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.controls.List;
	
	public class ComponenteListaSelecao	extends ListaSelecaoView {
		
		public function ComponenteListaSelecao() {
			super();
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoInformacao", TipoInformacaoVO);
		}
		
		protected override function removerItem():void {
			var item:TipoInformacaoVO = obterItemSelecionadoListaItensSelecionados();
			
			if(item){
				if(!item.informacaoObrigatoriaExportacao.valor) {
					super.removerItem();
				} else {
					exibirMensagem("O parâmetro não pode ser removido da lista pois é obrigatório.");
				}
			}
		}
		
		protected override function removerTodos():void {
			var deveExibirMensagem:Boolean = false;
			for(var i:int = this.listaItensSelecionados.dataProvider.length-1; i >= 0; i--){
				var item:TipoInformacaoVO = this.listaItensSelecionados.dataProvider[i];
				if(item && !item.informacaoObrigatoriaExportacao.valor) {
					this.adicionarItemListaSelecao(this.listaItensSelecionados.dataProvider[i]);
					this.removerItemListaItensSelecionados(this.listaItensSelecionados.dataProvider[i]);
					this.selecionaPrimeiroItem(this.listaSelecao);
				}else {
					deveExibirMensagem = true;
				}
			}
			
			if(deveExibirMensagem){
				exibirMensagem("Foram removidos apenas o(s) parâmetro(s) não obrigatório(s).");
			}
		}
		
		private function adicionarItemListaSelecao(item:Object):void {
			ArrayCollection(this.listaSelecao.dataProvider).addItem(item);
		}
		
		private function removerItemListaItensSelecionados(item:Object):void {
			ArrayCollection(this.listaItensSelecionados.dataProvider).removeItemAt(ArrayCollection(this.listaItensSelecionados.dataProvider).getItemIndex(item));
		}
		
		private function selecionaPrimeiroItem(lista:List):void {
			if(lista.dataProvider.length > 0){
				lista.selectedItem = lista.dataProvider[0];
			}
		}
		
		private function obterItemSelecionadoListaItensSelecionados():TipoInformacaoVO {
			return this.listaItensSelecionados.selectedItem as TipoInformacaoVO;
		}
		
		protected override function montaLista(resultado:Object):void {
			this.listaSelecao.dataProvider = resultado.dados[this.nomeLista];
			if (nomeListaNaoSelecionado != ""){
				listaItensSelecionados.dataProvider = obterListaObrigatorios(resultado);
			}
		}
		
		private function obterListaObrigatorios(resultado:Object):ArrayCollection {
			var listaSelecionados:ArrayCollection = new ArrayCollection();
			FlexUtil.clonarLista(listaSelecionados, resultado.dados[this.nomeListaNaoSelecionado]);
			
			var lista:ArrayCollection = resultado.dados[this.nomeLista];
			
			for each(var item:TipoInformacaoVO in lista) {
				if(item && item.informacaoObrigatoriaExportacao.valor && !listaSelecionados.contains(item)) {
					listaSelecionados.addItem(item);
				}
			}
			return listaSelecionados;
		}
		
		private function exibirMensagem(mensagem:String):void {
			Alerta.show(mensagem, "Aviso", Alerta.ALERTA_INFORMACAO);
		}
	}
}