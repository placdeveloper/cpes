package br.com.sicoob.capes.cadastrarContato.abas
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.sicoob.capes.comum.vo.entidades.EmailVO;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	
	public class abaListarEmail extends abaListarEmailView {	
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */		
		public function abaListarEmail()	{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);			
		}

	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent=null):void {
			
			obterGrid().dataProvider = new ArrayCollection();
			obterGrid().addEventListener(ListEvent.CHANGE, registroSelecionado);
			obterGrid().addEventListener(ListEvent.ITEM_DOUBLE_CLICK, duploClique);
			obterGrid().doubleClickEnabled = true;			
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		public function carregarEmails(listaEmail:Object): void {
			obterGrid().dataProvider = listaEmail;
			obterGrid().selectedIndex = -1;
		}	
		
		public function obterEmailSelecionado(): EmailVO {
			var itemLista:Object = obterGrid().selectedItem;
			return EmailVO(itemLista);
		}
		
		public function obterGrid():Grid {
			this.gridDados.validateNow();
			return this.gridDados;
		}

	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------
		private function registroSelecionado(evt:ListEvent=null):void {
			this.dispatchEvent(new Event(Modulo.REGISTRO_SELECIONADO));	
		}	
		
		private function duploClique(evt:ListEvent=null):void {
			this.dispatchEvent(new Event(ListEvent.ITEM_DOUBLE_CLICK));	
		}		
	}
}