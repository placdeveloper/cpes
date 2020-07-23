package br.com.sicoob.capes.monitoracaoAtualizacaoCadastral{
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	
	import mx.collections.HierarchicalCollectionView;
	import mx.collections.IViewCursor;
	import mx.controls.AdvancedDataGrid;
	import mx.events.ListEvent;
	
	public class GridUtils{
	
		public static function criarDefaultSortFunction(coluna:Object) : Function {
			
			return function (objeto1:Object, objeto2:Object) : int{
				var nomePropriedade:String = coluna.dataField;
				var valor1:String = String(ReflectionUtils.recuperarPropriedade(objeto1, 
					nomePropriedade));
				var valor2:String = String(ReflectionUtils.recuperarPropriedade(objeto2, 
					nomePropriedade));

				valor1 = valor1 == null ? "" : valor1; 
				valor2 = valor2 == null ? "" : valor2;

				return valor1 > valor2 ? 1 : valor1 < valor2 ? -1 : 0;
			}
		}

		public static function defaultLabelFunction(objeto:Object, 
				coluna:Object):String {
					
			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, coluna.dataField);
			return (valor == null) ? "" : String(valor);
		}

        public static function defaultDatetimeLabelFunction(objeto:Object, 
        		coluna:Object):String {     

			var valor:Object = ReflectionUtils.recuperarPropriedade(objeto, 
					coluna.dataField);
            return FormataData.formataDataHora(valor is IDateTime ? (valor as IDateTime).data : valor as Date); 
        } 

		public static function criarDefaultDateSortFunction(coluna:Object) : Function {
			return function (objeto1:Object, objeto2:Object) : int{
				var nomePropriedade:String = coluna.dataField;
				var valor1:Date = IDateTime(ReflectionUtils.recuperarPropriedade(objeto1, 
						nomePropriedade)).data;
				var valor2:Date = IDateTime(ReflectionUtils.recuperarPropriedade(objeto2, 
						nomePropriedade)).data;

				valor1 = valor1 == null ? new Date(0) : valor1; 
				valor2 = valor2 == null ? new Date(0) : valor2;

				return valor1 > valor2 ? 1 : valor1 < valor2 ? -1 : 0;
			}
		}
		
		public static function criarDefaultValorNumericoSortFunction(coluna:Object) : Function {
			return function (objeto1:Object, objeto2:Object) : int{
				var nomePropriedade:String = coluna.dataField;
				var valor1:Number = Number(ReflectionUtils.recuperarPropriedade(objeto1, 
					nomePropriedade));
				var valor2:Number = Number(ReflectionUtils.recuperarPropriedade(objeto2, 
					nomePropriedade));
					
				valor1 = isNaN(valor1) ? Number.MIN_VALUE : valor1; 
				valor2 = isNaN(valor2) ? Number.MIN_VALUE : valor2;

				return valor1 > valor2 ? 1 : valor1 < valor2 ? -1 : 0;   
			}
		}

		public static function adg_itemClick(e:ListEvent):void{
			var item:Object = AdvancedDataGrid(e.currentTarget).selectedItem;
			e.target.expandItem(item, !e.target.isItemOpen(item), true);
		}
		
		public static function expandirPrimeiroNivel(adg:AdvancedDataGrid) : void {
			var items : HierarchicalCollectionView = HierarchicalCollectionView(adg.dataProvider);
			var cursor:IViewCursor = items.createCursor();
			while(!cursor.afterLast) {
			    var item:Object = cursor.current;
				if (adg.getParentItem(item) == null) {
					adg.expandItem(item, true, true);
				}
			    cursor.moveNext();
			}

		}
	}
}