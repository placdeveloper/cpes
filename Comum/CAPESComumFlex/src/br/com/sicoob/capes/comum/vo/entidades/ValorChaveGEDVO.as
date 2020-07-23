package br.com.sicoob.capes.comum.vo.entidades {
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.ValorChaveGED",
		ValorChaveGEDVO);
	public class ValorChaveGEDVO extends ValorChaveGEDVOBase {
		
		public function ValorChaveGEDVO(idChave:Number = NaN, valorChave:String = null) {
			super(idChave, valorChave);
		}
	}
}