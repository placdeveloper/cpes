package br.com.sicoob.capes.comum.vo{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.DadosCPFVO",	DadosCPFVO);
	public class DadosCPFVO extends DadosCPFVOBase{
		
		public override function get numeroInscricao():String {
			return cpf;
		}

	}
}