package br.com.sicoob.capes.encaminharAutorizacao{
	import mx.collections.GroupingField;
	import mx.controls.advancedDataGridClasses.AdvancedDataGridColumn;
	
	
	public class GridUtils{
	
		public static function groupFunc(item:Object, field:GroupingField):String
		{
			var label:String = item.tipoAutorizacao; 
			return label;
		}
			
		public static function compare(a:Object, b:Object, fields:Array=null):int{
			return compareValues(a, b);
		}
		
		private static function compareValues(a:Object, b:Object):int
		{
			var itemA:String = a.tipoAutorizacao;
			var itemB:String = b.tipoAutorizacao;
			
			if (itemA == null && itemB == null)
				return 0;
			
			if (itemA == null)
				return 1;
			
			if (itemB == null)
				return -1;
			
			if (itemA < itemB)
				return -1;
			
			if (itemA > itemB)
				return 1;
			
			return 0;
		}
	}
}