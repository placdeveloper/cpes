package br.com.sicoob.capes.cadastroRegraValidacaoCadastral.validacao
{
	import br.com.bancoob.componentes.input.ComboBooleana;
	import br.com.bancoob.componentes.input.RadioGrupoBancoob;
	import br.com.bancoob.componentes.input.TextoArea;
	import br.com.bancoob.util.StringUtils;
	
	import mx.validators.ValidationResult;
	import mx.validators.Validator;
	
	public class ValidadorSituacao extends Validator {
		
		private var situacao:RadioGrupoBancoob;
		private var query:TextoArea;
		
		public function ValidadorSituacao(situacao:RadioGrupoBancoob, query:TextoArea) {
			this.situacao = situacao;
			this.query = query;
		}
		
		protected override function doValidation(value : Object) : Array {
			var results : Array = new Array();
			if(Boolean(situacao.selectedValue) && (StringUtils.trim(query.text).length == 0)){
				results.push(new ValidationResult(true, "", "", 
					"A regra só pode ser ativada se a query estiver preenchida!"));
			}
			return results;
		}
	}
}