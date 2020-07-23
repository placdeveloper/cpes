package br.com.sicoob.capes.cadastrarProdutor.janelas
{
	import br.com.bancoob.componentes.Modulo;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	
	public class FinalizarExploracao extends FinalizarExploracaoView
	{
	
		public static const FINALIZAR_EXPLORACAO:String = "finalizarExploracao";
				
		public function FinalizarExploracao() {
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void {

			botFechar.addEventListener(MouseEvent.CLICK, fechar);
			botOk.addEventListener(MouseEvent.CLICK, okClicado);
			rdbSimNao.addEventListener(Event.CHANGE, habilitarCampos);
			habilitarCampos();
		}
		
		public function iniciar(): void {
			rdbSimNao.selectedValue = false;
			habilitarCampos();
		}

		public function habilitarCampos(evt:Event=null) : void {
			
			var houveFrustracao: Boolean = rdbSimNao.selectedValue as Boolean;
			txtPercentual.enabled = houveFrustracao;
			txtPercentual.validarObrigatorio = houveFrustracao;
			txtDataOcorrencia.enabled = houveFrustracao;
			txtDataOcorrencia.validarObrigatorio = houveFrustracao;
			
			if(!houveFrustracao) {
				rdbSimNao.selectedValue = false;
				txtPercentual.text = "";
				txtDataOcorrencia.selectedDate = null;
			}
		}
		
	    private function fechar(evt:MouseEvent=null):void {
			this.fecharJanela();			
		}

		private function okClicado(evt:MouseEvent=null):void {
			executarSeValido(finalizar);	
		}
		
	    private function finalizar():void {
	    	this.dispatchEvent(new Event(FINALIZAR_EXPLORACAO));	
			this.fecharJanela();			
		}
	}
}