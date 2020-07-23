package br.com.sicoob.capes.comum.util.janelas
{
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.managers.IFocusManagerContainer;

	public class AlertaConfirmacao extends AlertaConfirmacaoView implements IFocusManagerContainer
	{	
		private var _funcaoOk:Function;
		private var _mensagem:String;
			
	    //--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */
		public function AlertaConfirmacao(funcaoOk:Function, mensagem:String):void{

			super();
			
			_funcaoOk = funcaoOk;
			_mensagem = mensagem;
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init); //após criação configura componente
		}
		
		//configura dados do alerta
		private function init(evt:FlexEvent):void{
			
			txtMsg.text = _mensagem;
			
			//executa função passada como listener paga click no botão
			btSim.addEventListener(MouseEvent.CLICK, _funcaoOk);

			//adiciona listener para remover aletar ao clicar em algum botão
			btSim.addEventListener(MouseEvent.CLICK, fechar);
			btNao.addEventListener(MouseEvent.CLICK, fechar);
			btNao.setFocus();
		}		
		
	    private function fechar(evt:MouseEvent=null):void {
			this.fecharJanela();			
		}		
		
	}
}