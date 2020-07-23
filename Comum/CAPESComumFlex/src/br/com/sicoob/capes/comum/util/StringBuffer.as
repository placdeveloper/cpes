package br.com.sicoob.capes.comum.util {
	
	/**
	 * Classe utilitária para armazenar/concatenar textos.
	 * 
	 * Equivalente à classe de mesmo nome no JAVA.
	 * 
	 * Utilização:
	 * 
	 * <pre>
	 * var retorno:StringBuffer = new StringBuffer();
	 * retorno.append("texto");
	 * retorno.append("concatenado");
	 * 
	 * trace(retorno.toString());
	 * </pre>
	 */
	public class StringBuffer {
		
		private var buffer:Array = new Array();
		
		/**
		 * Acrescenta um texto à instância.
		 * 
		 * @param texto
		 * 			O texto para ser acrescentado à instância.
		 * 
		 */
		public function append(texto:String):void {
			for (var i:Number = 0; i < texto.length; i++) {
				buffer.push(texto.charCodeAt(i));
			}
		}
		
		/**
		 * Retorna o texto total concatenado.
		 * 
		 * @return <code>String</code> o texto concatenado.
		 */
		public function toString():String {
			return String.fromCharCode.apply(this, buffer);
		}
	}

}