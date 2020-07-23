package br.com.sicoob.capes.comum.enums 
{
	
	import flash.utils.IDataInput;
	
	import org.granite.util.Enum;
	
	[Bindable]
	[RemoteClass(alias="br.com.sicoob.capes.cadastro.negocio.enums.EstadoCivilEnum")]
	public class EstadoCivilEnum extends Enum {
		
		public static const SOLTEIRO : EstadoCivilEnum = new EstadoCivilEnum("SOLTEIRO", 1, "SOLTEIRO(A)", _);
		public static const CASADO : EstadoCivilEnum = new EstadoCivilEnum("CASADO", 2, "CASADO(A)", _);
		public static const VIUVO : EstadoCivilEnum = new EstadoCivilEnum("VIUVO", 3, "VIÚVO(A)", _);
		public static const DIVORCIADO : EstadoCivilEnum = new EstadoCivilEnum("DIVORCIADO", 5, "DIVORCIADO(A)", _);
		public static const SEPARADO : EstadoCivilEnum = new EstadoCivilEnum("SEPARADO", 6, "SEPARADO(A)", _);
		public static const UNIAO_ESTAVEL : EstadoCivilEnum = new EstadoCivilEnum("UNIAO_ESTAVEL", 7, "UNIÃO ESTÁVEL", _);
		
		private var _codigo				:Number;
		private var _descricao			:String;
		
		function EstadoCivilEnum(name:String = null, codigo:Number = 0, descricao:String = null, restrictor:* = null) {
			super((name || SOLTEIRO.name), restrictor);
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
			return [SOLTEIRO, CASADO, VIUVO, DIVORCIADO, SEPARADO, UNIAO_ESTAVEL];
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:EstadoCivilEnum = valueOf(name);
			_codigo = constantObject._codigo;
			_descricao = constantObject.descricao;
		}
		
		public static function valueOf(name:String):EstadoCivilEnum {
			return EstadoCivilEnum(SOLTEIRO.constantOf(name));
		}
	}
}