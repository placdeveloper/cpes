package br.com.sicoob.capes.cadastrarAnotacao.abas
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.sicoob.capes.comum.vo.entidades.AnotacaoVO;
	
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	
	public class AbaListaAnotacoesBaixadas extends AbaListaAnotacoesBaixadasView {
		
	    /**
	     *  Construtor.
	     */
		public function AbaListaAnotacoesBaixadas() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent=null):void {
			obterGrid().addEventListener(ListEvent.CHANGE, registroSelecionado);	
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		public function carregarAnotacoes(listaAnotacoes:Object): void {
			this.listaAnotacoes.dataProvider = null;
			this.listaAnotacoes.dataProvider = listaAnotacoes;
			this.listaAnotacoes.selectedIndex = -1;
		}		
		
		public function obterAnotacaoSelecionada(): AnotacaoVO {
			var itemLista:Object = listaAnotacoes.selectedItem;
			return AnotacaoVO(itemLista);
		}
		
		public function obterGrid():Grid {
			this.listaAnotacoes.validateNow();
			return this.listaAnotacoes;
		}	
		
	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------
		private function registroSelecionado(evt:ListEvent=null):void {
			this.dispatchEvent(new Event(Modulo.REGISTRO_SELECIONADO));	
		}		
			
	}

}