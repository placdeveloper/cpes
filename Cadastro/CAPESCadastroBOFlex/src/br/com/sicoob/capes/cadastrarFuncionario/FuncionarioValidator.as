package br.com.sicoob.capes.cadastrarFuncionario {
	import br.com.bancoob.componentes.input.Data;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoa.ProcurarPessoaCAPES;
	
	import flash.events.Event;
	
	import mx.validators.ValidationResult;
	import mx.validators.Validator;
	
	public class FuncionarioValidator extends Validator {
		
		private var dataAdmissao:Data;
		private var dataDesligamento:Data;
		private var procurarPessoa:ProcurarPessoaCAPES;
		
		public function FuncionarioValidator(procurarPessoa:ProcurarPessoaCAPES, dataAdmissao:Data, dataDesligamento:Data)
		{
			super();
			this.dataAdmissao = dataAdmissao;
			this.dataDesligamento = dataDesligamento;
			this.procurarPessoa = procurarPessoa;
			dataAdmissao.addEventListener("change", dataMinimaDesligamento);
		}
		
		protected function dataMinimaDesligamento(evt: Event): void{
			dataDesligamento.dataMinima = dataAdmissao.selectedDate;
			dataDesligamento.validar(dataDesligamento.compDate.selectedDate);
		}
		
		protected override function doValidation(value : Object) : Array {
			var results : Array = new Array();
			if(!dataAdmissao.validar(dataAdmissao.compDate.selectedDate) || dataAdmissao.compMask.errorString != ""){
				results.push(new ValidationResult(true, dataAdmissao.name, "", dataAdmissao.compMask.errorString));
			} else {
				if(!dataDesligamento.validar(dataDesligamento.compDate.selectedDate) || dataDesligamento.compMask.errorString != ""){
					results.push(new ValidationResult(true, dataDesligamento.name, "", dataDesligamento.compMask.errorString));
				}
			}
			if(!procurarPessoa.validar()){
				results.push(new ValidationResult(true, procurarPessoa.name, "", "Pessoa não encontrada."));
			}
			return results;
		}
	}
}