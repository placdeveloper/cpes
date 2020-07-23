package br.com.sicoob.capes.cadastrarRelacionamentoPessoa {
	import br.com.bancoob.componentes.input.Combo;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoRelacionamentoPessoaVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoa.ProcurarPessoaCAPES;
	
	import mx.collections.ArrayCollection;
	import mx.validators.ValidationResult;
	import mx.validators.Validator;

	public class ValidadorTipoRelacionamento extends Validator {
		
		private var cmbTipoRelacionamento : Combo; 
		private var codTipoPessoaRelacionamento : Number;
		private var procurarPessoa : ProcurarPessoaCAPES;
		
		public function ValidadorTipoRelacionamento(cmbTipoRelacionamento:Combo, 
				procurarPessoa:ProcurarPessoaCAPES, codTipoPessoaRelacionamento:Number) {
					
			super();
			this.cmbTipoRelacionamento = cmbTipoRelacionamento;
			this.procurarPessoa = procurarPessoa;
			this.codTipoPessoaRelacionamento = codTipoPessoaRelacionamento;
		}
		
		protected override function doValidation(value : Object) : Array {
			
			var results : Array = new Array();
			var complementoMsg : String = "";
			var codigosRelacionamento : ArrayCollection = new ArrayCollection();
			var codigosRelacionada : ArrayCollection = new ArrayCollection();
			
			var tipoRelacionamento : TipoRelacionamentoPessoaVO = TipoRelacionamentoPessoaVO(
					this.cmbTipoRelacionamento.selectedItem)
			var codTipoPessoaRelacionada : Number = procurarPessoa.registro.codTipoPessoa;
			for each(var tipoPessoaRelacionamento : TipoPessoaVO in tipoRelacionamento.tiposPessoaRelacionamento) {
				codigosRelacionamento.addItem(tipoPessoaRelacionamento.codTipoPessoa);
				for each(var tipoPessoaRelacionada : TipoPessoaVO in tipoRelacionamento.tiposPessoaRelacionada) {
					codigosRelacionada.addItem(tipoPessoaRelacionada.codTipoPessoa);
					if (complementoMsg.length > 0) {
						complementoMsg += ", ";
					}
					complementoMsg += tipoPessoaRelacionamento.descTipoPessoa + "/" 
							+ tipoPessoaRelacionada.descTipoPessoa;
				}
			}
			
			if (!codigosRelacionamento.contains(codTipoPessoaRelacionamento)
					|| !codigosRelacionada.contains(codTipoPessoaRelacionada)) {
				results.push(new ValidationResult(true, "", "", prepararMensagem(complementoMsg)));
			}
			return results;
		}
		
		private function prepararMensagem(complemento : String) : String {
			return "O tipo de relacionamento \"" + this.cmbTipoRelacionamento.selectedItem.descricao 
								+ "\" só permite as seguintes composições de relacionamento: " 
								+ complemento + ".";
		}
	}
}