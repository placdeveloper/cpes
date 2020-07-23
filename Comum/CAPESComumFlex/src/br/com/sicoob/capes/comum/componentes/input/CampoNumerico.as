package br.com.sicoob.capes.comum.componentes.input {
	import br.com.bancoob.componentes.campos.CampoNumerico;
	
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	
	/**
	 * Campo numérico.
	 * 
	 * Foi sobreescrito apenas para remover o valor do campo caso a validação falhe.
	 * 
	 * @author bruno.carneiro
	 */
	public class CampoNumerico extends br.com.bancoob.componentes.campos.CampoNumerico {
		
		/**
		 * Construtor
		 */
		public function CampoNumerico(){
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método de inicialização.
		 **/
		public function inicializar(event:Event):void {
			
		}
		
		/**
		 * @inheritDoc
		 */
		public override function validarNumero():Boolean {
			var retorno:Boolean = super.validarNumero();
			if(!retorno) {
				this.text = "";
			}
			return retorno;
		}
	}
}