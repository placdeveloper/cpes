/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Mon Jan 17 15:40:49 GMT 2011.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (CampoTelaVO.as).
 */

package br.com.sicoob.capes.comum.vo {

    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;

    [Bindable]
    public class CampoTelaVOBase implements IExternalizable {

		private var _alterado:Boolean;
        private var _label:String;
		private var _ordenacao:Number;
        private var _valorAntigo:String
        private var _valorNovo:String

		public function set alterado(value:Boolean):void {
			_alterado = value;
		}
		public function get alterado():Boolean {
			return _alterado;
		}
		
        public function set label(value:String):void {
            _label = value;
        }
        public function get label():String {
            return _label;
        }
		
		public function set ordenacao(value:Number):void {
			_ordenacao = value;
		}
		public function get ordenacao():Number {
			return _ordenacao;
		}

        public function set valorAntigo(value:String):void {
            _valorAntigo = value;
        }
        public function get valorAntigo():String {
            return _valorAntigo;
        }

        public function set valorNovo(value:String):void {
            _valorNovo = value;
        }
        public function get valorNovo():String {
            return _valorNovo;
        }

        public function readExternal(input:IDataInput):void {
			_alterado = input.readObject() as Boolean;
	        _label = input.readObject() as String;
			_ordenacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
	        _valorAntigo = input.readObject() as String;
	        _valorNovo = input.readObject() as String;
        }

        public function writeExternal(output:IDataOutput):void {
			output.writeObject(_alterado);
            output.writeObject(_label);
            output.writeObject(_ordenacao);
            output.writeObject(_valorAntigo);
            output.writeObject(_valorNovo);
        }
    }
}