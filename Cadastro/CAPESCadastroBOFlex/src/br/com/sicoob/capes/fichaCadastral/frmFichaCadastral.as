package br.com.sicoob.capes.fichaCadastral{

	import flash.events.Event;
	
	public class frmFichaCadastral extends frmFichaCadastralView  {
				
		public function fechar(event:Event):void {
			navegacaoTab.selectedIndex = 0;
			abaCadastro.limparForm();
			abaCadastroEmBranco.limparForm();			
		}		
	}
}