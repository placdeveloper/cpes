package br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.abas {
	import br.com.bancoob.vo.DestinoVO;
	
	import mx.collections.ArrayCollection;
	
	
	public interface IAbaMonitoracaoAtualizacaoCadastral {
		
		function configurarDestino(destino:DestinoVO):void;
		function get mensagens():ArrayCollection;
		function set dados(dados:Object):void;
		function get label():String;
		function set label(value:String):void;
		function get id():String;
	}
}