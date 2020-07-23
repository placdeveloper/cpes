package br.com.sicoob.capes.comum.enums {
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IExternalizable;
	
	import org.granite.util.Enum;
	
	[Bindable]
	public class SituacaoRegistroEnum extends Enum implements IExternalizable {
		
		public static const VIGENTE : SituacaoRegistroEnum 			= new SituacaoRegistroEnum	("VIGENTE", _);
		public static const BLOQUEADO : SituacaoRegistroEnum 		= new SituacaoRegistroEnum	("BLOQUEADO", _);
		public static const EM_AUTORIZACAO : SituacaoRegistroEnum 	= new SituacaoRegistroEnum	("EM_AUTORIZACAO", _);
		public static const DEVOLVIDO : SituacaoRegistroEnum 		= new SituacaoRegistroEnum	("DEVOLVIDO", _);
		public static const A_ENCAMINHAR : SituacaoRegistroEnum 	= new SituacaoRegistroEnum	("A_ENCAMINHAR", _);
		
		function SituacaoRegistroEnum(name:String = null, restrictor:* = null) {
			super((name || VIGENTE.name), restrictor);
		}
		
		public static function get constants():Array {
			return [VIGENTE, BLOQUEADO, EM_AUTORIZACAO, DEVOLVIDO, A_ENCAMINHAR];
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
		}
		
		public static function valueOff(valor:Number):SituacaoRegistroEnum {
			if(valor && valor < constants.length){
				return constants[valor];
			}
			return VIGENTE;
		}
	}
}