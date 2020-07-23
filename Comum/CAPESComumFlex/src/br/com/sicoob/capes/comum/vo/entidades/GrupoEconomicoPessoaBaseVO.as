package br.com.sicoob.capes.comum.vo.entidades{
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.tipos.IDateTime;
	
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoPessoaBase<ID>",
		GrupoEconomicoPessoaBaseVO);
	public class GrupoEconomicoPessoaBaseVO extends GrupoEconomicoPessoaBaseVOBase {
		
		public override function get dataHoraInicio() : IDateTime {
			if (super.dataHoraInicio == null) {
				dataHoraInicio = DateTimeBase.getDateTime(new Date());
			}
			return super.dataHoraInicio;
		}   
	}
}