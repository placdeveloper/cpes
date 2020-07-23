/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Thu Sep 15 16:37:21 BRT 2011.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (UnidadeInstituicaoVO.as).
 */

package br.com.sicoob.capes.comum.vo{

    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;

    [Bindable]
    public class UnidadeInstituicaoVOBase implements IExternalizable {

        private var _descricao:String;
        private var _id:Number;
        private var _idInstituicao:Number;

        public function set descricao(value:String):void {
            _descricao = value;
        }
        public function get descricao():String {
            return _descricao;
        }

        public function set id(value:Number):void {
            _id = value;
        }
        public function get id():Number {
            return _id;
        }
		
		public function get codigoDescricao():String {
			return id + " - " + descricao;
		}

        public function set idInstituicao(value:Number):void {
            _idInstituicao = value;
        }
        public function get idInstituicao():Number {
            return _idInstituicao;
        }

        public function readExternal(input:IDataInput):void {
	        _descricao = input.readObject() as String;
            _id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_descricao);
            output.writeObject(_id);
            output.writeObject(_idInstituicao);
        }
    }
}