package br.com.sicoob.capes.comum.enums {
	
	import flash.utils.IDataInput;
	
	import org.granite.util.Enum;
	
	[Bindable]
	[RemoteClass(alias="br.com.sicoob.capes.cadastro.negocio.enums.TipoAplicacaoEnum")]
	public class TipoAplicacaoEnum extends Enum {
		
		public static const PESSOA_FISICA 	: TipoAplicacaoEnum = new TipoAplicacaoEnum("PESSOA_FISICA", 	1, "Pessoa Física", _);
		public static const PESSOA_JURIDICA : TipoAplicacaoEnum = new TipoAplicacaoEnum("PESSOA_JURIDICA", 	2, "Pessoa Jurídica", _);
		public static const AMBAS 			: TipoAplicacaoEnum = new TipoAplicacaoEnum("AMBAS", 			3, "Ambas", _);
		
		private var _idTipoAplicacao	:Number;
		private var _descricao			:String;
		
		function TipoAplicacaoEnum(name:String = null, idTipoAplicacao:Number = 0, descricao:String = null, restrictor:* = null) {
			super((name || PESSOA_FISICA.name), restrictor);
			if (restrictor != null) {
				this._idTipoAplicacao = idTipoAplicacao;
				this._descricao = descricao;
			}
		}
		
		public function get idTipoAplicacao():Number {
			return _idTipoAplicacao;
		}
		
		public function get descricao():String {
			return _descricao;
		}
		
		public static function get constants():Array {
			return [PESSOA_FISICA, PESSOA_JURIDICA, AMBAS];
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:TipoAplicacaoEnum = valueOf(name);
			_descricao = constantObject.descricao;
			_idTipoAplicacao = constantObject.idTipoAplicacao;
		}
		
		public static function valueOf(name:String):TipoAplicacaoEnum {
			return TipoAplicacaoEnum(PESSOA_FISICA.constantOf(name));
		}
	}
}