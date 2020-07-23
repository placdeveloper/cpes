package br.com.sicoob.capes.cadastrarBem.abas{
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.events.FlexEvent;

	public class editarRegistro extends editarRegistroView {
		
		public function editarRegistro() {
			addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		private function inicializar(evento:FlexEvent):void {
			qtdArea.addEventListener(FocusEvent.FOCUS_OUT, verificarCampo);
		}
		
		private function verificarCampo(evento:Event):void {
			var valor:Number = qtdArea.valor;
			if(!qtdArea.validar() || valor < qtdArea.valorMinimo || valor > qtdArea.valorMaximo) {
				qtdArea.text = "";
			}
		}
	}
}