package br.com.sicoob.capes.comum.vo.entidades{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilTarifario", PerfilTarifarioVO);
    public class PerfilTarifarioVO extends PerfilTarifarioVOBase {
    	
    	public function get chaveComposta() : String {
    		var chave : String = null;
    		if (pk != null) {
    			chave = pk.idInstituicao + "-" + pk.codPerfilTarifario;
    		}
    		return chave;
    	}
    }
}