package br.com.sicoob.capes.comum.componentes.tabelafipe.vo {
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	registerClassAlias("br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.DetalheFipeVO", DetalheFipeVO);
	public class DetalheFipeVO extends FipeVO {
		
		private var _anoModelo:Number;
		private var _codigoFipe:String;
		private var _combustivel:String;
		private var _marca:String;
		private var _preco:String;
		private var _referencia:String;
		private var _veiculo:String;
		
		public function get codigoFipe():String {
			return _codigoFipe;
		}
		
		public function set codigoFipe(valor:String):void {
			this._codigoFipe = valor;
		}
		
		public function get combustivel():String {
			return _combustivel;
		}
		
		public function set combustivel(valor:String):void {
			this._combustivel = valor;
		}
		
		public function get marca():String {
			return _marca;
		}
		
		public function set marca(valor:String):void {
			this._marca = valor;
		}
		
		public function get anoModelo():Number {
			return _anoModelo;
		}
		
		public function set anoModelo(valor:Number):void {
			this._anoModelo = valor;
		}
		
		public function get veiculo():String {
			return _veiculo;
		}
		
		public function set veiculo(valor:String):void {
			this._veiculo = valor;
		}
		
		public function get referencia():String {
			return _referencia;
		}
		
		public function set referencia(valor:String):void {
			this._referencia = valor;
		}
		
		public function get preco():String {
			return _preco;
		}
		
		public function set preco(valor:String):void {
			this._preco = valor;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_anoModelo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoFipe = input.readObject() as String;
			_combustivel = input.readObject() as String;
			_marca = input.readObject() as String;
			_preco = input.readObject() as String;
			_referencia = input.readObject() as String;
			_veiculo = input.readObject() as String;
		}
		
		public override function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
			output.writeObject(_anoModelo);
			output.writeObject(_codigoFipe);
			output.writeObject(_combustivel);
			output.writeObject(_marca);
			output.writeObject(_preco);
			output.writeObject(_referencia);
			output.writeObject(_veiculo);
		}
	}
}