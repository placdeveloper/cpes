package br.com.sicoob.capes.cadastrarAnotacao.util {
	import br.com.bancoob.componentes.tabelapaginada.TabelaPaginadaUtils;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	import br.com.sicoob.capes.comum.vo.entidades.AnotacaoVO;
	
	import mx.controls.dataGridClasses.DataGridColumn;
	
	public class AnotacaoUtils extends TabelaPaginadaUtils {
		
		public static function quantidadeLabelFunction(objeto:Object, 
													   coluna:DataGridColumn):String {
			
			var quantidade:String = "";
			var anotacao:AnotacaoVO = objeto as AnotacaoVO;
			
			//if (anotacao.tipoAnotacao.registraQuantidade.valor) {
				quantidade = String(anotacao.quantidade);
			//}
			return quantidade;
		}
		
		public static function valorLabelFunction(objeto:Object, 
												  coluna:DataGridColumn):String {
			
			var valor:String = "";
			var anotacao:AnotacaoVO = objeto as AnotacaoVO;
			
			//if (anotacao.tipoAnotacao.registraValor.valor) {
				valor = FormataNumero.formata(Number(anotacao.valor));
			//}
			return valor;
		}
	}
}