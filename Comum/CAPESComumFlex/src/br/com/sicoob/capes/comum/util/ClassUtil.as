package br.com.sicoob.capes.comum.util {
	
	import flash.utils.getQualifiedClassName;
	
	public class ClassUtil {
		
		/**
		 * Método responsável por obter o Retorna o nome de classe totalmente qualificado de um objeto 
		 * 
		 * @param value - O objeto pelo qual é desejado o nome de classe totalmente qualificado. Qualquer valor do ActionScript pode ser transmitido a esse método, inclusive todos os tipos, ocorrências de objeto, tipos primitivos do ActionScript disponíveis, como uint e objetos de classe.
		 * 
		 * @return ServicoJava
		 */
		public static function className(value:*):String {
			return flash.utils.getQualifiedClassName(value).replace("::",".");
		}
		
		/**
		 * Método responsável por obter o Retorna o nome de classe totalmente qualificado de um objeto 
		 * 
		 * @param value - O objeto pelo qual é desejado o nome de classe totalmente qualificado. Qualquer valor do ActionScript pode ser transmitido a esse método, inclusive todos os tipos, ocorrências de objeto, tipos primitivos do ActionScript disponíveis, como uint e objetos de classe.
		 * 
		 * @return ServicoJava
		 */
		public static function simpleClassName(value:*):String {
			var fullName:String = className(value);
			var lastIndex:int = fullName.lastIndexOf(".");
			if(lastIndex > 0) {
				return fullName.substr(lastIndex);
			}
			return fullName;
		}
		
		
	}
	
}