package br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.eventos
{
	
	import br.com.sicoob.capes.comum.vo.entidades.MensagemReplicacaoVO;
	
	import flash.events.Event;

	public class ReprocessarEvent extends Event {
		
		public static const REPROCESSAR : String = "reprocessar";

		private var _mensagem : MensagemReplicacaoVO;
		
		public function ReprocessarEvent(mensagem : MensagemReplicacaoVO) {
			super(REPROCESSAR, false, false);
			_mensagem = mensagem;
		}
		
		public function get mensagem() : MensagemReplicacaoVO {
			return _mensagem;
		}
		private function set mensagem(value : MensagemReplicacaoVO) : void {
			_mensagem = value;
		}
	}
}