package br.com.sicoob.capes.corporativo.componentes.procurarBens.vo {
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;

	registerClassAlias("br.com.sicoob.capes.api.negocio.vo.BemProprietarioVO", BemProprietarioVO);
	public class BemProprietarioVO extends BancoobVO {

		private var _areaPosse:Number;
		private var _codigoTipoPessoa:Number;
		private var _cpfCnpj:String;
		private var _idPessoa:Number;
		private var _nomePessoa:String;
		private var _percentualProprietario:Number;

		public function get areaPosse():Number {
			return this._areaPosse;
		}

		public function set areaPosse(valor:Number):void {
			this._areaPosse = valor;
		}

		public function get codigoTipoPessoa():Number {
			return this._codigoTipoPessoa;
		}

		public function set codigoTipoPessoa(valor:Number):void {
			this._codigoTipoPessoa = valor;
		}

		public function get cpfCnpj():String {
			return this._cpfCnpj;
		}

		public function set cpfCnpj(valor:String):void {
			this._cpfCnpj = valor;
		}

		public function get idPessoa():Number {
			return this._idPessoa;
		}

		public function set idPessoa(valor:Number):void {
			this._idPessoa = valor;
		}

		public function get nomePessoa():String {
			return this._nomePessoa;
		}

		public function set nomePessoa(valor:String):void {
			this._nomePessoa = valor;
		}

		public function get percentualProprietario():Number {
			return this._percentualProprietario;
		}

		public function set percentualProprietario(valor:Number):void {
			this._percentualProprietario = valor;
		}

		public function readExternal(input:IDataInput):void {
			_areaPosse = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoPessoa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_cpfCnpj = input.readObject() as String;
			_idPessoa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_nomePessoa = input.readObject() as String;
			_percentualProprietario = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
		}

		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_areaPosse);
			output.writeObject(_codigoTipoPessoa);
			output.writeObject(_cpfCnpj);
			output.writeObject(_idPessoa);
			output.writeObject(_nomePessoa);
			output.writeObject(_percentualProprietario);
		}

	}
}