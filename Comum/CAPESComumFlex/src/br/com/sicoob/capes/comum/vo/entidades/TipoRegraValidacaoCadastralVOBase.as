package br.com.sicoob.capes.comum.vo.entidades{
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import org.granite.collections.IPersistentCollection;
	import org.granite.meta;

	use namespace meta;
	
	[Bindable]
	public class TipoRegraValidacaoCadastralVOBase extends CAPESEntidadeVO{
		private var __laziness:String = null;
		
		private var _codigo:String;
		private var _descricao:String;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name)
				return __laziness === null;
			
			var property:* = this[name];
			return (
				(!(property is TipoRegraValidacaoCadastralVOBase) || (property as TipoRegraValidacaoCadastralVOBase).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
			);
		}

		public function get descricao():String{
			return _descricao;
		}

		public function set descricao(value:String):void{
			_descricao = value;
		}

		public function get codigo():String	{
			return _codigo;
		}

		public function set codigo(value:String):void {
			_codigo = value;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_codigo = input.readObject() as String;
				_descricao = input.readObject() as String;
			}
			else {
				_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_codigo);
				output.writeObject(_descricao);
			}
			else {
				output.writeObject(_codigo);
			}
		}
		
	}
}