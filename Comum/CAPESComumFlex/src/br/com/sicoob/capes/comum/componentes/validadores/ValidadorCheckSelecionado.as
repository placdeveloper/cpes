package br.com.sicoob.capes.comum.componentes.validadores {
	import br.com.bancoob.componentes.input.Check;
	
	import mx.utils.StringUtil;
	import mx.validators.ValidationResult;
	import mx.validators.Validator;

	public class ValidadorCheckSelecionado extends Validator {
		
		[ArrayElementType("br.com.bancoob.componentes.input.Check")]
		private var _mensagemPadrao : String = "Pelo menos MINIMO opção(ões) deve(m) ser selecionada(s) para o campo LABEL";
		private var _mensagemErro : String;
		private var _campos : Array;
		private var _quantidadeMinima : int = 1;
		
		public function ValidadorCheckSelecionado()	{
			super();
		}
		
		protected override function doValidation(value : Object) : Array {
			var results : Array = new Array();
			var cont : int = 0;
			for each(var check : Check in campos) {
				cont += check.selected ? 1 : 0;
			}
			if (cont < quantidadeMinima) {
				results.push(new ValidationResult(true, "label", "", prepararMensagem()));
			}
			return results;
		}
		
		private function prepararMensagem() : String {
			var mensagem : String = null;
			
			if ((mensagemErro != null) && (StringUtil.trim(mensagemErro).length > 0)) {
				mensagem = mensagemErro;
			} else {
				mensagem = _mensagemPadrao.replace(/MINIMO/,quantidadeMinima);
			}
			return mensagem;
		}
		
		public function set campos(valor : Array) : void {
			_campos = valor;
		}
		public function get campos() : Array {
			if (_campos == null) {
				_campos = new Array();
			}
			return _campos;
		}
		
		public function set quantidadeMinima(valor : int) : void {
			_quantidadeMinima = valor;
		}
		public function get quantidadeMinima() : int {
			return _quantidadeMinima;
		}
		
		public function set mensagemErro(valor : String) : void {
			_mensagemErro = valor;
		}
		public function get mensagemErro() : String {
			return _mensagemErro;
		}
	}
}