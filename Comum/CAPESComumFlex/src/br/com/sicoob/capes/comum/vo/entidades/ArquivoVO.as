package br.com.sicoob.capes.comum.vo.entidades {
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.Arquivo", ArquivoVO);
	public class ArquivoVO extends ArquivoVOBase {
		
		public function ArquivoVO(nome:String=null, extensao:String=null, path:String=null) {
			super(nome, extensao, path);
		}
	}
}