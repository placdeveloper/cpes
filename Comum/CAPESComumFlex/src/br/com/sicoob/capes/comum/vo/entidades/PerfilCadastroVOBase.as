package br.com.sicoob.capes.comum.vo.entidades {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.meta;
    import org.granite.collections.IPersistentCollection;

    use namespace meta;

    [Bindable]
    public class PerfilCadastroVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;

        private var _codigo:Number;
        private var _descricao:String;
		private var _ordem:Number;

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is PerfilCadastroVO) || (property as PerfilCadastroVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }

        public function set codigo(value:Number):void {
            _codigo = value;
        }
        public function get codigo():Number {
            return _codigo;
        }

        public function set descricao(value:String):void {
            _descricao = value;
        }
        public function get descricao():String {
            return _descricao;
        }
		public function set ordem(value:Number):void {
			_ordem = value;
		}
		public function get ordem():Number {
			return _ordem;
		}
        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
                _codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _descricao = input.readObject() as String;
				_ordem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
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
				output.writeObject(_ordem);
            }
            else {
                output.writeObject(_codigo);
            }
        }
    }
}