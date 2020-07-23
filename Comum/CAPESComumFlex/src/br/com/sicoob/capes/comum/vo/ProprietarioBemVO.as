package br.com.sicoob.capes.comum.vo {
	
	import br.com.bancoob.vo.BancoobVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;
	
	/**
	 * Classe utilizada no módulo de bens e representa o proprietário do bem (bemPessoaCompartilhamento).
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.ProprietarioBemVO", ProprietarioBemVO);
	public class ProprietarioBemVO extends BancoobVO implements IExternalizable {
		
		private var _areaPosse:Number;
		private var _areaTotal:Number;
		private var _codigoTipoPessoa:Number;
		private var _cpfCnpj:String;
		private var _idBemPessoaCompartilhamento:Number;
		private var _idPessoaCompartilhamento:Number;
		private var _marcadoExclusao:Boolean = false;
		private var _nome:String;
		private var _pessoaCompartilhamento:PessoaCompartilhamentoVO;
		private var _porcentagem:Number;
		private var _tipoRelacionamento:String;
		
		public function get areaPosse():Number {
			return _areaPosse;
		}
		
		public function set areaPosse(valor:Number):void {
			this._areaPosse = valor;
		}
		
		public function get areaTotal():Number {
			return _areaTotal;
		}
		
		public function set areaTotal(valor:Number):void {
			this._areaTotal = valor;
		}
		
		public function get codigoTipoPessoa():Number {
			return _codigoTipoPessoa;
		}
		
		public function set codigoTipoPessoa(valor:Number):void {
			this._codigoTipoPessoa = valor;
		}
		
		public function get cpfCnpj():String {
			return _cpfCnpj;
		}
		
		public function set cpfCnpj(valor:String):void {
			this._cpfCnpj = valor;
		}
		
		public function get idBemPessoaCompartilhamento():Number {
			return _idBemPessoaCompartilhamento;
		}
		
		public function set idBemPessoaCompartilhamento(valor:Number):void {
			this._idBemPessoaCompartilhamento = valor;
		}
		
		public function get idPessoaCompartilhamento():Number {
			return _idPessoaCompartilhamento;
		}
		
		public function set idPessoaCompartilhamento(valor:Number):void {
			this._idPessoaCompartilhamento = valor;
		}
		
		public function get marcadoExclusao():Boolean {
			return _marcadoExclusao;
		}
		
		public function set marcadoExclusao(valor:Boolean):void {
			this._marcadoExclusao = valor;
		}
		
		public function get pessoaCompartilhamento():PessoaCompartilhamentoVO {
			return _pessoaCompartilhamento;
		}
		
		public function set pessoaCompartilhamento(valor:PessoaCompartilhamentoVO):void {
			this._pessoaCompartilhamento = valor;
		}
		
		public function get nome():String {
			return _nome;
		}
		
		public function set nome(valor:String):void {
			this._nome = valor;
		}
		
		public function get porcentagem():Number {
			return _porcentagem;
		}
		
		public function set porcentagem(valor:Number):void {
			this._porcentagem = valor;
		}
		
		public function get tipoRelacionamento():String {
			return _tipoRelacionamento;
		}
		
		public function set tipoRelacionamento(valor:String):void {
			this._tipoRelacionamento = valor;
		}
		
		public function readExternal(input:IDataInput):void {
			_areaPosse = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_areaTotal = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoPessoa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_cpfCnpj = input.readObject() as String;
			_idBemPessoaCompartilhamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idPessoaCompartilhamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_marcadoExclusao = input.readObject() as Boolean;
			_nome = input.readObject() as String;
			_pessoaCompartilhamento = input.readObject() as PessoaCompartilhamentoVO;
			_porcentagem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_tipoRelacionamento = input.readObject() as String;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_areaPosse);
			output.writeObject(_areaTotal);
			output.writeObject(_codigoTipoPessoa);
			output.writeObject(_cpfCnpj);
			output.writeObject(_idBemPessoaCompartilhamento);
			output.writeObject(_idPessoaCompartilhamento);
			output.writeObject(_marcadoExclusao);
			output.writeObject(_nome);
			output.writeObject(_pessoaCompartilhamento);
			output.writeObject(_porcentagem);
			output.writeObject(_tipoRelacionamento);
		}
	}
}