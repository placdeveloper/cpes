package br.com.sicoob.capes.comum.componentes.input
{
	import br.com.bancoob.componentes.input.Combo;
	
	import mx.collections.ArrayCollection;
	
	public class ComboDDD extends Combo
	{

		private var lista:ArrayCollection = new ArrayCollection(
				[ 	{label:"11"},{label:"12"},{label:"13"},{label:"14"},{label:"15"},{label:"16"},
				  	{label:"17"},{label:"18"},{label:"19"},
				  	{label:"21"},{label:"22"},{label:"24"},{label:"27"},{label:"28"},
				  	{label:"31"},{label:"32"},{label:"33"},{label:"34"},{label:"35"},
				  	{label:"37"},{label:"38"},
				  	{label:"41"},{label:"42"},{label:"43"},{label:"44"},{label:"45"},{label:"46"},
				  	{label:"47"},{label:"48"},{label:"49"},
				  	{label:"51"},{label:"53"},{label:"54"},{label:"55"},
				  	{label:"61"},{label:"62"},{label:"63"},{label:"64"},{label:"65"},{label:"66"},
				  	{label:"67"},{label:"68"},{label:"69"},
				  	{label:"71"},{label:"73"},{label:"74"},{label:"75"},{label:"77"},{label:"79"},
				  	{label:"81"},{label:"82"},{label:"83"},{label:"84"},{label:"85"},{label:"86"},
				  	{label:"87"},{label:"88"},{label:"89"},
				  	{label:"91"},{label:"92"},{label:"93"},{label:"94"},{label:"95"},{label:"96"},
			  		{label:"97"},{label:"98"},{label:"99"} ]);
		
		public function ComboDDD()
		{
			super.inserirItemOpcional=true
			super.labelField = "label";
			super.data= "label"
			super.dataProvider = lista;
		}

	}
}