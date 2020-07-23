package br.com.sicoob.capes.comum.vo
{
	import flash.net.registerClassAlias;
	
	import br.com.bancoob.vo.BancoobVO;

	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.OcorrenciaAtividadeVO", OcorrenciaAtividadeVO);
	public class OcorrenciaAtividadeVO extends BancoobVO{
		
		private var _data:Date;
		private var _idOcorrenciaAtividade:Number;
		private var _idOcorrenciaProcesso:Number;
		private var _idProcedimento:Number;
		private var _idUsuario:String;
		private var _justificativa:String;
		private var _nomeProcedimento:String;

		public function get data():Date {
			return _data;
		}
		
		public function set data(value:Date):void {
			_data = value;
		}

		public function get idOcorrenciaAtividade():Number {
			return _idOcorrenciaAtividade;
		}
		
		public function set idOcorrenciaAtividade(value:Number):void {
			_idOcorrenciaAtividade = value;
		}

		public function get idOcorrenciaProcesso():Number {
			return _idOcorrenciaProcesso;
		}
		
		public function set idOcorrenciaProcesso(value:Number):void {
			_idOcorrenciaProcesso = value;
		}
		
		public function get idProcedimento():Number {
			return _idProcedimento;
		}
		public function set idProcedimento(value:Number):void {
			_idProcedimento = value;
		}

		public function get idUsuario():String {
			return _idUsuario;
		}
		
		public function set idUsuario(value:String):void {
			_idUsuario = value;
		}

		public function get justificativa():String {
			return _justificativa;
		}
		
		public function set justificativa(value:String):void {
			_justificativa = value;
		}

		public function get nomeProcedimento():String {
			return _nomeProcedimento;
		}
		
		public function set nomeProcedimento(value:String):void {
			_nomeProcedimento = value;
		}
		
	}
}