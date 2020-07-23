package br.com.sicoob.capes.comum.vo {

    import br.com.bancoob.vo.BancoobVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class StatusTransferenciaClienteVOBase extends BancoobVO {
		
		private var _idUnidadeInst:Number;
		private var _nomeNucleo:String;
		private var _nomePessoa:String;
		private var _numeroNucleo:Number;
		private var _quantidade:Number;
		
		public function get idUnidadeInst():Number {
			return _idUnidadeInst;
		}
		
		public function set idUnidadeInst(value:Number):void {
			_idUnidadeInst = value;
		}
		
		public function get nomeNucleo():String {
			return _nomeNucleo;
		}
		
		public function set nomeNucleo(value:String):void {
			_nomeNucleo = value;
		}
		
		public function get nomePessoa():String {
			return _nomePessoa;
		}
		
		public function set nomePessoa(value:String):void {
			_nomePessoa = value;
		}
		
		public function get numeroNucleo():Number {
			return _numeroNucleo;
		}
		
		public function set numeroNucleo(value:Number):void {
			_numeroNucleo = value;
		}
		
		public function get quantidade():Number {
			return _quantidade;
		}
		
		public function set quantidade(value:Number):void {
			_quantidade = value;
		}
		
		public function readExternal(input:IDataInput):void {
			_idUnidadeInst = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_nomePessoa = input.readObject() as String;
			_nomeNucleo = input.readObject() as String;
			_numeroNucleo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_quantidade = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_idUnidadeInst);
			output.writeObject(_nomeNucleo);
			output.writeObject(_nomePessoa);
			output.writeObject(_numeroNucleo);
			output.writeObject(_quantidade);
        }
    }
}