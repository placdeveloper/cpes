/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Thu Sep 15 10:23:35 BRT 2011.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (UFVO.as).
 */

package br.com.sicoob.capes.comum.vo.entidades {

    import br.com.bancoob.negocio.entidades.BancoobEmbeddableObject;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class UFVOBase extends BancoobEmbeddableObject {

        private var _idUF:Number;
        private var _nomeUF:String;
        private var _siglaUF:String;

        public function set idUF(value:Number):void {
            _idUF = value;
        }
        public function get idUF():Number {
            return _idUF;
        }

        public function set nomeUF(value:String):void {
            _nomeUF = value;
        }
        public function get nomeUF():String {
            return _nomeUF;
        }

        public function set siglaUF(value:String):void {
            _siglaUF = value;
        }
        public function get siglaUF():String {
            return _siglaUF;
        }

        override public function readExternal(input:IDataInput):void {
            _idUF = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
	        _nomeUF = input.readObject() as String;
	        _siglaUF = input.readObject() as String;
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(_idUF);
            output.writeObject(_nomeUF);
            output.writeObject(_siglaUF);
        }
    }
}