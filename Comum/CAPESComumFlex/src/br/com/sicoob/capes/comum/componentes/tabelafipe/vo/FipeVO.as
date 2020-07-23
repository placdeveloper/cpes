package br.com.sicoob.capes.comum.componentes.tabelafipe.vo {
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	registerClassAlias("br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.FipeVO", FipeVO);
	public class FipeVO extends BancoobVO implements IExternalizable {
		
		private var _chave:String;
		private var _id:Number;
		private var _nome:String;
		private var _nomeFipe:String;
		
		public function get chave():String {
			return _chave;
		}
		
		public function set chave(valor:String):void {
			this._chave = valor;
		}
		
		public function get id():Number {
			return _id;
		}
		
		public function set id(valor:Number):void {
			this._id = valor;
		}
		
		public function get nome():String {
			return _nome;
		}
		
		public function set nome(valor:String):void {
			this._nome = valor;
		}
		
		public function get nomeFipe():String {
			return _nomeFipe;
		}
		
		public function set nomeFipe(valor:String):void {
			this._nomeFipe = valor;
		}
		
		public function readExternal(input:IDataInput):void {
			_chave = input.readObject() as String;
			_id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_nome = input.readObject() as String;
			_nomeFipe = input.readObject() as String;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_chave);
			output.writeObject(_id);
			output.writeObject(_nome);
			output.writeObject(_nomeFipe);
		}
	}
}