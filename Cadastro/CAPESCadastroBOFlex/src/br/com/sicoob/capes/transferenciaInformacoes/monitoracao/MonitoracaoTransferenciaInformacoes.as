package br.com.sicoob.capes.transferenciaInformacoes.monitoracao {
	import mx.events.FlexEvent;

	public class MonitoracaoTransferenciaInformacoes extends MonitoracaoTransferenciaInformacoesView {
		
		public function MonitoracaoTransferenciaInformacoes() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evento:FlexEvent):void {
			this.navegacaoTab.selectedIndex = 0;
		} 
		
	}
}