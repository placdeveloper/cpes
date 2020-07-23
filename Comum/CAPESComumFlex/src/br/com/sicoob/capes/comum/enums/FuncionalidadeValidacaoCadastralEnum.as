package br.com.sicoob.capes.comum.enums{
	import flash.utils.IDataInput;
	
	import org.granite.util.Enum;
	
	[Bindable]
	[RemoteClass(alias="br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum")]
	public class FuncionalidadeValidacaoCadastralEnum extends Enum{
		
		public static const BEM : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("BEM", "Bem", _);
		public static const CERTIDAO : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("CERTIDAO", "Certidão", _);
		public static const EMAIL : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("EMAIL", "E-Mail", _);
		public static const ENDERECO : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("ENDERECO", "Endereço", _);
		public static const FONTE_RENDA : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("FONTE_RENDA", "Fonte de Renda", _);
		public static const INFO_PROFISSIONAL : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("INFO_PROFISSIONAL", "Informação Profissional", _);
		public static const PESSOA : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("PESSOA", "Pessoa", _);
		public static const PESSOA_INSTITUICAO : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("PESSOA_INSTITUICAO", "Dados do Cliente", _);
		public static const PRODUTOR : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("PRODUTOR", "Produtor", _);
		public static const REFERENCIA : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("REFERENCIA", "Referência", _);
		public static const RELACIONAMENTO : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("RELACIONAMENTO", "Relacionamento", _);
		public static const TELEFONE : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("TELEFONE", "Telefone", _);
		public static const TRIBUTACAO : FuncionalidadeValidacaoCadastralEnum = new FuncionalidadeValidacaoCadastralEnum("TRIBUTACAO", "Tributação", _);

		private var _descricao : String;
		
		function FuncionalidadeValidacaoCadastralEnum(name:String = null, descricao:String = null, restrictor:* = null){
			super((name || BEM.name), restrictor);
			_descricao = descricao;
		}

		public function get descricao():String{
			return _descricao;
		}
		
		public static function get constants():Array {
			return [BEM,CERTIDAO,EMAIL,ENDERECO,FONTE_RENDA,INFO_PROFISSIONAL,PESSOA,PESSOA_INSTITUICAO,PRODUTOR,REFERENCIA,RELACIONAMENTO,TELEFONE,TRIBUTACAO];
		}
		
		protected override function getConstants():Array {
			return constants;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:FuncionalidadeValidacaoCadastralEnum = valueOf(name);
			_descricao = constantObject.descricao;
		}
		
		public static function valueOf(name:String):FuncionalidadeValidacaoCadastralEnum {
			for each (var value:* in constants){
				var funcionalidade : FuncionalidadeValidacaoCadastralEnum = FuncionalidadeValidacaoCadastralEnum(value);
				if (value.name == name) {
					return value;
				}
			}
			return null;
		}
	}
}