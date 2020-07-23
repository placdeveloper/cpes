package br.com.sicoob.capes.comum.vo{
	import flash.net.getClassByAlias;
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.DadosCNPJVO",
		DadosCNPJVO);
	public class DadosCNPJVO extends DadosCNPJVOBase{
		
		public override function get nome():String {
			return nomeEmpresarial;
		}
		
		public override function get numeroInscricao():String {
			return cnpj;
		}
		
	}
}