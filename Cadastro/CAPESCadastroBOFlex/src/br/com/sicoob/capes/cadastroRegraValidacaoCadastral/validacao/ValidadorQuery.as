package br.com.sicoob.capes.cadastroRegraValidacaoCadastral.validacao
{
	import br.com.bancoob.componentes.input.TextoArea;
	import br.com.bancoob.util.StringUtils;
	
	import mx.utils.StringUtil;
	import mx.validators.ValidationResult;
	import mx.validators.Validator;
	
	public class ValidadorQuery extends Validator
	{
		private var query:TextoArea;
		
		public function ValidadorQuery(campoQuery:TextoArea){
			super();
			this.query = campoQuery;
		}
		
		protected override function doValidation(value:Object):Array {
			
			var results : Array = new Array();
			var pattern:RegExp = new RegExp("\\b(SELECT\\s+)([\\w.\\s]+,\\s*){3}CURRENT(\\s+|_)TIMESTAMP"
				+ "\\s+(FROM\\s+)[\\w.\\s=<>\\(\\),]+(?=VC\\s+)[\\w.\\s=<>\\(\\),]*(?=WHERE\\s+)"
				+ "(\\w|\\()[\\w.\\s=<>]*\\b", "i");
			var tempQuery:String = StringUtils.trim(query.text);
			if (tempQuery != ""){
				var result:Object = pattern.exec(tempQuery);
				if(result == null) {
					results.push(new ValidationResult(true, "", "", 
						"A query não está em um formato válido!"));
				}
			}
			return results;
		}
	}
}