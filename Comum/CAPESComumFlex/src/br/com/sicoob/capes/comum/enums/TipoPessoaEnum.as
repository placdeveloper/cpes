package br.com.sicoob.capes.comum.enums {
	
	import flash.utils.IDataInput;
	
	import org.granite.util.Enum;
	
	[Bindable]
	[RemoteClass(alias="br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum")]
	public class TipoPessoaEnum extends Enum {
		
		public static const PESSOA_FISICA 	: TipoPessoaEnum = new TipoPessoaEnum ("PESSOA_FISICA", 0, "Pessoa Física", "CADPFCOM", _);
		public static const PESSOA_JURIDICA : TipoPessoaEnum = new TipoPessoaEnum ("PESSOA_JURIDICA", 1, "Pessoa Jurídica", "CADPJCOM", _);
		
		private var _codigo				:Number;
		private var _descricao			:String;
		private var _siglaAssuntoGED	:String;
		
		function TipoPessoaEnum(name:String = null, codigo:Number = 0, descricao:String = null, siglaAssuntoGED:String = null, restrictor:* = null) {
			super((name || PESSOA_FISICA.name), restrictor);
			if (restrictor != null) {
				this._codigo = codigo;
				this._descricao = descricao;
				this._siglaAssuntoGED = siglaAssuntoGED
			}
		}
		
		public function get codigo():Number {
			return _codigo;
		}
		
		public function get descricao():String {
			return _descricao;
		}
		
		public function get siglaAssuntoGED():String {
			return _siglaAssuntoGED;
		}
		
		public static function get constants():Array {
			return [PESSOA_FISICA, PESSOA_JURIDICA];
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:TipoPessoaEnum = valueOf(name);
			_codigo = constantObject._codigo;
			_descricao = constantObject.descricao;
			_siglaAssuntoGED = constantObject._siglaAssuntoGED;
		}
		
		public static function valueOf(name:String):TipoPessoaEnum {
			return TipoPessoaEnum(PESSOA_FISICA.constantOf(name));
		}
	}
}