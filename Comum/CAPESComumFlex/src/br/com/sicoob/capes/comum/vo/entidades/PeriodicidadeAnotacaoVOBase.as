/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Mon Jan 17 12:52:22 GMT 2011.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (PeriodicidadeAnotacaoVO.as).
 */

package br.com.sicoob.capes.comum.vo.entidades {

    import br.com.sicoob.capes.comum.vo.entidades.CAPESEntidadeVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class PeriodicidadeAnotacaoVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;

        private var _descPeriodicidadeAnotacao:String;
        private var _idPeriodicidadeAnotacao:Number;

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is PeriodicidadeAnotacaoVO) || (property as PeriodicidadeAnotacaoVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }

        public function set descPeriodicidadeAnotacao(value:String):void {
            _descPeriodicidadeAnotacao = value;
        }
        public function get descPeriodicidadeAnotacao():String {
            return _descPeriodicidadeAnotacao;
        }

        public function set idPeriodicidadeAnotacao(value:Number):void {
            _idPeriodicidadeAnotacao = value;
        }
        public function get idPeriodicidadeAnotacao():Number {
            return _idPeriodicidadeAnotacao;
        }

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
                _descPeriodicidadeAnotacao = input.readObject() as String;
                _idPeriodicidadeAnotacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
            else {
                _idPeriodicidadeAnotacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
            output.writeObject(_descPeriodicidadeAnotacao);
            output.writeObject(_idPeriodicidadeAnotacao);
            }
            else {
                output.writeObject(_idPeriodicidadeAnotacao);
            }
        }
    }
}