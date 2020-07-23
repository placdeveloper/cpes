package br.com.sicoob.capes.cadastrarProdutor.janelas
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.util.FormataNumero;
	import br.com.sicoob.capes.comum.vo.entidades.CategoriaProdutorVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	
	public class SugerirCategoriaProdutor extends SugerirCategoriaProdutorView
	{
			
		private var _categoriaSugerida: CategoriaProdutorVO = null;
		
		public function SugerirCategoriaProdutor() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void {

			grdCategorias.addEventListener(ListEvent.CHANGE, registroSelecionado);
			grdCategorias.doubleClickEnabled = false;
			
			botRejeitar.addEventListener(MouseEvent.CLICK, fechar);
			botFechar.addEventListener(MouseEvent.CLICK, fechar);
			botAceitar.addEventListener(MouseEvent.CLICK, aceitar);
		}
		
		private function registroSelecionado(evt:ListEvent=null):void {
			_categoriaSugerida = evt.target.selectedItem as CategoriaProdutorVO;
			botAceitar.enabled = true;
		}
		
		public function carregar(listaCategorias :ArrayCollection, rendaTotal: Number): void {
			grdCategorias.dataProvider = listaCategorias;
			txtCalcRendaApurada.text = FormataNumero.formata(rendaTotal,2,false);
		}
		
	    private function fechar(evt:MouseEvent=null):void {
			this.fecharJanela();			
		}

	    private function aceitar(evt:MouseEvent=null):void {
	    	this.dispatchEvent(new Event(Modulo.REGISTRO_SELECIONADO));	
			this.fecharJanela();			
		}
		
 		public function get categoriaSugerida():CategoriaProdutorVO { 
 			return _categoriaSugerida; 
 		}
	}
}