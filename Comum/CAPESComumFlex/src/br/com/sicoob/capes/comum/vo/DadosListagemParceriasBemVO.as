package br.com.sicoob.capes.comum.vo {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    
	/**
	 * Classe para os dados de listagem de bens.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemParceriasBemVO", DadosListagemParceriasBemVO);
    public class DadosListagemParceriasBemVO extends DadosListagemBemVO implements IExternalizable {

		private var _areaPosse:Number;
		private var _areaTotal:Number;
		private var _incra:String;
		private var _matricula:String;
		private var _municipio:String;
		private var _nirf:String;
		
		public function get areaPosse():Number {
			return _areaPosse;
		}
		
		public function set areaPosse(valor:Number):void {
			_areaPosse = valor;
		}
		
		public function get areaTotal():Number {
			return _areaTotal;
		}
		
		public function set areaTotal(valor:Number):void {
			_areaTotal = valor;
		}
		
		public function get incra():String {
			return _incra;
		}
		
		public function set incra(valor:String):void {
			_incra = valor;
		}
		
		public function get matricula():String {
			return _matricula;
		}
		
		public function set matricula(valor:String):void {
			_matricula = valor;
		}
		
		public function get municipio():String {
			return _municipio;
		}
		
		public function set municipio(valor:String):void {
			_municipio = valor;
		}
		
		public function get nirf():String {
			return _nirf;
		}
		
		public function set nirf(valor:String):void {
			_nirf = valor;
		}
		
        public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_areaPosse = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_areaTotal = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_incra = input.readObject() as String;
			_matricula = input.readObject() as String;
			_municipio = input.readObject() as String;
			_nirf = input.readObject() as String;
        }

        public override function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
            output.writeObject(_areaPosse);
            output.writeObject(_areaTotal);
            output.writeObject(_incra);
            output.writeObject(_matricula);
            output.writeObject(_municipio);
            output.writeObject(_nirf);
        }
    }
}