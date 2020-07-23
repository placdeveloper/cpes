package br.com.sicoob.capes.comum.vo.entidades{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicaoEsferaAdministrativa", TipoFormaConstituicaoEsferaAdministrativaVO);
	public class TipoFormaConstituicaoEsferaAdministrativaVO extends TipoFormaConstituicaoEsferaAdministrativaVOBase {
		
		
		public function get codigoTipoFormaConstituicao():Number {
			if(tipoFormaConstituicao != null) {
				return tipoFormaConstituicao.codigo;
			}
			return NaN;
		}

		public function get chaveComposta() : String {
			var chave : String = null;
			if (pk != null) {
				chave = pk.codigoEsferaAdministrativa + "-" + pk.codigoTipoFormaConstituicao;
			}
			return chave;
		}
	}
}