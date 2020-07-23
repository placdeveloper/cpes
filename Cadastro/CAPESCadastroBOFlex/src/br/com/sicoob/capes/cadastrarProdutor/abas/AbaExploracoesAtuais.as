package br.com.sicoob.capes.cadastrarProdutor.abas
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.grid.Grid;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	
	public class AbaExploracoesAtuais extends AbaExploracoesAtuaisView {	
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */		
		public function AbaExploracoesAtuais()	{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);			
		}

	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent=null):void {
			
			obterGrid().dataProvider = new ArrayCollection();
			obterGrid().addEventListener(ListEvent.CHANGE, registroSelecionado);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		public function carregarExploracoes(lista:Object): void {
			obterGrid().dataProvider = lista;
			obterGrid().selectedIndex = -1;
		}	
		
		public function obterGrid():Grid {
			this.gridDados.validateNow();
			return this.gridDados;
		}

	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------
		private function registroSelecionado(evt:ListEvent=null):void {
			this.dispatchEvent(evt);	
		}	
	}
}