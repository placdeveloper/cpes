package br.com.sicoob.capes.cadastrarTipoFonteRenda {
	import br.com.bancoob.componentes.input.Check;
	
	import mx.validators.ValidationResult;
	import mx.validators.Validator;
	
	public class TipoPessoaValidator extends Validator {
		
		private var tpPessoaFisica:Check;
		private var tpPessoaJuridica:Check;
		
		public function TipoPessoaValidator(tpPessoaFisica:Check, tpPessoaJuridica:Check)
		{
			super();
			this.tpPessoaFisica = tpPessoaFisica;
			this.tpPessoaJuridica = tpPessoaJuridica;
		}
		
		protected override function doValidation(value : Object) : Array {
			var results : Array = new Array();
			if(!tpPessoaFisica.selected && ! tpPessoaJuridica.selected){
				results.push(new ValidationResult(true, "", "", "Selecione ao menos um tipo de pessoa."));
			}
			return results;
		}
	}
}