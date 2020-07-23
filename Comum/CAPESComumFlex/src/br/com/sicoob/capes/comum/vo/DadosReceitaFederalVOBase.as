package br.com.sicoob.capes.comum.vo
{
	import br.com.bancoob.tipos.SerializacaoObjetos;
	
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;
	
	[Bindable]
	public class DadosReceitaFederalVOBase
	{
		
		private var _codigoSituacaoCadastral:int;
		private var _dataUltimaAtualizacao:Date;
		private var _idConsulta:Number;
		private var _idInstituicao:Number;
		private var _idUnidadeInst:Number;
		
		public function get codigoSituacaoCadastral():int {
			return _codigoSituacaoCadastral;
		}
		public function set codigoSituacaoCadastral(codigoSituacaoCadastral:int):void {
			this._codigoSituacaoCadastral = codigoSituacaoCadastral;
		}
		
		public function get dataUltimaAtualizacao():Date {
			return _dataUltimaAtualizacao;
		}
		public function set dataUltimaAtualizacao(dataUltimaAtualizacao:Date):void {
			this._dataUltimaAtualizacao = dataUltimaAtualizacao;
		}
		
		public function set idConsulta(value:Number):void {
			_idConsulta = value;
		}
		public function get idConsulta():Number {
			return _idConsulta;
		}
		
		public function set idInstituicao(value:Number):void {
			_idInstituicao = value;
		}
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set idUnidadeInst(value:Number):void {
			_idUnidadeInst = value;
		}
		public function get idUnidadeInst():Number {
			return _idUnidadeInst;
		}
		
		public function get nome():String {
			return null;
		}
		
		public function get numeroInscricao():String {
			return null;
		}
		
		public function readExternal(input:IDataInput):void {
			_codigoSituacaoCadastral = input.readInt();
			_dataUltimaAtualizacao = SerializacaoObjetos.lerDate(input);
			_idConsulta = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idUnidadeInst = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
		}

		public function writeExternal(output:IDataOutput):void {
			output.writeInt(_codigoSituacaoCadastral);
			SerializacaoObjetos.escreverDate(_dataUltimaAtualizacao, output);
			output.writeObject(_idConsulta);
			output.writeObject(_idInstituicao);
			output.writeObject(_idUnidadeInst);
		}
	}
}