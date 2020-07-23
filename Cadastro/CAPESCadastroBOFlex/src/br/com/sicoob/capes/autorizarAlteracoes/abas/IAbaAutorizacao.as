package br.com.sicoob.capes.autorizarAlteracoes.abas {
	import br.com.bancoob.vo.DestinoVO;

	public interface IAbaAutorizacao{
		function configurarDestino(dest:DestinoVO):void;
		
		function pesquisar(pagina:int = 1):void;

		function get pesquisaRealizada():Boolean;
		function set pesquisaRealizada(value:Boolean):void;
	}
}