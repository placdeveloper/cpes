package br.com.sicoob.capes.comum.enums 
{
	
	import flash.utils.IDataInput;
	
	import org.granite.util.Enum;
	
	[Bindable]
	[RemoteClass(alias="br.com.sicoob.capes.comum.negocio.enums.TipoRelacionamentoPessoaEnum")]
	public class TipoRelacionamentoPessoaEnum extends Enum {
		
		public static const ADMINISTRADOR : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("ADMINISTRADOR", 1, "ADMINISTRADOR", _);
		public static const CONJUGE_COMPANHEIRO : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("CONJUGE_COMPANHEIRO", 2, "CÔNJUGE/COMPANHEIRO", _);
		public static const CURADOR : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("CURADOR", 3, "CURADOR", _);
		public static const INVENTARIANTE : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("INVENTARIANTE", 5, "INVENTARIANTE", _);
		public static const PROCURADOR : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("PROCURADOR", 6, "PROCURADOR", _);
		public static const REPRESENTANTE_LEGAL : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("REPRESENTANTE_LEGAL", 7, "REPRESENTANTE LEGAL", _);
		public static const RESPONSAVEL_LEGAL : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("RESPONSAVEL_LEGAL", 7, "RESPONSÁVEL LEGAL", _);
		public static const SOCIO : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("SOCIO", 8, "SÓCIO", _);
		public static const SOCIO_ADMINISTRADOR : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("SOCIO_ADMINISTRADOR", 9, "SÓCIO/ADMINISTRADOR", _);
		public static const TUTOR : TipoRelacionamentoPessoaEnum = new TipoRelacionamentoPessoaEnum("TUTOR", 10, "TUTOR", _);
		
		private var _codigo				:Number;
		private var _descricao			:String;
		
		function TipoRelacionamentoPessoaEnum(name:String = null, codigo:Number = 0, descricao:String = null, restrictor:* = null) {
			super((name || ADMINISTRADOR.name), restrictor);
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
			return [ADMINISTRADOR, CONJUGE_COMPANHEIRO, CURADOR, INVENTARIANTE, PROCURADOR, REPRESENTANTE_LEGAL, RESPONSAVEL_LEGAL, SOCIO, SOCIO_ADMINISTRADOR, TUTOR];
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:TipoRelacionamentoPessoaEnum = valueOf(name);
			_codigo = constantObject._codigo;
			_descricao = constantObject.descricao;
		}
		
		public static function valueOf(name:String):TipoRelacionamentoPessoaEnum {
			return TipoRelacionamentoPessoaEnum(ADMINISTRADOR.constantOf(name));
		}
	}
}