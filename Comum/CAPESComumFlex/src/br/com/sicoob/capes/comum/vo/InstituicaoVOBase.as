package br.com.sicoob.capes.comum.vo{

    import br.com.bancoob.vo.BancoobVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class InstituicaoVOBase extends BancoobVO {

		private var _codigoSituacaoInst:Number;
		private var _codigoTipoInstituicao:Number;
		private var _idInstituicao:Number;
		private var _nome:String;
		private var _numero:Number;
		private var _siglaInstituicao:String;

		public function get codigoSituacaoInst():Number {
			return _codigoSituacaoInst;
		}
		
		public function set codigoSituacaoInst(valor:Number):void {
			_codigoSituacaoInst = valor;
		}
		
		public function get codigoTipoInstituicao():Number {
			return _codigoTipoInstituicao;
		}
		
		public function set codigoTipoInstituicao(valor:Number):void {
			_codigoTipoInstituicao = valor;
		}
		
        public function set idInstituicao(value:Number):void {
            _idInstituicao = value;
        }
		
        public function get idInstituicao():Number {
            return _idInstituicao;
        }

        public function set nome(value:String):void {
            _nome = value;
        }
		
        public function get nome():String {
            return _nome;
        }

        public function set numero(value:Number):void {
            _numero = value;
        }
		
        public function get numero():Number {
            return _numero;
        }
		
		public function get siglaInstituicao():String {
			return _siglaInstituicao;
		}
		
		public function set siglaInstituicao(valor:String):void {
			_siglaInstituicao = valor;
		}
		
        public function readExternal(input:IDataInput):void {
			_codigoSituacaoInst = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
	        _nome = input.readObject() as String;
	        _numero = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_siglaInstituicao = input.readObject() as String;
        }
		
        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_codigoSituacaoInst);
            output.writeObject(_codigoTipoInstituicao);
            output.writeObject(_idInstituicao);
           	output.writeObject(_nome);
            output.writeObject(_numero);
            output.writeObject(_siglaInstituicao);
        }
    }
}