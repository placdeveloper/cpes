package br.com.sicoob.capes.monitoracaoFilasCapes.janelas
{
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataData;
	
	import mx.events.FlexEvent;
			
	public class CanvasIndicador extends CanvasIndicadorView
	{
		public function CanvasIndicador() {
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);	
		}
			
		public static const COR_ALERTA:Number = 0xFFCC00;
		public static const COR_ERRO:Number = 0x990000;
		public static const COR_SUCESSO:Number = 0x00B21D;
		
		public static const NIVEL_NORMAL:int = 1; 
		public static const NIVEL_ATENCAO:int = 2;
		public static const NIVEL_CRITICO:int = 3;			
		
		public function init(evt:FlexEvent):void {
		}
				
		public function carregarDados(obj:Object):void {
			
			if ( obj != null ) {
				this.labelNomeFila.text = obj["nomeFila"];
				this.labelNumeroMensagens.text = obj["quantidadeMensagens"];
				this.labelDataPrimeira.data = formatarData(obj["dataPrimeira"]);
				this.labelDataUltima.data = formatarData(obj["dataUltima"]);
				
				alertar(obj["nivelAlerta"]);
			}
		}		
			
		private function formatarData(valor: Date): String {
			return FormataData.formataDataHora(valor is IDateTime ? (valor as IDateTime).data : valor as Date);
		}			
			
		private function alertar(nivel: int):void {
			
			var cor:Number = COR_ERRO;
			
			switch ( nivel ) {
				case NIVEL_ATENCAO :
					cor = COR_ALERTA;
					break;
				case NIVEL_CRITICO :
					cor = COR_ERRO;
					break;
				case NIVEL_NORMAL :
					cor = COR_SUCESSO;
					break;
			}
			
			canvasIndicador.setStyle("backgroundColor", cor);
		}
		
	}
}