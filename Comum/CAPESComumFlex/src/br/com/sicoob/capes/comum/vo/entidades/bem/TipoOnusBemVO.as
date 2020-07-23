package br.com.sicoob.capes.comum.vo.entidades.bem {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import br.com.sicoob.capes.comum.vo.entidades.CAPESEntidadeVO;
    
    import org.granite.meta;
    import org.granite.collections.IPersistentCollection;

    use namespace meta;

	/**
	 * Classe que representa os tipos de ônus do bem.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem", TipoOnusBemVO);
    public class TipoOnusBemVO extends CAPESEntidadeVO {
		private var __laziness:String = null;

		private var _codigo:Number;
		private var _descricao:String;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return ((!(property is TipoOnusBemVO) || (property as TipoOnusBemVO).meta::isInitialized()) &&
					(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized()));
		}
		
		public function get codigo():Number {
			return _codigo;
		}
		
		public function set codigo(valor:Number):void {
			this._codigo = valor;
		}
		
		public function get descricao():String {
			return _descricao;
		}
		
		public function set descricao(valor:String):void {
			this._descricao = valor;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_descricao = input.readObject() as String;
			}else {
				_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_codigo);
				output.writeObject(_descricao);
			}else {
				output.writeObject(_codigo);
			}
		}
    }
}