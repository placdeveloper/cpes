package br.com.sicoob.capes.fichaCadastral{

	import flash.events.Event;
	
	public class frmFichaCadastralNova extends frmFichaCadastralNovaView  {
				
		public function fechar(event:Event):void {
			navegacaoTab.selectedIndex = 0;
		}		
	}
}