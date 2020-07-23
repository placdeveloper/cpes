package br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno.vo {
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO", ProcurarPessoaExternoVO);
	public class ProcurarPessoaExternoVO extends BancoobVO implements IExternalizable {
		
		private var _idPessoa:Number;
		private var _idPessoaLegado:Number;
		private var _idTipoPessoa:Number;
		private var _cpfCnpj:String;
		private var _nome:String;
		private var _apelido:String;
		
		public function get idPessoa():Number {
			return _idPessoa;
		}
		
		public function set idPessoa(value:Number):void {
			_idPessoa = value;
		}
		
		public function get idPessoaLegado():Number {
			return _idPessoaLegado;
		}
		
		public function set idPessoaLegado(value:Number):void {
			_idPessoaLegado = value;
		}
		
		public function get idTipoPessoa():Number {
			return _idTipoPessoa;
		}
		
		public function set idTipoPessoa(value:Number):void {
			_idTipoPessoa = value;
		}
		
		public function get cpfCnpj():String {
			return _cpfCnpj;
		}
		
		public function set cpfCnpj(value:String):void {
			_cpfCnpj = value;
		}
		
		public function get nome():String {
			return _nome;
		}
		
		public function set nome(value:String):void {
			_nome = value;
		}
		
		public function get apelido():String {
			return _apelido;
		}
		
		public function set apelido(value:String):void {
			_apelido = value;
		}
		
		public function readExternal(input:IDataInput):void {
			_apelido = input.readObject() as String;
			_cpfCnpj = input.readObject() as String;
			_idPessoa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idPessoaLegado = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idTipoPessoa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_nome = input.readObject() as String;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_apelido);
			output.writeObject(_cpfCnpj);
			output.writeObject(_idPessoa);
			output.writeObject(_idPessoaLegado);
			output.writeObject(_idTipoPessoa);
			output.writeObject(_nome);
		}
	}
}