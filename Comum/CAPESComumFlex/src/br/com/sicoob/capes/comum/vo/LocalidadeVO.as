package br.com.sicoob.capes.comum.vo {
	
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;
	
	/**
	 * Classe que representa a localidade, utilizada no componente de pesquisa de endereços.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO", LocalidadeVO);
	public class LocalidadeVO extends BancoobVO implements IExternalizable {
		
		private var _descComplementoLogradouro:String;
		private var _idLocalidade:Number;
		private var _idLocalidadePai:Number;
		private var _idLogradouro:Number;
		private var _idTipoLogradouro:Number;
		private var _nomeLocalidade:String;
		private var _nomeBairro:String;
		private var _nomeLogradouro:String;
		private var _numCep:String;
		private var _siglaUF:String;
		
		public function get descComplementoLogradouro():String {
			return _descComplementoLogradouro;
		}
		
		public function set descComplementoLogradouro(valor:String):void {
			this._descComplementoLogradouro = valor;
		}
		
		public function get idLocalidade():Number {
			return _idLocalidade;
		}
		
		public function set idLocalidade(valor:Number):void {
			this._idLocalidade = valor;
		}
		
		public function get idLocalidadePai():Number {
			return _idLocalidadePai;
		}
		
		public function set idLocalidadePai(valor:Number):void {
			this._idLocalidadePai = valor;
		}
		
		public function get idLogradouro():Number {
			return _idLogradouro;
		}
		
		public function set idLogradouro(valor:Number):void {
			this._idLogradouro = valor;
		}
		
		public function get idTipoLogradouro():Number {
			return _idTipoLogradouro;
		}
		
		public function set idTipoLogradouro(valor:Number):void {
			this._idTipoLogradouro = valor;
		}
		
		public function get nomeLocalidade():String {
			return _nomeLocalidade;
		}
		
		public function set nomeLocalidade(valor:String):void {
			this._nomeLocalidade = valor;
		}
		
		public function get nomeBairro():String {
			return _nomeBairro;
		}
		
		public function set nomeBairro(valor:String):void {
			this._nomeBairro = valor;
		}
		
		public function get nomeLogradouro():String {
			return _nomeLogradouro;
		}
		
		public function set nomeLogradouro(valor:String):void {
			this._nomeLogradouro = valor;
		}
		
		public function get numCep():String {
			return _numCep;
		}
		
		public function set numCep(valor:String):void {
			this._numCep = valor;
		}
		
		public function get siglaUF():String {
			return _siglaUF;
		}
		
		public function set siglaUF(valor:String):void {
			this._siglaUF = valor;
		}
		
		public function readExternal(input:IDataInput):void {
			_descComplementoLogradouro = input.readObject() as String;
			 _idLocalidade = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idLocalidadePai = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idLogradouro = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idTipoLogradouro = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_nomeLocalidade = input.readObject() as String;
			_nomeBairro = input.readObject() as String;
			_nomeLogradouro = input.readObject() as String;
			_numCep = input.readObject() as String;
			_siglaUF = input.readObject() as String;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_descComplementoLogradouro);
			output.writeObject(_idLocalidade);
			output.writeObject(_idLocalidadePai);
			output.writeObject(_idLogradouro);
			output.writeObject(_idTipoLogradouro);
			output.writeObject(_nomeLocalidade);
			output.writeObject(_nomeBairro);
			output.writeObject(_nomeLogradouro);
			output.writeObject(_numCep);
			output.writeObject(_siglaUF);
		}
	}
}