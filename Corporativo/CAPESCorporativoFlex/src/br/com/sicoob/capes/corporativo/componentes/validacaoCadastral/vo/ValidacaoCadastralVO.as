package br.com.sicoob.capes.corporativo.componentes.validacaoCadastral.vo {
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO", ValidacaoCadastralVO);
	public class ValidacaoCadastralVO extends BancoobVO /*implements IExternalizable*/ {
		
		private var _codigo						:Number;
		private var _funcionalidade				:String;
		private var _idPessoaCompartilhamento	:Number;
		private var _mensagem					:String;
		private var _perfilCadastro				:String;
		private var _tipo						:String;
		
		
		public function get codigo():Number {
			return _codigo;
		}
		
		public function set codigo(value:Number):void {
			_codigo = value;
		}
		
		public function get funcionalidade():String {
			return _funcionalidade;
		}
		
		public function set funcionalidade(value:String):void {
			_funcionalidade = value;
		}
		
		public function get idPessoaCompartilhamento():Number {
			return _idPessoaCompartilhamento;
		}
		
		public function set idPessoaCompartilhamento(value:Number):void {
			_idPessoaCompartilhamento = value;
		}
		
		public function get mensagem():String {
			return _mensagem;
		}
		
		public function set mensagem(value:String):void {
			_mensagem = value;
		}
		
		public function get perfilCadastro():String {
			return _perfilCadastro;
		}
		
		public function set perfilCadastro(value:String):void {
			_perfilCadastro = value;
		}
		
		public function get tipo():String {
			return _tipo;
		}
		
		public function set tipo(value:String):void {
			_tipo = value;
		}
		
		public function readExternal(input:IDataInput):void {
			_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_funcionalidade = input.readObject() as String;
			_idPessoaCompartilhamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_mensagem = input.readObject() as String;
			_perfilCadastro = input.readObject() as String;
			_tipo = input.readObject() as String;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_codigo);
			output.writeObject(_funcionalidade);
			output.writeObject(_idPessoaCompartilhamento);
			output.writeObject(_mensagem);
			output.writeObject(_perfilCadastro);
			output.writeObject(_tipo);
		}
	}
}