package br.com.sicoob.capes.comum.enums {
	
	import flash.utils.IDataInput;
	
	import org.granite.util.Enum;
	
	/**
	 * Enum que representa os tipos de localização do bem imóvel.
	 * 
	 * @author bruno.carneiro
	 */
	[Bindable]
	[RemoteClass(alias = "br.com.sicoob.capes.comum.negocio.enums.TipoLocalizacaoBemImovelEnum")]
	public class TipoLocalizacaoBemImovelEnum extends Enum {
		
		public static const URBANO:TipoLocalizacaoBemImovelEnum = new TipoLocalizacaoBemImovelEnum("URBANO", 1, "Urbano", _);
		public static const RURAL:TipoLocalizacaoBemImovelEnum = new TipoLocalizacaoBemImovelEnum("RURAL", 2, "Rural", _);
		
		private var _codigo:Number;
		private var _descricao:String;
		
		function TipoLocalizacaoBemImovelEnum(name:String = null, codigo:Number = 0, descricao:String = null, restrictor:* = null) {
			super((name || URBANO.name), restrictor);
			if (restrictor != null) {
				this._codigo = codigo;
				this._descricao = descricao;
			}
		}
		
		public function get codigo():Number {
			return _codigo;
		}
		
		public function get descricao():String {
			return _descricao;
		}
		
		public static function get constantes():Array {
			return [URBANO, RURAL];
		}
		
		protected override function getConstants():Array {
			return constantes;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var objeto:TipoLocalizacaoBemImovelEnum = valueOf(name);
			_codigo = objeto.codigo;
			_descricao = objeto.descricao;
		}
		
		public static function valueOf(name:String):TipoLocalizacaoBemImovelEnum {
			return TipoLocalizacaoBemImovelEnum(URBANO.constantOf(name));
		}
		
		public static function obterPorCodigo(codigo:Number):TipoLocalizacaoBemImovelEnum {
			var retorno:TipoLocalizacaoBemImovelEnum = null;
			
			for each(var tipoLocalizacaoBem:TipoLocalizacaoBemImovelEnum in constantes){
				if(tipoLocalizacaoBem._codigo == codigo){
					retorno = tipoLocalizacaoBem;
				}
			}
			return retorno;
		}
	}
}