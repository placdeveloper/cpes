package br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel {
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	
	/**
	 * Evento para a tela de associação de parceiros.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class AssociarParceiroEvent extends ObjetoEvent {
		
		private var _edicao:Boolean;
		
		/**
		 * Construtor.
		 */
		public function AssociarParceiroEvent(type:String, objeto:Object=null, edicao:Boolean=false, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, objeto, bubbles, cancelable);
			this._edicao = edicao;
		}
		
		public function get edicao():Boolean {
			return _edicao;
		}
	}
}