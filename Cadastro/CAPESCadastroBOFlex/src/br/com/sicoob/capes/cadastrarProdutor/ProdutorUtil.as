package br.com.sicoob.capes.cadastrarProdutor
{
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	
	import mx.controls.dataGridClasses.DataGridColumn;
	
	/**
	 * Classe que contém métodos utilitários para o caso de uso manter produtor.
	 * 
	 * @author Bancoob
	 */
	public class ProdutorUtil{
		
		public function ProdutorUtil(){
		}

		public static function defaultAnoSafra(objeto : Object,
     			coluna : DataGridColumn) : String {
     	
     		var anoInicio: Number = objeto.anoInicioSafra;
     		var anoFim: Number  = objeto.anoFimSafra;
     		
     		return anoInicio + " / " + anoFim;			
 		}
     	

	}
}
