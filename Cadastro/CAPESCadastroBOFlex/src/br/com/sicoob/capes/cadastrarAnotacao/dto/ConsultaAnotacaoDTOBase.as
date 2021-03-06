/**
 * Generated by Gas3 v1.1.0 (Granite Data Services) on Mon Aug 23 18:35:05 GMT 2010.
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERRIDDEN EACH TIME YOU USE
 * THE GENERATOR. CHANGE INSTEAD THE INHERITED CLASS (ConsultaAnotacaoDTO.as).
 */

package br.com.sicoob.capes.cadastrarAnotacao.dto {

    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;

    [Bindable]
    public class ConsultaAnotacaoDTOBase implements IExternalizable {

        private var _anotacoesBaixadas:Booleano;
        private var _dataInicio:Date;
        private var _dataFim:Date;

        public function set anotacoesBaixadas(value:Booleano):void {
            _anotacoesBaixadas = value;
        }
        public function get anotacoesBaixadas():Booleano {
            return _anotacoesBaixadas;
        }

		public function set dataInicio(value:Date):void {
			_dataInicio = value;
		}
		public function get dataInicio():Date {
			return _dataInicio;
		}
		
		public function set dataFim(value:Date):void {
			_dataFim = value;
		}
		public function get dataFim():Date {
			return _dataFim;
		}
		
        public function readExternal(input:IDataInput):void {
            _anotacoesBaixadas = SerializacaoObjetos.lerBooleano(input);
            _dataInicio = SerializacaoObjetos.lerDate(input);
            _dataFim = SerializacaoObjetos.lerDate(input);
        }

        public function writeExternal(output:IDataOutput):void {
        	SerializacaoObjetos.escreverBooleano(_anotacoesBaixadas, output);
        	SerializacaoObjetos.escreverDate(_dataInicio, output);
        	SerializacaoObjetos.escreverDate(_dataFim, output);
        }
    }
}