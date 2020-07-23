package br.com.sicoob.capes.comum.enums {
	import flash.utils.IDataInput;
	import flash.utils.IExternalizable;
	
	import org.granite.util.Enum;
	
	[Bindable]
	[RemoteClass(alias="br.com.sicoob.capes.cadastro.negocio.enums.TipoCapturaEnum")]
	public class TipoCapturaEnum extends Enum implements IExternalizable {
		
		public static const AUTOMATICA : TipoCapturaEnum 		= new TipoCapturaEnum("AUTOMATICA", 1, "Automática", _); 
		public static const SEMI_AUTOMATICA : TipoCapturaEnum 	= new TipoCapturaEnum("SEMI_AUTOMATICA", 2, "Semi-automática", _); 
		public static const MANUAL : TipoCapturaEnum 			= new TipoCapturaEnum("MANUAL", 3, "Manual", _); 
		
		private var _codigo:Number;
		private var _descricao:String;
												
		function TipoCapturaEnum(name:String, codigo:Number, descricao:String, restrictor:* = null) {
			super((name || AUTOMATICA.name), restrictor);
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
		
		public static function get constants():Array {
			return [AUTOMATICA, SEMI_AUTOMATICA, MANUAL];
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:TipoCapturaEnum = valueOf(name);
			_codigo = constantObject.codigo;
			_descricao = constantObject.descricao;
		}
		
		public static function valueOf(name:String):TipoCapturaEnum {
			return TipoCapturaEnum(AUTOMATICA.constantOf(name));
		}
	}
}