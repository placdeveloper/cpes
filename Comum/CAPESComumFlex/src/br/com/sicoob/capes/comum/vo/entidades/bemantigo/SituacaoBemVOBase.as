/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Thu Apr 28 10:13:59 BRT 2011.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (SituacaoBemVO.as).
 */

package br.com.sicoob.capes.comum.vo.entidades.bemantigo {

    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    import br.com.sicoob.capes.comum.vo.entidades.CAPESEntidadeVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import mx.collections.ListCollectionView;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class SituacaoBemVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;

        private var _cadastraOnus:Booleano;
        private var _codigo:Number;
        private var _descricao:String;
        private var _subtipos:ListCollectionView;

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is SituacaoBemVO) || (property as SituacaoBemVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }

        public function set cadastraOnus(value:Booleano):void {
            _cadastraOnus = value;
        }
        public function get cadastraOnus():Booleano {
            return _cadastraOnus;
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

        public function set subtipos(value:ListCollectionView):void {
            _subtipos = value;
        }
        public function get subtipos():ListCollectionView {
            return _subtipos;
        }

        public function get codigoListaItem():String {
            return null;
        }

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
            	_cadastraOnus = SerializacaoObjetos.lerBooleano(input);
                _codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _descricao = input.readObject() as String;
                _subtipos = input.readObject() as ListCollectionView;
            }
            else {
                _codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
	        	SerializacaoObjetos.escreverBooleano(_cadastraOnus, output);
	            output.writeObject(_codigo);
	            output.writeObject(_descricao);
	            output.writeObject(_subtipos);
            } else {
                output.writeObject(_codigo);
            }
        }
    }
}