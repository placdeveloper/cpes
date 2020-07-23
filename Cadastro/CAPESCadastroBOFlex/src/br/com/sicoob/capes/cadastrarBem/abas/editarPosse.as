package br.com.sicoob.capes.cadastrarBem.abas{
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.events.FlexEvent;

	public class editarPosse extends editarPosseView {
		
		public function editarPosse() {
			addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		private function inicializar(evento:FlexEvent):void {
			areaPosse.addEventListener(FocusEvent.FOCUS_OUT, verificarCampo);
		}
		
		private function verificarCampo(evento:Event):void {
			var valor:Number = areaPosse.valor;
			if(!areaPosse.validar() || valor < areaPosse.valorMinimo || valor > areaPosse.valorMaximo) {
				areaPosse.text = "";
			}
		}

	}
}