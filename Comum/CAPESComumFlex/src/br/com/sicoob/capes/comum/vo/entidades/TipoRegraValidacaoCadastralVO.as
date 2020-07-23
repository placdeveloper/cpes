package br.com.sicoob.capes.comum.vo.entidades{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoRegraValidacaoCadastral",
		TipoRegraValidacaoCadastralVO);
	public class TipoRegraValidacaoCadastralVO extends TipoRegraValidacaoCadastralVOBase{
		public static const RESTRITIVA:String = "R";
		public static const INFORMATIVA:String = "I";
	}
}