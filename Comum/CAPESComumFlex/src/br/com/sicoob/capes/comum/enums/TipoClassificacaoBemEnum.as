package br.com.sicoob.capes.comum.enums {
	
	import flash.utils.IDataInput;
	
	import org.granite.util.Enum;
	
	/**
	 * Enum que representa o tipo de classificação dos bens.
	 * 
	 * @author bruno.carneiro
	 */
	[Bindable]
	[RemoteClass(alias = "br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum")]
	public class TipoClassificacaoBemEnum extends Enum {
		
		public static const BEM_IMOVEL:TipoClassificacaoBemEnum = new TipoClassificacaoBemEnum("BEM_IMOVEL", 1, "Bem Imóvel", _);
		public static const BEM_MOVEL:TipoClassificacaoBemEnum = new TipoClassificacaoBemEnum("BEM_MOVEL", 2, "Bem Móvel", _);
		
		private var _codigo:Number;
		private var _descricao:String;
		
		/**
		 * Construtor.
		 */
		function TipoClassificacaoBemEnum(name:String = null, codigo:Number = 0, descricao:String = null, restrictor:* = null) {
			super((name || BEM_IMOVEL.name), restrictor);
			if (restrictor != null) {
				this._codigo = codigo;
				this._descricao = descricao;
			}
		}
		
		/**
		 * Obtém o valor do código.
		 */
		public function get codigo():Number {
			return _codigo;
		}
		
		/**
		 * Obtém o valor da descrição.
		 */
		public function get descricao():String {
			return _descricao;
		}
		
		public static function get constantes():Array {
			return [BEM_IMOVEL, BEM_MOVEL];
		}
		
		protected override function getConstants():Array {
			return constantes;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var objeto:TipoClassificacaoBemEnum = valueOf(name);
			_codigo = objeto.codigo;
			_descricao = objeto.descricao;
		}
		
		public static function valueOf(name:String):TipoClassificacaoBemEnum {
			return TipoClassificacaoBemEnum(BEM_IMOVEL.constantOf(name));
		}
		
		/**
		 * Obtém o enum à partir do código informado.
		 */
		public static function obterPorCodigo(codigo:Number):TipoClassificacaoBemEnum {
			var retorno:TipoClassificacaoBemEnum = null;
			
			for each(var tipoClassificacaoBem:TipoClassificacaoBemEnum in constantes){
				if(tipoClassificacaoBem._codigo == codigo){
					retorno = tipoClassificacaoBem;
				}
			}
			return retorno;
		}
		
	}
}