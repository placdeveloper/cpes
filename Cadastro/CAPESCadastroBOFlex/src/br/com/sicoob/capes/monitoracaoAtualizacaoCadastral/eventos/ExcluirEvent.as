package br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.eventos
{
	import flash.events.Event;
	
	public class ExcluirEvent extends Event
	{
		public static const EXCLUIR : String = "excluir";
		public static const CLICK : String = "excluirClicado";
		private var _id:Number;
		
		public function ExcluirEvent(tipo:String = CLICK, id:Number = NaN){
			super(tipo, false, false);
			_id = id;
		}
		
		public function get id():Number {
			return _id;
		}
		public function set id(value:Number):void {
			_id = value;
		}
	}
}