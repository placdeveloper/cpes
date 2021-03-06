/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Wed Oct 19 14:45:34 BRST 2011.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (OcupacaoProfissionalVO.as).
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
    public class OcupacaoProfissionalVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;

        private var _ativo:Booleano;
        private var _codigo:String;
        private var _codigoPai:String;
        private var _codigoTipoOcupacao:Number;
        private var _descricao:String;
        private var _idOcupacaoProfissional:Number;

         meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is OcupacaoProfissionalVO) || (property as OcupacaoProfissionalVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }

        public function set ativo(value:Booleano):void {
            _ativo = value;
        }
        public function get ativo():Booleano {
            return _ativo;
        }

        public function set codigo(value:String):void {
            _codigo = value;
        }
        public function get codigo():String {
            return _codigo;
        }

        public function set codigoPai(value:String):void {
            _codigoPai = value;
        }
        public function get codigoPai():String {
            return _codigoPai;
        }

        public function set codigoTipoOcupacao(value:Number):void {
            _codigoTipoOcupacao = value;
        }
        public function get codigoTipoOcupacao():Number {
            return _codigoTipoOcupacao;
        }

        public function set descricao(value:String):void {
            _descricao = value;
        }
        public function get descricao():String {
            return _descricao;
        }

        public function set idOcupacaoProfissional(value:Number):void {
            _idOcupacaoProfissional = value;
        }
        public function get idOcupacaoProfissional():Number {
            return _idOcupacaoProfissional;
        }

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
            	_ativo = SerializacaoObjetos.lerBooleano(input);
                _codigo = input.readObject() as String;
                _codigoPai = input.readObject() as String;
                _codigoTipoOcupacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _descricao = input.readObject() as String;
                _idOcupacaoProfissional = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
            else {
                _idOcupacaoProfissional = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
        	SerializacaoObjetos.escreverBooleano(_ativo, output);
            output.writeObject(_codigo);
            output.writeObject(_codigoPai);
            output.writeObject(_codigoTipoOcupacao);
            output.writeObject(_descricao);
            output.writeObject(_idOcupacaoProfissional);
            }
            else {
                output.writeObject(_idOcupacaoProfissional);
            }
        }
    }
}