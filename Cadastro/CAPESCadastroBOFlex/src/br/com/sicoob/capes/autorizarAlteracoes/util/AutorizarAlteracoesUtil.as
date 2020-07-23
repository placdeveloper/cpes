package br.com.sicoob.capes.autorizarAlteracoes.util {
	import br.com.sicoob.capes.comum.vo.ItemAutorizacaoVO;
	
	import mx.controls.dataGridClasses.DataGridColumn;
	
	public class AutorizarAlteracoesUtil {
		public function AutorizarAlteracoesUtil(){
		}
		
		public static function formatarNumeroNomeCooperativa(objeto : Object,
     			coluna : DataGridColumn) : String {
     	
     		var vo : ItemAutorizacaoVO = ItemAutorizacaoVO(objeto);
     		return vo.numeroCooperativa + " - " + vo.nomeInstituicao;			
 		}
	}
}