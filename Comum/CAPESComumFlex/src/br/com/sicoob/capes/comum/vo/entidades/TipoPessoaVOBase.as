/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Mon Aug 23 18:35:04 GMT 2010.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (TipoPessoaVO.as).
 */

package br.com.sicoob.capes.comum.vo.entidades {

    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class TipoPessoaVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;

        private var _codTipoPessoa:Number;
        private var _descTipoPessoa:String;

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is TipoPessoaVO) || (property as TipoPessoaVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }

        public function set codTipoPessoa(value:Number):void {
            _codTipoPessoa = value;
        }
        public function get codTipoPessoa():Number {
            return _codTipoPessoa;
        }

        public function set descTipoPessoa(value:String):void {
            _descTipoPessoa = value;
        }
        public function get descTipoPessoa():String {
            return _descTipoPessoa;
        }

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
                _codTipoPessoa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _descTipoPessoa = input.readObject() as String;
            }
            else {
                _codTipoPessoa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
            output.writeObject(_codTipoPessoa);
            output.writeObject(_descTipoPessoa);
            }
            else {
                output.writeObject(_codTipoPessoa);
            }
        }
    }
}