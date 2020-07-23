package br.com.sicoob.capes.comum.util
{
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	
	import mx.controls.dataGridClasses.DataGridColumn;
	
	/**
	 * Classe que contém métodos utilitários para as tabelas paginadas.
	 * 
	 * @author Bancoob
	 */
	public class FormatadorUtil{
		
		public static const TIPO_APLICACAO_PESSOA_FISICA: int = 1;
		public static const TIPO_APLICACAO_PESSOA_JURIDICA: int = 2;
		public static const TIPO_APLICACAO_AMBAS: int = 3;
		
		public static const TIPO_PESSOA_FISICA: int = 0;
		public static const TIPO_PESSOA_JURIDICA: int = 1;
		
		public static const TIPO_OPERACAO_ALTERACAO : String = "A";
		public static const TIPO_OPERACAO_EXCLUSAO : String = "E";
		public static const TIPO_OPERACAO_INCLUSAO : String = "I";
		
		public static const TIPO_LISTA_A_AUTORIZAR : int = 1;
		public static const TIPO_LISTA_AGUARDANDO_AUTORIZACAO : int = 2;
		
		public static const SITUACAO_ANOTACAO_VIGENTES : String = "Vigentes";
		public static const SITUACAO_ANOTACAO_BAIXADAS : String = "Baixadas";
		
		public static const TIPO_PRAZO_CERTIDAO_INFORMADO : int = 0;
		public static const TIPO_PRAZO_CERTIDAO_DIAS : int = 1;
		public static const TIPO_PRAZO_CERTIDAO_MESES : int = 2;
		public static const TIPO_PRAZO_CERTIDAO_SEM_VENCIMENTO : int = 3;
		public static const TIPO_PRAZO_CERTIDAO_ANO_BASE : int = 4;

		
		public function FormatadorUtil(){
		}

		//**********
		// Serviços:
		//**********		
		
		public static function defaultStatusTipoAnotacaoLabelFunction(objeto : Object, 
				coluna : DataGridColumn) : String {
					
			var retorno : String = "Ativo";
			var valor : Object = ReflectionUtils.recuperarPropriedade(objeto, 
					recuperarNomePropriedade(coluna));
			if (!valor.valor) {
				retorno = "Inativo";
			} 
			return retorno;
		}
		
        public static function defaultTipoAplicacaoLabelFunction(objeto:Object, 
        		coluna:DataGridColumn):String {
        			     
			var codigo:Object = ReflectionUtils.recuperarPropriedade(objeto, 
					recuperarNomePropriedade(coluna));
			
			var valor:String = "";  
			
			if(codigo == TIPO_APLICACAO_PESSOA_FISICA) {
				valor = "Pessoa Física";	
			} else if(codigo == TIPO_APLICACAO_PESSOA_JURIDICA) {
				valor = "Pessoa Jurídica";	
			} else if(codigo == TIPO_APLICACAO_AMBAS) {
				valor = "Ambas";	
			}

			
            return valor; 
        }
     
     	public static function defaultTipoOperacaoLabelFunction(objeto : Object,
     			coluna : DataGridColumn) : String {
     	
     		var tipo : Object = ReflectionUtils.recuperarPropriedade(objeto, 
     				recuperarNomePropriedade(coluna));
     		var label : String = "";
     		if (tipo == TIPO_OPERACAO_ALTERACAO) {
     			label = "Alteração";
     		} else if (tipo == TIPO_OPERACAO_EXCLUSAO) {
     			label = "Exclusão";
     		} else if (tipo == TIPO_OPERACAO_INCLUSAO) {
     			label = "Inclusão";
     		}
     		return label;
 		}
		
     	public static function defaultRendaAnual(objeto : Object, coluna : DataGridColumn) : String {
     	
			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, 
					recuperarNomePropriedade(coluna));
			var dataValidade:IDateTime = ReflectionUtils.recuperarPropriedade(objeto,"dataValidadeRenda") as IDateTime;
			
			var rendaAnual: Number = CadastroUnicoUtil.calcularRendaBrutaAnual(Number(valor), 
				 dataValidade != null? dataValidade.data:null);
            return FormataNumero.formata(rendaAnual); 
			
 		}		
			
     	public static function defaultFormaRenda(objeto : Object, coluna : DataGridColumn) : String {
     	
			var valor : Object = ReflectionUtils.recuperarPropriedade(objeto, 
					recuperarNomePropriedade(coluna));
			var verdadeiro: Boolean = false;
			
			if(valor is Booleano) {
				verdadeiro = Booleano(valor).valor;
			} else {
				verdadeiro = valor;
			}
		
			return verdadeiro == true ? "Fixa": "Variável";
 		}		
     	
		public static function defaultBooleanLabelFunction(objeto:Object, 
        		coluna:DataGridColumn):String {     
        			
			var obj:Object = ReflectionUtils.recuperarPropriedade(objeto, 
					recuperarNomePropriedade(coluna));

			var retorno:String = "";
			if(obj is Booleano) {
				retorno = (String(obj.valor) == "true")? "Sim": "Não";
			} else {
				retorno = obj == true ? "Sim": "Não"; 
			}
			 					
            return retorno;  
        }  	     	
     	
		private function defaultPorcentagemLabelFunction(objeto:Object, coluna:ColunaGrid):String{
			var retorno:String = "";		
		
			var obj:Object = ReflectionUtils.recuperarPropriedade(objeto, recuperarNomePropriedade(coluna));
			if(obj != null) {		
				retorno = FormataNumero.formata(obj as Number,2,false).toString() + "%";
			}
			return retorno;						
		}     	
     	
		public static function recuperarNomePropriedade(coluna:DataGridColumn):String {
			return (coluna is ColunaGrid && ColunaGrid(coluna).propriedade != null)
					? ColunaGrid(coluna).propriedade
					: coluna.dataField;
		}        
	}
}
